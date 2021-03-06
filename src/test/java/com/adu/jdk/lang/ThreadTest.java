package com.adu.jdk.lang;

import com.adu.BaseTest;
import org.junit.Test;

public class ThreadTest extends BaseTest {

    @Test
    public void testInterrupt() throws InterruptedException {
        Thread thread = new Thread() {

            @Override
            public void run() {
                try {
                    Thread.currentThread().setName("test");
                    logger.info("thread-start");
                    for (int i = 0; i < 10; i++) {
                        for (int j = 0; j < 1000000000; j++) {
                            Math.sin(0.34334);
                        }
                        logger.info("i={},interrupted={}", i, Thread.currentThread().isInterrupted());
                    }
                    logger.info("thread-end,interrupted={}", Thread.currentThread().isInterrupted());
                } catch (Exception e) {
                    logger.info("Exception,interrupted={}", Thread.currentThread().isInterrupted(), e);
                } finally {
                    logger.info("finally~~,interrupted={}", Thread.currentThread().isInterrupted());
                }
            }
        };
        thread.start();
        Thread.sleep(1000);
        logger.info("start interrupt thread!");
        thread.interrupt();
        logger.info("end interrupt thread!");

        thread.join();
        logger.debug("end");

    }


    @Test
    public void priority() {
        int res = Thread.MIN_PRIORITY;
        logger.debug("res=" + res);

    }

    @Test
    public void sleep() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        logger.debug("end");
    }

    @Test
    public void yield() {
        Thread.yield();
        logger.debug("end");
    }

    @Test
    public void waitTest() {
        Thread thread = new Thread();
        try {
            thread.wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        logger.debug("end");
    }

    @Test
    public void joinTest() {
        Thread thread = new Thread();
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        logger.debug("end");
    }

    @Test
    public void test() throws InterruptedException {
        final Generator generator = new Generator();

        Thread thread1 = new Thread() {

            @Override
            public void run() {
                for (int i = 0; i < 1000; i++) {
                    System.out.println("[1-" + i + "]" + generator.getValue());
                }
            }
        };
        Thread thread2 = new Thread() {

            @Override
            public void run() {
                for (int i = 0; i < 1000; i++) {
                    System.out.println("[2-" + i + "]" + generator.getValue());
                }
            }
        };
        thread1.start();
        thread2.start();
        Thread.sleep(3000);
    }
}

class Generator {

    private int value = 1;

    public int getValue() {
        return value++;
    }
}
