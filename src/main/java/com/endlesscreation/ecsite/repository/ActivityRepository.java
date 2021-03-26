package com.endlesscreation.ecsite.repository;

import com.endlesscreation.ecsite.domain.Activity;
import com.endlesscreation.ecsite.domain.Location;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActivityRepository extends JpaRepository<Activity, Long> {

    Page<Activity> findAllByLocation(Pageable pageable, Location location);
    Long countByLocation(Location location);
}
