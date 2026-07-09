import sys
from collections import deque

input = lambda: sys.stdin.readline().strip()

dx = [-1, 1, 0, 0]
dy = [0, 0, -1, 1]

N, M = map(int, input().split())
grid = [list(input()) for _ in range(N)]
visited = [[[[False] * M for _ in range(N)] for _ in range(M)] for _ in range(N)]

for i in range(N):
    for j in range(M):
        if grid[i][j] == "R":
            Rx, Ry = i, j
        elif grid[i][j] == "B":
            Bx, By = i, j


def move(x, y, dx, dy):
    cnt = 0
    while grid[x + dx][y + dy] != "#" and grid[x][y] != "O":
        x += dx
        y += dy
        cnt += 1
    return x, y, cnt


def bfs(Rx, Ry, Bx, By):
    cnt = 0
    que = deque()
    que.append((Rx, Ry, Bx, By))
    visited[Rx][Ry][Bx][By] = True

    while que:
        for _ in range(len(que)):
            Rx, Ry, Bx, By = que.popleft()

            for i in range(4):
                nRx, nRy, tmp1 = move(Rx, Ry, dx[i], dy[i])
                nBx, nBy, tmp2 = move(Bx, By, dx[i], dy[i])

                if grid[nBx][nBy] == "O":
                    continue

                if grid[nRx][nRy] == "O":
                    return cnt + 1

                if (nRx, nRy) == (nBx, nBy):
                    if tmp1 > tmp2:
                        nRx -= dx[i]
                        nRy -= dy[i]
                    else:
                        nBx -= dx[i]
                        nBy -= dy[i]

                if visited[nRx][nRy][nBx][nBy]:
                    continue

                visited[nRx][nRy][nBx][nBy] = True
                que.append((nRx, nRy, nBx, nBy))

        cnt += 1

    return -1


cnt = bfs(Rx, Ry, Bx, By)
print(cnt if cnt != -1 else -1)
