class Solution {
    public long maximumValue(int n, int s, int m) {
        if (n == 1) return s;

        if (n % 2 == 0) return s + 1L * m * (n / 2) - ((n - 1) / 2);
        else return s + m + (1L * (m - 1) * (n - 3)) / 2;
    }
}