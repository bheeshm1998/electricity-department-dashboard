package com.bcg.ebdashboardbackend.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ConnectionsPage {

    Long totalLength;

    List<ConnectionRequestDTO> connections;
}
