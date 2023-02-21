package com.sudeepcv;

import com.sudeepcv.entity.Department;
import com.sudeepcv.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;

@SpringBootApplication
@EnableEurekaClient
public class DepartmentServiceApplication {
	@Autowired
	private DepartmentRepository departmentRepository;

	public static void main(String[] args) {
		SpringApplication.run(DepartmentServiceApplication.class, args);
	}
	@PostConstruct
	public void saveDepartment(){
		Department department=new Department();
		department.setDepartmentCode("departmentcode");
		department.setDepartmentName("departmentname");
		department.setDepartmentDescription("department description");
		departmentRepository.save(department);
	}


}
