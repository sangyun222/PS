class Solution {
    int[] tmp;
    public int[] sortArray(int[] nums) {
        merge_sort(nums);
        return nums;
    }
    private void merge_sort(int[] nums) {
        tmp = new int[nums.length];
        merge_sort(nums, 0, nums.length - 1);
    }
    private void merge_sort(int[] nums, int left, int right) {
        if (left == right) return;

        int mid = (left + right) / 2;
        merge_sort(nums, left, mid);
        merge_sort(nums, mid + 1, right);

        merge(nums, left, mid, right);
    }
    private void merge(int[] nums, int left, int mid, int right) {
        int l = left, r = mid + 1, idx = left;

        while (l <= mid && r <= right) {
            if (nums[l] <= nums[r]) tmp[idx++] = nums[l++];
            else tmp[idx++] = nums[r++];
        }

        while (r <= right) tmp[idx++] = nums[r++];
        while (l <= mid) tmp[idx++] = nums[l++];

        System.arraycopy(tmp, left, nums, left, right - left + 1);
    }
}