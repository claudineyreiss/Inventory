package com.siersolutions.util.jpa;

import java.io.Serializable;

import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

@Interceptor
@Transactional
public class TransactionInterceptor implements Serializable {

	private static final long serialVersionUID = 1L;

	private @Inject EntityManager em;
	
	@AroundInvoke
	public Object invoke(InvocationContext context) throws Exception {
		EntityTransaction trx = em.getTransaction();
		boolean creater = false;

		try {
			if (!trx.isActive()) {
				// trick to do the roll back with you already passed
				// (otherwise, in furter commit, would confirm operation without a transaction)
				trx.begin();
				trx.rollback();
				
				// transaction begins
				trx.begin();
				
				creater = true;
			}

			return context.proceed();
		} catch (Exception e) {
			if (trx != null && creater) {
				trx.rollback();
			}

			throw e;
		} finally {
			if (trx != null && trx.isActive() && creater) {
				trx.commit();
			}
		}
	}
	
}