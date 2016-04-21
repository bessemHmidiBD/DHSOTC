package com.adoa.azportal.application.impl;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Properties;
import java.util.Random;

import javax.ejb.Remove;
import javax.ejb.Stateful;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;

import org.hibernate.validator.InvalidStateException;
import org.hibernate.validator.InvalidValue;
import org.jboss.logging.Logger;
import org.picketlink.Identity;

import com.adoa.azportal.application.interfaces.RenewalManager;
import com.adoa.azportal.domain.LicenseType;
import com.adoa.azportal.domain.LineItem;
import com.adoa.azportal.domain.Orders;
import com.adoa.azportal.domain.ProductCatalog;
import com.adoa.azportal.util.GlobalParams;
import com.adoa.azportal.util.JSFContextEmulator;
import com.adoa.azportal.util.Response;

import com.adoa.azportal.util.Severity;
//import com.adoa.azportal.util.JndiLookup;
import com.nicusa.azportal.scs.GAOTransactionMarshal;
import com.nicusa.azportal.scs.HTTPCapture;
import com.nicusa.azportal.scs.HTTPPostHelper;

import gov.az.payment.GAOLineItemType;
import gov.az.payment.GAOTransactionLineItemList;
import gov.az.payment.GAOTransactionType;

@Stateful
@Named
// @Scope(ScopeType.CONVERSATION)
public class RenewalAction implements RenewalManager {

	@Inject
	JSFContextEmulator jsfContextEmulator;

	@Inject
	Logger appLog;

	@Inject
	Identity identity;

	@Inject
	private EntityManager entityManager;

	@Inject
	@GlobalParams
	Properties properties;

	private Orders orders = new Orders();
	private long facilityId;
	private String userId;
	private int numBedsKids;
	private Double copyFees;
	private Double amountpenalty;
	private String invoiceNumber;
	// private double totalBedFees;
	LicenseType licenseType = new LicenseType();
	List<ProductCatalog> feesList;
	List<LineItem> lineItemList = null;
	private Double totalFees;
	private Double totalBedFees = new Double(0.0);
	private Double calculatedFees = new Double(0.0);
	private Double totalRenewalFees = new Double(0.0);

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.nicusa.az.dhs.otc.action.RenewalManager#checkForm()
	 */
	
	
	
