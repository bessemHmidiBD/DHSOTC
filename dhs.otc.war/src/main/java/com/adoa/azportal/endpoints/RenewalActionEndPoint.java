package com.adoa.azportal.endpoints;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.adoa.azportal.application.interfaces.RenewalManager;
import com.adoa.azportal.domain.LicenseType;
import com.adoa.azportal.domain.LineItem;
import com.adoa.azportal.domain.Orders;
import com.adoa.azportal.model.FeeSummaryOutput;
import com.adoa.azportal.model.LineItemModel;
import com.adoa.azportal.model.RenewalInputs;
import com.adoa.azportal.util.JSFContextEmulator;
import com.adoa.azportal.util.Response;

@Path("/RenewalAction")
@SessionScoped
public class RenewalActionEndPoint implements Serializable{

	@Inject
	RenewalManager renewalManager;

	
	@Inject JSFContextEmulator jsfContextEmulator;
	
	
	@GET
	@Path("/LicenseTypeList")
	@Produces(MediaType.APPLICATION_JSON)
	public List<LicenseType> getLicenseTypeList() {
		return renewalManager.getLicenseTypeList();
	}
	
	
	@POST
	@Path("/SubmitForm")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response checkForm(RenewalInputs inputs) {
		
		
		
		
		Orders orders=renewalManager.getOrders();
		
		orders.setPenaltyAmount(inputs.getAmountPenality());
		orders.setFacilityId(inputs.getFacilityId() );
		orders.setLicenseType(inputs.getLicenseType());
		orders.setNumberBedsKids(inputs.getNumberOfBeds());
		
		orders.setFlagLateFee(inputs.isLateFee());
		orders.setFlagUseEmpowerPack(inputs.isEmpowerPack());
		
		orders.setInvoiceNumber(inputs.getInvoiceNumber()==null?null:String.valueOf(inputs.getInvoiceNumber()));
		
		if(inputs.getCopyFees()!=null){
		renewalManager.setCopyFees(inputs.getCopyFees());
		}
		
		renewalManager.setUserId(inputs.getUserId());
//		renewalManager.setFacilityId(inputs.getFacilityId());
//		renewalManager.setLicenseType(inputs.getLicenseType());
		
		
		String result=renewalManager.checkForm();
		
		jsfContextEmulator.getResponse().setData(result);
		return jsfContextEmulator.getResponse(); 
	}
	
	
	@POST
	@Path("/beginCheckout")
	@Produces(MediaType.APPLICATION_JSON)
	public Response beginCheckout() {
		
	renewalManager.beginCheckout();
	
	
	return jsfContextEmulator.getResponse();
	
	}
	
	
	@GET
	@Path("/feeSummary")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getFeeSummary() {
		
	FeeSummaryOutput feeSummaryOutput=new FeeSummaryOutput();
	
	feeSummaryOutput.setInvoiceNumber(renewalManager.getOrders().getInvoiceNumber());
	feeSummaryOutput.setLicenseType(renewalManager.getLicenseType());
	feeSummaryOutput.setTotalFees(renewalManager.getTotalFees());
	
	List<LineItemModel> lineItemModels=new ArrayList<>();
	
	for(LineItem item :renewalManager.getOrders().getLineItems()){
		
		LineItemModel itemModel=new LineItemModel();
		itemModel.setProductCatalogDescription(item.getProductCatalog().getDescription());
		itemModel.setFees(item.getItemFees());
		
		lineItemModels.add(itemModel);
	}
	
	
	feeSummaryOutput.setLineItems(lineItemModels);
	
	Response response=jsfContextEmulator.getResponse();
	
	response.setData(feeSummaryOutput);
	
	renewalManager.remove();
	
	
	return response;
	
	}
	
}
