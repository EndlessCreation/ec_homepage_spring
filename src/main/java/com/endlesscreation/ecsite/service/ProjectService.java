package com.endlesscreation.ecsite.service;

import com.endlesscreation.ecsite.domain.project.MemberProject;
import com.endlesscreation.ecsite.domain.project.Project;
import com.endlesscreation.ecsite.domain.project.Tech;
import com.endlesscreation.ecsite.dto.CountResponse;
import com.endlesscreation.ecsite.dto.ParticipantResponse;
import com.endlesscreation.ecsite.dto.ProjectDetailResponse;
import com.endlesscreation.ecsite.dto.SimpleProjectResponse;
import com.endlesscreation.ecsite.repository.MemberProjectRepository;
import com.endlesscreation.ecsite.repository.ProjectRepository;
import com.endlesscreation.ecsite.repository.TechRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class ProjectService {

    private final ProjectRepository projectRepository;
    private final MemberProjectRepository memberProjectRepository;
    private final TechRepository techRepository;

    public List<SimpleProjectResponse> findEcPickProjects() {
        List<Project> ecpicks = projectRepository.findByEcPick(true);
        List<SimpleProjectResponse> res = new ArrayList<SimpleProjectResponse>();
        ecpicks.forEach(ecpick -> {
            res.add(
                    SimpleProjectResponse.builder()
                            .id(ecpick.getId())
                            .imageUrl(ecpick.getImageUrl())
                            .build());
        });

        return res;
    }

    public List<SimpleProjectResponse> findNormalProjects(Pageable pageable) {
        Page<Project> normalProjects = projectRepository.findByEcPick(pageable, false);
        List<SimpleProjectResponse> res = new ArrayList<SimpleProjectResponse>();
        normalProjects.forEach(project -> {
            res.add(
                    SimpleProjectResponse.builder()
                            .id(project.getId())
                            .imageUrl(project.getImageUrl())
                            .build());
        });

        return res;
    }

    public CountResponse countNormalProjects() {
        return CountResponse.builder()
                .count(projectRepository.countByEcPick(false))
                .build();
    }

    public ProjectDetailResponse findProjectById(Long id) throws ParseException {
        List<MemberProject> projectWithMember = memberProjectRepository.findAllByProject_Id(id);
        List<ParticipantResponse> participantResponses = new ArrayList<ParticipantResponse>();
        Project project = projectWithMember.get(0).getProject();

        projectWithMember.forEach(relation -> {
            participantResponses.add(
                    ParticipantResponse.builder()
                            .id(relation.getMember().getId())
                            .name(relation.getMember().getName())
                            .generation(relation.getMember().getGeneration())
                            .role(relation.getRole())
                            .build());
        });

        List<Tech> techs = techRepository.findAllByProject_Id(id);
        List<String> techStack = new ArrayList<String>();

        techs.forEach(tech -> {
            techStack.add(
                    tech.getTech());
        });

        return ProjectDetailResponse.builder()
                .id(project.getId())
                .name(project.getName())
                .imageUrl(project.getImageUrl())
                .description(project.getDescription())
                .participantResponses(participantResponses)
                .event(project.getEvent())
                .startDate(project.getStartDate())
                .endDate(project.getEndDate())
                .techStack(techStack)
                .presentationUrl(project.getPresentationUrl())
                .githubUrl(project.getGithubUrl())
                .ecpick(project.getEcPick())
                .build();
    }
}
