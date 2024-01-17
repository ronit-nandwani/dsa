package advanced.linkedlist;

import java.util.LinkedList;

// Design and implement a Linked List data structure.
//A node in a linked list should have the following attributes - an integer value and a pointer to the next node.
//It should support the following operations:
//insert_node(position, value) - To insert the input value at the given position in the linked list.
//delete_node(position) - Delete the value at the given position from the linked list.
//print_ll() - Print the entire linked list, such that each element is followed by a single space (no trailing spaces).
//Note:
//If an input position does not satisfy the constraint, no action is required.
//Each print query has to be executed in a new line.
//Problem Constraints
//1 <= value <= 105
//1 <= position <= n where, n is the size of the linked-list.
//Input Format
//First line contains an integer denoting number of cases, let's say t. Next t line denotes the cases.
//Output Format
//When there is a case of print_ll(), Print the entire linked list, such that each element is followed by a single space.
//There should not be any trailing space.
//NOTE: You don't need to return anything.
//Example Input
//Input 1:
//5
//i 1 23
//i 2 24
//p
//d 1
//p
//Input 2:
//3
//i 1 54
//d 10
//p
//Example Output
//Output 1:
//23 24
//24
//Output 2:
//54
//Example Explanation
//Explanation 1:
//After first two cases linked list contains two elements 23 and 24.
//At third case print: 23 24.
//At fourth case delete value at first position, only one element left 24.
//At fifth case print: 24.
//Explanation 2:
//After the first case,  linked list contains: 54
//At second case delete value at 10th position,
//Since the position is higher than the length of the linkedlist.
//It does not satisfy the constraint, So no action is required.
//At third case print: 54.
class LL {

    static class Node {
        public int val;
        public Node next;

        Node(int x) {
            val = x;
            next = null;
        }
    }

    public static Node head = null;

    public static void insert_node(int position, int value) {
        // @params position, integer
        // @params value, integer
        Node newNode = new Node(value);
        if (position == 1) {
            newNode.next = head;
            head = newNode;
        } else {
            Node temp = head;
            for (int i = 0; i < position - 2 && temp != null; i++) {
                temp = temp.next;
            }
            if (temp != null) {
                newNode.next = temp.next;
                temp.next = newNode;
            }
        }
    }

    public static void delete_node(int position) {
        // @params position, integer
        if (head == null)
            return;
        if (position == 1) {
            head = head.next;
        } else {
            Node temp = head;
            int pos = 1;
            while (temp != null && temp.next != null) {
                if ((pos++) == (position - 1)) {
                    temp.next = temp.next.next;
                    break;
                }
                temp = temp.next;
            }
        }
    }

    public static void print_ll() {
        // Output each element followed by a space
        if (head == null)
            return;
        Node temp = head;
        while (temp.next != null) {
            System.out.print(temp.val + " ");
            temp = temp.next;
        }
        System.out.print(temp.val);
    }
}

// Definition for singly-linked list.
class ListNode {
    public int val;
    public ListNode next;

    ListNode(int x) {
        val = x;
        next = null;
    }
}

public abstract class LinkedList1 {

    // You are given a singly linked list having head node A. You have to reverse
    // the linked list and return the head node of that reversed list.
    // NOTE: You have to do it in-place and in one-pass.
    // Problem Constraints
    // 1 <= Length of linked list <= 105
    // Value of each node is within the range of a 32-bit integer.
    // Input Format
    // First and only argument is a linked-list node A.
    // Output Format
    // Return a linked-list node denoting the head of the reversed linked list.
    // Example Input
    // Input 1:
    // A = 1 -> 2 -> 3 -> 4 -> 5 -> NULL
    // Input 2:
    // A = 3 -> NULL
    // Example Output
    // Output 1:
    // 5 -> 4 -> 3 -> 2 -> 1 -> NULL
    // Output 2:
    // 3 -> NULL
    // Example Explanation
    // Explanation 1:
    // The linked list has 5 nodes. After reversing them, the list becomes : 5 -> 4
    // -> 3 -> 2 -> 1 -> NULL
    // Expalantion 2:
    // The linked list consists of only a single node. After reversing it, the list
    // becomes : 3 -> NULL
    public ListNode reverseList(ListNode A) {
        ListNode pre = null;
        ListNode curr = A;
        while (curr != null) {
            ListNode nxt = curr.next;
            curr.next = pre;
            pre = curr;
            curr = nxt;
        }
        A = pre;
        return A;
    }
}
