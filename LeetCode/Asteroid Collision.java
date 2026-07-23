class Solution {
    public int[] asteroidCollision(int[] asteroids) {
        Deque<Integer> stack = new ArrayDeque<>();
        stack.push(asteroids[0]);

        for (int i = 1; i < asteroids.length; i++) {
            int n = asteroids[i];
            boolean alive = true;

            while (!stack.isEmpty()) {
                int top = stack.pop();
                if (n * top > 0) {
                    stack.push(top);
                    stack.push(n);
                    alive = false;
                    break;
                }
                else {
                    if (top < 0) {
                        stack.push(top);
                        stack.push(n);
                        alive = false;
                        break;
                    }
                    else {
                        if (top > -n) {
                            stack.push(top);
                            alive = false;
                            break;
                        }
                        else if (top == -n) {
                            alive = false;
                            break;
                        }
                    }
                }
            }

            if (alive) stack.push(n);
        }

        int[] res = new int[stack.size()];
        for (int i = res.length - 1; i >= 0; i--) res[i] = stack.pop();
        return res;
    }
}