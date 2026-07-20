/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode insertionSortList(ListNode head) {
        ListNode res = new ListNode(), now = head;

        while (now != null) {
            ListNode prev = res;
            while (prev.next != null && prev.next.val < now.val) {
                prev = prev.next;
            }

            prev.next = new ListNode(now.val, prev.next);
            now = now.next;
        }

        return res.next;
    }
}