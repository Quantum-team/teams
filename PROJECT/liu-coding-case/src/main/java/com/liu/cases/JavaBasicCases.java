package com.liu.cases;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * 编程案例源码 
 * Java 基础篇
 * @老刘编程 点赞关注私信获取源码
 *
 */
public class JavaBasicCases { }

class HelloWorldCase { // 类体

	// 单行注释
	/*
	 * 多行注释
	 */
	
	/*
	 * - main方法是所有Java程序的入口，相当于汽车的启动按钮
	 * - 一个类里有且仅有一个main方法
	 */
	// 修饰符 关键字 返回类型 方法名 参数类型 参数
	public static void main(String[] args) { // 方法体

		// 使用程序打印输出方法
		System.out.println("Hello world! ");
	}
}

/**
 * 声明类时需要注意
 * - 一个源文件中只能有一个 public 类
 * - 一个源文件可以有多个非 public 类
 * - 源文件的名称应该和 public 类的类名保持一致
 * class Puppy 与 Puppy.java
 * 
 * @author Matthew
 */
class Puppy {

	private int age;

	private String name;

	/*
	 * 不用定义，Java自带，写出来仅作为演示
	 * 构造方法，无参数
	 */
	public Puppy() {

	}

	/**
	 * 按需定义自己想要的构造方法
	 * 构造方法，带一个参数
	 * 
	 * @param name
	 */
	public Puppy(String name) {
		this.name = name;
	}

	public static void main(String[] args) {

		/*
		 * 创建两个对象 设置属性使用setter
		 * 注意两个puppy对象的构造过程对比
		 */
		Puppy puppy1 = new Puppy();
//		puppy1.setName("Bob");
		puppy1.setAge(1);
		// 设置属性使用getter
		System.out.println("puppy1: " + puppy1.getName() + " " + puppy1.getAge());

		Puppy puppy2 = new Puppy("Tom");
		puppy2.setAge(8);
		System.out.println("puppy2: " + puppy2.getName() + " " + puppy2.getAge());

	}


	/*
	 * 私有属性的外部访问方法 
	 */
	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}

/**
 * 变量只会出现在两个地方
 * 类体，方法体
 * 
 * 变量初始化
 * 
 * @author Matthew
 *
 */
class VariableCase {

	/*
	 * - 实例变量在方法体之外的非static变量
	 * 
	 * 声明变量
	 * - 声明时可以不初始化
	 * - 不初始化的变量值为该类型的默认值（不建议）
	 * - 在方法体中初始化
	 */
	int a, b, c; 
	
	/*
	 * 声明变量
	 * 声明时可以同时初始化
	 */
	int d = 3, 
		e = 4, 
		f = 5;

	/*
	 * 静态变量/类变量
	 * 
	 * - 在规范中需要声明变量的同时初始化
	 * - static变量可以改变，但是通常初始化之后不变
	 * - static变量在作用域内仅保持一份，节省空间
	 */
	static int staticVariable = 0;

	/*
	 * 静态常量
	 * - 被定义后任何地方不允许修改
	 * - 比static变量更严格
	 * - 声明通常使用全大写命名
	 * - 常用于表示恒常不变的变量、数字或运算符号
	 */
	final static double CONSTANT_PI = 3.14D;
	
	
	/*
	 * - 外部变量与方法内部同名变量不冲突
	 * - 使用this引用外部变量
	 * - 为便于代码阅读，通常不使用同名变量
	 */
	int i_, j_ = 1;
	
	public void method() {
		
		// 测试常量不可变更
//				CONSTANT_PI = 3.1;
		
		/*
		 * - 局部变量，定义在方法体中
		 */
		int i = 2;
		int j;
		j = 2;
		
		System.out.println("method 方法外的变量，赋值前：this.i_ = " + this.i_ + ", this.j_ = " + this.j_);
		
		int i_ = 6;
		int j_ = 6;
		
		this.i_ = i_;
		this.j_ = j_;
		
		System.out.println("method 方法外的变量，赋值后：this.i_ = " + this.i_ + ", this.j_ = " + this.j_);
		System.out.println("method 方法体内的局部变量：i_ = " + i_ + ", j_ = " + j_);
		System.out.println("method 方法体内的局部变量：i = " + i + ", j = " + j);
		
	}


	public static void main(String[] args) {
		VariableCase caz = new VariableCase();
		
		/*
		 * 调用方法
		 * 使用对象的引用调用方法
		 */
		caz.method();
		System.out.println();
		
		/*
		 * 静态变量
		 * - 静态常量可直接调用，该类域内
		 * - 或者用类名调用，其他类中
		 * - 调用不需要用对象
		 */
		System.out.println("静态变量：staticVariable = " + staticVariable);
		System.out.println("静态变量：VariableCase.staticVariable = " + VariableCase.staticVariable);
		System.out.println();
		
		System.out.println("静态常量：CONSTANT_PI = " + CONSTANT_PI);
		System.out.println("静态常量：VariableCase.CONSTANT_PI = " + VariableCase.CONSTANT_PI);
	}
}

/**
 * 基本类型转换
 * 
 * @author Matthew
 *
 */
class TypeExchangeCase {

