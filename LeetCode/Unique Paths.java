class Solution {
    public int uniquePaths(int m, int n) {
        int r = Math.min(m - 1, n - 1);

        long res = 1;
        for (int i = 1; i <= r; i++) {
            res = res * ((m + n - 2) - r + i) / i;
        }

        return (int)res;
    }
}