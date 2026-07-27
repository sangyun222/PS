class Solution {
    public int[] beautifulArray(int n) {
        if (n == 1) return new int[]{1};

        int[] res = new int[n];

        int[] left = beautifulArray((n + 1) / 2);
        int idx = 0;
        for (int num : left) res[idx++] = 2 * num - 1;

        int[] right = beautifulArray(n / 2);
        for (int num : right) res[idx++] = 2 * num;

        return res;
    }
}