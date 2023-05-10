package com.example.employee.service;

import com.example.employee.exception.EmployeeAlreadyAddedException;
import com.example.employee.exception.EmployeeNotFoundException;
import com.example.employee.exception.EmployeeStorageIsFullException;
import com.example.employee.model.Employee;

import java.util.ArrayList;
import java.util.Collection;

public class EmployeeService {
    private final Collection<Employee> employees = new ArrayList<>(SIZE_LIMIT);
    private static final int SIZE_LIMIT = 10;

    public Employee add(Employee employee) {
        if (employees.size() >= SIZE_LIMIT) {
            throw new EmployeeStorageIsFullException();
        }
        if (employees.contains(employee)) {
            throw new EmployeeAlreadyAddedException();
        }
        employees.add(employee);
        return employee;
    }

    public Employee find(String firstName, String lastName) {
        for (Employee employee : employees) {
            if (employee.getFirstName().equals(firstName) && employee.getLastName().equals(lastName)) {
                return employee;
            }
        }
        throw new EmployeeNotFoundException();
    }

    public Employee remove(String firstName, String lastName) {
        Employee employee = find(firstName, lastName);
        employees.remove(employee);
        return employee;
    }

}
