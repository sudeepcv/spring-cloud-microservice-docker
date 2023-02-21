package com.sudeepcv.service;

import com.sudeepcv.dto.APIResponseDto;
import com.sudeepcv.dto.DepartmentDto;
import com.sudeepcv.dto.EmployeeDto;
import com.sudeepcv.dto.OrganizationDto;
import com.sudeepcv.entity.Employee;
import com.sudeepcv.mapper.EmployeeMapper;
import com.sudeepcv.repository.EmployeeRepository;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService{
    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeServiceImpl.class);

//    private RestTemplate restTemplate;
private WebClient webClient;
//APIClient apiClient;
    EmployeeRepository employeeRepository;

    @Override
    public EmployeeDto saveEmployee(EmployeeDto employeeDto) {
//        Employee employee=new Employee(
//                employeeDto.getId(),
//                employeeDto.getFirstName(),
//                employeeDto.getLastName(),
//                employeeDto.getEmail(),
//                employeeDto.getDepartmentCode()
//        );
        Employee employee =EmployeeMapper.mapToEmployee(employeeDto);

        Employee result = employeeRepository.save(employee);

//        EmployeeDto dtoResult=new EmployeeDto(
//                result.getId(),
//                result.getFirstName(),
//                result.getLastName(),
//                result.getEmail(),
//                result.getDepartmentCode()
//        );
        EmployeeDto dtoResult =EmployeeMapper.mapToEmployeeDto(result);

        return dtoResult;
    }

    @Override
//    @CircuitBreaker(name = "${spring.application.name}", fallbackMethod = "getDefaultDepartment")
    @Retry(name = "${spring.application.name}", fallbackMethod = "getDefaultDepartment")
    public APIResponseDto getEmployeeById(Long id) {
        LOGGER.info("inside getEmployeeById() method");
        Employee result = employeeRepository.findById(id).get();

//        ResponseEntity<DepartmentDto> departmentResult = restTemplate.getForEntity("http://localhost:8080/api/departments/" + result.getDepartmentCode(),
//                DepartmentDto.class);
//        DepartmentDto departmentDto=departmentResult.getBody();

       DepartmentDto departmentResult = webClient.get()
                .uri("http://department-service:8080/api/departments/" + result.getDepartmentCode())
                .retrieve()
                .bodyToMono(DepartmentDto.class)
                .block();

        OrganizationDto organizationDto = webClient.get()
                .uri("http://organization-service:8083/api/organizations/" + result.getOrganizationCode())
                .retrieve()
                .bodyToMono(OrganizationDto.class)
                .block();


//        DepartmentDto departmentResult = apiClient.getDepartment(result.getDepartmentCode());



//        EmployeeDto employeeDto=new EmployeeDto(
//                result.getId(),
//                result.getFirstName(),
//                result.getLastName(),
//                result.getEmail(),
//                result.getDepartmentCode()
//        );

        EmployeeDto employeeDto =EmployeeMapper.mapToEmployeeDto(result);


        APIResponseDto apiResponseDto =new APIResponseDto();
        apiResponseDto.setEmployee(employeeDto);
        apiResponseDto.setDepartment(departmentResult);
        apiResponseDto.setOrganization(organizationDto);
        return apiResponseDto;
    }

    public APIResponseDto getDefaultDepartment(Long employeeId, Exception exception) {

        LOGGER.info("inside getDefaultDepartment() method");

        Employee employee = employeeRepository.findById(employeeId).get();

        DepartmentDto departmentDto = new DepartmentDto();
        departmentDto.setDepartmentName("R&D Department");
        departmentDto.setDepartmentCode("RD001");
        departmentDto.setDepartmentDescription("Research and Development Department");

        EmployeeDto employeeDto = EmployeeMapper.mapToEmployeeDto(employee);

        APIResponseDto apiResponseDto = new APIResponseDto();
        apiResponseDto.setEmployee(employeeDto);
        apiResponseDto.setDepartment(departmentDto);
        return apiResponseDto;
    }

}
