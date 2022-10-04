package com.liu.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Order;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.liu.dao.IPersonDao;
import com.liu.pojo.Person;

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
@Repository("personDao")
public class PersonDaoImpl implements IPersonDao {

    // 注入 jdbcTemplate
    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * 无条件查询结果集
     */
    public List<Person> queryList() {

        String sql =
                "select " +
                    "id, job, real_name, email, location, avatar, status, timestamp " +
                "from " + T_PERSON;

        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<Person>(Person.class));
    }
    
    public Integer count() {
    	
    	return jdbcTemplate.queryForObject("select count(0) c from " + T_PERSON, Integer.class);
    }
    
    /**
     * 带分页查询
     */
    public Page<Person> queryListPage(Pageable paging) {
    	
        Order order = 
        		!paging.getSort().isEmpty() ?				//
        				paging.getSort().toList().get(0) : 	//
    					Order.by(PRIMARY_KEY);				//
    	
    	String sqlPaging = 
    			null != paging ? //
    					"limit " + paging.getOffset() + "," + paging.getPageSize() : //
						"";
    	
//    	select id from t_person 
//    	where true 
//    	order by id asc
//    	limit 2,5
    	String sql =
    			"select " +
					"id, job, real_name, email, location, avatar, status, timestamp " +
				"from " + T_PERSON + " " +
				"where true " +
				"order by " + order.getProperty() + " " + order.getDirection().name() + " " +
				sqlPaging
				;
    	
    	List<Person> list = jdbcTemplate.query(sql, (rs, rowNum) -> mapResult(rs));
    	
//    	List<Person> list_ = jdbcTemplate.query(sql, (rs, rowNum) -> {
//    		
//    		Person o = new Person();
//    		
//    		o.setId(rs.getInt("id"));
//    		o.setJob(rs.getString("job"));
//    		o.setRealName(rs.getString("real_name"));
//    		o.setEmail(rs.getString("email"));
//    		o.setLocation(rs.getString("location"));
//    		o.setAvatar(rs.getString("avatar"));
//    		o.setTimestamp(rs.getTimestamp("timestamp"));
//    		o.setStatus(rs.getString("status"));
//    		
//    		return o;
//    	});
    	
    	
    	return new PageImpl<Person>(list, paging, this.count());
    }
    
    
//    private Person mapResult(final ResultSet rs) throws SQLException {
//
//		Person o = new Person();
//		
//		o.setId(rs.getInt("id"));
//		o.setJob(rs.getString("job"));
//		o.setRealName(rs.getString("real_name"));
//		o.setEmail(rs.getString("email"));
//		o.setLocation(rs.getString("location"));
//		o.setAvatar(rs.getString("avatar"));
//		o.setTimestamp(rs.getTimestamp("timestamp"));
//		o.setStatus(rs.getString("status"));
//
//		return o;
//	}
    
    
}
