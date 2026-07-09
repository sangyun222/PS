import sys
from collections import deque

input = lambda: sys.stdin.readline().strip()

dx = [-1, 1, 0, 0]
dy = [0, 0, -1, 1]

T = int(input())
for _ in range(T):
    w, h = map(int, input().split())
    grid = [list(input()) for _ in range(h)]
    visited = [[False] * w for _ in range(h)]
    fire = deque()
    move = deque()
    for i in range(h):
        for j in range(w):
            if grid[i][j] == "@":
                move.append((i, j))
            elif grid[i][j] == "*":
                fire.append((i, j))

    def fire_bfs():
        cnt = 1
        while fire:
            for _ in range(len(fire)):
                x, y = fire.popleft()

                for i in range(4):
                    nx = x + dx[i]
                    ny = y + dy[i]

                    if not (0 <= nx < h and 0 <= ny < w):
                        continue

                    if not (grid[nx][ny] == "." or grid[nx][ny] == "@"):
                        continue
                        
                    grid[nx][ny] = cnt
                    fire.append((nx, ny))

            cnt += 1

    def move_bfs():
        cnt = 1
        while move:
            for _ in range(len(move)):
                x, y = move.popleft()
                visited[x][y] = True

                if x == 0 or x == h - 1 or y == 0 or y == w - 1:
                    return cnt

                for i in range(4):
                    nx = x + dx[i]
                    ny = y + dy[i]

                    if not (0 <= nx < h and 0 <= ny < w):
                        continue

                    if grid[nx][ny] == "#" or grid[nx][ny] == "*" or visited[nx][ny]:
                        continue

                    if type(grid[nx][ny]) == int:
                        if grid[nx][ny] <= cnt:
                            continue

                    visited[nx][ny] = True
                    move.append((nx, ny))

            cnt += 1

        return -1

    fire_bfs()
    cnt = move_bfs()
    print("IMPOSSIBLE" if cnt == -1 else cnt)
