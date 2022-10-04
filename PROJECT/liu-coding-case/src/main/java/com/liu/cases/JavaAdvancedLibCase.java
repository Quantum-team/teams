package com.liu.cases;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 编程案例源码 
 * Java 高级篇
 * @老刘 点赞关注私信获取源码
 *
 */
public class JavaAdvancedLibCase {

}

/**
 * 集合 Collection 
 * 
 * 集合装数据的袋子，适应不同数据类型的有不同的袋子
 * 
 * List	可重复集合
 * Set	不重复集合
 * Map	键值对集合/映射
 * 
 * 
 * - 集合存储任意个数的对象
 * - 集合长度可变，数组不可变
 * - 集合接口提供操作集合的丰富、便捷的方法
 *
 */
class CollectionCase {
	
	public static void main(String[] args) {
		
		/*
		 * java.util.ArrayList 基于数组结构，适用于
		 * - 频繁访问列表中的某一个元素 
		 * - 只需要在列表末尾进行添加和删除元素操作
		 */
		List<String> list_1 = new ArrayList<>();
		list_1.add("123");
		list_1.add("12");
		list_1.add("44");
		list_1.add("6");
		list_1.add("789");
		
		List<Integer> list_2 = new ArrayList<>();
		list_1.forEach(o -> list_2.add(Integer.parseInt(o)));
		
		// 实现集合元素排序
		Collections.sort(list_2);
		System.out.println("// 遍历集合元素");
		list_2.forEach(System.out::println);
		
		System.out.println();
		System.out.println("首个元素：" + list_2.get(0));
		
		/*
		 * Assignment
		 * 
		 * - 自主查阅Java API java.util.ArrayList类，测试使用以下方法
		 * 
		 * boolean add(E e)
		 * E get(int index)
		 * void clear()
		 * Object clone()
		 * int index(Object o)
		 * int lastIndexOf(Object o)
		 * List<E> subList(int fromlndex, int tolndex)
		 * void	forEach(Consumer<? super E> action)
		 * boolean isEmpty()
		 * E remove(int index)
		 * boolean remove(Object o)
		 * boolean retainAll(Collection<?> c)
		 * int size()
		 * void sort(Comparator<? super E> c)
		 * <T> T[] toArray(T[] a)
		 */
		
		
		
		// ===================================================================================
		
		
				
		System.out.println();
		
		/*
		 * java.util.LinkedList 基于链表结构，常用于
		 * - 需要通过循环迭代来访问列表中的某些元素
	     * - 需要频繁的在列表开头、中间、末尾等位置进行添加和删除元素操作
		 */
		LinkedList<String> list_3 = new LinkedList<>();
		list_3.add("Tom");
		list_3.add("Jerry");
		list_3.add("Bob");
		list_3.add("Marry");
		list_3.add("Ann");
		list_3.add("");
		list_3.add("Nicholas");
		
		System.out.println("首元素：" + list_3.getFirst());
		System.out.println("末元素：" + list_3.getLast());
		list_3.removeLast();
		list_3.removeIf(o -> o.equals("Bob"));
		list_3.removeIf(o -> o.isEmpty());
		list_3.forEach(System.out::println);
		
		/*
		 * Assignment
		 * 
		 * - 自主查阅Java API java.util.LinkedList 类，测试使用以下方法
		 *
		 * void addFirst(E e)
		 * void addLast(E e)
		 * E getFirst()
		 * E getLast()
		 * E removeFirst()
		 * E removeLast()
		 * E remove(int index)
		 * boolean remove(Object o)
		 * E peek()
		 * E peekFirst()
		 * E peekLast()
		 */
		
		
		
		// ===================================================================================
		
		
		
		/*
		 * 实现 Comparator 接口实现自定义排序问题 
		 */
		
		System.out.println();
		
		BadBoy[] boys = { 
				new BadBoy("Tom", 1, 2000), 	//
				new BadBoy("Jerry", 2, 800), 	//
				new BadBoy("Bob", 3, 12000),	//
				new BadBoy("Marry", 4, 3400), 	//
				new BadBoy("Ann", 5, 800), 		//
				new BadBoy("Nicholas", 6, 11000) 
			};
		
		List<BadBoy> listBoys = Arrays.asList(boys);
		
//		listBoys.sort(new SalaryComparator()); 
		
		listBoys.sort(new Comparator<BadBoy>() { // 传入匿名内部类的简化写法

			@Override
			public int compare(BadBoy o, BadBoy o_) {
				
				/*
				 * 还记得三元表达式 ? : 吗
				 * 按照 salary 比较大小
				 * <	-1
				 * ==	0
				 * >	1
				 */
				return // o1.getSalary().compareTo(o2.getSalary())
				o.getSalary() < o_.getSalary() ? -1 : // 
					o.getSalary() == o_.getSalary() ? 0 : 1;
			}
			
		});
		
		/*
		 * 最简
		 */
		listBoys.sort((o, o_) -> {
			return o.getSalary() < o_.getSalary() ? -1 : // 
				o.getSalary() == o_.getSalary() ? 0 : 1;
		});
		
		System.out.println("// 按照 salary 排序 List<BadBoy>");
		listBoys.forEach(System.out::println);

		/*
		 * Assignment
		 * 
		 * - 思考，上述排序案例中 salary 倒序应该如何改动？
		 * - 参考 SalaryComparator 自定义比较类，自主实现 List<BadBoy> 
		 * 按 name 首字母排序
		 * 提示：比较首字母用 compareToIgnoreCase() 方法
		 */
		
		
		
		// ===================================================================================
		
		
		
		/*
		 * java.util.HashSet
		 * - 不允许有重复元素的集合，是否重复判别依据是 hashCode & equals() 同时满足
		 * - 允许有 null 值
		 * - 无序，不会记录插入的顺序
		 * - 非线程安全
		 */
		System.out.println();
		System.out.println("// 打印 Set 集合，不重复的姓名列表");
		
		Set<String> setNames = new HashSet<>();
		listBoys.forEach(o -> setNames.add(o.getName()));
		setNames.add("Nicholas"); // 重复的元素不会被添加
		setNames.add("Lee");
		setNames.forEach(System.out::println);
		System.out.println("// 不重复的姓名个数：" + setNames.size());

		
		
		// ===================================================================================
		
		
		
		/*
		 * java.util.HashMap
		 * - 存储键值对(key-value)映射
		 * - 最多允许一条记录的键为 null，非线程安全
		 * - key不可重复，value可以重复
		 * 
		 */
		
		System.out.println();
		System.out.println("// 遍历 Map key-value对的集合");
		
		Map<Integer, String> map_1 = new HashMap<>();
		listBoys.forEach(o -> map_1.put(o.getId(), o.getName()));
		
		// 使用 entrySet() 同时获得 Map 的 key-value，即Entry，然后遍历
		map_1
			.entrySet() // Set<Entry<Integer, String>>
			.forEach(o ->  {
			Integer id = o.getKey();
			String name = o.getValue();
			System.out.println("id = " + id + ", name = " + name);
		});
		
		/*
		 * Assignment
		 * 
		 * 自主查阅Java API java.util.HashSet 类
		 * 
		 * - 使用 keySet() 获取 Map 的所有 key 并遍历
		 * 
		 * - 使用 values() 获取 Map 的所有 value 并遍历
		 * 
		 * - 测试使用以下方法
		 * void clear()
		 * boolean containsKey(Object key)
		 * V get(Object key)
		 * V put(K key, V value)
		 * void putAll(Map m)
		 * V remove(Object key)
		 * boolean remove(Object key, Object value)
		 * Set entrySet()
		 * Set keySet()
		 * boolean isEmpty()
		 * int size()
		 * Collection values()
		 * 
		 */

		
		
		// ===================================================================================
		
		
		
		/*
		 * java.util.Iterator
		 * - 迭代器是一种访问集合的通用方法
		 * - 基本操作 hasNext() next() remove()
		 */
		
		System.out.println();
		System.out.println("// 使用 while 遍历迭代器");
		
		Iterator<BadBoy> iterator =  listBoys.iterator();
		while (iterator.hasNext()) {
			
			BadBoy boy = iterator.next();
			System.out.println(boy);
		}

		
		
		// ===================================================================================
		
		
	}
}

