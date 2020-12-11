package com.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


import Model.Product;

@Controller
public class CartController {
	List<Product> dataSave = null;
	List<String> QtySave = null;
	Session session=null;
	Transaction t=null;
	
	@RequestMapping(value= "SeeCart.do" , method = RequestMethod.POST, params= {"ProductCodes[]","ItemQtys[]"})
	//@RequestMapping(method = RequestMethod.GET)
		public String SeeCart(ModelMap model,@RequestParam("ProductCodes[]") String[] ProductCodes,@RequestParam("ItemQtys[]") List<String> ItemQtys) {
		List<Product> data = null;		if(data==null) {data=new ArrayList<Product>();}
		List<String> Qtydata = null;	if(Qtydata==null) {Qtydata=new ArrayList<String>();}
		
        for (String s:ProductCodes)
        {
        	if(!s.equals("")) {
        	Product dada=findById(s);
        	data.add(dada);}        	
        }
        for (String X:ItemQtys)
        {
        	if(!X.equals("")) {        	
        		Qtydata.add(X);}        	
        }
        dataSave=data;
        QtySave=Qtydata;	
        
		model.addAttribute("message", dataSave);
		model.addAttribute("msg2", QtySave);
		if(QtySave.isEmpty()) {model.addAttribute("NoMapping", "購物車內沒有物品喔!趕快去選購");return "viewCart";}
		return "viewCart";
	}
	
	@RequestMapping(value= "CheckOut.do" , method = RequestMethod.POST, params= {"ProductCodes[]","ItemQtys[]"})
	//@RequestMapping(method = RequestMethod.GET)
		public String CheckOut(ModelMap model,@RequestParam("ProductCodes[]") String[] ProductCodes,@RequestParam("ItemQtys[]") List<String> ItemQtys) {
		List<Product> data = null;		if(data==null) {data=new ArrayList<Product>();}
		List<String> Qtydata = null;	if(Qtydata==null) {Qtydata=new ArrayList<String>();}
		int sum=0;String AllProduct="";
        for (String s:ProductCodes)
        {
        	if(!s.equals("")) {
        	Product dada=findById(s);
        	data.add(dada);
        	AllProduct=AllProduct+"#"+dada.getProductName();
        	}        	
        }
        for (String X:ItemQtys)
        {
        	if(!X.equals("")) {        	
        		Qtydata.add(X);}        	
        }
        for(int i=0;i<data.size();i++) {
        sum=sum+data.get(i).getPrice()*Integer.parseInt(Qtydata.get(i));
        }
        dataSave=data;
        QtySave=Qtydata;	
        
		model.addAttribute("message", dataSave);
		model.addAttribute("msg2", QtySave);
		model.addAttribute("Sum", sum);
		model.addAttribute("AllProduct", AllProduct);
		if(QtySave.isEmpty()) {model.addAttribute("NoMapping", "購物車內沒有物品喔!趕快去選購");return "viewCart";}
		return "viewCheckout";
	}
	public Session configGo() {
		Configuration configObj = new Configuration();
		configObj.addClass(Model.Product.class);
		configObj.configure("/Model/hibernate.cfg.xml");
		ServiceRegistry serviceRegistryObj = new StandardServiceRegistryBuilder()
				.applySettings(configObj.getProperties()).build();
		session = configObj.buildSessionFactory(serviceRegistryObj).openSession();
		return session;
	}
	
	public Product findById(String ProductCode) {
		try {	
			session=configGo();
			t=session.beginTransaction();
			Product instance = (Product) session.find(Model.Product.class, Integer.parseInt(ProductCode));
			t.commit();
			return instance;
		} catch (RuntimeException re) {
			throw re;

		}
	}
}
