class Solution {
    public int countRatioSubarrays(int[] nums, int a, int b) {
        int res = 0;
        double target = (double) a / b;

        for (int i = 0; i < nums.length; i++) {
            int oddCnt = 0, evenCnt = 0;

            for (int j = i; j < nums.length; j++) {
                if (nums[j] % 2 == 0) evenCnt++;
                else oddCnt++;

                if (oddCnt > 0) {
                    double tmp = (double) evenCnt / oddCnt;
                    if (tmp <= target) res++;
                }
            }
        }

        return res;
    }
}