/*
 * 实现 Comparator 接口的类，实现自定义比较方法，用于排序
 */
class SalaryComparator implements Comparator<BadBoy> {

	@Override
	public int compare(BadBoy o1, BadBoy o2) {
		
		/*
		 * 还记得三元表达式 ? : 吗
		 * 按照 salary 比较大小
		 * <	-1
		 * ==	0
		 * >	1
		 */
		return // o1.getSalary().compareTo(o2.getSalary())
		o1.getSalary() < o2.getSalary() ? -1 : // 
			o1.getSalary() == o2.getSalary() ? 0 : 1;
	}
}

/**
 * 
 * @老刘 点赞关注私信获取源码
 * 
 * Java/Scala/Hadoop/Spark大数据陪练，学习指导
 * vx: liucoding
 * 
 * <> 泛型
 * 
 * - 泛型相当于传递一个标记类型的参数
 * - 使用非常普遍，在编译时进行数据类型约束
 * - 泛型可以标记类、接口和方法
 * - 泛型必须是引用类型，不能是基本数据类型
 * - 泛型有继承性，没有声明 extends 则默认继承 Object
 * 
 * Java 常用泛型标记符
 * E - Element 在集合中使用，因为集合中存放的是元素
 * T - Type 普通Java类
 * K - Key 键
 * V - Value 值
 * N - Number 数值类型
 * ? - 不确定的 java 类型 
 * 
 * 为什么有泛型？
 * - 泛型频繁与集合搭配使用，泛型的诞生是为了解决集合中元素类型“丢失”的问题。
 * - 如果一个List不带泛型时，元素类型默认都为Object，如果创建一个BadBoy的List，
 * 没有泛型约束，允许放入NiceGirl，这样给程序带来极大的隐患，必须把这种隐患在
 * 编译级别消除。
 * 
 * 定义数据访问层 PersonDAO ，用于封装数据查询相关方法
 * 泛型为人类 Person
 * 
 */
