package com.endlesscreation.ecsite.domain.project;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class Tech
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String tech;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id")
    private Project project;
}
