package T9;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ExecuteStatement {
	//驱动类的类名
	private static final String DRIVERNAME="com.mysql.jdbc.Driver";
	//连接数据的URL路径
	private static final String URL="jdbc:mysql://localhost:3306/jw02";
	//数据库登录账号
	private static final String USER="root";
	//数据库登录密码
	private static final String PASSWORD="";
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
	//向数据库中插入数据
	public boolean inserData(Emp emp){
		Connection conn=null;
		PreparedStatement pStat=null;
		try {
			//定义SQL语句
			String sql=" insert into emp(no,name,birthday,address,post)"   
                       +" values(?,?,?,?,?)";
			conn=getConnection();//获取数据库连接
			//3.获取PraparedStatement对象
			pStat=conn.prepareStatement(sql);//预处理SQL语句
			//将占位符?使用预处理的方式替换成员工的账号，1表示第一个?占位符
			pStat.setString(1, emp.getNo());
			pStat.setString(2, emp.getName());
			pStat.setString(3, emp.getBirthday());
			pStat.setString(4, emp.getAddress());
			pStat.setString(5, emp.getPost());
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
	
	
	
	//修改操作
	public  boolean  updateData(Emp emp){
		Connection conn=null;
		PreparedStatement pStat=null;
		try {
			//定义SQL语句
			String sql=" update emp set name=?,birthday=?,address=?,post=?"   
	                   +" where no=?";
			conn=getConnection();//获取数据库连接
			//获取PraparedStatement对象
			pStat=conn.prepareStatement(sql);//预处理SQL语句
			//将占位符?使用预处理的方式替换成员工的账号，1表示第一个?占位符
			pStat.setString(1, emp.getName());
			pStat.setString(2, emp.getBirthday());
			pStat.setString(3, emp.getAddress());
			pStat.setString(4, emp.getPost());
			pStat.setString(5, emp.getNo());
			//执行sql语句
			return pStat.executeUpdate()>0?true:false;
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			//关闭相关资源
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
	//根据用户编号，删除员工信息
	public boolean deleteByNo(String no) {
		Connection conn = null;
		PreparedStatement ps = null;
		String sql = "delete from emp where no = '" + no +"'";
		try {
			conn = getConnection();
			ps = conn.prepareStatement(sql);
			int isDelete = ps.executeUpdate();
			if(isDelete >0) {
				//删除成功
				return true;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	public List<Emp> queryEmp(){
		Connection conn=null;
		PreparedStatement pStat=null;
		ResultSet rs=null;
		try {
			String sql="select * from emp";
			conn=getConnection();
			pStat=conn.prepareStatement(sql);
			rs=pStat.executeQuery();//返回ResultSet实例
			ArrayList<Emp> empList=new ArrayList<Emp>();
			//判断结果集中是否还有数据
			while(rs.next()){
			   //通过列名为no获取对应的列值
			   String no=rs.getString("no");
               //通过列名为name获取对应的列值
			   String name=rs.getString("name");
              //通过列名为birthday获取对应的列值
			   String birthday=rs.getString("birthday");
              //通过列名为address获取对应的列值
			   String address=rs.getString("address");
              //通过列名为post获取对应的列值
			   String post=rs.getString("post");
			   Emp emp=new Emp();
              //将从ResultSet结果集获取到信息封装至emp对象中
			   emp.setNo(no);
			   emp.setName(name);
			   emp.setBirthday(birthday);
			   emp.setAddress(address);
			   emp.setPost(post);
			   empList.add(emp);
			}
			return empList;
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				DbConnection.colse(rs, pStat, conn);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
	public boolean queryIsEmp(String empNo){
		Connection conn=null;
		PreparedStatement pStat=null;
		ResultSet rs=null;
		try {
			String sql="select * from emp where no = '"+empNo+"'";
			conn=getConnection();
			pStat=conn.prepareStatement(sql);
			rs=pStat.executeQuery();//返回ResultSet实例
			//判断结果集中是否还有数据
			while(rs.next()){
			   return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				DbConnection.colse(rs, pStat, conn);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return false;
	}
	
	
	
	public static void main(String[] args) {
//		Emp emp=new Emp();
//		emp.setNo("emp_001");
//		emp.setName("马化腾");
//		emp.setBirthday("1971/10/29");
//		emp.setAddress("广东省深圳市");
//		emp.setPost("首席执行官");
//		ExecuteStatement ex=new ExecuteStatement();
//		boolean flag=ex.inserData(emp);
//		if(flag){
//			System.out.println("员工信息添加成功:");
//			System.out.println(" "+emp);
//		}else{
//			System.out.println("添加员工信息失败");
//		}
		
		
//		Emp emp=new Emp();
//		emp.setNo("emp_001");
//		emp.setName("马化腾");
//		emp.setBirthday("1971/10/29");
//		emp.setAddress("广东省广州市");
//		emp.setPost("退居二线");
//		ExecuteStatement ex=new ExecuteStatement();
//		boolean flag=ex.updateData(emp);
//		if(flag){
//			System.out.println("员工信息修改成功:");
//			System.out.println(" "+emp);
//		}else{
//			System.out.println("修改员工信息失败");
//		}
		
		
		ExecuteStatement es=new ExecuteStatement();
		List<Emp> empList=es.queryEmp();
		System.out.println("序号      员工工号       员工姓名       出生日期               家庭地址           所在职务");
		for(int i=0;i<empList.size();i++){
			Emp emp=empList.get(i);
			System.out.println((i+1)+"  "+emp.getNo()+"   "+emp.getName()+"   "+emp.getBirthday()+"  "+emp.getAddress()+"  "+emp.getPost());
		}
	}

}
