package com.dolphin.config;

import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

import com.netflix.hystrix.exception.HystrixTimeoutException;
import org.springframework.cloud.netflix.zuul.filters.route.FallbackProvider;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Logger;

@Component
public class ApiFallbackProvider implements FallbackProvider {

	 private Logger logger = Logger.getLogger(ApiFallbackProvider.class.toString());

	 	/**
	 	 * 该Provider应用的Route ID，例如：testservice，如果设置为 * ，那就对所有路由生效
	 	 */
	    @Override
	    public String getRoute() {
	        return "*";
	    }

	    /**
	     * fallbackResponse(String route, Throwable cause) : 快速回退失败/响应，即处理异常并返回对应输出/响应内容。route：发生异常的RouteID，cause：触发快速回退/失败的异常/错误
	     * ClientHttpResponse: Spring提供的HttpResponse接口。可以通过实现该接口自定义Http status、body、header
	     */
	    @Override
	    public ClientHttpResponse fallbackResponse(String route, Throwable cause) {
	        logger.warning(String.format("route:%s,exceptionType:%s,stackTrace:%s", route, cause.getClass().getName(), cause.getStackTrace()));
	        String message = "";
	        if (cause instanceof HystrixTimeoutException) {
	            message = "Timeout";
	        } else {
	            message = "Service exception";
	        }
	        return fallbackResponse(message);
	    }
	    
	    
	   
	    public ClientHttpResponse fallbackResponse(String message) {

	        return new ClientHttpResponse() {
	            @Override
	            public HttpStatus getStatusCode() throws IOException {
	                return HttpStatus.OK;
	            }

	            @Override
	            public int getRawStatusCode() throws IOException {
	                return 200;
	            }

	            @Override
	            public String getStatusText() throws IOException {
	                return "OK";
	            }

	            @Override
	            public void close() {

	            }

	            @Override
	            public InputStream getBody() throws IOException {
	                String bodyText = String.format("{\"code\": 888,\"message\": \"Service unavailable is  %s\"}", message);
	                return new ByteArrayInputStream(bodyText.getBytes());
	            }

	            @Override
	            public HttpHeaders getHeaders() {
	                HttpHeaders headers = new HttpHeaders();
	                headers.setContentType(MediaType.APPLICATION_JSON);
	                return headers;
	            }
	        };

	    }


}
