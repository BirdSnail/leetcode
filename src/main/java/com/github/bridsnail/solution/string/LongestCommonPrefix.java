package com.github.bridsnail.solution.string;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 若干个字符串的最长公共前缀,如果不存在返回 "".
 *  输入: ["flower","flow","flight"]
 *  输出: "fl"
 *
 *  输入: ["dog","racecar","car"]
 *  输出: ""flower
 *  解释: 输入不存在公共前缀。
 *
 * @author: Mr.Yang
 * @create: 2019-09-29
 */
public class LongestCommonPrefix {

	public static void main(String[] args) {
		String[] contents1 = {"flower","flow","flight"};
		String[] contents2= {"dog","racecar","car"};
		System.out.println(solution(contents1));
	}

	public static String solution(String[] strs) {
		if (strs.length == 0) {
			return "";
		}

		List<Character> prefix = new ArrayList<Character>();
		String base = strs[0];

		for (int i = 0; i < base.length(); i++) {
			char ch = base.charAt(i);
			for (int j = 0; j < strs.length; j++) {
				if (i >= strs[j].length() || ch != strs[j].charAt(i)) {
					return transToString(prefix);
				}
			}
			prefix.add(ch);
		}

		return transToString(prefix);
	}

	public static String transToString(List<Character> prefix){
		if (prefix.size() == 0) {
			return "";
		}

		final StringBuilder result = new StringBuilder();
		for (Character ch : prefix) {
			result.append(ch);
		}

		return result.toString();
	}
}
