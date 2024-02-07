package advanced.linkedlist;

public class LinkedList2 {
    // Remove Loop from Linked List
     //You are given a linked list that contains a loop.
     //You need to find the node, which creates a loop and break it by making the node point to NULL.
     //Problem Constraints
     //1 <= number of nodes <= 1000
     //Input Format
     //The first of the input contains a LinkedList, where the first number is the number of nodes N, and the next N nodes are the node value of the linked list.
     //The second line of the input contains an integer which denotes the position of node where cycle starts.
     //Output Format
     //return the head of the updated linked list.
     //Example Input
     //Input 1:
     //1 -> 2
     //^    |
     //| - -
     //Input 2:
     //
     //3 -> 2 -> 4 -> 5 -> 6
     //          ^         |
     //          |         |
     //          - - - - - -
     //
     //Example Output
     //Output 1:
     // 1 -> 2 -> NULL
     //Output 2:
     // 3 -> 2 -> 4 -> 5 -> 6 -> NULL
     //Example Explanation
     //Explanation 1:
     // Chain of 1->2 is broken.
     //Explanation 2:
     // Chain of 4->6 is broken.
    public void loopRemove(ListNode slow, ListNode fast, ListNode head) {
        slow = head;
        if(slow != fast) {
            while(slow.next != fast.next) {
                slow = slow.next;
                fast = fast.next;
            }
            fast.next = null;
        } else {
            while(fast.next != slow) {
                fast = fast.next;
            }
            fast.next = null;
        }
    }
    public ListNode solve(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        boolean skip = false;
        while(fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if(slow == fast ) {
                loopRemove(slow, fast, head);
            }
        }
        return head;
    }

    // Solution provided
    // public ListNode solve(ListNode A) {
    //        detectAndRemoveLoop(A);
    //        return A;
    //    }
    //    int detectAndRemoveLoop(ListNode node) {
    //        ListNode slow = node, fast = node;
    //        while (slow != null && fast != null && fast.next != null) {
    //            slow = slow.next;
    //            fast = fast.next.next;
    //
    //            // If slow and fast meet at same point then loop is present
    //            if (slow == fast) {
    //                removeLoop(slow, node);
    //                return 1;
    //            }
    //        }
    //        return 0;
    //    }
    //
    //    // Function to remove loop
    //    void removeLoop(ListNode loop, ListNode curr) {
    //        ListNode ptr1 = null;
    //        ListNode ptr2 = null;
    //        /* Set a pointer to the beginning of the Linked List and
    //         move it one by one to find the first node which is
    //         part of the Linked List */
    //        ptr1 = curr;
    //        while (1 == 1) {
    //            /* Now start a pointer from loop_node and check if it ever
    //             reaches ptr2 */
    //            ptr2 = loop;
    //            while (ptr2.next != loop && ptr2.next != ptr1) {
    //                ptr2 = ptr2.next;
    //            }
    //            /* If ptr2 reahced ptr1 then there is a loop. So break the
    //             loop */
    //            if (ptr2.next == ptr1) {
    //                break;
    //            }
    //            /* If ptr2 did't reach ptr1 then try the next node after ptr1 */
    //            ptr1 = ptr1.next;
    //        }
    //        /* After the end of loop ptr2 is the last node of the loop. So
    //         make next of ptr2 as NULL */
    //        ptr2.next = null;
    //    }
}
