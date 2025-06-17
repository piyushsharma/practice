

- JVM

JVM is the implementation of a specification that can run code compiled into Java bytecode 
irrespective of the language in which the code was originally wriiten. 

JVM by definition is a VM or an abstract computer. Like a real machine - it has an instruction set,
a virtual computer architecture and an execution model. It is capable of running code written with this
virtual instruction set. 

JRE is the implementation of the JVM. It contains the JVM and java binaries and other classes to execute 
any program. 

JVM has 3 components:
1) Class Loader Subsystem
2) Runtime Data Area
3) Execution Engine - (contains interpretor, just-in-time compiler and the garbage collector)


- JIT Compiler

Java programs consist of classes, which contain platform-neutral bytecodes that can be interpreted by a JVM 
on many different computer architectures. The JIT compiler helps improve the performance of Java programs by 
compiling bytecodes into native machine code at run time.

- JIT is not actually part of the JVM standard, it is, nonetheless, an essential component of Java.



- Synchronized

Java’s fundamental construct for thread synchronization is the synchronized keyword. 
It can be used to restrict access to critical sections one thread at a time.

Each object in Java has an entity associated with it called the 
monitor lock or just monitor that acts as an exclusive lock. 
Once a thread gets hold of the monitor of an object, it has 
exclusive access to all the methods marked as synchronized. 
No other thread will be allowed to invoke a method on the object 
that is marked as synchronized and will block, 
till the first thread releases the monitor 
which is equivalent of the first thread exiting the synchronized method.

Note carefully:

1. For static methods, the monitor will be the class object, which is distinct from the monitor of each instance of the same class.
2. If an uncaught exception occurs in a synchronized method, the monitor is still released.
3. Furthermore, synchronized blocks can be re-entered that is they are reentrant.

