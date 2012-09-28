package org.aquamethods.fashbook.dao.interceptor;


import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class DaoInterceptor {

	private static final Logger logger = LoggerFactory
			.getLogger(DaoInterceptor.class);
	
	@Pointcut("execution(* org.aquamethods.fashbook.dao.jpa.PersonServiceDaoImpl.getTagPerson(..))")
    public void aroundGetTagPerson() { }
	
	@Around("aroundGetTagPerson()")
	public Object aroundGetTagPersonAdvice(ProceedingJoinPoint pjp) throws Throwable{
		long start = System.currentTimeMillis();
		//logger.debug("Going to call the method.");
        Object output = pjp.proceed();
        //logger.debug("Method execution completed.");
        long elapsedTime = System.currentTimeMillis() - start;
        logger.info("Method execution time:: " + elapsedTime + " milliseconds.");
        return output;
	}
	
}
