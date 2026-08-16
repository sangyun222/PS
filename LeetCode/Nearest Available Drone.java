class Solution {
    public int nearestDrone(int[][] drones, int[] target) {
        int tx = target[0], ty = target[1];

        int mx = Integer.MAX_VALUE;
        int idx = -1;

        for (int i = 0; i < drones.length; i++) {
            int x = drones[i][0], y = drones[i][1], r = drones[i][2];

            int tmp = Math.abs(x - tx) + Math.abs(y - ty);
            if (tmp <= r && tmp < mx) {
                mx = tmp;
                idx = i;
            }
        }

        return mx == Integer.MAX_VALUE ? -1 : idx;
    }
}