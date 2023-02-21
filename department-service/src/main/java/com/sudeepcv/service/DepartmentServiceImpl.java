package com.sudeepcv.service;

import com.sudeepcv.dto.DepartmentDto;
import com.sudeepcv.entity.Department;
import com.sudeepcv.mapper.DepartmentMapper;
import com.sudeepcv.repository.DepartmentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DepartmentServiceImpl implements  DepartmentService{
    private DepartmentRepository departmentRepository;

//    public DepartmentServiceImpl(DepartmentRepository departmentRepository) {
//        this.departmentRepository = departmentRepository;
//    }

    @Override
    public DepartmentDto saveDepartment(DepartmentDto departmentDto) {
//        Department department=new Department(
//                departmentDto.getId(),
//                departmentDto.getDepartmentName(),
//                departmentDto.getDepartmentDescription(),
//                departmentDto.getDepartmentCode()
//        );

        Department department=DepartmentMapper.mapToDepartment(departmentDto);

        Department savedDepartment=departmentRepository.save(department);

//        DepartmentDto savedDepartmentDto=new DepartmentDto(
//                savedDepartment.getId(),
//                savedDepartment.getDepartmentName(),
//                savedDepartment.getDepartmentDescription(),
//                savedDepartment.getDepartmentCode()
//        );
        DepartmentDto savedDepartmentDto=DepartmentMapper.mapToDepartmentDto(savedDepartment);


        return savedDepartmentDto;
    }

    @Override
    public DepartmentDto getDepartmentByCode(String code) {
        Department department=departmentRepository.findByDepartmentCode(code);

//        DepartmentDto departmentDto=new DepartmentDto(
//                department.getId(),
//                department.getDepartmentName(),
//                department.getDepartmentDescription(),
//                department.getDepartmentCode()
//        );
        DepartmentDto departmentDto=DepartmentMapper.mapToDepartmentDto(department);



        return departmentDto;
    }
}
