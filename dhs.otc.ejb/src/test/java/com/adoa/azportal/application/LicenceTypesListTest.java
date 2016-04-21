package com.adoa.azportal.application;

import static org.junit.Assert.assertEquals;

import javax.inject.Inject;
import javax.transaction.Transactional;

import org.jglue.cdiunit.AdditionalClasses;
import org.jglue.cdiunit.AdditionalClasspaths;
import org.jglue.cdiunit.CdiRunner;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.picketlink.Identity;
import org.picketlink.internal.AbstractIdentity;

import com.adoa.azportal.application.impl.RenewalAction;
import com.adoa.azportal.application.interfaces.RenewalManager;
import com.adoa.azportal.application.util.EntityManagerProducer;
import com.adoa.azportal.application.util.TransactionalInterceptor;
import com.adoa.azportal.application.util.Transactionnal;
import com.adoa.azportal.util.LoggerProducer;

@RunWith(CdiRunner.class)
@AdditionalClasses({ EntityManagerProducer.class, TransactionalInterceptor.class, RenewalManager.class,RenewalAction.class,LoggerProducer.class })
@AdditionalClasspaths({Identity.class,AbstractIdentity.class})
public class LicenceTypesListTest {

	@Inject
	RenewalManager renewalManager;

	@Test
	@Transactionnal
	public void testLicenceTypesList() {

		assertEquals(9, renewalManager.getLicenseTypeList().size());

	}

}
