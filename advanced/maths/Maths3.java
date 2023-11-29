package advanced.maths;

import java.util.ArrayList;

public class Maths3 {
    // Check if num is Prime
    public boolean checkPrime(int n) {
        for(int i=2;i*i<=n;i++) {
            if(n%i==0) {
                return false;
            }
        }
        return true;
    }

    // Given an integer A. Find the list of all prime numbers in the range [1, A].
    public ArrayList<Integer> findAllPrimes(int A) {
        ArrayList<Integer> arr = new ArrayList<Integer>();
        for(int i=2;i<=A;i++) {
            if(checkPrime(i)) {
                arr.add(i);
            }
        }
        return arr;
    }
}
