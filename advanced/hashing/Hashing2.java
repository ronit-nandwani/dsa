package advanced.hashing;

import java.util.HashMap;
import java.util.HashSet;

public class Hashing2 {
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
