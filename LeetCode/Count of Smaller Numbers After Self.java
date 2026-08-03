class Solution {
    int[] fenwick;
    int N;
    private void update(int i, int v) {
        while (i <= N) {
            fenwick[i] += v;
            i += (i & -i);
        }
    }
    private int query(int i) {
        int res = 0;
        while (i > 0) {
            res += fenwick[i];
            i -= (i & -i);
        }
        return res;
    }
    public List<Integer> countSmaller(int[] nums) {
        TreeSet<Integer> set = new TreeSet<>();
        for (int n : nums) set.add(n);

        Map<Integer, Integer> rankMap = new HashMap<>();
        int rank = 1;
        for (int n : set) rankMap.put(n, rank++);

        N = rankMap.size();
        fenwick = new int[N + 1];

        List<Integer> res = new ArrayList<>();
        for (int i = nums.length - 1; i >= 0; i--) {
            int idx = rankMap.get(nums[i]);

            res.add(query(idx - 1));
            update(idx, 1);
        }

        Collections.reverse(res);
        return res;
    }
}