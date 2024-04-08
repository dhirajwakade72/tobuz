package com.tobuz.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="testimonial")
public class Testimonial extends BaseEntity{
	
	@Column(name="email")
	private String email;
	
	@Column(name="description")
	private String description;
	
	@Column(name="user_name")
	private String userName;
	
	@Column(name="about_user")
	private String aboutUser;
	
	@Column(name="image_id")
	private Long imageId;
	
	@Column(name="is_admin_approved")
	private Boolean isAdminApproved;
	
	@Column(name="testimonial_by_user_id")
	private Long testimonialByUserId;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getAboutUser() {
		return aboutUser;
	}

	public void setAboutUser(String aboutUser) {
		this.aboutUser = aboutUser;
	}

	public Long getImageId() {
		return imageId;
	}

	public void setImageId(Long imageId) {
		this.imageId = imageId;
	}

	public Boolean getIsAdminApproved() {
		return isAdminApproved;
	}

	public void setIsAdminApproved(Boolean isAdminApproved) {
		this.isAdminApproved = isAdminApproved;
	}

	public Long getTestimonialByUserId() {
		return testimonialByUserId;
	}

	public void setTestimonialByUserId(Long testimonialByUserId) {
		this.testimonialByUserId = testimonialByUserId;
	}
	
	

}