	public static void main(String[] args) {

		/*
		 * 自动类型转换 
		 * 低类型向高类型自动转换，不需要声明
		 */
		char c1 = 'A';// 定义一个char类型
		int i1 = c1;// char自动类型转换为int
		System.out.println("自动转换的情况：");
		System.out.println("char c1被自动转换为int：" + i1);

		char c2 = 'A';// 定义一个char类型
		int i2 = c2 + 1;// char 类型和 int 类型计算
		System.out.println("char c2被自动转换为int：" + i2);
		System.out.println();
		
		/*
		 * 不能转换的情况 
		 * boolean 类型不能转换 
		 * 在面向对象中，不相关类型不能转换
		 */
		boolean /* int */ b1 = true;
		System.out.println("boolean值b1 = " + b1);
		System.out.println();

		/*
		 * 高类型向低类型转换的情况 
		 * 注意可能溢出（超出范围）或损失精度
		 */
		int i3; // 未赋值int默认值是0
		long l3 = 100L; // 整数的默认类型是int，使用L对long定义进行显式声明，float F，double D
		i3 = 1; // 赋值i3
		System.out.println("强制转换的情况：");
		System.out.println("变量i3 = " + i3);
		System.out.println("变量l3 = " + l3);

		long l4 = i3 + l3; // 结果默认为高类型，不需转换
		int i4 = (int) (i3 + l3); // 结果需要为低类型，强制转换
		System.out.println("变量l4 = " + l4);
		System.out.println("变量i4 = " + i4);
		System.out.println();
		
		/*
		 * 高类型向低类型转换结果溢出的情况
		 */
		long l5 = 100000000000000L;
		int i5 = (int) (i3 * l5); 
		byte b2 = (byte) 302;
		System.out.println("高类型向低类型转换结果溢出的情况：");
		System.out.println("变量i5 = " + i5 + "，结果溢出，数值无意义。");
		System.out.println("变量b1 = " + b2 + "，结果溢出，数值无意义。");
		System.out.println();
		
		double d1 = 12.12345D;
		int i6 = (int) d1;
		System.out.println("高类型向低类型转换损失精度的情况");
		System.out.println("变量i6 = " + i6 + "，结果损失了精度。");
		
	}
}

/**
 * 演示8种基本数据类型 
 * 数值类型：byte short int long float double 
 * 布尔类型：boolean 
 * 字符类型：char
 * 
 * @author Matthew
 *
 */
class PrimitiveTypeCase {
	public static void main(String[] args) {
		
		/*
		 * 分别定义8不同的数据类型
		 */
		byte 	b1 = 1;
		short 	s1 = 1;
		int 	i1 = 1;
		long 	l1 = 1L;
		float	f1 = 1.0F;
		double	d1 = 1.0D;
		char	c1 = 'A';
		boolean bl = true; // false
		
		System.out.println(b1);
		System.out.println(s1);
		System.out.println(i1);
		System.out.println(l1);
		System.out.println(f1);
		System.out.println(d1);
		System.out.println(c1);
		System.out.println(bl);
		System.out.println();
		
		/*
		 * 定义整数值 8位 byte 长度只有 1/4 int
		 */
		System.out.println("基本类型：byte 二进制位数：" + Byte.SIZE);
		System.out.println("包装类：java.lang.Byte");
		System.out.println("最小值：Byte.MIN_VALUE=" + Byte.MIN_VALUE);
		System.out.println("最大值：Byte.MAX_VALUE=" + Byte.MAX_VALUE);
		System.out.println();

		/*
		 * 定义整数值 16位 short 长度只有 1/4 int
		 */
		System.out.println("基本类型：short 二进制位数：" + Short.SIZE);
		System.out.println("最小值：Short.MIN_VALUE=" + Short.MIN_VALUE);
		System.out.println("最大值：Short.MAX_VALUE=" + Short.MAX_VALUE);
		System.out.println();

		/*
		 * 定义整数值 32位 int
		 */
		System.out.println("基本类型：int 二进制位数：" + Integer.SIZE);
		System.out.println("最小值：Integer.MIN_VALUE=" + Integer.MIN_VALUE);
		System.out.println("最大值：Integer.MAX_VALUE=" + Integer.MAX_VALUE);
		System.out.println();

		/*
		 * 定义整数值 64位 long
		 */
		System.out.println("基本类型：long 二进制位数：" + Long.SIZE);
		System.out.println("最小值：Long.MIN_VALUE=" + Long.MIN_VALUE);
		System.out.println("最大值：Long.MAX_VALUE=" + Long.MAX_VALUE);
		System.out.println();

		/*
		 * 定义单精度浮点型 32位 float
		 */
		System.out.println("基本类型：float 二进制位数：" + Float.SIZE);
		System.out.println("包装类：java.lang.Float");
		System.out.println("最小值：Float.MIN_VALUE=" + Float.MIN_VALUE);
		System.out.println("最大值：Float.MAX_VALUE=" + Float.MAX_VALUE);
		System.out.println();

		/*
		 * 定义双精度浮点型 64位 double
		 */
		System.out.println("基本类型：double 二进制位数：" + Double.SIZE);
		System.out.println("包装类：java.lang.Double");
		System.out.println("最小值：Double.MIN_VALUE=" + Double.MIN_VALUE);
		System.out.println("最大值：Double.MAX_VALUE=" + Double.MAX_VALUE);
		System.out.println();

		/*
		 * 定义布尔类型 true false boolean
		 */
		System.out.println("包装类：java.lang.Boolean");
		System.out.println("布尔类型：boolean " + (1 > 2));
		System.out.println("布尔类型：boolean " + (1 < 2));
		System.out.println();

		/*
		 * 定义char字符类型 char
		 */
		System.out.println("基本类型：char 二进制位数：" + Character.SIZE);
		System.out.println("包装类：java.lang.Character");
		char char_1 = 'A';
//		 char char_2 = 'AA';
		System.out.println("char_1 =" + char_1);
	}
}

/**
 * 运算符
 * 1）
 * 常用赋值*5：= += -= /= %=
 * 关系*6：== != > < >= <=
 * 
 * 2）
 * 逻辑*3：&& || !
 * 算术*7：+ - * / % ++ --
 * 
 * @author Matthew
 *
 */
class OperatingCharacter {

