package advanced.maths;

import java.util.ArrayList;

public class Maths3 {

    // Write a program to find smallest prime factor for 2 to N
    public int[] spf(int N) {
        int[] spf = new int[N+1];
        for(int i=0;i<=N;i++) {
            spf[i]=i;
        }
        for(int i=2; i*i <= N;i++) {
            if(spf[i]==i) {
                for(int j=i*i;j<=N;j=j+i) {
                    if(i<spf[j]) {
                        spf[j]=i;
                    }
                }
            }
        }
        return spf;
    }

    // Write program to count factor for a given number if spf is already created
    public int countFactor(int N, int[] spf) {
        int ans = 1;
        while(N>1) {
            int pow = 0;
            int s = spf[N];
            while(N%s == 0) {
                pow++;
                N= N/s;
            }
            ans = ans * (pow+1);
        }
        return ans;
    }

    // Given an array of integers A, find and return the count of divisors of each element of the array.
    // NOTE: The order of the resultant array should be the same as the input array.
    public int[] countFactorsForAllElementsInArray(int[] A) {
        int n = A.length;
        int[] factors = new int[n];
        int max = -1;
        for(int i=0;i<n;i++) {
            if(max < A[i]) {
                max = A[i];
            }
        }
        int[] spfArr = spf(max);
        for(int i=0;i<n;i++) {
            factors[i] = countFactor(A[i], spfArr);
        }
        return factors;
    }

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
