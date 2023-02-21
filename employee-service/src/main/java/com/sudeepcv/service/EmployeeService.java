package com.sudeepcv.service;

import com.sudeepcv.dto.APIResponseDto;
import com.sudeepcv.dto.EmployeeDto;

public interface EmployeeService {
    EmployeeDto saveEmployee(EmployeeDto employeeDto);
    APIResponseDto getEmployeeById(Long id);
}
