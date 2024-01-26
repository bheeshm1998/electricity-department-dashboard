package com.bcg.ebdashboardbackend.entity;

import com.bcg.ebdashboardbackend.model.IdType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
//@Table(uniqueConstraints = {@UniqueConstraint(name = "unique_idType_idNumber", columnNames = {"idType", "idNumber"})})
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GovtIdentification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    IdType idType;

    String idNumber;

    @OneToOne(mappedBy = "govtIdentification")
    @JoinColumn(name="customer_id")
    Customer customer;
}
