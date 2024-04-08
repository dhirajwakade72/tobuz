package com.tobuz.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name="business_service_type")
public class BusinessServiceType extends BaseEntity{
	
	@Column(name="business_service_type")
	private String businessServiceType;

	public String getBusinessServiceType() {
		return businessServiceType;
	}

	public void setBusinessServiceType(String businessServiceType) {
		this.businessServiceType = businessServiceType;
	}
	
}
