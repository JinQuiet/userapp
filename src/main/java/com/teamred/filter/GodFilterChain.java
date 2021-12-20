package com.teamred.filter;

import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;

public class GodFilterChain implements FilterChain {

    private FilterChain chain;
    private List<Filter> filters = new ArrayList<Filter>();
    private Iterator<Filter> iterator;

    public GodFilterChain(FilterChain chain) {
        this.chain = chain;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response) throws IOException, ServletException {
        if (iterator == null) {
            iterator = filters.iterator();
        }

        if (iterator.hasNext()) {
            iterator.next().doFilter(request, response, this);
        } else {
            this.chain.doFilter(request, response);
        }
    }

    public void addFilter(Filter filter) {
        if (iterator != null) {
            throw new IllegalStateException();
        }

        filters.add(filter);
    }

}