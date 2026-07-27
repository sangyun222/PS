class Solution {
    public int findCircleNum(int[][] isConnected) {
        int N = isConnected.length;
        boolean[] visited = new boolean[N];
        int res = 0;

        for (int i = 0; i < N; i++) {
            if (!visited[i]) {
                res++;
                dfs(i, isConnected, visited);
            }
        }

        return res;
    }
    private void dfs(int start, int[][] isConnected, boolean[] visited) {
        visited[start] = true;
        for (int i = 0; i < isConnected.length; i++) {
            if (start == i) continue;

            if (isConnected[start][i] == 1 && !visited[i]) dfs(i, isConnected, visited);
        }
    }
}