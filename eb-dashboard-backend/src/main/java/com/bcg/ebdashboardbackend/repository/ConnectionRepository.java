package com.bcg.ebdashboardbackend.repository;

import com.bcg.ebdashboardbackend.entity.Connection;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConnectionRepository extends JpaRepository<Connection, Long> {

}
