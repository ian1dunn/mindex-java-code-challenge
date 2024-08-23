package com.mindex.challenge.service.impl;

import com.mindex.challenge.dao.EmployeeRepository;
import com.mindex.challenge.data.Employee;
import com.mindex.challenge.service.EmployeeService;
import com.mindex.challenge.service.ReportingStructureService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReportingStructureServiceImpl implements ReportingStructureService {
    @Autowired
    private EmployeeService employeeService;

    @Override
    /**
     * This is a recursive method to get the number of reports of an employee (i.e. the number of direct reports for an
     * employee and all of their distinct reports).
     *
     * @param  employeeId  The employee ID of the employee to get the total number of reports of.
     * @return             The total number of reports of the employee.
     */
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

    public int getNumberOfReports(Employee employee) {
        if (employee.getDirectReports() == null || employee.getDirectReports().isEmpty())
            return 0;

        int totalNumberOfReports = 0;

        for (Employee report : employee.getDirectReports())
            totalNumberOfReports += 1 + getNumberOfReports(report);

        return totalNumberOfReports;
    }
}
