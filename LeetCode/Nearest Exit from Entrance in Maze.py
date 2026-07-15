class Solution:
    def nearestExit(self, maze: List[List[str]], entrance: List[int]) -> int:
        dx = [-1, 1, 0, 0]
        dy = [0, 0, -1, 1]

        N = len(maze)
        M = len(maze[0])

        visited = [[False] * M for _ in range(N)]

        def bfs(x, y):
            que = deque()
            que.append((x, y))
            visited[x][y] = True
            res = -1

            while que:
                for _ in range(len(que)):
                    x, y = que.popleft()

                    for i in range(4):
                        nx = x + dx[i]
                        ny = y + dy[i]

                        if not (0 <= nx < N and 0 <= ny < M):
                            if abs(nx - entrance[0]) + abs(ny - entrance[1]) == 1:
                                continue

                            return res + 1

                        if maze[nx][ny] == '+': continue

                        if visited[nx][ny]: continue

                        visited[nx][ny] = True
                        que.append((nx, ny))
                res += 1

            return -1

        return bfs(entrance[0], entrance[1])