import sys
from collections import deque

input = lambda: sys.stdin.readline().strip()

where_map = {
    "U": {"L": "L", "D": "R"},
    "D": {"L": "R", "D": "L"},
    "L": {"L": "D", "D": "U"},
    "R": {"L": "U", "D": "D"},
}
UDLR = {"U": 0, "D": 1, "L": 2, "R": 3}
dx = [-1, 1, 0, 0]
dy = [0, 0, -1, 1]

N = int(input())
K = int(input())

apple = [[False] * N for _ in range(N)]
now = deque()
now.append((0, 0))
time = 0
where_move = "R"

for _ in range(K):
    x, y = map(int, input().split())
    apple[x - 1][y - 1] = True

L = int(input())
next_move = {}
for _ in range(L):
    X, C = input().split()
    next_move[int(X)] = C

while True:
    time += 1
    current_x, current_y = now[-1]
    new_x, new_y = (
        current_x + dx[UDLR[where_move]],
        current_y + dy[UDLR[where_move]],
    )

    if (not (0 <= new_x < N and 0 <= new_y < N)) or (new_x, new_y) in now:
        print(time)
        break

    if not apple[new_x][new_y]:
        now.popleft()
    else:
        apple[new_x][new_y] = False

    now.append((new_x, new_y))

    if time in next_move:
        where_move = where_map[where_move][next_move[time]]
