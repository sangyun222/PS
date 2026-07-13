from collections import deque

class Solution:
    def numIslands(self, grid: List[List[str]]) -> int:
        dx = [-1, 1, 0, 0]
        dy = [0, 0, -1, 1]

        N = len(grid)
        M = len(grid[0])

        visited = [[False] * M for _ in range(N)]


        def bfs(x, y):
            que = deque()
            que.append((x, y))
            visited[x][y] = True

            while que:
                x, y = que.popleft()

                for i in range(4):
                    nx = x + dx[i]
                    ny = y + dy[i]

                    if not (0 <= nx < N and 0 <= ny < M):
                        continue

                    if visited[nx][ny]:
                        continue

                    if grid[nx][ny] == "1":
                        visited[nx][ny] = True
                        que.append((nx, ny))


        res = 0
        for i in range(N):
            for j in range(M):
                if not visited[i][j] and grid[i][j] == '1':
                    bfs(i, j)
                    res += 1

        return res