class PersonDAO<T extends Person> {
	
	/*
	 * 声明 T 类型的引用
	 * 该引用可以表示 Person/BadBoy/NiceGirl
	 */
	private List<T> ts;
	
	public void list() {
		System.out.println("// 使用 PersonDAO 二次封装方法查询");
		ts.forEach(System.out::println);
	}
	
	public static void main(String[] args) {
		
		BadBoy[] boys = { 
				new BadBoy("Tom", 1, 2000), 	//
				new BadBoy("Jerry", 2, 800), 	//
				new BadBoy("Bob", 3, 12000),	//
				new BadBoy("Marry", 4, 3400), 	//
				new BadBoy("Ann", 5, 800), 		//
				new BadBoy("Nicholas", 6, 11000) 
			};
		
		List<BadBoy> listBoys = Arrays.asList(boys);
		
		PersonDAO<BadBoy> personDAO = new PersonDAO<>();
		personDAO.setTs(listBoys);
		personDAO.list(); // 使用DAO查询类操作List，实现数据与访问分层
		
	}

	public List<T> getTs() {
		return ts;
	}

	public void setTs(List<T> ts) {
		this.ts = ts;
	}

	/*
	 * Assignment
	 * 
	 * - 创建一个不带泛型和带泛型的List，进行遍历，增删等
	 * - 思考使用泛型二次封装有哪些好处？
	 * - 思考如果没有泛型，list()方法应该如何改动，有什么缺点？
	 * - 思考泛型 PersonDAO 实现了代码的通用化，这样做有什么好处？
	 */
	

}
