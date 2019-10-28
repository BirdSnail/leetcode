package com.github.bridsnail.solution.string;

import java.util.*;

/**
 * 字符串的最长子串长度
 *  输入: "abcabcbb"
 *  输出: 3
 *  解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
 *
 *  解法：
 *      1：暴力破解法，两个for循环，检查每个子字符串。
 *      2. 构建一个滑动窗口，检查后续的元素是否在窗口中
 *  
 * @author: Mr.Yang
 * @create: 2019-09-27
 */
public class LengthOfLongestSubstring {

	public static void main(String[] args) {
		String s = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
		//solutionWithForeach(s);
		//System.out.println(solutionWithSlideWindow("abcbd"));
		System.out.println(betterSolutionWithSlideWindow("abcbd"));
	}

	/**暴力破解，遍历所有的可能的子字符串*/
	public static void solutionWithForeach(String s) {
		int length = 0;
		List<Character> result = new ArrayList<Character>();

		for (int i = 0; i < s.length(); i++) {
			for (int j = i; j < s.length(); j++) {
				char ch = s.charAt(j);
				if (result.contains(ch)) {
					if (result.size() > length) {
						length = result.size();
					}
					result.clear();
				}
				result.add(ch);
			}
			if (result.size() > length) {
				length = result.size();
			}
			result.clear();
		}

		System.out.println(length);
	}

	/**滑动窗口的解法*/
	public static int solutionWithSlideWindow(String s) {
		int start = 0, stop = 0, length = 0;
		Set<Character> slidewindowOfChar = new HashSet<Character>();

		while (start < s.length() && stop < s.length()) {
			// 包含重复字符窗口长度就减1
			if (slidewindowOfChar.contains(s.charAt(stop))) {
				slidewindowOfChar.remove(s.charAt(start++));
			}else{ // 不再窗口中就右边界加1
				slidewindowOfChar.add(s.charAt(stop++));
				length = Math.max(length, stop - start);
			}
		}

		return length;
	}

	/**
	 * 直接移动窗口的左边界到重复元素的后面，再增长右边界。
	 * 右边界一直再增长，因此每次都需要计算最大的长度
	 * @param s
	 * @return
	 */
	public static int betterSolutionWithSlideWindow(String s) {
		int length = 0;
		Map<Character, Integer> map = new HashMap<Character, Integer>();

		for (int i = 0,j = 0; j < s.length(); j++) {
			if (map.containsKey(s.charAt(j))) {
				i = Math.max(map.get(s.charAt(j)) + 1, i);
			}

			length = Math.max(length, j - i + 1);
			map.put(s.charAt(j), j);
		}

		return length;
	}
}
