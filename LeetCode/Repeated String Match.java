class Solution {
    public int repeatedStringMatch(String a, String b) {
        if (a.contains(b)) return 1;

        boolean[] exist = new boolean['z' - 'a' + 1];
        for (int i = 0; i < a.length(); i++) exist[a.charAt(i) - 'a'] = true;

        for (int i = 0; i < b.length(); i++) {
            if (!exist[b.charAt(i) - 'a']) return -1;
        }

        StringBuilder tmp = new StringBuilder(a);
        int res = 1;

        while (true) {
            tmp.append(a);
            res++;
            if (tmp.toString().contains(b)) return res;
            if (tmp.length() > b.length() * 2) return -1;
        }
    }
}