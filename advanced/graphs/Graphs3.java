package advanced.graphs;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

class DPair {
    int node;
    int weight;

    DPair(int n, int w) {
        this.node = n;
        this.weight = w;
    }
}
class Pair {
    int a, b;
    public Pair(int u, int v) {
        this.a = u;
        this.b = v;
    }
}
class CustomComp implements Comparator < Pair > {
    @Override
    public int compare(Pair aa, Pair bb) {
        return aa.a - bb.a;
    }
}
public class Graphs3 {
    // Construction Cost

    // Given a graph with A nodes and C weighted edges. Cost of constructing the graph is the sum of weights of all the edges in the graph.

    // Find the minimum cost of constructing the graph by selecting some given edges such that we can reach every other node from the 1st node.

    // NOTE: Return the answer modulo 109+7 as the answer can be large.

    // Problem Constraints
    // 1 <= A <= 100000
    // 0 <= C <= 100000
    // 1 <= B[i][0], B[i][1] <= N
    // 1 <= B[i][2] <= 109



    // Input Format
    // First argument is an integer A.
    // Second argument is a 2-D integer array B of size C*3 denoting edges. B[i][0] and B[i][1] are connected by ith edge with weight B[i][2]



    // Output Format
    // Return an integer denoting the minimum construction cost.



    // Example Input
    // Input 1:

    // A = 3
    // B = [   [1, 2, 14]
    //         [2, 3, 7]
    //         [3, 1, 2]   ]
    // Input 2:

    // A = 3
    // B = [   [1, 2, 20]
    //         [2, 3, 17]  ]


    // Example Output
    // Output 1:

    // 9
    // Output 2:

    // 37


    // Example Explanation
    // Explanation 1:

    // We can take only two edges (2 -> 3 and 3 -> 1) to construct the graph. 
    // we can reach the 1st node from 2nd and 3rd node using only these two edges.
    // So, the total cost of costruction is 9.
    // Explanation 2:

    // We have to take both the given edges so that we can reach the 1st node from 2nd and 3rd node.


    ArrayList<int[]> [] adj;
    Queue<int[]> heap;
    boolean[] visited;

    public int solve(int A, int[][] B) {
        adj = new ArrayList[A + 1];
        createAdj(B);        
        heap = new PriorityQueue<>(Comparator.comparingInt(i -> i[2]));
        visited = new boolean[A + 1];
        visited[1] = true;
        addNodes(1);
        int count = 1;
        long ans = 0;
       
        while (count < A) {
            int[] node = heap.poll();
            if (!visited[node[1]]) {
                visited[node[1]] = true;
                count++;
                ans += node[2];
                ans %= 1000000007;
                addNodes(node[1]);      
            }
        }
        return (int)ans;
    }

    private void createAdj(int[][] B) {
        for (int i = 0; i < adj.length; i++) adj[i] = new ArrayList<>();
        for (int i = 0; i < B.length; i++) {
            adj[B[i][0]].add(new int[]{B[i][1], B[i][2]});
            adj[B[i][1]].add(new int[]{B[i][0], B[i][2]});
        }
    }

    private void addNodes(int source) {
        for (int i = 0; i < adj[source].size(); i++) {
            int[] node = adj[source].get(i);
            if (!visited[node[0]]) heap.add(new int[]{source, node[0], node[1]});
        }        
    }

    // Solution by team
    public class Solution {
        static int maxn = 100009;
        static int[] arr = new int[maxn];
        static int[] sz = new int[maxn];
        static ArrayList < pair > edges;
        static long mod = 1000000007;
        public int solve(int A, int[][] B) {
            ini();
            for (int i = 0; i < B.length; i++)
                edges.add(new pair(B[i][2], i));
            Collections.sort(edges);
            int ans = 0;
            for (pair p: edges) {
                int ind = p.second;
                int val = p.first;
                int u = B[ind][0];
                int v = B[ind][1];
                if (root(u) == root(v))
                    continue;
                else {
                    un(u, v);
                    ans += (int) val;
                    ans %= mod;
                }
            }
            return (int) ans;
    
        }
        public static void ini() {
            edges = new ArrayList < pair > ();
            for (int i = 0; i < maxn; i++) {
                arr[i] = i;
                sz[i] = 1;
            }
        }
        public static int root(int a) {
            while (arr[a] != a) {
                arr[a] = arr[arr[a]];
                a = arr[a];
            }
            return a;
        }
        public static void un(int a, int b) {
            int ra = root(a);
            int rb = root(b);
            if (sz[ra] <= sz[rb]) {
                arr[ra] = rb;
                sz[rb] += sz[ra];
            } else {
                arr[rb] = ra;
                sz[ra] += sz[rb];
            }
        }
    }
    class pair implements Comparable < pair > {
        int first;
        int second;
        public pair(int a, int b) {
            this.first = a;
            this.second = b;
        }
        @Override
        public int compareTo(pair temp) {
            return this.first - temp.first;
        }
    }




    // Dijkstra
    // Given a weighted undirected graph having A nodes and M weighted edges, and a source node C.

    // You have to find an integer array D of size A such that:

    // D[i]: Shortest distance from the C node to node i.
    // If node i is not reachable from C then -1.
    // Note:

