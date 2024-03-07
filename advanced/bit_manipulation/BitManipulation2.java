package advanced.bit_manipulation;

public class BitManipulation2 {
    // Subarray OR
    // You are given an array of integers A of size N.
    // The value of a subarray is defined as BITWISE OR of all elements in it.
    // Return the sum of value of all subarrays of A % 109 + 7.
    // Problem Constraints
    // 1 <= N <= 105
    // 1 <= A[i] <= 108
    // Input Format
    // The first argument given is the integer array A.
    // Output Format
    // Return the sum of Value of all subarrays of A % 109 + 7.
    // Example Input
    // Input 1:
    // A = [1, 2, 3, 4, 5]
    // Input 2:
    // A = [7, 8, 9, 10]
    // Example Output
    // Output 1:
    // 71
    // Output 2:
    // 110
    // Example Explanation
    // Explanation 1:
    // Value([1]) = 1
    // Value([1, 2]) = 3
    // Value([1, 2, 3]) = 3
    // Value([1, 2, 3, 4]) = 7
    // Value([1, 2, 3, 4, 5]) = 7
    // Value([2]) = 2
    // Value([2, 3]) = 3
    // Value([2, 3, 4]) = 7
    // Value([2, 3, 4, 5]) = 7
    // Value([3]) = 3
    // Value([3, 4]) = 7
    // Value([3, 4, 5]) = 7
    // Value([4]) = 4
    // Value([4, 5]) = 5
    // Value([5]) = 5
    // Sum of all these values = 71
    // Explanation 2:
    // Sum of value of all subarray is 110.

    // checking whether the index bit is set or not in val
    int checkbit( int val, int index ) {
        if( ( val&(1<<index) ) == 0 ) {
            return 0;
        }
        else {
            return 1;
        }
    }
    public int subarrayOR(int[] A) {
        int N = A.length;
        long total_subarrays = (long) N * (N+1) / 2;
        int mod = (int) 1e9 + 7; // int mod = 1000000007;
        long count_subarray_with_one = 0;
        long final_ans = 0;
        // Iterate each bit from bit 0 to 31
        for( int i=0; i<31; i++ ) {
            long count_subarray_with_zeros = 0;
            long consecutive_zeros = 0;
           
            // traverse each element of the array one by one
            for( int j=0; j<N; j++ ) {
                if ( checkbit( A[j], i) == 0 ) { // so ith is unset
                    // Here we keep on increasing the count of consecutive 0's
                    consecutive_zeros += 1;
                }
                else {  // so ith bit is set
                    //as we get ith bit as 1 -> first we store our subarray count with ith bit as 0
                    count_subarray_with_zeros += ( consecutive_zeros * (consecutive_zeros + 1) / 2 );
                    // since we got -> ith bit is 1/set so some A[j]..
                    // ..So for next A[j] -> our consecutive_zeros should be = 0
                    consecutive_zeros = 0;
                }
            }
            // after looping -> if there are consecutive_zeros at the end -> then also..
            // ..we should add it in count_subarray_with_zeros
            count_subarray_with_zeros +=  ( consecutive_zeros * (consecutive_zeros + 1) / 2 ) ;
            count_subarray_with_one = total_subarrays - count_subarray_with_zeros;
            final_ans +=  count_subarray_with_one * (1 << i) ; // only they will contribute
        }
        return  (int) (final_ans % mod) ;
    }
    // Solution by team
    // public class Solution {
    //     public int solve(int[] A) {
    //         int n = A.length;
    //         int[] idx = new int[32];
    //         long ans = 0;
    //         for (int i = 1; i <= n; ++i) {
    //             long tmp = A[i - 1];
    //             for (int j = 0; j <= 31; ++j) {
    //                 long pw = 1 << j;
    //                 if ((tmp & pw) != 0) { //if jth bit is set
    //                     ans += pw * i; // add its contribution in ans for all subarrays ending at index i
    //                     idx[j] = i; // store the index for next elements
    //                 } else if (idx[j] != 0) // if jth bit is not set
    //                 {
    //                     ans += pw * idx[j]; // add its contribution in ans for all subarrays ending at index i using 
    //                 } // the information of last element having jth bit set
    //             }
    //         }
    //         return (int)(ans % 1000000007);
    //     }
    // }


