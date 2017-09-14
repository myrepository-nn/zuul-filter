package com.nishant.zuul.apigateway;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

public class RouteFilter extends ZuulFilter {

	@Override
	public boolean shouldFilter() {
		return true;
	}

	@Override
	public Object run() {
		RequestContext context=RequestContext.getCurrentContext();
		HttpServletRequest req=context.getRequest();
		if(req.getMethod().equalsIgnoreCase("post")) {
			try {
				System.out.println("............route filter executed........."+req.getRequestURI()+".."+IOUtils.toString(req.getReader()));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if(req.getMethod().equalsIgnoreCase("get")) {
			System.out.println("............route filter executed........."+req.getRequestURI());
		}
		return null;
	}

	@Override
	public String filterType() {
		return "route";
	}

	@Override
	public int filterOrder() {
		return 3;
	}

}