	public String checkForm() {

		
		String retval = "valid";
		lineItemList = new ArrayList<LineItem>(0);
		orders.setMiscellaneousFee(copyFees != null ? copyFees : 0.0);
		orders.setPenaltyAmount(amountpenalty != null ? amountpenalty : 0.0);

		appLog.debug("Checking form information.");

		try {

			// try {

			
			// Validation of input.
						if (orders.getLicenseType() == null || orders.getLicenseType().getId() < 1) {
							appLog.debug("You must select a License Type from the dropdown menu.");
							jsfContextEmulator.getResponse().addMessage(Severity.ERROR, "You must select a License Type from the dropdown menu.");
							retval= "invalid";
							return retval;
						}

						
						if
						((orders.getLicenseType().getId() < 9 && copyFees==null)
						||
						(orders.getLicenseType().getId() < 9 && orders.getMiscellaneousFee() < 0.0)
						)
						{
							appLog.debug(
									"You must enter a valid Copy Fee.  Only enter numbers or a decimal. If no fees apply, enter 0.00.");
							jsfContextEmulator.getResponse().addMessage(Severity.ERROR,
									"You must enter a valid Copy Fee. Only enter numbers or a decimal. If no fees apply, enter 0.00.");
							retval="invalid";
							
						}
						
			if ( orders.getLicenseType().getId() < 10
					&& (orders.getFacilityId() == null
							|| orders.getFacilityId() != null && orders.getFacilityId().length() == 0)) {
				appLog.debug("You must enter the Facility ID.");
				jsfContextEmulator.getResponse().addMessage(Severity.ERROR, "You must enter the Facility ID.");
				retval= "invalid";
				
			}

			if ( orders.getLicenseType().getId() > 9
					&& (orders.getInvoiceNumber() == null
							|| orders.getInvoiceNumber() != null && orders.getInvoiceNumber().length() == 0)) {
				appLog.debug("You must enter the Invoice Number.");
				jsfContextEmulator.getResponse().addMessage(Severity.ERROR, "You must enter the Invoice Number.");
				retval= "invalid";
			}

			if (orders.getLicenseType().getId() > 9
					&& (orders.getPenaltyAmount() == null || orders.getPenaltyAmount() < (double) 0
							|| orders.getPenaltyAmount() > (double) 100000)) {
				appLog.debug(
						"You must enter the amount for the invoice number. Valid values are between 0 and 100,000 $.");
				jsfContextEmulator.getResponse().addMessage(Severity.ERROR,
						"You must enter the amount for the invoice number. Valid values are between 0 and 100,000 $.");
				retval= "invalid";
			}

			if (orders.getLicenseType().getId() == 9 && orders.getPenaltyAmount() == null) {
				appLog.debug("You must enter the Amount.");
				jsfContextEmulator.getResponse().addMessage(Severity.ERROR, "You must enter the Amount.");
				retval= "invalid";
			}

			if (orders.getLicenseType().getId() == 5 || orders.getLicenseType().getId() == 6
					|| orders.getLicenseType().getId() == 7 || orders.getLicenseType().getId() == 8) {

				appLog.debug("lic id more than 4 - reset the num beds.");

				// Set the number of beds/kids to zero.
				orders.setNumberBedsKids(0);
			} else if (orders.getLicenseType().getId() == 1 || orders.getLicenseType().getId() == 2
					|| orders.getLicenseType().getId() == 3 || orders.getLicenseType().getId() == 4) {

				appLog.debug("lic id less than 5 - reset flags to FALSE.");

				// Set the child care flags to false.
				orders.setFlagUseEmpowerPack(false);
				orders.setFlagLateFee(false);
			}

			
			
			
			if (orders.getLicenseType().getId() < 9 && (orders.getNumberBedsKids() == null
					|| orders.getNumberBedsKids() < 0 || orders.getNumberBedsKids() > 10000)) {
				appLog.debug("You must enter the number of beds in the facility. Valid values are 0 � 10000.");
				jsfContextEmulator.getResponse().addMessage(Severity.ERROR,
						"You must enter the number of beds in the facility. Valid values are 0 � 10000.");
				retval= "invalid";
				
			}

			
			
			
			
			

		} catch (Exception e) {

			appLog.error("An unexpected exception occurred.", e);

			jsfContextEmulator.getResponse().addMessage(Severity.ERROR, "An error has occurred.");
			e.printStackTrace();

			retval= "invalid";
			
		}

		if (retval.equals("invalid"))return retval;
		
		appLog.debug("all is good...let's calculate fees.");

		if (orders.getLineItems() == null) {

			appLog.debug("orders.LineItems is NULL. Initializing...");
			orders.setLineItems(new ArrayList<LineItem>(0));
		}

		// Calculate fees for selected license.
		if (orders.getLicenseType().getId() == 1 || orders.getLicenseType().getId() == 2
				|| orders.getLicenseType().getId() == 3 || orders.getLicenseType().getId() == 4) {

			this.calculateBedHomeFees();
		}

		if (orders.getLicenseType().getId() == 5 || orders.getLicenseType().getId() == 6
				|| orders.getLicenseType().getId() == 7 || orders.getLicenseType().getId() == 8) {
			this.calculateChildCareFees();
		}

		if (orders.getLicenseType().getId() == 9) {
			this.calculatePenaltiesFees();
		}

		if (orders.getLicenseType().getId() == 10 || orders.getLicenseType().getId() == 11) {
			this.calculateInvoiceFees(orders.getLicenseType().getId());
		}

		// Get timestamp for transaction.
		Calendar cal1 = Calendar.getInstance();
		cal1.getTime();
		appLog.debug("date/time: " + cal1.getTime());

		// Set remaining orders properties.
		orders.setOrderStatus("P");
		orders.setPaymentDate(cal1.getTime());
		orders.setUserId(getUserId());
		orders.setCalculatedAmount(this.calculatedFees);
		orders.setActualAmountPaid(this.totalFees);

		if (orders.getLineItems() == null || orders.getLineItems().isEmpty())
			appLog.debug("orders.getLineItems is NULL or EMPTY.");
		else {
			appLog.debug("orders.getLineItems are...");
			for (LineItem i : orders.getLineItems()) {

				appLog.debug("order id:   " + i.getOrders().getId());
				appLog.debug("prod lic:   " + i.getProductCatalog().getDescription());
				appLog.debug("item fees:  " + i.getItemFees());
			}

		}

		appLog.debug("order info: " + orders.toString(orders));



		return retval;
	}

