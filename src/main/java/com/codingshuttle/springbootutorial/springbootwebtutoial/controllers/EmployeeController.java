package com.codingshuttle.springbootutorial.springbootwebtutoial.controllers;

import com.codingshuttle.springbootutorial.springbootwebtutoial.dto.EmployeeDTO;
import com.codingshuttle.springbootutorial.springbootwebtutoial.entities.EmployeeEntity;
import com.codingshuttle.springbootutorial.springbootwebtutoial.repositories.EmployeeRepository;
import com.codingshuttle.springbootutorial.springbootwebtutoial.services.EmployeeService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(path = "/employees")
public class EmployeeController {

//    @GetMapping(path = "/")
//    public String getMySuperSecretMessage(){
//
//    }


    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/{employeeId}")
    public EmployeeDTO getEmployeeById(@PathVariable(name = "employeeId") Long  id){


        return employeeService.findById(id);


    }

    @GetMapping(path = "/employees")
    public List<EmployeeDTO> getAllEmployees(@RequestParam (required = false) Integer age){
        return employeeService.findAll();
    }

    @PostMapping
    public EmployeeDTO createNewEmployee(@RequestBody EmployeeDTO inputEmployee){
        return employeeService.createNeEmployee(inputEmployee);
    }

}
