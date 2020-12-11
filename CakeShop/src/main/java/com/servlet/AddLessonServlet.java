package com.servlet;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import Model.Lesson;
import Model.Product;

/**
 * Servlet implementation class AddLessonServlet
 */
@MultipartConfig
@WebServlet("/admin/addLesson.do")
public class AddLessonServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddLessonServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    Session session=null;
	Transaction t=null;
	
	public Session configGo() {
		Configuration configObj = new Configuration();
		configObj.addClass(Model.Lesson.class);
		configObj.configure("/Model/hibernate.cfg.xml");
		ServiceRegistry serviceRegistryObj = new StandardServiceRegistryBuilder()
				.applySettings(configObj.getProperties()).build();
		session = configObj.buildSessionFactory(serviceRegistryObj).openSession();
		return session;
	}
	
	public void addObj(Object obj) {
		try {
			session=configGo();
			t=session.beginTransaction();
			session.save(obj);

			System.out.println("\n.......Records Saved Successfully To The Database.......\n");
			t.commit();

		} catch (Exception sqlException) {
			if (null != session.getTransaction()) {
				System.out.println("\n.......Transaction Is Being Rolled Back.......");
				session.getTransaction().rollback();
			}
			sqlException.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}
	}
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		request.setCharacterEncoding("UTF-8");
    	response.setCharacterEncoding("UTF-8");
    	response.setContentType("text/html;charset=utf-8");
    	HttpSession session=request.getSession();
		Part part=request.getPart("photo");
		String filename=getFilename(part);
		System.out.println(filename);
		String uploadpath=request.getServletContext().getRealPath("/images/");
		
		InputStream in=part.getInputStream();
		OutputStream out=new FileOutputStream(uploadpath+filename);
		byte[] buffer=new byte[1024];
		int length=-1;
		while((length=in.read(buffer)) != -1) {
			out.write(buffer, 0, length);
		}
		in.close();
		out.close();
		
		String lessonName=request.getParameter("lessonName");
		String lessonDescription=request.getParameter("lessonDescription");
		String lessonPrice=request.getParameter("lessonPrice");
		String lessonDate=request.getParameter("lessonDate");
		Lesson lesson=new Lesson(lessonName, lessonDescription, lessonPrice, lessonDate, filename);
		addObj(lesson);
		
		request.getRequestDispatcher("add-lesson.jsp").forward(request, response);
		
	}
	
	private String getFilename(Part part) {
		String header=part.getHeader("Content-Disposition");
		String filename=header.substring(header.indexOf("filename=\"")+10, header.lastIndexOf("\""));
		return filename;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
