package com.endlesscreation.ecsite.controller;

import com.endlesscreation.ecsite.dto.CountResponse;
import com.endlesscreation.ecsite.dto.ProjectDetailResponse;
import com.endlesscreation.ecsite.dto.SimpleProjectResponse;
import com.endlesscreation.ecsite.service.ProjectService;
import io.swagger.annotations.*;

import java.text.ParseException;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = {"3. Projects"})
@RestController
@RequestMapping("/projects")
public class ProjectController {

    private final ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @ApiOperation(value = "EC Pick 목록 조회", notes = "EC Pick들의 간단한 정보를 조회한다.")
    @GetMapping("/ecpick")
    public ResponseEntity<List<SimpleProjectResponse>> findEcPickProjects() {
        return ResponseEntity.ok()
            .body(projectService.findEcPickProjects());
    }

    @ApiOperation(value = "일반 프로젝트 목록 조회", notes = "일반 프로젝트들의 간단한 정보를 조회한다. 꼭 page와 size 파라미터를 작성해야 한다.")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "page", value = "페이지 번호(몇번째 페이지인지. 0부터 시작)", required = false),
        @ApiImplicitParam(name = "size", value = "페이지 당 데이터 크기(한 페이지 당 몇 명?)", required = false)
    })
    @GetMapping("/normal")
    public ResponseEntity<List<SimpleProjectResponse>> findNormalProjects(@PageableDefault(page = 0, size = 987654321) Pageable pageable) {
        return ResponseEntity.ok()
                .body(projectService.findNormalProjects(pageable));
    }

    @ApiOperation(value = "일반 프로젝트 개수 조회", notes = "페이징 처리를 위한 프로잭트 개수를 조회한다.")
    @GetMapping("/normal/count")
    public ResponseEntity<CountResponse> countNormalProjects() {
        return ResponseEntity.ok()
                .body(projectService.countNormalProjects());
    }

    @ApiOperation(value = "프로젝트 세부 조회", notes = "프로젝트 하나의 자세한 내용을 조회한다")
    @GetMapping("/{id}")
    public ResponseEntity<ProjectDetailResponse> findProjectById(
            @ApiParam(name = "id", value = "조회할 프로젝트 id", example = "100", required = true)
            @PathVariable Long id)
            throws ParseException {
        return ResponseEntity.ok()
            .body(projectService.findProjectById(id));
    }
}
