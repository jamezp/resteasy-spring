package org.jboss.resteasy.springmvc;

import java.util.Map;

import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.jboss.resteasy.core.ResteasyContext;
import org.springframework.web.servlet.DispatcherServlet;

public class ResteasySpringDispatcherServlet extends DispatcherServlet {
    @Override
    protected void doDispatch(HttpServletRequest request, HttpServletResponse response) throws Exception {
        try {
            ServletContext servletContext = this.getServletContext();
            Map<Class<?>, Object> map = ResteasyContext.getContextDataMap();
            map.put(ServletContext.class, servletContext);
            super.doDispatch(request, response);
        } finally {
            ResteasyContext.popContextData(ServletContext.class);
        }
    }
}
