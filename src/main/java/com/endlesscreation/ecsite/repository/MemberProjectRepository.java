package com.endlesscreation.ecsite.repository;

import com.endlesscreation.ecsite.domain.project.MemberProject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface MemberProjectRepository extends JpaRepository<MemberProject, Long> {

    @Query(value = "select DISTINCT mp from MemberProject mp join fetch mp.member m join fetch mp.project p where p.id=:id")
    public List<MemberProject> findAllByProject_Id(Long id);
}
