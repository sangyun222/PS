class Solution {
    public List<Integer> remainingMethods(int n, int k, int[][] invocations) {
        boolean[] suspicious = new boolean[n];
        suspicious[k] = true;

        List<Integer>[] graph = new ArrayList[n];
        for (int i = 0; i < n; i++) graph[i] = new ArrayList<>();
        for (int[] i : invocations) graph[i[0]].add(i[1]);

        dfs(k, graph, suspicious);

        boolean canRemove = true;
        for (int[] e : invocations) {
            int u = e[0], v = e[1];

            if (!suspicious[u] && suspicious[v]) {
                canRemove = false;
                break;
            }
        }

        List<Integer> res = new ArrayList<>();
        if (!canRemove) {
            for (int i = 0; i < n; i++) res.add(i);
        }
        else {
            for (int i = 0; i < n; i++) if (!suspicious[i]) res.add(i);
        }
        return res;
    }
    private void dfs(int cur, List<Integer>[] graph, boolean[] suspicious) {
        suspicious[cur] = true;

        for (int next : graph[cur]) {
            if (!suspicious[next]) dfs(next, graph, suspicious);
        }
    }
}