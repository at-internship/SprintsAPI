package com.sprints.interceptors;

import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.core.DefaultParameterNameDiscoverer;
import org.springframework.core.MethodParameter;
import org.springframework.core.ParameterNameDiscoverer;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.sprints.exception.UndeclaredRequestParamException;

import org.springframework.util.StringUtils;



public class UndeclaredParamsHandlerInterceptor extends HandlerInterceptorAdapter{
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
							 Object handler) throws Exception {
		if (handler instanceof HandlerMethod) {
			HandlerMethod handlerMethod = (HandlerMethod) handler;
			if (handlerMethod.getMethodAnnotation(DisallowUndeclaredRequestParams.class) != null) {
				checkParams(request, getDeclaredRequestParams(handlerMethod));
			}
		}
		return true;
	}

	private void checkParams(HttpServletRequest request, Set<String> allowedParams) {
		request.getParameterMap().entrySet().forEach(entry -> {
			String param = entry.getKey();
			if (!allowedParams.contains(param)) {
				throw new UndeclaredRequestParamException();
			}
		});
	}

	private Set<String> getDeclaredRequestParams(HandlerMethod handlerMethod) {
		Set<String> declaredRequestParams = new HashSet<>();
		MethodParameter[] methodParameters = handlerMethod.getMethodParameters();
		ParameterNameDiscoverer parameterNameDiscoverer = new DefaultParameterNameDiscoverer();
	
		for (MethodParameter methodParameter : methodParameters) {
			if (methodParameter.hasParameterAnnotation(RequestParam.class)) {
				RequestParam requestParam = methodParameter.getParameterAnnotation(RequestParam.class);
				if (StringUtils.hasText(requestParam.value())) {
					declaredRequestParams.add(requestParam.value());
				} else {
					methodParameter.initParameterNameDiscovery(parameterNameDiscoverer);
					declaredRequestParams.add(methodParameter.getParameterName());
				}
			}
		}
		return declaredRequestParams;
	}
}