	private void calculateBedHomeFees() {

		appLog.debug("entered calculateFees()...");

		totalBedFees = new Double(0.0);
		calculatedFees = new Double(0.0);
		totalRenewalFees = new Double(0.0);

		if (orders.getLicenseType().getId() < 5) {

			appLog.debug("calc fees for NON-child care.");

			orders.setFlagUseEmpowerPack(false);
			orders.setFlagLateFee(false);

			// Get the license fee for the beds.
			ProductCatalog bedLicense = (ProductCatalog) entityManager.createNamedQuery("getBedLicenseFee")
					.setParameter("licenseTypeId", orders.getLicenseType().getId())
					.setParameter("numBedsKids", orders.getNumberBedsKids()).getSingleResult();

			appLog.debug("bed license description: " + bedLicense.getDescription());
			Double bedLicFee = new Double(bedLicense.getProductFee().doubleValue());
			appLog.debug("bed lic fee: " + bedLicFee.doubleValue());

			calculatedFees = calculatedFees + bedLicFee;
			totalRenewalFees = totalRenewalFees + bedLicFee;

			LineItem bedLi = new LineItem();
			bedLi.setId(0);
			bedLi.setProductCatalog(bedLicense);
			bedLi.setOrders(orders);
			bedLi.setItemFees(bedLicFee);
			lineItemList.add(bedLi);

			if (orders.getNumberBedsKids() > 0) {

				appLog.debug("PER BED FEE - add it.");
				// Get the per bed fee for the beds.
				ProductCatalog perBedFee = (ProductCatalog) entityManager.createNamedQuery("getPerBedFee")
						.setParameter("licenseTypeId", orders.getLicenseType().getId()).getSingleResult();

				appLog.debug("per bed license: " + perBedFee.getDescription());
				appLog.debug("per bed fee: " + perBedFee.getProductFee());

				totalBedFees = perBedFee.getProductFee().doubleValue() * orders.getNumberBedsKids();
				appLog.debug("total bed fees: " + totalBedFees);

				calculatedFees = calculatedFees + totalBedFees;
				totalRenewalFees = totalRenewalFees + totalBedFees;

				LineItem perBedLi = new LineItem();
				perBedLi.setId(0);
				perBedLi.setProductCatalog(perBedFee);
				perBedLi.setOrders(orders);
				perBedLi.setItemFees(totalBedFees);
				lineItemList.add(perBedLi);
			}

			appLog.debug("APPLICATION FEE - add it.");
			// Get miscellaneous fee
			// ProductCatalog appFee =
			// (ProductCatalog)entityManager.createNamedQuery("getApplicationFee")
			// .setParameter("licenseTypeId", new Long(0))
			// .getSingleResult();

			ProductCatalog appFee = (ProductCatalog) entityManager.createNamedQuery("getApplicationFee")
					.setParameter("licenseTypeId", (short) 0).getSingleResult();

			appLog.debug("app fee license: " + appFee.getDescription());
			Double appLicFee = appFee.getProductFee().doubleValue();
			appLog.debug("app fee: " + appLicFee);

			calculatedFees = calculatedFees + appLicFee;
			totalRenewalFees = totalRenewalFees + appLicFee;

			LineItem appLi = new LineItem();
			appLi.setId(0);
			appLi.setProductCatalog(appFee);
			appLi.setOrders(orders);
			appLi.setItemFees(appLicFee);
			lineItemList.add(appLi);

			appLog.debug("MISCELLANEOUS FEE");
			Double miscLicFee = orders.getMiscellaneousFee();
			appLog.debug("misc fee: " + miscLicFee);

			if (miscLicFee > 0.0) {

				appLog.debug("MISCELLANEOUS FEE > 0.0 - add fee to list.");
				// Get miscellaneous fee
				// ProductCatalog miscFee = (ProductCatalog) entityManager
				// .createNamedQuery("getMiscellaneousFee")
				// .setParameter("licenseTypeId", new Long(0))
				// .getSingleResult();

				ProductCatalog miscFee = (ProductCatalog) entityManager.createNamedQuery("getMiscellaneousFee")
						.setParameter("licenseTypeId", (short) 0).getSingleResult();

				appLog.debug("misc fee license: " + miscFee.getDescription());

				totalRenewalFees = totalRenewalFees + miscLicFee;

				LineItem miscLi = new LineItem();
				miscLi.setId(0);
				miscLi.setProductCatalog(miscFee);
				miscLi.setOrders(orders);
				miscLi.setItemFees(miscLicFee);
				lineItemList.add(miscLi);
			}

			appLog.debug("calculatedFees:   " + calculatedFees);
			appLog.debug("totalRenewalFees: " + totalRenewalFees);

			appLog.debug("lineItemSet size: " + lineItemList.size());
			orders.setLineItems(lineItemList);
		}
		totalFees = totalRenewalFees;

		appLog.debug("exiting calculateFees()...");

	}

