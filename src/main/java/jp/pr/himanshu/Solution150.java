package jp.pr.himanshu;

import java.util.Stack;

public class Solution150 {

    public int evalRPN(String[] tokens) {
        Stack<Integer> stack = new Stack<>();
        for(String token: tokens){
            switch (token) {
                case "+" -> {
                    int second = stack.pop();
                    int first = stack.pop();
                    stack.push(first + second);
                }
                case "-" -> {
                    int second = stack.pop();
                    int first = stack.pop();
                    stack.push(first - second);
                }
                case "*" -> {
                    int second = stack.pop();
                    int first = stack.pop();
                    stack.push(first * second);
                }
                case "/" -> {
                    int second = stack.pop();
                    int first = stack.pop();
                    stack.push(first / second);
                }
                default -> stack.push(Integer.parseInt(token));
            }
        }
        return stack.peek();
    }
}
