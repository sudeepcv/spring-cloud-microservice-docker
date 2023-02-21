package com.sudeepcv;

import com.netflix.discovery.converters.Auto;
import com.sudeepcv.entity.Employee;
import com.sudeepcv.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import javax.annotation.PostConstruct;

@SpringBootApplication
@EnableFeignClients
@EnableEurekaClient
public class EmployeeServiceApplication {
    @Autowired
	private EmployeeRepository employeeRepository;

	public static void main(String[] args) {
		SpringApplication.run(EmployeeServiceApplication.class, args);
	}

//	@Bean
//	public RestTemplate restTemplate(){
//		return new RestTemplate();
//	}

	@Bean
	public WebClient webClient(){
		return WebClient.builder().build();
	}

	@PostConstruct
	public void saveEmployee(){
		Employee employee =new Employee();
		employee.setDepartmentCode("departmentcode");
		employee.setEmail("informsudeep@gmail.com");
		employee.setFirstName("sudeep");
		employee.setLastName("cv");
		employee.setOrganizationCode("organizationcode");
		employeeRepository.save(employee);

	}
}
