package com.codingshuttle.springbootutorial.springbootwebtutoial.controllers;

import com.codingshuttle.springbootutorial.springbootwebtutoial.dto.EmployeeDTO;
import com.codingshuttle.springbootutorial.springbootwebtutoial.entities.EmployeeEntity;
import com.codingshuttle.springbootutorial.springbootwebtutoial.repositories.EmployeeRepository;
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


    private final EmployeeRepository employeeRepository;

    public EmployeeController(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @GetMapping("/{employeeId}")
    public EmployeeEntity getEmployeeById(@PathVariable(name = "employeeId") Long  id){


        return employeeRepository.findById(id).orElse(null);
//        return new EmployeeDTO(
//                id,
//                "Abdullah",
//                "abdullah.safwi@maersk.com",
//                27,
//                LocalDate.of(
//                        2024,
//                        1, 2
//                ),
//                true
//        );

    }

    @GetMapping(path = "/employees")
    public List<EmployeeEntity> getAllEmployees(@RequestParam (required = false) Integer age){
        return employeeRepository.findAll();
    }

    @PostMapping
    public EmployeeEntity createNewEmployee(@RequestBody EmployeeEntity inputEmployee){
        employeeRepository.save(inputEmployee);
        return inputEmployee;
    }

}
