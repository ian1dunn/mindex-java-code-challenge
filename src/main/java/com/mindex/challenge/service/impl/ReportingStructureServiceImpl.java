package com.mindex.challenge.service.impl;

import com.mindex.challenge.dao.EmployeeRepository;
import com.mindex.challenge.data.Employee;
import com.mindex.challenge.service.EmployeeService;
import com.mindex.challenge.service.ReportingStructureService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public int getNumberOfReports(String employeeId) {
        Employee employee = employeeService.read(employeeId);
        int totalNumberOfReports = 0;

        if (employee.getDirectReports() == null || employee.getDirectReports().isEmpty()) {
            return totalNumberOfReports;
        }

        for (Employee report : employee.getDirectReports()) {
            totalNumberOfReports += 1 + getNumberOfReports(report.getEmployeeId());
        }

        return totalNumberOfReports;
    }
}
