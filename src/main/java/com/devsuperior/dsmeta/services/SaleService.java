package com.devsuperior.dsmeta.services;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.devsuperior.dsmeta.domain.requests.ReportCriteria;
import com.devsuperior.dsmeta.dto.SaleMinDTO;
import com.devsuperior.dsmeta.dto.SaleReportDTO;
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
        String name = criteria.getName().orElse("");
        LocalDate maxDate = criteria.getMaxDate().orElse(LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault()));
        LocalDate minDate = criteria.getMinDate().orElse(maxDate.minusYears(1L));
        return repository.querySalesBy(name, minDate, maxDate, pageable)
            .map(SaleReportDTO::new);
    }

}
