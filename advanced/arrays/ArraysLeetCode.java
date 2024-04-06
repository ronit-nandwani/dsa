package advanced.arrays;

import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;

// import javax.swing.tree.TreeNode;

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

public class ArraysLeetCode {
    // 1038. Binary Search Tree to Greater Sum Tree
    // Given the root of a Binary Search Tree (BST), convert it to a Greater Tree such that every key of the original BST is changed to the original key plus the sum of all keys greater than the original key in BST.
    // As a reminder, a binary search tree is a tree that satisfies these constraints:
    // The left subtree of a node contains only nodes with keys less than the node's key.
    // The right subtree of a node contains only nodes with keys greater than the node's key.
    // Both the left and right subtrees must also be binary search trees.

    // Example 1:

    // Input: root = [4,1,6,0,2,5,7,null,null,null,3,null,null,null,8]
    // Output: [30,36,21,36,35,26,15,null,null,null,33,null,null,null,8]
    // Example 2:

    // Input: root = [0,null,1]
    // Output: [1,null,1]
    
    // Constraints:

    // The number of nodes in the tree is in the range [1, 100].
    // 0 <= Node.val <= 100
    // All the values in the tree are unique.
    
    // Note: This question is the same as 538: https://leetcode.com/problems/convert-bst-to-greater-tree/
    int sum = 0;
    public TreeNode bstToGst(TreeNode root) {
        traverse(root);
        return root;
    }

    private void traverse(TreeNode node) {
        if (node == null) {
            return;
        }
        
        // Traverse right subtree
        traverse(node.right);
        
        // Update the current node's value with the running sum
        sum += node.val;
        node.val = sum;
        
        // Traverse left subtree
        traverse(node.left);
    }

    // 1438. Longest Continuous Subarray With Absolute Diff Less Than or Equal to Limit
    // Given an array of integers nums and an integer limit, return the size of the longest non-empty subarray such that the absolute difference between any two elements of this subarray is less than or equal to limit.

    // Example 1:

    // Input: nums = [8,2,4,7], limit = 4
    // Output: 2 
    // Explanation: All subarrays are: 
    // [8] with maximum absolute diff |8-8| = 0 <= 4.
    // [8,2] with maximum absolute diff |8-2| = 6 > 4. 
    // [8,2,4] with maximum absolute diff |8-2| = 6 > 4.
    // [8,2,4,7] with maximum absolute diff |8-2| = 6 > 4.
    // [2] with maximum absolute diff |2-2| = 0 <= 4.
    // [2,4] with maximum absolute diff |2-4| = 2 <= 4.
    // [2,4,7] with maximum absolute diff |2-7| = 5 > 4.
    // [4] with maximum absolute diff |4-4| = 0 <= 4.
    // [4,7] with maximum absolute diff |4-7| = 3 <= 4.
    // [7] with maximum absolute diff |7-7| = 0 <= 4. 
    // Therefore, the size of the longest subarray is 2.
    // Example 2:

    // Input: nums = [10,1,2,4,7,2], limit = 5
    // Output: 4 
    // Explanation: The subarray [2,4,7,2] is the longest since the maximum absolute diff is |2-7| = 5 <= 5.
    // Example 3:

    // Input: nums = [4,2,2,2,4,4,2,2], limit = 0
    // Output: 3

    // Constraints:

    // 1 <= nums.length <= 105
    // 1 <= nums[i] <= 109
    // 0 <= limit <= 109
    public int longestSubarray(int[] nums, int limit) {
        Deque<Integer> maxDeque = new LinkedList<>();
        Deque<Integer> minDeque = new LinkedList<>();
        int left = 0;
        int maxLength = 0;
        
        for (int right = 0; right < nums.length; right++) {
            // Maintain max deque
            while (!maxDeque.isEmpty() && nums[maxDeque.peekLast()] <= nums[right]) {
                maxDeque.pollLast();
            }
            maxDeque.addLast(right);
            
            // Maintain min deque
            while (!minDeque.isEmpty() && nums[minDeque.peekLast()] >= nums[right]) {
                minDeque.pollLast();
            }
            minDeque.addLast(right);
            
            // Shrink the window if the current window is invalid
            while (nums[maxDeque.peekFirst()] - nums[minDeque.peekFirst()] > limit) {
                left++;
                if (maxDeque.peekFirst() < left) {
                    maxDeque.pollFirst();
                }
                if (minDeque.peekFirst() < left) {
                    minDeque.pollFirst();
                }
            }
            
            // Update the maximum length of the valid subarray
            maxLength = Math.max(maxLength, right - left + 1);
        }
        
        return maxLength;
    }



