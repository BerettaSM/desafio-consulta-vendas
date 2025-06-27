package com.devsuperior.dsmeta.dto;

import com.devsuperior.dsmeta.projections.SaleSummaryProjection;

public class SaleSummaryDTO {

    private final String sellerName;
    private final Double total;

    public SaleSummaryDTO(String sellerName, Double total) {
        this.sellerName = sellerName;
        this.total = total;
    }

    public SaleSummaryDTO(SaleSummaryProjection projection) {
        this(projection.getSellerName(), projection.getTotal());
    }

    public String getSellerName() {
        return sellerName;
    }

    public Double getTotal() {
        return total;
    }

}
