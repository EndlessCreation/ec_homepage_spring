package com.endlesscreation.ecsite.dto;

import java.util.List;

import com.endlesscreation.ecsite.domain.Position;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Getter
@Builder
public class MemberResponse {

    private Long id;
    private String name;
    private int generation;    // 동아리 기수
    private Position position;
    private Boolean part;
    private String imageUrl;
}
