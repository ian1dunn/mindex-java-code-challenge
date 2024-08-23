package com.mindex.challenge.controller;

import com.mindex.challenge.data.Employee;
import com.mindex.challenge.data.ReportingStructure;
import com.mindex.challenge.service.EmployeeService;
import com.mindex.challenge.service.ReportingStructureService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class ReportingStructureController {
    private static final Logger LOG = LoggerFactory.getLogger(ReportingStructureController.class);

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private ReportingStructureService reportingStructureService;

    @GetMapping("/reportingStructure/{id}")
    public ReportingStructure getStructure(@PathVariable String id) {
        LOG.debug("Received reporting structure request for id [{}]", id);

        Employee employee = employeeService.read(id);
        ReportingStructure reportingStructure = new ReportingStructure(employee);

        int numberOfReports = reportingStructureService.getNumberOfReports(id);
        reportingStructure.setNumberOfReports(numberOfReports);

        return reportingStructure;
    }
}
