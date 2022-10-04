package com.liu.cases;

/**
 * @Author 老刘Matthew
 * @Timestamp 2022/8/10 08:32
 * @Description
 *
 * 普通接口，一个接口带多个方法，非单一职责，即"不纯"
 * 即"违反"了单一职责化原则
 * 单一职是理论上"一个接口只负责一件事（一个唯一方法）"，这很形式化
 *
 */
public interface INonPureFunction {

    public void say();

    public String echo(String content);
}
