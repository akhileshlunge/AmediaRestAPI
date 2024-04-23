package com.employee.AmediaRestAPI.controller;

import com.employee.AmediaRestAPI.entity.Employee;
import com.employee.AmediaRestAPI.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/amedia")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @PostMapping(value = "saveEmployee")
    public ResponseEntity<Boolean> saveEmployee(@Validated @RequestBody Employee employee){
        boolean isAdded = employeeService.saveEmployee(employee);
        if (isAdded)
            return new ResponseEntity<>(isAdded, HttpStatus.CREATED);
        else
            return new ResponseEntity<>(isAdded, HttpStatus.BAD_REQUEST);
    }

    @PostMapping(value = "saveEmployees")
    public ResponseEntity<Boolean> saveEmployees(@RequestBody List<Employee> employees){
        boolean isSaved = employeeService.saveEmployees(employees);
        if (isSaved)
            return new ResponseEntity<>(isSaved, HttpStatus.CREATED);
        else
            return new ResponseEntity<>(isSaved, HttpStatus.BAD_REQUEST);
    }

    @GetMapping(value = "getEmployeeById/{employeeId}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable int employeeId){
        Employee employee = employeeService.getEmployeeById(employeeId);

        if (employee != null)
            return new ResponseEntity<Employee>(employee, HttpStatus.FOUND);
        else
            return new ResponseEntity<Employee>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "getAllEmployee")
    public ResponseEntity<List<Employee>> getAllEmployee(){
        List<Employee> employeeList = employeeService.getAllEmployee();
        if(!employeeList.isEmpty())
            return new ResponseEntity<List<Employee>>(employeeList, HttpStatus.FOUND);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping(value = "deleteEmployee/{employeeId}")
    public ResponseEntity<Boolean> deleteEmployee(@PathVariable int employeeId){
        boolean isDeleted = employeeService.deleteEmployee(employeeId);
        if(isDeleted == true)
            return new ResponseEntity<>(isDeleted, HttpStatus.OK);
        else
            return new ResponseEntity<>(isDeleted, HttpStatus.NOT_FOUND);
    }

    @PutMapping(value = "updateEmployee")
    public ResponseEntity<Boolean> updateEmployee(@RequestBody Employee employee){
        boolean isUpdated = employeeService.updateEmployee(employee);
        if (isUpdated == true)
            return new ResponseEntity<>(isUpdated, HttpStatus.ACCEPTED);
        else
            return new ResponseEntity<>(isUpdated, HttpStatus.BAD_REQUEST);
    }

    @GetMapping(value = "getMaxSalary")
    public ResponseEntity<Double> getMaxSalary(){
        double maxSalary = employeeService.getMaxSalary();
        if (maxSalary != 0.0)
            return new ResponseEntity<>(maxSalary, HttpStatus.FOUND);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "getAvgSalary")
    public ResponseEntity<Double> getAvgSalary(){
        double avgSalary = employeeService.getAvgSalary();
        if (avgSalary != 0.0)
            return new ResponseEntity<>(avgSalary, HttpStatus.FOUND);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping (value = "sumOfSalary")
    public ResponseEntity<Double> sumOfSalary(){
        double salarySum = employeeService.sumOfSalary();
        if (salarySum != 0.0)
            return new ResponseEntity<>(salarySum, HttpStatus.FOUND);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping (value = "totalEmployee")
    public ResponseEntity<Long> totalEmployee(){
        long count = employeeService.totalEmployee();
        if (count != 0)
            return new ResponseEntity<>(count, HttpStatus.FOUND);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
