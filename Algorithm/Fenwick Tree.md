# Fenwick Tree

## 개요

**Fenwick Tree(Binary Indexed Tree, BIT)** 는 배열의 **누적 합(Prefix Sum)** 을 효율적으로 관리하는 자료구조이다.

다음 두 연산을 빠르게 수행할 수 있다.

- 특정 위치의 값 변경 (Point Update)
- 구간 합 계산 (Prefix Sum / Range Sum)

기존 배열에서 매번 합을 계산하면 `O(N)`이 걸리지만,

Fenwick Tree를 이용하면 두 연산 모두 `O(log N)`에 수행할 수 있다.

---

## 시간 복잡도

| Operation | Time |
| :------: | :--: |
| Update | `O(log N)` |
| Query | `O(log N)` |
| Build | `O(N log N)` |

공간 복잡도

- **Space** : `O(N)`

---

## 핵심 아이디어

Fenwick Tree는 각 노드가 **일정 구간의 누적 합**을 저장한다.

예를 들어

```
Index

1 2 3 4 5 6 7 8
```

각 인덱스가 관리하는 구간은

| Index | 저장하는 구간 |
|:----:|:-------------|
|1|1|
|2|1~2|
|3|3|
|4|1~4|
|5|5|
|6|5~6|
|7|7|
|8|1~8|

처럼 구성된다.

이 구간의 크기는

```
index & (-index)
```

으로 결정된다.

---

## Lowbit

Fenwick Tree에서 가장 중요한 연산은

```java
index & -index
```

이다.

이를 **Lowbit** 이라고 한다.

예를 들어

| Index | Binary | Lowbit |
|:----:|:------:|:------:|
|1|0001|1|
|2|0010|2|
|3|0011|1|
|4|0100|4|
|5|0101|1|
|6|0110|2|
|8|1000|8|

Lowbit는 현재 노드가 담당하는 구간의 크기를 의미한다.

---

## Update

특정 위치의 값을 변경하면

그 값을 포함하는 모든 구간을 함께 갱신해야 한다.

```java
while (idx <= N) {
    tree[idx] += value;
    idx += (idx & -idx);
}
```

다음으로 이동하는 위치 역시 Lowbit를 이용하여 결정된다.

예를 들어

```
idx = 5

↓

6

↓

8

↓

16
```

처럼 부모 노드 방향으로 이동한다.

---

## Query

Prefix Sum은 현재 위치에서

Lowbit만큼 이전으로 이동하며 값을 더한다.

```java
while (idx > 0) {
    sum += tree[idx];
    idx -= (idx & -idx);
}
```

예를 들어

```
idx = 13

↓

12

↓

8

↓

0
```

가 되며,

지나간 노드들의 합이

```
[1 ~ 13]
```

의 누적 합이 된다.

---

## 좌표 압축

Fenwick Tree의 인덱스는

```
1 ~ N
```

범위여야 한다.

만약

```text
1000000000
10
100000
500
```

처럼 값의 범위가 매우 크다면

좌표 압축을 수행한다.

```java
TreeSet<Integer> set = new TreeSet<>();

for (int num : arr)
    set.add(num);
```

정렬된 값을 순서대로

```java
Map<Integer, Integer> rankMap = new HashMap<>();
```

에 저장하여

```
10          → 1
500         → 2
100000      → 3
1000000000  → 4
```

처럼 새로운 인덱스를 만든다.

---

## 활용 예시 : Inversion Counting

Inversion이란

$$
i<j,\quad arr[i]>arr[j]
$$

를 만족하는 쌍의 개수이다.

배열을 왼쪽부터 탐색한다고 하자.

현재까지 처리한 원소의 개수는

```java
i
```

이고,

현재 값 이하의 원소 개수는

```java
query(compressedValue)
```

이다.

따라서 현재 원소보다 큰 이전 원소의 개수는

```java
i - query(compressedValue)
```

가 된다.

```java
inversionCount += i - query(compressedValue);
```

이후 현재 값을 Fenwick Tree에 추가한다.

```java
update(compressedValue, 1);
```

이 과정을 모든 원소에 대해 반복하면

전체 Inversion의 개수를 구할 수 있다.