	@SuppressWarnings("unchecked")
	private void calculateChildCareFees() {

		appLog.debug("entered calculateChildCareFees()...");

		calculatedFees = new Double(0.0);
		totalRenewalFees = new Double(0.0);

		appLog.debug("Get all fees for license type.");
		List<ProductCatalog> childFeesList = (List<ProductCatalog>) entityManager
				.createNamedQuery("getAllFeesPerLicenseType")
				.setParameter("licenseTypeId", orders.getLicenseType().getId()).getResultList();

		appLog.debug("all child fees list size: " + childFeesList.size());

		int ctr = 0;
		boolean mainLicense = false;
		for (ProductCatalog pc : childFeesList) {

			appLog.debug("FEE: " + pc.getDescription());
			appLog.debug(pc.toString(pc));

			appLog.debug("testing conditions...");

			if (mainLicense == false) {

				if (orders.getFlagUseEmpowerPack() == false) {

					appLog.debug("   Orders Empower flag is FALSE - get REGULAR license.");
					if (pc.getFlagEmpower() == false && pc.getFlagLateFee() == false) {

						appLog.debug("Empower: " + pc.getFlagEmpower() + " - late fee: " + pc.getFlagLateFee());
						appLog.debug("Adding license to feesList: " + pc.getDescription());
						Double empLicFee = pc.getProductFee().doubleValue();

						LineItem empLicLi = new LineItem();
						empLicLi.setId(0);
						empLicLi.setProductCatalog(pc);
						empLicLi.setOrders(orders);
						empLicLi.setItemFees(empLicFee);
						lineItemList.add(empLicLi);

						calculatedFees = calculatedFees + empLicFee;
						totalRenewalFees = totalRenewalFees + empLicFee;

						appLog.debug("calculatedFees:   " + calculatedFees);
						appLog.debug("totalRenewalFees: " + totalRenewalFees);

						appLog.debug("lineItemSet size: " + lineItemList.size());
						orders.setLineItems(lineItemList);

						mainLicense = true;
					}
				} else {

					appLog.debug("   Orders Empower flag is TRUE - get EMPOWER license.");
					if (pc.getFlagEmpower() == true && pc.getFlagLateFee() == false) {

						appLog.debug("Empower: " + pc.getFlagEmpower() + " - late fee: " + pc.getFlagLateFee());
						appLog.debug("Adding license to feesList: " + pc.getDescription());

						Double regLicFee = pc.getProductFee().doubleValue();

						LineItem regLicLi = new LineItem();
						regLicLi.setId(0);
						regLicLi.setProductCatalog(pc);
						regLicLi.setOrders(orders);
						regLicLi.setItemFees(regLicFee);
						lineItemList.add(regLicLi);

						calculatedFees = calculatedFees + regLicFee;
						totalRenewalFees = totalRenewalFees + regLicFee;

						appLog.debug("calculatedFees:   " + calculatedFees);
						appLog.debug("totalRenewalFees: " + totalRenewalFees);

						appLog.debug("lineItemSet size: " + lineItemList.size());
						orders.setLineItems(lineItemList);

						mainLicense = true;
					}
				}
			}

			ctr++;
			appLog.debug("ctr: " + ctr);

			if (orders.getFlagLateFee() == true && mainLicense == true) {

				if (pc.getFlagEmpower() == false && pc.getFlagLateFee() == true) {

					appLog.debug("Late fee flag is TRUE - get LATE fee.");
					appLog.debug("Adding license to feesList: " + pc.getDescription());

					Double lateLicFee = pc.getProductFee().doubleValue();

					LineItem lateLicLi = new LineItem();
					lateLicLi.setId(0);
					lateLicLi.setProductCatalog(pc);
					lateLicLi.setOrders(orders);
					lateLicLi.setItemFees(lateLicFee);
					lineItemList.add(lateLicLi);

					calculatedFees = calculatedFees + lateLicFee;
					totalRenewalFees = totalRenewalFees + lateLicFee;

					appLog.debug("calculatedFees:   " + calculatedFees);
					appLog.debug("totalRenewalFees: " + totalRenewalFees);

					appLog.debug("lineItemSet size: " + lineItemList.size());
					orders.setLineItems(lineItemList);
				}
			}
		}

		appLog.debug("MISCELLANEOUS FEE");
		appLog.debug("miscLicFee: " + orders.getMiscellaneousFee());

		if (orders.getMiscellaneousFee() > 0.0) {

			appLog.debug("MISCELLANEOUS FEE > 0.0 - add fee to list.");
			// Get miscellaneous fee
			// ProductCatalog miscFee = (ProductCatalog) entityManager
			// .createNamedQuery("getMiscellaneousFee")
			// .setParameter("licenseTypeId", new Long(0))
			// .getSingleResult();
			ProductCatalog miscFee = (ProductCatalog) entityManager.createNamedQuery("getMiscellaneousFee")
					.setParameter("licenseTypeId", (short) 0).getSingleResult();

			LineItem miscFeeLi = new LineItem();
			miscFeeLi.setId(0);
			miscFeeLi.setProductCatalog(miscFee);
			miscFeeLi.setOrders(orders);
			miscFeeLi.setItemFees(orders.getMiscellaneousFee());
			lineItemList.add(miscFeeLi);

			totalRenewalFees = totalRenewalFees + orders.getMiscellaneousFee();
			appLog.debug("totalRenewalFees: " + totalRenewalFees);
		}

		totalFees = totalRenewalFees;
		appLog.debug("total fees: " + totalFees);

		appLog.debug("lineItemSet size: " + lineItemList.size());
		orders.setLineItems(lineItemList);

		appLog.debug("exiting calculateFees()...");

	}

