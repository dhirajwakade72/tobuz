package com.tobuz.object;

public class MessageDTO {

	private long id ;
	
	private String name ;
	
	private String email ;
	
	private String dialingCode ;
	
	private String mobile;
	
	private String createdOn ;
	
	
	private String subject;
	
	private String bodyText;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDialingCode() {
		return dialingCode;
	}

	public void setDialingCode(String dialingCode) {
		this.dialingCode = dialingCode;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(String createdOn) {
		this.createdOn = createdOn;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getBodyText() {
		return bodyText;
	}

	public void setBodyText(String bodyText) {
		this.bodyText = bodyText;
	}
	
	
}
