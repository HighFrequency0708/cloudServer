package com.dolphin;

import javax.servlet.http.HttpServletRequest;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

public class AccessFilterTtest1 extends ZuulFilter{
    
	/*过滤器具体逻辑代码*/
    @Override
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        //log.info("send {} request tpo {}",request.getMethod(),request.getRequestURL().toString());
        System.out.println("send {"+request.getMethod()+"} request tpo {"+request.getRequestURL().toString()+"}");
        Object accessToken = request.getParameter("accessToken");
        if (accessToken == null){
            ctx.setSendZuulResponse(false);  /*设置不进行路由*/
            ctx.setResponseStatusCode(401); /*设置返回错误码*/
            return null;
        }
        System.out.println("access token ok");
        return null;
    }
    /*过滤类型，决定在请求的哪个阶段执行，pre表示被路由之前执行*/
    /* pre:路由前 ； routing ：路由请求时 ； post ：在routing 和 error 之后 ； error：处理请求时发生错误时 ；*/
    @Override
    public String filterType() {
        return "pre";
    }

    /*过滤器的执行顺序，根据返回值依次执行*/
    @Override
    public int filterOrder() {
        return 0;
    }

    /*判断过滤器是否需要执行*/
    @Override
    public boolean shouldFilter() {
        return true;
    }


}