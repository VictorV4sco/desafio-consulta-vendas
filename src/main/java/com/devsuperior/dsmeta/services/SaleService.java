package com.devsuperior.dsmeta.services;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.devsuperior.dsmeta.dto.SaleMinDTO;
import com.devsuperior.dsmeta.dto.SummaryDTO;
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
	
	public Page<SaleMinDTO> findSales(String minDate, String maxDate, String sellerName, Pageable pageable) {
		LocalDate max = maxDate.isEmpty() ? LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault()) : LocalDate.parse(maxDate);
		LocalDate min = minDate.isEmpty() ? max.minusYears(1L) : LocalDate.parse(minDate);
	
		return repository.searchBySales(min, max, sellerName, pageable);
	}
	
	public List<SummaryDTO> findSallerAmount(String minDate, String maxDate) {
        LocalDate max = maxDate.isEmpty() ? LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault()) : LocalDate.parse(maxDate);
        LocalDate min = minDate.isEmpty() ? max.minusYears(1L) : LocalDate.parse(minDate);

        return repository.findSalesGroupedBySellerAndDate(min, max);
    }
	
	
}
