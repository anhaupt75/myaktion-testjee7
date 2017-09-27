package de.dpunkt.myaktion.util;

import javax.annotation.Resource;
import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;
import javax.transaction.UserTransaction;

public class TransactionInterceptor {

	@Resource 
	private UserTransaction userTransaction;
	
	@AroundInvoke
	public Object doTransaction(InvocationContext ctx) throws Exception {
		try {
			userTransaction.begin();
			Object ret = ctx.proceed();
			userTransaction.commit();
			return ret;
		}
		catch (Exception ex) {
			try {
				userTransaction.rollback();
				System.err.println("Transaction wurde zurückgerollt");
			}
			catch (Exception ex2)
			{
				System.err.println("Fehler beim Zurückrollen von Transaction");
			}
			throw ex;
		}
	}
}
