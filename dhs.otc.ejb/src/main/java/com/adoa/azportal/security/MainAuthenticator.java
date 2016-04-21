
package com.adoa.azportal.security;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import org.jboss.logging.Logger;
import org.picketlink.annotations.PicketLink;
import org.picketlink.authentication.BaseAuthenticator;
import org.picketlink.idm.credential.Password;
import org.picketlink.idm.model.basic.User;

import com.adoa.azportal.security.mock.SasMock;

/**
 * <p>
 * A simple authenticator that supports two credential types: username/password
 * or a simple token.
 * </p>
 */
@RequestScoped
@PicketLink
public class MainAuthenticator extends BaseAuthenticator {

	@Inject
	private MainLoginCredentials credentials;

	@Inject
	Logger logger;

	@Inject
	@SasMock
	SasServiceFacade sasService;

	@Override
	public void authenticate() {
		if (this.credentials.getCredential() == null) {
			return;
		}

		if (isUsernamePasswordCredential()) {
			String userId = this.credentials.getUserId();
			Password password = (Password) this.credentials.getCredential();

			int agencyId = this.credentials.getAgencyId();

			String authType = sasService.login(userId, new String(password.getValue()), agencyId, true);

			if ("1".equals(authType)) {

				logger.info("Username and/or Password entered were invalid.");
				return;
			}
			if ("2".equals(authType)) {
				logger.info("Your password has expired please change your password.");

				return;
			}
			if ("3".equals(authType)) {

				logger.info("An unexpected error has occured, please try again.");
				return;
			}
			if ("4".equals(authType)) {

				logger.info(
						"User access is no longer active. Please contact the administrator of this service for assistance.");
				return;
			}
			if ("5".equals(authType)) {

				logger.info(
						"User access is no longer active. Please contact the administrator of this service for assistance.");
				return;
			}

			logger.info("Grant Access");
			successfulAuthentication();

		}
	}

	private boolean isUsernamePasswordCredential() {
		return Password.class.equals(credentials.getCredential().getClass()) && credentials.getUserId() != null;
	}

	private User getDefaultUser() {
		return new User("user");
	}

	private void successfulAuthentication() {
		setStatus(AuthenticationStatus.SUCCESS);
		setAccount(getDefaultUser());
	}

}
