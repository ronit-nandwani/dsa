package advanced.graphs;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;

public class Graphs1 {
    // Number of islands
    // Given a matrix of integers A of size N x M consisting of 0 and 1. A group of connected 1's forms an island. From a cell (i, j) such that A[i][j] = 1 you can visit any cell that shares a corner with (i, j) and value in that cell is 1.
    // More formally, from any cell (i, j) if A[i][j] = 1 you can visit:
    // (i-1, j) if (i-1, j) is inside the matrix and A[i-1][j] = 1.
    // (i, j-1) if (i, j-1) is inside the matrix and A[i][j-1] = 1.
    // (i+1, j) if (i+1, j) is inside the matrix and A[i+1][j] = 1.
    // (i, j+1) if (i, j+1) is inside the matrix and A[i][j+1] = 1.
    // (i-1, j-1) if (i-1, j-1) is inside the matrix and A[i-1][j-1] = 1.
    // (i+1, j+1) if (i+1, j+1) is inside the matrix and A[i+1][j+1] = 1.
    // (i-1, j+1) if (i-1, j+1) is inside the matrix and A[i-1][j+1] = 1.
    // (i+1, j-1) if (i+1, j-1) is inside the matrix and A[i+1][j-1] = 1.
    // Return the number of islands.

    // NOTE: Rows are numbered from top to bottom and columns are numbered from left to right.

    // Problem Constraints
    // 1 <= N, M <= 100

    // 0 <= A[i] <= 1

    // Input Format
    // The only argument given is the integer matrix A.

    // Output Format
    // Return the number of islands.

    // Example Input
    // Input 1:

    // A = [ 
    //     [0, 1, 0]
    //     [0, 0, 1]
    //     [1, 0, 0]
    //     ]
    // Input 2:

    // A = [   
    //     [1, 1, 0, 0, 0]
    //     [0, 1, 0, 0, 0]
    //     [1, 0, 0, 1, 1]
    //     [0, 0, 0, 0, 0]
    //     [1, 0, 1, 0, 1]    
    //     ]


    // Example Output
    // Output 1:

    // 2
    // Output 2:

    // 5


    // Example Explanation
    // Explanation 1:

    // The 1's at position A[0][1] and A[1][2] forms one island.
    // Other is formed by A[2][0].
    // Explanation 2:

    // There 5 island in total.
    int[][] visited1 = new int[1][1];
    int n = 0;
    int m = 0;
    int[] rows = new int[]{0,-1,0,1,-1,-1,1,1};
    int[] cols = new int[]{-1,0,1,0,-1,1,1,-1};
    public int numberOfIslands(int[][] A) {
        
        int count = 0;
        n = A.length;
        m = A[0].length;
        visited1 = new int[n][m];
        for(int i=0;i<n;i++) {
            for(int j=0;j<m;j++) {
                if(visited1[i][j] == 0 && A[i][j] == 1) {
                    count++;
                    dfs(i,j, A);
                }
            }
        }
        return count;
    }

