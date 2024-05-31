package advanced.bit_manipulation;

public class BitManipulationLeetCode {

    // 2220. Minimum Bit Flips to Convert Number
    // Solved
    // Easy
    // Topics
    // Companies
    // Hint
    // A bit flip of a number x is choosing a bit in the binary representation of x and flipping it from either 0 to 1 or 1 to 0.

    // For example, for x = 7, the binary representation is 111 and we may choose any bit (including any leading zeros not shown) and flip it. We can flip the first bit from the right to get 110, flip the second bit from the right to get 101, flip the fifth bit from the right (a leading zero) to get 10111, etc.
    // Given two integers start and goal, return the minimum number of bit flips to convert start to goal.

    

    // Example 1:

    // Input: start = 10, goal = 7
    // Output: 3
    // Explanation: The binary representation of 10 and 7 are 1010 and 0111 respectively. We can convert 10 to 7 in 3 steps:
    // - Flip the first bit from the right: 1010 -> 1011.
    // - Flip the third bit from the right: 1011 -> 1111.
    // - Flip the fourth bit from the right: 1111 -> 0111.
    // It can be shown we cannot convert 10 to 7 in less than 3 steps. Hence, we return 3.
    // Example 2:

    // Input: start = 3, goal = 4
    // Output: 3
    // Explanation: The binary representation of 3 and 4 are 011 and 100 respectively. We can convert 3 to 4 in 3 steps:
    // - Flip the first bit from the right: 011 -> 010.
    // - Flip the second bit from the right: 010 -> 000.
    // - Flip the third bit from the right: 000 -> 100.
    // It can be shown we cannot convert 3 to 4 in less than 3 steps. Hence, we return 3.
    

    // Constraints:

    // 0 <= start, goal <= 109


    // Solution by me - 0 ms
    public int minBitFlips(int start, int goal) {
        // XOR start and goal to find the bits that differ
        int xorResult = start ^ goal;
        // 0 0 0 0
        // 1 0 1 0
        // 1 0 1 0
        // Count the number of 1's in the XOR result
        int flips = 0;
        while (xorResult != 0) {
            flips += xorResult & 1;  // Add 1 if the least significant bit is 1
            xorResult >>= 1;         // Right shift to check the next bit
        }
        
        return flips;
    }

    // Other Solution - 0 ms

    public int minBitFlipsOther(int start, int goal) {
        
        // Base case
        if (start == 0 && goal == 0) return 0;

        // Flip for the current least significant bit
        int flip = (start & 1) != (goal & 1) ? 1 : 0;

        // Recruse for the next bits by right-shifting both numbers
        return flip + minBitFlips(start >> 1, goal >> 1);
        
    }

    // -----------------------------------------------------------


    // 1310. XOR Queries of a Subarray
    // Solved
    // Medium
    // Topics
    // Companies
    // Hint
    // You are given an array arr of positive integers. You are also given the array queries where queries[i] = [lefti, righti].

    // For each query i compute the XOR of elements from lefti to righti (that is, arr[lefti] XOR arr[lefti + 1] XOR ... XOR arr[righti] ).

    // Return an array answer where answer[i] is the answer to the ith query.

    // Example 1:

    // Input: arr = [1,3,4,8], queries = [[0,1],[1,2],[0,3],[3,3]]
    // Output: [2,7,14,8] 
    // Explanation: 
    // The binary representation of the elements in the array are:
    // 1 = 0001 
    // 3 = 0011 
    // 4 = 0100 
    // 8 = 1000 
    // The XOR values for queries are:
    // [0,1] = 1 xor 3 = 2 
    // [1,2] = 3 xor 4 = 7 
    // [0,3] = 1 xor 3 xor 4 xor 8 = 14 
    // [3,3] = 8
    // Example 2:

    // Input: arr = [4,8,2,10], queries = [[2,3],[1,3],[0,0],[0,3]]
    // Output: [8,0,4,4]
    
    // Constraints:

    // 1 <= arr.length, queries.length <= 3 * 104
    // 1 <= arr[i] <= 109
    // queries[i].length == 2
    // 0 <= lefti <= righti < arr.length

    // Solution by me - 2 ms

    public int[] xorQueries(int[] arr, int[][] queries) {
        int n = arr.length;
        int m = queries.length;
        int[] answers = new int[m];
        int[] pf = new int[n];
        pf[0] = arr[0];
        for(int i=1;i<n;i++) {
            pf[i] = pf[i-1] ^ arr[i];
        }
        for(int i =0;i<m;i++) {
            int l = queries[i][0];
            int r = queries[i][1];

            if(l == r) {
                answers[i] = arr[l];
            } else if(l == 0) {
                answers[i] = pf[r];
            } else {
                answers[i] = pf[r] ^ pf[l-1];
            }
        }
        return answers;
    }

    // ------------------------------------------------------


    // 476. Number Complement
    // Solved
    // Easy
    // Topics
    // Companies
    // The complement of an integer is the integer you get when you flip all the 0's to 1's and all the 1's to 0's in its binary representation.

    // For example, The integer 5 is "101" in binary and its complement is "010" which is the integer 2.
    // Given an integer num, return its complement.

    

    // Example 1:

    // Input: num = 5
    // Output: 2
    // Explanation: The binary representation of 5 is 101 (no leading zero bits), and its complement is 010. So you need to output 2.
    // Example 2:

    // Input: num = 1
    // Output: 0
    // Explanation: The binary representation of 1 is 1 (no leading zero bits), and its complement is 0. So you need to output 0.
    

    // Constraints:

    // 1 <= num < 231
    

    // Note: This question is the same as 1009: https://leetcode.com/problems/complement-of-base-10-integer/

    // Solution by me - 0 ms
    public int findComplement(int num) {
        // Calculate the bitmask which has the same length as the binary representation of num
       int bitmask = (Integer.highestOneBit(num) << 1) - 1;
       
       // Return the complement by XORing num with the bitmask
       return num ^ bitmask;
   }

   // Other solution - 0 ms
   public int findComplementOther(int num) {
    // int ans = 0 ;
    // while(num > 0){
    //     if((num & 1) == 1){
    //         ans = ans << 1 | 0;
    //     }
    //     else{
    //         ans = ans << 1 | 1;
    //     }
    //     num >>= 1;
    // }
    // return ans;
    if (num == 0) {
        return 1;
    }

    // Compute the number of bits in the binary representation of num
    int bitLength = Integer.toBinaryString(num).length();

    // Create a mask with all bits set to 1
    int mask = (1 << bitLength) - 1;

    // Compute the complement
    return mask - num;
}

}