	private void calculateInvoiceFees(int type) {
		appLog.debug("entered calculateInvoiceFees()...");

		calculatedFees = new Double(0.0);
		totalRenewalFees = new Double(0.0);
		appLog.debug("lineItemSet size: " + lineItemList.size());
		orders.setLineItems(lineItemList);
		orders.setNumberBedsKids(0);
		orders.setFacilityId("0");

		appLog.debug("Invoice FEE");
		appLog.debug("InvoiceFee: " + orders.getInvoiceNumber());

		if (orders.getPenaltyAmount() > 0.0) {
			ProductCatalog penFee = new ProductCatalog();
			appLog.debug("Invoice FEE > 0.0 - add fee to list." + type);
			if (type == 10) {
				penFee = (ProductCatalog) entityManager.createNamedQuery("getNBSFirstFee")
						.setParameter("licenseTypeId", (short) type).getSingleResult();
			}

			if (type == 11) {
				penFee = (ProductCatalog) entityManager.createNamedQuery("getNBSSecondFee")
						.setParameter("licenseTypeId", (short) type).getSingleResult();
			}

			LineItem NbsFeeLi = new LineItem();
			NbsFeeLi.setId(0);
			NbsFeeLi.setProductCatalog(penFee);
			NbsFeeLi.setOrders(orders);
			NbsFeeLi.setItemFees(orders.getPenaltyAmount());
			lineItemList.add(NbsFeeLi);

			calculatedFees = calculatedFees + orders.getPenaltyAmount();
			totalRenewalFees = totalRenewalFees + orders.getPenaltyAmount();
			appLog.debug("totalRenewalFees: " + totalRenewalFees);
		}

		totalFees = totalRenewalFees;
		appLog.debug("total fees: " + totalFees);

		appLog.debug("lineItemSet size: " + lineItemList.size());
		orders.setLineItems(lineItemList);

		appLog.debug("exiting calculateFees()...");

	}

	private void calculatePenaltiesFees() {

		appLog.debug("entered calculatePenaltiesFees()...");

		calculatedFees = new Double(0.0);
		totalRenewalFees = new Double(0.0);

		appLog.debug("lineItemSet size: " + lineItemList.size());
		orders.setLineItems(lineItemList);
		orders.setNumberBedsKids(0);

		appLog.debug("Penalty FEE");
		appLog.debug("penaltyFee: " + orders.getPenaltyAmount());

		if (orders.getPenaltyAmount() > 0.0) {

			appLog.debug("PENALTY FEE > 0.0 - add fee to list.");
			// Get miscellaneous fee

			ProductCatalog penFee = (ProductCatalog) entityManager.createNamedQuery("getPenaltyFee")
					.setParameter("licenseTypeId", (short) 9).getSingleResult();

			LineItem penaltyFeeLi = new LineItem();
			penaltyFeeLi.setId(0);
			penaltyFeeLi.setProductCatalog(penFee);
			penaltyFeeLi.setOrders(orders);
			penaltyFeeLi.setItemFees(orders.getPenaltyAmount());
			lineItemList.add(penaltyFeeLi);

			calculatedFees = calculatedFees + orders.getPenaltyAmount();
			totalRenewalFees = totalRenewalFees + orders.getPenaltyAmount();
			appLog.debug("totalRenewalFees: " + totalRenewalFees);
		}

		totalFees = totalRenewalFees;
		appLog.debug("total fees: " + totalFees);

		appLog.debug("lineItemSet size: " + lineItemList.size());
		orders.setLineItems(lineItemList);

		appLog.debug("exiting calculateFees()...");

	}

