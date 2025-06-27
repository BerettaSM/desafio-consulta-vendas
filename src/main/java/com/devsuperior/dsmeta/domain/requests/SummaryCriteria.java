package com.devsuperior.dsmeta.domain.requests;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

public class SummaryCriteria {

    @DateTimeFormat(iso = ISO.DATE)
    private LocalDate maxDate;

    @DateTimeFormat(iso = ISO.DATE)
    private LocalDate minDate;

    public SummaryCriteria() {
    }

    public SummaryCriteria(LocalDate minDate, LocalDate maxDate) {
        this.minDate = minDate;
        this.maxDate = maxDate;
    }

    public LocalDate getMaxDate() {
        return maxDate;
    }

    public LocalDate getMinDate() {
        return minDate;
    }

    @Override
    public String toString() {
        return "SummaryCriteria [maxDate=" + maxDate + ", minDate=" + minDate + "]";
    }

}
