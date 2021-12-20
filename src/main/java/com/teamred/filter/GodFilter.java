package com.teamred.filter;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;

@WebFilter(urlPatterns = {"/*"})
public class GodFilter implements Filter {

    private Map<Pattern, Filter> filters = new LinkedHashMap<Pattern, Filter>();

    @Override
    public void init(FilterConfig config) throws ServletException {
        
        
        RootFilter staticFilter = new RootFilter();
        staticFilter.init(config);
        filters.put(new Pattern("/*"), staticFilter);
        
        UsersFilter usersFilter = new UsersFilter();
        usersFilter.init(config);
        filters.put(new Pattern("/users/*"), usersFilter);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest hsr = (HttpServletRequest) request;
        String path = hsr.getRequestURI().substring(hsr.getContextPath().length());
        
        GodFilterChain godChain = new GodFilterChain(chain);

        for (Entry<Pattern, Filter> entry : filters.entrySet()) {
            if (entry.getKey().matches(path)) {
                godChain.addFilter(entry.getValue());
            }
        }

        godChain.doFilter(request, response);
    }

}

class Pattern {

    private int position;
    private String url;

    public Pattern(String url) {
        this.position = url.startsWith("*") ? 1
                      : url.endsWith("*") ? -1
                      : 0;
        this.url = url.replaceAll("/?\\*", "");
    }

    public boolean matches(String path) {
        return 
      
        (position == -1) ? path.startsWith(url)
             : (position == 1) ? path.endsWith(url)
             : path.equals(url);
    }
}