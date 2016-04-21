package com.adoa.azportal.application.impl;

import java.util.Properties;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.jboss.logging.Logger;

import com.adoa.azportal.domain.LineItem;
import com.adoa.azportal.domain.Orders;
import com.adoa.azportal.util.GlobalParams;

/**
 * @author Lance Stine
 *
 */
// @Stateful
// @RequestScoped
/*
 * @Scope( ScopeType.EVENT )
 * 
 * @Name("checkoutEnd")
 */

@Path("/CheckoutEnd")
@Stateless
public class CheckoutEndAction {

	private @Inject Logger applog;

	@Inject
	@GlobalParams
	Properties properties;

	@Inject
	private EntityManager entityManager;

	private Orders orders = new Orders();

	@GET
	public Response receiveCheckout(

			@QueryParam("orderNumber") String orderNumber, @QueryParam("paymentStatus") String paymentStatus,
			@QueryParam("authorizationCode") String authorizationCode,
			@QueryParam("partialCCNumber") String partialCCNumber, @QueryParam("creditCardType") String creditCardType

	) {

		try {

			String returnUrl = properties.getProperty("dhsotc/home");

			if (authorizationCode == null || authorizationCode.equals("")) {

				// this is not an error because a decline will call back without
				// an auth code
				applog.debug("receiveCheckout called with no authorizaton code");

				try {
					returnUrl = properties.getProperty("app/root");
				} catch (Exception e) {// catch (NamingException e) {
					applog.error("properties lookup failed for app/root");
				}

				returnUrl += "/app/dhsotc";

			}

			// payment status is always 1 unless an error occurs on checkout's
			// side

			if (paymentStatus != null && paymentStatus.equals("0")) {
				applog.error("receiveCheckout called with 0 for paymentStatus");
				throw new Exception("receiveCheckout called with paymentStatus of " + paymentStatus);
			}

			if (orderNumber != null && orderNumber.equals("")) {
				applog.error("receiveCheckout called with no orderNumber");
				throw new Exception("receiveCheckout called with no orderNumber " + orderNumber);
			}

			if (partialCCNumber != null && partialCCNumber.equals("")) {
				applog.error("receiveCheckout called with no partialCCNumber");
				throw new Exception("receiveCheckout called with no partialCCNumber " + partialCCNumber);
			}

			if (creditCardType != null && creditCardType.equals("")) {
				applog.error("receiveCheckout called with no creditCardType");
				throw new Exception("receiveCheckout called with no creditCardType " + creditCardType);
			}

			// update database with above values
			orders = entityManager.find(Orders.class, Long.parseLong(orderNumber));
			orders.setTpeOrderNumber(Long.parseLong(authorizationCode));
			orders.setCreditCardType(creditCardType);
			orders.setPartialCC(partialCCNumber);
			orders.setOrderStatus("C");

			if (orders.getLineItems() == null || orders.getLineItems().isEmpty())
				applog.debug("orders.getLineItems is NULL or EMPTY.");
			else {
				applog.debug("orders.getLineItems are...");
				for (LineItem i : orders.getLineItems()) {

					applog.debug("order id:   " + i.getOrders().getId());
					// changed to set as short description for payment service
					// to correctly correlate codes.
					applog.debug("prod lic:   " + i.getProductCatalog().getShortDesc());
					applog.debug("item fees:  " + i.getItemFees());
				}

			}
			applog.debug("merge checkout data with orders orderNumber: " + orderNumber);

			entityManager.merge(orders);
			entityManager.flush();

			returnUrl = returnUrl + "?orderNumber=" + orderNumber;

			return Response.status(Status.OK).header("Location", returnUrl).build();

		} catch (Exception e) {
			// not found, multiple entries
			applog.error("receiveCheckout failue  for order number " + orderNumber, e);
			e.printStackTrace();
			try {
				String returnUrl = properties.getProperty("app/root");

				return Response.status(Status.OK).header("Location", returnUrl + "/app/dhsotc/paymenterror.xhtml")
						.build();

			} catch (Exception ex) {
				applog.error("properties lookup failed for app/root");
			}

			return Response.status(Status.OK).entity("error : bigtime").build();

		}
	}

	/*
	 * public String generateReceipt() { String retVal = "";
	 * 
	 * String orderNumber = request.getParameter("orderNumber"); orders =
	 * entityManager.find(Orders.class, Long.parseLong(orderNumber));
	 * 
	 * return retVal; }
	 */

	// public Orders getOrders() {
	// return orders;
	// }
	//
	// public void setOrders(Orders orders) {
	// this.orders = orders;
	// }

}
