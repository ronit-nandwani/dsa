package advanced.graphs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class Graphs2 {

    // Topological Sort

    // Given an directed acyclic graph having A nodes. A matrix B of size M x 2 is given which represents the M edges such that there is a edge directed from node B[i][0] to node B[i][1].
    // Topological sorting for Directed Acyclic Graph (DAG) is a linear ordering of vertices such that for every directed edge uv, vertex u comes before v in the ordering. Topological Sorting for a graph is not possible if the graph is not a DAG.

    // Return the topological ordering of the graph and if it doesn't exist then return an empty array.

    // If there is a solution return the correct ordering. If there are multiple solutions print the lexographically smallest one.

    // Ordering (a, b, c) is said to be lexographically smaller than ordering (e, f, g) if a < e or if(a==e) then b < f and so on.

    // NOTE:

    // There are no self-loops in the graph.
    // The graph may or may not be connected.
    // Nodes are numbered from 1 to A.
    // Your solution will run on multiple test cases. If you are using global variables make sure to clear them.


    // Problem Constraints
    // 2 <= A <= 104

    // 1 <= M <= min(100000,A*(A-1))

    // 1 <= B[i][0], B[i][1] <= A



    // Input Format
    // The first argument given is an integer A representing the number of nodes in the graph.

    // The second argument given a matrix B of size M x 2 which represents the M edges such that there is a edge directed from node B[i][0] to node B[i][1].



    // Output Format
    // Return a one-dimensional array denoting the topological ordering of the graph and it it doesn't exist then return empty array.



    // Example Input
    // Input 1:

    // A = 6
    // B = [  [6, 3] 
    //         [6, 1] 
    //         [5, 1] 
    //         [5, 2] 
    //         [3, 4] 
    //         [4, 2] ]
    // Input 2:

    // A = 3
    // B = [  [1, 2]
    //         [2, 3] 
    //         [3, 1] ]


    // Example Output
    // Output 1:

    // [5, 6, 1, 3, 4, 2]
    // Output 2:

    // []


    // Example Explanation
    // Explanation 1:

    // The given graph contain no cycle so topological ordering exists which is [5, 6, 1, 3, 4, 2]
    // Explanation 2:

    // The given graph contain cycle so topological ordering not possible we will return empty array.

    List<List<Integer>> list;
    public int[] solve(int A, int[][] B) {        
        int []indegree = new int[A+1];
        buildGraph(A,B,indegree);
        return topologicalOrder(A,indegree);
    }

   private int[] topologicalOrder(int n,int []indegree){
       int[] ans = new int[n];
       int k=0;
       PriorityQueue<Integer> q = new PriorityQueue();
       for(int i=1;i<=n;i++){
           if(indegree[i]==0){
               q.add(i);
           }
       }
       while(!q.isEmpty()){
           int curr = q.poll();
           ans[k]=curr;
           k++;
           List<Integer> nghbr = list.get(curr);
           for(int v:nghbr){
               indegree[v]--;
               if(indegree[v]==0){
                   q.add(v);
               }
           }
       }
       if(Arrays.stream(ans).anyMatch(i->i!=0)){
          return ans;
       }
       return new int[0];

    }
    private void buildGraph(int n, int[][] edges, int[] indegree){
        list = new ArrayList();
        for(int i=0;i<=n;i++){
            list.add(new ArrayList());
        }
        for(int i=0;i<edges.length;i++){
            int u=edges[i][0];
            int v=edges[i][1];
            list.get(u).add(v);
            indegree[v]++;
        }
    }

    // Solution by team

    public class Solution {
        static int maxn = 10009;
        static int[] in = new int[maxn];
        static ArrayList < ArrayList < Integer > > adj;
        public static void graph() {
            adj = new ArrayList < ArrayList < Integer > > (maxn);
            for (int i = 0; i < maxn; i++) {
                in [i] = 0;
                adj.add(new ArrayList < Integer > ());
            }
        }
        public int[] solve(int A, int[][] B) {
            graph();
            for (int[] edge: B) {
                adj.get(edge[0]).add(edge[1]); 
                in[edge[1]]++;
            }
            PriorityQueue < Integer > pq = new PriorityQueue < > ();
            for (int i = 1; i <= A; i++) {
                if ( in [i] == 0)
                    pq.offer(i);
            }
            ArrayList < Integer > ans = new ArrayList < Integer > ();
            while (pq.size() > 0) {
                int temp = pq.poll();
                ans.add(temp);
                for (int v: adj.get(temp)) {
                    in [v]--;
                    if (in[v] == 0)
                        pq.offer(v);
                }
            }
            if (ans.size() != A)
                ans.clear();
            int[] res = new int[ans.size()];
            for (int i = 0; i < ans.size(); i++)
                res[i] = ans.get(i);
            return res;
        }
    }



    // Possibility of Finishing

    // There are a total of A courses you have to take, labeled from 1 to A.

    // Some courses may have prerequisites, for example to take course 2 you have to first take course 1, which is expressed as a pair: [1,2].

    // So you are given two integer array B and C of same size where for each i (B[i], C[i]) denotes a pair.

    // Given the total number of courses and a list of prerequisite pairs, is it possible for you to finish all courses?

    // Return 1 if it is possible to finish all the courses, or 0 if it is not possible to finish all the courses.



    // Problem Constraints
    // 1 <= A <= 6*104

    // 1 <= length(B) = length(C) <= 105

    // 1 <= B[i], C[i] <= A



    // Input Format
    // The first argument of input contains an integer A, representing the number of courses.

    // The second argument of input contains an integer array, B.

    // The third argument of input contains an integer array, C.



    // Output Format
    // Return 1 if it is possible to finish all the courses, or 0 if it is not possible to finish all the courses.



    // Example Input
    // Input 1:

    // A = 3
    // B = [1, 2]
    // C = [2, 3]
    // Input 2:

    // A = 2
    // B = [1, 2]
    // C = [2, 1]


    // Example Output
    // Output 1:

    // 1
    // Output 2:

    // 0


    // Example Explanation
    // Explanation 1:

    // It is possible to complete the courses in the following order:
    //     1 -> 2 -> 3
    // Explanation 2:

    // It is not possible to complete all the courses.

    int cycle = 1;
    public int solve(int A, int[] B, int[] C) {
        int[][] visited = new int[A + 1][2];
        int clock = 1;
        for (int i = 0; i < B.length; i++) {
            clock = dfs(B[i], visited, B, C, clock);
            if (cycle == 0) return cycle;
        }
        return cycle;
    }

    private int dfs (int source, int[][] visited, int[] B, int[] C, int clock) {
        if (visited[source][0] == 1) {
            if (visited[source][1] == 0) {
                for (int i = 0; i < visited.length; i++) visited[i][0] = 1;
                cycle = 0;
            }
            return clock;
        }
        visited[source][0] = 1;
        clock++;
        for (int i = 0; i < B.length; i++) {
            if (B[i] == source) {
                clock = dfs(C[i], visited, B, C, clock);
            }
        }
        visited[source][1] = clock;
        clock++;
        return clock;
    }

    // Solution by team
    public class Solution {
        static int maxn = 100009;
        static ArrayList < ArrayList < Integer > > g;
        static int[] visited = new int[maxn];
        static int f = 0;
        public static void graph() {
            g = new ArrayList < ArrayList < Integer > > (maxn);
            for (int i = 0; i < maxn; i++) {
                visited[i] = 0;
                g.add(new ArrayList < Integer > ());
            }
        }
        public int solve(int A, int[] B, int[] C) {
            graph();
            for (int i = 0; i < B.length; i++) {
                g.get(B[i]).add(C[i]);
            }
            for (int i = 1; i <= A; i++) {
                if (visited[i] == 0) {
                    f = 0;
                    check_cycle(i);
                    if (f == 1)
                        break;
                }
            }
            f = 1 - f;
            return f;
        }
        public static void check_cycle(int u) {
            visited[u] = 1;
            for (int v: g.get(u)) {
                if (visited[v] == 0)
                    check_cycle(v);
                else if (visited[v] == 1)
                    f = 1;
            }
            visited[u] = 2;
        }
    }

    // Rotten Oranges

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
