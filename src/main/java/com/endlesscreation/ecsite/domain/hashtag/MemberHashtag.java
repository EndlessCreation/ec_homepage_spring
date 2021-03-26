package com.endlesscreation.ecsite.domain.hashtag;

import com.endlesscreation.ecsite.domain.Member;
import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class MemberHashtag
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hashTag_id")
    private Hashtag hashtag;

    private int orderBy;
}
