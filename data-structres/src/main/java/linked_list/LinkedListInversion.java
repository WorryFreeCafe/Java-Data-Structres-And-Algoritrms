package linked_list;

/**
 * 链表反转
 */
public class LinkedListInversion {

    private class ListNode {
        int value;
        ListNode next;

        ListNode(int key, int value) {
            this.value = value;
            this.next = null;
        }
    }
    public ListNode reverseList(ListNode head) {
        ListNode prev = null;
        ListNode current = head;
        while (current != null) {
            ListNode nextNode = current.next;
            current.next = prev;
            prev = current;
            current = nextNode;
        }
        return prev;
    }
}
