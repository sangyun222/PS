class Solution {
    public int maxArea(int[][] mat) {
        int N = mat.length, M = mat[0].length;

        int cnt = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) if (mat[i][j] == 1) cnt++;
            if (cnt >= 2) break;
        }
        if (cnt < 2) return 0;

        int[][] prefix = new int[N + 1][M + 1];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                prefix[i + 1][j + 1] = prefix[i][j + 1] + prefix[i + 1][j] - prefix[i][j] + mat[i][j];
            }
        }

        int size = Math.min(N, M);
        size = Math.min(size, (int)Math.sqrt((double)((long)N * M) / 2.0));

        for (int i = size; i >= 1; i--) {
            if (placeCheck(prefix, N, M, i)) {
                return i * i;
            }
        }
        return 0;
    }
    private boolean placeCheck(int[][] pre, int N, int M, int size) {
        int minR = Integer.MAX_VALUE, maxR = Integer.MIN_VALUE;
        int minC = Integer.MAX_VALUE, maxC = Integer.MIN_VALUE;
        int cnt = 0;

        for (int i = 0; i + size <= N; i++) {
            for (int j = 0; j + size <= M; j++) {
                int tmp = pre[i + size][j + size] - pre[i][j + size] - pre[i + size][j] + pre[i][j];

                if (tmp == size * size) {
                    cnt++;
                    minR = Math.min(minR, i);
                    maxR = Math.max(maxR, i);
                    minC = Math.min(minC, j);
                    maxC = Math.max(maxC, j);

                    if (cnt >= 2 && (maxR - minR >= size || maxC - minC >= size)) return true;
                }
            }
        }

        return false;
    }
}