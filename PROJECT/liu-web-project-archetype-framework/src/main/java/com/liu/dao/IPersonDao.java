package com.liu.dao;

import com.liu.pojo.Person;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 *
 * @author 刘老师
 *
 *         - 源码请所有同学合理使用，禁止非学习用途。 - 参照源码多想多练多Debug，杜绝无脑Copy！ -
 *         有问题找学委统一汇总，课堂答疑，也可到办公室问我。 - 小红书|微信视频号 @老刘编程 三连到位，禁止下次一定！
 *
 *         面向接口定义 DAO 的 abstract 方法 由 Impl 类实现这些方法是项目开发规范
 * @Autowired 声明注入时使用 IUserDao 接口类型 保证多态性并实现开发解耦
 *
 */
public interface IPersonDao extends IGeneralDao<Person> {

	public List<Person> queryList();

	public Page<Person> queryListPage(Pageable paging);

	default Person mapResult(final ResultSet rs) throws SQLException {

		Person o = new Person();
		
		o.setId(rs.getInt("id"));
		o.setJob(rs.getString("job"));
		o.setRealName(rs.getString("real_name"));
		o.setEmail(rs.getString("email"));
		o.setLocation(rs.getString("location"));
		o.setAvatar(rs.getString("avatar"));
		o.setTimestamp(rs.getTimestamp("timestamp"));
		o.setStatus(rs.getString("status"));

		return o;
	}

}
