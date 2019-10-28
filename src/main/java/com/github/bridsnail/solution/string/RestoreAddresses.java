package com.github.bridsnail.solution.string;

import java.util.*;

/**
 * 回溯法还原ip地址：
 *  输入: "25525511135"
 *  输出: ["255.255.11.135", "255.255.111.35"]
 *
 * 思路：
 *   1. 三个点将字符串划分为4个小段，目标就是确认三个点的位置
 *   2. 每个小段应该满足两个条件
 *      1. 长度不超过3位数且不大于255
 *      2. 当以0为开头时长度必须为1
 *   3. 先确定第一个点，第一个点确定了确定第二个点，当不合格时回溯
 * @author: Mr.Yang
 * @create: 2019-10-09
 */
public class RestoreAddresses {

	public static void main(String[] args) {
		solution("0000").forEach(e -> System.out.println(e));
	}

	public static List<String> solution(String ip){
		List<String> res = new ArrayList<>();
		if (ip.length() < 4) {
			return res;
		}

		backtrack(res,"",ip,0,0);
		return res;
	}

	/**
	 * 第一次划分时，第一个点用下标为0开始，第二个下标为1，第三个点下标为2.
	 * 有小片段不符合要求时就进行回溯，从最后一个点开始前进1。
	 * 每个点最多前进三次
	 *
	 * @param temp 可能的中间结果
	 * @param count 确认的片段数量
	 */
	public static void backtrack(List<String> result, String temp, String ip, int start, int count) {
		// 递归出口
		if (count == 4) {
			if (start > ip.length() - 1) {
				result.add(temp.substring(0, temp.length() - 1));
			}
			return;
		}

		for (int i = start; i <= ip.length() - 1 && i < start + 3; i++) {
			String substring = ip.substring(start, i + 1);
			if (checkSegment(substring)) {
				backtrack(result, temp + substring + ".", ip, i + 1, count + 1);
			}
		}
	}

	/**检查是否符合ip要求*/
	public static boolean checkSegment(String segment){
		if (segment.length() > 3) {
			return false;
		}
		return segment.charAt(0) != '0' ? Integer.valueOf(segment) <=255 : segment.length() ==1;
	}
}
