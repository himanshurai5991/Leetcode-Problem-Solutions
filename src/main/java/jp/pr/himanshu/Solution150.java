package jp.pr.himanshu;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.function.UnaryOperator;

public class Solution150 {

    Map<String, BinaryOperator<Integer>> operators = Map.of(
            "+", (a,b) -> a + b,
            "-", (a, b) -> a - b,
            "*", (a, b) -> a * b,
            "/", (a, b) -> {
                if(b == 0) {
                    throw new ArithmeticException("divide by 0");
                }
                return a/b;
            }
    );

    Map<String, UnaryOperator<Integer>> unaryOperators =
            Map.of(
                    "NEG", value -> -value,
                    "ABS", Math::abs
            );

    private final ConcurrentMap<String, BinaryOperator<Integer>> concurrentOperator =
            new ConcurrentHashMap<>();


    public int evalRPN(String[] tokens) {

        Deque<Integer> stack = new ArrayDeque<>();
        for(String token: tokens) {
            if(operators.containsKey(token)) {
                if(stack.size() < 2) {
                    throw new IllegalArgumentException("Insufficient operands for operator: " + token);
                }
                int first = stack.pop();
                int second = stack.pop();
                BiFunction<Integer, Integer, Integer> biFunction = operators.get(token);
                int result = biFunction.apply(second, first);
                stack.push(result);
            } else  {
                try{
                    stack.push(Integer.parseInt(token));
                } catch (NumberFormatException e) {
                    throw new IllegalArgumentException("Unknown token: " + token);
                }
            }
        }

        if(stack.size() > 1){
            throw new IllegalArgumentException("Invalid RPN expression");
        }

        return stack.peek();
    }
}
