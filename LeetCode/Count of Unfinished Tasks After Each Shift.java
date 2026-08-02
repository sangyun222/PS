class Solution {
    public int[] countTasks(int[] tasks, int[] shifts) {
        long[] prefix = new long[tasks.length + 1];
        prefix[0] = 0;
        for (int i = 0; i < tasks.length; i++) prefix[i + 1] = prefix[i] + tasks[i];

        int[] res = new int[shifts.length];
        int idx = 1;
        long prev = 0;
        for (int i = 0; i < shifts.length; i++) {
            long s = shifts[i] + prev;

            if (s >= prefix[tasks.length]) {
                res[i] = 0;
                idx = 1;
                prev = 0;
            }
            else {
                while (idx < prefix.length && s >= prefix[idx]) idx++;

                res[i] = tasks.length - idx + 1;
                prev = s;
            }
        }

        return res;
    }
}