package com.endlesscreation.ecsite.dto;

import com.endlesscreation.ecsite.domain.Location;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Getter
@Builder
public class ActivityResponse {
    private final String imageUrl;
    private final Location location;
}
