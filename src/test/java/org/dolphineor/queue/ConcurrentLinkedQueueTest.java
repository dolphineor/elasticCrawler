package org.dolphineor.queue;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by baizz on 2015-1-17.
 */
public class ConcurrentLinkedQueueTest {
    private static int taskNum = 2;   // 任务数量
    private static CountDownLatch latch = new CountDownLatch(taskNum);
    private static ConcurrentLinkedQueue<Integer> queue = new ConcurrentLinkedQueue<>();

    public static void init() {
//        int count = 1_000_000;
        int count = 100;
        for (int i = 0; i < count; i++) {
            queue.offer(i);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ConcurrentLinkedQueueTest.init();
        ExecutorService service = Executors.newFixedThreadPool(4);
        long timeStart = System.currentTimeMillis();
        for (int i = 0; i < taskNum; i++) {
            service.submit(() -> {
                while (!queue.isEmpty()) {
                    queue.poll();
                }
                latch.countDown();
            });
        }
        latch.await();
        System.out.println("cost time: " + (System.currentTimeMillis() - timeStart) + "ms");
        service.shutdown();
    }

    public static ConcurrentLinkedQueue<Integer> getQueue() {
        return queue;
    }
}
