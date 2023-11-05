package advanced.bit_manipulation;

public class BitManipulation2 {

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
