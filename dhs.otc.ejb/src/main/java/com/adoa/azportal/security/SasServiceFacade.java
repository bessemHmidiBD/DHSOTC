package com.adoa.azportal.security;

import java.util.Calendar;
import java.util.List;

import com.adoa.azportal.security.mock.WsUser;
import com.adoa.azportal.security.mock.WsUserCookie;


public interface SasServiceFacade {

	WsUserCookie loginCookie(String username, String password,int serviceId,boolean expirationCheck);
	WsUserCookie validateCookie(String cookie, Integer timoutInMilliseconds);
	String login(String username, String password, int agnecyId, boolean expirationCheck);
	List<WsUser> eSigList(int serviceId, String roleName);
	WsUser getUser(String username,int serviceId);
	WsUser getFullUser(String username);
	WsUser getUserById(int userId);
	void logoutCookie(String username);
	
	List<WsUser> retrieveUsersByServiceStatus(int serviceId, String status);
	String updateUserStatusRole(WsUser wsUser, int serviceId);
	List<WsUser> retrieveUserUpdates(int serviceId, Calendar date);
	
}
