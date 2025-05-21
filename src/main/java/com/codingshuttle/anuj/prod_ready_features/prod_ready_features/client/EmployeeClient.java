package com.codingshuttle.anuj.prod_ready_features.prod_ready_features.client;

import com.codingshuttle.anuj.prod_ready_features.prod_ready_features.dto.EmployeeDTO;

import java.util.List;

public interface EmployeeClient {
    List<EmployeeDTO> getAllEmployees();

    EmployeeDTO getEmployeeById(Long emp);

    EmployeeDTO createNewEmployee(EmployeeDTO employeeDTO);
}
