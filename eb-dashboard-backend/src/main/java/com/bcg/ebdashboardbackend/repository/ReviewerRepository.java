package com.bcg.ebdashboardbackend.repository;

import com.bcg.ebdashboardbackend.entity.Reviewer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewerRepository  extends JpaRepository<Reviewer, Long> {
}
