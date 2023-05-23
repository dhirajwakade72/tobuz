package com.tobuz.object;

import java.io.Serializable;

public class SubCategoryDTO implements Serializable{

	private String name;
	
	private long id ;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	
}
