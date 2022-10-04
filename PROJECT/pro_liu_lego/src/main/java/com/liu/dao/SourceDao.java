package com.liu.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.liu.pojo.Source;
import com.liu.util.JDBCUtil;

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
public class SourceDao {

	// 查询
	public List<Source> queryList(String keyword) throws SQLException {
		String sql = "select id, name, type, buyDate, price, imageUrl, status from tb_source";

		if (keyword != null && !keyword.trim().isEmpty()) {
			sql += " where name like '%" + keyword + "%'";
		}

		Connection connection = JDBCUtil.getConnection();
		PreparedStatement ps = connection.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();

		ArrayList<Source> listSource = new ArrayList<Source>();
		while (rs.next()) {
			Source source = new Source();
			source.setId(rs.getInt("id"));
			source.setName(rs.getString("name"));
			source.setType(rs.getString("type"));
			source.setBuyDate(rs.getDate("buyDate"));
			source.setPrice(rs.getString("price"));
			source.setImageUrl(rs.getString("imageUrl"));
			source.setStatus(rs.getString("status"));
			listSource.add(source);
		}

		connection.close();
		return listSource;
	}

	// 添加
	public int add(Source source) throws SQLException {
		String sql = "insert into tb_source(name, type, buyDate, price) values(?, ?, ?, ?)";
		Connection connection = JDBCUtil.getConnection();
		PreparedStatement ps = connection.prepareStatement(sql);
		ps.setString(1, source.getName());
		ps.setString(2, source.getType());
		ps.setDate(3, source.getBuyDate());
		ps.setString(4, source.getPrice());

		int result = ps.executeUpdate();
		connection.close();

		return result;
	}

	// 删除
	public int deleteById(Integer id) throws SQLException {
		String sql = "delete from tb_source where id = " + id;
		Connection connection = JDBCUtil.getConnection();
		PreparedStatement ps = connection.prepareStatement(sql);
		int result = ps.executeUpdate();
		connection.close();

		return result;
	}
	
}
