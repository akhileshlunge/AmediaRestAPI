package com.employee.AmediaRestAPI.dao;

import com.employee.AmediaRestAPI.entity.Employee;

import jakarta.persistence.Query;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class EmployeeDaoImpl implements EmployeeDao{
    @Autowired
    private SessionFactory sessionFactory;
    @Override
    public boolean saveEmployee(Employee employee) {
        boolean isAdded = false;
        Session session = sessionFactory.openSession();;
        Transaction transaction = session.beginTransaction();
        try {
            Employee emp = session.get(Employee.class, employee.getEmployeeId());
            if (emp == null) {
                session.persist(employee);  // save() is deprecated.
                transaction.commit();
                isAdded = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null){
                session.close();
            }
        }
        return isAdded;
    }
    public boolean saveEmployees(List<Employee> employees){
        Session session = sessionFactory.openSession();
        boolean isSaved = false;
        try {
            for (Employee employee : employees) {
                Transaction transaction = session.beginTransaction();
                Employee emp = session.get(Employee.class, employee.getEmployeeId());
                if (emp == null){
                    session.persist(employee);
                    transaction.commit();
                    isSaved = true;
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        finally {
            session.close();
        }
        return isSaved;
    }
    @Override
    public Employee getEmployeeById(int employeeId){
        Session session = sessionFactory.openSession();
        Employee employee = null;
        try{
            employee = session.get(Employee.class, employeeId);
        }catch (Exception e){
            e.printStackTrace();
        }
        finally {
            if (session != null)
                session.close();
        }
        return employee;
    }

    @Override
    public List<Employee> getAllEmployee(){
        Session session = sessionFactory.openSession();
        List<Employee> employeeList = new ArrayList();
        try{
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<Employee> criteria = criteriaBuilder.createQuery(Employee.class);
            Root<Employee> root = criteria.from(Employee.class);

            // "Root" is for adding restriction
            /*criteria.where(criteriaBuilder.equal(root.get("employeeName"),"Ravi"));*/

            employeeList = session.createQuery(criteria).getResultList();
        }catch (Exception e){
            e.printStackTrace();
        }
        finally {
            if (session != null)
                session.close();
        }
        return employeeList;
    }

    @Override
    public boolean deleteEmployee(int employeeID) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        boolean isDeleted = false;
        try{
            Employee employee = session.get(Employee.class, employeeID);
            if (employee != null){
                session.delete(employee);
                transaction.commit();
                isDeleted = true;
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        finally {
            if (session != null)
                session.close();
        }
        return isDeleted;
    }

    @Override
    public boolean updateEmployee(Employee employee){
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        boolean isUpdated = false;
        try {
            Employee emp = session.get(Employee.class, employee.getEmployeeId());
            session.evict(emp);
            if (emp != null){
                session.update(employee);
                transaction.commit();
                isUpdated = true;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        finally {
            session.close();
        }
        return isUpdated;
    }

    @Override
    public double getMaxSalary(){
        Session session = sessionFactory.openSession();
        double maxSalary = 0.0;
        try{
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<Double> criteriaQuery = criteriaBuilder.createQuery(Double.class);
            Root<Employee> root = criteriaQuery.from(Employee.class);
            criteriaQuery.select(criteriaBuilder.max(root.get("employeeSalary")));
            Query query = session.createQuery(criteriaQuery);
            maxSalary= (double) query.getSingleResult();
            System.out.println("Maximum salary : " + maxSalary);
        }catch (Exception e){
            e.printStackTrace();
        }
        finally {
            session.close();
        }
        return maxSalary;
    }

    @Override
    public double getAvgSalary(){
        Session session = sessionFactory.openSession();
        double avgSalary = 0.0;
        try{
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<Double> criteriaQuery = criteriaBuilder.createQuery(Double.class);
            Root<Employee> root = criteriaQuery.from(Employee.class);
            criteriaQuery.select(criteriaBuilder.avg(root.get("employeeSalary")));
            Query query = session.createQuery(criteriaQuery);
            avgSalary = (double) query.getSingleResult();
            System.out.println("Average salary : " + avgSalary);
        }
        catch (Exception e){

            e.printStackTrace();
        }
        finally {
            session.close();
        }
        return avgSalary;
    }

    @Override
    public double sumOfSalary() {
        Session session = sessionFactory.openSession();
        Double salarySum = 0.0;
        try{
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<Double> criteriaQuery = criteriaBuilder.createQuery(Double.class);
            Root<Employee> root = criteriaQuery.from(Employee.class);
            criteriaQuery.select(criteriaBuilder.sum(root.get("employeeSalary")));
            Query query = session.createQuery(criteriaQuery);
            salarySum = (Double) query.getSingleResult();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        finally {
            session.close();
        }
        return salarySum;
    }

    @Override
    public long totalEmployee(){
        Session session = sessionFactory.openSession();
        long count = 0l;
        try {
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);
            Root<Employee> root = criteriaQuery.from(Employee.class);
            criteriaQuery.select(criteriaBuilder.count(root));
            Query query = session.createQuery(criteriaQuery);
            count = (long) query.getSingleResult();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        finally {
            session.close();
        }
        return count;
    }
}
