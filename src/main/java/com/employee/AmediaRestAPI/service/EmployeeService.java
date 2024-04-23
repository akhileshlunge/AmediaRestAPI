package com.employee.AmediaRestAPI.service;

import com.employee.AmediaRestAPI.entity.Employee;

import java.util.List;

public interface EmployeeService {
    public boolean saveEmployee(Employee employee);
    public boolean saveEmployees(List<Employee> employees);
    public Employee getEmployeeById(int employeeId);
    public List<Employee> getAllEmployee();
    public boolean deleteEmployee(int employeeId);
    public boolean updateEmployee(Employee employee);
    public double getMaxSalary();
    public double getAvgSalary();
    public double sumOfSalary();
    public long totalEmployee();
}
