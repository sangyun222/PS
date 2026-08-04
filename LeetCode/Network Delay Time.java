class Solution {
    public int networkDelayTime(int[][] times, int n, int k) {
        List<List<Node>> graph = new ArrayList<>();
        for (int i = 0; i <= n; i++) graph.add(new ArrayList<>());

        for (int[] time : times) {
            int u = time[0], v = time[1], w = time[2];

            graph.get(u).add(new Node(v, w));
        }

        int[] dist = new int[n + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[k] = 0;

        PriorityQueue<Node> pq = new PriorityQueue<>((a, b) -> a.cost - b.cost);
        pq.offer(new Node(k, 0));

        while (!pq.isEmpty()) {
            Node curr = pq.poll();
            if (curr.cost > dist[curr.city]) continue;

            for (Node next : graph.get(curr.city)) {
                int newCost = curr.cost + next.cost;

                if (newCost < dist[next.city]) {
                    dist[next.city] = newCost;
                    pq.offer(new Node(next.city, newCost));
                }
            }
        }

        int res = 0;
        for (int i = 1; i <= n; i++) {
            if (dist[i] == Integer.MAX_VALUE) return -1;
            res = Math.max(res, dist[i]);
        }

        return res;
    }
}
class Node {
    int city;
    int cost;

    public Node(int city, int cost) {
        this.city = city;
        this.cost = cost;
    }
}