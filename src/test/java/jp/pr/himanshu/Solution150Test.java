package jp.pr.himanshu;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class Solution150Test {

    private Solution150 solution150 = new Solution150();

    @Test
    public void test() {

        String[] tokens = {"2", "1", "*"};
        int result = solution150.evalRPN(tokens);
        assertEquals(2, result);
    }

    @Test
    public void test2() {
        String[] tokens = {"4", "13", "5", "/", "+"};
        int result = solution150.evalRPN(tokens);
        assertEquals(6, result);
    }


    @Test
    public void test3() {
        String[] tokens = {"4", "13", "5", "+"};
        assertThrows(
                IllegalArgumentException.class,
                () -> solution150.evalRPN(tokens)
        );
    }

    @Test
    public void test4() {
        String[]  tokens  = {"+" , "4", "3", "*"};
        assertThrowsExactly(
                IllegalArgumentException.class , () -> solution150.evalRPN(tokens)
        );
        String[] tokens1  = new String[]{"4", "3", "*"};
        assertDoesNotThrow(
                () -> solution150.evalRPN(tokens1));
    }

    @Test
    public void test5() {
        String[]  tokens  = {"+" , "4", "3", "*"};
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class , () -> solution150.evalRPN(tokens));
        assertEquals("Insufficient operands for operator", exception.getMessage());
        String[] tokens1  = new String[]{"4", "3", "*"};
        assertDoesNotThrow(
                () -> solution150.evalRPN(tokens1));
    }


}
