package advanced.linkedlist;

import java.util.HashSet;
import java.util.List;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode() {}
    TreeNode(int val) { this.val = val; }
    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}



public class LinkedListLeetCode {
    // 1367. Linked List in Binary Tree
    // Solved
    // Medium
    // Topics
    // Companies
    // Hint
    // Given a binary tree root and a linked list with head as the first node. 

    // Return True if all the elements in the linked list starting from the head correspond to some downward path connected in the binary tree otherwise return False.

    // In this context downward path means a path that starts at some node and goes downwards.

    // Example 1:

    // Input: head = [4,2,8], root = [1,4,4,null,2,2,null,1,null,6,8,null,null,null,null,1,3]
    // Output: true
    // Explanation: Nodes in blue form a subpath in the binary Tree.  
    // Example 2:

    // Input: head = [1,4,2,6], root = [1,4,4,null,2,2,null,1,null,6,8,null,null,null,null,1,3]
    // Output: true
    // Example 3:

    // Input: head = [1,4,2,6,8], root = [1,4,4,null,2,2,null,1,null,6,8,null,null,null,null,1,3]
    // Output: false
    // Explanation: There is no path in the binary tree that contains all the elements of the linked list from head.

    // Constraints:

    // The number of nodes in the tree will be in the range [1, 2500].
    // The number of nodes in the list will be in the range [1, 100].
    // 1 <= Node.val <= 100 for each node in the linked list and binary tree.


    // Solution by me - 1 ms
    public boolean isSubPath(ListNode head, TreeNode root) {
        if(root == null) {
            return false;
        }
        if(checkPath(head, root)) {
            return true;
        }
        return isSubPath(head, root.left) || isSubPath(head, root.right);
    }

    boolean checkPath(ListNode head, TreeNode root) {
        if(head == null) {
            return true;
        }
        if(root == null || head.val != root.val) {
            return false;
        }
        return checkPath(head.next, root.left) || checkPath(head.next, root.right);
    }

    // Fastest Solution - 0 ms
    public boolean isSubPathFastest(ListNode head, TreeNode root) {
        return dfs(head, head, root);  // Start DFS search
    }

    // DFS helper function
    boolean dfs(ListNode head, ListNode cur, TreeNode root) {
        // If we reach the end of the linked list, return true (successful match)
        if (cur == null) return true;
        // If we reach the end of a path in the tree without fully matching the list, return false
        if (root == null) return false;
        
        // Match the current tree node with the current linked list node
        if (cur.val == root.val) cur = cur.next;
        // If no match, but the tree node matches the head of the linked list, start a new match
        else if (head.val == root.val) head = head.next;
        // Otherwise, reset `cur` to `head` to attempt matching the linked list from scratch
        else cur = head;

        // Continue DFS down both left and right children
        return dfs(head, cur, root.left) || dfs(head, cur, root.right);
    }


    // ----------------------------------------------------------------------




    // 2807. Insert Greatest Common Divisors in Linked List
    // Solved
    // Medium
    // Topics
    // Companies
    // Given the head of a linked list head, in which each node contains an integer value.

    // Between every pair of adjacent nodes, insert a new node with a value equal to the greatest common divisor of them.

    // Return the linked list after insertion.

    // The greatest common divisor of two numbers is the largest positive integer that evenly divides both numbers.

    // Example 1:


    // Input: head = [18,6,10,3]
    // Output: [18,6,6,2,10,1,3]
    // Explanation: The 1st diagram denotes the initial linked list and the 2nd diagram denotes the linked list after inserting the new nodes (nodes in blue are the inserted nodes).
    // - We insert the greatest common divisor of 18 and 6 = 6 between the 1st and the 2nd nodes.
    // - We insert the greatest common divisor of 6 and 10 = 2 between the 2nd and the 3rd nodes.
    // - We insert the greatest common divisor of 10 and 3 = 1 between the 3rd and the 4th nodes.
    // There are no more adjacent nodes, so we return the linked list.
    // Example 2:


    // Input: head = [7]
    // Output: [7]
    // Explanation: The 1st diagram denotes the initial linked list and the 2nd diagram denotes the linked list after inserting the new nodes.
    // There are no pairs of adjacent nodes, so we return the initial linked list.
    

    // Constraints:

    // The number of nodes in the list is in the range [1, 5000].
    // 1 <= Node.val <= 1000


    // Solutionn by me - 2 ms

    public ListNode insertGreatestCommonDivisors(ListNode head) {
        ListNode curr = head;
        while(curr.next!=null) {
            addGCDNode(curr);
            curr = curr.next.next;
        }
        return head;
    }

    private void addGCDNode(ListNode curr) {
        int currVal = curr.val;
        int nextVal = curr.next.val;
        ListNode temp = new ListNode(gcd(currVal, nextVal));

        ListNode next = curr.next;
        curr.next = temp;
        temp.next = next;
    }

