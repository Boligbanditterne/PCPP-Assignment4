// raup@itu.dk * 2023-10-20
package exercises07;

// Very likely you will need some imports here

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.atomic.AtomicInteger;
import org.junit.jupiter.api.BeforeEach;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CyclicBarrier;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.RepeatedTest;

class TestLockFreeStack {
    LockFreeStack<Integer> stack;

    // The imports above are just for convenience, feel free add or remove imports
    @BeforeEach
    public void initialize()
    {
        stack = new LockFreeStack<>();
    }

    @RepeatedTest(50)
    public void pushTest(){
        List<Thread> threads = new ArrayList<>();
        CyclicBarrier barrier = new CyclicBarrier(100 + 1);
        int expectedAmount = 0;
        int actualAmount = 0;

        for(int i = 0; i < 100; i++){
            int n = i;
            expectedAmount += n;
            Thread t = new Thread(() -> {
                try {
                    barrier.await();
                    stack.push(n);


                } catch (Exception e) {
                }
            });
            threads.add(t);
            t.start();
        }

        try { // barrier
            barrier.await();
        } catch (Exception e) {
        }

        for (Thread t : threads) { // joins
            try {
                t.join();
            } catch (Exception e) {
                System.err.println(e);
            }
            
        }
        int size = 0;
        while (true) { 
            var value = stack.pop();
            if (value != null) {
                size++;
                actualAmount += value;

            }
            else break;
        }
        assertEquals(expectedAmount, actualAmount);

        assertEquals(100, size);
        
    }

    @RepeatedTest(50)
    public void popTest(){
        List<Thread> threads = new ArrayList<>();
        CyclicBarrier barrier = new CyclicBarrier(100 + 1);
        AtomicInteger actualAmount = new AtomicInteger(0);

        
        int expectedAmount = 0;
        for(int i = 0; i<100;i++){
            stack.push(i);
            expectedAmount+=i;
        }
        
        for(int i = 0; i < 100; i++){
            int n = i;
            Thread t = new Thread(() -> {
                try {
                    barrier.await();
                    actualAmount.addAndGet(stack.pop());


                } catch (Exception e) {
                }
            });
            threads.add(t);
            t.start();
        }

        try { // barrier
            barrier.await();
        } catch (Exception e) {
        }

        for (Thread t : threads) { // joins
            try {
                t.join();
            } catch (Exception e) {
                System.err.println(e);
            }
            
        }
        
        int size = 0; // check size
        while (true) { 
            if (stack.pop() != null) size++;
            else break;
        }
        assertEquals(expectedAmount, actualAmount.get());
        assertEquals(0, size);
        
    }

    @RepeatedTest(50)
    public void popEmptyTest(){
        List<Thread> threads = new ArrayList<>();
        CyclicBarrier barrier = new CyclicBarrier(100 + 1);
        AtomicInteger nulls = new AtomicInteger(0);

        
        for(int i = 0; i<10;i++){
            stack.push(i);
        }
        
        for(int i = 0; i < 100; i++){
            Thread t = new Thread(() -> {
                try {
                    barrier.await();
                    if (stack.pop() == null){
                        nulls.addAndGet(1);
                    }


                } catch (Exception e) {
                }
            });
            threads.add(t);
            t.start();
        }

        try { // barrier
            barrier.await();
        } catch (Exception e) {
        }

        for (Thread t : threads) { // joins
            try {
                t.join();
            } catch (Exception e) {
                System.err.println(e);
            }
            
        }
        
        int size = 0; // check size
        while (true) { 
            if (stack.pop() != null) size++;
            else break;
        }
        assertEquals(90, nulls.get());
        assertEquals(0, size);
        
    }

}
