package com.employee.AmediaRestAPI.service;

import com.employee.AmediaRestAPI.dao.EmployeeDao;
import com.employee.AmediaRestAPI.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService{
    @Autowired
    private EmployeeDao employeeDao;
    @Override
    public boolean saveEmployee(Employee employee){
        boolean isAdded = employeeDao.saveEmployee(employee);
        return isAdded;
    }
    @Override
    public boolean saveEmployees(List<Employee> employees){
        boolean isSaved = employeeDao.saveEmployees(employees);
        return isSaved;
    }
    @Override
    public Employee getEmployeeById(int employeeId){
        Employee employee = employeeDao.getEmployeeById(employeeId);
        return employee;
    }
    @Override
    public List<Employee> getAllEmployee() {
        List<Employee> employeeList = employeeDao.getAllEmployee();
        return employeeList;
    }
    @Override
    public boolean deleteEmployee(int employeeID){
        boolean isDeleted = employeeDao.deleteEmployee(employeeID);
        return isDeleted;
    }
    @Override
    public boolean updateEmployee(Employee employee){
        boolean isUpdated = employeeDao.updateEmployee(employee);
        return isUpdated;
    }
    @Override
    public double getMaxSalary(){
        double maxSalary = employeeDao.getMaxSalary();
        return maxSalary;
    }
    @Override
    public double getAvgSalary(){
        double avgSalary = employeeDao.getAvgSalary();
        return avgSalary;
    }
    @Override
    public double sumOfSalary(){
        double sumOfSalary = employeeDao.sumOfSalary();
        return sumOfSalary;
    }
    @Override
    public long totalEmployee(){
        long count = employeeDao.totalEmployee();
        return count;
    }
}
