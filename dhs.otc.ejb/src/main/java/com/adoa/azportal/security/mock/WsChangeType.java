package com.adoa.azportal.security.mock;
// Generated Sep 17, 2008 11:28:27 AM by Hibernate Tools 3.2.0.CR1


public class WsChangeType implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private String description;


	public WsChangeType() {
	}

	public WsChangeType(int id) {
		this.id = id;
	}
	public WsChangeType(int id, String description
		) {
		this.id = id;
		this.description = description;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}


}
