package com.controller;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import Model.Orderdetail;
import Model.Orderlist;


@Controller
public class OrderListController {
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
	
	@RequestMapping(value= "/admin/orders.do", method = RequestMethod.GET)
	public String listOrderlist(ModelMap model) {
		List<Orderlist> data = getAll();
		model.addAttribute("pageBegin", 0);
		model.addAttribute("pageEnd", 7);
		int pageMax=data.size()/8;
		if (data.size()%8>0)pageMax+=1;
		int pageN=3;
		if (pageMax<=3) pageN=1;
		model.addAttribute("pageN", pageN);
		model.addAttribute("pageMax", pageMax);	
		model.addAttribute("message", data);		
		return "vieworder";		
	}
	
	@RequestMapping(value= "/admin/SelectPdPageO.do" , method = RequestMethod.POST, params= {"page"})
	public String SelectPdPage(ModelMap model ,@RequestParam("page") String page ) {
		List<Orderlist> data = getAll();
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
		return "vieworder";		
	}

	List<Orderlist> getAll() {
		try {
			session=configGo();
			t = session.beginTransaction();
			List data = session.createQuery("FROM Orderlist").list();
			for (Iterator iterator = data.iterator(); iterator.hasNext();) {
				Orderlist st = (Orderlist) iterator.next();				
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
	
	public Orderlist findById(int orderNumber) {
		try {	
			session=configGo();
			t=session.beginTransaction();
			Orderlist instance = (Orderlist) session.find(Model.Orderlist.class, orderNumber);
			t.commit();
			return instance;
		} catch (RuntimeException re) {
			throw re;

		}
	}
	
	@RequestMapping(value = "/admin/updateOrder.do", method = RequestMethod.POST, params= {"oNumber", "pCode","qty","pr"})
	public ModelAndView updateOrder(@RequestParam("oNumber") Integer oNumber, @RequestParam("pCode") Integer pCode,
			@RequestParam("qty") Integer qty,@RequestParam("pr") Integer pr, Orderdetail Orderdetail) {
		Orderdetail=new Orderdetail(oNumber,pCode,qty,pr);
		dao.updateObj(Orderdetail);		
		return  new ModelAndView("redirect:/admin/orders.do");

	}
	
	@RequestMapping(value = "/admin/deleteOrderlist.do", method = RequestMethod.POST, params= {"orderNumber"})
	public ModelAndView deleteOrderlist(@RequestParam("orderNumber") Integer orderNumber){
		Orderlist orderlist=new Orderlist();
		orderlist.setOrderNumber(orderNumber);
		dao.deleteObj(orderlist);		
		return  new ModelAndView("redirect:/admin/orders.do");

	}
}
	
	
