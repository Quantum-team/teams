package project.dao;

import java.awt.image.SampleModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import project.model.Pro;
import project.utils.BaseDao;

public class ProDao {
	Connection con=null;
	PreparedStatement smt=null;
	ResultSet rs=null;
	
	public List<Pro> select() throws SQLException{
		con=BaseDao.getConn();
		String sql="select * from projec_db";
		smt=con.prepareStatement(sql);
		rs=smt.executeQuery();
		List<Pro> list=new ArrayList<Pro>();
		if (rs!=null) {
			while (rs.next()) {
			Pro p=new Pro();
			p.setPrj_id(rs.getInt("prj_id"));
			p.setPrj_name(rs.getString("prj_name"));
			p.setPrj_cycle(rs.getInt("prj_cycle"));
			p.setPrj_cycle(rs.getInt("prj_cycle"));
			p.setPrj_count(rs.getInt("prj_count"));
			p.setPrj_manager(rs.getString("prj_manager"));
			list.add(p);
		}
		return list;
		}else {
			BaseDao.closse(con, smt, rs);
		}
		return null;
	}
	
	public int add(Pro pro) throws SQLException {
		con=BaseDao.getConn();
		String sql="insert into projec_db valuse(0,?,?,?,?)";
		try {
		smt =con.prepareStatement(sql);
		smt.setString(1, pro.getPrj_name());
		smt.setInt(2, pro.getPrj_cycle());
		smt.setInt(3, pro.getPrj_count());
		smt.setString(4, pro.getPrj_manager());
		return smt.executeUpdate();
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			BaseDao.closse(con, smt, rs);
		}
		return 0;
	}
	
	public int delect(int id) throws SQLException {
		con=BaseDao.getConn();
		try {
			String sql="delete from projec_db where prj_id=?";
			smt=con.prepareStatement(sql);
			smt.setInt(1, id);
			return smt.executeUpdate();
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			BaseDao.closse(con, smt, rs);
		}
		return 0;
	}
}
