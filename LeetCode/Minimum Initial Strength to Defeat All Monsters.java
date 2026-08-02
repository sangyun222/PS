class Solution {
    public long minInitialStrength(int[] monsters, int[][] boosts) {
        long[] prefix = new long[monsters.length + 1];
        for (int i = 0; i < boosts.length; i++) {
            int l = boosts[i][0], r = boosts[i][1], v = boosts[i][2];

            prefix[l] += v;
            prefix[r + 1] -= v;
        }
        for (int i = 1; i < monsters.length; i++) prefix[i] += prefix[i - 1];

        long res = BSearch(monsters, prefix, 0, 50000000000000L);
        return res;
    }
    private long BSearch(int[] monsters, long[] prefix, long start, long end) {
        long res = end;
        while (start <= end) {
            long mid = start + (end - start) / 2;
            if (fight(monsters, prefix, mid)) {
                res = mid;
                end = mid - 1;
            }
            else start = mid + 1;
        }

        return res;
    }
    private boolean fight(int[] monsters, long[] prefix, long hp) {
        long now = hp;
        for (int i = 0; i < monsters.length; i++) {
            if (now + prefix[i] < monsters[i]) return false;

            now = Math.max(0, now - monsters[i]);
        }

        return true;
    }
}