#include <iostream>
#include <string>
using namespace std;
int N, grid[64][64];
void func(int x, int y, int size) {
    if (size == 1) {
        cout << grid[x][y];
        return;
    }

    bool OnlyZero, OnlyOne;
    OnlyZero = OnlyOne = true;

    for (int i = x; i < x + size; i++) {
        for (int j = y; j < y + size; j++) {
            if (grid[i][j] == 0) OnlyOne = false;
            if (grid[i][j] == 1) OnlyZero = false;
        }
    }

    if (OnlyZero) {
        cout << 0;
        return;
    }
    if (OnlyOne) {
        cout << 1;
        return;
    }

    int new_size = size / 2;
    cout << "(";
    func(x, y, new_size);
    func(x, y + new_size, new_size);
    func(x + new_size, y, new_size);
    func(x + new_size, y + new_size, new_size);
    cout << ")";
}
int main() {
    ios::sync_with_stdio(false);
    cin.tie(NULL);

    cin >> N;

    for (int i = 0; i < N; i++) {
        string S;
        cin >> S;

        for (int j = 0; j < S.length(); j++) {
            grid[i][j] = S[j] - '0';
        }
    }

    func(0, 0, N);
    return 0;
}