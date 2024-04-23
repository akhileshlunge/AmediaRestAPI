package com.employee.AmediaRestAPI.dao;

import com.employee.AmediaRestAPI.entity.Employee;

import java.util.List;

public interface EmployeeDao {
    public boolean saveEmployee(Employee employee);
    public boolean saveEmployees(List<Employee> employees);
    public Employee getEmployeeById(int id);
    public List<Employee> getAllEmployee();
    public boolean deleteEmployee(int employeeID);
    public boolean updateEmployee(Employee employee);
    public double getMaxSalary();
    public double getAvgSalary();
    public double sumOfSalary();
    public long totalEmployee();
}
