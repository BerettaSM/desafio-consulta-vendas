package com.devsuperior.dsmeta.domain.requests;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

public class ReportCriteria {

    private String name;

    @DateTimeFormat(iso = ISO.DATE)
    private LocalDate maxDate;

    @DateTimeFormat(iso = ISO.DATE)
    private LocalDate minDate;

    public ReportCriteria() {
    }

    public ReportCriteria(String name, LocalDate minDate, LocalDate maxDate) {
        this.name = name;
        this.maxDate = maxDate;
        this.minDate = minDate;
    }

    public String getName() {
        return name;
    }

    public LocalDate getMaxDate() {
        return maxDate;
    }

    public LocalDate getMinDate() {
        return minDate;
    }

    @Override
    public String toString() {
        return "ReportCriteria [name=" + name + ", maxDate=" + maxDate + ", minDate=" + minDate + "]";
    }

}