    public int gcd(int a, int b){
        if(a == 1 || b==1){
            return 1;
        }
        while(b !=0){
            int rem = a % b;
            a = b;
            b= rem;
        }
        return a;
    }

    private int gcdOld(int a, int b)
    {
        // Find Minimum of a and b
        int result = Math.min(a, b);
        while (result > 0) {
            if (a % result == 0 && b % result == 0) {
                break;
            }
            result--;
        }

        // Return gcd of a and b
        return result;
    }

    // Fastest solution - 1 ms

    public ListNode insertGreatestCommonDivisorsFaster(ListNode head) {
        
        ListNode curr = head;

        while(curr != null && curr.next != null) {
            int hcf = gcdFaster(curr.val, curr.next.val);
            ListNode temp = new ListNode(hcf);
            temp.next = curr.next;
            curr.next = temp;
            curr = curr.next.next;
        }

        return head;
    }


    public int gcdFaster(int a, int b) {
        if(b == 0) {
            return a;
        }
        return gcd(b, a%b);
    }


    // ----------------------------------------------------------------



    // 725. Split Linked List in Parts
    // Solved
    // Medium
    // Topics
    // Companies
    // Hint
    // Given the head of a singly linked list and an integer k, split the linked list into k consecutive linked list parts.

    // The length of each part should be as equal as possible: no two parts should have a size differing by more than one. This may lead to some parts being null.

    // The parts should be in the order of occurrence in the input list, and parts occurring earlier should always have a size greater than or equal to parts occurring later.

    // Return an array of the k parts.

    // Example 1:


    // Input: head = [1,2,3], k = 5
    // Output: [[1],[2],[3],[],[]]
    // Explanation:
    // The first element output[0] has output[0].val = 1, output[0].next = null.
    // The last element output[4] is null, but its string representation as a ListNode is [].
    // Example 2:


    // Input: head = [1,2,3,4,5,6,7,8,9,10], k = 3
    // Output: [[1,2,3,4],[5,6,7],[8,9,10]]
    // Explanation:
    // The input has been split into consecutive parts with size difference at most 1, and earlier parts are a larger size than the later parts.
    

    // Constraints:

    // The number of nodes in the list is in the range [0, 1000].
    // 0 <= Node.val <= 1000
    // 1 <= k <= 50


    // Solution by me - 1 ms

    public ListNode[] splitListToParts(ListNode head, int k) {
        ListNode[] list = new ListNode[k];
        if(head == null) {
            int i = 0;
            while(i < k) {
                list[i++] = null;
            }
            return list;
        }
        int len = 0;
        ListNode curr = head;
        ListNode prev = head;
        while(curr != null) {
            len++;
            curr = curr.next;
        }
        System.out.println(len);
        curr = head;
        int i = 0;
        if(len <= k) {
            while(curr != null) {
                list[i++] = curr;
                prev = curr;
                curr = curr.next;
                prev.next = null;
            }
            while(i<k) {
                list[i++] = null;
            }
        } else {
            int mod = len % k;

            int batchSize = len / k;
            curr = head;
            for(i=0;i<k;i++) {
                int j = 0;
                while(j < batchSize) {
                    if(j == 0) {
                        list[i] = curr;
                    }
                    prev = curr;
                    curr = curr.next;
                    j++;
                }
                if(mod > 0) {
                    prev = curr;
                    curr = curr.next;
                    mod--;
                }
                prev.next = null;
            }
        }
        return list;
    }


    // Faster Solution - 0 ms
    public ListNode[] splitListToPartsFaster(ListNode head, int k) {
        // Step 1: Calculate the total length of the list
        int length = 0;
        ListNode current = head;
        while (current != null) {
            length++;
            current = current.next;
        }
        
        // Step 2: Determine the size of each part
        int partSize = length / k;        // minimum size of each part
        int extraNodes = length % k;      // the remainder, which indicates how many parts will have an extra node
        
        // Step 3: Create the array of k parts
        ListNode[] result = new ListNode[k];
        current = head;
        for (int i = 0; i < k; i++) {
            if (current == null) {
                result[i] = null;  // If there are no nodes left, just set this part as null
                continue;
            }
            
            result[i] = current;  // Set the current node as the head of this part
            
            // Calculate the size of this part (some parts may have one more node than others)
            int currentPartSize = partSize + (extraNodes > 0 ? 1 : 0);
            extraNodes--;
            
            // Move to the last node of the current part
            for (int j = 1; j < currentPartSize; j++) {
                current = current.next;
            }
            
            // Detach the current part from the rest of the list
            ListNode nextPartHead = current.next;
            current.next = null;  // Disconnect the current part
            current = nextPartHead;  // Move to the next part
        }
        
        return result;
    }





    // ---------------------------


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
