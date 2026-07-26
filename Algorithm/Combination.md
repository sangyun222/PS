# Combination (nCr) with Modular Inverse

## 개요

모듈러 환경에서 조합 `nCr`을 계산하는 방법

일반적인 조합의 정의는 다음과 같다.

$$
\binom{n}{r}
=
\frac{n!}{r!(n-r)!}
$$

하지만 모듈러 연산에서는 나눗셈을 직접 수행할 수 없다.

따라서 **페르마의 소정리** 를 이용하여
분모의 **모듈러 역원** 을 구한 뒤 곱셈으로 계산한다.

---

## 시간 복잡도

- **Time** : `O(r + log MOD)`
- **Space** : `O(1)`

---

## 핵심 아이디어

모듈러 연산에서

$$
a^{-1}
\equiv
a^{MOD-2}
\pmod{MOD}
$$

이 성립한다. (`MOD`는 소수)

따라서 조합은 다음과 같이 계산할 수 있다.

$$
\binom{n}{r}
=
\frac{n!}{r!(n-r)!}
\equiv
n!
\times
(r!(n-r)!)^{-1}
\pmod{MOD}
$$

여기서 `(n-r)!`을 따로 계산하지 않고,

분자를

$$
n(n-1)(n-2)\cdots(n-r+1)
$$

형태로 계산하여

$$
\text{num}
=
n(n-1)\cdots(n-r+1)
$$

$$
\text{den}
=
r!
$$

으로 만든 뒤

$$
\boxed{
\binom{n}{r}
=
\text{num}
\times
\text{den}^{MOD-2}
\pmod{MOD}
}
$$

를 이용해 값을 구한다.

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

`5C2`를 계산하면

분자

$$
5 \times 4 = 20
$$

분모

$$
2! = 2
$$

따라서

$$
20
\times
2^{MOD-2}
\pmod{MOD}
=
10
$$

이 되어 최종 결과는

$$
\binom{5}{2}=10
$$

이다.

---

## 참고

- `pow()`는 **Binary Exponentiation** 을 이용하여 `O(log MOD)`에 거듭제곱을 계산한다.