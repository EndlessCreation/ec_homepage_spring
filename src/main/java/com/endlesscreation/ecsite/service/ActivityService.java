package com.endlesscreation.ecsite.service;

import static org.springframework.data.domain.PageRequest.of;

import com.endlesscreation.ecsite.domain.Activity;
import com.endlesscreation.ecsite.domain.Location;
import com.endlesscreation.ecsite.dto.ActivityResponse;
import com.endlesscreation.ecsite.dto.ClearAndSaveActivitiesRequest;
import com.endlesscreation.ecsite.dto.CountResponse;
import com.endlesscreation.ecsite.repository.ActivityRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class ActivityService {

    private final ActivityRepository activityRepository;

    @Transactional
    public void clearAndSave(List<ClearAndSaveActivitiesRequest> request) {
        validate(request);

        activityRepository.deleteAll();
        // Todo: 파일 시스템에 있는 파일들도 삭제할 것

        request.forEach(req -> {
            Activity activity = new Activity(req.getImageUrl(), req.getLocation());
            activityRepository.save(activity);
        });
    }

    private void validate(List<ClearAndSaveActivitiesRequest> request) {
        request.forEach(req -> {
            String imageUrl = req.getImageUrl();
            Location location = req.getLocation();
            if (imageUrl.isBlank()) {
                throw new IllegalArgumentException("활동 사진의 URL이 비어있습니다.");
            }
            if (location == null){
                throw new IllegalArgumentException("활동 사진의 보여질 위치가 비어있습니다.");
            }
        });
    }

    public List<ActivityResponse> findAllByMain(Pageable pageable) {
        Page<Activity> activities = activityRepository.findAllByLocation(pageable, Location.MAIN);
        return getActivityResponses(activities);
    }

    public List<ActivityResponse> findAllByDetail(Pageable pageable) {
        Page<Activity> activities = activityRepository.findAllByLocation(pageable, Location.DETAIL);
        return getActivityResponses(activities);
    }

    private List<ActivityResponse> getActivityResponses(Page<Activity> activities) {
        List<ActivityResponse> res = new ArrayList<>();
        if (Objects.isNull(activities) || activities.isEmpty()) {
            return Collections.emptyList();
        }

        activities.forEach(activity -> { res.add(
                ActivityResponse.builder()
                        .imageUrl(activity.getImageUrl())
                        .location(activity.getLocation())
                        .build());
        });

        return res;
    }

    public CountResponse count() {
        return CountResponse.builder()
                .count(activityRepository.countByLocation(Location.DETAIL))
                .build();
    }
}
