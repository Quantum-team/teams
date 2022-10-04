package com.liu.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

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
 * 面向接口定义 DAO 的 abstract 方法
 * 由 Impl 类实现这些方法是项目开发规范
 * @Autowired 声明注入时使用 IUserDao 接口类型
 * 保证多态性并实现开发解耦
 *
 */
public interface IUserDao extends IGeneralDao<User> {

	public List<User> queryList();

    public User queryByUsername(String username);

    public User queryById(Integer id);

    public Long queryCountWhere(String sqlCondition);

    public int add(User o);
    
	default User mapResult(final ResultSet rs) throws SQLException {
		
		User o = new User();
		
		o.setId(rs.getInt("id"));
		o.setUsername(rs.getString("username"));
		o.setPassword(rs.getString("password"));
		o.setType(rs.getString("type"));
		o.setStatus(rs.getString("status"));
		
		return o;
	}
    
}
