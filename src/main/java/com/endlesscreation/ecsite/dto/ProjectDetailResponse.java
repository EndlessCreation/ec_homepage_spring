package com.endlesscreation.ecsite.dto;

import java.time.LocalDate;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Getter
@Builder
public class ProjectDetailResponse {

    private Long id;
    private String imageUrl;
    private String name;
    private String description;
    private List<ParticipantResponse> participantResponses;
    private String event;    // 소속 행사 (ex EC어드벤스)
    private LocalDate startDate;
    private LocalDate endDate;
    private List<String> techStack;
    private String presentationUrl;
    private String githubUrl;
    private Boolean ecpick;
}
