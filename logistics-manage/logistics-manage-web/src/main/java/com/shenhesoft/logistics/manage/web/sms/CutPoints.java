// ******************************************************************************
// Copyright (C) 2017 ShenHeSoft, All Rights Reserved.
// ******************************************************************************
package com.shenhesoft.logistics.manage.web.sms;

import java.util.Arrays;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

/**
 * @description 短信计划的切面
 *
 * @author LiangLin
 *
 * @date 2017年12月15日
 */

@Aspect
public class CutPoints {

	/**
	 * 
	 * @description 定义一个切入点  例子
	 * @author liangLin
	 * @date 2017年12月15日
	 * @param
	 * @return
	 */
	@Pointcut("execution(* com.shenhesoft.service.impl.xxx.*(..))")
	private void anyMethod() {
	}
	
	
	@Pointcut("execution(* com.shenhesoft.logistics.manage.web.sms.Test.sendMsg(int,String))")
	private void SendMethod(int num,String code) {
	}

	
	/**
	 * 
	 * @description 前置通知 
	 * @author liangLin
	 * @date 2017年12月15日
	 * @param 
	 * @return
	 */
	@Before("anyMethod() && args(name)")
	public void doAccessCheck(String name) {
		System.out.println(name);
		System.out.println("前置通知");
	}

	
	/**
	 * 
	 * @description  后置通知
	 * @author liangLin
	 * @date 2017年12月15日
	 * @param 
	 * @return
	 */
	@AfterReturning("anyMethod()")
	public void doAfter() {
		System.out.println("后置通知");
	}

	/**
	 * 
	 * @description  最终通知
	 * @author liangLin
	 * @date 2017年12月15日
	 * @param 
	 * @return
	 */
	@After("anyMethod()")
	public void after() {
		System.out.println("最终通知");
	}

	
	/**
	 * 
	 * @description 例外通知
	 * @author liangLin
	 * @date 2017年12月15日
	 * @param 
	 * @return
	 */
	@AfterThrowing("anyMethod()")
	public void doAfterThrow() {
		System.out.println("例外通知");
	}

	/**
	 * 
	 * @description 环绕通知
	 * @author liangLin
	 * @date 2017年12月15日
	 * @param 
	 * @return
	 */
	@Around("SendMethod(num,code)")
	public Object doBasicProfiling(int num,String code,ProceedingJoinPoint pjp) throws Throwable {
        String methodName = pjp.getSignature().getName();  
        Object args = Arrays.asList(pjp.getArgs()); 
        
        System.out.println("methodName"  + methodName);
        System.out.println("args"  + args);
        System.out.println("num :"+num +" .code: "+code);
        
		System.out.println("进入环绕通知");
		Object object = pjp.proceed();// 执行该方法
		System.out.println("退出方法");
		return object;
	}
}
