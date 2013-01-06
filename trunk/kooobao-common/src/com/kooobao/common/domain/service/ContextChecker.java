package com.kooobao.common.domain.service;

import java.text.MessageFormat;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.apache.commons.lang.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ContextChecker implements MethodInterceptor {

	private Logger logger = LoggerFactory.getLogger(getClass());

	public Object invoke(MethodInvocation invocation) throws Throwable {
		if (invocation.getArguments().length == 0
				|| !(invocation.getArguments()[0] instanceof Context)) {
			if (logger.isDebugEnabled()) {
				logger.debug(MessageFormat.format(
						"{0}:No Context found, ignore.", invocation.getMethod()
								.getName()));
			}
			return invocation.proceed();
		}
		Context context = (Context) invocation.getArguments()[0];
		Validate.notEmpty(context.getOperatorId(),
				ErrorCode.COMMON_NO_OPERATOR.name());
		return invocation.proceed();
	}
}
