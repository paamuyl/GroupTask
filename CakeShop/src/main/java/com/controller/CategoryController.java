package com.controller;

import java.sql.Date;
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

import Model.Category;

@Controller
public class CategoryController {

	Session session=null;
	Transaction t=null;
	Dao dao=new Dao();
	
	public Session configGo() {
		Configuration configObj = new Configuration();
		configObj.addClass(Model.Category.class);
		configObj.configure("/Model/hibernate.cfg.xml");
		ServiceRegistry serviceRegistryObj = new StandardServiceRegistryBuilder()
				.applySettings(configObj.getProperties()).build();
		session = configObj.buildSessionFactory(serviceRegistryObj).openSession();
		return session;
	}
	
	@RequestMapping(value= "/admin/cate.do", method = RequestMethod.GET)
	public String listCate(ModelMap model) {
		List<Category> data = getAll();
		model.addAttribute("message", data);		
		return "viewcate";		
	}
	
	@RequestMapping(value= "/admin/category.do", method = RequestMethod.GET)
	public String listCategory(ModelMap model) {
		List<Category> data = getAll();
		model.addAttribute("pageBegin", 0);
		model.addAttribute("pageEnd", 7);
		int pageMax=data.size()/8;
		if (data.size()%8>0)pageMax+=1;
		int pageN=3;
		if (pageMax<=3) pageN=1;
		model.addAttribute("pageN", pageN);
		model.addAttribute("pageMax", pageMax);	
		model.addAttribute("message", data);		
		return "viewCategory";		
	}
	
	@RequestMapping(value= "/admin/SelectPdPageC.do" , method = RequestMethod.POST, params= {"page"})
	public String SelectPdPage(ModelMap model ,@RequestParam("page") String page ) {
		List<Category> data = getAll();
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
		return "viewCategory";		
	}

	@RequestMapping(value = "/admin/quyIdCat.do", method = RequestMethod.POST)
	public ModelAndView QueryById(ModelMap model ,@ModelAttribute("category") Category stu) {
		System.out.println("stu obj:" + stu);
		stu = findById(stu.getCategoryId());		
		model.addAttribute("categoryId", stu.getCategoryId());
		model.addAttribute("cateName", stu.getCategoryName());
		return  new ModelAndView("amendCategory");
	}
	
	@RequestMapping(value = "/admin/addCategory.do", method = RequestMethod.POST)
	public ModelAndView addCategory(@ModelAttribute("category") Category stu) {
		dao.addObj(stu);		
		return  new ModelAndView("viewCategory","message",getAll());
	}
	
	@RequestMapping(value = "/admin/updateCategory.do", method = RequestMethod.POST)
	public ModelAndView updateCategory(@ModelAttribute("category") Category stu) {
		System.out.println("stu obj:" + stu+"A");
		dao.updateObj(stu);		
		return  new ModelAndView("redirect:/admin/category.do");

	}
	
	@RequestMapping(value = "/admin/deleCategory.do", method = RequestMethod.POST)
	public ModelAndView deleteCategory(@ModelAttribute("category") Category stu) {
		System.out.println("stu obj:" + stu);
		dao.deleteObj(stu);		
		return  new ModelAndView("redirect:/admin/category.do");
	}
	
	List<Category> getAll() {
		try {
			session=configGo();
			t = session.beginTransaction();
			List data = session.createQuery("FROM Category").list();
			for (Iterator iterator = data.iterator(); iterator.hasNext();) {
				Category st = (Category) iterator.next();
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
	
	public Category findById(int categoryId) {
		try {	
			session=configGo();
			t=session.beginTransaction();
			Category instance = (Category) session.find(Model.Category.class, categoryId);
			t.commit();
			return instance;
		} catch (RuntimeException re) {
			throw re;

		}
	}
}
