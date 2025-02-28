package com.devsuperior.dsmeta.repositories;

import java.time.LocalDate;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.devsuperior.dsmeta.entities.Sale;

public interface SaleRepository extends JpaRepository<Sale, Long> {

	@Query("SELECT obj FROM Sale obj " +
		       "WHERE (:minDate IS NULL OR obj.date >= :minDate) " +
		       "AND (:maxDate IS NULL OR obj.date <= :maxDate) " +
		       "AND (:sellerName IS NULL OR LOWER(obj.seller.name) LIKE LOWER(CONCAT('%', :sellerName, '%')))")
		Page<Sale> searchSales(
		        @Param("minDate") LocalDate minDate,
		        @Param("maxDate") LocalDate maxDate,
		        @Param("sellerName") String sellerName,
		        Pageable pageable);

}
