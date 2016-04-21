package com.adoa.azportal.security;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Specializes;

import org.picketlink.credential.DefaultLoginCredentials;

@RequestScoped
@Specializes
public class MainLoginCredentials extends DefaultLoginCredentials {

	private int agencyId;

	public int getAgencyId() {
		return agencyId;
	}

	public void setAgencyId(int agencyId) {
		this.agencyId = agencyId;
	}

}
