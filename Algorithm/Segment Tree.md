# Segment Tree

## 개요

**Segment Tree**는 배열을 여러 구간으로 나누어 각 구간에 대한 정보를 저장하는 자료구조이다.

주로 다음과 같은 연산을 효율적으로 처리할 때 사용한다.

- 구간 합
- 구간 최댓값 / 최솟값
- 구간 최대공약수
- 그 외 결합 연산이 가능한 다양한 구간 질의

일반적인 배열에서는 구간의 값을 직접 계산하면 `O(N)`이 필요하지만,

Segment Tree를 이용하면

- **Query** : `O(log N)`
- **Point Update** : `O(log N)`

에 처리할 수 있다.

---

## 시간 복잡도

| Operation | Time |
| :---: | :---: |
| Build | `O(N)` |
| Query | `O(log N)` |
| Point Update | `O(log N)` |

공간 복잡도

- **Space** : `O(N)`

> 일반적인 재귀 Segment Tree에서는 `O(4N)` 정도의 배열을 사용하지만,
> **비재귀 Segment Tree**에서는 `O(2N)` 크기의 배열만 사용한다.

---

# 기본 구조

이 구현에서는 `tree`의 앞쪽 절반을 부모 노드,
뒤쪽 절반을 실제 배열의 원소로 사용한다.

배열의 크기가 `N`이라면

```java
tree = new int[n * 2];
```

와 같이 만든다.

구조는 다음과 같다.

```text
tree

[1 ... N-1] [N ... 2N-1]
     ↑              ↑
   내부 노드       실제 값
```

예를 들어 `N = 4`이고 배열이

```text
[1, 2, 3, 4]
```

라면

```text
index :  0  1  2  3  4  5  6  7
value :  -  -  -  -  1  2  3  4
```

가 된다.

즉,

```java
tree[n + i] = arr[i];
```

를 통해 원본 배열을 트리의 리프 노드에 저장한다.

---

# Tree 구성

리프 노드에 값을 저장한 후,
부모 노드는 두 자식 노드를 이용하여 계산한다.

```java
for (int i = n - 1; i > 0; i--) {
    tree[i] = tree[i << 1] + tree[i << 1 | 1];
}
```

---

# 핵심 원리

Segment Tree의 핵심은

> **부모 노드의 값을 두 자식 노드의 값으로 계산할 수 있어야 한다.**

는 것이다.

현재 구현에서는

```text
parent = left child + right child
```

를 사용하므로 **구간 합** 을 구하는 Segment Tree이다.

하지만 이 연산을 바꾸면

- `min()` → 구간 최솟값
- `max()` → 구간 최댓값
- `gcd()` → 구간 최대공약수

등으로 쉽게 변경할 수 있다.

---

# Point Update

특정 위치의 값을 변경하는 경우

```java
tree[p += n] = value;
```

를 통해 해당 리프 노드를 먼저 수정한다.

그 이후 부모 노드로 올라가면서 값을 다시 계산한다.

```java
for (; p > 1; p >>= 1) {
    tree[p >> 1] = tree[p] + tree[p ^ 1];
}
```

여기서

```text
p >> 1
```

은 부모 노드의 인덱스이다.

또한

```text
p ^ 1
```

은 현재 노드의 형제 노드를 의미한다.

따라서

```java
tree[p >> 1] = tree[p] + tree[p ^ 1];
```

은

> 현재 노드와 형제 노드를 이용하여 부모 노드를 다시 계산한다.

는 의미이다.

---

# Range Query

구간 `[left, right]`의 합을 구할 때는

```java
left += n;
right += n;
```

을 통해 실제 리프 노드의 위치로 이동한다.

```java
for (left += n, right += n; left <= right; left >>= 1, right >>= 1) {
    ...
}
```

이후 양 끝에서부터 필요한 구간만 선택한다.

```java
if ((left & 1) == 1)
    res += tree[left++];

if ((right & 1) == 0)
    res += tree[right--];
```

현재 노드가 해당 구간에 포함되면 결과에 추가하고,
포함한 노드는 더 이상 탐색할 필요가 없으므로 다음 노드로 이동한다.

마지막으로

```java
left >>= 1;
right >>= 1;
```

을 수행하여 한 단계 위의 부모 노드로 이동한다.

---

# Range Sum

현재 구현은 부모 노드를

```java
tree[i] = tree[i << 1] + tree[i << 1 | 1];
```

로 계산하기 때문에 **Range Sum Segment Tree**이다.

Query에서도

```java
res += tree[left];
```

와 같이 합을 누적한다.

따라서

```text
Operation = +
```

