package lesson_3;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConcurrentCounter {

    private static final int ITERATIONS = 100;

    public static void main(String[] args) throws InterruptedException {
        ConcurrentCounter myCounter = new ConcurrentCounter();
        myCounter.start();
    }

    private void start() throws InterruptedException {
        Lock lock = new ReentrantLock();
        Counter counter1 = new Counter(lock);
        Counter counter2 = new Counter(lock);
        Counter counter3 = new Counter(lock);

        Thread thread1 = new Thread(new ThreadCounter(counter1, counter3, ITERATIONS));
        Thread thread2 = new Thread(new ThreadCounter(counter2, counter3, ITERATIONS));
        Thread thread3= new Thread(new ThreadCounter(counter3, counter3, ITERATIONS));

        thread1.start();
        thread2.start();
        thread3.start();

        thread1.join();
        thread2.join();
        thread3.join();

        System.out.println("counter 1: " + counter1.getValue());
        System.out.println("counter 2: " + counter2.getValue());
        System.out.println("counter 3: " + counter3.getValue());

    }

    private class ThreadCounter implements Runnable{

        private final Counter counter1;
        private final Counter counter2;
        private final int iterations;

        public ThreadCounter(Counter counter1, Counter counter2, int iterations) {
            this.counter1 = counter1;
            this.counter2 = counter2;
            this.iterations = iterations;
        }

        public void run() {
            for (int i = 0; i < iterations; i++) {
                counter1.incrementAndGet();
                counter2.incrementAndGet();
            }
        }
    }

    private class Counter {
        private long value;
        private Lock lock;

        public Counter(Lock lock) {
            this.lock = lock;
        }

        public long getValue() {
            return value;
        }

        public long incrementAndGet() {
            return incrementAndGet(1L);
        }

        public long incrementAndGet(long increment) {
            try {
                lock.lock();
                this.value += increment;
            } finally {
                lock.unlock();
            }
            return value;
        }
    }
}
