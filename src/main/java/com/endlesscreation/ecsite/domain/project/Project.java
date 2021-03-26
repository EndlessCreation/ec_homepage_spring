package com.endlesscreation.ecsite.domain.project;

import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
@Getter
public class Project
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String event;

    private String description;

    private String imageUrl;
    private String githubUrl;

    private LocalDate endDate;
    private LocalDate startDate;

    private String presentationUrl;
    private Boolean ecPick;
}