인 Segment Tree라고 생각할 수 있다.

---

# Range Minimum Query (RMQ)

구간 최솟값을 구하고 싶다면
부모 노드의 연산을 `+`에서 `min`으로 변경하면 된다.

## Tree 구성

기존

```java
tree[i] = tree[i << 1] + tree[i << 1 | 1];
```

을

```java
tree[i] = Math.min(tree[i << 1], tree[i << 1 | 1]);
```

로 변경한다.

단, `tree`의 초기값은 `0`이 아니라 충분히 큰 값으로 초기화해야 한다.

```java
Arrays.fill(tree, Integer.MAX_VALUE);
```

---

## Update

기존

```java
tree[p >> 1] = tree[p] + tree[p ^ 1];
```

을

```java
tree[p >> 1] = Math.min(tree[p], tree[p ^ 1]);
```

로 변경한다.

---

## Query

기존

```java
int res = 0;
```

은 최솟값 연산에서는 사용할 수 없다.

따라서

```java
int res = Integer.MAX_VALUE;
```

로 초기화한다.

그리고

```java
res += tree[left];
```

를

```java
res = Math.min(res, tree[left]);
```

로 변경한다.

오른쪽도 동일하다.

```java
res = Math.min(res, tree[right]);
```

---

# Fenwick Tree와 비교

Fenwick Tree도 Prefix Sum 및 특정 형태의 구간 연산을 `O(log N)`에 처리할 수 있다.

하지만 Segment Tree는 더 다양한 연산을 지원한다.

| | Fenwick Tree | Segment Tree |
| :--- | :---: | :---: |
| Point Update | `O(log N)` | `O(log N)` |
| Range Query | `O(log N)` | `O(log N)` |
| 메모리 | `O(N)` | `O(N)` |
| Sum | ✅ | ✅ |
| Min / Max | 제한적 | ✅ |
| GCD | 제한적 | ✅ |
| XOR | ✅ | ✅ |
| 복합 연산 | 제한적 | ✅ |
| Lazy Propagation | ❌ | ✅ |

Fenwick Tree는 구조가 단순하고 메모리 효율이 좋기 때문에
**누적 합과 같이 비교적 단순한 연산**에서는 좋은 선택이다.

반면 Segment Tree는 각 구간에 원하는 정보를 저장할 수 있기 때문에
**다양한 형태의 Range Query**가 필요한 경우 더 적합하다.

---

# Segment Tree를 선택해야 하는 경우

다음과 같은 상황이라면 Segment Tree를 고려한다.

```text
배열 값 변경
+
특정 구간에 대한 질의
```

예를 들어

```text
arr[p] = value
```

와 동시에

```text
[min / max / sum / gcd / xor](l, r)
```

같은 연산을 반복적으로 수행해야 하는 경우이다.

---

# 주의사항

## 1. Query의 구간 정의

현재 구현은

```text
[left, right]
```

의 **닫힌 구간** 을 사용한다.

즉,

```java
query(2, 5)
```

는

```text
2, 3, 4, 5
```

를 포함한다.

---

## 2. 연산의 항등원(Identity Element)

Query의 초기값은 사용하는 연산에 따라 달라진다.

$$
0+a=a
$$

이므로 Sum과 XOR은 `0`을 사용할 수 있다.

GCD 역시

$$
\gcd(0,a)=a
$$

이므로 `0`을 사용할 수 있다.

반면 Min은 `Integer.MAX_VALUE`,
Max는 `Integer.MIN_VALUE`를 사용해야 한다.

---

## 3. Overflow

Sum Segment Tree에서 값의 합이 `int` 범위를 넘어갈 수 있다면

```java
long[] tree;
```

를 사용해야 한다.

---

# 전체 코드

```java
class SegmentTree {
    private int n;
    private int[] tree;
    public SegmentTree(int[] arr) {
        this.n = arr.length;
        this.tree = new int[n * 2];

        for (int i = 0; i < n; i++) {
            tree[n + i] = arr[i];
        }

        for (int i = n - 1; i > 0; i--) {
            tree[i] = tree[i << 1] + tree[i << 1 | 1];
        }
    }
    public void update(int p, int value) {
        for (tree[p += n] = value; p > 1; p >>= 1) {
            tree[p >> 1] = tree[p] + tree[p ^ 1];
        }
    }
    public int query(int left, int right) {
        int res = 0;

        for (left += n, right += n; left <= right; left >>= 1, right >>= 1) {
            if ((left & 1) == 1) res += tree[left++];
            if ((right & 1) == 0) res += tree[right--];
        }

        return res;
    }
}
```