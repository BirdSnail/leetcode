package com.github.bridsnail.solution.string;

/**
 * 给定两个字符串 s1 和 s2，写一个函数来判断 s2 是否包含 s1 的排列。
 *  换句话说，第一个字符串的排列之一是第二个字符串的子串。
 *
 * 思路：
 *  1. s2上使用固定大小的窗口，长度为s1字符串的长度
 *  2. s1和窗口都进行排序然后比较，如果相同可以认为s1一定有一种排列与窗口的子字符串相同
 *  3. 向右移动这个窗口，重复2的排序比较
 *
 * @author: Mr.Yang
 * @create: 2019-09-29
 */
public class CheckInclusion {

	public static void main(String[] args) {
		String s1 = "ab";
		String s2 = "eidboaoo";
		System.out.println(betterSolutionWithArray(s1, s2));
	}

	/**
	 * 没有排序，提前创建好26个字母对应的数组，数组下标是对应的字母排序，值是出现的次数。
	 *
	 * @param s1 短字符串
	 * @param s2 长字符串
	 * @return
	 */
	public static boolean solutionWithArray(String s1, String s2) {
		int[] s1map = new int[26];
		for (int i = 0; i < s1.length(); i++) {
			s1map[s1.charAt(i) - 'a']++;
		}

		for (int i = 0; i <= s2.length() - s1.length(); i++) {
			int[] s2map = new int[26];
			for (int j = i; j < s1.length(); j++) {
				s2map[s2.charAt(j) - 'a']++;
			}

			if (matcher(s1map, s2map)) {
				return true;
			}
		}

		return false;
	}

	private static boolean matcher(int[] a, int[] b) {
		for (int i = 0; i < a.length; i++) {
			if (a[i] != b[i]) {
				return false;
			}
		}

		return true;
	}

	/**
	 * 不用每次都创建窗口，提前创建号一个固定窗口。
	 * 窗口向右移动时，添加新的元素，移除老的元素，相当于一个队列，新的元素进入队列，老元素出队列。
	 * @param s1
	 * @param s2
	 * @return
	 */
	public static boolean betterSolutionWithArray(String s1, String s2) {
		int[] s1map = new int[26];
		int[] s2map = new int[26];

		for (int i = 0; i < s1.length(); i++) {
			s1map[s1.charAt(i) - 'a']++;
			s2map[s2.charAt(i) - 'a']++;
		}

		for (int i = 0; i < s2.length() - s1.length(); i++) {
			if (matcher(s1map, s2map)) {
				return true;
			}

			s2map[s2.charAt(s1.length() + i) - 'a']++;
			s2map[s2.charAt(i) - 'a']--;
		}

		return matcher(s1map, s2map);
	}
}