    public void dfs(int i, int j, int[][] A) {
        visited1[i][j] = 1;
        
        for(int k=0;k<8;k++) {
            int x = i+rows[k];
            int y = j+cols[k];

            if(x >= 0 && x < n && y>=0&&y<m&&visited1[x][y] == 0 && A[x][y]==1) {
                dfs(x,y, A);
            }
        }
    }
    // Solution by team
    public class Solution {
        static int[] dx = new int[] {0, 1, -1, 0, 1, -1, 1, -1};
        static int[] dy = new int[] {1, 0, 0, -1, -1, 1, 1, -1};
        static int tc = 0;
        static int[][] visited = new int[105][105];
        public int solve(int[][] A) {
            int n = A.length;
            int m = A[0].length;
            ++tc;
            int ans = 0;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (A[i][j] == 1 && visited[i][j] != tc) {
                        dfs(i, j, n, m, A);
                        ans++;
                    }
                }
            }
            return ans;
        }
        public static boolean check(int i, int j, int n, int m, int[][] A) {
            return (i >= 0 && i < n && j >= 0 && j < m && (A[i][j] == 1) && visited[i][j] != tc);
        }
        public static void dfs(int i, int j, int n, int m, int[][] A) {
            visited[i][j] = tc;
            int di, dj;
            for (int k = 0; k < 8; ++k) {
                di = i + dx[k];
                dj = j + dy[k];
                if (check(di, dj, n, m, A))
                    dfs(di, dj, n, m, A);
            }
        }
    }



    // Path in directed graph

    // Given an directed graph having A nodes labelled from 1 to A containing M edges given by matrix B of size M x 2such that there is a edge directed from node
    // B[i][0] to node B[i][1].
    // Find whether a path exists from node 1 to node A.
    // Return 1 if path exists else return 0.
    // NOTE:
    // There are no self-loops in the graph.
    // There are no multiple edges between two nodes.
    // The graph may or may not be connected.
    // Nodes are numbered from 1 to A.
    // Your solution will run on multiple test cases. If you are using global variables make sure to clear them.
    // Problem Constraints
    // 2 <= A <= 105
    // 1 <= M <= min(200000,A*(A-1))
    // 1 <= B[i][0], B[i][1] <= A
    // Input Format
    // The first argument given is an integer A representing the number of nodes in the graph.
    // The second argument given a matrix B of size M x 2 which represents the M edges such that there is a edge directed from node B[i][0] to node B[i][1].
    // Output Format
    // Return 1 if path exists between node 1 to node A else return 0.
    // Example Input
    // Input 1:
    // A = 5
    // B = [  [1, 2] 
    //         [4, 1] 
    //         [2, 4] 
    //         [3, 4] 
    //         [5, 2] 
    //         [1, 3] ]
    // Input 2:
    // A = 5
    // B = [  [1, 2]
    //         [2, 3] 
    //         [3, 4] 
    //         [4, 5] ]
    // Example Output
    // Output 1:
    // 0
    // Output 2:
    // 1
    // Example Explanation
    // Explanation 1:
    // The given doens't contain any path from node 1 to node 5 so we will return 0.
    // Explanation 2:
    // Path from node1 to node 5 is ( 1 -> 2 -> 3 -> 4 -> 5 ) so we will return 1.
    Map<Integer,ArrayList<Integer>> map = new HashMap<>();
    int[] visited = new int[1];
    boolean path = false;
    public int pathInDirectedGraph(int A, int[][] B) {
        visited = new int[A+1];
        for(int i=0;i<B.length;i++) {
            if(map.containsKey(B[i][0])) {
                ArrayList<Integer> arr = map.get(B[i][0]);
                arr.add(B[i][1]);
                map.put(B[i][0], arr);
            } else {
                ArrayList<Integer> arr = new ArrayList<>();
                arr.add(B[i][1]);
                map.put(B[i][0], arr);
            }
        }

        dfs(1, A);
        if(path) return 1;
        return 0;
    }

    public void dfs(int u, int x) {
        visited[u] = 1;
        
        ArrayList<Integer> arr = map.get(u);
        if(arr == null) return;
        for(int v: arr) {
            if(x == v) path = true;
            if(visited[v] != 1) {
                dfs(v, x);
            }
        }
    }
    // Solution by team
    //     public class Solution {
    // static int maxn = 100009;
    // static int[] visited = new int[maxn];
    // static ArrayList < ArrayList < Integer > > adj;
    // public int solve(int A, int[][] B) {
    //     adj = new ArrayList < ArrayList < Integer > > (maxn);
    //     for (int i = 0; i < maxn; i++) {
    //     visited[i] = 0;
    //     adj.add(new ArrayList < Integer > ());
    //     }
    //     for (int[] edge: B)
    //     adj.get(edge[0]).add(edge[1]);
    //     if (isReachable(1, A) == true)
    //     return 1;
    //     return 0;
    // }
    // public static boolean isReachable(int s, int d) {
    //     if (s == d)
    //     return true;
    //     Queue < Integer > q = new ArrayDeque < > ();
    //     q.offer(s);
    //     visited[s] = 1;
    //     while (q.size() > 0) {
    //     s = q.poll();
    //     for (int v: adj.get(s)) {
    //         if (v == d) return true;
    //         if (visited[v] == 0) {
    //         visited[v] = 1;
    //         q.offer(v);
    //         }
    //     }
    //     }
    //     return false;
    // }
    // }
}
