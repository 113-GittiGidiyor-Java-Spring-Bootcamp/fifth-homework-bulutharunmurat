package dev.patika.config;


import dev.patika.util.ClientRequestInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class CustomInterceptor extends HandlerInterceptorAdapter{

    @Autowired
    ClientRequestInfo clientRequestInfo;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        clientRequestInfo.setClientUrl(request.getRequestURI());
        clientRequestInfo.setClientIpAddress(request.getRemoteAddr());
        clientRequestInfo.setSessionActivityId(request.getSession().getId());

        //return true;
        return super.preHandle(request, response, handler);
    }
}