	public void beginCheckout() {
		
		
		
		appLog.debug("begin checkout for OTC.");

		String sc = "";

		try {
			appLog.debug("before orders is persisted...checking lineItems.");
			if (orders.getLineItems() == null || orders.getLineItems().isEmpty())
				appLog.debug("orders.getLineItems is NULL or EMPTY.");
			else {
				appLog.debug("orders.getLineItems are...");
				for (LineItem i : orders.getLineItems()) {

					appLog.debug("order id:   " + i.getOrders().getId());
					appLog.debug("prod lic:   " + i.getProductCatalog().getDescription());
					appLog.debug("item fees:  " + i.getItemFees());
				}
			}

			// Generate local order number.
			appLog.debug("generate order number.");
			long orderNumber = 0;

			Long idValue = (Long) entityManager.createQuery("Select MAX(o.id) from Orders o").getSingleResult();

			if (idValue != null) // added by bessem
				orderNumber = idValue.longValue();

			appLog.trace("Last order number: " + orderNumber);
			orderNumber += 1;
			appLog.debug("orderNumber = " + orderNumber);

			orders.setId(orderNumber);
			appLog.debug("orderNum " + orderNumber);

			// Make a secure key
			Random r = new Random();
			while (sc.length() < 20) {
				int useNum = r.nextInt(2);
				if (useNum == 1) {
					char c = (char) (65 + r.nextInt(25));
					sc = sc.concat(String.valueOf(c));
				} else {
					sc = sc.concat(String.valueOf(r.nextInt(10)));
				}
			}
			orders.setCheckoutKey(sc);
			// entityManager.merge(orders);

			entityManager.persist(orders);
			entityManager.flush();

			appLog.debug("after orders is persisted...checking lineItems.");
			if (orders.getLineItems() == null || orders.getLineItems().isEmpty())
				appLog.debug("orders.getLineItems is NULL or EMPTY.");
			else {
				appLog.debug("orders.getLineItems are...");
				for (LineItem i : orders.getLineItems()) {

					appLog.debug("order id:   " + i.getOrders().getId());
					appLog.debug("prod lic:   " + i.getProductCatalog().getDescription());
					appLog.debug("item fees:  " + i.getItemFees());
				}

			}

			appLog.debug("beginCheckout save success for order #" + orders.getId());
			appLog.debug("done with save");

		} catch (InvalidStateException ex) {
			for (InvalidValue invalidValue : ex.getInvalidValues()) {
				appLog.info("Instance of bean class: " + invalidValue.getBeanClass().getSimpleName()
						+ " has an invalid property: " + invalidValue.getPropertyName() + " with message: "
						+ invalidValue.getMessage());
			}
			ex.printStackTrace();
			return;
		} catch (Exception e) {
			addCheckoutErrorMessage(e);
			appLog.error("beginCheckout save failure for order #" + orders.getId(), e);
			e.printStackTrace();
			return;
		}

		try {

			String returnUrl = properties.getProperty("dhsotc/notifyPayment");

			appLog.debug("returnUrl = " + returnUrl);

			// this needs to be changed to get HSA-VM--modified
			// dhsotc-service.xml file

			String tpeMerchantId = properties.getProperty("dhsotc/tpeMerchant");

			appLog.debug("tpeMerchantId = " + tpeMerchantId);

			String tpeService = properties.getProperty("dhsotc/tpeService");

			// TPE and the scs use a non decimal money system which requires all
			// amounts to be
			// multiplies by 100

			GAOTransactionMarshal gtm = new GAOTransactionMarshal("GAOPaymentTransaction.xsd");
			GAOTransactionType g = new GAOTransactionType();
			g.setMerchantName("Arizona Department of Health Services");
			// this needs to be changed to get HSA-VM
			g.setMerchantNumber(tpeMerchantId);
			g.setService(tpeService);
			g.setOrderNumber(String.valueOf(orders.getId()));
			g.setReturnURL(returnUrl);
			g.setSummaryDescription("A payment for the " + orders.getLicenseType().getDescription()
					+ (orders.getLicenseType().getId() == 9 ? "" : " license renewal ")
					+ (orders.getLicenseType().getId() < 10 ? " Facility " : " Invoice ") + " #"
					+ (orders.getLicenseType().getId() < 10 ? orders.getFacilityId() : orders.getInvoiceNumber())
					+ " has been received. ");
			g.setTotalAmount(multiplyByHundred(orders.getActualAmountPaid()));
			g.setGaoBatchAgency("HSA");
			g.setGaoDocNoAgency("HSA");

			// consider _TEST-VM This ensures that the test is being used rather
			// than real payment processing.
			if (tpeMerchantId.indexOf("_TEST-VM") != -1)
				g.setTest("test");

			GAOTransactionLineItemList list = new GAOTransactionLineItemList();
			g.setLineItemList(list);

			// add the fees
			for (LineItem li : lineItemList) {

				GAOLineItemType item = new GAOLineItemType();
				item.setAmount(multiplyByHundred(li.getItemFees()));
				item.setGaoAgency(li.getProductCatalog().getBatchAgency());
				item.setQuantity(BigInteger.ONE);
				// this should be short description so that it correctly
				// correlates with payment service expectation.
				item.setGaoDescription(li.getProductCatalog().getShortDesc());
				item.setGaoProductCode(li.getProductCatalog().getProductCode());
				// item.setGaoIndex(li.getProductCatalog().getIndex1());
				// item.setGaoCompObject(li.getProductCatalog().getCobj());
				item.setRevenueSource(li.getProductCatalog().getRevenueSource());
				item.setFunction(li.getProductCatalog().getFunction());
				item.setAccountingTemplate(li.getProductCatalog().getAccountingTemplate());

				// added by bessem
				if (item.getAccountingTemplate() == null || "NULL".equalsIgnoreCase(item.getAccountingTemplate())) {
					item.setAccountingTemplate("");
				}

				// added by bessem
				if (item.getGaoDescription().contains("&")) {
					item.setGaoDescription(item.getGaoDescription().replace("&", "AND"));
				}

				if (g.getSummaryDescription().contains("#")) {
					g.setSummaryDescription(g.getSummaryDescription().replace("#", "NUMBER "));
				}

				item.setDepartmentRevenueSource(li.getProductCatalog().getDepartmentRevenueSource());
				list.getLineItem().add(item);
				appLog.debug("added fee " + li.getProductCatalog().getDescription());
			}

			// //this better validation method is dying on prodtest
			// //getting rid of it for now
			// // //
			// //JAXBElement<GAOTransactionType> jg = (new
			// ObjectFactory()).createTransaction(g);
			// //gtm.marshal(jg);
			String xml = gtm.marshal(g);

			appLog.debug("sending\n\n" + xml);

			HTTPPostHelper hTTPPostHelper = new HTTPPostHelper();
			String results = null;

			String postUrl = properties.getProperty("scs/chargePayment");

			appLog.debug("posting to " + postUrl);

			try {

				HTTPCapture capture = hTTPPostHelper.doSSLPost(postUrl, xml, "text/xml");

				results = capture.getHtml();

			} catch (Exception e) {

				e.printStackTrace();
				throw new Exception("HTTP post failed " + e.getMessage());
			}
			appLog.debug("results from post " + results);

			String url = "";
			String error = "";

			if (results.indexOf("&") > -1) {

				String[] arr = results.split("&");
				int i = 0;

				while (i < arr.length) {

					if (arr[i].startsWith("url=")) {
						url = arr[i].substring(4);
						appLog.debug("redirct url is " + url);
					} else if (arr[i].startsWith("error=")) {
						error = arr[i].substring(6);
						appLog.debug("error is " + error);
					}
					i++;
				}

				if (error.equals("0")) {

					appLog.debug("order taken to checkout " + orders.getId());

					// TODO redirection to the payement page
					jsfContextEmulator.getResponse().setRedirectTo(url);

					appLog.debug("finished");
				} else {

					appLog.debug("order not taken to checkout " + orders.getId() + " error returned: " + error);
					if (error.equals("1"))
						throw new Exception(
								"error of type 1 recieved which mean that the xml transmission could't be parsed.");
					else if (error.equals("2"))
						throw new Exception(
								"error of type 2 recieved which mean that the xml transmission was an invalid GAO transaction.");
					else if (error.equals("3"))
						throw new Exception("error of type 3 recieved which mean that an internal error has occured.");
					else
						throw new Exception("unknown error " + error);
				}
			} else {
				throw new Exception("invalid data returned " + results);
			}

		} catch (Exception e) {

			addCheckoutErrorMessage(e);
			appLog.error("beginCheckout post failure for order #" + orders.getId(), e);
			e.printStackTrace();
		}

	}

