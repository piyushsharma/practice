# Concurrent System

- System running several distinct programs or more than one independent unit of the same program in 
overlapping time intervals is called a concurrent system. 
Two programs progress at the same time - doesn't imply execution.
Eg. Single core OS - doing I/O in one thread and responding to user clicks in another.

- Parallelism : Ability to execute multiple programs at the same time. Multi core processors / computing clusters.

- If a thread attempts to unlock a mutex that it has not locked or a mutex which is unlocked - undefined behavior 
results; Semaphore - there's no notion of ownership.

- Monitor, mutex, and semaphores can be confusing concepts initially. A monitor is made up of a mutex 
and a condition variable. One can think of a mutex as a subset of a monitor.

- ReentrantLock is similar to implicit monitor lock when using synchronized methods or blocks; you are free
to lock and unlock it in different methods but not with different threads. Similar to mutex with additional bells
and whistles. 
