package com.github.bridsnail.solution.linkedlist;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author BirdSnail
 * @date 2019/10/24
 */
public class AddTwoNumbers {

    /**
     * 思路：
     *  链表通用的方法：双指针（预先指针，当前指针）
     *  加法的思想：
     *      1. 用一个变量carry保存进位
     *      2. 取两个数的sum加上上一位的进位然后取余，得到当前位置的值。
     *      3. 更新进位数
     *      4. 最后一步要判断最后一次加法是否有进位的情况
     */
    public ListNode add(ListNode l1, ListNode l2) {
        ListNode prev = ListNode.singleListNode(0);
        ListNode curr = prev;
        int carry = 0;

        while (l1 != null || l2 != null) {
            int a = (l1 == null ? 0 : l1.val);
            int b = (l2 == null ? 0 : l2.val);

            int sum = a + b + carry;
            curr.next = ListNode.singleListNode(sum % 10);
            curr = curr.next;
            carry = sum / 10;

            if (l1 != null) {
                l1 = l1.next;
            }

            if (l2 != null) {
                l2 = l2.next;
            }
        }

        if (carry > 0) {
            curr.next = ListNode.singleListNode(carry);
        }

        return prev.next;
    }

    public static void main(String[] args) {
        int[] ary1 = {2};
        int[] ary2 = {1, 2};

        AddTwoNumbers test = new AddTwoNumbers();
        test.add(ListNode.getInstance(ary1, false), ListNode.getInstance(ary2, false))
                .print();
    }
}
