class Solution {
    int N, M, K;
    int[] dx = new int[]{-1, 1, 0, 0};
    int[] dy = new int[]{0, 0, -1, 1};
    public int shortestPath(int[][] grid, int k) {
        N = grid.length;
        M = grid[0].length;
        K = k;
        boolean[][][] visited = new boolean[N][M][K + 1];

        return bfs(grid, visited);
    }
    private int bfs(int[][] grid, boolean[][][] visited) {
        Queue<int[]> que = new ArrayDeque<>();
        que.offer(new int[]{0, 0, 0});
        visited[0][0][0] = true;
        int res = 0;

        while (!que.isEmpty()) {
            int tmp = que.size();
            for (int z = 0; z < tmp; z++) {
                int[] q = que.poll();
                int x = q[0], y = q[1], e = q[2];
                if (x == N - 1 && y == M - 1) return res;

                for (int i = 0; i < 4; i++) {
                    int nx = x + dx[i];
                    int ny = y + dy[i];

                    if (!(nx >= 0 && nx < N && ny >= 0 && ny < M)) continue;

                    if (grid[nx][ny] == 1) {
                        if (e + 1 <= K && !visited[nx][ny][e + 1]) {
                            visited[nx][ny][e + 1] = true;
                            que.offer(new int[]{nx, ny, e + 1});
                        }
                    }
                    else if (grid[nx][ny] == 0 && !visited[nx][ny][e]) {
                        visited[nx][ny][e] = true;
                        que.offer(new int[]{nx, ny, e});
                    }
                }
            }
            res++;
        }

        return -1;
    }
}