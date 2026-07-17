class Solution {
    public int firstMissingPositive(int[] nums) {
        HashMap<Integer, Integer> map = new HashMap<>();
        int max = 0;
        for (int n : nums) {
            if (n > 0) map.put(n, 1);
            if (n > max) max = n;
        }

        for (int i = 1; i <= max; i++) {
            if (!map.containsKey(i)) return i;
        }

        return max + 1;
    }
}