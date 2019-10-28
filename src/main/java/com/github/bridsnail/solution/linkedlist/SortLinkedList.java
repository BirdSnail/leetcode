package com.github.bridsnail.solution.linkedlist;

/**
 * @author BirdSnail
 * @date 2019/10/28
 */
public class SortLinkedList {

    public static void main(String[] args) {
        ListNode node = ListNode.getInstance(new int[]{4, 2, 1, 3}, false);
//        ListNode node = ListNode.getInstance(new int[]{1, 8, 6, 4, 5});
        node.print();
        System.out.println();

        SortLinkedList test = new SortLinkedList();
        test.sort(node).print();
//        test.merge(node, ListNode.getInstance(new int[]{2, 3})).print();
//        test.cut(node , 8).print();
    }


    /**
     * 思路：
     *  迭代，自底向上进行排序。本质思想是归并排序。
     *  <p>还是需要一个预先指针pre，并且pre.next始终指向merge合并后的头。
     *  另外需要一个tail指针，用于将剪断的链表重新连接起来。</p>
     *  1. 先进行元素大小为1的排序
     *  2. 局部有序后进行大小为2的排序。
     *  关于链表的主要两个技术:
     *  1. merge：合并连个有序的链表
     *  2. cut(node, n)：链表的摘除操作
     *
     * @param node
     * @return
     */
    public ListNode sort(ListNode node) {
        ListNode dumy = ListNode.singleListNode(Integer.MIN_VALUE);
        dumy.next = node;

        int length = 0;
        ListNode p = node;
        while (p != null) {
            p = p.next;
            length++;
        }

        for (int size = 1; size < length; size <<= 1) {
            ListNode cur = dumy.next;
            ListNode tail = dumy; // 可以使dumy连接到最新的节点头
            // 以size个元素进行merge
            // size为1时 （1，2） ->（3，4） - （5，6）-（7，8）
            // size长度为2时（1->2）（3->4）-（5->6）（7->8）
            while (cur != null) {
                ListNode left = cur;
                ListNode right = cut(cur, size); // right的起始位置
                cur = cut(right, size); // 将size个元素从原始链表中拆除，将拆除下来的元素进行合并
                tail.next = merge(left, right); // 将之前剪断的链表连接回来，重新连接起来的链表局部是有序的 // dumy.next = merge(left, right)
                while (tail.next != null) {
                    tail = tail.next;
                }
            }
        }
        return dumy.next;
    }

    /**
     * 摘除链表前 n 个元素
     * @param head
     * @param n
     * @return 剪短过后剩余的链表头
     */
    private ListNode cut(ListNode head, int n) {
        if (n <= 0) {
            return head;
        }
        ListNode p = head;
        while (p != null && --n > 0) {
            p = p.next;
        }
        if (p == null) {
            return null;
        }

        ListNode next = p.next;
        p.next = null; // 剪断
        return next;
    }

    /**
     * 合并两个有序的链表，返回链表头
     * @return
     */
    public ListNode merge(ListNode l1, ListNode l2) {
        ListNode pre = ListNode.singleListNode(0);
        ListNode cur = pre;
        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                cur.next = l1;
                cur = cur.next;
                l1 = l1.next;
            } else {
                cur.next = l2;
                cur = cur.next;
                l2 = l2.next;
            }
        }

        if (l1 == null) {
            cur.next = l2;
        } else {
            cur.next = l1;
        }
        return pre.next;
    }
}
