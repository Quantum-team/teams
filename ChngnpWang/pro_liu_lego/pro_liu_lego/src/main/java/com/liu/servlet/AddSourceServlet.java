package com.liu.servlet;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.liu.dao.SourceDao;
import com.liu.pojo.Source;

/**
 * 
 * @author 刘老师
 * 
 * - 源码请所有同学合理使用，禁止非学习用途。
 * - 参照源码多想多练多Debug，杜绝无脑Copy！
 * - 有问题找学委统一汇总，课堂答疑，也可到办公室问我。
 * - 小红书|微信视频号 @老刘编程 三连到位，禁止下次一定！
 *
 */
@WebServlet("/add")
public class AddSourceServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");

		SourceDao dao = new SourceDao();
		
		String names = request.getParameter("names");
		String type = request.getParameter("type");
		String buyDate = request.getParameter("buyDate");
		String price = request.getParameter("price");

		Source srouce = new Source();
		srouce.setName(names);
		srouce.setType(type);
		srouce.setBuyDate(Date.valueOf(buyDate));
		srouce.setPrice(price);
		
		int result = 0;

		try {
			result = dao.add(srouce);
		} catch (SQLException e) {
			e.printStackTrace();
		} 

		if (result > 0) {
			response.sendRedirect("list");
		}
	}

}
