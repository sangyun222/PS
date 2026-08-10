class Solution {
    public int characterReplacement(String s, int k) {
        int[] cnt = new int['Z' - 'A' + 1];

        int left = 0, right = 0;
        int mx = 0;
        while (right < s.length()) {
            cnt[s.charAt(right) - 'A']++;

            mx = Math.max(mx, cnt[s.charAt(right) - 'A']);

            if ((right - left + 1) - mx > k) {
                cnt[s.charAt(left) - 'A']--;
                left++;
            }

            right++;
        }

        return right - left;
    }
}