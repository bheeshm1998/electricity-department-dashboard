package com.bcg.ebdashboardbackend.entity;

import com.bcg.ebdashboardbackend.model.ApplicationStatus;
import com.bcg.ebdashboardbackend.model.PlotType;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Positive;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Connection {

    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "customer_id")
    Customer customer;

    @Positive(message = "Load must be a positive")
    Float loadAppliedInKW;

    LocalDate applicationDate;

    LocalDate approvalDate;

    LocalDate modifiedDate;

    @Enumerated(EnumType.STRING)
    ApplicationStatus applicationStatus;

    @Enumerated(EnumType.STRING)
    PlotType plotType;

    Long reviewerId;

//    @ManyToOne(cascade = CascadeType.PERSIST)
//    @JoinColumn(name = "reviewer_id")
    @Transient
    Reviewer reviewer;

    @Column(columnDefinition = "TEXT")
    String reviewerComments;

}
