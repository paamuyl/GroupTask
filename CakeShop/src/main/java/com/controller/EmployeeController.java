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
import java.util.logging.Level;
import java.util.logging.Logger;


@Controller
public class EmployeeController {
	
	Session session=null;
	Transaction t=null;
	Dao dao=new Dao();
	
	public Session configGo() {
		Configuration configObj = new Configuration();
		configObj.addClass(Model.Employee.class);	
		configObj.configure("/Model/hibernate.cfg.xml");
		ServiceRegistry serviceRegistryObj = new StandardServiceRegistryBuilder()
				.applySettings(configObj.getProperties()).build();
		session = configObj.buildSessionFactory(serviceRegistryObj).openSession();
		return session;
	}
	
	@RequestMapping(value="/admin/employee.do", method = RequestMethod.GET)
	public String listEmployee(ModelMap model) {
		List<Employee> data = getAll();
		model.addAttribute("message", data);		
		return "viewemp";		
	}

	

	@RequestMapping(value = "/admin/addEmployee.do", method = RequestMethod.POST)
	public ModelAndView addEmployee(@ModelAttribute("emp") Employee emp) {
		Date a=new Date();
		emp.setCreatedDate(a);
		dao.addObj(emp);
		
		return  new ModelAndView("viewemp","message", getAll());
	}
	
	@RequestMapping(value = "/admin/updateEmployee.do", method = RequestMethod.POST, params= {"empId", "empName", "empAccount", "empPassword", "empEmail"})
	public ModelAndView updateEmployee(@RequestParam("empId") Integer empId, @RequestParam("empName") String empName, 
			@RequestParam("empAccount") String empAccount, @RequestParam("empPassword") String empPassword, @RequestParam("empEmail") String empEmail, Employee emp ) {
		Employee e1=new Employee(); e1=findById(empId);
		Date createdDate=e1.getCreatedDate();
		
		if(empName.equals("")) empName=e1.getEmployeeName();
		if(empAccount.equals("")) empAccount=e1.getAccount();
		if(empPassword.equals("")) empPassword=e1.getPassword();
		if(empEmail.equals("")) empEmail=e1.getEmail();
		
		emp=new Employee(empId, empName, empAccount, empPassword, empEmail, createdDate);		
		dao.updateObj(emp);		
		return  new ModelAndView("viewemp","message",getAll());

	}
	
	@RequestMapping(value = "/admin/deleteEmployee.do", method = RequestMethod.POST, params= {"employeeName"})
	public ModelAndView deleteEmployee(@RequestParam("employeeName") String employeeName , Employee emp) {
		emp.setEmployeeId(Integer.parseInt(employeeName.split("del")[1]));
		dao.deleteObj(emp);		
		return  new ModelAndView("viewemp","message", getAll());

	}
	
	List<Employee> getAll() {
		try {
			session=configGo();
			t = session.beginTransaction();
			List data = session.createQuery("FROM Employee").list();
			for (Iterator iterator = data.iterator(); iterator.hasNext();) {
				Employee st = (Employee) iterator.next();				
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
	
	public Employee findById(int employeeId) {
		try {	
			session=configGo();
			t=session.beginTransaction();
			Employee instance = (Employee) session.find(Model.Employee.class, employeeId);
			t.commit();
			return instance;
		} catch (RuntimeException re) {
			throw re;
		}
	}
}