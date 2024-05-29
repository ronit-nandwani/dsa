package intermediate.strings;

import java.util.ArrayList;
import java.util.List;

public class StringsLeetCode {
    // 592. Fraction Addition and Subtraction
    // Solved
    // Medium
    // Topics
    // Companies
    // Given a string expression representing an expression of fraction addition and subtraction, return the calculation result in string format.

    // The final result should be an irreducible fraction. If your final result is an integer, change it to the format of a fraction that has a denominator 1. So in this case, 2 should be converted to 2/1.

    

    // Example 1:

    // Input: expression = "-1/2+1/2"
    // Output: "0/1"
    // Example 2:

    // Input: expression = "-1/2+1/2+1/3"
    // Output: "1/3"
    // Example 3:

    // Input: expression = "1/3-1/2"
    // Output: "-1/6"
    

    // Constraints:

    // The input string only contains '0' to '9', '/', '+' and '-'. So does the output.
    // Each fraction (input and output) has the format ±numerator/denominator. If the first input fraction or the output is positive, then '+' will be omitted.
    // The input only contains valid irreducible fractions, where the numerator and denominator of each fraction will always be in the range [1, 10]. If the denominator is 1, it means this fraction is actually an integer in a fraction format defined above.
    // The number of given fractions will be in the range [1, 10].
    // The numerator and denominator of the final result are guaranteed to be valid and in the range of 32-bit int.

    // Solution by me - 5 ms
    public String fractionAddition(String expression) {
        int numerator = 0;
        int denominator = 1; // Initialize with 1 since we will be multiplying denominators
        
        int index = 0;
        int length = expression.length();
        
        while (index < length) {
            // Read the sign
            int sign = 1;
            if (expression.charAt(index) == '-') {
                sign = -1;
                index++;
            } else if (expression.charAt(index) == '+') {
                index++;
            }
            
            // Read the numerator
            int num = 0;
            while (index < length && Character.isDigit(expression.charAt(index))) {
                num = num * 10 + (expression.charAt(index) - '0');
                index++;
            }
            
            // Read the '/'
            index++; // skip '/'
            
            // Read the denominator
            int denom = 0;
            while (index < length && Character.isDigit(expression.charAt(index))) {
                denom = denom * 10 + (expression.charAt(index) - '0');
                index++;
            }
            
            // Apply the sign to the numerator
            num *= sign;
            
            // Calculate the new numerator and denominator
            numerator = numerator * denom + num * denominator;
            denominator *= denom;
            
            // Simplify the fraction by GCD
            int gcd = gcdGcd(Math.abs(numerator), Math.abs(denominator));
            numerator /= gcd;
            denominator /= gcd;
        }
        
        return numerator + "/" + denominator;
    }
    
    // Helper function to calculate GCD (Greatest Common Divisor)
    private int gcdGcd(int a, int b) {
        if (b == 0) return a;
        return gcdGcd(b, a % b);
    }

    // Faster Solution - 0 ms

    private static class Fraction {
        int num;
        int den;
        Fraction(int n, int d) {
            this.num = n;
            this.den = d;
        }
    }
    private void parseExpression(char[] expChArr, List<Fraction> fractionList) {
         int i = 0;
            while(i<expChArr.length) {
                int num = 0, j = i, den = 0;
                boolean numNeg = false, denNeg = false;

                if(expChArr[j]=='-' || expChArr[j] == '+') {
                    numNeg = expChArr[j]=='-';
                    j++;
                }
                while(j < expChArr.length && expChArr[j]!='/') {
                    int digit = (int)expChArr[j++] - 48;
                    num = (num * 10) + digit;
                }
                j++;

                if(expChArr[j]=='-' || expChArr[j] == '+') {
                    denNeg = expChArr[j]=='-';
                    j++;
                }
                while(j<expChArr.length && expChArr[j]!='-' && expChArr[j]!='+') {
                    int digit = (int)expChArr[j++] - 48;
                    den = (den * 10) + digit;
                }

                if(numNeg != denNeg) {
                    num *= -1;
                }

                Fraction fraction = new Fraction(num, den);

                fractionList.add(fraction);
                i = j;
            }
    }

    private Fraction evaluate(List<Fraction> fractions) {
            Fraction prev = new Fraction(0, 1);
            for(Fraction curr: fractions) {
                int gcd = gcdHelper(prev.den, curr.den);
                int lcm = (prev.den * curr.den)/gcd;

                curr.num = curr.num * (lcm/curr.den);
                prev.num = prev.num * (lcm/prev.den);

                prev.num = prev.num + curr.num;
                prev.den = lcm;
            }

            int gcd = gcdHelper(Math.abs(prev.num), prev.den);
            prev.num = prev.num/gcd;
            prev.den = prev.den/gcd;
            return prev;
        }

        private int gcdHelper(int a, int b) {
            return a > b ? gcd(a,b) : gcd(b,a);
        }

        private int gcd(int a, int b) {
            if(b==0) return a;
            return gcd(b, a % b);
        }

    public String fractionAdditionFaster(String expression) {

        int rn = 0;
        char[] cs = expression.toCharArray();
        int[] sgns = new int[256];
        sgns['-'] = -1;
        sgns['+'] = 1;
        int i = 0;
        char sgn;
        if (cs[0] == '-') {
            sgn = '-';
            i = 1;
        } else if (cs[0] == '+') {
            sgn = '+';
            i = 1;
        } else {
            sgn = '+';
        }
        final int len = cs.length;
        for (; ; i++) {
            //int sign;
            int n;
            n = sgns[sgn] * (cs[i++] - '0');
            if (cs[i] == '0') {
                n *= 10;
                i++;
            }
            int d = cs[++i] - '0';
            i++;
            if (i < len && cs[i] == '0') {
                d = 10;
                i++;
            }
            rn += n * 2520 / d;
            if (i < len) {
                sgn = cs[i];
            } else {
                break;
            }
        }
        int dn = 2520;
        int mod8 = (int)rn % 8;
        switch (mod8) {
            case 0: rn /= 8; dn = 315; break;
            case 4: case -4: rn /= 4; dn = 630; break;
            case 2: case 6: case -2: case -6: rn /= 2; dn = 1260; break;
            default:
        }
        if (rn % 3 == 0) {
            if (rn % 9 == 0) {
                rn /= 9;
                dn /= 9;
            } else {
                rn /= 3;
                dn /= 3;
            }
        }
        if (rn % 5 == 0) {
            rn /= 5;
            dn /= 5;
        }
        if (rn % 7 == 0) {
            rn /= 7;
            dn /= 7;
        }
        return new StringBuilder().append(rn).append("/").append(dn).toString();
    }




    // ---------------------------------------------

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
