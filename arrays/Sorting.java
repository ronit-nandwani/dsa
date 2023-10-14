import java.util.Arrays;

public class Sorting {
    // Reverse an Array
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
