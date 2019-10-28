package com.github.bridsnail.solution.linkedlist;

import lombok.ToString;

import java.util.Arrays;
import java.util.Objects;

/**
 * 将两个有序链表合并为一个新的有序链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。
 *
 * 示例：
 *      输入：1->2->4, 1->3->4
 *      输出：1->1->2->3->4->4
 * @author BirdSnail
 * @date 2019/10/23
 */
public class MergerSortLinkedList {

    /**
     * 思路：
     *  递归
     * @return
     */
    public ListNode mergerTwoLinkedList(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }

        // l1的值更小时将 l1.next 与 l2进行合并
        if (l1.val < l2.val) {
            l1.next = mergerTwoLinkedList(l1.next, l2);
            return l1;
        }else {
            l2.next = mergerTwoLinkedList(l1, l2.next);
            return l2;
        }
    }

    public static void main(String[] args) {
        int[] n1_0 = {1, 3, 5, 8, 12};
        int[] n2_0 = {2, 5, 7, 9, 10};


        ListNode n1 = ListNode.getInstance(n1_0);
        printList(n1);
        ListNode n2 = ListNode.getInstance(n2_0);
        printList(n2);

        MergerSortLinkedList solution = new MergerSortLinkedList();
        printList(solution.mergerTwoLinkedList(n1, n2));
    }

    public static void printList(ListNode node) {
        while (node != null) {
            System.out.print(node + "--->");
            node = node.next;
        }
        System.out.println();
    }
}
