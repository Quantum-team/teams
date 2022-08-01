package controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import dao.sourceMapper;
import model.source;

/**
 * Servlet implementation class queryAllServlet
 */
@WebServlet("/delServlet")
public class delServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private SqlSessionFactory sqlSessionFactory ;
	private SqlSession sqlSession;
	
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public delServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    public void init(){
    	try {
			InputStream resourceAsStream = Resources.getResourceAsStream("config/mybatis-config.xml");
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//设置编码
		request.setCharacterEncoding("UTF-8");
		response.setContentType("UTF-8");
		response.setContentType("text/html;UTF-8");
		
		//获取请求参数 --- id
        String id = request.getParameter("id");
		
		 sqlSession = sqlSessionFactory.openSession(true);
		 
		 sourceMapper mapper = sqlSession.getMapper(sourceMapper.class);
		 
		 int i = mapper.delById(Integer.parseInt(id));
		 
		 System.out.println(i);
		 
		 if (i>0) {
			 response.sendRedirect("queryAllServlet");
		}
		 
		 
		 sqlSession.close();
		
	}

}