    // Given an array A. For every pair of indices i and j (i != j), find the maximum A[i] & A[j].
    public int maximumAndPair(int[] A) {
        int ans = 0;
        int n = A.length;
        for(int i=31;i>=0;i--) {
            int count = 0;
            for(int j=0; j<n ;j++) {
                if((A[j] & (1 << i)) > 0) {
                    count++;
                }
            }
            if(count >= 2) {
                ans |= (1 << i);
                for(int j=0; j<n ; j++) {
                    if((A[j] & (1 << i)) == 0) {
                        A[j] = 0;
                    }
                }
            }
        }
        return ans;
    }

    // Given an array A of length N where all the elements are distinct and are in the range [1, N+2].
    //Two numbers from the range [1, N+2] are missing from the array A. Find the two missing numbers.
    public int[] findTwoMissingNumbers(int[] A) {
        int xor = 0;
        int n = A.length;
        for(int i=0;i<n;i++) {
            xor = xor ^ A[i];
        }
        for(int i=1;i<=n+2;i++) {
            xor = xor ^ i;
        }
        int b = -1;
        for(int i=0;i<n;i++) {
            if((xor & (1 << i)) > 0) {
                b=i;
                break;
            }
        }
        int[] ans = new int[2];
        for(int i=0;i<n;i++) {
            if((A[i] & (1 << b)) > 0) {
                ans[0] = ans[0] ^ A[i];
            } else {
                ans[1] = ans[1] ^ A[i];
            }
        }
        for(int i=1;i<=n+2;i++) {
            if((i & (1 << b)) > 0) {
                ans[0] = ans[0] ^ i;
            } else {
                ans[1] = ans[1] ^ i;
            }
        }
        if(ans[0] > ans[1]) {
            int t = ans[0];
            ans[0] = ans[1];
            ans[1] = t;
        }
        return ans;
    }


    // Given an array of positive integers A, two integers appear only once, and all the other integers appear twice.
    //Find the two integers that appear only once.
    //Note: Return the two numbers in ascending order.
    public int[] singleNumberWithTwoUniqueElements(int[] A) {
        int xor = 0;
        int n = A.length;
        for(int i=0;i<n;i++) {
            xor = xor ^ A[i];
        }
        int b = -1;
        for(int i=0;i<32;i++) {
            if((xor & (1 << i)) > 0) {
                b=i;
                break;
            }
        }
        int[] ans = new int[2];
        for(int i=0;i<n;i++) {
            if((A[i] & (1 << b)) > 0) {
                ans[0] = ans[0] ^ A[i];
            } else {
                ans[1] = ans[1] ^ A[i];
            }
        }
        if(ans[0] > ans[1]) {
            int t = ans[0];
            ans[0] = ans[1];
            ans[1] = t;
        }
        return ans;
    }


    // Given an array of integers, every element appears thrice except for one, which occurs once.
    //Find that element that does not appear thrice.
    //NOTE: Your algorithm should have a linear runtime complexity.
    //Could you implement it without using extra memory?
    public int singleNumberWith3RepeatingElementsAnd1UniqueElement(final int[] A) {
        int ans = 0;
        for(int i=0;i<32;i++) {
            int count = 0;
            for(int j = 0;j<A.length;j++) {
                if((A[j] & (1 << i)) > 0) {
                    count++;
                }
            }
            if(count % 3 == 1) {
                ans |= (1 << i);
            }
        }
        return ans;
    }

    // Given an array of integers A, every element appears twice except for one. Find that integer that occurs once.
    //NOTE: Your algorithm should have a linear runtime complexity. Could you implement it without using extra memory?
    public int singleNumberWithOneUniqueElement(final int[] A) {
        int ans = 0;
        for(int i=0;i<32;i++) {
            int count = 0;
            for(int j = 0;j<A.length;j++) {
                if((A[j] & (1 << i)) > 0) {
                    count++;
                }
            }
            if(count % 2 == 1) {
                ans |= (1 << i);
            }
        }
        return ans;
    }
}
