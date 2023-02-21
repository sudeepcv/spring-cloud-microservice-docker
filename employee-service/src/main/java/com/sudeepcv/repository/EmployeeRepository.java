package com.sudeepcv.repository;

import com.sudeepcv.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.Embeddable;
@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Long> {
}
