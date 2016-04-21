package com.adoa.azportal.security.mock;
// Generated Sep 17, 2008 11:28:27 AM by Hibernate Tools 3.2.0.CR1

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * User generated by hbm2java
 */

public class WsUser implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private String username;
	private String state;
	private String password;
	private String firstName;
	private String lastName;
	private String businessName;
	private String phone;
	private String street;
	private String street2;
	private String city;
	private String zipcode;
	private String email;
	private String securityQuestion;
	private String securityAnswer;
	private String changePasswordFlag;
	private String status;
	private String createdBy;
	private Date createdDate;
	private Date lastUpdatedDate;
	private String lastUpdatedBy;
	private Date lastPasswordResetDate;
	private String key;
	private Date resetDate;
	private String county;
	private String title;
	private List<WsPreviousPassword> previousPasswords = new ArrayList<WsPreviousPassword>(
			0);
	private List<WsUserAccess> userAccesses = new ArrayList<WsUserAccess>(0);
	
	public WsUser() {
	}

	public WsUser(int id) {
		this.id = id;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}


	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}


	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}


	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getBusinessName() {
		return this.businessName;
	}

	public void setBusinessName(String businessName) {
		if ("".equals(businessName)){
			businessName = null;
		}
		this.businessName = businessName;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getStreet() {
		return this.street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getStreet2() {
		return this.street2;
	}

	public void setStreet2(String street2) {
		this.street2 = street2;
	}

	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getZipcode() {
		return this.zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}
	
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSecurityQuestion() {
		return this.securityQuestion;
	}

	public void setSecurityQuestion(String securityQuestion) {
		this.securityQuestion = securityQuestion;
	}

	public String getSecurityAnswer() {
		return this.securityAnswer;
	}

	public void setSecurityAnswer(String securityAnswer) {
		this.securityAnswer = securityAnswer;
	}

	public String getChangePasswordFlag() {
		return this.changePasswordFlag;
	}

	public void setChangePasswordFlag(String changePasswordFlag) {
		this.changePasswordFlag = changePasswordFlag;
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
	

	public Date getLastPasswordResetDate() {
		return this.lastPasswordResetDate;
	}

	public void setLastPasswordResetDate(Date lastPasswordResetDate) {
		this.lastPasswordResetDate = lastPasswordResetDate;
	}
	

	public String getKey() {
		return this.key;
	}

	public void setKey(String key) {
		this.key = key;
	}
	

	public Date getResetDate() {
		return this.resetDate;
	}

	public void setResetDate(Date resetDate) {
		this.resetDate = resetDate;
	}
	

	public String getCounty() {
		return this.county;
	}

	public void setCounty(String county) {
		this.county = county;
	}
	

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<WsPreviousPassword> getPreviousPasswords() {
		return this.previousPasswords;
	}

	public void setPreviousPasswords(List<WsPreviousPassword> previousPasswords) {
		this.previousPasswords = previousPasswords;
	}
	

	public List<WsUserAccess> getUserAccesses() {
		return this.userAccesses;
	}

	public void setUserAccesses(List<WsUserAccess> userAccesses) {
		this.userAccesses = userAccesses;
	}

	public boolean equals(Object o)
	{	
		if(! (o instanceof WsUser)) 
			return false;
		WsUser u = (WsUser) o;
		if (!this.businessName.equals(u.businessName)){
			return false;
		}
		if (!this.city.equals(u.city)){
			return false;
		}
    	if (!this.email.equals(u.email)){
			return false;
		}
		if (!this.firstName.equals(u.firstName)){
			return false;
		}
		if (!this.lastName.equals(u.lastName)){
			return false;
		}
		if (!this.phone.equals(u.phone)){
			return false;
		}
		if (!this.getState().equals(u.getState())){
			return false;
		}
		if (!this.status.equals(u.status)){
			return false;
		}
		if (!this.street.equals(u.street)){
			return false;
		}
		if (!this.street2.equals(u.street2)){
			return false;
		}
		if (!this.zipcode.equals(u.zipcode)){
			return false;
		}
		return true;
	}

}
