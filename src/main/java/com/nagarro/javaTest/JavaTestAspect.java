package com.nagarro.javaTest;

import java.util.Arrays;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nagarro.javaTest.helper.GlobalApiResponseEntity;

@Aspect
@Configuration
public class JavaTestAspect {

	private static final Logger logger = LoggerFactory.getLogger(JavaTestAspect.class);

	// logger for controllers
	@SuppressWarnings("unchecked")
	@Around("execution(org.springframework.http.ResponseEntity<com.nagarro.javaTest.helper.GlobalApiResponseEntity> *(..))"
			+ " && !within(com.nagarro.javaTest.helper.ControllerExceptionHandler)")
	public ResponseEntity<GlobalApiResponseEntity> printLog(ProceedingJoinPoint joinPoint) throws Throwable {
		Long entryTime = System.currentTimeMillis();
		ResponseEntity<GlobalApiResponseEntity> response = null;
		var line="********************************************************************************************";
		logger.info(line);
		var entryLogger = new StringBuilder(
				"Entering into Controller { " + joinPoint.getStaticPart().getSignature() + " }");
		RequestEntity<?> request = null;
		Object[] args = joinPoint.getArgs();
		try {

			request = (RequestEntity<Object>) args[0];
			var mapper = new ObjectMapper();
			var requestBody = mapper.writeValueAsString(request.getBody());
			entryLogger.append(". Request body : " + requestBody);

			logger.info("Log this: {}",entryLogger);

			response = (ResponseEntity<GlobalApiResponseEntity>) joinPoint.proceed(args);

			logger.info("Exiting from Controller {} " , joinPoint.getStaticPart().getSignature());
			logger.info(". Service took {} ms.",(System.currentTimeMillis() - entryTime) );
			logger.info(line);
		} catch (ClassCastException e) {
			entryLogger.append(". Request Parameters : ");
			Arrays.stream(args).forEach(arg -> entryLogger.append(arg).append(", "));
		} catch (JsonProcessingException e) {
			entryLogger.append(". Request body : " + request.getBody().toString());
		} catch (Exception e) {
			logger.info("Exiting from Controller {} " , joinPoint.getStaticPart().getSignature());
			logger.info(" with Exception. Total time took : {} ms." , (System.currentTimeMillis() - entryTime) );
			logger.info(line);
			throw e;
		}
		return response;
	}

	@SuppressWarnings("all")
	@Around("execution( *com.nagarro.javaTest.helper.ControllerExceptionHandler.*(..))")
	public ResponseEntity<GlobalApiResponseEntity> getHome(ProceedingJoinPoint pjp) {
		try {
			logger.error("---------------------ExceptionHandler---------------------");
			Exception th = (Exception) pjp.getArgs()[0];
			logger.error("Exception occured : " + Utils.getStackTrace(th));
			ResponseEntity<GlobalApiResponseEntity> response = null;
			response = (ResponseEntity<GlobalApiResponseEntity>) pjp.proceed(pjp.getArgs());
			logger.info("---------------------End ExceptionHandler---------------------");
			return response;
		} catch (Throwable th) {
			logger.error("Exception Occurred while processing Exception Auditin. Cause : " + Utils.getStackTrace(th));
			return null;
		}
	}

}
