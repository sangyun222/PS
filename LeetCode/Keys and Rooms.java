class Solution {
    public boolean canVisitAllRooms(List<List<Integer>> rooms) {
        int N = rooms.size();
        boolean[] visited = new boolean[N];
        Queue<Integer> que = new ArrayDeque<>();

        que.offer(0);
        visited[0] = true;
        int cnt = 1;

        while (!que.isEmpty()) {
            int idx = que.poll();
            List<Integer> keys = rooms.get(idx);

            for (int key : keys) {
                if (visited[key]) continue;

                visited[key] = true;
                que.offer(key);
                cnt++;
            }
        }

        if (cnt == N) return true;
        return false;
    }
}