package com.controller;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import Model.Employee;
import Model.Member;
import Model.Orderdetail;
import Model.Orderlist;

@Controller
public class LoginController {
	Session session=null;
	Transaction t=null;
	
	public Session configGo() {
		Configuration configObj = new Configuration();
		configObj.addClass(Employee.class);
		configObj.addClass(Member.class);
		configObj.addClass(Orderdetail.class);
		configObj.addClass(Orderlist.class);
		configObj.configure("/Model/hibernate.cfg.xml");
		ServiceRegistry serviceRegistryObj = new StandardServiceRegistryBuilder()
				.applySettings(configObj.getProperties()).build();
		session = configObj.buildSessionFactory(serviceRegistryObj).openSession();
		return session;
	}
	
	@RequestMapping(value="/admin/logout.do", method = RequestMethod.POST)
	public ModelAndView Logout(HttpSession session) {
		session.setAttribute("user", 0);
		return new ModelAndView("redirect:/admin/login.jsp");
	}

	@RequestMapping(value="/admin/employeelogin.do", method = RequestMethod.POST, params= { "account" , "password" })
	public ModelAndView queryemployee(HttpSession session, @RequestParam("account") String Account, @RequestParam("password") String Password) {		
		String result = getemployee(Account, Password);
		if(result.equals("ok")) {
			session.setAttribute("user", 1);
			return new ModelAndView("viewsuccess", "result", result);
		}else {
			return new ModelAndView("viewsuccess", "result", result);
		}
	}
	
	String getemployee(String account,String password) {
		try {
			session=configGo();Employee st = null;
			t = session.beginTransaction();
			List employeeData = session.createQuery("FROM Employee where account='"+account+"'").list();
			for (Iterator iterator = employeeData.iterator(); iterator.hasNext();) {
				st = (Employee) iterator.next();
			}				
			t.commit();
			if(st!=null) {
				if (password.equals(st.getPassword())) return "ok";else {return "trash";}}
			else {return "trash";}
				
		 } catch (HibernateException e) {
			if (t != null)
				t.rollback();
			e.printStackTrace();
			return "連線異常,請稍後再試！";
		 }  finally {			
			session.close();
		}		
	}

	@RequestMapping(value="/Memberlogin.do", method = RequestMethod.POST, params= {"account","password"})
	public ModelAndView listMember(HttpSession session, Model model,
												@RequestParam("account") String Account,
												@RequestParam("password") String Password) {
		String result=getMember(Account,Password);
		if(result.equals("incorrect")) {
			System.out.println("Fail to access member account");
			model.addAttribute("result", "incorrect");			
			return new ModelAndView("viewsuccess", "result", result);			
		}else {
			System.out.println("Member Id: " + result + " just logs in");
			Member m=new Member();
			m=findById(Integer.parseInt(result));
			session.setAttribute("memberId", m);			
			model.addAttribute("result", "success");
			return new ModelAndView("viewsuccess", "result", "success");
		}
	}
	
	@RequestMapping(value = "/memlogout.do", method = RequestMethod.POST)
	protected void Logout(HttpServletRequest ques, HttpServletResponse resp, Member member) throws ServletException, IOException {
		Object str = ques.getSession().getAttribute("memberId");
		member=(Member)str;
		System.out.println("MemberId : " + member.getMemberId() + " has logged out");
		ques.getSession().invalidate();
        resp.sendRedirect("logout.html");
      }
	
	String getMember(String account,String password) {
		try {
			session=configGo();Member st = null;
			t = session.beginTransaction();
			List MemberData = session.createQuery("FROM Member where account='"+account+"'").list();
			for (Iterator iterator = MemberData.iterator(); iterator.hasNext();) {
				st = (Member) iterator.next();
			}
			t.commit();
			if(st!=null) {
				if (password.equals(st.getPassword())) { 
					return st.getMemberId().toString();
				}else {
					return "incorrect";}
				}
			else {
				return "incorrect";
				}
				
		 } catch (HibernateException e) {
			if (t != null)
				t.rollback();
			e.printStackTrace();
			return "連線異常, 請再試一次";
		 }  finally {			
			session.close();
		}
	}
	
	@RequestMapping(value="/header.do", method = RequestMethod.POST)
	public ModelAndView listMember(HttpSession session, Model model) {
		Object header=session.getAttribute("memberId");
		if(header==null) {
			return new ModelAndView("viewheader1");			
		}else {
			return new ModelAndView("viewheader2");
		}		
	}
	
	public Member findById(int memberId) {
		try {	
			session=configGo();
			t=session.beginTransaction();
			Member instance = (Member) session.find(Member.class, memberId);
			t.commit();
			return instance;
		} catch (RuntimeException re) {
			throw re;
		}
	}
}
