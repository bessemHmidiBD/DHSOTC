package com.adoa.azportal.security.mock;

import java.util.Calendar;
import java.util.List;

import com.adoa.azportal.security.SasServiceFacade;



@SasMock
public class SasServiceMockImpl implements SasServiceFacade{

	@Override
	public WsUserCookie loginCookie(String username, String password, int serviceId, boolean expirationCheck) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public WsUserCookie validateCookie(String cookie, Integer timoutInMilliseconds) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String login(String username, String password, int agnecyId, boolean expirationCheck) {
		
		System.out.println(
				
				String.format("userName: %s password: %s agencyId: %d , exp: %s",username,password,agnecyId,expirationCheck)
				
				);
		
		if("valid".equals(username)&&("valid".equals(password))){ return "0";}
		if("expired".equals(username)&&("expired".equals(password))){ return "2";}
		if("error".equals(username)&&("error".equals(password))){ return "3";}
		if("noactive".equals(username)&&("noactive".equals(password))){ return "4";}
		return "1";
	}

	@Override
	public List<WsUser> eSigList(int serviceId, String roleName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public WsUser getUser(String username, int serviceId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public WsUser getFullUser(String username) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public WsUser getUserById(int userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void logoutCookie(String username) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<WsUser> retrieveUsersByServiceStatus(int serviceId, String status) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String updateUserStatusRole(WsUser wsUser, int serviceId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<WsUser> retrieveUserUpdates(int serviceId, Calendar date) {
		// TODO Auto-generated method stub
		return null;
	}

}
