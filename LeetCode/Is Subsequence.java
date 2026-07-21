class Solution {
    public boolean isSubsequence(String s, String t) {
        int slen = s.length(), tlen = t.length();
        if (slen > tlen || slen == tlen && !s.equals(t)) return false;

        int prev = -1;
        for (int i = 0; i < slen; i++) {
            int idx = t.indexOf(s.charAt(i), prev + 1);
            if (idx > prev) prev = idx;
            else return false;
        }

        return true;
    }
}