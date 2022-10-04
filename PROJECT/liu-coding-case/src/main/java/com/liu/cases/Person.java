package com.liu.cases;

import com.matthew.interfaces.BizInterface;
import com.matthew.interfaces.WorkingInterface;

/**
 * 面向对象 封装性
 * 
 * private
 * getter/setter
 * 用公有方法访问私有属性
 * 
 * @老刘 点赞关注私信获取源码
 */
/**
 * 面向对象 继承性
 * 
 * - 支持单继承 A extends B
 * - 支持多重继承 A extends B extends C
 * - 支持多对一继承 A extends B, C extends B
 * - 不支持一对多继承 !( A extends B, A extends C )
 * 
 * @老刘 点赞关注私信获取源码
 *
 */
/**
 * 抽象 abstract
 * - 抽象类，abstract修饰的类，可以包含抽象方法或否
 * - 抽象方法，abstract修饰的方法，没有方法实现体
 * - 抽象类不能被实例化，被继承后子类可实例化
 * - 子类必须重写/实现所有抽象方法，除非是子类也是抽象类
 * - 抽象类也遵循单继承规则
 * - 抽象类用于对一些父类公有方法的归纳描述，通常在设计架构时考虑是否使用
 * 
 * @老刘 点赞关注私信获取源码
 */
public abstract class Person {
	private Integer id;
	private String name;
	private Integer salary;
	
	/*
	 * 构造方法之间，互为方法重载Overload
	 * 方法名相同，参数列表不同
	 * public Person()
	 * public Person(String name, int id)
	 * 
	 * 构造方法是创造对象的母体，是Java世界的造物主
	 * 什么样的构造方法，决定了对象如何来到Java世界
	 * 
	 * 在Person构造方法中
	 * 有的人天生就有该有的，有名有份，有家有底（有参构造），
	 * 
	 * 有的人生来平凡，一无所有（无参构造），无名无姓，无家无世无地位，
	 * 只有一个生命的躯壳，来到世间犹如草芥，只能通过后天的努力拼搏（动态赋值）
	 * 来争取属于自己的一切。
	 * 
	 * 来到世界的机会和在世界中选择努力的机会总是公平的，Java世界不论出身如何
	 * 每个对象都有自身的价值。
	 * 
	 * 我生来就是高山而非溪流，我欲于群峰之巅俯视平庸的沟壑。
	 * 我生来就是人杰而非草芥，我站在伟人之肩藐视卑微的懦夫！
	 */
	protected Person() {
		System.out.println("// 1 用无参构造方法 Person() 创建一个人 ");
		System.out.println("// 感谢你创造了我！");
	}
	
	protected Person(String name, int id) {
		this.name = name;
		this.id = id;
		System.out.println("// 1 用有参构造方法 Person(String name, int id) 创建一个人 ");
		System.out.println("// 感谢你创造了我！我是：" + getName());
	}
	
	final public void breath() {
		System.out.println("// 人会呼吸。");
	}
	
	protected void play() {
		System.out.println("// 人类的活动。");
	}

	public void show() {
		System.out.println("其实，我是一个人类：" + this.getId() + "号" + getName() + "。");
	}
	
	/*
	 * 多态
	 * 
	 * - 继承关系
	 * - 方法重写
	 * - 父类引用指向子类对象 Person person = new BadBoy();
	 */
	public static void check(Person person) {
		if (person instanceof BadBoy)
			System.out.println("// 注意，这个人是渣男！" + person.getName());
		else if (person instanceof NiceGirl)
			System.out.println("// 加油，好女孩。" + person.getName());
		else
			System.out.println("// 没有这个对象，无法判断。");
	}
	
	/*
	 * 抽象方法
	 * 子类必须重写并实现方法体
	 */
	public abstract void work(String work);
	
	public static void main(String[] args) {
//		System.out.println("// 创建 Person 1");
//		Person p1 = new Person();
//		p1.setId(1);
//		p1.setName("Tom");
//		p1.show();
//		
//		System.out.println();
//		System.out.println("// 创建 Person 2");
//		Person p2 = new Person("Jerry", 2);
//		p2.show();

//		System.out.println("// 测试方法多态性");
//		BadBoy badboy1 = new BadBoy("Tom", 1);
//		Person badboy2 = new BadBoy("Jerry", 2);

//		System.out.println();
//		System.out.println("// 子类引用指向子类对象");
//		System.out.println("BadBoy badboy1 = new BadBoy(\"Tom\", 1);");
//		badboy1.play();
//		
//		System.out.println();
//		System.out.println("// 父类引用指向子类对象");
//		System.out.println("Person badboy2 = new BadBoy(\"Jerry\", 2);");
//		badboy2.play();

//		System.out.println();
//		System.out.println("// 使用 instanceof 判断对象类型");
//		check(badboy1);
//		check(badboy2);
//		check(new NiceGirl());
//		check(null);
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getSalary() {
		return salary;
	}

	public void setSalary(Integer salary) {
		this.salary = salary;
	}
}

/*
 * 设计继承关系
 * 渣男类 	BadBoy extends Person	
 * 好女孩类	NiceGirl extends Person
 */

class BadBoy extends Person implements WorkingInterface, BizInterface {
	
	private String 	timestamp;
	private String 	locate;

