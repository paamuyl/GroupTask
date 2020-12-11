package com.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Model.Employee;

/**
 * Servlet Filter implementation class Login
 */
//@WebFilter(filterName="LoginFilter", urlPatterns="/admin/index.jsp")
public class LoginFilter implements Filter {

	private int Authority = -1;
	int getAuthority() {
		return this.Authority;
	}

	void setAuthority(int Authority) {
		this.Authority = Authority;
	}

	public LoginFilter() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request=(HttpServletRequest) servletRequest;
		HttpServletResponse response=(HttpServletResponse) servletResponse;
		if(request.getSession().getAttribute("user")==null)request.getSession().setAttribute("user", 0);
		setAuthority((int)request.getSession().getAttribute("user"));
		System.out.println(Authority);
		
		if (Authority>=1) {
			
			chain.doFilter(request, response);
		} else {
			
			request.getRequestDispatcher("/admin/login.jsp").forward(request, response);
		}
		
	}
	
	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
