package com.bcg.ebdashboardbackend.repository;

import com.bcg.ebdashboardbackend.entity.Connection;
import com.bcg.ebdashboardbackend.model.ApplicationStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConnectionRepository extends JpaRepository<Connection, Long> {

    List<Connection> findAllByYearOfRequest(String year);

    @Query("SELECT COUNT(e) FROM Connection e WHERE e.applicationStatus = ?1")
    Long countByStatus(ApplicationStatus applicationStatus);

}
