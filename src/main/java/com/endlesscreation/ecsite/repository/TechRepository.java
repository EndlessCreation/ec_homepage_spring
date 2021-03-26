package com.endlesscreation.ecsite.repository;

import com.endlesscreation.ecsite.domain.project.MemberProject;
import com.endlesscreation.ecsite.domain.project.Tech;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface TechRepository extends JpaRepository<Tech, Long> {

    @Query("select DISTINCT t from Tech t join fetch t.project where t.project.id=:id")
    public List<Tech> findAllByProject_Id(Long id);
}
