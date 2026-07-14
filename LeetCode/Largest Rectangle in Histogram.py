class Solution:
    def largestRectangleArea(self, heights: List[int]) -> int:
        stack = []
        res = 0

        for i in range(len(heights)):
            tmp = i
            while stack and stack[-1][1] > heights[i]:
                idx, h = stack.pop()
                tmp = idx
                res = max(res, (i - idx) * h)

            stack.append((tmp, heights[i]))

        while stack:
            idx, h = stack.pop()
            res = max(res, (len(heights) - idx) * h)

        return res