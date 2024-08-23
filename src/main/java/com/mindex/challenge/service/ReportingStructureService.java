package com.mindex.challenge.service;

import com.mindex.challenge.data.Employee;

public interface ReportingStructureService {
    Employee buildReportStructure(String employeeId);
    int getNumberOfReports(Employee employee);
}
