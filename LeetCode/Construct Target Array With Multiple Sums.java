class Solution {
    public boolean isPossible(int[] target) {
        PriorityQueue<Integer> heap = new PriorityQueue<>();
        long sum = 0;
        for (int n : target) {
            heap.offer(-n);
            sum += n;
        }

        while (true) {
            int tmp = -heap.poll();
            sum -= tmp;
            if (tmp == 1 || sum == 1) return true;

            if (sum == 0 || tmp <= sum || tmp % sum == 0) return false;
            heap.offer((int)-(tmp % sum));

            sum = sum + tmp % sum;
        }
    }
}