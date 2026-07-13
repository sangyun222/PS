def solution(name:str):
    res = 0
    cur = list('A' * len(name))
    idx = 0

    while True:
        if cur[idx] != name[idx]:
            res += min(ord(name[idx]) - ord('A'), 1 + ord('Z') - ord(name[idx]))
            cur[idx] = name[idx]

        if cur == list(name): break

        tmp = 0
        while True:
            tmp += 1

            if cur[(idx + tmp) % len(name)] != name[(idx + tmp) % len(name)]:
                idx = (idx + tmp) % len(name)
                res += tmp
                break
            elif cur[(idx - tmp) % len(name)] != name[(idx - tmp) % len(name)]:
                idx = (idx - tmp) % len(name)
                res += tmp
                break



    return res