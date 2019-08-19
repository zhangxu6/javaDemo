package com.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamTest {

	public static void main(String[] args) {
		// java8 stream

		System.out.println("Java 7");
		System.out.println("===========================================================");
		List<String> strings = Arrays.asList("abc", "", "bc", "efg", "abcd", "", "jkl", "hgygcd", "", "gg", "ytt",
				"tttsa", "", "dyg", "tt", "", "ret", "yu", "", "tsrds", "yuy", "abc", "", "bc", "efg", "abcd", "",
				"jkl", "hgygcd", "", "gg", "ytt", "tttsa", "", "dyg", "tt", "", "ret", "yu", "", "tsrds", "yuy", "abc",
				"", "bc", "efg", "abcd", "", "jkl", "hgygcd", "", "gg", "ytt", "tttsa", "", "dyg", "yusguyge");
		System.out.println("列表: " + strings);
		getCountEmptyStringUsingJava7(strings);
		getCountLengthUsingJava7(strings);
		deleteEmptyStringsUsingJava7(strings);
		getMergedStringUsingJava7(strings, ", ");

		List<Integer> numbers = Arrays.asList(3, 2, 2, 3, 7, 3, 5, 25, 12, 19);
		System.out.println("列表: " + numbers);
		getSquares(numbers);
		getMax(numbers);
		getMin(numbers);
		System.out.println("所有数之和 : " + getSum(numbers));
		getAverage(numbers);
		
		
		System.out.println("Java 8");
		System.out.println("===========================================================");
		System.out.println("列表: " + strings);
		long count=0;
		List<String> listString = new ArrayList<String>();
		//filter 过滤流
		count = strings.stream().filter(string->string.isEmpty()).count();
		System.out.println("空字符数量为:"+count);
		count = strings.stream().filter(string->string.length()==3).count();
		System.out.println("字符串长度为 3 的数量为:  "+count);
		//Collectors 集合
		listString = strings.stream().filter(string->!string.isEmpty()).collect(Collectors.toList());
		System.out.println("筛选后的列表: " + listString);
		String merged = strings.stream().filter(string->!string.isEmpty()).collect(Collectors.joining(", "));
		System.out.println("合并字符串: " + merged);
		List<Integer> listInteger = new ArrayList<Integer>();
		//map 用于映射每个元素到对应的结果  distinct() 去重
		listInteger = numbers.stream().map( i ->i*i).distinct().collect(Collectors.toList());
		System.out.println("Squares List: " + listInteger);
		//IntSummaryStatistics 配合stream使用   用于统计整形数组中元素的最大值，最小值，平均值，个数，元素总和等等
		IntSummaryStatistics stats = numbers.stream().mapToInt((x) ->x).summaryStatistics();
		System.out.println("列表中最大的数 : " + stats.getMax());
	    System.out.println("列表中最小的数 : " + stats.getMin());
	    System.out.println("所有数之和 : " + stats.getSum());
	    System.out.println("平均数 : " + stats.getAverage());
	    
	    System.out.println("随机数: ");
	    Random random = new Random();
	    //forEach迭代  limit获取指定数量的流  sorted排序
	    random.ints().limit(10).sorted().forEach(System.out::println);
	        
	    // 并行处理
	    count = strings.parallelStream().filter(string -> string.isEmpty()).count();
	    System.out.println("空字符串的数量为: " + count);
	    
	    listString = strings.parallelStream().filter(string->!string.isEmpty()).collect(Collectors.toList());
		System.out.println("筛选后的列表: " + listString);
		
		
		

	}

	private static void getCountEmptyStringUsingJava7(List<String> strings) {
		long startTime = System.currentTimeMillis();
		int count = 0;
		for (String string : strings) {
			if (string.isEmpty()) {
				count++;
			}
		}
		System.out.println("空字符数量为: " + count);
		long endTime = System.currentTimeMillis();
		// float excTime=(float)(endTime-startTime)/1000;
		System.out.println("执行时间：" + (endTime - startTime) + "ms");
	}

	private static void getCountLengthUsingJava7(List<String> strings) {
		long startTime = System.currentTimeMillis();
		int count = 0;
		for (String string : strings) {
			if (string.length() == 3) {
				count++;
			}
		}
		System.out.println("字符串长度为 3 的数量为:  " + count);
		long endTime = System.currentTimeMillis();
		System.out.println("执行时间：" + (endTime - startTime) + "ms");
	}

	private static void deleteEmptyStringsUsingJava7(List<String> strings) {
		long startTime = System.currentTimeMillis();
		List<String> filteredList = new ArrayList<String>();

		for (String string : strings) {
			if (!string.isEmpty()) {
				filteredList.add(string);
			}
		}
		System.out.println("删除空字符串后的列表: " + filteredList);
		long endTime = System.currentTimeMillis();
		System.out.println("执行时间：" + (endTime - startTime) + "ms");
	}

	private static void getMergedStringUsingJava7(List<String> strings, String separator) {
		long startTime = System.currentTimeMillis();
		StringBuilder stringBuilder = new StringBuilder();

		for (String string : strings) {

			if (!string.isEmpty()) {
				stringBuilder.append(string);
				stringBuilder.append(separator);
			}
		}
		String mergedString = stringBuilder.toString();
		System.out.println("合并字符串: " + mergedString.substring(0, mergedString.length() - 2));
		long endTime = System.currentTimeMillis();
		System.out.println("执行时间：" + (endTime - startTime) + "ms");
	}

	private static void getSquares(List<Integer> numbers) {
		long startTime = System.currentTimeMillis();
		List<Integer> squaresList = new ArrayList<Integer>();

		for (Integer number : numbers) {
			Integer square = new Integer(number.intValue() * number.intValue());

			if (!squaresList.contains(square)) {
				squaresList.add(square);
			}
		}
		System.out.println("平方数列表:" + squaresList);
		long endTime = System.currentTimeMillis();
		System.out.println("执行时间：" + (endTime - startTime) + "ms");
	}

	private static void getMax(List<Integer> numbers) {
		long startTime = System.currentTimeMillis();
		int max = numbers.get(0);

		for (int i = 1; i < numbers.size(); i++) {

			Integer number = numbers.get(i);

			if (number.intValue() > max) {
				max = number.intValue();
			}
		}
		System.out.println("列表中最大的数 : " + max);
		long endTime = System.currentTimeMillis();
		System.out.println("执行时间：" + (endTime - startTime) + "ms");
	}

	private static void getMin(List<Integer> numbers) {
		long startTime = System.currentTimeMillis();
		int min = numbers.get(0);

		for (int i = 1; i < numbers.size(); i++) {
			Integer number = numbers.get(i);

			if (number.intValue() < min) {
				min = number.intValue();
			}
		}
		System.out.println("列表中最小的数 : " + min);
		long endTime = System.currentTimeMillis();
		System.out.println("执行时间：" + (endTime - startTime) + "ms");
	}

	private static int getSum(List<Integer> numbers) {
		int sum = (int) (numbers.get(0));

		for (int i = 1; i < numbers.size(); i++) {
			sum += (int) numbers.get(i);
		}
		return sum;
	}

	private static void getAverage(List<Integer> numbers) {
		long startTime = System.currentTimeMillis();
		System.out.println("平均数 : " + getSum(numbers) / numbers.size());
		long endTime = System.currentTimeMillis();
		System.out.println("执行时间：" + (endTime - startTime) + "ms");
	}

}
