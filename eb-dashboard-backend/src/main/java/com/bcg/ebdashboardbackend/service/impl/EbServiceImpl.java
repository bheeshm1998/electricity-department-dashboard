package com.bcg.ebdashboardbackend.service.impl;

import com.bcg.ebdashboardbackend.dto.*;
import com.bcg.ebdashboardbackend.entity.Connection;
import com.bcg.ebdashboardbackend.entity.Reviewer;
import com.bcg.ebdashboardbackend.model.ApplicationStatus;
import com.bcg.ebdashboardbackend.repository.ConnectionRepository;
import com.bcg.ebdashboardbackend.repository.ReviewerRepository;
import com.bcg.ebdashboardbackend.service.EbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Service
public class EbServiceImpl implements EbService {
    @Autowired
    ConnectionRepository connectionRepository;

    @Autowired
    ReviewerRepository reviewerRepository;

    @Override
    public CustomerDetailDTO getCustomerDetails(Long id) {
        Optional<Connection> optionalConnectionr = connectionRepository.findById(id);
        return optionalConnectionr.map(connection -> convertConnectionToCustomerDetailDTO.apply(connection)).orElse(null);
    }

    @Override
    public List<ConnectionRequestDTO> getListOfConnectionRequests(Integer pageSize, Integer pageNumber) {
        Pageable page = PageRequest.of(pageNumber, pageSize);

        Page<Connection> connectionPage = connectionRepository.findAll(page);
        List<Connection> customerList = connectionPage.getContent();
        List<ConnectionRequestDTO> connectionRequestDTOList = customerList.stream().map(convertCustomerToConnectionDto).toList();
        return connectionRequestDTOList;
    }

    @Override
    public BasicStatsDTO getBasicStats() {
        Long pending = connectionRepository.countByStatus(ApplicationStatus.PENDING);
        Long approved = connectionRepository.countByStatus(ApplicationStatus.APPROVED);
        Long released = connectionRepository.countByStatus(ApplicationStatus.CONNECTION_RELEASED);
        Long rejected = connectionRepository.countByStatus(ApplicationStatus.REJECTED);

        return BasicStatsDTO.builder()
                .numConnectionsReleased(released)
                .numRequestsApproved(approved)
                .numRequestsRejected(rejected)
                .numRequestsPending(pending)
                .build();
    }

    @Override
    public MonthlyChartDataDTO getChartDataForAYear(String year) {
        List<Connection> connections = connectionRepository.findAllByYearOfRequest(year);
        MonthlyChartDataDTO chartData = new MonthlyChartDataDTO();
        for(Connection connection: connections){
            int monthIndex = connection.getApplicationDate().getMonthValue();
            switch (connection.getApplicationStatus()){
                case APPROVED -> chartData.getTotalRequestsApprovedEveryMonth().set(monthIndex-1, chartData.getTotalRequestsApprovedEveryMonth().get(monthIndex - 1) + 1);
                case REJECTED -> chartData.getTotalRequestsRejectedEveryMonth().set(monthIndex-1, chartData.getTotalRequestsRejectedEveryMonth().get(monthIndex - 1) + 1);
                case CONNECTION_RELEASED -> chartData.getTotalConnectionsReleasedEveryMonth().set(monthIndex-1, chartData.getTotalConnectionsReleasedEveryMonth().get(monthIndex - 1) + 1);
                case PENDING -> chartData.getTotalRequestsPendingEveryMonth().set(monthIndex-1, chartData.getTotalRequestsPendingEveryMonth().get(monthIndex - 1) + 1);
            }
        }
        return chartData;
    }

