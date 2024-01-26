package com.bcg.ebdashboardbackend.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Reviewer {

    @Id
    private Long id;

    private String name;

//    @OneToMany(mappedBy = "reviewer", cascade = CascadeType.ALL)
//    List<Connection> connections;

}
