class Solution {
    public long weightedSum(int[] parent, int[] nums) {
        int n = parent.length;
        int[] depth = new int[n];
        Arrays.fill(depth, -1);
        depth[0] = 1;

        int mx = 1;
        for (int i = 1; i < n; i++) {
            if (depth[i] != -1) continue;

            List<Integer> path = new ArrayList<>();
            int curr = i;
            while (depth[curr] == -1) {
                path.add(curr);
                curr = parent[curr];
            }

            int d = depth[curr];
            for (int j = path.size() - 1; j >= 0; j--) {
                d++;
                depth[path.get(j)] = d;
                mx = Math.max(mx, d);
            }
        }

        long res = 0;
        for (int i = 0; i < n; i++) {
            res += (long) nums[i] * (mx - depth[i] + 1);
        }
        return res;
    }
}