	public static void main(String[] args) {
		
		/*
		 * 常用赋值运算*5
		 * = += -= /= %=
		 * 
		 * x _= y <=> (x) = x _ y 
		 */
		int f_a = 1, // 变量 = 值
			f_b = 2,
			f_c = 0;
		
		System.out.println("f_a = " + f_a);
		System.out.println("f_b = " + f_b);
		System.out.println("f_c = " + f_c);
		
		/*
		 * 普通赋值
		 */
		f_c = f_a + f_b;
		System.out.println("f_c = f_a + b = " + f_c);
		
		/*
		 * f_c += f_a
		 * f_c = f_c + f_a
		 */
		f_c = 0; // 将f_c置0
		System.out.println("f_c += f_a = " + (f_c += f_a));
		
		f_c = 0;
		System.out.println("f_c -= f_a = " + (f_c -= f_a));
		
		f_c = 0;
		System.out.println("f_c *= f_a = " + (f_c *= f_a));
	
		f_c = 0;
		System.out.println("f_c /= f_a = " + (f_c /= f_a));
		
		f_c = 0;
		System.out.println("f_c %= f_a = " + (f_c %= f_a));
		
		System.out.println();
		/*
		 * 算术运算
		 * + - * / % ++ --
		 * 
		 * *注意
		 * / 除法运算用 int 赋值时，仅保留整数部分
		 * % 取模运算，相当于取余
		 */
		int a = 10;
		int b = 20;
		int c = 25;
		int d = 25;
		
		System.out.println("a = " + a);
		System.out.println("b = " + b);
		System.out.println("c = " + c);
		System.out.println("d = " + d);
		System.out.println("a + b = " + (a + b));
		System.out.println("a - b = " + (a - b));
		System.out.println("a * b = " + (a * b));
		System.out.println("b / a = " + (b / a));
		System.out.println("b % a = " + (b % a));
		System.out.println("c % a = " + (c % a));

		System.out.println();
		
		/*
		 * 自增/减运算
		 * 前缀自增/减法 ++a/--a: 先进行自增/减运算，再进行表达式运算
		 * 后缀自增/减法 a++/a--: 先进行表达式运算，再进行自增/减运算
		 */
		int i 		= 1,
			j 		= 1,
			plus_i 	= ++i,
			j_plus 	= j++;
		
		/*
		 * i = 1
		 * plus_i(++i) = 2
		 * i = 2
		 * 2 * plus_i = 4
		 */
		System.out.println("plus_i, ++i = " + plus_i + ", 2 * ++i = " + 2 * plus_i);
		
		/*
		 * j = 1
		 * j_plust(j++) = 1
		 * j = 2
		 * 2 * j_plust = 2
		 */
		System.out.println("j_plus, j++ = " + j_plus + ", 2 * j++ = " + 2 * j_plus);
		System.out.println();
		
		/*
		 * 小作业
		 * - 自主编写小案例，深入理解不同的赋值运算符
		 * - 自增/自减运算符，掌握 ++ / -- 过程中的赋值细节
		 */
		
		/*
		 * 关系运算*6
		 * == != > < >= <=
		 */
		 int e = 10;
	     int f = 20;
	     System.out.println("e == f = " + (e == f));
	     System.out.println("e != f = " + (e != f));
	     System.out.println("e > f = " + (e > f));
	     System.out.println("e < f = " + (e < f));
	     System.out.println("f >= e = " + (f >= e));
	     System.out.println("f <= e = " + (f <= e));
	     System.out.println();
	     
	    /*
	 	 * 逻辑运算
	 	 * 与 或 非
	 	 * & | !
	 	 */
	 	
		boolean T = true;
		boolean F = false;
		
		/*
		 * 逻辑与 &
		 * 同真则真，有假则假
		 */
		System.out.println("T & T = " + (T & T));
		System.out.println("T & F = " + (T & F));
		System.out.println("F & F = " + (F & F));
		
		/*
		 * 逻辑或 |
		 * 同假则假，有真则真
		 */
		System.out.println("T | F = " + (T | F));
		System.out.println("T | T = " + (T | T));
		System.out.println("F | F = " + (F | F));
		
		/*
		 * 逻辑非 !
		 * 非假则真
		 * 非真则假
		 */
		System.out.println("!T = " + !T);
		System.out.println("!F = " + !F);
		System.out.println();
		
		/*
		 * 短路逻辑的情况
		 * & &&
		 * | ||
		 */
		int g = 5;
		int h = 5;
		boolean b1 = (g < 4) & (g++ < 10);
		boolean b2 = (h < 4) && /* 短路 */ (h++ < 10);
		
		System.out.println("短路逻辑的情况");
		System.out.println("未使用短路逻辑运算，g = " + g);
		System.out.println("b1 = " + b1);
		System.out.println("使用短路逻辑运算，h = " + h);
		System.out.println("b2 = " + b2);
		System.out.println();

		/*
		 * 小作业
		 * - 自主编写小案例，深入理解 & | ! 在复合类型时的结果情况
		 * 比如 !(2 < 5 | 7 > 4) 的结果
		 * - 深入理解逻辑短路的情况
		 */
		
	}
}

/**
 * 几种常见的转义字符
 * 
 * @author Sasaki
 *
 */
class EscapeCharacterCase {
	
	public static void main(String[] args) {
		
		System.out.println("\\n 换行字符转义：A换行\nB");
		System.out.println("\\t Tab字符转义：ATab空格\tB");
		System.out.println("\\\" 双引号字符转义：\"");
		System.out.println("\\\' 双引号字符转义：\'");
		System.out.println("\\\\ 捺字符转义：\\");
		System.out.println("\"");
		System.out.println('\'');
	}
}

class EmployeeCase {

	public static void main(String[] args) {
		/* 使用构造器创建两个对象 */
		Employee employee_1 = new Employee("Tom");
		Employee employee_2 = new Employee("Bob");

		// 调用这两个对象的成员方法
		employee_1.setAge(26);
		employee_1.setDesignation("高级程序员");
		employee_1.setSalary(1000);
		employee_1.printEmployee();
		
		System.out.println();

		employee_2.setAge(21);
		employee_2.setDesignation("菜鸟程序员");
		employee_2.setSalary(500);
		employee_2.printEmployee();
		
	}
}

/**
 * 对Employee 员工类的创建
 * 属性和访问方法
 * 打印员工信息方法
 * 
 * @author Matthew
 */
class Employee {
	private String name;
	private int age;
	private String designation;
	private double salary;

	// Employee 类的构造方法
	public Employee(String name) {
		this.name = name;
	}

