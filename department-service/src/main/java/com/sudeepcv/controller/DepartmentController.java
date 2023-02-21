package com.sudeepcv.controller;

import com.sudeepcv.dto.DepartmentDto;
import com.sudeepcv.service.DepartmentService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/departments")
@AllArgsConstructor
public class DepartmentController {
    private DepartmentService departmentService;

//    public DepartmentController(DepartmentService departmentService) {
//        this.departmentService = departmentService;
//    }
    @PostMapping
    public ResponseEntity<DepartmentDto> saveDepartment(@RequestBody DepartmentDto departmentDto){

        DepartmentDto response = departmentService.saveDepartment(departmentDto);

        return  new ResponseEntity<>(response, HttpStatus.CREATED);
    }
    @GetMapping("{department-cod}")
    public ResponseEntity<DepartmentDto> getDepartment(@PathVariable("department-cod") String departmentCode){

        DepartmentDto response = departmentService.getDepartmentByCode(departmentCode);

        return  new ResponseEntity<>(response, HttpStatus.OK);
    }
}
