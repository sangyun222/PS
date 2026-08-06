class Solution {
    public int countPaths(int n, int[][] roads) {
        int MOD = 1000000007;

        List<List<Node>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) graph.add(new ArrayList<>());
        for (int[] road : roads) {
            int u = road[0], v = road[1], t = road[2];

            graph.get(u).add(new Node(v, t));
            graph.get(v).add(new Node(u, t));
        }

        long[] dist = new long[n];
        Arrays.fill(dist, Long.MAX_VALUE);
        dist[0] = 0;

        PriorityQueue<Node> pq = new PriorityQueue<>((a, b) -> Long.compare(a.cost, b.cost));
        pq.offer(new Node(0, 0));

        int[] ways = new int[n];
        ways[0] = 1;

        while (!pq.isEmpty()) {
            Node curr = pq.poll();
            if (curr.cost > dist[curr.city]) continue;

            for (Node next : graph.get(curr.city)) {
                long newCost = curr.cost + next.cost;

                if (newCost < dist[next.city]) {
                    dist[next.city] = newCost;
                    ways[next.city] = ways[curr.city];
                    pq.offer(new Node(next.city, newCost));
                }
                else if (newCost == dist[next.city]) {
                    ways[next.city] = (ways[next.city] + ways[curr.city]) % MOD;
                }
            }
        }

        return ways[n - 1] % MOD;
    }
}
class Node {
    int city;
    long cost;

    public Node(int city, long cost) {
        this.city = city;
        this.cost = cost;
    }
}