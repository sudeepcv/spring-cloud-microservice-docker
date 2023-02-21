package com.sudeepcv.controller;

import com.sudeepcv.dto.APIResponseDto;
import com.sudeepcv.dto.EmployeeDto;
import com.sudeepcv.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/employees")
@AllArgsConstructor
public class EmployeeController {

    private EmployeeService employeeService;
    @PostMapping
    public ResponseEntity<EmployeeDto> saveEmployee(@RequestBody EmployeeDto employeeDto){
        EmployeeDto result = employeeService.saveEmployee(employeeDto);
        return  new ResponseEntity<>(result, HttpStatus.CREATED);
    }
    @GetMapping("{id}")
    public ResponseEntity<APIResponseDto> getEmployById(@PathVariable("id") Long employeeId){
        APIResponseDto result = employeeService.getEmployeeById(employeeId);
        return new ResponseEntity<>(result,HttpStatus.OK);
    }

}
