class NumArray {
    int N;
    int[] tree;
    public NumArray(int[] nums) {
        this.N = nums.length;
        this.tree = new int[N * 2];

        for (int i = 0; i < N; i++) {
            tree[N + i] = nums[i];
        }

        for (int i = N - 1; i > 0; i--) {
            tree[i] = tree[i << 1] + tree[i << 1 | 1];
        }
    }

    public void update(int index, int val) {
        for (tree[index += N] = val; index > 1; index >>= 1) {
            tree[index >> 1] = tree[index] + tree[index ^ 1];
        }
    }

    public int sumRange(int left, int right) {
        int res = 0;

        for (left += N, right += N; left <= right; left >>= 1, right >>= 1) {
            if ((left & 1) == 1) res += tree[left++];
            if ((right & 1) == 0) res += tree[right--];
        }

        return res;
    }
}

/**
 * Your NumArray object will be instantiated and called as such:
 * NumArray obj = new NumArray(nums);
 * obj.update(index,val);
 * int param_2 = obj.sumRange(left,right);
 */