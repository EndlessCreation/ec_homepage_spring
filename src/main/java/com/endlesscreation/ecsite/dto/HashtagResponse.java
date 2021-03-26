package com.endlesscreation.ecsite.dto;

import com.endlesscreation.ecsite.domain.hashtag.Hashtag;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Getter
@Builder
public class HashtagResponse
{
    private Hashtag hashTags;
}
