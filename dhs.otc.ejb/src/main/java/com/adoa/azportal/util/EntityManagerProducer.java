package com.adoa.azportal.util;

import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class EntityManagerProducer {
	
	@PersistenceContext(unitName="dhsotc")
	EntityManager entityManager;
	
	@Produces
	EntityManager produceEntityManager(){
		return entityManager;
		
	}

}
