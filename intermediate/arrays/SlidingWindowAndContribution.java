package intermediate.arrays;

public class SlidingWindowAndContribution {
    // Given an array A of N non-negative numbers and a non-negative number B,
    // you need to find the number of subarrays in A with a sum less than B.
    // We may assume that there is no overflow.
    // Input: A = [2, 5, 6] && B = 10
    // Output: 4
    public int countingSubArrays(int[] A, int B) {
        int n = A.length;
        int count = 0;
        for (int s = 0; s < n; s++){    
            int sum=0;
            for (int e = s; e < n; e++){    
                sum = sum + A[e];  
                if(sum < B){
                    count++;
                }
            }
        }
        return count;
    }

    // Given an array of integers A and an integer B, find and return the minimum number of swaps required to bring all the numbers less than or equal to B together.
    // Note: It is possible to swap any two elements, not necessarily consecutive.
    public int minimumSwaps(int[] A, int B) {
        //Step 1: Counting the number of elements which are less than or equal to B
        int minCount = 0;

        for (int i = 0; i < A.length; i++) {
            if (A[i] <= B) {
                minCount++;
            }
        }

        //Step 2: After calculating the #elements less than or equal to k
        //this count becomes the length of our sliding window.
        //counting the #elements (in var maxCount) greater B in this window
        int maxCount = 0;
        for (int i = 0; i < minCount; i++) {
            if (A[i] > B) {
                maxCount++;
            }
        }

        //Step 3: minSwap is the answer variable.
        //Initializing minSwap with maxCount for the current window
        int minSwap = maxCount;

        for (int i = minCount; i < A.length; i++) {

            //Checking if the current window is greater than B
            //If true then increment maxCount by 1

            if (A[i] > B) {
                maxCount++;
            }

            //Checking if the previous window was greater than B
            //If true then decrement maxCount by 1
            //leaving bigger element behind

            if (A[i - minCount] > B) {
                maxCount--;
            }

            //Checking if the current window (maxCount) or previous window is lesser
            //If maxCount (current window count) < minSwap (previous window count)
            //Then update minimum value
            minSwap = Math.min(minSwap, maxCount);

        }

        return minSwap;
    }

    // You are given an integer array A of length N.
    // You have to find the sum of all sub array sums of A.
    public long subArraySum(int[] A) {
        long totalSum = 0;
        int n = A.length;
        for(int i=0;i<n;i++) {
            totalSum += (long)A[i] * (i+1) * (n-i);
        }
        return totalSum;
    }

    // Given an array A of length N. Also given are integers B and C.
    // Return 1 if there exists a sub array with length B having sum C and 0 otherwise
    public int SubArrayWithGivenSumAndLength(int[] A, int B, int C) {
        int sum = 0;
        int n = A.length;
        for(int i=0;i<B;i++) {
            sum += A[i];
        }
        if(sum == C) {
            return 1;
        }

        int i=1, j=B;
        while(j<n) {
            sum = sum - A[i-1] + A[j];
            if(sum == C) {
                return 1;
            }
            i++;j++;
        }
        return 0;
    }

    //You are given an integer array C of size A. Now you need to find a sub array (contiguous elements) so that the sum of contiguous elements is maximum.
    //But the sum must not exceed B.
    public int maxSubArray(int A, int B, int[] C) {
        int sum = 0;
        int n = C.length;

        int maxSum = 0;
        for(int i=0;i<n;i++) {
            sum = 0;
            for(int j = i;j<n;j++) {
                sum += C[j];
                if(maxSum < sum && sum <= B) {
                    maxSum = sum;
                }
            }
        }
        return maxSum;
    }
}
