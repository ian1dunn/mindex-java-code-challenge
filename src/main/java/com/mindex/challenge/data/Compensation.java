package com.mindex.challenge.data;

import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDate;
import java.util.Objects;

public class Compensation {
    @Field("employee")
    private Employee employee;
    private int salary;
    private LocalDate effectiveDate;

    public Compensation() {

    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public LocalDate getEffectiveDate() {
        return effectiveDate;
    }

    public void setEffectiveDate(LocalDate effectiveDate) {
        this.effectiveDate = effectiveDate;
    }

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Compensation)) return false;

        Compensation that = (Compensation) o;
        return salary == that.salary && Objects.equals(employee, that.employee) && Objects.equals(effectiveDate, that.effectiveDate);
    }
}
