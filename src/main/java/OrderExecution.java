import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

//HIGH priority orders first.
//Within the same priority, older orders (createdAt) first.
//Orders with the same createdAt should preserve their original input order.
//Do not modify the original input list.
//Return a new List<Order> containing the processed ordering.

public class OrderExecution {

    public List<Order> getRequiredOrders(List<Order> orders) {
//        List<Order> requiredOrders = new ArrayList<>();
//
//        List<Order> highFilteredOrders = orders.stream().filter(order -> order.priority.equals(Priority.HIGH)).toList();
//        List<Order> mediumFilteredOrders = orders.stream().filter(order -> order.priority.equals(Priority.MEDIUM)).toList();
//        List<Order> lowFilteredOrders = orders.stream().filter(order -> order.priority.equals(Priority.LOW)).toList();
//        highFilteredOrders = highFilteredOrders.stream().sorted(Comparator.comparing(order -> order.createdAt)).toList();
//        mediumFilteredOrders = mediumFilteredOrders.stream().sorted(Comparator.comparing(order -> order.createdAt)).toList();
//        lowFilteredOrders = lowFilteredOrders.stream().sorted(Comparator.comparing(order -> order.createdAt)).toList();
//        requiredOrders.addAll(highFilteredOrders);
//        requiredOrders.addAll(mediumFilteredOrders);
//        requiredOrders.addAll(lowFilteredOrders);
//        return requiredOrders;


        List<Order> requiredOrders = new ArrayList<>(orders);

        Comparator<Order> comparator =
                Comparator.comparingInt((Order order)  -> priorityRank(order.getPriority()))
                        .thenComparing(Order::getCreatedAt);

        requiredOrders.sort(comparator);

        return requiredOrders;
    }

    private int priorityRank(Priority priority) {
        return switch (priority) {
            case HIGH -> 1;
            case MEDIUM -> 2;
            case LOW -> 3;
        };
    }

    public List<Order> getCancelledOrders(List<Order> orders) {
        List<Order> cancelledOrders = new ArrayList<>(orders);
        cancelledOrders = orders.stream().filter(Order::getCancelled).toList();
        return cancelledOrders;
    }


}


class Order {
    private final String orderId;
    private final String customerId;
    private final BigDecimal amount;
    private final Instant createdAt;
    private final Priority priority;
    private final Boolean cancelled;
    Order(String orderId, String customerId, BigDecimal amount, Instant createdAt, Priority priority, Boolean cancelled) {
        this.orderId = orderId;
        this.customerId = customerId;
        this.amount = amount;
        this.createdAt = createdAt;
        this.priority = priority;
        this.cancelled = cancelled;
    }

    public String getOrderId() {
        return orderId;
    }
    public String getCustomerId() {
        return customerId;
    }
    public BigDecimal getAmount() {
        return amount;
    }
    public Instant getCreatedAt() {
        return createdAt;
    }
    public Priority getPriority() {
        return priority;
    }
    public Boolean getCancelled() {
        return cancelled;
    }
}

enum Priority {
    HIGH,
    MEDIUM,
    LOW
}
