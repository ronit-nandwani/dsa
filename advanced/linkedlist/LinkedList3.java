package advanced.linkedlist;

import java.util.HashMap;
 class ListNode1 {
      public int val;
      public ListNode1 next;
      ListNode1(int x) { val = x; next = null; }
}

public class LinkedList3 {
    // Partition List
    // Given a linked list A and a value B, partition it such that all nodes less than B come before nodes greater than or equal to B.

    // You should preserve the original relative order of the nodes in each of the two partitions.

    // Problem Constraints
    // 1 <= |A| <= 106

    // 1 <= A[i], B <= 109

    // Input Format
    // The first argument of input contains a pointer to the head to the given linked list.

    // The second argument of input contains an integer, B.

    // Output Format
    // Return a pointer to the head of the modified linked list.

    // Example Input
    // Input 1:

    // A = [1, 4, 3, 2, 5, 2]
    // B = 3
    // Input 2:

    // A = [1, 2, 3, 1, 3]
    // B = 2

    // Example Output
    // Output 1:

    // [1, 2, 2, 4, 3, 5]
    // Output 2:

    // [1, 1, 2, 3, 3]

    // Example Explanation
    // Explanation 1:

    // [1, 2, 2] are less than B wheread [4, 3, 5] are greater than or equal to B.
    // Explanation 2:

    // [1, 1] are less than B wheread [2, 3, 3] are greater than or equal to B.
    public ListNode1 partition(ListNode1 A, int B) {
        ListNode1 curr = A;
        ListNode1 less = new ListNode1(-1);
        ListNode1 l1 = less;
        ListNode1 great = new ListNode1(-1);
        ListNode1 g1 = great;

        while(curr != null){

            if(curr.val < B){
                less.next = curr;
                less = less.next;
                curr = curr.next;
            }
            else if(curr.val >= B){
                great.next = curr;
                great = great.next;
                curr = curr.next;
            }
        }
        great.next = null;
        less.next = g1.next;
        return l1.next;
    }
    // Solution by team
    // public ListNode partition(ListNode A, int B) {
    //     ListNode lessPrev = null;
    //     ListNode greaterPrev = null;
    //     ListNode head = A;
    //     ListNode greaterHead = null;
    //     while (A != null) {
    //         if (A.val < B) {
    //             // contains the node with value < B
    //             if (lessPrev != null) {
    //                 // append A to the list
    //                 lessPrev.next = A;
    //                 lessPrev = A;
    //             } else {
    //                 // A is the starting node
    //                 lessPrev = A;
    //                 head = A;
    //             }
    //         } else {
    //             // contains the node with value >= B
    //             if (greaterPrev != null) {
    //                 // append A to the list
    //                 greaterPrev.next = A;
    //                 greaterPrev = A;
    //             } else {
    //                 // A is the starting node
    //                 greaterPrev = A;
    //                 greaterHead = A;
    //             }
    //         }
    //         A = A.next;
    //     }
    //     if (greaterPrev != null)
    //         greaterPrev.next = null;
    //     if (lessPrev != null) {
    //         lessPrev.next = greaterHead;
    //     }
    //     return head;
    // }



    // LRU Cache
    // Design and implement a data structure for Least Recently Used (LRU) cache. It should support the following operations: get and set.

    // get(key) - Get the value (will always be positive) of the key if the key exists in the cache, otherwise return -1.
    // set(key, value) - Set or insert the value if the key is not already present. When the cache reaches its capacity, it should invalidate the least recently used item before inserting the new item.
    // The LRUCache will be initialized with an integer corresponding to its capacity. Capacity indicates the maximum number of unique keys it can hold at a time.

    // Definition of "least recently used" : An access to an item is defined as a get or a set operation of the item. "Least recently used" item is the one with the oldest access time.

    // NOTE: If you are using any global variables, make sure to clear them in the constructor.

    // Example :

