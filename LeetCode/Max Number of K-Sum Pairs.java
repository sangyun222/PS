class Solution {
    public int maxOperations(int[] nums, int k) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int n : nums) map.put(n, map.getOrDefault(n, 0) + 1);

        int res = 0;
        for (int n : nums) {
            int another = k - n;
            if ((n == another && map.getOrDefault(n, 0) >= 2) || (n != another && map.getOrDefault(n, 0) >= 1 && map.getOrDefault(another, 0) >= 1)) {
                map.put(n, map.getOrDefault(n, 0) - 1);
                map.put(another, map.getOrDefault(another, 0) - 1);
                res++;
            }
        }

        return res;
    }
}