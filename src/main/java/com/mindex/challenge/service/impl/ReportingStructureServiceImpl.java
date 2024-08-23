package com.mindex.challenge.service.impl;

import com.mindex.challenge.data.Employee;
import com.mindex.challenge.service.EmployeeService;
import com.mindex.challenge.service.ReportingStructureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReportingStructureServiceImpl implements ReportingStructureService {
    @Autowired
    private EmployeeService employeeService;

    /**
     * This is a recursive method to build the full report structure of a given employee ID.
     *
     * @param  employeeId  The employee ID of the employee to build the directReports field.
     * @return             A modified employee object with ALL direct reports, rather than just the first level.
     */
    @Override
    public Employee buildReportStructure(String employeeId) {
        Employee employee = employeeService.read(employeeId);

        if (employee.getDirectReports() == null || employee.getDirectReports().isEmpty())
            return employee;

        List<Employee> directReports = new ArrayList<>();

        for (Employee report : employee.getDirectReports())
            directReports.add(buildReportStructure(report.getEmployeeId()));

        employee.setDirectReports(directReports);

        return employee;
    }

    /**
     * This is a recursive method to total the number of direct reports of an employee.
     *
     * @param  employee  The employee object of which the directReports field will be traversed.
     * @return           The total number of direct reports of the employee.
     */
    @Override
    public int getNumberOfReports(Employee employee) {
        if (employee.getDirectReports() == null || employee.getDirectReports().isEmpty())
            return 0;

        int totalNumberOfReports = 0;

        for (Employee report : employee.getDirectReports())
            totalNumberOfReports += 1 + getNumberOfReports(report);

        return totalNumberOfReports;
    }
}