    // Input : 
    //         capacity = 2
    //         set(1, 10)
    //         set(5, 12)
    //         get(5)        returns 12
    //         get(1)        returns 10
    //         get(10)       returns -1
    //         set(6, 14)    this pushes out key = 5 as LRU is full. 
    //         get(5)        returns -1 
    HashMap<Integer,ListNode> hm = new HashMap();
    class ListNode {
        int key;
      int value;
      ListNode prev,next;
      ListNode(int x, int y) { this.key = x; this.value=y; prev = null; next = null;}
    };
    ListNode head = new ListNode(-1,-1);
    ListNode tail = new ListNode(-1,-1);
    int capacity;
    public void Solution(int capacity) {
        head.next = tail;
        tail.prev=head;
        this.capacity = capacity;
    }
    
    public int get(int key) {
        // Check if the key is present in the cache.
        if (hm.containsKey(key)) {

            // Need to update the key-node to recently used.

            // Step 1: Get the key-node address and delete it.
            ListNode temp = hm.get(key);
            remove(temp);

            // Step 2: Insert the key address again so it becomes the recently used node.
            insert(temp);

            // Return the value of the key.
            return temp.value;

        } else {
            // If the key is not present, return -1.
            return -1;
        }
    }
    
    public void set(int key, int value) {
        // Check if the key is already present in the cache.
        if (hm.containsKey(key)) {

            // Remove the existing node.
            ListNode temp = hm.get(key);
            remove(temp);
        }

        // Check if the cache capacity is not full.
        if (hm.size() == capacity) {

            // Remove the first node after the default head.
            remove(head.next);
        }

        // Create a new node.
        ListNode newNode = new ListNode(key, value);

        // Insert the new node into the cache.
        insert(newNode);
    }

    // Remove method.
    private void remove(ListNode node) {

        // First remove from the cache map.
        hm.remove(node.key);

        // Get the previous and next nodes of the given node.
        ListNode tP = node.prev; // tP: previous node of the given node
        ListNode tN = node.next; // tN: next node of the given node

        // Connect tP to tN.
            tP.next = tN;
            tN.prev = tP;

        // Disconnect the node.
        node.next = null;
        node.prev = null;
    }

    // Insert method for making a node recently used in the cache.
    private void insert(ListNode node) {

        // Insert in the cache map.
        hm.put(node.key, node);

        // Get the previous node of the tail.
        if(tail != null) {
            ListNode temp = tail.prev;

            // Connect the node to temp and tail.
            node.prev = temp;
            node.next = tail;

            // Connect temp to the node.
            temp.next = node;

            // Connect tail to the node.
            tail.prev = node;
        }
    }
    // Solution by team
    // static class Node {
    //     int key;
    //     int val;
    //     Node prev, next;
    //     public Node(int key, int val) {
    //         this.key = key;
    //         this.val = val;
    //     }
    // }
    
    // Node head;
    // Node tail;
    // int N;
    // int MAX;
    // HashMap<Integer, Node> mMap;
    
    // public void updateAccessList(Node node){
    //     Node temp = node.prev;
    //     temp.next = node.next;
    //     temp = node.next;
    //     if (temp != null)
    //         temp.prev = node.prev;
            
    //     node.next = head;
    //     head.prev = node;
    //     node.prev = null;
    //     head = node;
    // }
    
    // public Solution(int capacity) {
    //     head = null;
    //     tail = null;
    //     MAX = capacity;
    //     N = 0;
    //     mMap = new HashMap<>();
    // }
    
    // public int get(int key) {
    //     if (N == 0)
    //         return -1;
        
    //     if (mMap.containsKey(key)) {
    //         Node node = mMap.get(key);
            
    //         if (key == head.key) {
    //             return node.val;
    //         }
    //         if (tail.key == key) {
    //             tail = tail.prev;
    //         }
            
    //         updateAccessList(node);
            
    //         return node.val;
    //     }
        
        
    //     return -1;
    // }
    
    // public void set(int key, int value) {
        
    //     if (mMap.containsKey(key)) {
            
    //         Node node = mMap.get(key);
            
    //         if (node.key == head.key) {
    //             node.val = value;
    //             return;
    //         }
            
    //         if (tail.key == key) {
    //             tail = tail.prev;
    //         }
            
    //         updateAccessList(node);
            
    //         node.val = value;
            
    //         return;
    //     }
        
