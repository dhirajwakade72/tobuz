package com.tobuz.model;

import java.util.List;

import javax.persistence.Entity;


@Entity
public class InvestmentRange extends BaseEntity {
	
	public String investmentRangeKey;
	
	public String investmentRangeValue;
	
	public String getInvestmentRangeKey() {
		return investmentRangeKey;
	}


	public void setInvestmentRangeKey(String investmentRangeKey) {
		this.investmentRangeKey = investmentRangeKey;
	}


	public String getInvestmentRangeValue() {
		return investmentRangeValue;
	}


	public void setInvestmentRangeValue(String investmentRangeValue) {
		this.investmentRangeValue = investmentRangeValue;
	}


}
