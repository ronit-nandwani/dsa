import java.util.Arrays;

public class Basics {
    //Optimized Rotate Array k times - TC: O(n) where n is array length, SC: O(1)
    public static int[] rotateArrayMostOptimized(int[] A, int B) {
        int n = A.length;
        if(B >= n) {
            B=B%n;
        }
        reverseArrayFromBtoC(A, 0, n - 1);
        reverseArrayFromBtoC(A, 0, B - 1);
        reverseArrayFromBtoC(A, B, n - 1);
        return A;
    }
    //Optimized Rotate Array k times - TC: O(n) where n is array length, SC: O(n)
    public static int[] rotateArrayOptimized(int[] A, int B) {
        int n = A.length;
        int[] arr = new int[n];
            for(int i=0;i<n;i++) {
                int j = (i+B)%n;
                arr[j] = A[i];
            }
        return arr;
    }

    //Simplest Rotate Array k times - TC: O(n*B) where n is array length, SC: O(1)
    public int[] rotateArraySimple(int[] A, int B) {
        int n = A.length;
        for(int i=0;i<B;i++) {
            int t = A[n-1];
            for(int j=n-1;j>0;j--) {
                A[j] = A[j-1];
            }
            A[0] = t;
        }
        return A;
    }

    // Reverse array from B to C indexes only
    public static int[] reverseArrayFromBtoC(int[] arr, int B, int C) {
        for(int i=B;i<C;i++) {
            int t = arr[C];
            arr[C] = arr[i];
            arr[i] = t;
            C--;
        }

//        while(B<C) {
//            int t = arr[C];
//            arr[C] = arr[B];
//            arr[B] = t;
//            B++;
//            C--;
//        }
        return arr;
    }
    // Reverse array without using extra space
    public static int[] reverseOfArray(int[] arr) {
        int n = arr.length;
        for(int i=0;i<n/2;i++) {
            int t = arr[n-i-1];
            arr[n-i-1] = arr[i];
            arr[i] = t;
        }
        return arr;
    }
    public static void main(String[] args) {
        int[] arr = {1,2,3,4,-5,0,-4,5};

        // Reverse array without using extra space
        // int[] a = reverseOfArray(arr);

        int[] a = reverseArrayFromBtoC(arr, 1,5);
        System.out.print(Arrays.toString(a));


    }
}
