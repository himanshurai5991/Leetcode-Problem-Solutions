//Implement a circuit breaker with these requirements:
//
//        - Count failed requests over the last X minutes.
//        - If failures >= Y, open the circuit.
//        - While open, requests must fail immediately.
//        - After Z minutes, allow requests again.
//        - If the trial request succeeds, close the circuit.
//        - If it fails, open the circuit again.


import java.util.ArrayDeque;
import java.util.Deque;

enum STATE {
    OPEN,
    CLOSED,
    HALF_OPEN,
    HALF_CLOSED

}

public class CircuitBreakerImpl {

    private final Deque<Long> failureTimeStamp = new ArrayDeque<>();
    STATE state = STATE.CLOSED;


    long windowMillis;       // X minutes
    int failureThreshold;    // Y failures
    long cooldownMillis;     // Z minutes

    long openedAt;

    void removeOldFailures(long now) {
        while (!failureTimeStamp.isEmpty() && now - failureTimeStamp.peekFirst() >= windowMillis) {
            failureTimeStamp.pollFirst();
        }
    }

    synchronized void recordFailure() {
        long now = System.currentTimeMillis();
        removeOldFailures(now);
        failureTimeStamp.addLast(now);


        if (failureTimeStamp.size() >= failureThreshold) {
            state = STATE.OPEN;
            openedAt = now;
        }
    }

    boolean isOpen() {
        return state == STATE.OPEN;
    }
    boolean isClosed() {
        return state == STATE.CLOSED;
    }

    synchronized boolean allowRequest() {
        long now = System.currentTimeMillis();

        if(state == STATE.CLOSED) {
            removeOldFailures(now);
            return true;
        }


        if(state == STATE.OPEN) {
            if (now - openedAt >= cooldownMillis) {
                state = STATE.HALF_OPEN;
                return true;
            } else {
                return false;
            }
        }
        return true;
    }


}
