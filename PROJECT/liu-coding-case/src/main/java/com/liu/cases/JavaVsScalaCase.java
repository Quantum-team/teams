package com.liu.cases;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @Matthew
 *
 * Java/Scala/Hadoop/Spark大数据课程
 *
 * - 回顾数据开发与"盒子"理论，大数据编程就是用盒子装数据的编程
 *
 * - Eclipse 与 IDEA
 *
 * - 交互式编程与脚本式
 * 交互式：测试环境，有来有往，小型案例测试 —— 谈恋爱 互动频繁
 * 脚本式：开发环境，编译、部署、运行 —— 结婚（上线） 稳定可靠
 *
 * - 在 Java / Scala 案例中对比关键的不同点，
 * 加深对两门语言的印象，提高学习效率
 *
 * 为什么不比较相同点？
 *
 * 大家一起找不同
 *
 */
public class JavaVsScalaCase {

    /**
     * - 比较方法定义
     * 换行方法
     */
    public static void enter() {
        System.out.println();
    }

    /**
     * - 比较 main 方法
     */
    public static void main(String[] args) {

        /**
         * - 比较打印
         */
        System.out.println("Hello, world!");


        /**
         * - 比较方法调用
         */
        enter();



        /**
         * - 比较创建变量
         * - 比较 数组 / List 创建
         */

        // 创建 List 动态添加元素
        Integer[] arrayInt = {
            1, 2, 3, 4, 5, 6, 7, 8, 9, 10
        };

        List<Integer> list = new ArrayList<>();



        /**
         * - 比较 for / foreach
         */
        for (int o: arrayInt) {
            list.add(o * -1);
        }

        list.forEach(o -> System.out.println(o));

        list.forEach(System.out::println);

    }
}
