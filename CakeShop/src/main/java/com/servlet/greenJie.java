package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import ecpay.payment.integration.AllInOne;
import ecpay.payment.integration.domain.AioCheckOutALL;
/**
 * Servlet implementation class greenJie
 */
@WebServlet("/greenJie.do")
public class greenJie extends HttpServlet {
	private static final long serialVersionUID = 1L;
    int i=1090022;
    public static AllInOne all;
	AioCheckOutALL obj = new AioCheckOutALL();
    public greenJie() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        Date a=new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        String dateString = sdf.format(a);
        all=new AllInOne("");
		obj.setMerchantTradeNo("GJunBakery"+i);i++;
		obj.setMerchantTradeDate(dateString);
		obj.setTotalAmount(request.getParameter("Sum"));
		obj.setTradeDesc(request.getParameter("TradeDesc"));
		obj.setItemName(request.getParameter("ProductName"));
		obj.setReturnURL("http://211.23.128.214:5000");
		obj.setNeedExtraPaidInfo("N");
		String form = all.aioCheckOut(obj, null);
		out.println(form);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
