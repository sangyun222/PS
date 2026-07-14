class Solution:
    def exclusiveTime(self, n: int, logs: List[str]) -> List[int]:
        res = [0] * n
        stack = []
        prev = 0
        for log in logs:
            cid, cmd, time = log.split(":")
            cid, time = map(int, (cid, time))

            if not stack:
                stack.append(cid)
                prev = time
            elif cmd == "start":
                res[stack[-1]] += time - prev
                stack.append(cid)
                prev = time
            else:
                sc = stack.pop()
                res[sc] += time - prev + 1
                prev = time + 1

        return res