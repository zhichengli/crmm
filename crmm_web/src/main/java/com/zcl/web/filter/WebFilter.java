package com.zcl.web.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class WebFilter extends ZuulFilter {
    @Override
    public String filterType() {
        //过滤类型 pre post
        return "pre";
    }

    @Override
    public int filterOrder() {
        //优先级数字越小越先执行
        return 0;
    }

    /**
     * 当前过滤器是否开启，true 表示开启
     * @return
     */
    @Override
    public boolean shouldFilter() {
        return true;
    }

    /**
     * 过滤器内执行的操作 return 任何object的值都表示继续执行
     * setsendzullResponse(false) 表示不再执行
     * @return
     * @throws ZuulException
     */
    @Override
    public Object run() throws ZuulException {
        System.out.println("经过过滤器了");
        //先得到request上下文
        RequestContext currentContext = RequestContext.getCurrentContext();
        HttpServletRequest request = currentContext.getRequest();
        String header = request.getHeader("Authorization");
        if(!StringUtils.isEmpty(header)){
            //把头信息继续往下传
            currentContext.addZuulRequestHeader("Authorization",header);
        }
        return null;
    }
}
