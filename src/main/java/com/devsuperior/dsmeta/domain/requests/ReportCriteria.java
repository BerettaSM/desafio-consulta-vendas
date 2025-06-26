package com.devsuperior.dsmeta.domain.requests;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

public class ReportCriteria {

    private String name = "";

    @DateTimeFormat(iso = ISO.DATE)
    private LocalDate maxDate = LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault());

    @DateTimeFormat(iso = ISO.DATE)
    private LocalDate minDate = maxDate.minusYears(1L);

    public ReportCriteria() {
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
        return "ReportCriteria [name='" + name + "', maxDate=" + maxDate + ", minDate=" + minDate + "]";
    }

}
