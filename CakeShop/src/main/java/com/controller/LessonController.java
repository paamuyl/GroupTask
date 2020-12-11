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

import Model.Lesson;
import Model.Member;


@Controller
public class LessonController {
	
	Session session=null;
	Transaction t=null;
	Dao dao=new Dao();
	
	public Session configGo() {
		Configuration configObj = new Configuration();
		configObj.addClass(Model.Lesson.class);
		configObj.configure("/Model/hibernate.cfg.xml");
		ServiceRegistry serviceRegistryObj = new StandardServiceRegistryBuilder()
				.applySettings(configObj.getProperties()).build();
		session = configObj.buildSessionFactory(serviceRegistryObj).openSession();
		return session;
	}
	
	@RequestMapping(value="/admin/lesson.do", method = RequestMethod.GET)
	public String listlesson(ModelMap model) {
		List<Lesson> data = getAll();
		model.addAttribute("message", data);		
		return "viewlesson";		
	}
	
	@RequestMapping(value="/lesson.do", method = RequestMethod.GET)
	public String listlessonFrontend(ModelMap model) {
		List<Lesson> data = getAll();
		model.addAttribute("messagelessonFrontend", data);		
		return "viewlessonFrontend";		
	}
	
	@RequestMapping(value="/selectLesson.do", method = RequestMethod.POST)
	public String listSelectLesson(ModelMap model) {
		List<Lesson> data = getSelect();
		model.addAttribute("message", data);		
		return "viewSelectLesson";		
	}

	@RequestMapping(value = "/admin/updateLesson.do", method = RequestMethod.POST, params= {"lessonId", "lessonName", "lessonDescription", "lessonPrice", "lessonDate"})
	public ModelAndView updateEmployee(@RequestParam("lessonId") Integer lessonId, @RequestParam("lessonName") String lessonName, @RequestParam("lessonDescription") String lessonDescription,
			@RequestParam("lessonPrice") String lessonPrice, @RequestParam("lessonDate") String lessonDate, Lesson lesson) {
		Lesson l1=new Lesson(); l1=findById(lessonId);
		String lessonPicUrl=l1.getLessonPicUrl();
		
		if(lessonName.equals("")) lessonName=l1.getLessonName();
		if(lessonDescription.equals("")) lessonDescription=l1.getLessonDescription();
		if(lessonPrice.equals("")) lessonPrice=l1.getLessonPrice();
		if(lessonDate.equals("")) lessonDate=l1.getLessonDate();
		
		lesson=new Lesson(lessonId, lessonName, lessonDescription, lessonPrice, lessonDate, lessonPicUrl);
		dao.updateObj(lesson);		
		return  new ModelAndView("redirect:/admin/lesson.do");

	}
	
	@RequestMapping(value = "/admin/deleteLesson.do", method = RequestMethod.POST, params= {"lessonName"})
	public ModelAndView deletelesson(@RequestParam("lessonName") String lessonName , Lesson lesson) {
		lesson.setLessonId(Integer.parseInt(lessonName.split("del")[1]));
		dao.deleteObj(lesson);		
		return  new ModelAndView("redirect:/admin/lesson.do");

	}
	
	List<Lesson> getAll() {
		try {
			session=configGo();
			t = session.beginTransaction();
			List data = session.createQuery("FROM Lesson").list();
			for (Iterator iterator = data.iterator(); iterator.hasNext();) {
				Lesson st = (Lesson) iterator.next();	
			}
			t.commit();
			return data;
		} catch (HibernateException e) {
			if (t != null)
				t.rollback();
			e.printStackTrace();
		} finally {
			session.close();
			t=null;
		}
		return null;
	}
	
	List<Lesson> getSelect() {
		Configuration configObj = new Configuration();
		configObj.addClass(Model.Lesson.class);
		configObj.configure("/Model/hibernate.cfg.xml");
		ServiceRegistry serviceRegistryObj = new StandardServiceRegistryBuilder()
				.applySettings(configObj.getProperties()).build();
		Session session=configObj.buildSessionFactory(serviceRegistryObj).openSession();
		Transaction t = session.beginTransaction();
		try {
			
			List data = session.createQuery("FROM Lesson").list();
			for (Iterator iterator = data.iterator(); iterator.hasNext();) {
				Lesson st = (Lesson) iterator.next();	
			}
			t.commit();
			return data;
		} catch (HibernateException e) {
			if (t != null)
				t.rollback();
			e.printStackTrace();
		} finally {
			session.close();
			t=null;
		}
		return null;
	}
	
	public Lesson findById(int lessonId) {
		try {	
			session=configGo();
			t=session.beginTransaction();
			Lesson instance = (Lesson) session.find(Model.Lesson.class, lessonId);
			t.commit();
			return instance;
		} catch (RuntimeException re) {
			throw re;

		}
	}
}
