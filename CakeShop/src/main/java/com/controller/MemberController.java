package com.controller;

import java.io.IOException;
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
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import Model.Member;
import Model.Orderdetail;
import Model.Orderlist;


@Controller
public class MemberController {
	
	Session session=null;
	Transaction t=null;
	Dao dao=new Dao();
	
	public Session configGo() {
		Configuration configObj = new Configuration();
		configObj.addClass(Member.class);
		configObj.addClass(Orderlist.class);
		configObj.addClass(Orderdetail.class);
		configObj.configure("/Model/hibernate.cfg.xml");
		ServiceRegistry serviceRegistryObj = new StandardServiceRegistryBuilder()
				.applySettings(configObj.getProperties()).build();
		session = configObj.buildSessionFactory(serviceRegistryObj).openSession();
		return session;
	}
	
	@RequestMapping(value= "/admin/member.do", method = RequestMethod.GET)
	public String listMember(ModelMap model) {
		List<Member> data = getAll();
		model.addAttribute("pageBegin", 0);
		model.addAttribute("pageEnd", 7);
		int pageMax=data.size()/8;
		if (data.size()%8>0)pageMax+=1;
		int pageN=3;
		if (pageMax<=3) pageN=1;
		model.addAttribute("pageN", pageN);
		model.addAttribute("pageMax", pageMax);	
		model.addAttribute("message", data);		
		return "viewmember";		
	}
	
	@RequestMapping(value= "/admin/SelectPdPageM.do" , method = RequestMethod.POST, params= {"page"})
	public String SelectPdPage(ModelMap model ,@RequestParam("page") String page ) {
		List<Member> data = getAll();
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
		return "viewmember";		
	}
	
	@RequestMapping(value = "/addMember.do", method = RequestMethod.POST)
	public ModelAndView addMember(@ModelAttribute("member") Member member) {
		dao.addObj(member);
		return  new ModelAndView("redirect:/confirmMem.jsp");
	}
	
	@RequestMapping(value = "/admin/updateMember.do", method = RequestMethod.POST, params= {"memId", "memName",
			"memGender", "memBirthday", "memEmail", "memAddress", "memPhoneNumber"})
	public ModelAndView updateMember(@RequestParam("memId") Integer memId, @RequestParam("memName") String memName,
			@RequestParam("memGender") String memGender, @RequestParam("memBirthday") String memBirthday, @RequestParam("memEmail") String memEmail,
			@RequestParam("memAddress") String memAddress, @RequestParam("memPhoneNumber") String memPhoneNumber, Member member) {
		Member m1=new Member(); m1=findById(memId);
		String account=m1.getAccount();
		String password=m1.getPassword();
		
		if(memName.equals("")) memName=m1.getMemberName();
		if(memGender.equals("")) memGender=m1.getGender();
		if(memBirthday.equals("")) memBirthday=m1.getBirthday();
		if(memEmail.equals("")) memEmail=m1.getEmail();
		if(memAddress.equals("")) memAddress=m1.getAddress();
		if(memPhoneNumber.equals("")) memPhoneNumber=m1.getPhoneNumber();
		
		member=new Member(memId, account, password,	memName, memGender, memBirthday, memEmail, memAddress, memPhoneNumber);
		dao.updateObj(member);		
		return  new ModelAndView("redirect:/admin/member.do");

	}
	
	@RequestMapping(value = "/admin/deleteMember.do", method = RequestMethod.POST, params= {"memberName"})
	public ModelAndView deleteMember(@RequestParam("memberName") String memberName , Member member) {
		member.setMemberId(Integer.parseInt(memberName.split("del")[1]));
		dao.deleteObj(member);		
		return  new ModelAndView("redirect:/admin/member.do");

	}
	
	@RequestMapping(value = "/quyIdMem.do", method = RequestMethod.POST)
	public ModelAndView QueryById(ModelMap model, @ModelAttribute("Member") Member stu) {		
		 System.out.println("stu obj:" + stu); stu = findById(stu.getMemberId());
		 model.addAttribute("memberId", stu.getMemberId());
		 model.addAttribute("account", stu.getAccount());
		 model.addAttribute("password", stu.getPassword());
		 model.addAttribute("memberName", stu.getMemberName());
		 model.addAttribute("gender", stu.getGender()); model.addAttribute("birthday",
		 stu.getBirthday()); model.addAttribute("email", stu.getEmail());
		 model.addAttribute("phoneNumber", stu.getPhoneNumber());
		 model.addAttribute("address", stu.getAddress()); return new
		 ModelAndView("viewAccount");
		 
	}

	@RequestMapping(value = "/quySMID.do", method = RequestMethod.POST)
	public ModelAndView QueryBySMID() {
		return new ModelAndView("viewAccount");
	}
		
	@RequestMapping(value = "/updateFM.do", method = RequestMethod.POST, params = { "memId", "memPwd",
				"memEmail", "memAddress", "memPhoneNumber"})
	public ModelAndView updateFM(@RequestParam("memId") Integer memId, @RequestParam("memPwd") String memPwd, 
				@RequestParam("memEmail") String memEmail, @RequestParam("memAddress") String memAddress, 
				@RequestParam("memPhoneNumber") String memPhoneNumber, Member member, HttpSession session) {
			Member m1 = new Member();
			m1 = findById(memId);
			String account = m1.getAccount();
			String memberName=m1.getMemberName();
			String gender=m1.getGender();
			String birthday=m1.getBirthday();

			if (memPwd.equals(""))
				memPwd = m1.getPassword(); // 前台允許變更密碼
			if (memEmail.equals(""))
				memEmail = m1.getEmail();
			if (memAddress.equals(""))
				memAddress = m1.getAddress();
			if (memPhoneNumber.equals(""))
				memPhoneNumber = m1.getPhoneNumber();

			member = new Member(memId, account, memPwd, memberName, gender, birthday, memEmail, memAddress,	memPhoneNumber);
			dao.updateObj(member);
			System.out.println("Member name: " + m1.getMemberName() + " has amended his/her info.");
			session.removeAttribute("memberId");
			session.setAttribute("memberId", member);
			return new ModelAndView("viewAccount");
			
		}
	
	List<Member> getAll() {
		try {
			session=configGo();
			t = session.beginTransaction();
			List data = session.createQuery("FROM Member").list();
			for (Iterator iterator = data.iterator(); iterator.hasNext();) {
				Member st = (Member) iterator.next();				
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