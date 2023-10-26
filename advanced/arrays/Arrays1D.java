package advanced.arrays;

public class Arrays1D {

    public static int trap(final int[] A) {
        int n = A.length;
        int i=0,j=n-1;
        int ans = 0, water = 0;

        int lMax = A[0], rMax = A[n-1];
        while(i<j) {
            if(lMax < rMax) {
                i++;
                water = lMax - A[i];
                lMax = lMax >= A[i] ? lMax : A[i];
            } else {
                j--;
                water = rMax - A[j];
                rMax = rMax >= A[j] ? rMax : A[j];
            }

            if(water > 0) {
                ans += water;
            }
        }

        return ans;
    }

    // There are A beggars sitting in a row outside a temple. Each beggar initially has an empty pot. When the devotees come to the temple, they donate some amount of coins to these beggars. Each devotee gives a fixed amount of coin(according to their faith and ability) to some K beggars sitting next to each other.
    //Given the amount P donated by each devotee to the beggars ranging from L to R index, where 1 <= L <= R <= A, find out the final amount of money in each beggar's pot at the end of the day, provided they don't fill their pots by any other means.
    //For ith devotee B[i][0] = L, B[i][1] = R, B[i][2] = P, given by the 2D array B
    public int[] continuousSumQuery(int n, int[][] B) {
        int q = B.length;
        int[] A = new int[n];
        for(int i=0;i<q;i++) {
            A[B[i][0]-1] += B[i][2];

            if(B[i][1]-1 < n-1) {
                A[B[i][1]] -= B[i][2];
            }
        }

        for(int i=1;i<n;i++) {
            A[i] = A[i-1] + A[i];
        }
        return A;
    }

    // Kadane's algorithm - Find the maximum sum of contiguous non-empty sub array within an array A of length N.
    public int maxSubArray(final int[] A) {
        int n = A.length;
        int sum = 0;
        int ans = A[0];
        for(int i=0;i<n;i++) {
            sum = sum + A[i];
            if(sum > ans) {
                ans = sum;
            }
            if(sum < 0) {
                sum = 0;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(trap(new int[]{2,1,4,3,1,6,2}));
    }
}
