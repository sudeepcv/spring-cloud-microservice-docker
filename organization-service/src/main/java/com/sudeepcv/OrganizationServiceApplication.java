package com.sudeepcv;

import com.sudeepcv.entity.Organization;
import com.sudeepcv.repository.OrganizationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

import javax.annotation.PostConstruct;

@SpringBootApplication
@EnableEurekaClient
public class OrganizationServiceApplication {
	@Autowired
	private OrganizationRepository organizationRepository;

	public static void main(String[] args) {
		SpringApplication.run(OrganizationServiceApplication.class, args);
	}
    @PostConstruct
	public void saveOrganization(){
		Organization organization =new Organization();
		organization.setOrganizationCode("organizationcode");
		organization.setOrganizationDescription("organization description");
		organization.setOrganizationName("organization name");
		organizationRepository.save(organization);
	}

}
