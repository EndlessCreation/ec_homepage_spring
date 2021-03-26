package com.endlesscreation.ecsite.controller;

import com.endlesscreation.ecsite.dto.ActivityResponse;
import com.endlesscreation.ecsite.dto.ClearAndSaveActivitiesRequest;
import com.endlesscreation.ecsite.dto.CountResponse;
import com.endlesscreation.ecsite.dto.PageRequest;
import com.endlesscreation.ecsite.service.ActivityService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = {"2. 동아리 활동 사진"})
@RequiredArgsConstructor
@RestController
@RequestMapping("/activities")
public class ActivityController {

    private final ActivityService activityService;

    @ApiOperation(value = "활동 사진들 새로 올리기", notes = "기존의 모든 활동 사진들을 삭제하고 새로운 사진들로 셋팅한다.")
    @PutMapping
    public ResponseEntity<Void> clearAndSave(@RequestBody List<ClearAndSaveActivitiesRequest> request) {
        activityService.clearAndSave(request);

        return ResponseEntity.ok()
            .build();
    }

    @ApiOperation(value = "Activity 탭의 활동 사진의 총 갯수", notes = "Activity 탭에 들어갈 활동 사진 갯수를 조회한다")
    @GetMapping("/detail/count")
    public ResponseEntity<CountResponse> count() {
        return ResponseEntity.ok()
            .body(activityService.count());
    }

    @ApiOperation(value = "Main Page 활동 사진 조회", notes = "Main Page에 들어갈 동아리 활동 사진들을 조회한다")
    @GetMapping("/main")
    public ResponseEntity<List<ActivityResponse>> findAllByMain(@PageableDefault(page = 0, size = 987654321) Pageable pageable) {
        return ResponseEntity.ok()
            .body(activityService.findAllByMain(pageable));
    }

    @ApiOperation(value = "Activity 탭의 활동 사진 조회", notes = "Activity 탭에 들어갈 동아리 활동 사진들을 조회한다")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "페이지 번호(몇번째 페이지인지. 0부터 시작)", required = false),
            @ApiImplicitParam(name = "size", value = "페이지 당 데이터 크기(한 페이지 당 몇 명?)", required = false)
    })
    @GetMapping("/detail")
    public ResponseEntity<List<ActivityResponse>> findAllByDetail(@PageableDefault(page = 0, size = 987654321) Pageable pageable) {
        return ResponseEntity.ok()
                .body(activityService.findAllByDetail(pageable));
    }
}
