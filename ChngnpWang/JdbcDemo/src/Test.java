import java.util.List;

import dao.UserDao;
import entity.User;

/*
 * �������ݿ��������
 * */
public class Test {
	public static void main(String[] args) {
//		��ѯ����
		/*
		UserDao userDao = new UserDao();
		List<User> user = userDao.queryAll();
		System.out.println(user);*/
//		ɾ��
		/*UserDao userDao = new UserDao();
		boolean i = userDao.deleteById(5);
		System.out.println(i);*/
//		���
		/*
		User user = new User();
		user.setId(5);
		user.setName("����");
		user.setAge(18);
		user.setEmail("xxxmail");
		
		UserDao userDao = new UserDao();
		boolean i = userDao.addUser(user);
		System.out.println(i);*/
//		�޸�
		User user = new User();
		user.setId(5);
		user.setName("����1");
		user.setAge(18);
		user.setEmail("xxxmail");
		
		UserDao userDao = new UserDao();
		boolean i = userDao.updateById(user);
		System.out.println(i);
		
	}
}