	/**
	 * 打印员工信息
	 */
	public void printEmployee() {
		System.out.println("名字:" + this.getName());
		System.out.println("年龄:" + this.getAge());
		System.out.println("职位:" + this.getDesignation());
		System.out.println("薪水:" + this.getSalary());
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

}

/**
 * 
 * 访问修饰符 访问修饰符控制package访问范围 
 * - public 所有包 
 * - protected 修饰变量、方法、接口，与OOP继承相关 
 * - [default] 同一包 
 * - private 当前类
 * 
 * 本节主要讨论public、default、private情况
 * 更多具体的情况在后续OOP的继承性中讨论
 * 
 * @author Matthew
 *
 */
class AccessCharacterCase {

	private 	int i_prviate;		// OOP建议private属性
	protected 	int i_protected;	// 通常与OOP的继承相关
	/*default*/	int i_default;		// 通常仅在本类中使用这个属性
	public 		int i_public; 		// 尽管没语法错误，OOP编程规范中很少使用public属性
	
	private		int i;				// OOP建议private属性

	public static void main(String[] args) {
		
		/*
		 * 当前类中可以自由访问本类的属性
		 */
		AccessCharacterCase ac = new AccessCharacterCase();
		System.out.println("AccessCharacter i_prviate = " + ac.i_prviate);
		System.out.println("AccessCharacter i_protected = " + ac.i_protected);
		System.out.println("AccessCharacter i_default = " + ac.i_default);
		System.out.println("AccessCharacter i_public = " + ac.i_public);
		
		System.out.println("AccessCharacter i = " + ac.i);
		System.out.println("AccessCharacter i by getter = " + ac.getI());
		
	}

	/*
	 * private属性的getter/setter
	 */
	public int getI() {
		return i;
	}

	public void setI(int i) {
		this.i = i;
	}
}

class SubAccessCharacterCase {
	
	public static void main(String[] args) {
		
		/*
		 * 在其他类中创建 AccessCharacter 对象
		 * 测试访问 AccessCharacter 属性的情况
		 */
		AccessCharacterCase ac = new AccessCharacterCase();
		
		/*
		 * 在其他类可以自由访问 AccessCharacterCase 的非private属性
		 * 一定程度上造成了不安全因素，OOP的编程规范是，设置为private属性
		 * 提供getter/setter方法供其他类访问，这体现了OOP的封装性
		 * 
		 * 后续在OOP的继承性中，讨论protected、default的更多情况
		 */
		
//		System.out.println("SubAccessCharacter i_prviate = " + ac.i_prviate);
		System.out.println("SubAccessCharacter i_protected = " + ac.i_protected);
		System.out.println("SubAccessCharacter i_default = " + ac.i_default);
		System.out.println("SubAccessCharacter i_public = " + ac.i_public);
//		System.out.println("SubAccessCharacter i = " + ac.i);
		System.out.println("SubAccessCharacter i_getter = " + ac.getI());
		
	}
}

/**
 * 非访问修饰符
 * static	静态的
 * final	最终的
 * synchronized 同步的
 * 
 * 设计模式中的经典：单例模式
 * 
 * @author Matthew
 *
 */
class SingleInstanceCase {
	
	/*
	 * 声明引用变量但不赋值
	 * 
	 * static 变量在类加载时被率先加载
	 * 对象在方法调用时获取，以提高效率
	 */
	private static SingleInstanceCase caz;
	
	/*
	 * final 必须在声明同时初始化单例对象
	 * 比起不使用 final 效率有所下降
	 */
	private final static SingleInstanceCase CAZ_FINAL = new SingleInstanceCase();
	
	
	/*
	 * 构造方法私有化，使得调用者不能自主创建对象
	 * 对象总是保持单例
	 */
	private SingleInstanceCase() {
		
	}
	
	
	/*
	 * 单例方法
	 * 注意 static 和 synchronized 用途
	 * 
	 * static 静态方法，程序中仅有一个副本，共享内存
	 * synchronized 将该方法加锁为同步模式，在多线程中实现安全，不会创建出多会对象
	 */
	public static synchronized SingleInstanceCase getInstance() {
		if (caz == null)
			caz = new SingleInstanceCase(); // 注意创建对象的时机，该对象有且仅有一份，称为“单例”

		return caz;
	}
	
	/*
	 * 方法体的目标方法
	 */
	public void targetMethod() {
		System.out.println("This is a method.");
	}


	public static void main(String[] arguments) {

		/*
		 * 类外部不再使用 new SingleInstanceCase(); 创建对象
		 * 构造方法被私有化
		 */
		SingleInstanceCase.getInstance().targetMethod();
	}
	
	/*
	 * static tips
	 * - 单例模式常用于工具类方法中，不使用时不加载
	 * - static 定义的数据单元仅有一份
	 * - static 不能引用 非static
	 * - 非static 可以引用 static （因为static会在程序初始化时优先加载内存）
	 * 
	 * 小作业 
	 * 手动编写单例模式案例，并体会使用final关键字的异同
	 * 自行查阅“懒汉单例”与“饿汉单例”并体会它们的区别
	 */
	
}

/**
 * 循环结构 
 * while 
 * do-while 
 * for
 * for( : ) 增强for
 * forEach	Java8
 * 
 * break
 * 
 * @author Matthew
 *
 */
class LoopCase {

