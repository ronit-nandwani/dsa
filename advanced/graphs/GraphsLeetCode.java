package advanced.graphs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class GraphsLeetCode {
    // 1514. Path with Maximum Probability
    // Solved
    // Medium
    // Topics
    // Companies
    // Hint
    // You are given an undirected weighted graph of n nodes (0-indexed), represented by an edge list where edges[i] = [a, b] is an undirected edge connecting the nodes a and b with a probability of success of traversing that edge succProb[i].
    // Given two nodes start and end, find the path with the maximum probability of success to go from start to end and return its success probability.

    // If there is no path from start to end, return 0. Your answer will be accepted if it differs from the correct answer by at most 1e-5.

    // Example 1:

    // Input: n = 3, edges = [[0,1],[1,2],[0,2]], succProb = [0.5,0.5,0.2], start = 0, end = 2
    // Output: 0.25000
    // Explanation: There are two paths from start to end, one having a probability of success = 0.2 and the other has 0.5 * 0.5 = 0.25.
    // Example 2:

    // Input: n = 3, edges = [[0,1],[1,2],[0,2]], succProb = [0.5,0.5,0.3], start = 0, end = 2
    // Output: 0.30000
    // Example 3:

    // Input: n = 3, edges = [[0,1]], succProb = [0.5], start = 0, end = 2
    // Output: 0.00000
    // Explanation: There is no path between 0 and 2.
    
    // Constraints:

    // 2 <= n <= 10^4
    // 0 <= start, end < n
    // start != end
    // 0 <= a, b < n
    // a != b
    // 0 <= succProb.length == edges.length <= 2*10^4
    // 0 <= succProb[i] <= 1
    // There is at most one edge between every two nodes.

    // Solution by me - 28 ms

    public double maxProbability(int n, int[][] edges, double[] succProb, int start, int end) {
        // Step 1: Build the graph
        List<List<Pair>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }
        
        for (int i = 0; i < edges.length; i++) {
            int u = edges[i][0];
            int v = edges[i][1];
            double prob = succProb[i];
            graph.get(u).add(new Pair(v, prob));
            graph.get(v).add(new Pair(u, prob));
        }
        
        // Step 2: Use Dijkstra's algorithm with a max-heap (priority queue)
        PriorityQueue<Pair> maxHeap = new PriorityQueue<>((a, b) -> Double.compare(b.probability, a.probability));
        maxHeap.offer(new Pair(start, 1.0));
        
        double[] maxProb = new double[n];
        maxProb[start] = 1.0;
        
        while (!maxHeap.isEmpty()) {
            Pair current = maxHeap.poll();
            int node = current.node;
            double prob = current.probability;
            
            if (node == end) {
                return prob;
            }
            
            for (Pair neighbor : graph.get(node)) {
                int nextNode = neighbor.node;
                double edgeProb = neighbor.probability;
                double newProb = prob * edgeProb;
                
                if (newProb > maxProb[nextNode]) {
                    maxProb[nextNode] = newProb;
                    maxHeap.offer(new Pair(nextNode, newProb));
                }
            }
        }
        
        return 0.0;
    }
    
    // Helper class to store the node and its associated probability
    static class Pair {
        int node;
        double probability;
        
        Pair(int node, double probability) {
            this.node = node;
            this.probability = probability;
        }
    }

    // Fastest Solution - 8 ms

    public double maxProbabilityFastest(int n, int[][] edges, double[] succProb, int start_node, int end_node) {
        // Create an array to store the maximum probability for each node
        double[] maxProb = new double[n];
        Arrays.fill(maxProb, 0.0);  // Initialize all probabilities to 0.0
        maxProb[start_node] = 1.0;  // Probability of starting node is 1.0

        // Iterate 9 times (or you can use any number of iterations as needed)
        for (int i = 0; i < 9; ++i) {
            // Traverse all edges
            for (int j = 0; j < edges.length; ++j) {
                int u = edges[j][0];  // First node in the edge
                int v = edges[j][1];  // Second node in the edge
                double prob = succProb[j];  // Success probability for this edge

                // Update maxProb[v] if a better probability is found via u
                if (maxProb[u] * prob > maxProb[v]) {
                    maxProb[v] = maxProb[u] * prob;
                }

                // Update maxProb[u] if a better probability is found via v
                if (maxProb[v] * prob > maxProb[u]) {
                    maxProb[u] = maxProb[v] * prob;
                }
            }
        }

        // Return the maximum probability to reach the end node
        return maxProb[end_node];
    }
}
