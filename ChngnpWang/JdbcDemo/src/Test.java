import java.util.List;

import dao.UserDao;
import entity.User;

/*
 * 测试数据库操作方法
 * */
public class Test {
	public static void main(String[] args) {
//		查询所有
		/*
		UserDao userDao = new UserDao();
		List<User> user = userDao.queryAll();
		System.out.println(user);*/
//		删除
		/*UserDao userDao = new UserDao();
		boolean i = userDao.deleteById(5);
		System.out.println(i);*/
//		添加
		/*
		User user = new User();
		user.setId(5);
		user.setName("测试");
		user.setAge(18);
		user.setEmail("xxxmail");
		
		UserDao userDao = new UserDao();
		boolean i = userDao.addUser(user);
		System.out.println(i);*/
//		修改
		User user = new User();
		user.setId(5);
		user.setName("测试1");
		user.setAge(18);
		user.setEmail("xxxmail");
		
		UserDao userDao = new UserDao();
		boolean i = userDao.updateById(user);
		System.out.println(i);
		
	}
}
