class Solution {
    long MOD = 1000000007;
    public int countValidSequences(int n, int k) {
        long res = nCr(n - 1, k - 1);

        if ((n + k) % 2 == 0) res -= nCr((n + k) / 2 - 1, k - 1);

        return (int)((res % MOD + MOD) % MOD);
    }
    private long pow(long base, long exp) {
        long res = 1;
        base %= MOD;
        while (exp > 0) {
            if (exp % 2 == 1) res = (res * base) % MOD;
            base = (base * base) % MOD;
            exp /= 2;
        }

        return res;
    }
    private long nCr(int n, int r) {
        if (r < 0 || r > n) return 0;
        if (r == 0 || r == n) return 1;

        if (r > n - r) r = n - r;
        long num = 1;
        long den = 1;

        for (int i = 0; i < r; i++) {
            num = (num * (n - i)) % MOD;
            den = (den * (i + 1)) % MOD;
        }

        return (num * pow(den, MOD - 2)) % MOD;
    }
}