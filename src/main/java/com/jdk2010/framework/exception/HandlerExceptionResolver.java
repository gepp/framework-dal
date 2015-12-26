package com.jdk2010.framework.exception;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

public class HandlerExceptionResolver extends SimpleMappingExceptionResolver {
    
    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    protected ModelAndView doResolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
            Exception ex) {
        String viewName = determineViewName(ex, request);
        if (viewName != null) {
            Integer statusCode = determineStatusCode(request, viewName);
            if (statusCode != null) {
                applyStatusCodeIfPossible(request, response, statusCode);
            }
            request.setAttribute("exceptionMessage", ex.getMessage());
            logger.error("error",ex);
            return getModelAndView(viewName, ex, request);
        } else {
            return null;
        }
    }

}
