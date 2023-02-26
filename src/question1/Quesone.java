package question1;

import java.util.*;

class Quesone {
    int id;
    int time;
    int cost;

    public Quesone(int id, int time, int cost) {
        this.id = id;
        this.time = time;
        this.cost = cost;
    }
}

class CosteffectiveRouteWithTimeConstraint {
    public static int CosteffectiveRoute(int[][] edges, int[] charges, int source, int destination, int timeConstraint) {
        // creating an graph as an adjency list
        Map<Integer, List<Quesone>> graph = new HashMap<>();
        for (int[] edge : edges) {
            int from = edge[0];
            int to = edge[1];
            int time = edge[2];
            int cost = charges[to];
            List<Quesone> list = graph.getOrDefault(from, new ArrayList<>());
            list.add(new Quesone(to, time, cost));
            graph.put(from, list);
        }

        int[] distances = new int[charges.length];
        boolean[] visited = new boolean[charges.length];
        Arrays.fill(distances, Integer.MAX_VALUE);
        distances[source] = 0;

        //  using priority queue to find the shortest path
        PriorityQueue<Quesone> queue = new PriorityQueue<>((a, b) -> a.time - b.time);
        queue.offer(new Quesone(source, 0, charges[source]));

        // Dijkstra's algorithm
        while (!queue.isEmpty()) {
            Quesone curr = queue.poll();
            if (curr.id == destination) {
                return curr.cost;
            }
            if (visited[curr.id]) {
                continue;
            }
            visited[curr.id] = true;
            for (Quesone neighbor : graph.getOrDefault(curr.id, new ArrayList<>())) {
                int newTime = curr.time + neighbor.time;
                int newCost = curr.cost + charges[neighbor.id];
                if (newTime <= timeConstraint && newCost < distances[neighbor.id]) {
                    distances[neighbor.id] = newCost;
                    queue.offer(new Quesone(neighbor.id, newTime, newCost));
                }
            }
        }

        // if any path cant be found
        return -1;
    }

    public static void main(String[] args) {
        int a [][]={{0,1,5}, {0,3,2}, {1,2,5}, {3,4,5}, {4,5,6}, {2,5,5}};
        System.out.println(CosteffectiveRoute(a, new int[]{10, 2, 3, 25, 25, 4},0,5,14));
    }
}