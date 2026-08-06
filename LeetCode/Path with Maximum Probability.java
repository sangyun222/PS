class Solution {
    public double maxProbability(int n, int[][] edges, double[] succProb, int start_node, int end_node) {
        List<List<Node>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) graph.add(new ArrayList<>());
        for (int i = 0; i < edges.length; i++) {
            int a = edges[i][0], b = edges[i][1];
            double p = succProb[i];

            graph.get(a).add(new Node(b, p));
            graph.get(b).add(new Node(a, p));
        }

        double[] dist = new double[n];
        Arrays.fill(dist, -Double.MAX_VALUE);
        dist[start_node] = 1.0;

        PriorityQueue<Node> pq = new PriorityQueue<>((a, b) -> Double.compare(b.cost, a.cost));
        pq.offer(new Node(start_node, 1));

        while (!pq.isEmpty()) {
            Node curr = pq.poll();
            if (curr.cost < dist[curr.city]) continue;

            for (Node next : graph.get(curr.city)) {
                Double newCost = curr.cost * next.cost;

                if (newCost > dist[next.city]) {
                    dist[next.city] = newCost;
                    pq.offer(new Node(next.city, newCost));
                }
            }
        }

        return dist[end_node] == -Double.MAX_VALUE ? 0 : dist[end_node];
    }
}
class Node {
    int city;
    double cost;

    public Node(int city, double cost) {
        this.city = city;
        this.cost = cost;
    }
}