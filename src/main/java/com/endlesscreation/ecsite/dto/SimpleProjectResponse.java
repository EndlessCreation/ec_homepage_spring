package com.endlesscreation.ecsite.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Builder
@Getter
public class SimpleProjectResponse {

    private Long id;
    private String imageUrl;
}
