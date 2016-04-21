package com.adoa.azportal.application.util;

import javax.enterprise.inject.Produces;
import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;


public class EntityManagerProducer {
	
	@Produces
	@Singleton
	public EntityManager createEntityManager(){
		
		return Persistence.createEntityManagerFactory("dhsotc").createEntityManager();
		
	}
	

}
