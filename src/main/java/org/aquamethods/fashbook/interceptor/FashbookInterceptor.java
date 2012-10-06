package org.aquamethods.fashbook.interceptor;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class FashbookInterceptor {

	private static final Logger logger = LoggerFactory
			.getLogger(FashbookInterceptor.class);

	@Pointcut("execution(* org.aquamethods.fashbook.dao.jpa.PersonServiceDaoImpl.*(..))")
	public void aroundGetTagPerson() {
	}

	@Pointcut("execution(* org.aquamethods.fashbook.web.controller.WebController.*(..))")
	public void beforeMethod() {
	}
	
	@Around("aroundGetTagPerson()")
	public Object aroundGetTagPersonAdvice(ProceedingJoinPoint pjp)
			throws Throwable {
		long start = System.currentTimeMillis();
		// logger.debug("Going to call the method.");
		Object output = pjp.proceed();
		// logger.debug("Method execution completed.");
		long elapsedTime = System.currentTimeMillis() - start;
		logger.debug("Method "+ pjp.getSignature().getName() +" execution time:: " + elapsedTime + " milliseconds.");
		return output;
	}

	@Before("beforeMethod()")
	public void BeforeMethodAdvice(JoinPoint jp)
			throws Throwable {

		logger.debug("Entering :: "+ jp.getSignature().getName());
		
	}

}