    @Override
    public CustomerDetailDTO updateTheDetails(RequestUpdateDTO request) {
        Long id = request.getId();
        Connection connection = connectionRepository.findById(id).orElse(null);

        if(connection != null){
            if(request.getLoadAppliedInKW() < 1 || request.getLoadAppliedInKW() > 200){
                throw new RuntimeException("INVALID_LOAD_CAPACITY");
            }
            connection.setReviewerComments(request.getReviewerComments());
            connection.setModifiedDate(LocalDate.now());
            connection.setLoadAppliedInKW(request.getLoadAppliedInKW());
            connection.setApplicationStatus(ApplicationStatus.valueOf(request.getApplicationStatus()));
            if(ApplicationStatus.valueOf(request.getApplicationStatus()).equals(ApplicationStatus.APPROVED)){
                connection.setApprovalDate(LocalDate.now());
            }
            connectionRepository.save(connection);
        } else{
            throw new RuntimeException("APPLICANT_DOES_NOT_EXIST");
        }
        return convertConnectionToCustomerDetailDTO.apply(connection);
    }

    @Override
    public  ConnectionsPage findByFilterAndDateRange(String filter, LocalDate startDate, LocalDate endDate, Pageable pageable) {
        Page<Connection> connections =  connectionRepository.findBySearchKeyAndDateRange(filter, Date.valueOf(startDate), Date.valueOf(endDate), pageable);

        List<ConnectionRequestDTO> connectionRequestDTOList = connections.stream().map(convertCustomerToConnectionDto).toList();
        ConnectionsPage page = new ConnectionsPage();
        page.setTotalLength(connections.getTotalElements());
        page.setConnections(connectionRequestDTOList);
        return page;
    }

    Function<Connection, ConnectionRequestDTO> convertCustomerToConnectionDto = (conn) -> {

        Optional<Reviewer> optionalReviewer = Optional.ofNullable(reviewerRepository.findById(conn.getReviewerId()).orElse(null));
        Reviewer reviewer = optionalReviewer.isPresent() ? optionalReviewer.get() : null;

        ConnectionRequestDTO dto = new ConnectionRequestDTO();
        dto.setId(conn.getId());
        dto.setName(conn.getCustomer().getName());
        dto.setGender(conn.getCustomer().getGender());
        dto.setApplicationDate(conn.getApplicationDate());
        dto.setModifiedDate(conn.getModifiedDate());
        dto.setApprovalDate(conn.getApprovalDate());
        dto.setOwnershipType(conn.getCustomer().getAddress().getOwnershipType());
        dto.setPinCode(conn.getCustomer().getAddress().getPinCode());
        dto.setDistrict(conn.getCustomer().getAddress().getDistrict());
        dto.setState(conn.getCustomer().getAddress().getState());
        dto.setLoadAppliedInKW(conn.getLoadAppliedInKW());
        dto.setPlotType(conn.getPlotType());
        dto.setApplicationStatus(conn.getApplicationStatus());
        dto.setReviewerName(reviewer != null ? reviewer.getName() : null);
        return dto;
    };

    Function<Connection, CustomerDetailDTO> convertConnectionToCustomerDetailDTO = (conn) -> {

        Optional<Reviewer> optionalReviewer = Optional.ofNullable(reviewerRepository.findById(conn.getReviewerId()).orElse(null));
        Reviewer reviewer = optionalReviewer.isPresent() ? optionalReviewer.get() : null;
        CustomerDetailDTO dto = new CustomerDetailDTO();
        dto.setId(conn.getId());
        dto.setName(conn.getCustomer().getName());
        dto.setGender(conn.getCustomer().getGender());
        dto.setApplicationDate(conn.getApplicationDate());
        dto.setModifiedDate(conn.getModifiedDate());
        dto.setApprovalDate(conn.getApprovalDate());
        dto.setOwnershipType(conn.getCustomer().getAddress().getOwnershipType());
        dto.setPinCode(conn.getCustomer().getAddress().getPinCode());
        dto.setDistrict(conn.getCustomer().getAddress().getDistrict());
        dto.setState(conn.getCustomer().getAddress().getState());
        dto.setLoadAppliedInKW(conn.getLoadAppliedInKW());
        dto.setPlotType(conn.getPlotType());
        dto.setIdNumber(conn.getCustomer().getGovtIdentification().getIdNumber());
        dto.setApplicationStatus(conn.getApplicationStatus());
        dto.setReviewerComments(conn.getReviewerComments());
        dto.setReviewerName(reviewer != null ? reviewer.getName() : null);
        dto.setGovtIdType(conn.getCustomer().getGovtIdentification().getIdType());

        return dto;
    };

}
