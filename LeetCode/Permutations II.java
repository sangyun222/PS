class Solution {
    public List<List<Integer>> permuteUnique(int[] nums) {
        Arrays.sort(nums);

        List<List<Integer>> res = new ArrayList<>();
        backtrack(nums, res, new ArrayList<>(), new boolean[nums.length]);
        return res;
    }
    private void backtrack(int[] nums, List<List<Integer>> res, List<Integer> path, boolean[] visited) {
        if (path.size() == nums.length) {
            res.add(new ArrayList<>(path));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            if (visited[i]) continue;
            if (i > 0 && nums[i] == nums[i - 1] && !visited[i - 1]) continue;

            visited[i] = true;
            path.add(nums[i]);

            backtrack(nums, res, path, visited);

            visited[i] = false;
            path.remove(path.size() - 1);
        }
    }
}