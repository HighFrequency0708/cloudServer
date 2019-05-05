package com.dolphin.config;

import org.springframework.cloud.netflix.ribbon.support.RibbonCommandContext;
import org.springframework.cloud.netflix.zuul.filters.ProxyRequestHelper;
import org.springframework.http.client.ClientHttpResponse;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

public class RibbonRoutingFilter extends ZuulFilter{
	
	protected ProxyRequestHelper helper;
	@Override
	public boolean shouldFilter() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Object run() {
		// TODO Auto-generated method stub
		RequestContext context = RequestContext.getCurrentContext();
		this.helper.addIgnoredHeaders();
		try {
			RibbonCommandContext commandContext = buildCommandContext(context);
			ClientHttpResponse response = forward(commandContext);
			setResponse(response);
			return response;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}

	protected void setResponse(ClientHttpResponse response) {
		// TODO Auto-generated method stub
		
	}

	protected ClientHttpResponse forward(RibbonCommandContext commandContext) {
		// TODO Auto-generated method stub
		return null;
	}

	protected RibbonCommandContext buildCommandContext(RequestContext context) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String filterType() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int filterOrder() {
		// TODO Auto-generated method stub
		return 0;
	}

}
