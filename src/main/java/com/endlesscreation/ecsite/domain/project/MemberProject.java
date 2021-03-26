package com.endlesscreation.ecsite.domain.project;

import com.endlesscreation.ecsite.domain.Member;
import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class MemberProject
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String role;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id")
    private Project project;
}