	private void addCheckoutErrorMessage(Exception e) {
		appLog.error("SCS server is unavailable or DHS checkout error", e);
		jsfContextEmulator.getResponse().addMessage(Severity.ERROR,
				"An unexpected error occured before checkout. Please contact customer service.");
	}

	public String logout() {
		appLog.debug("logout");
		// TODO check that
		// Conversation.instance().end();
		return "";
	}

	@Remove
	public void remove() {
	}

	/**
	 * @return the orders
	 */
	public Orders getOrders() {

		return orders;
	}

	/**
	 * @param orders
	 *            the orders to set
	 */
	public void setOrders(Orders orders) {
		this.orders = orders;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.nicusa.az.dhs.otc.action.RenewalManager#getFacilityId()
	 */
	public long getFacilityId() {
		return facilityId;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.nicusa.az.dhs.otc.action.RenewalManager#setFacilityId(long)
	 */
	public void setFacilityId(long facilityId) {
		this.facilityId = facilityId;
	}

	/**
	 * @return the userId
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * @param userId
	 *            the userId to set
	 */
	@Override
	public void setUserId(String userId) {
		this.userId = userId;
	}

	/**
	 * @return the numBedsKids
	 */
	public int getNumBedsKids() {
		return numBedsKids;
	}

	/**
	 * @param numBedsKids
	 *            the numBedsKids to set
	 */
	public void setNumBedsKids(int numBedsKids) {
		this.numBedsKids = numBedsKids;
	}

	/**
	 * @return the copyFees
	 */
	public Number getCopyFees() {
		if (copyFees == null) {
			copyFees = new Double(0.0);
		}
		return copyFees;
	}

	/**
	 * @param copyFees
	 *            the copyFees to set
	 */
	public void setCopyFees(Number copyFees) {
		if (copyFees instanceof Double) {
			this.copyFees = (Double) copyFees;
		} else {
			this.copyFees = new Double(copyFees.doubleValue());
		}
	}

	/**
	 * @return the licenseType
	 */
	public LicenseType getLicenseType() {
		return licenseType;
	}

	/**
	 * @param licenseType
	 *            the licenseType to set
	 */
	public void setLicenseType(LicenseType licenseType) {
		this.licenseType = licenseType;
	}

	/**
	 * @return the licenseTypeList
	 */
	@SuppressWarnings("unchecked")
	public List<LicenseType> getLicenseTypeList() {

		List<LicenseType> licenseTypes = null;

		licenseTypes = entityManager.createNamedQuery("getLicenseTypes").getResultList();

		return licenseTypes;

	}

	/**
	 * @return the amountpenalty
	 */
	public Number getAmountpenalty() {
		if (amountpenalty == null) {
			amountpenalty = new Double(0.0);
		}
		return amountpenalty;

	}

	/**
	 * @param amountpenalty
	 *            the amountpenalty to set
	 */
	public void setAmountpenalty(Number amountpenalty) {
		if (amountpenalty instanceof Double) {
			this.amountpenalty = (Double) amountpenalty;
		} else {
			this.amountpenalty = new Double(amountpenalty.doubleValue());
		}
	}

	/**
	 * @return the feesList
	 */
	public List<ProductCatalog> getFeesList() {
		return feesList;
	}

	/**
	 * @param feesList
	 *            the feesList to set
	 */
	public void setFeesList(List<ProductCatalog> feesList) {
		this.feesList = feesList;
	}

	/**
	 * @return the totalFees
	 */
	public Double getTotalFees() {
		return totalFees;
	}

	/**
	 * @param totalFees
	 *            the totalFees to set
	 */
	public void setTotalFees(Double totalFees) {
		this.totalFees = totalFees;
	}

	/**
	 * @return the totalBedFees
	 */
	public double getTotalBedFees() {
		return totalBedFees;
	}

	/**
	 * @param totalBedFees
	 *            the totalBedFees to set
	 */
	public void setTotalBedFees(double totalBedFees) {
		this.totalBedFees = totalBedFees;
	}

	private BigInteger multiplyByHundred(Double value) {
		BigDecimal hundred = new BigDecimal("100");
		BigDecimal amount = new BigDecimal(value.doubleValue()).setScale(2, BigDecimal.ROUND_HALF_UP);
		amount = amount.multiply(hundred).setScale(0);
		return amount.toBigInteger();
	}
}
