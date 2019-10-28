package com.github.bridsnail.solution.linkedlist;

import java.util.Arrays;

/**
 * 定义链表节点
 * @author BirdSnail
 * @date 2019/10/23
 */
public class ListNode {
    public int val;
    public ListNode next;

    private ListNode(int val) {
        this.val = val;
    }

    public void print() {
        System.out.print("val:" + val + "--->");
        if (next != null) {
            next.print();
        }
    }

    /**
     * 返回一个单一的实例
     * @return
     */
    public static ListNode singleListNode(int val) {
        return new ListNode(val);
    }

    public static ListNode getInstance(int[] val, boolean isSort) {
        if (val.length == 0) {
            throw new RuntimeException("不能为空数组");
        }
        if (isSort) {
            Arrays.sort(val);
        }

        ListNode[] nodes = new ListNode[val.length];
        for (int i = 0; i < val.length; i++) {
            nodes[i] = new ListNode(val[i]);
        }

        for (int i = 0; i < val.length - 1; i++) {
            nodes[i].next = nodes[i + 1];
        }

        return nodes[0];
    }

    public static ListNode getInstance(int[] val) {
        return getInstance(val, true);
    }

    public static void printList(ListNode node) {
        while (node != null) {
            System.out.print(node + "--->");
            node = node.next;
        }
        System.out.println();
    }

    @Override
    public String toString() {
        return "val:" + val;
    }
}