	public static void main(String[] args) {

		/*
		 * while
		 * - 仅带条件
		 * - 先判断，再执行
		 */
		int i_1 = 0;
		while(i_1 <= 5) {
			System.out.println("while循环打印 i_1 = " + i_1++);
		}
		
		System.out.println();
		
		/*
		 * do-while
		 * - 即时循环条件不满足，至少执行一次循环体
		 * - 条件满足时，与while一致
		 */
		int i_2 = 0;
		do {
			System.out.println("do-while循环打印 i_2 = " + i_2++);
		} while (i_2 >= 5); // 不满足执行条件但至少打印一次先行步骤
		
		System.out.println();
		
		/*
		 * for
		 * 预先确定循环执行次数
		 */
		for (int i_3 = 0; i_3 <= 5; i_3++) {
			System.out.println("for循环打印 i_3 = " + i_3);
		}
		
		System.out.println();
		
		/*
		 * break
		 * 在给定条件下，控制跳出循环
		 */
		int j = 0;
		while (j <= 10) {
			System.out.println("while循环打印 j = " + j);
			j++;
			
			if (j == 3) { // break 经常搭配 if 条件使用
				System.out.println("j == " + j + ", 时，break");
				break;
			}
		}
		
		System.out.println();

		/*
		 * continue
		 * 在给定条件下，控制跳出循环一次，继续下一次循环
		 */
		int k = 0;
		while (k <= 10) {
			k++;

			if (k % 2 == 0) 
				continue;

			System.out.println("while循环打印 k = " + k);
		}
		
		System.out.println();
		
		/*
		 * Assignment
		 * - 自主编写案例，对比 break 与 continue 的细节差异
		 * - 将 while 案例使用 for 循环改写，实现同样的功能
		 */
		
		/*
		 * for ( : ) 
		 * - 增强for，JDK1.5后新增功能，常用于打印集合或数组
		 * - 不关注执行索引，只关注循环过程
		 */
		int[] array = { 0, 1, 2, 3, 4, 5 };
		
		for (int i_4 = 0; i_4 </* 注意条件是 < 不是 <= */ array.length; i_4++) {
			System.out.println("for循环打印 array[" + i_4 + "] = " + array[i_4]);
		}
		
		System.out.println();
		
		for (int array_i : array) {
			System.out.println("增强for循环打印 array_i = " + array_i);
		}
 		
		System.out.println();
		
		/*
		 * for-each
		 * JDK1.8新特性，与Lambda相关
		 * 
		 * 观察 for -> for( : ) -> for-each 的阶段变化
		 * 极大地简化代码量
		 * 
		 */
		List<String> list = new ArrayList<>();
		list.add("Tom");
		list.add("Jerry");
		list.add("Bob");
		
		// forEach方法与Lambda表达式
		list.forEach(i -> System.out.println(i));
		
		/*
		 * 小作业
		 * 使用不同循环模式体会他们的细微差异
		 */
	
	}
	
}


/**
 * 条件语句
 * if-(else if)-else
 * ? :
 * switch-case
 *
 * @author Matthew
 *
 */
class ConditionCase {

	public static void main(String[] args) {
		
		int i = 1;

		/*
		 * 仅if语句
		 */
		System.out.println("仅if语句的情况");
		if (i < 10) {
			System.out.println("i < 10 条件成立");
		}
		
		System.out.println();

		/*
		 * if-else语句
		 */
		System.out.println("if-else语句的情况");
		if (i < 10) {
			System.out.println("i = " + i + ", i < 10 条件成立");
		} else {
			System.out.println("i = " + i + ", i < 10 条件不成立");
		}
		
		System.out.println();
		
		/*
		 * if-else if-if语句
		 */
		System.out.println("if-else-if语句的情况");
		if (i < 10) {
			System.out.println("i = " + i + ", i < 10 条件成立");
		} else if(i == 10) {
			System.out.println("i = " + i + ", i = 10 条件不成立");
		} else {
			System.out.println("i = " + i + ", i < 10 | i = 10 条件不成立");
		}
		
		System.out.println();
		
		/*
		 * ? : 三元表达式
		 * - 等于简化 if-else
		 * - 注意 ? : 三元表达式有返回值，可以赋值
		 * - if-else是控制语句，不存在返回
		 */
		System.out.println((i < 10) ? "i < 10 条件成立" : "i < 10 条件不成立");
		
		System.out.println();
		
		// 对比j1和j2两种方式赋值
		boolean j1 = ((i < 10) ? true : false);
		boolean j2;
		if (i < 10) {
			j2 = true;
		} else {
			j2 = false;
		}
		
		System.out.println("j1 = " + j1 + ", j2 = " + j2);
		
		System.out.println();
		
		/*
		 * switch-case语句
		 * - 条件语句中效率最高
		 * - 特定情况下可以替代if-else
		 */
		char grade = 'C'; // 也可使用数值类型做匹配条件
		String gradeInfo = "";
		
		switch (grade) {
		case 'A':
			gradeInfo = "优";
			break;
		case 'B':
			gradeInfo = "良";
			break;
		case 'C':
			gradeInfo = "中";
			break;
		case 'D':
			gradeInfo = "及格";
			break;
		case 'E':
			gradeInfo = "不及格";
			break;
		default:
			gradeInfo = "未知";
		}
		System.out.println("最终等级是" + gradeInfo);
	}
}

/**
 * 包装类型*8
 * 
 * Boolean 	boolean
 * Byte 	byte
 * Short 	short
 * Integer 	int
 * Long 	long
 * Character char
 * Float	float
 * Double 	double
 * 
 * 基本数据类型的对象类型
 * 包装类型提供了系列常用的方法
 * 比如比较大小、计算、取最值等等
 * 空值是null，可以判定空
 * 
 * Number & Math类方法，常用操作数理计算
 * 
 * @author Matthew
 *
 */
class BoxedType {
	
