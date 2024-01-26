package com.bcg.ebdashboardbackend.repository;

import com.bcg.ebdashboardbackend.entity.Reviewer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewerRepository  extends JpaRepository<Reviewer, Integer> {
}
