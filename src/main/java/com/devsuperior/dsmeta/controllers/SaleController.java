package com.devsuperior.dsmeta.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.devsuperior.dsmeta.dto.SaleMinDTO;
import com.devsuperior.dsmeta.dto.SummaryDTO;
import com.devsuperior.dsmeta.services.SaleService;

@RestController
@RequestMapping(value = "/sales")
public class SaleController {

	@Autowired
	private SaleService service;
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<SaleMinDTO> findById(@PathVariable Long id) {
		SaleMinDTO dto = service.findById(id);
		return ResponseEntity.ok(dto);
	}

	@GetMapping(value = "/report")
	public ResponseEntity<Page<SaleMinDTO>> getReport(
			@RequestParam(defaultValue = "") String minDate, 
			@RequestParam(defaultValue = "") String maxDate,
			@RequestParam(name = "name", defaultValue = "") String sellerName,
			@PageableDefault(size = 12, page = 0) Pageable pageable
		) {
		
		return ResponseEntity.ok(service.findSales(minDate, maxDate, sellerName, pageable));
	}
	
	@GetMapping(value = "/summary")
	public ResponseEntity<List<SummaryDTO>> getSummary(
			@RequestParam(defaultValue = "") String minDate, 
			@RequestParam(defaultValue = "") String maxDate
			) {
		
		List<SummaryDTO> sales = service.findSallerAmount(minDate, maxDate);
		return ResponseEntity.ok(sales); 
	}
	

}
