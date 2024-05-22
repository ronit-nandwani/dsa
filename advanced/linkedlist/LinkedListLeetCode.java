package advanced.linkedlist;

import java.util.HashSet;
import java.util.List;

public class LinkedListLeetCode {
    // 3217. Delete Nodes From Linked List Present in Array
    // Solved
    // Medium
    // Topics
    // Companies
    // Hint
    // You are given an array of integers nums and the head of a linked list. Return the head of the modified linked list after removing all nodes from the linked list that have a value that exists in nums.

    

    // Example 1:

    // Input: nums = [1,2,3], head = [1,2,3,4,5]

    // Output: [4,5]

    // Explanation:



    // Remove the nodes with values 1, 2, and 3.

    // Example 2:

    // Input: nums = [1], head = [1,2,1,2,1,2]

    // Output: [2,2,2]

    // Explanation:



    // Remove the nodes with value 1.

    // Example 3:

    // Input: nums = [5], head = [1,2,3,4]

    // Output: [1,2,3,4]

    // Explanation:



    // No node has value 5.

    

    // Constraints:

    // 1 <= nums.length <= 105
    // 1 <= nums[i] <= 105
    // All elements in nums are unique.
    // The number of nodes in the given list is in the range [1, 105].
    // 1 <= Node.val <= 105
    // The input is generated such that there is at least one node in the linked list that has a value not present in nums.


    // Solution by me - 20 ms
    public ListNode modifiedList(int[] nums, ListNode head) {
        HashSet<Integer> hs = new HashSet<>();
        for (int v : nums) {
            hs.add(v);
        }
        // len = 1
        // head return null

        // len = 2
        // head return new head i.e. head.next
        // 2nd return head and change it's pointign to 2.next

        // len = 3
        // head return new head i.e. head.next
        // 2nd return head and change it's pointign to 2.next
        // 3rd return head and change it's pointign to 3.next

        ListNode prev = null;
        ListNode curr = head;


        while(curr != null) {
            if(hs.contains(curr.val)) {
                if(prev == null) {
                 head = head.next;
                } else {
                    prev.next = curr.next;
                }
                curr = curr.next;
            } else {
                prev = curr;
                curr = curr.next;
            }
        }

        return head;
    }

    // Fastest Solution - 3 ms
    public ListNode modifiedListLatest(int[] nums, ListNode head) {
        int max = 0;
        for(int n:nums){
            if(n > max){
                max = n;
            }
        }
        boolean[] seen = new boolean[max+1];

        for(int n:nums){
            seen[n] = true;
        }
        while(head != null && head.val <= max && seen[head.val]){
            head = head.next;
        }
        if(head == null){
            return head;
        }
        ListNode curr = head.next, prev = head;
        while(curr != null){
            if(curr.val <= max && seen[curr.val]){
                prev.next = curr.next;
            }else{
                prev = curr;
            }
            curr = curr.next;
        }        
        return head;
    }
}
