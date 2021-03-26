package com.endlesscreation.ecsite.controller;

import com.endlesscreation.ecsite.domain.Member;
import com.endlesscreation.ecsite.domain.hashtag.MemberHashtag;
import com.endlesscreation.ecsite.dto.MemberCountResponse;
import com.endlesscreation.ecsite.dto.MemberHashtagResponse;
import com.endlesscreation.ecsite.dto.MemberResponse;
import com.endlesscreation.ecsite.dto.PageRequest;
import com.endlesscreation.ecsite.service.MemberService;
import com.endlesscreation.ecsite.vo.MemberAndHashtag;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(tags = {"1. Member"})
@RequiredArgsConstructor
@RestController
@RequestMapping("/members")
public class MemberController {

    private final MemberService memberService;

    @ApiOperation(value = "임원진 조회", notes = "모든 임원진을 조회한다")
    @GetMapping("/executives")
    public ResponseEntity<List<MemberHashtagResponse>> findExecutives()
    {  // 임원진
        return ResponseEntity.ok()
                .body(memberService.findExecutives());
    }

    @ApiOperation(value = "일반 부원 조회", notes = "모든 일반 부원을 조회한다")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "page", value = "페이지 번호(몇번째 페이지인지. 0부터 시작)", required = false),
        @ApiImplicitParam(name = "size", value = "페이지 당 데이터 크기(한 페이지 당 몇 명?)", required = false)
    })
    @GetMapping("/students")
    public ResponseEntity<List<MemberHashtagResponse>> findStudents(@PageableDefault(page = 0, size = 987654321) Pageable pageable)
    {
        return ResponseEntity.ok()
                .body(memberService.findStudents(pageable));
    }

    @ApiOperation(value = "졸업생 조회", notes = "모든 졸업생을 조회한다")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "page", value = "페이지 번호(몇번째 페이지인지. 0부터 시작)", required = false),
        @ApiImplicitParam(name = "size", value = "페이지 당 데이터 크기(한 페이지 당 몇 명?)", required = false)
    })
    @GetMapping("/graduates")
    public ResponseEntity<List<MemberResponse>> findGraduates(@PageableDefault(page = 0, size = 987654321) Pageable pageable)
    {
        return ResponseEntity.ok()
                .body(memberService.findGraduates(pageable));
    }

    @GetMapping("/counts")
    public ResponseEntity<MemberCountResponse> count()
    {
        return ResponseEntity.ok()
                .body(memberService.findStudentsCount());
    }
}
