from collections import Counter


def solution(str1, str2):
    arr1 = []
    arr2 = []

    for i in range(len(str1) - 1):
        if str1[i].isalpha() and str1[i + 1].isalpha():
            arr1.append(str1[i].lower() + str1[i + 1].lower())

    for i in range(len(str2) - 1):
        if str2[i].isalpha() and str2[i + 1].isalpha():
            arr2.append(str2[i].lower() + str2[i + 1].lower())

    c1 = Counter(arr1)
    c2 = Counter(arr2)

    inter = c1 & c2
    union = c1 | c2

    inter_count = sum(inter.values())
    union_count = sum(union.values())

    if union_count == 0:
        result = 1
    else:
        result = inter_count / union_count

    result = int(result * 65536)

    return result