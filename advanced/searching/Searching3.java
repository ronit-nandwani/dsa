package advanced.searching;

import java.util.Arrays;

public class Searching3 {
    // Farmer John has built a new long barn with N stalls. Given an array of integers A of size N where each element of the array represents the location of the stall and an integer B which represents the number of cows.
    //His cows don't like this barn layout and become aggressive towards each other once put into a stall. To prevent the cows from hurting each other, John wants to assign the cows to the stalls, such that the minimum distance between any two of them is as large as possible. What is the largest minimum distance?
    //Problem Constraints
    //2 <= N <= 100000
    //0 <= A[i] <= 109
    //2 <= B <= N
    //Input Format
    //The first argument given is the integer array A.
    //The second argument given is the integer B.
    //Output Format
    //Return the largest minimum distance possible among the cows.
    //Example Input
    //Input 1:
    //A = [1, 2, 3, 4, 5]
    //B = 3
    //Input 2:
    //A = [1, 2]
    //B = 2
    //Example Output
    //Output 1:
    // 2
    //Output 2:
    //
    // 1
    //Example Explanation
    //Explanation 1:
    // John can assign the stalls at location 1, 3 and 5 to the 3 cows respectively. So the minimum distance will be 2.
    //Explanation 2:
    // The minimum distance will be 1.
    //Expected Output
    //Provide sample input and click run to see the correct output for the provided input. Use this to improve your problem understanding and test edge cases
    public int maxCows(int D, int[] A) {
        int count=1, l=A[0];
        for(int i=0;i<A.length;i++) {
            if((A[i]-l) >= D) {
                count++;
                l=A[i];
            }
        }
        return count;
    }
    public int aggressiveCows(int[] A, int B) {
        Arrays.sort(A);
        int n = A.length;
        int l=1,r=(A[n-1]-A[0]);
        while(l <= r) {
            int mid = l + (r-l)/2;
            int count = maxCows(mid, A);
            int count1 = maxCows(mid+1, A);
            if(count == B && count1 < B) {
                return mid;
            }
            if(count >= B) {
                l=mid+1;
            } else {
                r=mid-1;
            }
        }
        return r;
    }
    public static long minPainter(long X, int B, int[] C) {
        long count = 1;
        long pTime = X;
        for(int i=0;i<C.length;i++) {
            long bTime = (long)C[i]*B;
            if(pTime >= bTime) {
                pTime -= bTime;
            } else {
                count++;
                pTime = X-bTime;
            }
        }
        return count;
    }
    // Given 2 integers A and B and an array of integers C of size N. Element C[i] represents the length of ith board.
    //You have to paint all N boards [C0, C1, C2, C3 … CN-1]. There are A painters available and each of them takes B units of time to paint 1 unit of the board.
    //Calculate and return the minimum time required to paint all boards under the constraints that any painter will only paint contiguous sections of the board.
    //NOTE:
    //1. 2 painters cannot share a board to paint. That is to say, a board cannot be painted partially by one painter, and partially by another.
    //2. A painter will only paint contiguous boards. This means a configuration where painter 1 paints boards 1 and 3 but not 2 is invalid.
    //
    //Return the ans % 10000003.
    //Problem Constraints
    //1 <= A <= 1000
    //1 <= B <= 106
    //1 <= N <= 105
    //1 <= C[i] <= 106
    //
    //Input Format
    //The first argument given is the integer A.
    //The second argument given is the integer B.
    //The third argument given is the integer array C.
    //Output Format
    //Return minimum time required to paint all boards under the constraints that any painter will only paint contiguous sections of board % 10000003.
    //Example Input
    //Input 1:
    // A = 2
    // B = 5
    // C = [1, 10]
    //Input 2:
    // A = 10
    // B = 1
    // C = [1, 8, 11, 3]
    //Example Output
    //Output 1:
    // 50
    //Output 2:
    // 11
    //Example Explanation
    //Explanation 1:
    // Possibility 1:- One painter paints both blocks, time taken = 55 units.
    // Possibility 2:- Painter 1 paints block 1, painter 2 paints block 2, time take = max(5, 50) = 50
    // There are no other distinct ways to paint boards.
    // ans = 50 % 10000003
    //Explanation 2:
    // Each block is painted by a painter so, Painter 1 paints block 1, painter 2 paints block 2, painter 3 paints block 3
    // and painter 4 paints block 4, time taken = max(1, 8, 11, 3) = 11
    // ans = 11 % 10000003
    public static int paint(int A, int B, int[] C) {
        int n = C.length;
        long l=-1,r=0;
        for(int i=0;i<n;i++) {
            long t = (long)C[i]*B;
            if(t>l) {
                l=t;
            }
            r=r+C[i];
        }
        r=r*B;
        while(l<=r) {
            long mid = l + (r-l)/2;
            long count = minPainter(mid,B,C);
            long count1 = minPainter(mid-1,B,C);
            if(count == A && count1>A) {
                return (int)(mid%10000003);
            }
            if(count <= A) {
                r=mid-1;
            } else {
                l=mid+1;
            }
        }
        return (int)(l%10000003);
    }

    public static void main(String[] args) {
        System.out.println(paint(1,1000000,new int[]{1000000,1000000}));
    }
}
