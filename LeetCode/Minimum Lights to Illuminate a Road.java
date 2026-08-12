class Solution {
    public int minLights(int[] lights) {
        int n = lights.length;

        int[] light = new int[n];
        Arrays.fill(light, -1);

        for (int i = 0; i < n; i++) {
            if (lights[i] > 0) {
                int left = Math.max(0, i - lights[i]);
                int right = Math.min(n - 1, i + lights[i]);

                light[left] = Math.max(light[left], right);
            }
        }

        int res = 0;
        int prev = -1;
        for (int i = 0; i < n; i++) {
            prev = Math.max(prev, light[i]);

            if (prev < i) {
                res++;
                prev = Math.min(n - 1, i + 2);
            }
        }

        return res;
    }
}