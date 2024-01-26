package com.bcg.ebdashboardbackend.entity;

import com.bcg.ebdashboardbackend.model.OwnershipType;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    String district;

    String state;

    String pinCode;

    @Enumerated(EnumType.STRING)
    OwnershipType ownershipType;
}
