class Solution {
    public int minimumPushes(String word) {
        int[] counter = new int['z' - 'a' + 1];
        for (int i = 0; i < word.length(); i++) counter[word.charAt(i) - 'a']++;

        Queue<Integer> heap = new PriorityQueue<>();
        for (int i = 0; i <= 'z' - 'a'; i++) {
            if (counter[i] > 0) heap.offer(-counter[i]);
        }

        int res = 0, cnt = 0, w = 1;
        while (!heap.isEmpty()) {
            int n = -heap.poll();

            res += w * n;
            if (++cnt >= 8) {
                cnt = 0;
                w++;
            }
        }

        return res;
    }
}