// For week 7
// raup@itu.dk * 2023-10-20
package exercises07;

import java.util.concurrent.atomic.AtomicReference;

// Treiber's LockFree Stack (Goetz 15.4)
class LockFreeStack<T> {
    AtomicReference<Node<T>> top = new AtomicReference<Node<T>>(); // Initializes to null

    public void push(T value) {
        Node<T> newHead = new Node<T>(value);           //E1
        Node<T> oldHead;                                //E2
        do {
            oldHead      = top.get();                   //E3
            newHead.next = oldHead;                     //E4
        } while (!top.compareAndSet(oldHead,newHead));  //E5
    }

    public T pop() {
        Node<T> newHead;                                //P1
        Node<T> oldHead;                                //P2
        do {
            oldHead = top.get();                        //P3
            if(oldHead == null) { return null; }        //P4
            newHead = oldHead.next;                     //P5
        } while (!top.compareAndSet(oldHead,newHead));  //P6

        return oldHead.value;                           //P7
    }

    // class for nodes
    private static class Node<T> {
        public final T value;
        public Node<T> next;

        public Node(T value) {
            this.value = value;
            this.next  = null;
        }
    }
}
