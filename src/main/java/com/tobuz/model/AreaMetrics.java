package com.tobuz.model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import org.springframework.ui.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class AreaMetrics extends BaseEntity{
	
	@JsonIgnore
	@ManyToOne
	private Country country;
	
	private String metric;
	

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	public String getMetric() {
		return metric;
	}

	public void setMetric(String metric) {
		this.metric = metric;
	}
}