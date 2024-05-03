package advanced.graphs;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

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
