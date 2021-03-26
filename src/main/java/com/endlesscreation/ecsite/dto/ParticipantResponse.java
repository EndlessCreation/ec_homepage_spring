package com.endlesscreation.ecsite.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Getter
@Builder
public class ParticipantResponse {

    private final Long id;
    private final String name;
    private final Integer generation;
    private final String role;
}
