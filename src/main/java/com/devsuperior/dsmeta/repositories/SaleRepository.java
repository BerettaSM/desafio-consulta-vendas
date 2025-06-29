package com.devsuperior.dsmeta.repositories;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.devsuperior.dsmeta.entities.Sale;
import com.devsuperior.dsmeta.projections.SaleReportProjection;
import com.devsuperior.dsmeta.projections.SaleSummaryProjection;

public interface SaleRepository extends JpaRepository<Sale, Long> {

    @Query(nativeQuery = true, value = """
        SELECT sales.id,
               sales.date,
               sales.amount,
               seller.name as sellerName
        FROM TB_SELLER AS seller
        JOIN TB_SALES AS sales
        ON seller.id = sales.seller_id
        WHERE LOWER(seller.name) LIKE CONCAT('%', LOWER(:sellerName), '%')
            AND sales.date BETWEEN :minDate AND :maxDate
    """, countQuery = """
        SELECT COUNT(*)
        FROM TB_SELLER AS seller
        JOIN TB_SALES AS sales
        ON seller.id = sales.seller_id
        WHERE LOWER(seller.name) LIKE CONCAT('%', LOWER(:sellerName), '%')
            AND sales.date BETWEEN :minDate AND :maxDate
    """)
    Page<SaleReportProjection> querySalesBy(String sellerName, LocalDate minDate, LocalDate maxDate, Pageable pageable);

    @Query(nativeQuery = true, value = """
        SELECT seller.name as sellerName,
               SUM(sales.amount) as total
        FROM TB_SELLER AS seller
        JOIN TB_SALES AS sales
        ON seller.id = sales.seller_id
        WHERE  sales.date BETWEEN :minDate AND :maxDate
        GROUP BY sellerName       
    """)
    List<SaleSummaryProjection> summarizeSalesBy(LocalDate minDate, LocalDate maxDate);

}
