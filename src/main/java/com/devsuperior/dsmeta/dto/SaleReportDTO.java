package com.devsuperior.dsmeta.dto;

import java.time.LocalDate;

import com.devsuperior.dsmeta.projections.SaleReportProjection;

public class SaleReportDTO {
    
    private final Long id;
    private final LocalDate date;
    private final Double amount;
    private final String sellerName;

    public SaleReportDTO(Long id, LocalDate date, Double amount, String sellerName) {
        this.id = id;
        this.date = date;
        this.amount = amount;
        this.sellerName = sellerName;
    }

    public SaleReportDTO(SaleReportProjection projection) {
        this(
            projection.getId(),
            projection.getDate(),
            projection.getAmount(),
            projection.getSellerName());
    }

    public Long getId() {
        return id;
    }

    public LocalDate getDate() {
        return date;
    }

    public Double getAmount() {
        return amount;
    }

    public String getSellerName() {
        return sellerName;
    }

    @Override
    public String toString() {
        return "SaleReportDTO [id=" + id + ", date=" + date + ", amount=" + amount + ", sellerName=" + sellerName + "]";
    }
    
}
