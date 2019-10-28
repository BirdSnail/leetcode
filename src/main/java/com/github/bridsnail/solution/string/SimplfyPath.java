package com.github.bridsnail.solution.string;

import java.util.Arrays;
import java.util.Stack;

/**
 * 简化Unix上的文件路径，如：
 *  输入："/a/../../b/../c//.//"
 *  输出："/c"
 *
 *  输入："/a//b////c/d//././/.."
 *  输出："/a/b/c"
 *
 *  注意：
 *     最后一个目录名后面没有斜杠。
 *     从根目录向上一级是不可行的，因为根是你可以到达的最高级。
 *     在规范路径中，多个连续斜杠需要用一个斜杠替换。
 *
 *  思路：
 *      使用栈结构，‘a’目录入栈，".."代表出栈
 * @author: Mr.Yang
 * @create: 2019-09-30
 */
public class SimplfyPath {

	public static void main(String[] args) {
		System.out.println(solution("/a/./b/../../c/"));
	}

	public static String solution(String path) {
		Stack<String> dic = new Stack<>();
		String[] temp = path.split("/+");
		System.out.println(Arrays.toString(temp));

		for (String s : temp) {
			if (s.length() != 0) {
				switch (s) {
					case ".":
						break;
					case "..":
						if (!dic.empty())
							dic.pop();
						break;
					default:
						dic.push(s);
				}
			}
		}

		return transToString(dic);
	}

	private static String transToString(Stack<String> dic) {
		if (dic.empty()) {
			return "/";
		}

		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < dic.size(); i++) {
			sb.append("/");
			sb.append(dic.get(i));
		}

		return sb.toString();
	}
}
