class Solution {
    public int minimumObstacles(int[][] grid) {
        int N = grid.length;
        int M = grid[0].length;
        int[] dx = new int[]{-1, 1, 0, 0};
        int[] dy = new int[]{0, 0, -1, 1};

        int[][] dist = new int[N][M];
        for (int i = 0; i < N; i++) Arrays.fill(dist[i], Integer.MAX_VALUE);

        Deque<int[]> que = new ArrayDeque<>();
        que.addFirst(new int[]{0, 0, 0});
        dist[0][0] = 0;

        while (!que.isEmpty()) {
            int[] q = que.poll();
            int x = q[0], y = q[1], r = q[2];
            if (x == N - 1 && y == M - 1) return r;

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (!(nx >= 0 && nx < N && ny >= 0 && ny < M)) continue;

                int rem = (grid[nx][ny] == 0) ? 0 : 1;
                if (r + rem < dist[nx][ny]) {
                    dist[nx][ny] = r + rem;

                    if (rem == 0) que.addFirst(new int[]{nx, ny, r + rem});
                    else que.addLast(new int[]{nx, ny, r + rem});
                }
            }
        }

        return 0;
    }
}