package com.tobuz.object;

import java.io.Serializable;

public class BusinessServiceTypeDTO implements Serializable {
	
	private String serviceType;
	
	private long id;

	public String getServiceType() {
		return serviceType;
	}

	public void setServiceType(String serviceType) {
		this.serviceType = serviceType;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	

}
