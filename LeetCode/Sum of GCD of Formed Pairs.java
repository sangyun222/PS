class Solution {
    public long gcdSum(int[] nums) {
        ArrayList<Integer> prefixGcd = new ArrayList<>();
        int mx = nums[0];

        for (int i = 0; i < nums.length; i++) {
            mx = Math.max(mx, nums[i]);
            prefixGcd.add(gcd(nums[i], mx));
        }

        Collections.sort(prefixGcd);
        long res = 0;
        int size = prefixGcd.size();
        for (int i = 0; i < size / 2; i++) res += gcd(prefixGcd.get(i), prefixGcd.get(size - i - 1));

        return res;
    }
    public int gcd(int a, int b) {
        if (b == 0) return a;
        return gcd(b, a % b);
    }
}