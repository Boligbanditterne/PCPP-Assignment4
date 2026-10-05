## 7.1

### 7.1.1

Is this execution sequentially consistent? If so, provide a sequential execution that satisfies the standard
specification of a sequential FIFO queue. Otherwise, explain why it is not sequentially consistent.

A: ---------------|q.enq(x)|--|q.enq(y)|->

B: ---|q.deq(x)|------------------------->

### 7.1.2

Is this execution (same as above) linearizable? If so, provide a linearization that satisfies the standard
specification of a sequential FIFO queue. Otherwise, explain why it is not linearizable.

A: ---------------|q.enq(x)|--|q.enq(y)|->

B: ---|q.deq(x)|------------------------->

### 7.1.3

Is this execution linearizable? If so, provide a linearization that satisfies the standard specification of a
sequential FIFO queue. Otherwise, explain why it is not linearizable.

A: ---| q.enq(x) |-->

B: ------|q.deq(x)|--------------->

### 7.1.4

Is this execution linearizable? If so, provide a linearization that satisfies the standard specification of a
sequential FIFO queue. Otherwise, explain why it is not linearizable.

A: ---|q.enq(x)|-----|q.enq(y)|-->

B: --| q.deq(y) |->