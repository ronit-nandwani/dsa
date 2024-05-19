package intermediate.strings;

import java.util.ArrayList;
import java.util.List;

public class StringsLeetCode {
    // 564. Find the Closest Palindrome
    // Solved
    // Hard
    // Topics
    // Companies
    // Hint
    // Given a string n representing an integer, return the closest integer (not including itself), which is a palindrome. If there is a tie, return the smaller one.

    // The closest is defined as the absolute difference minimized between two integers.

    

    // Example 1:

    // Input: n = "123"
    // Output: "121"
    // Example 2:

    // Input: n = "1"
    // Output: "0"
    // Explanation: 0 and 2 are the closest palindromes but we return the smallest which is 0.
    

    // Constraints:

    // 1 <= n.length <= 18
    // n consists of only digits.
    // n does not have leading zeros.
    // n is representing an integer in the range [1, 1018 - 1].

    // Solution by me - 1 ms
    public String nearestPalindromic(String numberStr) {
        long number = Long.parseLong(numberStr);
        if (number <= 10) return String.valueOf(number - 1);
        if (number == 11) return "9";

        int length = numberStr.length();
        long leftHalf = Long.parseLong(numberStr.substring(0, (length + 1) / 2));
        
        long[] palindromeCandidates = new long[5];
        palindromeCandidates[0] = generatePalindromeFromLeft(leftHalf - 1, length % 2 == 0);
        palindromeCandidates[1] = generatePalindromeFromLeft(leftHalf, length % 2 == 0);
        palindromeCandidates[2] = generatePalindromeFromLeft(leftHalf + 1, length % 2 == 0);
        palindromeCandidates[3] = (long)Math.pow(10, length - 1) - 1;
        palindromeCandidates[4] = (long)Math.pow(10, length) + 1;

        long nearestPalindrome = 0;
        long minDifference = Long.MAX_VALUE;

        for (long candidate : palindromeCandidates) {
            if (candidate == number) continue;
            long difference = Math.abs(candidate - number);
            if (difference < minDifference || (difference == minDifference && candidate < nearestPalindrome)) {
                minDifference = difference;
                nearestPalindrome = candidate;
            }
        }

        return String.valueOf(nearestPalindrome);
    }

    private long generatePalindromeFromLeft(long leftHalf, boolean isEvenLength) {
        long palindrome = leftHalf;
        if (!isEvenLength) leftHalf /= 10;
        while (leftHalf > 0) {
            palindrome = palindrome * 10 + leftHalf % 10;
            leftHalf /= 10;
        }
        return palindrome;
    }

    // Other Solution - 2 ms

    public String nearestPalindromicSlow(String n) {
          int len = n.length();
        int i = len%2 == 0 ? len/2-1 : len/2;

        long firstHalf = Long.parseLong(n.substring(0,i+1));

        /* All possibilties */

        List<Long> possibilties = new ArrayList<>();

        possibilties.add(halfToPalindome(firstHalf,len%2==0));
        possibilties.add(halfToPalindome(firstHalf+1,len%2==0));
        possibilties.add(halfToPalindome(firstHalf-1,len%2==0));
        possibilties.add((long) Math.pow(10,len-1)-1);
        possibilties.add((long) Math.pow(10,len)+1);

        long n1 = Long.parseLong(n);
        long diff = Long.MAX_VALUE;
        long res=0;
        for(long poss : possibilties) {
            if(poss==n1) continue;

            if(Math.abs(poss-n1) < diff) {
               diff = Math.abs(poss-n1);
               res = poss;
            }
            else if(Math.abs(poss-n1)==diff) {
                res = Math.min(res,poss);
            }
        }
        return String.valueOf(res);
    }

    private long halfToPalindome(long left, boolean even) {
       
       long res = left;
       //for odd length middle number need not to be replicated
       if(!even) left = left/10;

       while(left > 0) {
           res = res*10 + (left%10);
           left = left/10;
       }

       return res;
    }
    

    // --------------------------------------------------------------------

    // 1945. Sum of Digits of String After Convert
    // Solved
    // Easy
    // Topics
    // Companies
    // Hint
    // You are given a string s consisting of lowercase English letters, and an integer k.

    // First, convert s into an integer by replacing each letter with its position in the alphabet (i.e., replace 'a' with 1, 'b' with 2, ..., 'z' with 26). Then, transform the integer by replacing it with the sum of its digits. Repeat the transform operation k times in total.

    // For example, if s = "zbax" and k = 2, then the resulting integer would be 8 by the following operations:

    // Convert: "zbax" ➝ "(26)(2)(1)(24)" ➝ "262124" ➝ 262124
    // Transform #1: 262124 ➝ 2 + 6 + 2 + 1 + 2 + 4 ➝ 17
    // Transform #2: 17 ➝ 1 + 7 ➝ 8
    // Return the resulting integer after performing the operations described above.

    

    // Example 1:

    // Input: s = "iiii", k = 1
    // Output: 36
    // Explanation: The operations are as follows:
    // - Convert: "iiii" ➝ "(9)(9)(9)(9)" ➝ "9999" ➝ 9999
    // - Transform #1: 9999 ➝ 9 + 9 + 9 + 9 ➝ 36
    // Thus the resulting integer is 36.
    // Example 2:

    // Input: s = "leetcode", k = 2
    // Output: 6
    // Explanation: The operations are as follows:
    // - Convert: "leetcode" ➝ "(12)(5)(5)(20)(3)(15)(4)(5)" ➝ "12552031545" ➝ 12552031545
    // - Transform #1: 12552031545 ➝ 1 + 2 + 5 + 5 + 2 + 0 + 3 + 1 + 5 + 4 + 5 ➝ 33
    // - Transform #2: 33 ➝ 3 + 3 ➝ 6
    // Thus the resulting integer is 6.
    // Example 3:

    // Input: s = "zbax", k = 2
    // Output: 8
    

    // Constraints:

    // 1 <= s.length <= 100
    // 1 <= k <= 10
    // s consists of lowercase English letters.

    // Solution by me - 0 ms

    public int getLucky(String s, int k) {
        int ans = 0;
        for(char ch : s.toCharArray()) {
            ans += breakIntoSingleDigit(ch-'a'+1);
        }
        for(int i=1;i<k;i++) {
            ans = breakIntoSingleDigit(ans);
        }
        return ans;
    }

    public int breakIntoSingleDigit(int a) {
        int ans = 0;
        //System.out.print("Ans before: " + ans);
       // System.out.print(" A: " + a);
        //System.out.print(" a/10: " + a/10);
        while(a != 0) {
            ans += a%10;
            a=a/10;
        }
        //System.out.println(" Ans: " + ans);
        return ans;
    }

    // Fastest Solution
    public int getLuckyFastest(String s, int k) {
        int sum = 0;
        int n = s.length();
        for (int i = 0; i < n; i++) {
            int temp = s.charAt(i) - 96;
            while (temp > 0) {
                int rem = temp % 10;
                sum += rem;
                temp /= 10;
            }
        }
        k--;
        int num = sum;
        while (k > 0) {
            sum = 0;
            int temp = num;
            while (temp > 0) {
                int rem = temp % 10;
                sum += rem;
                temp /= 10;
            }
            num = sum;
            k--;
        }
        return sum;
    }
}
