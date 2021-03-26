package com.endlesscreation.ecsite.domain;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class Member
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int generation;

    private String imageUrl;

    private String name;

    @Enumerated(EnumType.STRING)
    private Position position;

    private Boolean part;
}