    //     if (N == MAX) {
    //         if (tail != null) {
    //             mMap.remove(tail.key);
    //             tail = tail.prev;
                
    //             if (tail != null) {
    //                 tail.next.prev = null;
    //                 tail.next = null;
    //             }
    //             N--;
    //         }
    //     }
        
    //     Node node = new Node(key, value);
    //     node.next = head;
    //     if (head != null)
    //         head.prev = node;
    
    //     head = node;
    //     N++;
        
    //     if (N == 1)
    //         tail = head;
        
    //     mMap.put(key, node);    
    // }

    // Intersection of LinkedLists
    // Write a program to find the node at which the intersection of two singly linked lists, A and B, begins. For example, the following two linked lists:

    // A:          a1 → a2
    //                 ↘
    //                     c1 → c2 → c3
    //                 ↗
    // B:     b1 → b2 → b3
    // NOTE:

    // If the two linked lists have no intersection at all, return null.
    // The linked lists must retain their original structure after the function returns.
    // You may assume there are no cycles anywhere in the entire linked structure.
    // Your code should preferably run in O(n) time and use only O(1) memory.
    // The custom input to be given is different than the one explained in the examples. Please be careful.
    // Problem Constraints
    // 0 <= |A|, |B| <= 106

    // Input Format
    // The first argument of input contains a pointer to the head of the linked list A.

    // The second argument of input contains a pointer to the head of the linked list B.
    // Output Format
    // Return a pointer to the node after which the linked list is intersecting.

    // Example Input
    // Input 1:

    // A = [1, 2, 3, 4, 5]
    // B = [6, 3, 4, 5]
    // Input 2:

    // A = [1, 2, 3]
    // B = [4, 5]

    // Example Output
    // Output 1:

    // [3, 4, 5]
    // Output 2:

    // []

    // Example Explanation
    // Explanation 1:

    // In the first example, the nodes have the same values after 3rd node in A and 2nd node in B. Thus, the linked lists are intersecting after that point. 
    // Explanation 2:

    // In the second example, the nodes don't have the same values, thus we can return None/Null.

    public ListNode getIntersectionNode(ListNode A, ListNode B) {
        ListNode temp1 = A;
        int length1 = 0;
        while(temp1 != null) {
            length1++;
            temp1=temp1.next;
        }
        ListNode temp2 = B;
        int length2 = 0;
        while(temp2 != null) {
            length2++;
            temp2=temp2.next;
        }

        if(length1 == 0 || length2 == 0) {
            return null;
        }

        temp1 = A;
        temp2 = B;

        while(length1 > length2) {
            temp1 = temp1.next;
            length1--;
        }

        while(length2 > length1) {
            temp2 = temp2.next;
            length2--;
        }
        ListNode startPoint = null;
        while(temp1 !=null && temp2 !=null) {
            if(temp1 == temp2  && startPoint == null) {
                startPoint = temp1;
            }
            temp1 = temp1.next;
            temp2=temp2.next;
        }
        return startPoint;
	}

    // Solution by team
    // public ListNode getIntersectionNode(ListNode A, ListNode B) {
	//     ListNode lastA, lastB;
	//     int countA, countB;
	    
	//     if (A == null || B == null)
	//         return null;
	    
	//     countA = countB = 1;
	    
	//     lastA = A;
	//     lastB = B;
	    
	//     while (lastA.next != null) {
	//         lastA = lastA.next;
	//         countA++;
	//     }
	    
	//     while (lastB.next != null) {
	//         lastB = lastB.next;
	//         countB++;
	//     }
	    
	//     if (!lastA.equals(lastB))
	//         return null;
	    
	//     int diff = Math.abs(countA - countB);
	    
	//     lastA = A;
	//     lastB = B;
	    
	//     if (countA > countB) {
	//         while (diff-- > 0) {
	//             lastA = lastA.next;
	//         }
	//     } else {
	//         while (diff-- > 0)
	//             lastB = lastB.next;
	//     }
	    
	//     while (!lastA.equals(lastB)) {
	//         lastA = lastA.next;
	//         lastB = lastB.next;
	//     }
	    
	//     return lastA;
	    
	// }
}
