package com.bcg.ebdashboardbackend.batchProcess;

import com.bcg.ebdashboardbackend.entity.Connection;
import com.bcg.ebdashboardbackend.entity.Reviewer;

import com.bcg.ebdashboardbackend.repository.ConnectionRepository;
import com.bcg.ebdashboardbackend.repository.ReviewerRepository;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
public class JpaItemWriter implements ItemWriter<Connection> {

    @Autowired
    private ConnectionRepository connectionRepository;

    @Autowired
    private ReviewerRepository reviewerRepository;

    @Autowired
    private EntityManager entityManager;


    @Override
    public void write(List<? extends Connection> connections) throws Exception {
        for(Connection connection : connections){
            Reviewer existingReviewer = reviewerRepository.findById(connection.getReviewer().getId()).orElse(null);
            if (existingReviewer == null) {
                reviewerRepository.save(connection.getReviewer());
            }
            connection.setReviewer(existingReviewer != null ? existingReviewer : connection.getReviewer());
            connection.setReviewerId(connection.getReviewer().getId());
            if(connectionRepository.findById(connection.getId()).orElse(null) == null){
                log.info("Saving into the Database");
                connectionRepository.save(connection);
            }
        }
    }
}

