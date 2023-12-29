package advanced.hashing;

import java.util.HashMap;
import java.util.HashSet;

public class Hashing2 {
    // You are given an array A of N integers and an integer B.
    // Count the number of pairs (i,j) such that A[i] - A[j] = B and i ≠ j.
    // Since the answer can be very large, return the remainder after dividing the count with 109+7.
    // Problem Constraints
    // 1 <= N <= 105
    // 1 <= A[i] <= 109
    // 1 <= B <= 109
    // Input Format
    // First argument A is an array of integers and second argument B is an integer.
    // Output Format
    // Return an integer.
    // Example Input
    // Input 1:
    // A = [3, 5, 1, 2]
    // B = 4
    // Input 2:
    // A = [1, 2, 1, 2]
    // B = 1
    // Example Output
    // Output 1:
    // 1
    // Output 2:
    // 4
    // Example Explanation
    // Example 1:
    // The only pair is (2, 3) which gives difference as 4
    // Example 2:
    // The pair which gives difference as 3 are (2, 1), (4, 1), (2, 3) and (4, 3).

    public int countPairDifference(int[] A, int k) {
        int ans = 0;
        int n = A.length;
        HashMap<Integer,Integer> hm = new HashMap<Integer,Integer>();
        for(int i=0;i<n;i++) {
            if(hm.containsKey(A[i]-k)) {
                ans += hm.get(A[i]-k);
            }
            if(hm.containsKey(A[i]+k)) {
                ans += hm.get(A[i]+k);
            }
            if(hm.containsKey(A[i])) {
                hm.put(A[i],hm.get(A[i])+1);
            } else {
                hm.put(A[i],1);
            }
        }
        return ans%1000000007;
    }

    // You are given an array of N integers, A1, A2 ,..., AN and an integer B. Return the of count of distinct numbers in all windows of size B.
    // Formally, return an array of size N-B+1 where i'th element in this array contains number of distinct elements in sequence Ai, Ai+1 ,..., Ai+B-1.
    // NOTE: if B > N, return an empty array.
    // Problem Constraints
    // 1 <= N <= 106
    // 1 <= A[i] <= 109
    // Input Format
    // First argument is an integer array A
    // Second argument is an integer B.
    // Output Format
    // Return an integer array.
    // Example Input
    // Input 1:
    // A = [1, 2, 1, 3, 4, 3]
    // B = 3
    // Input 2:
    // A = [1, 1, 2, 2]
    // B = 1
    // Example Output
    // Output 1:
    // [2, 3, 3, 2]
    // Output 2:
    // [1, 1, 1, 1]
    // Example Explanation
    // Explanation 1:
    // A=[1, 2, 1, 3, 4, 3] and B = 3
    // All windows of size B are
    // [1, 2, 1]
    // [2, 1, 3]
    // [1, 3, 4]
    // [3, 4, 3]
    // So, we return an array [2, 3, 3, 2].
    // Explanation 2:
    // Window size is 1, so the output array is [1, 1, 1, 1].
    public int[] distinctNumbersInWindow(int[] A, int B) {
        int n = A.length;
        if(B>n) {
            return new int[]{};
        }
        int[] arr = new int[n-B+1];
        HashMap<Integer,Integer> hm = new HashMap<Integer,Integer>();
        for(int i=0;i<B;i++) {
            if(hm.containsKey(A[i])) {
                hm.put(A[i], hm.get(A[i])+1);
            } else {
                hm.put(A[i],1);
            }
        }
        int s=1,e=B;
        arr[0] = hm.size();
        while(e<n) {
            hm.put(A[s-1], hm.get(A[s-1])-1);
            if(hm.get(A[s-1])==0) {
                hm.remove(A[s-1]);
            }
            if(hm.containsKey(A[e])) {
                hm.put(A[e], hm.get(A[e])+1);
            } else {
                hm.put(A[e],1);
            }
            arr[s] = hm.size();
            s++;e++;
        }
        return arr;
    }
    // Given an array of integers A and an integer B.
    //Find the total number of sub arrays having sum equals to B.
    //Problem Constraints
    // 1 <= length of the array <= 50000
    //-1000 <= A[i] <= 1000
    //Input Format
    //The first argument given is the integer array A.
    //The second argument given is integer B.
    //Output Format
    //Return the total number of sub arrays having sum equals to B.
    //Example Input
    //Input 1:
    //A = [1, 0, 1]
    //B = 1
    //Input 2:
    //A = [0, 0, 0]
    //B = 0
    //Example Output
    //Output 1:
    //4
    //Output 2:
    //6
    //Example Explanation
    //Explanation 1:
    //[1], [1, 0], [0, 1] and [1] are four sub arrays having sum 1.
    //Explanation 1:
    //All the possible sub arrays having sum 0.
    public static int subArraySumEqualsB(int[] A, int B) {
        int n = A.length;
        int pff = 0;
        HashMap<Integer, Integer> hm = new HashMap<Integer, Integer>();
        int count=0;
        for(int i=0;i<n;i++) {
            pff += A[i];
            if(pff == B) {
                count++;
            }
            if(hm.containsKey(pff-B)) {
                count = count + hm.get(pff-B);
            }
            if(!hm.containsKey(pff)) {
                hm.put(pff,1);
            } else {
                int c = hm.get(pff);
                hm.put(pff,c+1);
            }
        }
        return count;
    }
    // Given an Array of integers A, and a target sum K.
    // Check if there exists a pair (i,j) such that Ai + Aj = A and i!=j.
    public int checkPairSum(int k, int[] A) {
        int n = A.length;
        HashSet<Integer> hs = new HashSet<Integer>();
        for(int i=0;i<n;i++) {
            if(hs.contains(k-A[i])) {
                return 1;
            }
            hs.add(A[i]);
        }
        return 0;
    }

    public static void main(String[] args) {
        // Expected Output: 0
        // System.out.println(solve(new int[]{-2,16,-12,5,7,-1,2,12,11},17));

        // Expected Output: 0
        // System.out.println(solve(new int[]{13,9,19,-9,-19,14,-15},15));

        // Expected Output: 10
        System.out.println(subArraySumEqualsB(new int[]{-14,-9,12,7,-12,-4,7,25,-5,48,33,-49,36,-31,-48,36,36,17,13,-47,-39,37,-20,35,38,26,-40,-43,36,-48,-33,-30,6,-28,11,27,22,18,-21,-11,-50,34,-21,44,-25,17,34,-12,14,26,30},16));
    }
}
