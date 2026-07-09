#include <stdio.h>
int N, r, c;
int ans = 0;
void Z(int x, int y, int size) {
    if (c == x && r == y) {
        printf("%d", ans);
        return;
    }
    else if (c >= x && c < x + size && r >= y && r < y + size) {
        int new_size = size / 2;
        Z(x, y, new_size);
        Z(x + new_size, y, new_size);
        Z(x, y + new_size, new_size);
        Z(x + new_size, y + new_size, new_size);
    }
    else ans += size * size;
}
int main() {
    scanf("%d %d %d", &N, &r, &c);

    Z(0, 0, 1 << N);
    return 0;
}