package com.kooobao.common.spring;

import org.aspectj.lang.ProceedingJoinPoint;
import org.slf4j.LoggerFactory;

public class ExceptionAdvice {

	public Object handleException(ProceedingJoinPoint pjp) {
		try {
			return pjp.proceed();
		} catch (Throwable e) {
			LoggerFactory.getLogger(pjp.getTarget().getClass()).error(
					"Exception in service", e);
			return null;
		}
	}
}
