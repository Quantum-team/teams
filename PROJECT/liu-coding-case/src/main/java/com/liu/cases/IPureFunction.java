package com.liu.cases;

/**
 * @Author 老刘Matthew
 * @Timestamp 2022/8/10 08:31
 * @Description 仅有一个方法的接口 => 函数式接口（纯功能的，无副作用的）
 *
 * 保证了单一职责化原则，可以有很多好处
 * 最直接的好处可以使用Lambda -> / =>，也是函数式语言Scala的基础
 */
public interface IPureFunction {

    public String echo(String content);

}
