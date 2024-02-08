package advanced.maths;

import java.util.HashMap;

public class Maths1 {
    // Mod Sum
    // Given an array of integers A, calculate the sum of A [ i ] % A [ j ] for all possible i, j pairs. Return sum % (109 + 7) as an output.
    //Problem Constraints
    //1 <= length of the array A <= 105
    //1 <= A[i] <= 103
    //Input Format
    //The only argument given is the integer array A.
    //Output Format
    //Return a single integer denoting sum % (109 + 7).
    //Example Input
    //Input 1:
    // A = [1, 2, 3]
    //Input 2:
    // A = [17, 100, 11]
    //Example Output
    //Output 1:
    // 5
    //Output 2:
    // 61
    //Example Explanation
    //Explanation 1:
    // (1 % 1) + (1 % 2) + (1 % 3) + (2 % 1) + (2 % 2) + (2 % 3) + (3 % 1) + (3 % 2) + (3 % 3) = 5
    public int modSum(int[] A) {
        // Define a constant for modulo operation
        int mod = 1000000007;

        // Create an array to store the frequency of each element in A
        int arr[] = new int[1001];

        // Iterate through the input array A and update the frequency array arr
        for (int i = 0; i < A.length; i++) {
            arr[A[i]]++;
            // System.out.println(arr[A[i]] +"ff");
        }

        // Initialize the answer variable to 0
        int ans = 0;

        // Iterate through the range [1, 1000] for both i and j
        for (int i = 1; i < 1001; i++) {
            for (int j = 1; j < 1001; j++) {
                // Calculate the contribution to the answer using modulo arithmetic
                ans += ((i % j) * arr[i] * arr[j]) % mod;
                ans %= mod;
            }
        }

        // Return the final answer
        return ans;
    }
    // Given an integer array A of size N. You have to delete one element such that the GCD(Greatest common divisor) of the remaining array is maximum.
    // Find the maximum value of GCD.
    // Duplicate method to find gcd below
    // public int gcd(int A, int B) {
    //     if(B==0) {
    //         return A;
    //     }
    //     if(A==0) {
    //         return B;
    //     }
    //     if(A>=B) {
    //         return gcd(B,(A % B));
    //     } else {
    //         return gcd(A,(B % A));
    //     }
    // }
    public int deleteOne(int[] A) {
        int n = A.length;
        int[] pf = new int[n];
        int[] sf = new int[n];

        pf[0] = A[0];
        for(int i=1;i<n;i++) {
            pf[i] = gcd(A[i], pf[i-1]);
        }

        sf[n-1] = A[n-1];
        for(int i=n-2;i>=0;i--) {
            sf[i] = gcd(A[i], sf[i+1]%A[i]);
        }

        int ans = Integer.MIN_VALUE;
        for(int i=0;i<n;i++) {
            int temp = 0;
            if(i==0) {
                temp = sf[1];
            } else if (i == n-1) {
                temp = pf[n-2];
            } else {
                temp = gcd(pf[i-1],sf[i+1]);
            }
            ans = Math.max(ans, temp);
        }
        return ans;
    }

    // GCD:Given 2 non-negative integers A and B, find gcd(A, B)
    // GCD of 2 integers A and B is defined as the greatest integer 'g' such that 'g' is a divisor of both A and B. Both A and B fit in a 32 bit signed integer.
    public int gcd(int A, int B) {
        if(B==0) {
            return A;
        }
        if(A==0) {
            return B;
        }
        if(A>=B) {
            return gcd(B,(A % B));
        } else {
            return gcd(A,(B % A));
        }
    }

    // Given an array of integers A and an integer B, find and return the number of pairs in A whose sum is divisible by B.
    // Since the answer may be large, return the answer modulo (109 + 7).
    // Note: Ensure to handle integer overflow when performing the calculations.
    public int pairSumDivisibleByM(int[] A, int B) {
        int n = A.length;
        HashMap<Integer, Integer> freq = new HashMap<Integer, Integer>();
        int ans = 0;
        for(int i=0;i<n;++i) {
            int val = A[i]%B;
            int pair = 0;
            if(val == 0) {
                pair = 0;
            } else {
                pair = B-val;
            }
            boolean p = freq.containsKey(pair);
            boolean v = freq.containsKey(val);
            if(p) {
                int c = freq.get(pair);
                ans = ((ans + c) % 1000000007);
            }
            if(v) {
                int c = freq.get(val);
                freq.put(val,c+1);
            } else {
                freq.put(val,1);
            }
        }
        return ans;
    }
}
