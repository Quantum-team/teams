package project;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import T9.Emp;

public class ExecuteStatement {
	//驱动类的类名
	private static final String DRIVERNAME="com.mysql.jdbc.Driver";
	//连接数据的URL路径
	private static final String URL="jdbc:mysql://localhost:3306/db_Storage";
	//数据库登录账号
	private static final String USER="root";
	//数据库登录密码
	private static final String PASSWORD="123456";
	//1.加载驱动，驱动仅需加载一次即可
	static{
		try {
			Class.forName(DRIVERNAME);
		} catch (ClassNotFoundException e) {
		    e.printStackTrace();
		}
	}
	//2.获取数据库连接
	public static Connection getConnection() throws Exception  {
		try {
			return DriverManager.getConnection(URL, USER,PASSWORD);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new Exception();
		}
	}
	//插入数据
	public boolean insertData(goodsList gl){
		Connection conn=null;
		PreparedStatement pStat=null;
		try {
			//定义SQL语句
			String sql=" insert into tb_Store(storeId,storeName,unitPrice,storeNum,createDate)"   
                       +" values(null,?,?,?,?)";
			conn=getConnection();//获取数据库连接
			//3.获取PraparedStatement对象
			pStat=conn.prepareStatement(sql);
			//预处理SQL语句
			//将占位符?使用预处理的方式替换成员工的账号，1表示第一个?占位符
			pStat.setString(1, gl.getStoreId());
			pStat.setString(2, gl.getStoreName());
			pStat.setString(3, gl.getUnitPrice());
			pStat.setString(4, gl.getStoreNum());
			pStat.setString(5, gl.getCreateDate());
			//4.执行sql语句
			return pStat.executeUpdate()>0?true:false;
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			//5.关闭相关资源
			try {
				if(pStat!=null){
					pStat.close();
				}if(conn!=null){
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return false;
	}
	//查询信息
	public boolean selectData() {
		Connection conn=null;
		PreparedStatement pStat=null;
		String sql="";
		return false;
	}
}
