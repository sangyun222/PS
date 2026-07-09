import sys
from collections import deque

input = lambda: sys.stdin.readline().strip()

dx = [-1, 1, 0, 0]
dy = [0, 0, -1, 1]

N = int(input())
grid = [list(map(int, input().split())) for _ in range(N)]


def rain():
    tmp = 0
    for i in range(N):
        for j in range(N):
            if grid[i][j] >= 1:
                grid[i][j] -= 1
                tmp += 1

    if tmp == 0:
        return -1


def bfs(x, y, visited):
    que = deque()
    que.append((x, y))

    while que:
        x, y = que.popleft()
        visited[x][y] = True

        for i in range(4):
            nx = x + dx[i]
            ny = y + dy[i]

            if not (0 <= nx < N and 0 <= ny < N):
                continue

            if visited[nx][ny]:
                continue

            if not grid[nx][ny] == 0:
                visited[nx][ny] = True
                que.append((nx, ny))


res = []
while True:
    cnt = 0
    visited = [[False] * N for _ in range(N)]
    for i in range(N):
        for j in range(N):
            if grid[i][j] != 0 and not visited[i][j]:
                visited[i][j] = True
                bfs(i, j, visited)
                cnt += 1
    res.append(cnt)

    if rain() == -1:
        break

print(max(res))