    // 1248. Count Number of Nice Subarrays
    // Given an array of integers nums and an integer k. A continuous subarray is called nice if there are k odd numbers on it.

    // Return the number of nice sub-arrays.
    // Example 1:

    // Input: nums = [1,1,2,1,1], k = 3
    // Output: 2
    // Explanation: The only sub-arrays with 3 odd numbers are [1,1,2,1] and [1,2,1,1].
    // Example 2:

    // Input: nums = [2,4,6], k = 1
    // Output: 0
    // Explanation: There are no odd numbers in the array.
    // Example 3:

    // Input: nums = [2,2,2,1,2,2,1,2,2,2], k = 2
    // Output: 16

    // Constraints:

    // 1 <= nums.length <= 50000
    // 1 <= nums[i] <= 10^5
    // 1 <= k <= nums.length
    public int numberOfSubarrays(int[] nums, int goal) {
            
            for(int i=0;i<nums.length;i++)
            {
                nums[i]=nums[i] & 1;
            }
        HashMap<Integer, Integer> sum = new HashMap<>();
        sum.put(0, 1);
        int total = 0;
        int ans = 0;
        for (int n : nums) {
            total += n;
            if (sum.containsKey(total - goal)) {
                int nooftimes = sum.get(Math.abs(total - goal));
                ans += nooftimes;

            }
            sum.put(total, sum.getOrDefault(total, 0) + 1);

        }
        return ans;

        }

        // or with less tc
        // more optimized solution
        // public int numberOfSubarrays(int[] nums, int k) {
        //     for (int i = 0; i < nums.length; i++) {
        //         nums[i] %= 2;
        //     }
            
        //     int[] prefixCount = new int[nums.length + 1];
        //     prefixCount[0] = 1;
        //     int s = 0;
        //     int ans = 0;
            
        //     for (int num : nums) {
        //         s += num;
        //         if (s >= k) {
        //             ans += prefixCount[s - k];
        //         }
        //         prefixCount[s]++;
        //     }
            
        //     return ans;
        // }




    // 1052. Grumpy Bookstore Owner - Sliding Window
    // There is a bookstore owner that has a store open for n minutes. Every minute, some number of customers enter the store. You are given an integer array customers of length n where customers[i] is the number of the customer that enters the store at the start of the ith minute and all those customers leave after the end of that minute.
    // On some minutes, the bookstore owner is grumpy. You are given a binary array grumpy where grumpy[i] is 1 if the bookstore owner is grumpy during the ith minute, and is 0 otherwise.
    // When the bookstore owner is grumpy, the customers of that minute are not satisfied, otherwise, they are satisfied.
    // The bookstore owner knows a secret technique to keep themselves not grumpy for minutes consecutive minutes, but can only use it once.
    // Return the maximum number of customers that can be satisfied throughout the day.
    // Example 1:
    // Input: customers = [1,0,1,2,1,1,7,5], grumpy = [0,1,0,1,0,1,0,1], minutes = 3
    // Output: 16
    // Explanation: The bookstore owner keeps themselves not grumpy for the last 3 minutes. 
    // The maximum number of customers that can be satisfied = 1 + 1 + 1 + 1 + 7 + 5 = 16.
    // Example 2:
    // Input: customers = [1], grumpy = [0], minutes = 1
    // Output: 1
    // Constraints:
    // n == customers.length == grumpy.length
    // 1 <= minutes <= n <= 2 * 104
    // 0 <= customers[i] <= 1000
    // grumpy[i] is either 0 or 1.

    public int maxSatisfied(int[] customers, int[] grumpy, int minutes) {
        int n = grumpy.length;
        int j = 0;
        int curWin = 0;
        int maxWin = 0;

        for(int i=0;j<n;j++) {
            if(grumpy[j] == 1) {
                curWin+=customers[j];
            }
            maxWin = Math.max(curWin, maxWin);
            if(j >= minutes - 1 ) {
                if(grumpy[i] == 1) {
                curWin -= customers[i];
                }
                i++;
            }
        }
        int res = maxWin;
        for(int i=0;i<grumpy.length;i++) {
            if(grumpy[i] == 0) {
                res+=customers[i];
            }
        }
        return res;

    }
}
