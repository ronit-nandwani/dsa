public class SubArray {
    public static int solve(int[] A) {
        int len;
        int min = A[0];
        int max = A[0];
        int n = A.length;
        int ans = n;
        for(int i=0;i<n;i++) {
            if(min > A[i]) {
                min = A[i];
            }
            if(max < A[i]) {
                max = A[i];
            }
        }

        int miIndex = -1;
        int maIndex = -1;
        for(int i=n-1;i>=0;i--) {
            if(A[i] == min) {
                miIndex = i;
                if(maIndex != -1) {
                    len = maIndex - miIndex + 1;
                    if(len < ans) {
                        ans = len;
                    }
                }
            }
            if(A[i] == max) {
                maIndex = i;
                if(miIndex != -1) {
                    len = miIndex - maIndex + 1;
                    if(len < ans) {
                        ans = len;
                    }
                }
            }
        }
        return ans;
    }
    public static void main(String[] args) {

    }
}
