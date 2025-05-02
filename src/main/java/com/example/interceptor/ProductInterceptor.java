package com.example.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;

public class ProductInterceptor implements Interceptor {

	private static final long serialVersionUID = 1L;
	
	private static final Logger logger = LoggerFactory.getLogger(ProductInterceptor.class);

	@Override
    public void destroy() {
    }

    @Override
    public void init() {
    }

    @Override
    public String intercept(ActionInvocation invocation) throws Exception {  	
		logger.info("Entering ProductInterceptor");
        String result = invocation.invoke();
        logger.info("Exiting ProductInterceptor");
        return result;
    }
}