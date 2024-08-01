package com.codingshuttle.springbootutorial.springbootwebtutoial.controllers;

import com.codingshuttle.springbootutorial.springbootwebtutoial.dto.EmployeeDTO;
import com.codingshuttle.springbootutorial.springbootwebtutoial.entities.EmployeeEntity;
import com.codingshuttle.springbootutorial.springbootwebtutoial.exceptions.ResourceNotFoundException;
import com.codingshuttle.springbootutorial.springbootwebtutoial.repositories.EmployeeRepository;
import com.codingshuttle.springbootutorial.springbootwebtutoial.services.EmployeeService;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@RequestMapping(path = "/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/{employeeId}")
    public ResponseEntity<EmployeeDTO>  getEmployeeById(@PathVariable(name = "employeeId") Long  id){
        Optional<EmployeeDTO> employeeDTO =  employeeService.findById(id);

        return employeeDTO.map(employeeDTO1 -> ResponseEntity.ok(employeeDTO1)).orElseThrow(
                () -> new ResourceNotFoundException("Employee not found with id: "+id)
        );

    }

    @GetMapping(path = "/employees")
    public ResponseEntity<List<EmployeeDTO>> getAllEmployees(@RequestParam (required = false) Integer age){
        return ResponseEntity.ok(employeeService.findAll());
//        return employeeService.findAll();
    }

    @PostMapping
    public ResponseEntity<EmployeeDTO> createNewEmployee(@RequestBody EmployeeDTO inputEmployee){
        EmployeeDTO savedEmployee = employeeService.createNeEmployee(inputEmployee);
        return new ResponseEntity<>(savedEmployee, HttpStatus.CREATED);
    }

    @PutMapping(path="/{employeeId}")
    public ResponseEntity<EmployeeDTO> updateEmployeeById(@RequestBody EmployeeDTO employeeDTO, @PathVariable Long employeeId){
        return ResponseEntity.ok(employeeService.updateEmployeeById(employeeId, employeeDTO));
    }

    @DeleteMapping(path="/{employeeId}")
    public ResponseEntity<Boolean> deleteEmployeeById(@PathVariable Long employeeId){

        boolean gotDeleted = employeeService.deleteEmployeeById(employeeId);

        if(gotDeleted)
            return ResponseEntity.ok(true);

        return ResponseEntity.notFound().build();
    }

    @PatchMapping(path = "/{employeeId}")
    public ResponseEntity<EmployeeDTO> updatePartialEmployeeById(@RequestBody Map<String, Object> updates, @PathVariable Long employeeId){
        EmployeeDTO employeeDTO = employeeService.updatePartialemployeeById(employeeId, updates);
        if(employeeDTO == null)return ResponseEntity.notFound().build();

        return ResponseEntity.ok(employeeDTO);
    }

}
