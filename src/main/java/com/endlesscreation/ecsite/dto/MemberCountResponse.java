package com.endlesscreation.ecsite.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Getter
@Builder
public class MemberCountResponse
{
    private Long studentCount;
    private Long graduateCount;
}