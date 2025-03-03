package com.devsuperior.dsmeta.dto;

public class SummaryDTO {
	
	private String sellerName;
	private Double amount;
	
	public SummaryDTO() {
	}

	public SummaryDTO(String sellerName, Double amount) {
		this.sellerName = sellerName;
		this.amount = amount;
	}

	public String getSellerName() {
		return sellerName;
	}

	public void setSellerName(String sellerName) {
		this.sellerName = sellerName;
	}

	public Double getTotalAmount() {
		return amount;
	}

	public void setTotalAmount(Double totalAmount) {
		this.amount = totalAmount;
	}
	
}
