package advanced.hashing;

import java.util.HashSet;

public class Hashing2 {
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
}
