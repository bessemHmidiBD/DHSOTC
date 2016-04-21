package com.adoa.azportal.security.mock;
// Generated Sep 17, 2008 11:28:27 AM by Hibernate Tools 3.2.0.CR1

import java.util.Date;

public class WsService implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private WsAgency agency;
	private String name;
	private String description;
	private String active;
	private Boolean hasRole;
	private Boolean hasReportService;
	private Boolean hasAdministrator;
	private String createdBy;
	private Date createdDate;
	private Date lastUpdatedDate;
	private String lastUpdatedBy;
	private String requireAgencyId;
	private String url;


	public WsService() {
	}

	public WsService(int id) {
		this.id = id;
	}
	public WsService(int id, WsAgency agency, String name, String description,
			String active, Boolean hasRole, Boolean hasReportService,
			Boolean hasAdministrator, String createdBy, Date createdDate,
			Date lastUpdatedDate, String lastUpdatedBy,String requireAgencyId,
			String url
			) {
		this.id = id;
		this.agency = agency;
		this.name = name;
		this.description = description;
		this.active = active;
		this.hasRole = hasRole;
		this.hasReportService = hasReportService;
		this.hasAdministrator = hasAdministrator;
		this.createdBy = createdBy;
		this.createdDate = createdDate;
		this.lastUpdatedDate = lastUpdatedDate;
		this.lastUpdatedBy = lastUpdatedBy;
		this.requireAgencyId = requireAgencyId;
		this.url = url;
		
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public WsAgency getAgency() {
		return this.agency;
	}

	public void setAgency(WsAgency agency) {
		this.agency = agency;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getActive() {
		return this.active;
	}

	public void setActive(String active) {
		this.active = active;
	}

	public Boolean getHasRole() {
		return this.hasRole;
	}

	public void setHasRole(Boolean hasRole) {
		this.hasRole = hasRole;
	}

	public Boolean getHasReportService() {
		return this.hasReportService;
	}

	public void setHasReportService(Boolean hasReportService) {
		this.hasReportService = hasReportService;
	}

	public Boolean getHasAdministrator() {
		return this.hasAdministrator;
	}

	public void setHasAdministrator(Boolean hasAdministrator) {
		this.hasAdministrator = hasAdministrator;
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
	
	public String getRequireAgencyId() {
		return this.requireAgencyId;
	}

	public void setRequireAgencyId(String requireAgencyId) {
		this.requireAgencyId = requireAgencyId;
	}
	
	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}



}
