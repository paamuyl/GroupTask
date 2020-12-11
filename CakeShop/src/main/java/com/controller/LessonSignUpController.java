package com.controller;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import Model.Lesson;
import Model.Lessonsignup;
import Model.Product;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.http.HttpSession;


@Controller
public class LessonSignUpController {
	
	Session session=null;
	Transaction t=null;
	Dao dao=new Dao();
	
	public Session configGo() {
		Configuration configObj = new Configuration();
		configObj.addClass(Lessonsignup.class);
		configObj.configure("/Model/hibernate.cfg.xml");
		ServiceRegistry serviceRegistryObj = new StandardServiceRegistryBuilder()
				.applySettings(configObj.getProperties()).build();
		session = configObj.buildSessionFactory(serviceRegistryObj).openSession();
		return session;
	}
	
	@RequestMapping(value= "/admin/lessonsignup.do", method = RequestMethod.GET)
	public ModelAndView listLessonsigup(ModelMap model) {
		List<Lessonsignup> data = getAll();
		model.addAttribute("pageBegin", 0);
		model.addAttribute("pageEnd", 7);
		int pageMax=data.size()/8;
		if (data.size()%8>0)pageMax+=1;
		int pageN=3;
		if (pageMax<=3) pageN=1;
		model.addAttribute("pageN", pageN);
		model.addAttribute("pageMax", pageMax);	
		model.addAttribute("message", data);		
		return new ModelAndView("viewlessonsignup");		
	}
	
	@RequestMapping(value= "/admin/SelectPdPagel.do" , method = RequestMethod.POST, params= {"page"})
	public String SelectPdPageP(ModelMap model ,@RequestParam("page") String page ) {
		List<Lessonsignup> data = getAll();
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
		return "viewlessonsignup";		
	}
	
	@RequestMapping(value = "/addLessonsignup.do", method = RequestMethod.POST, params= {"option"})
	public ModelAndView addLessonsignup(HttpSession session, @RequestParam("option") String option, @ModelAttribute("lessonsignup") Lessonsignup lessonsignup, Model model) {
		Date a=new Date();
		lessonsignup.setCreatedDate(a);
		
		if(option.equals("option1")) {
			if(session.getAttribute("memberId")!=null) {
				lessonsignup.setMemberId(Integer.parseInt(session.getAttribute("memberId").toString()));
				dao.addObj(lessonsignup);
				model.addAttribute("email", lessonsignup.getEmail());
				return  new ModelAndView("redirect:/sendEmail.do");
			}else {
				model.addAttribute("result", "loginFirst");
				return  new ModelAndView("viewsuccess");
			}
		}else if(option.equals("option2")) {
			dao.addObj(lessonsignup);
			model.addAttribute("email", lessonsignup.getEmail());
			return  new ModelAndView("redirect:/sendEmail.do");
		}else {
			model.addAttribute("result", "selectFirst");
			return  new ModelAndView("viewsuccess");
		}
		
	}
	
	@RequestMapping(value = "/admin/updateLessonsignup.do", method = RequestMethod.POST, params= {"lessonSignUpId", "name", "phoneNumber", "lessonId", "email"})
	public ModelAndView updatelessonsignuployee(@RequestParam("lessonSignUpId") Integer lessonSignUpId, @RequestParam("name") String name, 
			@RequestParam("phoneNumber") String phoneNumber, @RequestParam("lessonId") Integer lessonId, @RequestParam("email") String email, Lessonsignup lessonsignup ) {
		Lessonsignup e1=new Lessonsignup(); e1=findById(lessonSignUpId);
		Date createdDate=e1.getCreatedDate();
		
		if(name.equals("")) name=e1.getName();
		if(phoneNumber.equals("")) phoneNumber=e1.getPhoneNumber();
		if(lessonId==null) lessonId=e1.getLessonId();
		if(email.equals("")) email=e1.getEmail();
		
		lessonsignup=new Lessonsignup(lessonSignUpId, lessonId, name, phoneNumber, email, createdDate);		
		dao.updateObj(lessonsignup);		
		return  new ModelAndView("redirect:/admin/lessonsignup.do");

	}
	
	@RequestMapping(value = "/admin/deleteLessonsignup.do", method = RequestMethod.POST, params= {"name"})
	public ModelAndView deletelessonsignuployee(@RequestParam("name") String name , Lessonsignup lessonsignup) {
		lessonsignup.setLessonSignUpId(Integer.parseInt(name.split("del")[1]));
		dao.deleteObj(lessonsignup);		
		return  new ModelAndView("redirect:/admin/lessonsignup.do");
	}
	
	List<Lessonsignup> getAll() {
		try {
			session=configGo();
			t = session.beginTransaction();
			List data = session.createQuery("FROM Lessonsignup").list();
			for (Iterator iterator = data.iterator(); iterator.hasNext();) {
				Lessonsignup st = (Lessonsignup) iterator.next();				
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
	
	public Lessonsignup findById(int lessonSignUpId) {
		try {	
			session=configGo();
			t=session.beginTransaction();
			Lessonsignup instance = (Lessonsignup) session.find(Lessonsignup.class, lessonSignUpId);
			t.commit();
			return instance;
		} catch (RuntimeException re) {
			throw re;

		}
	}

}
