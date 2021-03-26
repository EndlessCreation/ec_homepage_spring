package com.endlesscreation.ecsite.domain;

import java.util.Objects;
import javax.persistence.*;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Activity
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String imageUrl;

    @Enumerated(EnumType.STRING)
    private Location location;

    public Activity(String imageUrl, Location location) {
        this(null, imageUrl, location);
    }

    private Activity(Long id, String imageUrl, Location location) {
        validateImageUrl(imageUrl);
        this.id = id;
        this.imageUrl = imageUrl;
        this.location = location;
    }

    private void validateImageUrl(String imageUrl) {
        if (Objects.isNull(imageUrl) || "".equals(imageUrl)) {
            throw new IllegalArgumentException("image url 이 비어있습니다.");
        }
        if (!imageUrl.startsWith("http")) {
            throw new IllegalArgumentException("url 형식이 올바르지 않습니다 - url : " + imageUrl);
        }
    }
}
