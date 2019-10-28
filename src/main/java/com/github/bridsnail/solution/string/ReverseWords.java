package com.github.bridsnail.solution.string;


/**
 * 反转单词：
 *  输入: "a good   example"
 *  输出: "example good a"
 *
 * 说明：
 *  1. 无空格字符构成一个单词。
 *  2. 输入字符串可以在前面或者后面包含多余的空格，但是反转后的字符不能包括。
 *  3. 如果两个单词间有多余的空格，将反转后单词间的空格减少到只含一个。
 *
 *
 * @author: Mr.Yang
 * @create: 2019-09-30
 */
public class ReverseWords {

	public static void main(String[] args) {
		System.out.println(solution("  hello world!  "));
	}

	public static String solution(String s) {
		String[] words = s.split("\\s+");
		StringBuilder res = new StringBuilder();

		for (int i = words.length - 1; i >= 0; i--) {
			if (words[i].length() ==0){
				continue;
			}
			res.append(words[i]);
			res.append(" ");

		}
		return res.toString().trim();
	}
}
