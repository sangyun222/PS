# Dijkstra

## 개요

**다익스트라 알고리즘**은 **음수 가중치가 없는 그래프**에서 하나의 시작 정점으로부터 모든 정점까지의 **최단 거리(Shortest Path)** 를 구하는 알고리즘이다.

현재까지 발견한 **가장 가까운 정점**부터 탐색을 진행하며, 우선순위 큐를 이용하여 효율적으로 구현할 수 있다.

---

## 시간 복잡도

| 자료구조 | 시간 복잡도 |
| :------: | :---------: |
| 배열 | `O(V²)` |
| Priority Queue | `O((V + E) log V)` |

공간 복잡도

- **Space** : `O(V + E)`

---

## 핵심 아이디어

다익스트라의 핵심은

> **가장 가까운 정점은 이후에도 최단 거리임이 보장된다.**

따라서

1. 현재 가장 가까운 정점을 선택한다.
2. 해당 정점을 거쳐 갈 수 있는 정점들의 거리를 갱신한다.
3. 이를 모든 정점에 대해 반복한다.

---

## 그래프 구성

인접 리스트를 이용하여 그래프를 저장한다.

```java
List<List<Node>> graph = new ArrayList<>();

for (int i = 0; i <= n; i++)
    graph.add(new ArrayList<>());
```

간선을 입력받으면

```java
graph.get(u).add(new Node(v, w));
```

처럼

- 목적지
- 가중치

를 저장한다.

---

## 최단 거리 배열

모든 정점의 거리를 무한대로 초기화한다.

```java
Arrays.fill(dist, Integer.MAX_VALUE);
```

시작 정점만

```java
dist[start] = 0;
```

으로 설정한다.

---

## Priority Queue

현재 가장 가까운 정점을 빠르게 선택하기 위해

Priority Queue를 사용한다.

```java
PriorityQueue<Node> pq =
    new PriorityQueue<>((a, b) -> a.cost - b.cost);
```

시작 정점을 삽입한다.

```java
pq.offer(new Node(start, 0));
```

항상 비용이 가장 작은 정점부터 탐색하게 된다.

---

## 오래된 정보 제거

Priority Queue에는 같은 정점이 여러 번 들어갈 수 있다.

따라서 이미 더 짧은 거리가 발견된 경우는 무시한다.

```java
Node curr = pq.poll();

if (curr.cost > dist[curr.city])
    continue;
```

이 과정이 없다면 불필요한 탐색이 증가한다.

---

## 거리 갱신

현재 정점을 거쳐 이동하는 비용을 계산한다.

```java
int newCost = curr.cost + next.cost;
```

더 짧은 경로를 발견했다면

```java
if (newCost < dist[next.city]) {
    dist[next.city] = newCost;
    pq.offer(new Node(next.city, newCost));
}
```

최단 거리를 갱신하고

새로운 정보를 Priority Queue에 넣는다.

---

## 전체 코드

```java
class Solution {
    public int networkDelayTime(int[][] times, int n, int k) {
        List<List<Node>> graph = new ArrayList<>();
        for (int i = 0; i <= n; i++) graph.add(new ArrayList<>());

        for (int[] time : times) {
            int u = time[0], v = time[1], w = time[2];

            graph.get(u).add(new Node(v, w));
        }

        int[] dist = new int[n + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[k] = 0;

        PriorityQueue<Node> pq = new PriorityQueue<>((a, b) -> a.cost - b.cost);
        pq.offer(new Node(k, 0));

        while (!pq.isEmpty()) {
            Node curr = pq.poll();
            if (curr.cost > dist[curr.city]) continue;

            for (Node next : graph.get(curr.city)) {
                int newCost = curr.cost + next.cost;

                if (newCost < dist[next.city]) {
                    dist[next.city] = newCost;
                    pq.offer(new Node(next.city, newCost));
                }
            }
        }

        int res = 0;
        for (int i = 1; i <= n; i++) {
            if (dist[i] == Integer.MAX_VALUE) return -1;
            res = Math.max(res, dist[i]);
        }

        return res;
    }
}
class Node {
    int city;
    int cost;

    public Node(int city, int cost) {
        this.city = city;
        this.cost = cost;
    }
}
```