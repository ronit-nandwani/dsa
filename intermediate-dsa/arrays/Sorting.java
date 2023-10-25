import java.util.Arrays;

public class Sorting {
    // Reverse an Array - Not related to sorting
    public static void reverse(int[] array)
    {

        // Length of the array
        int n = array.length;

        // Swapping the first half elements with last half
        // elements
        for (int i = 0; i < n / 2; i++) {

            // Storing the first half elements temporarily
            int temp = array[i];

            // Assigning the first half to the last half
            array[i] = array[n - i - 1];

            // Assigning the last half to the first half
            array[n - i - 1] = temp;
        }
    }


    // Given an integer array A, find if an integer p exists in the array such that the number of integers greater than p in the array equals p.
    public int nobleInteger(int[] A) {
        int n = A.length ;

        Arrays.sort(A);

        for(int i = 0 ; i < n-1 ; i++)
        {
            if(A[i] != A[i+1])   // suppose A[] ={9,9,9,9,9,9,9,9,9} --> Inthis case there are no element is greater  , but if we only use the inner if condition then it return 1 which means there is present .
            {                      //therefore we need this if condition
                int count_element = n - 1 - i ;
                if(A[i] == count_element)
                {
                    return 1 ;
                }
            }
        }

        int count_element = 0 ;         // edge case

        if(A[n-1] == count_element)
        {
            return 1 ;
        }

        return -1 ;
    }

    // Find the Bth smallest element in given array A .
    public int kthsmallest(final int[] a, int B) {
        int n = a.length;
        int minIndex,t;
        for(int i=0;i<n;i++) {
            minIndex = i;
            for(int j=i+1;j<n;j++) {
                if(a[j] < a[minIndex]) {
                    minIndex = j;
                }
            }
            t = a[i];
            a[i] = a[minIndex];
            a[minIndex] = t;
            if((i+1)==B) {
                return a[i];
            }
        }
        return 0;
    }


    // Given an integer array A of size N. You can remove any element from the array in one operation.
    //The cost of this operation is the sum of all elements in the array present before this operation.
    //Find the minimum cost to remove all elements from the array.
    public int elementsRemoval(int[] A) {
        int n = A.length;
        Arrays.sort(A);
        reverse(A);
        int ans = 0;
        for(int i=0;i<n;i++) {
            ans += (i+1)*A[i];
        }
        return ans;
    }
}
