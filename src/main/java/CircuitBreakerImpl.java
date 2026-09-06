//Implement a circuit breaker with these requirements:
//
//        - Count failed requests over the last X minutes.
//        - If failures >= Y, open the circuit.
//        - While open, requests must fail immediately.
//        - After Z minutes, allow requests again.
//        - If the trial request succeeds, close the circuit.
//        - If it fails, open the circuit again.


import java.time.Clock;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.concurrent.atomic.AtomicReference;

enum STATE {
    OPEN,
    CLOSED,
    HALF_OPEN

}

public class CircuitBreakerImpl {

    private final Deque<Long> failureTimeStamp = new ArrayDeque<>();
    //private STATE state = STATE.CLOSED;
    private boolean trialInProgress;
    private final Clock clock;

    private final AtomicReference<STATE> state =
            new AtomicReference<>(STATE.CLOSED);


    private final long windowMillis;       // X minutes
    private final int failureThreshold;    // Y failures
    private final long cooldownMillis;  // Z minutes

    CircuitBreakerImpl(long windowMillis, int failureThreshold, long cooldownMillis, Clock clock) {
        this.windowMillis = windowMillis;
        this.failureThreshold = failureThreshold;
        this.cooldownMillis = cooldownMillis;
        this.clock = clock;
    }
    long openedAt;

    void removeOldFailures(long now) {
        while (!failureTimeStamp.isEmpty() && now - failureTimeStamp.peekFirst() >= windowMillis) {
            failureTimeStamp.pollFirst();
        }
    }

    synchronized void recordFailure() {
        long now = clock.millis();

        if(state.get() == STATE.HALF_OPEN) {
            state.set(STATE.OPEN);
            trialInProgress = false;
            openedAt = now;
            return;
        }

        removeOldFailures(now);
        failureTimeStamp.addLast(now);


        if (failureTimeStamp.size() >= failureThreshold) {
            state.set(STATE.OPEN);
            openedAt = now;
        }
    }

    synchronized void recordSuccess() {
        if (state.get() == STATE.HALF_OPEN) {
            state.set(STATE.CLOSED);
            failureTimeStamp.clear();
            trialInProgress = false;
        }

    }

    boolean allowRequest() {
        long now = clock.millis();

        if(state.get() == STATE.CLOSED) {
            removeOldFailures(now);
            return true;
        }


        if(state.get() == STATE.OPEN) {
            if (now - openedAt >= cooldownMillis) {
                state.compareAndSet(STATE.OPEN, STATE.HALF_OPEN);
                trialInProgress = true;
                return true;
            } else {
                return false;
            }
        }

        if(state.get() == STATE.HALF_OPEN) {
            if(trialInProgress) {
                return false;
            }
            trialInProgress = true;
            return true;
        }

        return true;
    }


}
