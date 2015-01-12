package com.yj.carpg.controller;

import java.util.Map;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class SessionInterceptor extends AbstractInterceptor{

	private static final long serialVersionUID = 1L;

	@Override
	public String intercept(ActionInvocation arg0) throws Exception {
		Map<String,Object> session=ActionContext.getContext().getSession();
		String path=ServletActionContext.getRequest().getRequestURI();
		if("/sysCarpg/carinfo!shoppingCar.action".equals(path)){
			if(session.get("user")==null){
				return "redirect";
			}
		}
		return arg0.invoke();
	}

}
