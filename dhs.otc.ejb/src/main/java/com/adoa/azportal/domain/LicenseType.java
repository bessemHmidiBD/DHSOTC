package com.adoa.azportal.domain;

// Generated Dec 24, 2009 6:29:00 PM by Hibernate Tools 3.2.4.GA

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "LICENSE_TYPE")
@NamedQueries({ @NamedQuery(name = "getLicenseTypes", query = "select l from LicenseType l ") })
public class LicenseType implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private short id;
	private String description;
	private String shortDesc;

	public LicenseType() {
	}

	public LicenseType(short id, String description, String shortDesc) {
		this.id = id;
		this.description = description;
		this.shortDesc = shortDesc;
	}

	@Id
	@Column(name = "ID", unique = true, nullable = false)
	public short getId() {
		return this.id;
	}

	public void setId(short id) {
		this.id = id;
	}

	@Column(name = "DESCRIPTION", nullable = false, length = 100)
	@NotNull
	@Size(max = 100)
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name = "SHORT_DESC", nullable = false, length = 4)
	@NotNull
	@Size(max = 4)
	public String getShortDesc() {
		return this.shortDesc;
	}

	public void setShortDesc(String shortDesc) {
		this.shortDesc = shortDesc;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LicenseType other = (LicenseType) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "LicenseType [id=" + id + ", description=" + description + ", shortDesc=" + shortDesc + "]";
	}

	
	
}
