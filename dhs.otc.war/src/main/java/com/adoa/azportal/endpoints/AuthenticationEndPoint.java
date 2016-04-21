
package com.adoa.azportal.endpoints;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.jboss.logging.Logger;
import org.picketlink.Identity;
import org.picketlink.idm.model.Account;

import com.adoa.azportal.security.MainLoginCredentials;

/**
 * <p>
 * JAX-RS Authentication End Point.
 * </p>
 */
@Path("/authenticate")
public class AuthenticationEndPoint {

	public static final String USERNAME_PASSWORD_CREDENTIAL_CONTENT_TYPE = "application/x-authc-username-password+json";

	@Inject
	Logger logger;

	@Inject
	private Identity identity;

	@Inject
	private MainLoginCredentials credentials;

	@POST
	@Consumes({ USERNAME_PASSWORD_CREDENTIAL_CONTENT_TYPE })
	public Response authenticate(MainLoginCredentials credential) {
		if (!this.identity.isLoggedIn()) {
			this.credentials.setUserId(credential.getUserId());
			this.credentials.setPassword(credential.getPassword());
			this.credentials.setAgencyId(credential.getAgencyId());
			this.identity.login();
		}

		logger.debug("AUTHENTICATION...");

		Account account = this.identity.getAccount();

		return Response.ok().entity(account).type(MediaType.APPLICATION_JSON_TYPE).build();
	}

	@POST
	@Consumes({ "*/*" })
	public Response unsupportedCredentialType() {
		return Response.status(Response.Status.UNSUPPORTED_MEDIA_TYPE).build();
	}

}
