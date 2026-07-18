class Solution {
    public int minSubarray(int[] nums, int p) {
        long total = 0;
        for (int x : nums) total += x;

        if (total < p) return -1;

        int mod = (int)(total % p);
        if (mod == 0) return 0;

        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);

        long prefix = 0;
        int res = nums.length;

        for (int i = 0; i < nums.length; i++) {
            prefix += nums[i];
            int tmp = (int)(prefix % p);

            int target = (tmp - mod + p) % p;

            if (map.containsKey(target)) res = Math.min(res, i - map.get(target));

            map.put(tmp, i);
        }

        return res == nums.length ? -1 : res;
    }
}