class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);

        HashSet<List<Integer>> set = new HashSet<>();
        int N = nums.length;
        for (int i = 0; i < N - 2; i++) {
            int left = i + 1;
            int right = N - 1;

            while (left < right) {
                int tmp = nums[i] + nums[left] + nums[right];
                if (tmp == 0) {
                    set.add(Arrays.asList(nums[i], nums[left], nums[right]));
                    left++;
                }
                else if (tmp < 0) left++;
                else if (tmp > 0) right--;
            }
        }

        List<List<Integer>> ans = new ArrayList<>(set);
        return ans;
    }
}