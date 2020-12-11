package com.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.hibernate.service.ServiceRegistry;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import Model.Category;
import Model.Product;


@Controller
public class ProductController {
	
	//新增方法在AddProductServlet
	
	Session session=null;
	Transaction t=null;
	Dao dao=new Dao();
	
	public Session configGo() {
		Configuration configObj = new Configuration();
		configObj.addClass(Model.Product.class);
		configObj.addClass(Model.Category.class);
		configObj.configure("/Model/hibernate.cfg.xml");
		ServiceRegistry serviceRegistryObj = new StandardServiceRegistryBuilder()
				.applySettings(configObj.getProperties()).build();
		session = configObj.buildSessionFactory(serviceRegistryObj).openSession();
		return session;
	}
	
	@RequestMapping(value= "/shop.do" , method = RequestMethod.GET)
	public String listShop(ModelMap model) {
		
		List<Category> data2=CgetAll();
		model.addAttribute("message2", data2);
		
		List<Product> data1 = getAll();
		model.addAttribute("pageBegin", 0);
		model.addAttribute("pageEnd", 5);
		int pageMax=data1.size()/6;
		if (data1.size()%6>0)pageMax+=1;
		int pageN=3;
		if (pageMax<=3) pageN=1;
		model.addAttribute("pageN", pageN);
		model.addAttribute("pageMax", pageMax);
		
		model.addAttribute("message1", data1);

		
		return "viewShop";	
	}
	
	@RequestMapping(value= "/SelectPdPageS.do" , method = RequestMethod.POST, params= {"page"})
	public String SelectPdPageS(ModelMap model ,@RequestParam("page") String page ) {
		
		List<Category> data2=CgetAll();
		model.addAttribute("message2", data2);
		
		List<Product> data1 = getAll();
		int pageMax=data1.size()/6;
		if (data1.size()%6>0)pageMax+=1;
		int pageN=Integer.parseInt(page.split("page")[1]);
		model.addAttribute("pageBegin", (pageN-1)*6);
		model.addAttribute("pageEnd", (pageN*6)-1);		
		
		if (pageMax>3&&pageN<3)pageN=3;
		if (pageN+2>pageMax&&pageMax>3)pageN=pageMax-2;
		model.addAttribute("pageN", pageN);
		model.addAttribute("pageMax", pageMax);
		
		model.addAttribute("message1", data1);
		
		return "viewShop";		
	}
	
	@RequestMapping(value = "/searchShop.do", method = RequestMethod.POST, params= {"search"})
	public String SearchShop(ModelMap model, @RequestParam("search") String search , Product prod) {
		List<Category> data2=CgetAll();
		model.addAttribute("message2", data2);
		
		List<Product> data=null;
		if(search!=null) data=Search(search);
		if(search==null) data=getAll();
		model.addAttribute("message1", data);
		model.addAttribute("pageBegin", 0);
		model.addAttribute("pageEnd", 5);
		int pageMax=data.size()/6;
		if (data.size()%6>0)pageMax+=1;
		int pageN=3;
		if (pageMax<=3) pageN=1;
		model.addAttribute("pageN", pageN);
		model.addAttribute("pageMax", pageMax);
		return "viewShop";
	}
	
	@RequestMapping(value = "/categorySearch.do", method = RequestMethod.POST, params= {"categoryId"})
	public String CategorySearch(ModelMap model, @RequestParam("categoryId") Integer categoryId , Product prod) {
		List<Category> data2=CgetAll();
		model.addAttribute("message2", data2);
		
		List<Product> data=CategorySearch(categoryId);
		model.addAttribute("message1", data);
		model.addAttribute("pageBegin", 0);
		model.addAttribute("pageEnd", 5);
		int pageMax=data.size()/6;
		if (data.size()%6>0)pageMax+=1;
		int pageN=3;
		if (pageMax<=3) pageN=1;
		model.addAttribute("pageN", pageN);
		model.addAttribute("pageMax", pageMax);
		return "viewShop";
	}
	
