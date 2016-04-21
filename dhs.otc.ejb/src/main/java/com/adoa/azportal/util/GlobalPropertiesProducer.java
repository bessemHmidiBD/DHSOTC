package com.adoa.azportal.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.Properties;

import javax.enterprise.inject.Produces;
import javax.inject.Singleton;

public class GlobalPropertiesProducer {

	@Produces
	@Singleton
	@GlobalParams
	public Properties getProperties() {

		Properties properties = new Properties();

		try {

			File globalPropertiesFile = new File("Globals.properties");
			
			if (!globalPropertiesFile.exists()) {

				
				
				properties.setProperty("dhsotc/notifyPayment", "https://az.devaz.gov/app/dhsotc/checkoutReceive.xhtml");
				properties.setProperty("dhsotc/tpeMerchant", "HSA_OTC_TEST");
				properties.setProperty("dhsotc/tpeService", "HSA_OTC_TEST");
				properties.setProperty("scs/chargePayment", "https://prodtest.az.gov/app/pmtpages/chargePayment.serv");
				properties.setProperty("static/root", "https://az.devaz.gov");

				properties.setProperty("app/root", "https://az.devaz.gov");
				properties.setProperty("dhsotc/home", "https://az.devaz.gov/app/dhsotc/receipt.xhtml");
				properties.setProperty("wsdl/root", "http://localhost:8080/sas_user-sas_user/AuthenticateService");

				FileOutputStream fos=new FileOutputStream(globalPropertiesFile);
				properties.store(fos,
						"global properties file generated at:" + new Date());
				
				fos.flush();
				fos.close();

			}

			FileInputStream fis=new FileInputStream(globalPropertiesFile);
			properties.load(fis);

			fis.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return properties;

	}

}
