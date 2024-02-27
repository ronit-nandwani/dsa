package advanced.heaps;

import java.util.Comparator;
import java.util.PriorityQueue;

public class Heaps2 {
    // Running Median

    // Given an array of integers, A denoting a stream of integers. New arrays of integer B and C are formed.
    // Each time an integer is encountered in a stream, append it at the end of B and append the median of array B at the C.
    // Find and return the array C.
    // NOTE:
    // If the number of elements is N in B and N is odd, then consider the median as B[N/2] ( B must be in sorted order).
    // If the number of elements is N in B and N is even, then consider the median as B[N/2-1]. ( B must be in sorted order).
    // Problem Constraints
    // 1 <= length of the array <= 100000
    // 1 <= A[i] <= 109
    // Input Format
    // The only argument given is the integer array A.
    // Output Format
    // Return an integer array C, C[i] denotes the median of the first i elements.
    // Example Input
    // Input 1:
    // A = [1, 2, 5, 4, 3]
    // Input 2:
    // A = [5, 17, 100, 11]
    // Example Output
    // Output 1:
    // [1, 1, 2, 2, 3]
    // Output 2:
    // [5, 5, 17, 11]
    // Example Explanation
    // Explanation 1:
    // stream          median
    // [1]             1
    // [1, 2]          1
    // [1, 2, 5]       2
    // [1, 2, 5, 4]    2
    // [1, 2, 5, 4, 3] 3
    // Explanation 2:

    // stream          median
    // [5]              5
    // [5, 17]          5
    // [5, 17, 100]     17
    // [5, 17, 100, 11] 11 
    public int[] runningMedian(int[] A) {
        int n = A.length;
        int[] ans = new int[n];
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a, b) -> b - a);
        for(int i=0;i<n;i++) {
            if(maxHeap.isEmpty() || A[i] <= maxHeap.peek()) {
                maxHeap.add(A[i]);
            } else {
                minHeap.add(A[i]);
            }
            int ds = maxHeap.size() - minHeap.size();

            if(ds > 1) {
                int a = maxHeap.remove();
                minHeap.add(a);
            } else if(ds < 0) {
                int a = minHeap.remove();
                maxHeap.add(a);
            }
            ans[i] = maxHeap.peek();
        }
        return ans;
    }
    // Solution by team
    //     public class Solution {
    // static PriorityQueue < Integer > max_heap;
    // static PriorityQueue < Integer > min_heap;
    // public int[] solve(int[] A) {
    //     min_heap = new PriorityQueue();
    //     max_heap = new PriorityQueue(new CustomComp());
    //     int n = A.length;
    //     int[] ans = new int[n];
    //     for (int i = 0; i < n; ++i) {
    //     add(A[i]);
    //     ans[i] = get_median();
    //     }

    //     return ans;
    // }
    // public static int get_median() {
    //     int total = min_heap.size() + max_heap.size();
    //     int ret;
    //     if (total % 2 == 1) {
    //     if (max_heap.size() > min_heap.size())
    //         ret = max_heap.peek();
    //     else
    //         ret = min_heap.peek();
    //     } else {
    //     ret = Integer.MAX_VALUE;
    //     if (max_heap.size() != 0)
    //         ret = Math.min(ret, max_heap.peek());
    //     if (min_heap.size() != 0)
    //         ret = Math.min(ret, min_heap.peek());
    //     }
    //     return ret;
    // }
    // public static void add(int a) {
    //     if (max_heap.size() != 0 && (a >= max_heap.peek()))
    //     min_heap.offer(a);
    //     else
    //     max_heap.offer(a);

    //     if (Math.abs(max_heap.size() - min_heap.size()) > 1) {
    //     if (max_heap.size() > min_heap.size()) {
    //         int temp = max_heap.peek();
    //         max_heap.poll();
    //         min_heap.offer(temp);
    //     } else {
    //         int temp = min_heap.peek();
    //         min_heap.poll();
    //         max_heap.offer(temp);
    //     }
    //     }
    // }

    // }
    // class CustomComp implements Comparator < Integer > {
    // @Override
    // public int compare(Integer a, Integer b) {
    //     return b - a;
    // }
    // }


    // K Places Apart

    // N people having different priorities are standing in a queue.
    // The queue follows the property that each person is standing at most B places away from its position in the sorted queue.
    // Your task is to sort the queue in the increasing order of priorities.
    // NOTE:
    // No two persons can have the same priority.
    // Use the property of the queue to sort the queue with complexity O(NlogB).
    // Problem Constraints
    // 1 <= N <= 100000
    // 0 <= B <= N
    // Input Format
    // The first argument is an integer array A representing the priorities and initial order of N persons.
    // The second argument is an integer B.
    // Output Format
    // Return an integer array representing the sorted queue.
    // Example Input
    // Input 1:
    // A = [1, 40, 2, 3]
    // B = 2
    // Input 2:
    // A = [2, 1, 17, 10, 21, 95]
    // B = 1
    // Example Output
    // Output 1:
    // [1, 2, 3, 40]
    // Output 2:
    // [1, 2, 10, 17, 21, 95]
    // Example Explanation
    // Explanation 1:
    // Given array A = [1, 40, 2, 3]
    // After sorting, A = [1, 2, 3, 40].
    // We can see that difference between initial position of elements amd the final position <= 2.
    // Explanation 2:

    // After sorting, the array becomes [1, 2, 10, 17, 21, 95].
    public int[] kPlacesApart(int[] A, int B) {
        int n = A.length;
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for(int i=0;i<B;i++) {
            pq.add(A[i]);
        }
        int j = 0;
        int[] C = new int[n];
        for(int i=B;i<n;i++) {
            
            pq.add(A[i]);
        }

        while(!pq.isEmpty()) {
            C[j++] = pq.remove();
        }
        return C;
    }
    // Solution by team
    // public class Solution {
    //     public int[] solve(int[] A, int B) {
    //       PriorityQueue < Integer > pq = new PriorityQueue();
      
    //       int i = 0, n = A.length;
    //       for (i = 0; i <= Math.min(B, n - 1); i++) {
    //         pq.offer(A[i]);
    //       }
      
    //       int j = 0;
    //       while (i < n) {
    //         A[j] = pq.poll();
    //         pq.offer(A[i]);
    //         i++;
    //         j++;
    //       }
      
    //       while (j < n) {
    //         A[j] = pq.poll();
    //         j++;
    //       }
    //       return A;
    //     }
    //   }
}
