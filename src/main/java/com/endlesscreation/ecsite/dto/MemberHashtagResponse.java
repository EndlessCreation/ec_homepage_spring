package com.endlesscreation.ecsite.dto;

import com.endlesscreation.ecsite.domain.Position;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
@Builder
public class MemberHashtagResponse
{
    private Long id;
    private String name;
    private int generation;    // 동아리 기수
    private Position position;
    private Boolean part;
    private List<String> hashTags;
    private String imageUrl;
}
