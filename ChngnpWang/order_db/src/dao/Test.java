package dao;

import java.sql.SQLException;
import java.util.List;

import model.Order;

public class Test {
public static void main(String[] args) throws SQLException {
	Dao dao=new Dao();
	List<Order> list=dao.select("3");
	for (Order order:list) {
		System.out.println(order);
	}
}
}
