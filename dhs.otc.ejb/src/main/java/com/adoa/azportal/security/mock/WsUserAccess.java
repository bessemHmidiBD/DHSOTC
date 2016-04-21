package com.adoa.azportal.security.mock;
// Generated Sep 17, 2008 11:28:27 AM by Hibernate Tools 3.2.0.CR1

import java.util.Date;

public class WsUserAccess implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private WsUserRole userRole;
	private WsService service;
	private WsReportService reportService;
	private String agencyUserId;
	private String status;
	private String createdBy;
	private Date createdDate;
	private Date lastUpdatedDate;
	private String lastUpdatedBy;

	public WsUserAccess() {
	}

	public WsUserAccess(int id) {
		this.id = id;
	}
	public WsUserAccess(int id, WsUserRole userRole,
			WsService service, WsReportService reportService,
			String agencyUserId, String status, String createdBy,
			Date createdDate, Date lastUpdatedDate, String lastUpdatedBy) {
		this.id = id;
		this.userRole = userRole;
		this.service = service;
		this.reportService = reportService;
		this.agencyUserId = agencyUserId;
		this.status = status;
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
	
	public WsUserRole getUserRole() {
		return this.userRole;
	}

	public void setUserRole(WsUserRole userRole) {
		this.userRole = userRole;
	}
	

	public WsService getService() {
		return this.service;
	}

	public void setService(WsService service) {
		this.service = service;
	}
	

	public WsReportService getReportService() {
		return this.reportService;
	}

	public void setReportService(WsReportService reportService) {
		this.reportService = reportService;
	}

	
	public String getAgencyUserId() {
		return this.agencyUserId;
	}

	public void setAgencyUserId(String agencyUserId) {
		this.agencyUserId = agencyUserId;
	}

	
	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
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
