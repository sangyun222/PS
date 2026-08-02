class Solution {
    public long maxPairStrength(int[] nums) {
        long res = -1;
        for (int i = 0; i < nums.length - 1; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                int tmp_gcd = gcd(nums[i], nums[j]);
                long tmp = (1L * nums[i] * nums[j]) / (1L * tmp_gcd * tmp_gcd);
                res = Math.max(res, tmp);
            }
        }
        return res;
    }
    private int gcd(int a, int b) {
        if (b == 0) return a;
        return gcd(b, a % b);
    }
}