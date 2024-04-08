package com.tobuz.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="opt_details")
@Data
@AllArgsConstructor
@NoArgsConstructor
@SequenceGenerator(name = "opt_details_id_seq", sequenceName = "opt_details_id_seq", allocationSize = 1)
public class OtpDetails{
	
	@Id	
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "opt_details_id_seq")		
	@Column(name="id")
	private Long id;
	
	@Column(name="email")
	private String email;
	
	@Column(name="otp")
	private String otp;
	
	@Column(name="expiry_date")
	private LocalDateTime expiryDateTime;
	
	@Column(name="purpose")
	private String purpose;
	
	@Column(name="status")
	private String status;

	public OtpDetails(String email,String otp,String purpose) {
		super();
		this.email = email;
		this.purpose = purpose;
		this.otp=otp;
		this.expiryDateTime=LocalDateTime.now().plusMinutes(5);
		this.status="Y";		
	}

	
	
}
