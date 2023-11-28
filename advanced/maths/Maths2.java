package advanced.maths;

public class Maths2 {

    // Write a program to print the pascal triangle up to A rows.
    public int[][] pascalTriangle(int A) {
        int[][] arr = new int[A][A];
        for(int i=0; i<A;i++) {
            arr[i][0]=1;
            arr[i][i]=1;
            for(int j=1;j<i;j++) {
                arr[i][j] = arr[i-1][j-1]+arr[i-1][j];
            }
        }
        return arr;
    }

    // Given a positive integer A, return its corresponding column title as it appears in an Excel sheet.

    // For example:

    // 1 -> A
    // 2 -> B
    // 3 -> C
    // ...
    // 26 -> Z
    // 27 -> AA
    // 28 -> AB 
    public String excelColumnTitle(int A) {
        String ans = "";
        while (A>0) {
            A=A-1;
            ans = (char)('A'+(A%26)) + ans;
            A=A/26;
        }
        return ans;
    }
}
