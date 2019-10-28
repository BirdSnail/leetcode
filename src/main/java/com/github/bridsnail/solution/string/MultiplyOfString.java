package com.github.bridsnail.solution.string;

import java.util.Arrays;

/**
 * 两个整数字符串相乘，不使用BigInteger，不直接转成整数
 *
 * 思路：竖式相乘，使用一个数组保存结果，数组长度为两个字符串长度之和，因为两数相乘，结果长度不会超过两数长度之和。
 *  1. 从右向左遍历两个字符串
 *  2. 每遍历一次可以确定两个位置的数。num1[i] x num2[j] 的结果为 tmp(位数为两位，"0x","xy"的形式)，
 *     其第一位位于 res[i+j],第二位位于res[i+j+1]
 *
 * @author: Mr.Yang
 * @create: 2019-09-30
 */
public class MultiplyOfString {

	public static void main(String[] args) {
		System.out.println(solution("15", "12"));
	}

	public static String solution(String num1, String num2) {
		int[] result = new int[num1.length() + num2.length()];
		Arrays.fill(result,'0'); // 填充默认值

		for (int i = num2.length() - 1; i >= 0; i--) {
			int base = num2.charAt(i) - '0';
			for (int j = num1.length() - 1; j >= 0; j--) {
				// 加上当前位原有的数，提前进位
				int temp = base * (num1.charAt(j) - '0') + result[i + j + 1] - '0';
				// 当前位，取乘法结果的余数
				result[i + j + 1] = temp % 10 + '0';
				// 前进位,取乘法结果的高位
				result[i + j] += temp / 10;
			}
		}

		return transToString(result);
	}

	private static String transToString(int[] array) {
		for (int i = 0; i < array.length; i++) {
			if ((array[i] - '0') != 0){ // 有效数字的起始位置
				StringBuilder sb = new StringBuilder();
				for (int v : Arrays.copyOfRange(array, i, array.length)) {
					sb.append(v - '0');
				}
				return sb.toString();
			}
		}

		return "0";
	}
}
