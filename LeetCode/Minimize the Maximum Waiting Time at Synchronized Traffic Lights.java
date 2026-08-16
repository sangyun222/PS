class Solution {
    public int minPenalty(int period, int[] lights, int[] arrivalTime) {
        int mx = 0;
        for (int light : lights) mx = Math.max(mx, light);

        int res = 0;
        for (int time : arrivalTime) {
            int r = time % period;
            if (r >= mx) res = Math.max(res, period - r);
        }

        return res;
    }
}