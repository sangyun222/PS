import sys
import heapq

input = lambda: sys.stdin.readline().strip()
INF = int(1e9)

V, E = map(int, input().split())
K = int(input())
graph = [[] for _ in range(V + 1)]
distance = [INF] * (V + 1)

for _ in range(E):
    u, v, w = map(int, input().split())
    graph[u].append((w, v))


def dijkstra(start):
    que = []
    heapq.heappush(que, (0, start))
    distance[start] = 0

    while que:
        dist, now = heapq.heappop(que)

        if distance[now] < dist:
            continue

        for cost, neighbor in graph[now]:
            new_cost = dist + cost

            if new_cost < distance[neighbor]:
                distance[neighbor] = new_cost
                heapq.heappush(que, (new_cost, neighbor))


dijkstra(K)

for i in range(1, V + 1):
    if distance[i] == INF:
        print("INF")
    else:
        print(distance[i])
