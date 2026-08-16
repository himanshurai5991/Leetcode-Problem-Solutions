import java.util.ArrayList;
import java.util.List;

public class StackDesign {

    List<Integer> al;
    int totalCapacity;

    StackDesign(int capacity) {
        al = new ArrayList<>();
        totalCapacity = capacity;
    }

    public void push(int val) throws Exception {
        if(al.size() == totalCapacity) {
            throw new Exception("Stack is full");
        }

        al.add(val);
    }

    public int pop() throws Exception {
        if(al.isEmpty()) {
            throw new Exception("Stack is empty");
        }
        return al.getLast();
    }

    public static void main(String[] args) {
        StackDesign stack = new StackDesign(3);
        try {
            stack.push(1);
            stack.push(2);
            stack.push(3);
            
            System.out.println(stack.pop());
            stack.push(5);
            System.out.println(stack.pop());

        } catch (Exception e) {
            System.out.println(e);
        }
    }


}
