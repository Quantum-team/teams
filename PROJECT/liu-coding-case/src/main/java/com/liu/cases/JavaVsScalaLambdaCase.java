package com.liu.cases;

/**
 * @Author 老刘Matthew
 * @Timestamp 2022/8/10 08:30
 * @Description
 */
public class JavaVsScalaLambdaCase {

    // TODO
    //  Java OOP
    //  - 接口给类扩展职责
    //  垂直 继承 一条路走到底，不利于扩展
    //  一个普通学生，学文科、理科、艺术，粒度很粗
    //  横向 "继承" 扩展/实现 implements
    //  以理工科当中的某一职责/功能为例，比如Java编程，粒度细化，用于给普通学生赋予职责
    //  接口支持多"继承"
    //  interface就是表示一组职责abstract method的"class"，interface地位与class相当
    //  abstract 只有定义，没有实现，试卷的题就是抽象的
    //  interface的实现类来（*覆盖/重写/override <-> 重载overload）实现abstract method
    //  接口不能实例化，也就是不能new
    //  /
    //  匿名内部类 => lambda / ƛ
    //


    static final String HELLO = "hello";


    // 重载overload
    public void call(INonPureFunction function) {

        function.say();

        String content = function.echo(HELLO);
    }

    // 重载overload
    public void call(IPureFunction function) {

        function.echo(HELLO);
    }

    public static void main(String[] args) {

        JavaVsScalaLambdaCase caze = new JavaVsScalaLambdaCase();

        // TODO 实现接口，基础式，声明类
        INonPureFunction nonPureFunction_1 = new NonPureFunctionImpl();
        caze.call(new NonPureFunctionImpl());

        IPureFunction pureFunction_1 = new PureFunctionImpl();
        caze.call(pureFunction_1);

        // TODO 实现接口，匿名内部类式，一次性地声明类
        caze.call(new INonPureFunction() {

            @Override
            public void say() {
                System.out.println("你好！");
            }

            @Override
            public String echo(String content) {

                return content;
            }
        });

        caze.call(new IPureFunction() {

            @Override
            public String echo(String content) {
                return content;
            }
        });

        // TODO 实现接口，函数式，lambda / ƛ，仅纯接口可用
        caze.call(o ->  "this is: " + o);

        caze.call(o -> {

            System.out.println("做点别的事。");

            return o;
        });

    }
}

class NonPureFunctionImpl implements INonPureFunction {

    @Override
    public void say() {
        System.out.println("hello!");
    }

    @Override
    public String echo(String content) {

        return content;
    }
}

class PureFunctionImpl implements IPureFunction {

    @Override
    public String echo(String content) {

        return content;
    }
}