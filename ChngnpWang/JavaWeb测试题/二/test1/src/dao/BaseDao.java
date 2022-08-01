package dao;
/*
 * 数据库的操作类，查询和删除
 * */

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.sun.org.apache.regexp.internal.recompile;

import entity.teacher;

public class BaseDao {
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	//查询
	public ArrayList<teacher> query(){
	      try {
	    	  Connection conn = null;
		      PreparedStatement pstmt = null;
		      ResultSet rs = null;
		      conn = DBconnection.getConnection();
		      String sql = "select * from teacher";
		      pstmt = conn.prepareStatement(sql);
		      rs =pstmt.executeQuery();
		      ArrayList<teacher> list = new ArrayList<teacher>();
		      while(rs.next()){
		    	  int id = rs.getInt("id");
		    	  String name = rs.getString("name");
		    	  String sex = rs.getString("sex");
		    	  int age = rs.getInt("age");
		    	  String position = rs.getString("position");
		    	  teacher teacher= new teacher(id,name,sex,age,position);
		    	  list.add(teacher);
		      }
		      return list;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
//			关闭
			DBconnection.close(conn, pstmt, rs);
		}
		return null;
	}
	
	public static void main(String[] args) {
		
	}
	
	//删除
	public boolean delteacher(int id){
        DBconnection dc = new DBconnection();
    try {
        conn=dc.getConnection();
        String sql = "delete from teacher where id =?";
        pstmt=conn.prepareStatement(sql);
        pstmt.setInt(1,id);
        int i = pstmt.executeUpdate();
//        处理返回值
        if (i>0){
            return true;
        }else {
            return false;
        }
    } catch (SQLException throwables) {
        throwables.printStackTrace();
    }finally {
        dc.close(conn,pstmt,rs);
    }
  return false;
}
	
	
}