	@RequestMapping(value= "/admin/product.do", method = RequestMethod.GET)
	public String listProduct(ModelMap model) {
		List<Product> data = getAll();
		model.addAttribute("pageBegin", 0);
		model.addAttribute("pageEnd", 7);
		int pageMax=data.size()/8;
		if (data.size()%8>0)pageMax+=1;
		int pageN=3;
		if (pageMax<=3) pageN=1;
		model.addAttribute("pageN", pageN);
		model.addAttribute("pageMax", pageMax);	
		model.addAttribute("message", data);		
		return "viewprod";		
	}
	
	@RequestMapping(value= "/admin/SelectPdPageP.do" , method = RequestMethod.POST, params= {"page"})
	public String SelectPdPageP(ModelMap model ,@RequestParam("page") String page ) {
		List<Product> data = getAll();
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
		return "viewprod";		
	}
	
	@RequestMapping(value = "/admin/updateProduct.do", method = RequestMethod.POST, params= {"prodCode", "prodName", "prodPrice", "prodInStock"})
	public ModelAndView updateEmployee(@RequestParam("prodCode") Integer prodCode, @RequestParam("prodName") String prodName,
			@RequestParam("prodPrice") Integer prodPrice, @RequestParam("prodInStock") String prodInStock, Product prod) {
		Product p1=new Product();	p1=findById(prodCode);
		String description=p1.getDescription();
		Integer category=p1.getCategoryId();
		String expireDate=p1.getExpireDate();
		String picUrl=p1.getPicUrl();
		Date createdDate=p1.getCreatedDate();
		
		if(prodName.equals("")) prodName=p1.getProductName();
		if(prodPrice==null) prodPrice=p1.getPrice();
		if(prodInStock.equals("")) prodInStock=p1.getUnitInStock();
		
		prod=new Product(prodCode, prodName, description, prodPrice, category, expireDate, prodInStock, picUrl, createdDate);
		dao.updateObj(prod);		
		return  new ModelAndView("redirect:/admin/product.do");

	}
	
	@RequestMapping(value = "/admin/deleteProduct.do", method = RequestMethod.POST, params= {"productIdArr[]"})
	public String deleteEmployee(ModelMap model, @RequestParam("productIdArr[]") String[] productIdArr , Product prod) {
		prod=new Product();
		for(String A:productIdArr) {
			prod.setProductCode(Integer.parseInt(A));
			dao.deleteObj(prod);
		}
		return listProduct(model);
	}
		
	List<Product> getAll() {
		try {
			session=configGo();
			t = session.beginTransaction();
			List<Product> data = session.createQuery("FROM Product").list();
			for (Iterator iterator = data.iterator(); iterator.hasNext();) {
				Product st = (Product) iterator.next();				
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
	
	List<Category> CgetAll() {
		try {
			session=configGo();
			t = session.beginTransaction();
			List<Category> data = session.createQuery("FROM Category").list();
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
	
	List<Product> Search(String productName){
		session=configGo();
		t=session.beginTransaction();
		Query<Product> query=session.createQuery("FROM Product p where p.productName like :ProductName", Product.class);
		query.setParameter("ProductName", "%"+productName+"%");
		List<Product> prod=query.list();
		t.commit();
		return prod;
	}
	
	List<Product> CategorySearch(Integer categoryId){
		session=configGo();
		t=session.beginTransaction();
		Query<Product> query=session.createQuery("FROM Product p where p.categoryId= :categoryId", Product.class);
		query.setParameter("categoryId", categoryId);
		List<Product> prod=query.list();
		t.commit();
		return prod;
	}
	
	public Product findById(int productCode) {
		try {	
			session=configGo();
			t=session.beginTransaction();
			Product instance = (Product) session.find(Model.Product.class, productCode);
			t.commit();
			return instance;
		} catch (RuntimeException re) {
			throw re;

		}
	}
}