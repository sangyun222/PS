class Solution {
    public String predictPartyVictory(String senate) {
        Queue<Integer> R = new ArrayDeque<>();
        Queue<Integer> D = new ArrayDeque<>();

        int N = senate.length();
        for (int i = 0; i < N; i++) {
            if (senate.charAt(i) == 'R') R.offer(i);
            else D.offer(i);
        }

        while (!R.isEmpty() && !D.isEmpty()) {
            int Ridx = R.poll();
            int Didx = D.poll();

            if (Ridx < Didx) R.offer(Ridx + N);
            else D.offer(Didx + N);
        }

        return R.isEmpty() ? "Dire" : "Radiant";
    }
}