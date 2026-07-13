def solution(name:str):
    l = len(name)

    res = 0
    for c in name:
        res += min(ord(c) - ord('A'), 1 + ord('Z') - ord(c))

    tmp = l - 1
    for i in range(l):
        idx = i + 1

        while idx < l and name[idx] == 'A': idx += 1

        tmp = min(tmp, i * 2 + (l - idx))
        tmp = min(tmp, (l - idx) * 2 + i)

    return res + tmp