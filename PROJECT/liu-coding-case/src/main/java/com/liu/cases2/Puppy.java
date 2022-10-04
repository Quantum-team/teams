package com.liu.cases2;

import java.util.*;

public class Puppy extends Animal {
	
	private Integer weight;
	
	public String eat() {
		
		return "吃";
	}
	
	public static void main(String[] args) {
		
		Puppy puppy1 = new Puppy();
		puppy1.setName("旺财");
		puppy1.setEatable(true);
		puppy1.setWeight(10);

		System.out.println(puppy1.getName() + " " + puppy1.getId());
		System.out.println(puppy1.getAge());
//		System.out.println(puppy1.getEatable());

		List<Integer> list = new ArrayList<>();
		list.add(1);
		list.add(12);
		list.add(-123);
		list.add(24);
		list.add(-124);
		list.add(2412);
		list.add(24);

		list.sort(new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				return o1 - o2;
			}
		});

		// => Lambda
		list.sort((o1, o2) -> o1 - o2);

		list.forEach(o -> System.out.println(o));

		Integer[] array = new Integer[5];
		array[0] = 12;
		array[1] = 121;
		array[2] = -12;
		array[3] = 2;
		array[4] = 1212;

		Arrays.sort(array, new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				return o2 - o1;
			}
		});

		Arrays.sort(array, (o1, o2) -> o1 - o2);

		for (Integer o: array) {
			System.out.println(o);
		}

		Puppy puppy2 = new Puppy();
		puppy2.setName("老二");
		puppy2.setWeight(2);
		
		Puppy puppy3 = new Puppy();
		puppy3.setName("德福");
		puppy3.setWeight(18);
		
		Puppy puppy4 = new Puppy();
		puppy4.setName("小黑");
		puppy4.setWeight(11);

		// 定义同时初始化
		Puppy[] puppies_1 = new Puppy[] { puppy1, puppy2, puppy3, puppy4 };

		// 定义仅指定长度，动态初始化
		Puppy[] puppies_2 = new Puppy[4];
		puppies_2[0] = puppy1;
		puppies_2[1] = puppy2;
		puppies_2[2] = puppy3;
		puppies_2[3] = puppy4;
		
		List<Puppy> puppies_3 = new ArrayList<>();
		puppies_3.add(puppy4);
		puppies_3.add(puppy3);
		puppies_3.add(puppy2);
		puppies_3.add(puppy1);

//		System.out.println(puppies_3.size());

		// 带索引遍历数组 i / index
//		for(int i = 0; i < puppies_1.length; i++) {
//			Puppy puppy = puppies_1[i];
//			System.out.println(puppy.getName());
//		}
		
		// 不带索引遍历数组，增强for循环
//		for (Puppy puppy : puppies_2) {
//			System.out.println(puppy.getId());
//		}
		
		// Lambda Java 8 新特性
//		puppies_3.forEach(puppy -> System.out.println(puppy));
//		puppies_3.forEach(System.out::println);
//

		puppies_3.sort(new Comparator<Puppy>() {
			@Override
			public int compare(Puppy o, Puppy o_) {

				return o.getWeight() - o_.getWeight();
			}
		});

		puppies_3.sort((o, o_) -> {
			return o.getWeight() - o_.getWeight();
		});

		puppies_3.sort((puppy, puppy_) -> puppy.getWeight() - puppy_.getWeight());

		puppies_3.forEach(puppy -> {
			System.out.println(puppy.getName() + " " + puppy.getWeight());
		});



		// 定义并初始化
		Puppy[] puppies2 = new Puppy[] {
			puppy1, // index 0
			puppy2,	//
			puppy3, //
			puppy4	// index 3
		};

		// 定义后动态初始化
		Puppy[] puppies3 = new Puppy[4];
		puppies3[0] = puppy1;
		puppies3[1] = puppy2;
		puppies3[2] = puppy3;
		puppies3[3] = puppy4;
		// puppies3[4] = puppy1;

		List<Puppy> list2 = new ArrayList<>();
		list2.add(puppy1);
		list2.add(puppy2);
		list2.add(puppy3);
		list2.add(puppy4);

		List<Puppy> list3 = new LinkedList<>();

		// for
		// index for
		// 增强 (:） for
		// Lambda ->




	}

	public Integer getWeight() {
		return weight;
	}

	public void setWeight(Integer weight) {
		this.weight = weight;
	}
	
}
