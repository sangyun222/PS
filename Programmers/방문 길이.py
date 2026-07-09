def solution(dirs):
    x, y = 0, 0
    visited = set()
    count = 0

    for d in dirs:
        nx, ny = x, y
        if d == 'U' and y < 5:
            ny += 1
        elif d == 'D' and y > -5:
            ny -= 1
        elif d == 'R' and x < 5:
            nx += 1
        elif d == 'L' and x > -5:
            nx -= 1
        else:
            continue

        if ((x, y, nx, ny) not in visited) and ((nx, ny, x, y) not in visited):
            visited.add((x, y, nx, ny))
            count += 1

        x, y = nx, ny

    return count