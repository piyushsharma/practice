package com.concurrency;

/*

1. Java Memory Model - Happens-before edges: monitor enter/exit; volatile read-write; start / join; executor hand-off
Without an edge, you only have "as-if-serial" - so re-ordering breaks visibility
- To guarantee visibility to other threads - use volatile flag or lock acquisition that follows write

2. Locks: Intrinsic (synchronized) is re-entrant and fairish. ReentrantLock adds timed/try,
explicit unlock, fair option. StampedLock gives optimistic read and write stamps.
- Start safe with intrinsic, if contention shows - swap in ReentrantLock with tryLock back-off

3. Lock-free and CAS: Atomic* classes use VarHandle.compareAndSet ; problems: ABA
[ABA :: a thread came in between and did A -> B and B -> A; the current thread still reads A and
CAS will work -- this may or may not be ideal for programs] - solve with version stamp;
contention - use exponential back off;  Lock free cuts latency under low contention; but can starve threads;
always benchmark both paths

4. Coordination Primitives: One-shot - CountDownLatch (await zero); Cyclic - CyclicBarrier (resets);
Semaphore - permits (can model bounded buffer); Condition - wait/notify with Reentrant lock
If the count never resets; use CountDownLatch; otherwise CyclicBarrier

5. Liveness Hazards: Deadlock - circular wait :: avoid global lock ordering; LiveLock: threads keep retrying ::
add back-off;  Starvation - unfair lock or priority inversions
When introducing second lock, write acquisition order as a comment to ensure progress

6. Performance knobs: Reduce critical-section size -- Prefer immutable data for read paths;
Beware false sharing - pad hot atomics ; Use JMH to test, never System.nanoTime() loops.
First make it correct, then measure; optimization without profiling is guessing.

 */


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.Consumer;



/**
 * One-Shot Event System (register + fire)
 *
 * Thread-safe: callbacks registered before the first fire() run exactly once;
 * later registrations run immediately.  Ordered delivery preserved.
 */
public class EventOnce<T> {

    private final ReentrantLock lock = new ReentrantLock();
    private final List<Consumer<T>> pending = new ArrayList<>();

    // using volatile flag for read - mostly path; lock only on first race
    private volatile boolean fired = false;      // visibility flag
    private T payload;

    /** Register a callback; may execute immediately if already fired. */
    public void register(Consumer<T> cb) {
        if (fired) {              // fast path – no lock on steady state
            cb.accept(payload);
            return;
        }
        lock.lock();
        try {
            if (fired) {          // re-check after acquiring lock
                cb.accept(payload);
            } else {
                pending.add(cb);
            }
        } finally {
            lock.unlock();
        }
    }

    /* Fire once – idempotent. */
    /* Guarantee at-most-once callback execution; order preserved in list */
    public void fire(T data) {
        lock.lock();
        try {
            if (fired) return;     // ignore duplicate fire()
            fired = true;
            payload = data;        // safe before releasing lock
            pending.forEach(cb -> cb.accept(payload));
            pending.clear();       // help GC
        } finally {
            lock.unlock();
        }
    }





}
