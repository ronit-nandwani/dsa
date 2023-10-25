public class SlidingWindowAndContribution {
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
