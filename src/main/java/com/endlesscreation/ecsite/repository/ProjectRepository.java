package com.endlesscreation.ecsite.repository;

import com.endlesscreation.ecsite.domain.project.Project;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProjectRepository extends JpaRepository<Project, Long> {
    public List<Project> findByEcPick(Boolean ecPick);

    public Page<Project> findByEcPick(Pageable pageable, Boolean ecPick);

    public Long countByEcPick(Boolean ecPick);
}
