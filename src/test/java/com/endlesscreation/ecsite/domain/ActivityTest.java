package com.endlesscreation.ecsite.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ActivityTest {

    private static final String TEST_IMAGE_URL = "https://news.samsungdisplay.com/wp-content/uploads/2018/08/8.jpg";

    @Test
    @DisplayName("Activity 생성")
    void constructor() {
        Activity activity = new Activity(TEST_IMAGE_URL);

        assertThat(activity.getId()).isNull();
        assertThat(activity.getImageUrl()).isEqualTo(TEST_IMAGE_URL);
    }

    @Test
    @DisplayName("Activity 생성 - URL이 null인 경우 예외처리")
    void constructor_IfUrlIsNull_ThrowException() {
        assertThatThrownBy(() -> new Activity(null))
            .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("Activity 생성 - URL이 http로 시작하지 않을 경우 예외처리")
    void constructor_IfUrlDoNotStartsWithHttp_ThrowException() {
        assertThatThrownBy(() -> new Activity(" a into MEMBER"))
            .isInstanceOf(IllegalArgumentException.class);
    }
}