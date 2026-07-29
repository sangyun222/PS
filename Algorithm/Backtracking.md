# Backtracking

## 개요

**Backtracking(백트래킹)** 은 가능한 모든 경우를 탐색(Brute Force)하되, **정답이 될 수 없는 경우는 더 이상 탐색하지 않고 되돌아가는(Backtrack)** 탐색 기법이다.

DFS(Depth-First Search)를 기반으로 구현하는 경우가 대부분이며, 탐색 중간에 조건을 만족하지 않는 경우 해당 경로를 즉시 종료하여 탐색 횟수를 크게 줄일 수 있다.

---

## 시간 복잡도

최악의 경우 모든 경우를 탐색하므로

- **Time** : 문제에 따라 다름 (보통 `O(N!)`, `O(2^N)`, `O(K^N)` 등)
- **Space** : `O(N)` (재귀 호출 깊이)

> 백트래킹은 시간 복잡도를 바꾸는 알고리즘이 아니라, **불필요한 탐색을 줄이는 탐색 기법**이다.

---

## 핵심 아이디어

백트래킹은 다음 과정을 반복한다.

1. 현재 상태에서 가능한 선택을 한다.
2. 다음 상태로 이동한다.
3. 더 이상 진행할 수 없다면 이전 상태로 되돌아간다.
4. 다른 선택을 시도한다.

즉,

```
선택
 ↓
탐색
 ↓
복구
```

의 과정을 반복한다.

---

## 구현 패턴

```java
void dfs(...) {

    if (종료 조건) {
        // 정답 처리
        return;
    }

    for (선택 가능한 모든 경우) {

        // 선택
        ...

        dfs(...);

        // 복구
        ...
    }
}
```

---

## 예제

### 방문 배열을 이용한 순열(Permutation)

```java
private void backtrack(int[] nums, List<List<Integer>> res, List<Integer> path, boolean[] visited) {
    if (path.size() == nums.length) {
        res.add(new ArrayList<>(path));
        return;
    }

    for (int i = 0; i < nums.length; i++) {
        if (visited[i] == true) continue;

        visited[i] = true;
        path.add(nums[i]);

        backtrack(nums, res, path, visited);

        visited[i] = false;
        path.remove(path.size() - 1);
    }
}
```

백트래킹이 수행되는 부분은

```java
visited[i] = false;
path.remove(path.size() - 1);
```

이다.

현재 선택을 취소하여 이전 상태로 복원한 뒤,
다음 선택지를 탐색한다.

---

## DFS와의 차이

| DFS | Backtracking |
|------|--------------|
| 깊이 우선 탐색 방법 | DFS를 이용한 탐색 기법 |
| 모든 경로를 탐색 | 불가능한 경로는 즉시 종료 |
| 방문 자체가 목적 | 가능한 해를 찾는 것이 목적 |

즉,

> **Backtracking은 DFS를 이용하여 불필요한 탐색을 제거하는 기법이다.**

---

## 자주 사용되는 문제

- Permutation (순열)
- Combination (조합)
- N-Queen
- Sudoku
- Subset 생성
- 전화번호 문자 조합 (Letter Combinations)
- Combination Sum
- Palindrome Partitioning
- Rat in a Maze