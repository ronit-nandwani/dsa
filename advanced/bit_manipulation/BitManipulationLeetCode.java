package advanced.bit_manipulation;

public class BitManipulationLeetCode {
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
