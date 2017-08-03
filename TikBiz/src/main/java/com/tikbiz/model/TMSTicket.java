package com.tikbiz.model;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.PersistenceConstructor;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name="TMS_TICKET")
public class TMSTicket implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public TMSTicket(){
		
	}
	
	public TMSTicket(String errorMessage) {
		super();
		this.errorMessage = errorMessage;
	}
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="DESCRIPTION", nullable=false)
	private String description;
	
	@Column(name="STATUS", nullable=false)
	private String status;
	
	@Column(name="PRIORITY", nullable=false)
	private String priority;
	
	@Column(name="CREATED_BY")
	private String createdBy;
	
	@Column(name="MODIFIED_BY")
	private String modifiedBy;
	
	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATED_DATE")
	private Date createdDate;

	@UpdateTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "MODIFIED_DATE")
	private Date modifiedDate;
	
	@Transient
	private String errorMessage;
	
	@PersistenceConstructor
	public TMSTicket(Long id, String description, String status,
			String priority, String createdBy, String modifiedBy) {
		super();
		this.id = id;
		this.description = description;
		this.status = status;
		this.priority = priority;
		this.createdBy = createdBy;
		this.modifiedBy = modifiedBy;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getPriority() {
		return priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}
	
}
