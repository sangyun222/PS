class Solution {
    public int reductionOperations(int[] nums) {
        Arrays.sort(nums);
        for (int i = 0, j = nums.length - 1; i < j; i++, j--) {
            int temp = nums[i];
            nums[i] = nums[j];
            nums[j] = temp;
        }

        int cnt = 0, prev = nums[0];
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != prev) {
                prev = nums[i];
                cnt++;
            }
        }

        int res = 0;
        prev = nums[0];
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == prev) res += cnt;
            else {
                cnt--;
                res += cnt;
                prev = nums[i];
            }
        }

        return res;
    }
}