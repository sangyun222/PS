class Solution {
    public int maxProduct(int n) {
        PriorityQueue<Integer> heap = new PriorityQueue<>();
        while (n > 0) {
            heap.offer(-(n % 10));
            n /= 10;
        }

        int n1 = -heap.poll();
        int n2 = -heap.poll();
        return n1 * n2;
    }
}