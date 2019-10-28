package com.github.bridsnail.solution.linkedlist;

/**
 * 反转一个链表
 *
 * @author BirdSnail
 * @date 2019/10/23
 */
public class ReverseLinkedList {

    public static void main(String[] args) {
        int[] vals = {1, 2, 5, 6, 7};
        ListNode node = ListNode.getInstance(vals);
//        ListNode.printList(node);
        node.print();
        System.out.println();

        ReverseLinkedList obj = new ReverseLinkedList();
        obj.reverse(node).print();
    }

    /**
     * 思路：
     *  递归
     *  1. 先递归到最后一个节点
     *  2. 在每一层递归上都保留了现场即进入递归的元素。
     *  3. 我们将在现场的元素的下一个元素指向自己，这就实现了他前一个元素的反转.
     *      <code> curr.next.next = curr;</code>
     *    同时要改变当前元素的下一个元素指向，不然链表会变成环
     *      <code> curr.next = null </code>
     */
    public ListNode reverse(ListNode node) {
        if (node == null || node.next == null) {
            return node;
        }

        ListNode end = reverse(node.next);
        node.next.next = node;
        node.next = null;
        return end;
    }

    /**
     * 思路；
     *   迭代，双指针。
     *   一个指针为前指针，指向当前元素的前一个元素，
     *   一个指针指向当前进行迭代的元素。
     *   1. 获取当前元素的下一个元素 next
     *      prev   curr --> next
     *   2. 当前元素的下一个元素指向前指针
     *      prev <-- curr   next
     *   3. 将当前元素赋值给prev，next赋值给curr进行迭代
     */
    public ListNode reverse1(ListNode node) {
        ListNode pre = null;
        ListNode curr = node;
        while (curr != null) {
            ListNode next = curr.next;
            curr.next = pre;
            pre = curr;
            curr = next;
        }

        return pre;
    }
}
