package com.liu.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

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
@MultipartConfig(//
		fileSizeThreshold 	= 1024 * 1024 * 1, 	// 1 MB
		maxFileSize	 		= 1024 * 1024 * 50, // 50 MB
		maxRequestSize 		= 1024 * 1024 * 100 // 100 MB
)
@WebServlet("/upload")
public class FileUploadServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	/**
	 * 使用 Windows 的同学注意变更你的本机实际 Tomcat 部署 WebAPP 的地址！
	 */
	private final static String WEB_APP_HOME_OF_TOMCAT = "/Applications/apache-tomcat-9.0.60/wtpwebapps";

	public void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		// 设置字符编码
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");

		Part filePart = request.getPart("file");
		String fileName = filePart.getSubmittedFileName();
		
		if(!fileName.isEmpty()) {
			for (Part part : request.getParts()) {
				
				String path = WEB_APP_HOME_OF_TOMCAT + request.getContextPath()+ "/upload/";
				
				part.write(path + System.currentTimeMillis() + "_" + fileName);
			}
			
			request.setAttribute("message", "文件上传成功！");
			
		} else {
			request.setAttribute("message", "文件上传失败！");
		}
		
		request.getRequestDispatcher("/WEB-INF/message.jsp").forward(request, response);
	}
}
