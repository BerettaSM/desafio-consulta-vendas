package com.devsuperior.dsmeta.services;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.devsuperior.dsmeta.domain.requests.ReportCriteria;
import com.devsuperior.dsmeta.domain.requests.SummaryCriteria;
import com.devsuperior.dsmeta.dto.SaleMinDTO;
import com.devsuperior.dsmeta.dto.SaleReportDTO;
import com.devsuperior.dsmeta.dto.SaleSummaryDTO;
import com.devsuperior.dsmeta.entities.Sale;
import com.devsuperior.dsmeta.repositories.SaleRepository;

@Service
public class SaleService {

    @Autowired
    private SaleRepository repository;

    public SaleMinDTO findById(Long id) {
        Optional<Sale> result = repository.findById(id);
        Sale entity = result.get();
        return new SaleMinDTO(entity);
    }

    public Page<SaleReportDTO> querySalesByCriteria(ReportCriteria criteria, Pageable pageable) {
        String name = Optional.ofNullable(criteria.getName()).orElse("");
        Map<String, LocalDate> clampedDates = clampDates(criteria.getMinDate(), criteria.getMaxDate());
        return repository.querySalesBy(
                name,
                clampedDates.get("minDate"),
                clampedDates.get("maxDate"),
                pageable)
                .map(SaleReportDTO::new);
    }

    public List<SaleSummaryDTO> summarizeSalesByCriteria(SummaryCriteria criteria) {
        Map<String, LocalDate> clampedDates = clampDates(criteria.getMinDate(), criteria.getMaxDate());
        return repository.summarizeSalesBy(
                clampedDates.get("minDate"),
                clampedDates.get("maxDate"))
                .stream()
                .map(SaleSummaryDTO::new)
                .toList();
    }

    private Map<String, LocalDate> clampDates(LocalDate minDate, LocalDate maxDate) {
        LocalDate clampedMaxDate = Optional.ofNullable(maxDate)
            .orElse(LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault()));
        LocalDate clampedMinDate = Optional.ofNullable(minDate)
            .orElse(clampedMaxDate.minusYears(1L));
        return Map.of(
                "minDate", clampedMinDate,
                "maxDate", clampedMaxDate.isBefore(clampedMinDate) ? clampedMinDate : clampedMaxDate);
    }

}
