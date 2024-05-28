package advanced.bit_manipulation;

public class BitManipulationLeetCode {


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
