package com.devsuperior.dsmeta.repositories;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.devsuperior.dsmeta.dto.SaleMinDTO;
import com.devsuperior.dsmeta.dto.SummaryDTO;
import com.devsuperior.dsmeta.entities.Sale;

public interface SaleRepository extends JpaRepository<Sale, Long> {
	
	@Query(
		"SELECT"
				+ " new com.devsuperior.dsmeta.dto.SaleMinDTO(s.id, s.amount, s.date, s.seller.name)"
		+ " FROM Sale s"
		+ " INNER JOIN Seller sl ON s.seller.id = sl.id"
		+ " WHERE s.date BETWEEN :minDate AND :maxDate"
			+ " AND (LOWER(s.seller.name) LIKE LOWER(CONCAT('%', :sellerName, '%')))")
	Page<SaleMinDTO> searchBySales(
			@Param("minDate") LocalDate minDate, 
			@Param("maxDate") LocalDate maxDate,
			@Param("sellerName") String sellerName, 
			Pageable pageable
		);

	@Query(
		    "SELECT new com.devsuperior.dsmeta.dto.SummaryDTO(s.seller.name, SUM(s.amount)) "
		    +	"FROM Sale s "
		    +	"WHERE (:startDate IS NULL OR s.date >= :startDate) "
		    +	"AND (:endDate IS NULL OR s.date <= :endDate) "
		    +	"GROUP BY s.seller.name"
		)
		List<SummaryDTO> findSalesGroupedBySellerAndDate(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);


}

//SELECT tb_seller.name, SUM(tb_sales.amount) AS total_amount
//FROM tb_sales
//INNER JOIN tb_seller ON tb_sales.seller_id = tb_seller.id
//WHERE tb_sales.date BETWEEN '2023-12-14' AND '2024-06-16'
//GROUP BY tb_seller.name


