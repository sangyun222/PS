class Solution {
    public int orangesRotting(int[][] grid) {
        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};

        int N = grid.length, M = grid[0].length;
        boolean[][] visited = new boolean[N][M];

        Queue<int[]> que = new ArrayDeque<>();
        int fresh = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (grid[i][j] == 2) {
                    que.offer(new int[]{i, j});
                    visited[i][j] = true;
                }
                else if (grid[i][j] == 1) fresh++;
            }
        }

        int time = 0;
        while (!que.isEmpty()) {
            int size = que.size();
            for (int k = 0; k < size; k++) {
                int[] curr = que.poll();
                int x = curr[0], y = curr[1];

                for (int d = 0; d < 4; d++) {
                    int nx = x + dx[d];
                    int ny = y + dy[d];

                    if (nx >= 0 && nx < N && ny >= 0 && ny < M) {
                        if (!visited[nx][ny] && grid[nx][ny] == 1) {
                            grid[nx][ny] = 2;
                            visited[nx][ny] = true;
                            fresh--;
                            que.offer(new int[]{nx, ny});
                        }
                    }
                }
            }
            time++;
        }


        if (fresh > 0) return -1;
        return time == 0 ? 0 : time - 1;
    }
}