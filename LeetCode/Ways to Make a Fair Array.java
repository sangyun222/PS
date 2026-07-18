class Solution {
    public int waysToMakeFair(int[] nums) {
        int len = nums.length;

        if (len == 1) return 1;

        int[] oddSum = new int[len + 1];
        oddSum[0] = 0;
        int[] evenSum = new int[len + 1];
        evenSum[0] = nums[0];


        int idx = 1;
        for (int i = 1; i < len; i++) {
            oddSum[i] = oddSum[i - 1];
            evenSum[i] = evenSum[i - 1];

            if (i % 2 == 1) oddSum[i] += nums[i];
            else evenSum[i] += nums[i];
        }

        int res = 0;
        for (int i = 0; i < len; i++) {
            int prevOdd = (i == 0) ? 0 : oddSum[i - 1];
            int prevEven = (i == 0) ? 0 : evenSum[i - 1];

            int afterOdd = oddSum[len - 1] - oddSum[i];
            int afterEven = evenSum[len - 1] - evenSum[i];

            if (prevEven + afterOdd == prevOdd + afterEven) res++;
        }

        return res;
    }
}