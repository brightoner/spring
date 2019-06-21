package kr.or.ddit.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Aspect
public class ProfilingAspect {
	
	private static final Logger logger = LoggerFactory.getLogger(ProfilingAspect.class);
	
	//application-aop-scan.xml 적용
	@Pointcut("execution(* kr.or.ddit..service.*.*(..))")
	public void dummy() {}
	
	//application-aop-scan.xml 적용
	@Around(" dummy()")
	public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
		//전
		long startTime = System.currentTimeMillis();
		logger.debug("Logging Aspect around method before :{}", startTime);
		
		//실행
		//business logic 실행
		logger.debug("method name :{}", joinPoint.getSignature().getName());
		
		Object[] methodArgs = joinPoint.getArgs();
		Object returnObj = joinPoint.proceed(methodArgs);
		
		//후
		long endTime = System.currentTimeMillis();
		logger.debug("Logging Aspect around method before :{}", endTime);
		
		//시간차
		logger.debug("endTime - startTime :{}",endTime - startTime);
		
		return returnObj;
	}
	
	

}
