class Solution:
    def evalRPN(self, tokens: List[str]) -> int:
        def cal(c):
            n1 = nums.pop()
            n2 = nums.pop()

            if c == '+': return n2 + n1
            elif c == '-': return n2 - n1
            elif c == "*": return n2 * n1
            elif c == '/': return int(n2 / n1)

        nums = []
        for c in tokens:
            if c in "+-*/": nums.append(cal(c))
            else: nums.append(int(c))

        return nums[0]