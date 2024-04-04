package advanced.arrays;

import java.util.HashMap;

public class ArraysLeetCode {

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