    // There are no self-loops in the graph.
    // There are no multiple edges between two pairs of vertices.
    // The graph may or may not be connected.
    // Nodes are numbered from 0 to A-1.
    // Your solution will run on multiple test cases. If you are using global variables, make sure to clear them.


    // Problem Constraints
    // 1 <= A <= 1e5

    // 0 <= B[i][0],B[i][1] < A

    // 0 <= B[i][2] <= 1e3

    // 0 <= C < A



    // Input Format
    // The first argument is an integer A, representing the number of nodes in the graph.
    // The second argument is a matrix B of size M x 3, where each row represents an edge in the graph. The three columns of each row denote the source node B[i][0], the destination node B[i][1], and the weight of the edge B[i][2].
    // The third argument is an integer C, representing the source node for which the shortest distance to all other nodes needs to be found.


    // Output Format
    // Return the integer array D.



    // Example Input
    // Input 1:

    // A = 6
    // B = [   [0, 4, 9]
    //         [3, 4, 6] 
    //         [1, 2, 1] 
    //         [2, 5, 1] 
    //         [2, 4, 5] 
    //         [0, 3, 7] 
    //         [0, 1, 1] 
    //         [4, 5, 7] 
    //         [0, 5, 1] ] 
    // C = 4
    // Input 2:

    // A = 5
    // B = [   [0, 3, 4]
    //         [2, 3, 3] 
    //         [0, 1, 9] 
    //         [3, 4, 10] 
    //         [1, 3, 8]  ] 
    // C = 4


    // Example Output
    // Output 1:

    // D = [7, 6, 5, 6, 0, 6]
    // Output 2:

    // D = [14, 18, 13, 10, 0]


    // Example Explanation
    // Explanation 1:

    // All Paths can be considered from the node C to get shortest path
    // Explanation 2:

    // All Paths can be considered from the node C to get shortest path

    // Solution by me
    public ArrayList<Integer> dijkstra(int A, ArrayList<ArrayList<Integer>> B, int C) {
        ArrayList<ArrayList<DPair>> adjList = new ArrayList<>();
        ArrayList<Integer> dist = new ArrayList<>();
        for(int i=0;i<A;i++) {
            adjList.add(new ArrayList<>());
            dist.add(Integer.MAX_VALUE);
        }
        dist.set(C,0);

        for(int i=0; i < B.size(); i++) {
            adjList.get(B.get(i).get(0)).add(new DPair(B.get(i).get(1), B.get(i).get(2)));
            adjList.get(B.get(i).get(1)).add(new DPair(B.get(i).get(0), B.get(i).get(2))); // Adding both as it is undirected
        }

        PriorityQueue<DPair> pq = new PriorityQueue<>(new Comparator<DPair>() {
            @Override
            public int compare(DPair a, DPair b) {
                return a.weight - b.weight;
            }
        });
        pq.add(new DPair(C, 0));

        while(pq.size() > 0) {
            DPair curr = pq.remove();
            int totalCost = curr.weight;
            int cur = curr.node;

            if(dist.get(cur) < totalCost) continue;

            dist.set(cur, totalCost);

            for(int i=0; i < adjList.get(cur).size();i++) {
                DPair p = adjList.get(cur).get(i);

                int nbr = p.node;
                int edgeWeight = p.weight;

                int distToNbr = dist.get(cur) + edgeWeight;

                if(distToNbr < dist.get(nbr)) {
                    dist.set(nbr, distToNbr);
                    pq.add(new DPair(nbr, distToNbr));
                }
            }
        }

        for(int i = 0; i < dist.size(); i++){
            if(dist.get(i) == Integer.MAX_VALUE){
                dist.set(i,-1);
            }
        }

        return dist;
    }
    

    // Solution by team
    public class Solution {
        static int maxn = 100009;
        static int[] vis = new int[maxn];
        static ArrayList < ArrayList < Pair > > adj;
        public static void graph() {
            adj = new ArrayList < ArrayList < Pair > > (maxn);
            for (int i = 0; i < maxn; i++) {
                vis[i] = 0;
                adj.add(new ArrayList < Pair > ());
            }
        }
        public int[] solve(int A, int[][] B, int C) {
            graph();
            for (int[] edge: B) {
                adj.get(edge[0]).add(new Pair(edge[2], edge[1]));
                adj.get(edge[1]).add(new Pair(edge[2], edge[0]));
            }
            return dijkstra(A, C);
        }
    
        public static int[] dijkstra(int n, int source) {
            PriorityQueue < Pair > pq = new PriorityQueue < Pair > (new CustomComp());
            int[] dist = new int[n];
            for (int i = 0; i < n; i++) {
                dist[i] = Integer.MAX_VALUE;
            }
            dist[source] = 0;
            pq.offer(new Pair(0, source));
    
            while (pq.size() != 0) {
                Pair temp = pq.poll();
                int u = temp.b;
                if (vis[u] == 1)
                    continue;
                vis[u] = 1;
                for (int i = 0; i < adj.get(u).size(); i++) {
                    int v = adj.get(u).get(i).b, w = adj.get(u).get(i).a;
                    if (dist[u] + w < dist[v]) {
                        dist[v] = dist[u] + w;
                        pq.offer(new Pair(dist[v], v));
                    }
                }
            }
            for (int i = 0; i < n; i++) {
                if (dist[i] == Integer.MAX_VALUE)
                    dist[i] = -1;
            }
            return dist;
        }
    }

}