	/*
	 * 构造子类，先构造父类
	 * 对于无参构造方法，程序体会默认调用父类无参构造方法
	 * 
	 * 记住：渣男再渣也是人！
	 * 
	 * 先构造人、再构造渣男
	 */
	public BadBoy() {
		System.out.println("// 默认调用 Person 无参构造方法1 ");
	}
	
	/*
	 * 有参构造方法
	 * 手动使用 super 调用父类构造方法
	 */
	public BadBoy(String name, int id) {
		super(name, id);
	}

	/*
	 * 子类根据需要拓展本类构造方法
	 * 
	 * 渣男新特征：总是在不合适的时间出现在不合适的地方
	 */
	public BadBoy(String name, int id, String timestamp, String locate) {
		super(name, id);
		this.timestamp = timestamp;
		this.locate = locate;
	}
	
	public BadBoy(String name, int id, Integer salary) {
		super(name, id);
		this.setSalary(salary);
	}
	
	/*
	 * 测试 重写Override
	 * 子类重写父类的方法
	 * 
	 * 渣男的日常活动与常人的活动有区别，需要重写
	 */
	public void play() {
		System.out.println("// 喝酒、蹦迪、钓鱼");
	}
	
	/*
	 * 重载 Overload
	 * 方法 play() 与 play(String act) 互为重载
	 */
	public void play(String act) {
		System.out.println("// 时间：" + getTimestamp() + ", 地点：" + getLocate());
		System.out.println(act);
	}
	
	public void show(String info) {
		System.out.println(info);
	}
	
	/*
	 * 子类不是抽象类，必须实现父类的所有抽象方法
	 */
	public void work(String work) {
		System.out.println("// 这个渣男的工作是：" + work);
	}

	/*
	 * 覆盖实现渣男的事业
	 */
	public void startBiz() {
		System.out.println(this.getName() + "开始做点小生意。");
	}

	/*
	 * 覆盖实现渣男的就业
	 */
	public void startWorking() {
		System.out.println(this.getName() + "找到一份工作。");
	}
	
	public static void main(String[] args) {

		BadBoy b1 = new BadBoy("L哥", 1);
//		b1.show("大家好我是" + b1.getId() + "号渣男：" + b1.getName());
//		b1.play();
//		b1.work("无业游民。");
		b1.startWorking();
		b1.startBiz();
		
		System.out.println();
		
		BadBoy b2 = new BadBoy();
		b2.setName("W哥");
		b2.setId(2);
//		b2.setTimestamp("2022-02-14 23:00:00");
//		b2.setLocate("公司加班");
//		b2.show("大家好我是" + b2.getId() + "号渣男：" + b2.getName());
//		b2.play("// 看书、音乐、摄影");
//		b2.work("时间管理大师");
	}
	
	/*
	 * Assignment
	 * - 在 NiceGirl 中实现接口，重写相关方法
	 * - 思考 接口interface 与 抽象方法 的异同
	 * - 思考为什么需要使用接口，有什么优越性
	 */

	public String getLocate() {
		return locate;
	}

	public void setLocate(String locate) {
		this.locate = locate;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

}

class NiceGirl extends Person {

	public NiceGirl() {
		
	}
	
	public NiceGirl(String name, int id) {
		super(name, id);
	}
	
	/*
	 * 重写 Override
	 * 方法名相同、返回类型相同、参数列表相同
	 * 父类 Person 的 show() 方法
	 * 子类 Person 的 show() 方法
	 */
	public void show() {
		System.out.println("// 其实，我是一个渣男：" + super.getName());
	}
	
	public void play() {
		System.out.println("// 购物、逛街、做美甲");
	}
	
	@Override
	public void work(String work) {
		// TODO Auto-generated method stub
	}
	
	/*
	 * 测试 final 方法不能被重写
	 * 
	 * 好女孩想要不一样的呼吸，这是万万不可能的！
	 */
//	public void breath() {}
	
	public static void main(String[] args) {
		
		NiceGirl lily = new NiceGirl();
		lily.setId(1);
		lily.setName("Lily");
		
		System.out.println("// 调用 NiceGirl 重写 Person 的 show 方法");
		lily.show();
		
		System.out.println();
		System.out.println("// 演示方法 重写Override 和 重载Overload");
		
		lily.show();
		
		Person nancy = new NiceGirl();
		nancy.setId(2);
		nancy.setName("Nancy");
	}
	
	/*
	 * Tips
	 * 
	 * 相同点：都是“相同”的方法，方法名相同，但表现形式不同
	 * 
	 * 			重载			重写
	 * 			Overload	Override
	 * 参数列表	必变			不变
	 * 返回类型	可变可不变	不变
	 * 异常		可变可不变	可以减少或删除，一定不能抛出新的或者更广的异常
	 * 访问		可变可不变	限制只能放宽
	 * 场景		同一类中		不同类中，子类与父类
	 * 
	 * 重载的“打”-描述广度：打电话，打车，打饭，打人
	 * 重写的“打”-描述深度：父类打电话，子类打电话，子类继承父类
	 * 
	 * Assignment
	 * - 在 BadBoy 类中新增实现方法继承
	 * - 参照构造方法重载，实现 show 方法重载
	 * - 观察 重载Overload 和 重写Override 的异同
	 * - 思考 重载Overload 和 重写Override 的本质是什么，为什么需要这样做？
	 */
}