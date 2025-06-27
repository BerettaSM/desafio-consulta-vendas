package com.devsuperior.dsmeta.domain.requests;

import java.time.LocalDate;
import java.util.Optional;

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

    public Optional<String> getName() {
        return Optional.ofNullable(name);
    }

    public Optional<LocalDate> getMaxDate() {
        return Optional.ofNullable(maxDate);
    }

    public Optional<LocalDate> getMinDate() {
        return Optional.ofNullable(minDate);
    }

    @Override
    public String toString() {
        return "ReportCriteria [name=" + name + ", maxDate=" + maxDate + ", minDate=" + minDate + "]";
    }

}
