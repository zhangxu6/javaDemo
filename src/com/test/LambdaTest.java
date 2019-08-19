package com.test;

public class LambdaTest {

	public static void main(String[] args) {
		// java lambda

		/*
		 * lambda的结构有两种： (parameters) -> expression (parameters) -> { statements; }
		 * 
		 * parameters：参数，多个以逗号隔开。 expression ：语句，即一句话或者一个操作。 statements ：陈述，一个或多个语句。
		 * 
		 * lambda只能在函数式接口中使用，而函数式接口概括起来就是只有一个抽象方法的接口
		 * 
		 * lambda 表达式只能引用标记了 final 的外层局部变量
		 */

		LambdaTest tester = new LambdaTest();

		// 类型声明
		MathOperation addition = (int a, int b) -> a + b;

		// 不用类型声明
		MathOperation subtraction = (a, b) -> a - b;

		// 大括号中的返回语句
		MathOperation multiplication = (int a, int b) -> {
			return a * b;
		};

		// 没有大括号及返回语句
		MathOperation division = (int a, int b) -> a / b;

		System.out.println("10 + 5 = " + tester.operate(10, 5, addition));
		System.out.println("10 - 5 = " + tester.operate(10, 5, subtraction));
		System.out.println("10 x 5 = " + tester.operate(10, 5, multiplication));
		System.out.println("10 / 5 = " + tester.operate(10, 5, division));

		// 不用括号
		GreetingService greetService1 = message -> System.out.println("Hello " + message);

		// 用括号
		GreetingService greetService2 = (message) -> System.out.println("Hello " + message);

		greetService1.sayMessage("Runoob");
		greetService2.sayMessage("Google");

	}

	interface MathOperation {
		int operation(int a, int b);
	}

	interface GreetingService {
		void sayMessage(String message);
	}

	private int operate(int a, int b, MathOperation mathOperation) {
		return mathOperation.operation(a, b);
	}

}
