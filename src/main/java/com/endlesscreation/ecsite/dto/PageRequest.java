package com.endlesscreation.ecsite.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class PageRequest {

    private Integer page;    // 페이지번호
    private Integer size;    // 페이지 당 데이터 크기
}
