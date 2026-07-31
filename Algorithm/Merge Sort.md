# Merge Sort

## 개요

**Merge Sort(병합 정렬)** 는 **분할 정복(Divide and Conquer)** 기법을 사용하는 정렬 알고리즘이다.

배열을 절반씩 나누어 각각 정렬한 뒤, 두 정렬된 배열을 다시 하나로 합치는 과정을 반복한다. (투 포인터)

---

## 시간 복잡도

| Case | Time |
| :--: | :--: |
| Best | `O(N log N)` |
| Average | `O(N log N)` |
| Worst | `O(N log N)` |

공간 복잡도는 임시 배열을 사용하므로

- **Space** : `O(N)`

---

## 핵심 아이디어

Merge Sort는 크게 두 단계로 이루어진다.

1. **Divide**
    - 배열을 절반으로 계속 분할한다.

2. **Merge**
    - 두 정렬된 배열을 하나의 정렬된 배열로 합친다.

```
            [5 2 4 1 3]

          /             \

      [5 2 4]         [1 3]

     /      \         /    \

   [5 2]   [4]      [1]    [3]

   /   \

 [5]   [2]

        ↓ Merge

[2 5] → [2 4 5]
[1 3]

        ↓ Merge

[1 2 3 4 5]
```

---

## 구현 과정

### 1. 배열 분할

현재 구간을 절반으로 나눈다.

```java
int mid = (left + right) / 2;
```

왼쪽과 오른쪽을 각각 재귀적으로 정렬한다.

```java
mergeSort(left, mid);
mergeSort(mid + 1, right);
```

---

### 2. 종료 조건

구간의 크기가 1이라면 이미 정렬된 상태이다.

```java
if (left == right) return;
```

---

### 3. 두 배열 병합

두 포인터를 이용하여 작은 값을 차례대로 선택한다.

```java
while (l <= mid && r <= right) {
    if (nums[l] <= nums[r]) {
        tmp[idx++] = nums[l++];
    }
    else {
        tmp[idx++] = nums[r++];
    }
}
```

항상 더 작은 값을 선택하므로 병합 결과 역시 정렬된다.

---

### 4. 남은 원소 처리

한쪽 배열을 모두 사용한 이후에는

다른 배열의 남은 원소를 그대로 복사한다.

```java
while (l <= mid)
    ...

while (r <= right)
    ...
```

---

### 5. 원본 배열에 반영

병합이 끝난 임시 배열을 원본 배열로 복사한다.

```java
System.arraycopy(...);
```

---

## 전체 코드
```java
int[] tmp;
public int[] sortArray(int[] nums) {
    merge_sort(nums);
    return nums;
}
private void merge_sort(int[] nums) {
    tmp = new int[nums.length];
    merge_sort(nums, 0, nums.length - 1);
}
private void merge_sort(int[] nums, int left, int right) {
    if (left == right) return;

    int mid = (left + right) / 2;
    merge_sort(nums, left, mid);
    merge_sort(nums, mid + 1, right);

    merge(nums, left, mid, right);
}
private void merge(int[] nums, int left, int mid, int right) {
    int l = left, r = mid + 1, idx = left;

    while (l <= mid && r <= right) {
        if (nums[l] <= nums[r]) tmp[idx++] = nums[l++];
        else tmp[idx++] = nums[r++];
    }

    while (r <= right) tmp[idx++] = nums[r++];
    while (l <= mid) tmp[idx++] = nums[l++];

    System.arraycopy(tmp, left, nums, left, right - left + 1);
}
```

---

## 특징

### Stable Sort

같은 값을 가진 원소의 상대적인 순서가 유지된다.

```text
3(a) 1 3(b)

↓

1 3(a) 3(b)
```

---

### Divide and Conquer

Merge Sort는

```
Divide
↓

Conquer

↓

Merge
```

과정을 반복하여 문제를 해결한다.

---

## 장단점

### 장점

- 항상 `O(N log N)`을 보장한다.
- Stable Sort이다.
- 데이터 분포에 영향을 받지 않는다.
- Linked List에서도 효율적으로 사용할 수 있다.

### 단점

- `O(N)`의 추가 메모리가 필요하다.
- Quick Sort보다 캐시 효율이 낮아 실제 실행 속도는 느린 경우가 많다.

---

## 다른 정렬과 비교

| Algorithm | Best | Average | Worst | Stable | Space |
|-----------|:----:|:-------:|:-----:|:------:|:-----:|
| Merge Sort | `O(N log N)` | `O(N log N)` | `O(N log N)` | ✅ | `O(N)` |
| Quick Sort | `O(N log N)` | `O(N log N)` | `O(N²)` | ❌ | `O(log N)` |
| Heap Sort | `O(N log N)` | `O(N log N)` | `O(N log N)` | ❌ | `O(1)` |

---

## 정리

- Merge Sort는 **분할 정복(Divide and Conquer)** 기반의 정렬 알고리즘이다.
- 배열을 절반씩 분할한 뒤, 정렬된 두 배열을 병합하여 전체를 정렬한다.
- 항상 `O(N log N)`의 시간 복잡도를 보장한다.
- Stable Sort이지만, `O(N)`의 추가 메모리가 필요하다.