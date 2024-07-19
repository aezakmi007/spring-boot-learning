package com.codingshuttle.springbootutorial.springbootwebtutoial.services;

import com.codingshuttle.springbootutorial.springbootwebtutoial.dto.EmployeeDTO;
import com.codingshuttle.springbootutorial.springbootwebtutoial.entities.EmployeeEntity;
import com.codingshuttle.springbootutorial.springbootwebtutoial.repositories.EmployeeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeService {

      private final EmployeeRepository employeeRepository;
      private final ModelMapper modelMapper;

    public EmployeeService(EmployeeRepository employeeRepository, ModelMapper modelMapper) {
        this.employeeRepository = employeeRepository;
        this.modelMapper = modelMapper;
    }

    public EmployeeDTO findById(Long id) {

        EmployeeEntity employeeEntity = employeeRepository.findById(id).orElse(null);
        return modelMapper.map(employeeEntity, EmployeeDTO.class);

    }

    public List<EmployeeDTO> findAll() {
        List<EmployeeEntity> employeeEntities = employeeRepository.findAll();
        return employeeEntities.stream().map(
                employeeEntity -> modelMapper.map(
                employeeEntity, EmployeeDTO.class)).collect(Collectors.toList());


    }

    public EmployeeDTO createNeEmployee(EmployeeDTO inputEmployee) {

        EmployeeEntity toSaveEntity = modelMapper.map(inputEmployee, EmployeeEntity.class);
        EmployeeEntity savedEmployeeEntity = employeeRepository.save(toSaveEntity);
        return modelMapper.map(savedEmployeeEntity, EmployeeDTO.class);

    }

}