	public static void main(String[] args) {
		
		/*
		 * 定义包装类
		 */
		Boolean objectBoolean 	= new Boolean(false);
		Byte objectByte 		= new Byte("11");
		Short objectShort 		= new Short("0");
		Integer objectInt 		= new Integer(0);
		Long objectLong 		= new Long(0L);
		Character objectChar 	= new Character('C');
		Float objectFloat 		= new Float(1.23F);
		Double objectDouble 	= new Double(12.345D);

		System.out.println("objectBoolean = " + objectBoolean);
		System.out.println("objectByte = " + objectByte);
		System.out.println("objectShort = " + objectShort);
		System.out.println("objectInt = " + objectInt);
		System.out.println("objectLong = " + objectLong);
		System.out.println("objectChar = " + objectChar);
		System.out.println("objectFloat = " + objectFloat);
		System.out.println("objectDouble = " + objectDouble);
		
		System.out.println();
		
		/*
		 * 默认 import java.lang.* 不需要声明
		 * java.lang.Number 
		 * java.lang.Math
		 * 
		 * 常用数理常用工具方法
		 */
		Integer i1 = new Integer(1);
		Integer i2 = 2; // 自动装箱，不需要转换
		Integer i3 = null;
		
		System.out.println("包装类可以为空值：" + i3);
		System.out.println();
		
		System.out.println("返回目标类型值：" + i1.doubleValue());
		System.out.println("与目标值比较大小：" + i2.compareTo(3)); // 结果判别 < -1; > 1; = 0
		System.out.println("与目标值比较是否相等：" + i1.equals(1));
		System.out.println("转化字符串类型：" + i1.toString()); // OOP中可重写toString方法实现自由打印输出
		System.out.println();
		
		System.out.println("求最小值：" + Math.min(i1, i2));
		System.out.println("求最大值：" + Math.max(i1, i2));
		System.out.println("取随机数：" + Math.random());// 0~1之间的随机数
		System.out.println("取绝对值：" + Math.abs(-12));
		System.out.println("取四舍五入：" + Math.round(123.52));
		System.out.println("90度的正弦值：" + Math.sin(Math.PI/2));  
        System.out.println("0度的余弦值：" + Math.cos(0));  
        System.out.println("60度的正切值：" + Math.tan(Math.PI/3));  
        System.out.println("1的反正切值： " + Math.atan(1));  
        System.out.println("π/2的角度值：" + Math.toDegrees(Math.PI/2));  
        System.out.println(Math.PI); 
		
	}
}

/**
 * 
 * 引用类型
 * 
 * - 一旦声明类型不可改变
 * - 对象、数组都是引用数据类型
 * - 所有引用类型的默认值都是null
 * - 一个引用变量可以用来引用任何与之兼容的类型
 * 
 * @author Matthew
 *
 */
class ReferenceTypeCase {
	
	public static void main(String[] args) {

		// 直接创建
		String string_1 = ""; 
		
		// 使用构造方法创建
		String string_2 = new String(""); 
		
		// 引用类型的默认值是null
		String string_3 = null;
		
		// 不初始化，不建议
		String string_4;
		
		// String类型的数组引用类型
		String[] strings = new String[2];

		// 引用兼容类型, Object是所有类的父类/基类
		Object objectString = string_1;
	}
}

/**
 * String类以及常用方法
 * 
 * String在编程当中使用特别频繁
 * 常用方法要多练习，多熟悉
 * 
 * @author Matthew
 *
 */
class StringCase {
	
	public static void main(String[] args) {
		
		String string_1 = "string_1";
		String string_2 = "string_2";
		Float float_1 = 12.23F;
		Integer int_1 = 12;
		
		/*
		 * printf() 格式化字符串并打印
		 * format() 创建可复用的格式化字符串
		 */
		System.out.printf(				//
				"浮点型变量的值为 %f\n" + 	//
				"整型变量的值为 %d\n" + 	//
				"字符串变量的值为 %s\n" 	//
				, float_1, int_1, string_1);
		System.out.println();
		
		/*
		 * 使用format方法创建可复用的格式化字符串
		 */
		String string_3 = String.format(//
				"浮点型变量的值为 %f\n" + 	//
				"整型变量的值为 %d\n" + 	//
				"字符串变量的值为 %s" 		//
				, float_1, int_1, string_1);
		
		System.out.println("string_3 : \n" + string_3);
		System.out.println();
		
		/*
		 * String常用方法
		 * 注意方法的参数和返回值
		 * 
		 * - 返回字符串长度
		 * int length()
		 * 
		 * - 返回连接字符串
		 * String concat(String str)
		 * 
		 * - 返回返回指定索引处的 char 值
		 * char charAt(int index)
		 * 
		 * - 返回字符串比较是否相等
		 * boolean equals(Object anObject)
		 * 
		 * - 返回字符串转byte[]数组
		 * byte[] getBytes()
		 * 
		 * - 返回某字符在字符串中首次出现的位置
		 * int indexOf(int ch)
		 * 
		 * - 返回某字符在字符串中末次出现的位置
		 * int lastIndexOf(int ch)
		 * 
		 * - 返回用目标字符替代某字符
		 * String replace(String regex, String replacement)
		 * 
		 * - 返回字符串按分隔符切分，通常是, ; \t \s
		 * String[] split(String regex)
		 * 
		 * - 返回从某个位置开始的子串
		 * String substring(int beginIndex)
		 * 
		 * - 返回字符串全小写
	 	 * String toLowerCase()
	 	 * 
	 	 * - 返回转换字符串类型，可以重写方法进行打印输出
		 * String toString()
		 * 
		 * - 返回字符串去除两边空白的子串
		 * String trim()
		 * 
		 * - 返回字符串是否包含某子串
		 * contains(CharSequence chars)
		 * 
		 * - 返回字符串是否为空 ""
		 * isEmpty()
		 */
		System.out.println("字符串长度：" + string_1.length());
		System.out.println("字符串连接：" + string_1.concat(string_2));
		System.out.println("string_1的第三个字符是：" + string_1.charAt(3));
		System.out.println("string_1 string_2 是否相等：" + string_1.equals(string_2));
		System.out.println("string_1的bytes[]" + string_1.getBytes());
		System.out.println("字符在字符串中首次出现的位置：" + string_1.indexOf('r'));
		System.out.println("字符在字符串中末次出现的位置：" + string_1.lastIndexOf("_"));
		System.out.println("字符串替换" + string_1.replace("string", "int"));
		Arrays.asList("1;2;3;4;5;a;b;c;d".split(";")).forEach(System.out::println); // o -> System.out.println(o)
		System.out.println("返回从第3个索引开始的子串：" + string_1.substring(3)); // 注意索引从0开始
		System.out.println("字符串转换大写：" + string_1.toUpperCase());
		System.out.println("转换为字符串类型：" + new Integer(123).toString()); // toString()方法用于打印输出，经常重写使用
		System.out.println(" string_1 ".trim());
		System.out.println("字符串是否包含子串：" + string_1.contains("str"));
		System.out.println("\"string_1\" 字符串是否为空：" + string_1.isEmpty());
		System.out.println("\"\" 字符串是否为空：" + "".isEmpty()); // ""的长度为0
		
		/*
		 * Assignment
		 * 自主查询Java API String类中的对象方法，熟练使用这些方法
		 * url
		 * 中文 https://www.matools.com/api/java8 
		 * 英语 https://docs.oracle.com/javase/8/docs/api/?xd_co_f=47c934d9-e663-4eba-819c-b726fc2d0847
		 */
		
	}
}

/**
 * 
 * - String包含是对象的方法，每次调用会返回新的对象类型，不太适用连续多次操作String的情况
 * - StringBuffer 与 StringBuilder 都操作String本身，提供静态方法，方法基本一致
 * - SringBuilder 非线程安全，同步访问可能导致数据不一致
 * - SringBuilder 效率较高，通常情况使用较多
 * 
 * @author Matthew
 *
 */
class StringBufferAndStringBuilderCase {
	
