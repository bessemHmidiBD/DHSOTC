package com.adoa.azportal.application.util;

import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import javax.persistence.EntityManager;

@Interceptor
@Transactionnal  //binding the interceptor 

public class TransactionalInterceptor {
	
	@Inject EntityManager entityManager;
	
    @AroundInvoke
    public Object logMethodEntry(InvocationContext ctx) throws Exception {
       
    	Object result;
    	
    	try {
    		entityManager.getTransaction().begin();
        	result=ctx.proceed();
        	entityManager.getTransaction().commit();
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			throw(e);
		}
    
    	
        return result;
    }
}