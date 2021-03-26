package com.endlesscreation.ecsite.repository;

import com.endlesscreation.ecsite.domain.Member;
import com.endlesscreation.ecsite.domain.Position;
import com.endlesscreation.ecsite.vo.MemberAndHashtag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.EnumSet;
import java.util.List;

public interface MemberRepository extends JpaRepository<Member, Long>
{
    Long countByPosition(Position position);

    Page<Member> findAllByPosition(Position position, Pageable pageable);

    @Query(value = "SELECT DISTINCT m as member, h as hashtag FROM Member m left join fetch MemberHashtag mh ON m.id = mh.member.id left join fetch Hashtag h ON h.id = mh.hashtag.id where m.position = :position")
    List<MemberAndHashtag> findAllBy(Position position);
}