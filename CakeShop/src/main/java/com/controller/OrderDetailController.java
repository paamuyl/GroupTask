package com.controller;
import java.util.Date;
import java.time.LocalDate;
import java.util.Iterator;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import Model.Employee;
import Model.Member;
import Model.Orderdetail;
import Model.Orderlist;

import java.util.logging.Level;
import java.util.logging.Logger;


@Controller
public class OrderDetailController {
	Session session=null;
	Transaction t=null;
	Dao dao=new Dao();
	
	public Session configGo() {
		Configuration configObj = new Configuration();
		configObj.addClass(Model.Member.class);
		configObj.addClass(Model.Orderlist.class);
		configObj.addClass(Model.Orderdetail.class);
		configObj.configure("/Model/hibernate.cfg.xml");
		ServiceRegistry serviceRegistryObj = new StandardServiceRegistryBuilder()
				.applySettings(configObj.getProperties()).build();
		session = configObj.buildSessionFactory(serviceRegistryObj).openSession();
		return session;
	}

	@RequestMapping(value= "/admin/orderdetailquerybyid.do" , method = RequestMethod.GET, params= {"orderNumber"})
	public String listOrderdetailquerybyid(ModelMap model,@RequestParam("orderNumber") Integer orderNumber ) {
		List<Orderdetail> data =getorderdata(orderNumber);
		model.addAttribute("pageBegin", 0);
		model.addAttribute("pageEnd", 7);
		int pageMax=data.size()/8;
		if (data.size()%8>0)pageMax+=1;
		int pageN=3;
		if (pageMax<=3) pageN=1;
		model.addAttribute("pageN", pageN);
		model.addAttribute("pageMax", pageMax);	
		model.addAttribute("message", data);		
		return "vieworderdetail";		
	}
	
	@RequestMapping(value= "/admin/SelectPdPageOt.do" , method = RequestMethod.POST, params= {"page"})
	public String SelectPdPage(ModelMap model ,@RequestParam("page") String page ) {
		List<Orderdetail> data = getAll();
		int pageMax=data.size()/8;
		if (data.size()%8>0)pageMax+=1;
		int pageN=Integer.parseInt(page.split("page")[1]);
		model.addAttribute("pageBegin", (pageN-1)*8);
		model.addAttribute("pageEnd", (pageN*8)-1);		
		
		if (pageMax>3&&pageN<3)pageN=3;
		if (pageN+2>pageMax&&pageMax>3)pageN=pageMax-2;
		model.addAttribute("pageN", pageN);
		model.addAttribute("pageMax", pageMax);		
		model.addAttribute("message", data);		
		return "vieworderdetail";		
	}

	List<Orderdetail> getAll() {
		try {
			session=configGo();
			t = session.beginTransaction();
			List data = session.createQuery("FROM Orderdetail").list();
			for (Iterator iterator = data.iterator(); iterator.hasNext();) {
				Orderdetail st = (Orderdetail) iterator.next();				
			}
			t.commit();
			return data;
		} catch (HibernateException e) {
			if (t != null)
				t.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return null;
	}
	List<Orderdetail> getorderdata(int orderNumber) {
		try {
			session=configGo();
			t = session.beginTransaction();
			List data = session.createQuery("FROM Orderdetail where orderNumber="+orderNumber).list();
			for (Iterator iterator = data.iterator(); iterator.hasNext();) {
				Orderdetail st = (Orderdetail) iterator.next();				
			}
			t.commit();
			return data;
		} catch (HibernateException e) {
			if (t != null)
				t.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return null;
	}

	public Orderdetail findById(int orderdetailId) {
		try {	
			session=configGo();
			t=session.beginTransaction();
			Orderdetail instance = (Orderdetail) session.find(Model.Orderdetail.class,  orderdetailId);
			t.commit();
			return instance;
		} catch (RuntimeException re) {
			throw re;

		}
	}
	
	@RequestMapping(value = "/admin/updateOrderdetail.do", method = RequestMethod.POST, params= {"odid","oNumber", "pCode","qty","pr"})
	public ModelAndView updateOrderdetail(@RequestParam("odid") Integer odid,@RequestParam("oNumber") Integer oNumber, @RequestParam("pCode") Integer pCode,
			@RequestParam("qty") Integer qty,@RequestParam("pr") Integer pr, Orderdetail Orderdetail) {
		Orderdetail=new Orderdetail(odid,oNumber,pCode,qty,pr);
		dao.updateObj(Orderdetail);		
		return  new ModelAndView("redirect:/admin/orderdetailquerybyid.do","orderNumber",oNumber);

	}

	@RequestMapping(value = "/admin/deleteOrderdetail.do", method = RequestMethod.POST, params= {"orderdetailId","orderNumber"})
	public ModelAndView deleteOrderdetail(@RequestParam("orderdetailId") Integer orderdetailId,@RequestParam("orderNumber") Integer orderNumber) {
		Orderdetail orderdetail=new Orderdetail();
		orderdetail.setOrderdetailId(orderdetailId);;
		dao.deleteObj(orderdetail);		
		return  new ModelAndView("redirect:/admin/orderdetailquerybyid.do","orderNumber",orderNumber);
	}
	
}
	
	
