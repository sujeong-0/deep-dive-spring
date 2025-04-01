package com.deepdive.studyspring.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * 이 클래스는 로그 출력을 위한 AOP를 담당합니다.
 */
@Aspect // AOP를 제공하는 클래스임을 명시 -> AOP 프레임워크에서 처리되어야함을 알려줌, 스캔대상이 될 수 있음(실제 되는 것과는 다름)
@Component // 왜 AOP랑 component를 따로 지정할까? ->@Aspect만으로는 스캔대상으로 만들어줌
@Slf4j
public class AspectLog {

	@Pointcut("execution(* com.deepdive.studyspring..*(..))") // AOP를 적용할 범위(패키지, 클래스, 메소드)
	private void doExecute() {
	} // 여기는 사용하지 않음 -> 왜?...

	@Around("doExecute()") // point cut에서 정의한 메서드 전 후로 호출
	public Object writeLog(ProceedingJoinPoint joinPoint) throws Throwable {
		String methodName = joinPoint.getSignature().getName(); //  실행 대상의 메서드 이름
		log.info("Aspect - start :::: method name : {}" , methodName );
		
		log.info("Aspect - executing :::: getArgs() : {}" , joinPoint.getArgs()); // 대상의 arguments
		log.info("Aspect - executing :::: getKind() : {}" , joinPoint.getKind());
		log.info("Aspect - executing :::: getThis() : {}" , joinPoint.getThis());
		log.info("Aspect - executing :::: getTarget() : {}" , joinPoint.getTarget());
		Object obj = joinPoint.proceed(); // 실행 대상 메서드를 직접 실행하고, 그 결과를 Object로 리턴
		log.info("Aspect - executing :::: getSourceLocation() : {}" , joinPoint.getSourceLocation());
		log.info("Aspect - executing :::: getStaticPart() : {}" , joinPoint.getStaticPart()); // pointCut에서의 내용? (어떻게 잡았는지?)
		log.info("Aspect - executing :::: getSignature() : {}" , joinPoint.getSignature()); // 대상의 이름 전체 (패키지 명까지 포함)

		log.info("Aspect - end :::: obj : {}" , obj.toString() );
		return obj;
	}
}
