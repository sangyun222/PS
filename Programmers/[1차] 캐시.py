def solution(cacheSize, cities):
    time = 0
    cache = []

    if cacheSize == 0:
        return 5 * len(cities)

    for city in cities:
        city = city.lower()

        if city in cache:
            cache.remove(city)
            cache.append(city)
            time += 1
        else:
            time += 5
            if len(cache) == cacheSize:
                cache.pop(0)
            cache.append(city)

    return time
