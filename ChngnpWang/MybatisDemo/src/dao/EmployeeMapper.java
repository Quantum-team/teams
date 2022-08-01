package dao;
//定义增删查改方法

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import model.Employee;

public interface EmployeeMapper {
	//查全部
	@Select("select * from tb_employee")
	List<Employee> findAll();
	//根据id查
	@Select("select * from tb_employee where empId = #{id}")
	Employee findById(int id);
	@Insert("insert into tb_employee values(#{empId},#{empName},#{empSex},#{empPhone},#{empAddress})")
	int addEmp(Employee employee);
	@Delete("delete from tb_employee where empId=#{empId}")
	Employee deleteID(int id);
}
