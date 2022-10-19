// 对一个群体，有重复操作的时候，可选择使用循环

/**
for循环
*/
// for之前的语句
for(初始化语句A; 判断语句B; 控制语句C) {
	// 循环体D，需要重复进行的操作
}
//for之后的语句E
/**
传统for循环的执行流程
第1步：执行A
第2步：判断B的值为true还是false
    如果为true
        第3步：D被执行
        第4步：执行C
        第5步：跳到第2步进行判断
    如果为false
        循环结束，并开始执行for()循环之后的语句E
*/

// 打印： 1~100
for(int i = 1; i <= 100; i++) {
	
	// 对1~100，进行一个遍历
	// 当前被遍历的数字：i
	System.out.println(i);
}
// 累加：1~100
int result = 0;
for(int i = 1; i <= 100; i++) {
	
	// 对1~100，进行一个遍历
	// 当前被遍历的数字：i
	result = result + i;
}
System.out.println(result);

// 累加：1~100的所有偶数
int result = 0;
for(int i = 1; i <= 100; i++) {
	
	// 对1~100，进行一个遍历
	// 当前被遍历的数字：i
	if(i % 2) {
		result = result + i;
	}
}
System.out.println(result);

/**
while循环
*/
// 初始化语句
int i = 1
// 判断语句
while(i <= 100) {
	
	// 循环体
	System.out.println(i);

	// 控制语句
	i++;
}
