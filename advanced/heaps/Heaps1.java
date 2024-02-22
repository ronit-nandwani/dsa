package advanced.heaps;
import java.util.*;

  class ListNode {
      public int val;
      public ListNode next;
      ListNode(int x) { val = x; next = null; }
 }
public class Heaps1 {
      // Merge K Sorted Lists
    // Given a list containing head pointers of N sorted linked lists.
    //Merge these given sorted linked lists and return them as one sorted list.
    //Problem Constraints
    //1 <= total number of elements in given linked lists <= 100000
    //Input Format
    //The first and only argument is a list containing N head pointers.
    //Output Format
    //Return a pointer to the head of the sorted linked list after merging all the given linked lists.
    //Example Input
    //Input 1:
    // 1 -> 10 -> 20
    // 4 -> 11 -> 13
    // 3 -> 8 -> 9
    //Input 2:
    // 10 -> 12
    // 13
    // 5 -> 6
    //Example Output
    //Output 1:
    // 1 -> 3 -> 4 -> 8 -> 9 -> 10 -> 11 -> 13 -> 20
    //Output 2:
    // 5 -> 6 -> 10 -> 12 ->13
    //Example Explanation
    //Explanation 1:
    // The resulting sorted Linked List formed after merging is 1 -> 3 -> 4 -> 8 -> 9 -> 10 -> 11 -> 13 -> 20.
    //Explanation 2:
    // The resulting sorted Linked List formed after merging is 5 -> 6 -> 10 -> 12 ->13.
    public static ListNode mergeKLists(ArrayList<ListNode> a) {
        int n = a.size();
        PriorityQueue <ListNode> pq = new PriorityQueue<>(new Comparator<ListNode>(){
            public int compare(ListNode s1, ListNode s2) {
                if (s1.val < s2.val)
                    return -1;
                else if (s1.val > s2.val)
                    return 1;
                return 0;
            }
        });
        for(int i=0;i<n;i++) {
            pq.add(a.get(i));
        }
        ListNode head = null;
        ListNode temp = head;
        while(!pq.isEmpty()) {
            if(head == null) {
                head = pq.peek();
                temp = pq.remove();
            } else {
                temp.next = pq.peek();
                pq.remove();
                temp = temp.next;
            }
            if(temp.next != null) {
                pq.add(temp.next);
            }
        }
        return head;
    }
    // Solution by them
    // public class Solution {
    
    //     public ListNode mergeKLists(ArrayList<ListNode> A) {
            
    //         TreeMap<Integer, ArrayList<ListNode>> map = new TreeMap<>();
    //         ListNode node;
    //         ArrayList<ListNode> list;
    //         int val;
            
    //         for (ListNode head : A) {
    //             node = head;
    //             while (node != null) {
    //                 val = node.val;
                    
    //                 if (map.containsKey(val)) {
    //                     list = map.get(val);
    //                     list.add(node);
    //                 } else {
    //                     list = new ArrayList<>();
    //                     list.add(node);
    //                     map.put(val, list);
    //                 }
                    
    //                 node = node.next;
    //             }
    //         }
            
    //         ListNode head = null;
    //         node = null;
            
    //         for (Map.Entry<Integer, ArrayList<ListNode>> entry : map.entrySet()) {
                
    //             list = entry.getValue();
                
    //             for (ListNode n : list) {
    //                 if (head == null)
    //                     head = n;
    //                 if (node != null)
    //                     node.next = n;
    //                 node = n;
    //             }
                
    //         }
            
    //         return head;
    //     }
    // }

    // Connect Ropes
    // You are given an array A of integers that represent the lengths of ropes.
    //You need to connect these ropes into one rope. The cost of joining two ropes equals the sum of their lengths.
    //Find and return the minimum cost to connect these ropes into one rope.
    //Problem Constraints
    //1 <= length of the array <= 100000
    //1 <= A[i] <= 1000
    //Input Format
    //The only argument given is the integer array A.
    //Output Format
    //Return an integer denoting the minimum cost to connect these ropes into one rope.
    //Example Input
    //Input 1:
    // A = [1, 2, 3, 4, 5]
    //Input 2:
    // A = [5, 17, 100, 11]
    //Example Output
    //Output 1:
    // 33
    //Output 2:
    // 182
    //Example Explanation
    //Explanation 1:
    // Given array A = [1, 2, 3, 4, 5].
    // Connect the ropes in the following manner:
    // 1 + 2 = 3
    // 3 + 3 = 6
    // 4 + 5 = 9
    // 6 + 9 = 15
    // So, total cost  to connect the ropes into one is 3 + 6 + 9 + 15 = 33.
    //Explanation 2:
    // Given array A = [5, 17, 100, 11].
    // Connect the ropes in the following manner:
    // 5 + 11 = 16
    // 16 + 17 = 33
    // 33 + 100 = 133
    // So, total cost  to connect the ropes into one is 16 + 33 + 133 = 182.
    public int connectRopes(int[] A) {
        int n = A.length;
        // minimum heap
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for(int i=0; i<n; i++){
            pq.add(A[i]);
        }
        int ans =0;
        while(pq.size() > 1){
            int f = pq.remove();
            int s = pq.remove();

            int sum = f + s;
            pq.add(sum);
            ans += sum;
        }
        return ans;
    }

    public static void main(String[] args) {
        // Input
        // 2
        //2 1 2
        //3 1 2 3
        ArrayList<ListNode> arr = new ArrayList<>();
        ListNode l1 = new ListNode(2);arr.add(l1);
        ListNode l2 = new ListNode(3);l1.next = l2;
        ListNode l3 = new ListNode(4);arr.add(l3);
        ListNode l4 = new ListNode(5);l3.next = l4;
        ListNode l5 = new ListNode(6);l4.next = l5;

        System.out.println(mergeKLists(arr));
    }
}
