package jp.pr.himanshu;


import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Map;
import java.util.Stack;
import java.util.function.BiFunction;

// 3 4 − 5 + = ((3-4) + 5) = (-1+5) = 4
// 3 4 + = (3+ -1) = 2
//
// O(n)
// O(n)
// + ,3,4 -  invalid
// "34−5+"
// 10000*1000
// 100/33 = 3 , 3.33 ( round half up)
// 2.33 + 20/3 = 6.67  = 9.0
// 100/0
// 20/3 = 6.666666666
//
// [0,100 /]  100/0
// +,-,/,* %
// Test cases
public class ReversePolishNotation {

    private Map<String, BiFunction<BigDecimal,BigDecimal,BigDecimal>> map = Map.of(
       "+", (a,b) -> (a.add(b).setScale(2, RoundingMode.HALF_UP)),
          "-" , (a,b) -> (a.subtract(b).setScale(2, RoundingMode.HALF_UP)),
            "*" , (a,b) -> (a.multiply(b).setScale(2, RoundingMode.HALF_UP)),
            "/" , (a,b) -> {
           if (b.equals(BigDecimal.ZERO)) {
               throw new IllegalArgumentException("Dividend cannot be zero");
           }
           return a.divide(b).setScale(2, RoundingMode.HALF_UP);
       }

    ) ;

    public String calculate(String tokens) {
        // 3 4 − 5 +

        Stack<BigDecimal>  stack = new Stack<>();

        for(int i= 0; i< tokens.length();i++) {
            if(tokens.charAt(i) == ' '){
                continue;
            }
            BiFunction<BigDecimal, BigDecimal, BigDecimal>  operations = map.get(String.valueOf(tokens.charAt(i)));
            if(operations == null) {
                try {
                    stack.push(BigDecimal.valueOf(tokens.charAt(i) - '0'));
                } catch (Exception e) {
                    // [89+7$]
                    throw new IllegalArgumentException("The current char is no a number " + tokens.charAt(i));
                }
            } else {
                checkIsEmpty(stack);
                BigDecimal first = stack.pop(); // [1+]
                checkIsEmpty(stack);
                BigDecimal second = stack.pop();
                stack.push(operations.apply(first, second));
            }
        }

        if(stack.size() != 1) {
            throw new IllegalArgumentException("Invalid notation"); // [12+ 3] [3,3]
        }
        return stack.pop().toString();
    }

    public boolean checkIsEmpty(Stack<BigDecimal> stack) {
        if(stack.isEmpty()) {
            throw new IllegalArgumentException("input invalid");
        }
        return true;
    }

    // fun (input1, input2, operator) : Result
    // BiFunction (input1, input2, output) ->  a,b -> (a+b)


}
