package com.adoa.azportal.security.mock;
// Generated Sep 17, 2008 11:28:27 AM by Hibernate Tools 3.2.0.CR1

import java.util.Date;
     
public class WsAppUserRole implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private Date lastUpdatedDate;
	private String lastUpdatedBy;	

	public WsAppUserRole() {
	}

	public WsAppUserRole(int id) {
		this.id = id;
	}
	public WsAppUserRole(int id, 
			Date lastUpdatedDate,
			String lastUpdatedBy) {
		this.id = id;
		this.lastUpdatedDate = lastUpdatedDate;
		this.lastUpdatedBy = lastUpdatedBy;
		
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
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
	/*
	public boolean equals(Object o){	
		if(! (o instanceof WsAppUserRole)) 
			return false;
		WsAppUserRole aur = (WsAppUserRole) o;
		if (!(this.service.getId() == aur.service.getId())){
			return false;
		}
		if (!(this.userRole.getId() == aur.userRole.getId())){
			return false;
		}
		return true;
	}*/

}
