class Solution {
    public int[] closestPrimes(int left, int right) {
        int n = right + 1;
        boolean[] isNotPrime = new boolean[n];
        for (int i = 2; i * i <= right; i++) {
            if (!isNotPrime[i]) {
                for (int j = i * i; j <= right; j += i) {
                    isNotPrime[j] = true;
                }
            }
        }

        int[] res = {-1, -1};
        int prev = -1;
        int mn = Integer.MAX_VALUE;
        for (int idx = Math.max(left, 2); idx <= right; idx++) {
            if (!isNotPrime[idx]) {
                if (prev != -1 && idx - prev < mn) {
                    mn = idx - prev;
                    res[0] = prev;
                    res[1] = idx;
                }

                prev = idx;
            }
        }

        return res;
    }
}