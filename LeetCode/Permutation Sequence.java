class Solution {
    public String getPermutation(int n, int k) {
        int[] fac = new int[n + 1];
        fac[0] = 1;
        for (int i = 1; i <= n; i++) fac[i] = fac[i - 1] * i;

        List<Integer> nums = new ArrayList<>();
        for (int i = 1; i <= n; i++) nums.add(i);

        StringBuilder sb = new StringBuilder();
        k--;
        for (int i = n; i >= 1; i--) {
            int cnt = fac[i - 1];
            int idx = k / cnt;

            sb.append(nums.get(idx));
            nums.remove(idx);

            k %= cnt;
        }

        return sb.toString();
    }
}