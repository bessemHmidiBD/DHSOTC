package com.adoa.azportal.security.mock;
// Generated Sep 17, 2008 11:28:27 AM by Hibernate Tools 3.2.0.CR1

import java.util.Date;

public class WsAgency implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private String name;
	private String acronym;
	private Character active;
	private String createdBy;
	private Date createdDate;
	private Date lastUpdatedDate;
	private String lastUpdatedBy;


	public WsAgency() {
	}

	public WsAgency(int id) {
		this.id = id;
	}
	public WsAgency(int id, String name, String acronym, Character active,
			String createdBy, Date createdDate, Date lastUpdatedDate,
			String lastUpdatedBy) {
		this.id = id;
		this.name = name;
		this.acronym = acronym;
		this.active = active;
		this.createdBy = createdBy;
		this.createdDate = createdDate;
		this.lastUpdatedDate = lastUpdatedDate;
		this.lastUpdatedBy = lastUpdatedBy;

	}
	
	
	

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAcronym() {
		return this.acronym;
	}

	public void setAcronym(String acronym) {
		this.acronym = acronym;
	}

	public Character getActive() {
		return this.active;
	}

	public void setActive(Character active) {
		this.active = active;
	}

	public String getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	
	public Date getCreatedDate() {
		return this.createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	

	public Date getLastUpdatedDate() {
		return this.lastUpdatedDate;
	}

	public void setLastUpdatedDate(Date lastUpdatedDate) {
		this.lastUpdatedDate = lastUpdatedDate;
	}

	

	public String getLastUpdatedBy() {
		return this.lastUpdatedBy;
	}

	public void setLastUpdatedBy(String lastUpdatedBy) {
		this.lastUpdatedBy = lastUpdatedBy;
	}
	

	
}