	public static void main(String[] args) {
		
		/*
		 * - 创建 StringBuilder 对象
		 * new StringBuilder(_)
		 */
		StringBuilder builder = new StringBuilder("This is a basic string by StringBuilder.");
		StringBuffer buffer =  new StringBuffer("This is a basic string by StringBuffer.");
		
		builder
			.append("\n") 											// 追加换行
			.append("This is a new Line.\n") 						// 追加内容
			.append("string");
		System.out.println(builder.toString() + "\n"); 				// 打印输出
		
		int index_lastS = builder.lastIndexOf("s");					// 获取子串的最后一个索引
		int length = builder.length();								// 获取长度 

		String subString = builder.substring(index_lastS, length);	// 获取子串，String
		System.out.println(subString + "\n");
		
		builder.delete(index_lastS, length); 						// 删除某子串
		System.out.println(builder + "\n");
		
		builder.reverse();
		System.out.println(builder);
		
		/*
		 * Assignment
		 * - 使用 StringBuffer 对象，熟悉相关方法，实现同样的功能
		 * - 在API中查阅 StringBuilder 与 StringBuffer 的方法并使用
		 * 
		 */
		
	}
}

/**
 * 数组
 * - 存储固定大小的同类型元素
 * - 数组索引从0开始，到length-1
 * - 数组遍历是数组的常规基础操作
 * @老刘编程 点赞关注私信获取源码
 */
class ArrayCase {
	public static void main(String[] args) {
		
		/*
		 * 创建数据的两种方式
		 * - array_int_1 创建时不初始化，多用于动态装配数据场景
		 * - array_int_2 创建时初始化
		 */
		int size = 3;
		Integer[] array_int_1 = new Integer[size];
		array_int_1[0] = 0;
		array_int_1[1] = 100;
		array_int_1[2] = 200;

		Integer[] array_int_2 = { 1, 101, 201 };
		
		/*
		 * 遍历数组元素是一切操作数组的基础
		 * 使用 for / for-each 循环遍历数组
		 * 打印元素，计算总和
		 */
		int sum = 0;
		System.out.println("// 遍历数组 array_int_1 并计算无数和");
		for (int i = 0; i < array_int_1.length; i++) {
			sum += array_int_1[i];
			System.out.println("array_int_[" + i + "] = " + array_int_1[i]);
		}
			
		sum = 0;
		for(Integer int_1: array_int_1) 
			sum += int_1;
		
		System.out.println("\n数组array_int_1的值总和为：" + sum);
		
		/*
		 * 二维数组
		 * 一维数组中每个元素是另一个一维数组
		 */
		
		// 分别初始化数组元素
		String[][] _2DArray_1 = new String[2][3]; // 2行3列的数组
		_2DArray_1[0] = new String[2];
		_2DArray_1[1] = new String[3];
		_2DArray_1[0][1] = new String("Good");
		
		// 创建时直接初始化数组元素
		String[][] _2DArray_2 = {  //
				{ "A", "B", "C" }, // 第一行
				{ "D", "E", "F" }, // 第二行
				{ "G", "H", "I" }  // 第三行
		};
		System.out.println();
		
		System.out.println("// 遍历二维数组 _2DArray_1 元素");
		System.out.println("{");
		for (String[] _Row_i : _2DArray_2) { // 遍历行
			System.out.print("\t{ ");
			for (String _Column_i : _Row_i) {//遍历列
				System.out.print("\"" + _Column_i + "\", ");
			}
			System.out.println("}, ");
		}
		System.out.println("}\n");
		
		/*
		 * Arrays 操作数组
		 */
		System.out.println("// Lambda表达式操作 _2DArray_2 数组内部");
		Arrays.asList(_2DArray_2) 	// 转换为List
				.forEach(o -> { 	// forEach遍历第一层
					String[] _Row_i = o;
					
					Arrays.asList(_Row_i)	 //
							.forEach(o_ -> { // forEach遍历第二层
								String _Column_i = o_;
								System.out.print("\"" + _Column_i + "\", ");
							});
				});
		System.out.println("\n");
		
		System.out.println("// Lambda表达式遍历 _2DArray_2 数组，最简化打印输出");
		Arrays					//
			.asList(_2DArray_2) //
			.forEach(o -> 		//
				Arrays.asList(o).forEach(System.out::println)
			);
		System.out.println();
		
		/*
		 * sort()
		 * - 对数组进行升序排序
		 */
		System.out.println("// 使用 Arrays 对数组 array_int_3 升序排序");
		Integer[] array_int_3 = { 1, 12, 21, 34, 5, 22 };
		Arrays.sort(array_int_3);
		Arrays.asList(array_int_3).forEach(System.out::println);
		
		/*
		 * Assignment
		 * - 实践操作数组的方法
		 * - 实现数组降序排序
		 */
	}
}

/**
 * 日期时间
 * 
 * java.util.Date
 * java.util.SimpleDateFormat
 * 
 * @老刘编程 点赞关注私信获取源码
 */
class DateAndTime {
	
