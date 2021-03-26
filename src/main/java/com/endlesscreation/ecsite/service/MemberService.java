package com.endlesscreation.ecsite.service;

import com.endlesscreation.ecsite.domain.Member;
import com.endlesscreation.ecsite.domain.Position;
import com.endlesscreation.ecsite.domain.hashtag.MemberHashtag;
import com.endlesscreation.ecsite.dto.MemberCountResponse;
import com.endlesscreation.ecsite.dto.MemberHashtagResponse;
import com.endlesscreation.ecsite.dto.MemberResponse;
import com.endlesscreation.ecsite.repository.MemberHashtagRepository;
import com.endlesscreation.ecsite.repository.MemberRepository;
import com.endlesscreation.ecsite.util.*;
import com.endlesscreation.ecsite.vo.MemberAndHashtag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final MemberHashtagRepository memberHashtagRepository;

    public List<MemberHashtagResponse> findExecutives() {
        List<Position> positions = Arrays.asList(Position.KING, Position.PRESIDENT, Position.VICE_PRESIDENT, Position.ACADEMIC,
                Position.INFORMATION, Position.MANAGER, Position.PROMOTION,
                Position.PERSONNEL);

        List<MemberHashtag> memberHashtagList = memberHashtagRepository.findAllByMember_Position(positions);

        Set<Member> members = new HashSet<>();

        memberHashtagList.forEach(member ->
        {
            members.add(member.getMember());
        });

        List<MemberHashtagResponse> memberHashtagResponses = new ArrayList<>();

        members.forEach(member ->
        {
            List<String> hashtags = new ArrayList<>();
            memberHashtagList.forEach(memberHashtag ->
            {
                if (member.getId().equals(memberHashtag.getMember().getId())) {
                    hashtags.add(memberHashtag.getHashtag().getName());
                }
            });
            memberHashtagResponses.add(MemberHashtagResponse.builder()
                    .id(member.getId())
                    .generation(member.getGeneration())
                    .name(member.getName())
                    .imageUrl(member.getImageUrl())
                    .position(member.getPosition())
                    .part(member.getPart())
                    .hashTags(hashtags)
                    .build());
        });

        Collections.sort(memberHashtagResponses, new StudentPositionComparator());
        return memberHashtagResponses;
    }

    public List<MemberHashtagResponse> findStudents(Pageable pageable) {

        List<MemberAndHashtag> memberAndHashtags = memberRepository.findAllBy(Position.STUDENT);
        Set<Member> members = new HashSet<>();

        memberAndHashtags.forEach(memberAndHashtag -> {
            members.add(memberAndHashtag.getMember());
        });

        List<MemberHashtagResponse> memberHashtagResponses = new ArrayList<>();

        members.forEach(member -> {
            List<String> hashtags = new ArrayList<>();
            memberAndHashtags.forEach(memberAndHashtag -> {
                if(member.getId().equals(memberAndHashtag.getMember().getId()) &&
                        memberAndHashtag.getHashtag() != null){
                    hashtags.add(memberAndHashtag.getHashtag().getName());
                }
            });
            memberHashtagResponses.add(MemberHashtagResponse.builder()
                    .id(member.getId())
                    .generation(member.getGeneration())
                    .name(member.getName())
                    .imageUrl(member.getImageUrl())
                    .position(member.getPosition())
                    .part(member.getPart())
                    .hashTags(hashtags)
                    .build());
        });

        memberHashtagResponses.sort(new StudentNameComparator());
        memberHashtagResponses.sort(new StudentPartComparator());
        memberHashtagResponses.sort(new StudentGenerationComparator());

        return memberHashtagResponses;
    }

    public List<MemberResponse> findGraduates(Pageable pageable) {
        Page<Member> memberList = memberRepository.findAllByPosition(Position.GRADUATE, pageable);
        List<MemberResponse> memberResponses = new ArrayList<>();

        memberList.forEach(member ->
        {
            memberResponses.add(MemberResponse.builder()
                    .id(member.getId())
                    .generation(member.getGeneration())
                    .name(member.getName())
                    .imageUrl(member.getImageUrl())
                    .part(member.getPart())
                    .position(member.getPosition())
                    .build());
        });

        memberResponses.sort(new GraduateNameComparator());
        memberResponses.sort(new GraduateGenerationComparator());
        return memberResponses;
    }

    public MemberCountResponse findStudentsCount() {
        Long studentCount = memberRepository.countByPosition(Position.STUDENT);
        Long graduateCount = memberRepository.countByPosition(Position.GRADUATE);

        return new MemberCountResponse(studentCount, graduateCount);
    }
}
