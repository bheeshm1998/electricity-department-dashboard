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

//    @Autowired
//    private UserRepository userRepository;




    @Override
    public void write(List<? extends Connection> connections) throws Exception {
        log.info("Inside the writer method");
//        log.info("Writing the connections to the DB");
//        List<Person> list = new ArrayList<>();
//        list.add(Person.builder()
//                        .name("ABC")
//                        .reviewer(Reviewer.builder()
//                                .id(101L)
//                                .name("XXX")
//                                .build())
//                .build());
//        list.add(Person.builder()
//                .name("DFG")
//                .reviewer(Reviewer.builder()
//                        .id(102L)
//                        .name("YYY")
//                        .build())
//                .build());
//        list.add(Person.builder()
//                .name("SDF")
//                .reviewer(Reviewer.builder()
//                        .id(101L)
//                        .name("XXX")
//                        .build())
//                .build());
//        for(Person user: list){
//            log.info("Saving int to db " + user.getName());
//            Reviewer existingReviewer = reviewerRepository.findById(user.getReviewer().getId()).orElse(null);
//            if (existingReviewer == null) {
//                reviewerRepository.save(user.getReviewer());
//            }
//            user.setReviewer(existingReviewer != null ? existingReviewer : user.getReviewer());
//            userRepository.save(user);
//        }
        for(Connection connection : connections){
            Reviewer existingReviewer = reviewerRepository.findById(connection.getReviewer().getId()).orElse(null);
            if (existingReviewer == null) {
                reviewerRepository.save(connection.getReviewer());
            }
            connection.setReviewer(existingReviewer != null ? existingReviewer : connection.getReviewer());
            connection.setReviewerId(connection.getReviewer().getId());
            if(connectionRepository.findById(connection.getId()).orElse(null) == null){
                log.info("Saving into the repo");
                connectionRepository.save(connection);
            }
        }
    }
}

