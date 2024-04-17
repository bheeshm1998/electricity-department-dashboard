package com.bcg.ebdashboardbackend.repository;

import com.bcg.ebdashboardbackend.entity.Connection;
import com.bcg.ebdashboardbackend.model.ApplicationStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Repository
public interface ConnectionRepository extends JpaRepository<Connection, Long> {

    List<Connection> findAllByYearOfRequest(String year);

    @Query("SELECT COUNT(e) FROM Connection e WHERE e.applicationStatus = ?1")
    Long countByStatus(ApplicationStatus applicationStatus);


    @Query("SELECT e FROM Connection e WHERE (:filterParam IS NULL OR e.idAsString LIKE LOWER(CONCAT('%', :filterParam, '%'))) " +
            "AND (e.applicationDate2 BETWEEN :startDate AND :endDate) ORDER BY e.id")
    Page<Connection> findBySearchKeyAndDateRange(@Param("filterParam") String filter, @Param("startDate") Date startDate,
                                                 @Param("endDate") Date endDate, Pageable pageable);

}
