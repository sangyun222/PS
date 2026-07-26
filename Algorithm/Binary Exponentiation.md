# Binary Exponentiation

## 개요
거듭제곱을 **O(log N)** 에 계산하는 알고리즘

일반적으로 `base^exp`를 반복문으로 계산하면 `O(N)`이 걸리지만,
지수를 절반씩 줄여가며 계산

또한 모듈러 연산과 함께 사용하면 큰 수의 거듭제곱도 안전하게 계산 가능

## 시간 복잡도
- **Time** : `O(log exp)`
- **Space** : `O(1)`

## 코드 (Java)

```java
private long pow(long base, long exp) {
    long res = 1;
    base %= MOD;

    while (exp > 0) {
        if (exp % 2 == 1) res = (res * base) % MOD;
        base = (base * base) % MOD;
        exp /= 2;
    }

    return res;
}
```

## 동작 원리

예를 들어 `3^13`을 계산한다고 하면

```
13 = 1101₂
```

반복 과정은 다음과 같다.

| exp | res | base |
|-----:|----:|-----:|
| 13 (홀수) | 3 | 3 |
| 6 | 3 | 9 |
| 3 (홀수) | 27 | 81 |
| 1 (홀수) | 1594323 | 43046721 |

지수를 절반씩 줄여가므로 반복 횟수는 `log₂(exp)`번만 수행된다.

## 핵심 아이디어

- `exp`가 홀수라면 현재 `base`를 결과에 곱한다.
- 매 반복마다 `base = base²`
- `exp /= 2`로 지수를 절반으로 줄인다.
- 모듈러 연산을 함께 사용하면 오버플로우를 방지하면서 큰 거듭제곱을 계산할 수 있다.