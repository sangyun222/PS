class Solution {
    int[] tmp;
    int res = 0;
    public int reversePairs(int[] nums) {
        merge_sort(nums);
        return res;
    }
    private void merge_sort(int[] nums) {
        tmp = new int[nums.length];
        merge_sort(nums, 0, nums.length - 1);
    }
    private void merge_sort(int[] nums, int left, int right) {
        if (left >= right) return;

        int mid = (left + right) / 2;
        merge_sort(nums, left, mid);
        merge_sort(nums, mid + 1, right);

        merge(nums, left, mid, right);
    }
    private void merge(int[] nums, int left, int mid, int right) {
        int idx = mid + 1;
        for (int i = left; i <= mid; i++) {
            while (idx <= right && (long)nums[i] > 2L * nums[idx]) idx++;
            res += idx - (mid + 1);
        }

        int i = left, j = mid + 1, k = 0;
        while (i <= mid && j <= right) {
            if (nums[i] <= nums[j]) tmp[k++] = nums[i++];
            else tmp[k++] = nums[j++];
        }
        while (i <= mid) tmp[k++] = nums[i++];
        while (j <= right) tmp[k++] = nums[j++];

        System.arraycopy(tmp, 0, nums, left, right - left + 1);
    }
}