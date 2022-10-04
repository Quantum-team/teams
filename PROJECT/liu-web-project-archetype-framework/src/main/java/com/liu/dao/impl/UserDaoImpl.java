package com.liu.dao.impl;

import java.sql.Types;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.liu.dao.IUserDao;
import com.liu.pojo.User;

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

// 声明该 DAO 组件接受 Spring 托管，使用时注入
@Repository("userDao")
public class UserDaoImpl implements IUserDao {

	// 注入 jdbcTemplate
	@Autowired
	private JdbcTemplate jdbcTemplate;

	/**
	 * 无条件查询结果集
	 */
	public List<User> queryList() {

		String sql = "select id, username, password, type, status, timestamp from t_user";

		return jdbcTemplate.query(sql, new BeanPropertyRowMapper<User>(User.class));
	}


	/**
	 * 带条件查询对象，该查询仅用于特定登陆校验场景
	 *
	 * @param username
	 * @return
	 */
	public User queryByUsername(String username) {

		String sql = "select id, username, password, type, status from t_user where username = '" + username + "'";

		// 查询 count 无记录时该用户不存在，不必进行正式查询
		// 减少数据库压力
		if (0 == queryCountWhere("username='" + username + "'"))
			return null; // Controller 层进行 null 校验处理
		else {

			// TODO 注意 lambda 表达式 vs 匿名内部类写法

			// lambda
			return jdbcTemplate.queryForObject(sql, (rs, rowNum) -> this.mapResult(rs));

			// 匿名内部类
//				return		jdbcTemplate.queryForObject(sql, new RowMapper<User>() {
//			
//							@Override
//							public User mapRow(ResultSet rs, int rowNum) throws SQLException {
//			
//								User o = new User();
//								o.setId(rs.getInt("id"));
//								o.setUsername(username);
//								o.setPassword(rs.getString("password"));
//								o.setType(rs.getString("type"));
//								o.setStatus(rs.getString("status"));
//			
//								return o;
//							}
//						});
		}
	}

	/**
	 * 带条件查询对象
	 * @param id
	 * @return
	 */
	public User queryById(Integer id) {
		

		String sql = "select id, username, password, type, status from t_user where id = " + id;

		if (0 == queryCountWhere(null))
			return null;
		else
			return jdbcTemplate.queryForObject(sql, (rs, rowNum) -> this.mapResult(rs));
	}

	/**
	 * （可选）带条件查询 Count
	 * @param sqlConditionOption
	 * @return
	 */
	public Long queryCountWhere(String sqlConditionOption) {

		String sql = "select count(*) c from t_user ";

		if (null != sqlConditionOption && !sqlConditionOption.isEmpty())
			sql += "where " + sqlConditionOption;

		return (Long) jdbcTemplate.queryForMap(sql).get("c");
	}


	/**
	 * 新增注意 sql 不带 id
	 * @param o
	 * @return
	 */
	public int add(User o) {

		String sql = "insert into t_user(username, password, type, status) values(?, ?, ?, ?)";

		return jdbcTemplate.update(
				sql, 
				new Object[] { 			 // Integer[] 按 index 匹配列名
						o.getUsername(), //
						o.getPassword(), //
						o.getType(), 	 //
						o.getStatus() 	 //
				},
				new Integer[] { 		 // Integer[] 按 index 匹配 values 占位符
						Types.VARCHAR,	 // 注意使用数据库类型 String -> VARCHAR
						Types.VARCHAR,   // 注意使用数据库类型 String -> VARCHAR
						Types.VARCHAR,   // 注意使用数据库类型 String -> VARCHAR
						Types.VARCHAR    // 注意使用数据库类型 String -> VARCHAR
				});
	}
	

}
