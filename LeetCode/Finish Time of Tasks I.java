class Solution {
    private int[] baseTime;
    private List<Integer>[] children;
    public long finishTime(int n, int[][] edges, int[] baseTime) {
        this.baseTime = baseTime;

        List<Integer>[] children = new ArrayList[n];
        for (int i = 0; i < n; i++) children[i] = new ArrayList<>();
        for (int[] edge : edges) {
            int u = edge[0], v = edge[1];
            children[u].add(v);
        }
        this.children = children;

        return dfs(0);
    }
    private long dfs(int node) {
        if (children[node].isEmpty()) return baseTime[node];

        long earliest = Long.MAX_VALUE, latest = Long.MIN_VALUE;
        for (int child : children[node]) {
            long time = dfs(child);

            earliest = Math.min(earliest, time);
            latest = Math.max(latest, time);
        }

        long ownDuration = (latest - earliest) + baseTime[node];
        return latest + ownDuration;
    }
}