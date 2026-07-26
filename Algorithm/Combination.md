# Combination (nCr) with Modular Inverse

## 개요

모듈러 환경에서 조합 **nCr**을 계산하는 방법

조합은 다음과 같이 정의된다.

$$
C(n, r)=\frac{n!}{r!(n-r)!}
$$

하지만 모듈러 연산에서는 일반적인 나눗셈을 사용할 수 없다.

따라서 **페르마의 소정리** 를 이용하여
분모의 **모듈러 역원** 을 계산한 뒤 곱셈으로 변환한다.

---

## 시간 복잡도

| Time | Space |
| :---: | :---: |
| `O(r + log MOD)` | `O(1)` |

---

## 핵심 아이디어

페르마의 소정리에 의해 (`MOD`는 소수)

$$
a^{MOD-1}\equiv1\pmod{MOD}
$$

이므로,

$$
a^{-1}\equiv a^{MOD-2}\pmod{MOD}
$$

가 성립한다.

따라서 조합은 다음과 같이 계산할 수 있다.

$$
C(n,r)=n!\times(r!(n-r)!)^{-1}\pmod{MOD}
$$

---

## 구현 방식

`(n-r)!`을 직접 계산하지 않고

$$
\text{num}=n(n-1)(n-2)\cdots(n-r+1)
$$

$$
\text{den}=r!
$$

을 계산한 뒤

$$
\text{Answer}=\text{num}\times\text{den}^{MOD-2}\pmod{MOD}
$$

를 이용하여 `nCr`을 구한다.

---

## 코드

```java
private long nCr(int n, int r) {
    if (r < 0 || r > n) return 0;
    if (r == 0 || r == n) return 1;

    long num = 1;
    long den = 1;

    for (int i = 0; i < r; i++) {
        num = (num * (n - i)) % MOD;
        den = (den * (i + 1)) % MOD;
    }

    return (num * pow(den, MOD - 2)) % MOD;
}
```

---

## 예시

`5C2`

### ① 분자 계산

$$
\text{num}=5\times4=20
$$

### ② 분모 계산

$$
\text{den}=2!=2
$$

### ③ 모듈러 역원 적용

$$
20\times2^{MOD-2}\pmod{MOD}=10
$$

따라서

$$
C(5,2)=10
$$

---

## 참고

- `pow()`는 **Binary Exponentiation**을 이용하여 `O(log MOD)`에 거듭제곱을 계산한다.
- `MOD`는 반드시 **소수** 여야 한다.