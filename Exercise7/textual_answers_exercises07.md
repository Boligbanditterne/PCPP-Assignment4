## 7.1

### 7.1.1

Is this execution sequentially consistent? If so, provide a sequential execution that satisfies the standard
specification of a sequential FIFO queue. Otherwise, explain why it is not sequentially consistent.

A: ---------------|q.enq(x)|--|q.enq(y)|->

B: ---|q.deq(x)|------------------------->

<q.enq(x),q.deq(x),q.enq(y)> is a valid sequantial order. That satisfies the properties of a FIFO queue.
### 7.1.2

Is this execution (same as above) linearizable? If so, provide a linearization that satisfies the standard
specification of a sequential FIFO queue. Otherwise, explain why it is not linearizable.

A: ---------------|q.enq(x)|--|q.enq(y)|->

B: ---|q.deq(x)|------------------------->

It does not satisfy linearization because each action should happen at one "atomic" point. q.deq(x) appears to take effect before q.enq(x) and does therefor not satisfy linearization.

### 7.1.3

Is this execution linearizable? If so, provide a linearization that satisfies the standard specification of a
sequential FIFO queue. Otherwise, explain why it is not linearizable.

A: ---|      q.enq(x)          |-->

B: ------|q.deq(x)|--------------->

<q.enq(x),q.deq(x)> is a valid order, since q.enq(x) can atomically happen before the q.deq(x).  

### 7.1.4

Is this execution linearizable? If so, provide a linearization that satisfies the standard specification of a
sequential FIFO queue. Otherwise, explain why it is not linearizable.

A: ---|q.enq(x)|-----|q.enq(y)|-->

B: --|   q.deq(y)              |->

It is not possible to q.deq(y) here since the q.enq(x) must happen before q.enq(y) and it would therefore violate the properties of a FIFO queue.

## 7.2

### 7.2.1

***See LockFreeStack.java***

For push the only linearization point is E5. 

**Correctness**
If two threads execute push concurrently, then only one of them succeeds in executing. The other fails and repeats the push.

For pop can conditionally end after P3. Furthermore P6 is also a linearization point.

**Correctness**
If another threads pops concurrently, then one of the threads might discover that the head is now null and terminate. Otherwise they will continuously try to perform the pop. 

### 7.2.2

***TestLockFreeStack.java***

### 7.2.3

***TestLockFreeStack.java***

### 7.2.4

No the tests do not really cover the case where it tries to pop from an emptylist - where head is null.

***TestLockFreeStack.java***

## 7.3

### 7.3.1

All writer functions are wait-free. They do not loop but just complete without waiting.

Reader functions include a Do-while loop. This means that some thread will complete, however it is not garuanteed to be the one calling the function, it is only garuanteed that some thread will finish in a bounded number of steps. 