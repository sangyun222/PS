from collections import Counter


def solution(participant, completion):
    tmp = Counter(participant) - Counter(completion)

    return list(tmp.keys())[0]