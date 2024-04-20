package advanced.graphs;

import java.util.LinkedList;
import java.util.Queue;

public class Graphs2 {

    // Given a matrix of integers A of size N x M consisting of 0, 1 or 2.
    // Each cell can have three values:

    // The value 0 representing an empty cell.

    // The value 1 representing a fresh orange.

    // The value 2 representing a rotten orange.

    // Every minute, any fresh orange that is adjacent (Left, Right, Top, or Bottom) to a rotten orange becomes rotten. Return the minimum number of minutes that must elapse until no cell has a fresh orange. If this is impossible, return -1 instead.

    // Note: Your solution will run on multiple test cases. If you are using global variables, make sure to clear them.

    // Problem Constraints
    // 1 <= N, M <= 1000
    // 0 <= A[i][j] <= 2

    // Input Format
    // The first argument given is the integer matrix A.

    // Output Format
    // Return the minimum number of minutes that must elapse until no cell has a fresh orange.

    // If this is impossible, return -1 instead.
    // Example Input
    // Input 1:

    // A = [   [2, 1, 1]
    //         [1, 1, 0]
    //         [0, 1, 1]   ]
    // Input 2:

    // A = [   [2, 1, 1]
    //         [0, 1, 1]
    //         [1, 0, 1]   ]

    // Example Output
    // Output 1:

    // 4
    // Output 2:

    // -1

    // Example Explanation
    // Explanation 1:

    // Minute 0: [ [2, 1, 1]
    //             [1, 1, 0]
    //             [0, 1, 1] ]
    // Minute 1: [ [2, 2, 1]
    //             [2, 1, 0]
    //             [0, 1, 1] ]
    // Minute 2: [ [2, 2, 2]
    //             [2, 2, 0]
    //             [0, 1, 1] ]
    // Minute 3: [ [2, 2, 2]
    //             [2, 2, 0]
    //             [0, 2, 1] ]
    // Minute 4: [ [2, 2, 2]
    //             [2, 2, 0]
    //             [0, 2, 2] ]
    // At Minute 4, all the oranges are rotten.
    // Explanation 2:

    // The fresh orange at 2nd row and 0th column cannot be rotten, So return -1.
    public int rottenOranges(int[][] A) {
        int n = A.length;
        int m = A[0].length;

        int freshOranges = 0, ansTime = 0;
        Queue<int[]> q = new LinkedList<>();

        for(int i=0;i<n;i++) {
            for(int j=0;j<m;j++) {
                if(A[i][j] == 1) {
                    freshOranges++;
                } else if(A[i][j] == 2) {
                    q.add(new int[]{i,j,0}); // Add rotten oranges to queue
                    A[i][j] = 3; // Marking as visited
                }
            }
        }

        if(freshOranges == 0) {
            return -1;
        }

        while(q.size() != 0) {
            int[] cur = q.remove();
            int curi = cur[0];
            int curj = cur[1];
            int t = cur[2];
            ansTime = t;

            int[] dx = new int[]{-1,0,1,0};
            int[] dy = new int[]{0,1,0,-1};

            for(int k=0;k<4;k++) {
                int nbri = curi + dx[k];
                int nbrj = curj + dy[k];

                if(nbri >= 0 && nbri < n && nbrj >= 0 && nbrj < m && A[nbri][nbrj] == 1) {
                    q.add(new int[]{nbri, nbrj, t+1});
                    A[nbri][nbrj] = 3;
                    freshOranges--;
                }
            }
        }

        if(freshOranges == 0) {
            return ansTime;
        }
        return -1;
    }
    // Solution by team
    public class Solution {
        public int solve(int[][] grid) {
            Queue < int[] > queue = new LinkedList < > ();
            int fresh = 0;
            int time = 0;
            for (int i = 0; i < grid.length; i++) {
                for (int j = 0; j < grid[0].length; j++) {
                    if (grid[i][j] == 2) {
                        queue.add(new int[] { i, j });
                    } else if (grid[i][j] == 1) {
                        fresh++;
                    }
                }
            }
    
            int[][] direction = {
                { 1, 0 },
                { 0, 1 },
                {-1, 0 },
                { 0, -1 } };
            while (!queue.isEmpty() && fresh > 0) {
                time++;
                int size = queue.size();
                while (size > 0) {
                    int[] bad = queue.poll();
                    for (int[] dir: direction) {
                        int nrow = bad[0] + dir[0];
                        int ncol = bad[1] + dir[1];
    
                        if (nrow < 0 || nrow >= grid.length || ncol < 0 || ncol >= grid[0].length || grid[nrow][ncol] == 2 || grid[nrow][ncol] == 0) {
                            continue;
                        }
                        grid[nrow][ncol] = 2;
                        fresh--;
                        queue.add(new int[] {
                            nrow,
                            ncol
                        });
                    }
                    size--;
                }
            }
    
            if (fresh == 0) {
                return time;
            } else
                return -1;
        }
    }
}
