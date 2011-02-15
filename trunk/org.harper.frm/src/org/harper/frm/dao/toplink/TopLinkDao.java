package org.harper.frm.dao.toplink;

import oracle.toplink.expressions.ExpressionBuilder;
import oracle.toplink.sessions.Session;

import org.harper.frm.service.TransactionContext;

public class TopLinkDao {

	public <T> T readObject(Class<T> clazz, int oid) {
		return (T) TransactionContext.getSession().readObject(clazz,
				new ExpressionBuilder().get("oid").equal(oid));
	}

	public Session getSession() {
		Session uow = (Session) TransactionContext.getSession();
		return uow;
	}
}
