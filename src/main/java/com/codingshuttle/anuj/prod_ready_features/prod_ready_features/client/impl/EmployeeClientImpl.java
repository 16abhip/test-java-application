package com.codingshuttle.anuj.prod_ready_features.prod_ready_features.client.impl;

import com.codingshuttle.anuj.prod_ready_features.prod_ready_features.advice.ApiResponse;
import com.codingshuttle.anuj.prod_ready_features.prod_ready_features.client.EmployeeClient;
import com.codingshuttle.anuj.prod_ready_features.prod_ready_features.dto.EmployeeDTO;
import com.codingshuttle.anuj.prod_ready_features.prod_ready_features.exceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeClientImpl implements EmployeeClient {

    Logger log = LoggerFactory.getLogger(EmployeeClientImpl.class);
    private final RestClient restClient;

    @Override
    public List<EmployeeDTO> getAllEmployees() {
        log.trace("Trying to getAllEmployees");

        try {
            ApiResponse<List<EmployeeDTO>> employeeDTOList = restClient
                    .get()
                    .uri("emp")
                    .retrieve()
                    .onStatus(HttpStatusCode::is4xxClientError, (req, res)->{
                        log.error(new String(res.getBody().readAllBytes()));
                    })
                    .body(new ParameterizedTypeReference<>() {
                    });
            log.debug("Successfully retrived the employees in getAllEmployee");
            log.trace("Employees list in getAllEmployee: {}", employeeDTOList.getData());
            return employeeDTOList.getData();
        } catch (Exception e) {
            log.error("Exception occured in getAllEmployee", e);
            throw new RuntimeException(e);
        }

    }

    @Override
    public EmployeeDTO getEmployeeById(Long emp) {
        try {
            ApiResponse<EmployeeDTO> employeeDTOApiResponse = restClient
                    .get()
                    .uri("emp/{empployeeId}", emp)
                    .retrieve()
                    .body(new ParameterizedTypeReference<>(){
                    });
            return employeeDTOApiResponse.getData();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public EmployeeDTO createNewEmployee(EmployeeDTO employeeDTO) {
        try {
            ResponseEntity<ApiResponse<EmployeeDTO>> apiResponseResponseEntity = restClient
                    .post()
                    .uri("emp/create")
                    .body(employeeDTO)
                    .retrieve()
                    .onStatus(HttpStatusCode::is4xxClientError, (req, res) -> {
                        System.out.println(new String(res.getBody().readAllBytes()));
                        throw new ResourceNotFoundException("could not create the Employee");
                    })
                    .toEntity(new ParameterizedTypeReference<>(){
                    });
            return apiResponseResponseEntity.getBody().getData();
        } catch (Exception e){
            throw new RuntimeException(e);
        }
    }
}
