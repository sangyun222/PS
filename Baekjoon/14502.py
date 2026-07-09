import sys
from collections import deque
import copy

sys.setrecursionlimit(10**6)
input = sys.stdin.readline

N, M = map(int, input().split())
grid = [list(map(int, input().split())) for _ in range(N)]
empty = []
virus = deque()
tmp = []
res = deque()
area = []

for i in range(N):
    for j in range(M):
        if grid[i][j] == 0:
            empty.append((i, j))
        elif grid[i][j] == 2:
            virus.append((i, j))


def backtrack(start):
    if len(tmp) == 3:
        res.append(list(tmp))
        return

    for i in range(start, len(empty)):
        tmp.append(empty[i])

        backtrack(i + 1)

        tmp.pop()


backtrack(0)

dx = [-1, 1, 0, 0]
dy = [0, 0, -1, 1]


def bfs(grid_now, virus_tmp):
    while virus_tmp:
        x, y = virus_tmp.popleft()

        for i in range(4):
            nx = x + dx[i]
            ny = y + dy[i]

            if not (0 <= nx < N and 0 <= ny < M):
                continue

            if not grid_now[nx][ny] == 0:
                continue

            grid_now[nx][ny] = 2
            virus_tmp.append((nx, ny))


for wall in res:
    grid_now = copy.deepcopy(grid)
    virus_tmp = copy.deepcopy(virus)
    for wx, wy in wall:
        grid_now[wx][wy] = 1

    bfs(grid_now, virus_tmp)
    cnt = 0

    for i in range(N):
        for j in range(M):
            if grid_now[i][j] == 0:
                cnt += 1

    area.append(cnt)

print(max(area))
