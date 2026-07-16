class Solution {
    public long gcdSum(int[] nums) {
        int[] prefixGcd = new int[nums.length];
        int mx = nums[0];

        for (int i = 0; i < nums.length; i++) {
            mx = Math.max(mx, nums[i]);
            prefixGcd[i] = gcd(nums[i], mx);
        }

        Arrays.sort(prefixGcd);
        long res = 0;
        int size = prefixGcd.length;
        for (int i = 0; i < size / 2; i++) res += gcd(prefixGcd[i], prefixGcd[size - i - 1]);

        return res;
    }
    public int gcd(int a, int b) {
        if (b == 0) return a;
        return gcd(b, a % b);
    }
}