	public static void main(String[] args) {
		
		System.out.println("// 获取当前日期时间");
		Date date = new Date();
		System.out.println("当前时间标准输出：" + date);
		System.out.printf(Locale.CHINA, "当前时间中国时区输出：%tc%n\n", date);
		
		/*
		 * 使用Pattern yyyy-MM-dd hh:mm:ss
		 */
		System.out.println("// 格式化输出日期时间");
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		System.out.println("yyyy-MM-dd hh:mm:ss 格式化时间：" + format.format(date) + "\n");

		System.out.println("// 使用 printf 格式化输出");
		// c 包括全部日期和时间信息
		System.out.printf("全部日期和时间信息：%tc%n", date);
		// F "年-月-日"格式
		System.out.printf("年-月-日格式：%tF%n", date);
		// D "月/日/年"格式
		System.out.printf("月/日/年格式：%tD%n", date);
		// r "HH:MM:SS PM"格式（12时制）
		System.out.printf("HH:MM:SS PM格式（12时制）：%tr%n", date);
		// T "HH:MM:SS"格式（24时制）
		System.out.printf("HH:MM:SS格式（24时制）：%tT%n", date);
		// R "HH:MM"格式（24时制）
		System.out.printf("HH:MM格式（24时制）：%tR", date);
		System.out.println("\n");
		
		try {
			
			System.out.println("// 指定标准字符串解析为时间");
			String dateString = "2021.06.19";
			SimpleDateFormat format2 = new SimpleDateFormat("yyyy.MM.dd");
			Date resultDate = format2.parse(dateString);
			System.out.printf(Locale.CHINA, "解析后的日期：%tc%n", resultDate);
			System.out.println();
			
			System.out.println("// 简单计算时间间隔");
			long timestamp = System.currentTimeMillis();// 获取当前时间毫秒值
			System.out.printf(Locale.CHINA, "取当前时间：%tc%n", new Date());
			
			Thread.sleep(1000); // 程序线程休眠1s
			System.out.printf(Locale.CHINA, "取1s后时间：%tc%n", new Date());
			long timestamp_ = System.currentTimeMillis();
			
			long timeDifference = timestamp_ - timestamp;
			System.out.println("前后时间差值为：" + timeDifference + "毫秒\n");
			
			/*
			 * Assignment
			 * 在Java API中查询 
			 * java.util.Date 和 java.util.SimpleDateFormat
			 * 相关方法并尝试使用
			 */

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

/**
 * 
 * 使用 java.util.Calendar 类操作日历时间
 * 实现简单日期计算
 * 
 * @老刘编程 点赞关注私信获取源码
 */
class CalendarCase {
	
	public static void main(String[] args) {

		System.out.println("// 获取当前日历时间");
		Calendar calendar_1 = Calendar.getInstance();
		System.out.println("当前日历时间：" + calendar_1 + "\n");
		
		System.out.println("// 获取指定日历时间 2021-01-01");
		Calendar calendar_2 = Calendar.getInstance();
		calendar_2.set(2021, 1-1 /*参数从0开始，6月参数为6-1*/, 1);
		System.out.println("指定日历时间：" + calendar_2 + "\n");
		
		System.out.println("// 时间前后判断");
		System.out.println(calendar_1.before(calendar_2) + "\n");
		
		System.out.println("// 分别用Calendar常量获取日期时间值");
		// 获得年份
		int year = calendar_1.get(Calendar.YEAR);
		System.out.println("year: " + year);
		
		// 获得月份
		int month = calendar_1.get(Calendar.MONTH) + 1;
		System.out.println("month: " + month);
		
		// 获得日期
		int date_ = calendar_1.get(Calendar.DATE);
		System.out.println("date: " + date_);
		
		// 获得小时
		int hour = calendar_1.get(Calendar.HOUR_OF_DAY);
		System.out.println("hour: " + hour);
		
		// 获得分钟
		int minute = calendar_1.get(Calendar.MINUTE);
		System.out.println("minute: " + minute);
		
		// 获得秒
		int second = calendar_1.get(Calendar.SECOND);
		System.out.println("second: " + second);
		
		// 获得星期几 1-星期日，2-星期1，3-星期二，以此类推
		int day = calendar_1.get(Calendar.DAY_OF_WEEK);
		System.out.println("day: " + day + "\n");
		
		/*
		 * Assignment
		 * 在Java API中查询Calendar相关方法并尝试使用
		 */
	}
}

/**
 * 
 * 方法 Method
 * 
 * - 方法是解决一类问题的步骤组合
 * - 方法包含于类
 * - 命名规则：“驼峰原则”，首字母小写，后续单词首字符大写 getPersonInfo()
 * - 结构：方法修饰符、方法名、参数列表、方法体、返回值（可选）
 * 
 * @老刘编程 点赞关注私信获取源码
 * 
 */
class MethodCase {
	
	/**
	 * 无返回方法 void
	 * @param string
	 */
	public static void show(String string) {
		System.out.println("无参数方法打印：" + string);
	}
	
	/**
	 * 判断 num1 是否大于 num2
	 * @param num1
	 * @param num2
	 * @return
	 */
	public static Boolean isBigger(int num1, int num2) {
		return num1 > num2;
	}
	
	/**
	 * 返回 num1 和 num2 最大值
	 * @param num1
	 * @param num2
	 * @return
	 */
	public static Integer max(int num1, int num2) {
		return isBigger(num1, num2) ? num1 : num2;
	}
	
	/**
	 * 可变参数方法
	 * 返回一组数 numbers 当中的最大值
	 * @param numbers
	 * @return
	 */
	public static Integer max(int... numbers) {
		if (numbers.length == 0) 
			throw new NullPointerException("没有参数无法比较。");
		else if (numbers.length == 1)
			return numbers[0];
		else {
			int temp = numbers[0];
			for (int i = 0; i < numbers.length; i++) {
				if (isBigger(numbers[i], temp))
					temp = numbers[i];
			}
			return temp;
		}
	}

	public static void main(String[] args) {
		
		System.out.println("// 调用 isBigger 方法");
		System.out.println("2 比 1 大：" + isBigger(2, 1) + "\n");
		
		System.out.println("// 调用 max 方法");
		System.out.println("2 和 1 取最大值：" + max(2, 1) + "\n");

		System.out.println("// 调用可变参数方法 max");
		System.out.println(max(2, 5, 7, 1, -32, 45) + "\n");

		System.out.println("// 调用无返回方法 show");
		show("Hello!");
	}
}

