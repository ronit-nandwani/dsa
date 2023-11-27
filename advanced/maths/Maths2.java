package advanced.maths;

public class Maths2 {
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
