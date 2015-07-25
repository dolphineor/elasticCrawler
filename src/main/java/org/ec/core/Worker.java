package org.ec.core;

import org.ec.extractor.Page;
import org.ec.scheduler.Task;
import org.ec.scheduler.TaskQueue;

import java.io.IOException;

/**
 * Created by dolphineor on 2015-1-17.
 * <p>
 * worker负责爬取
 */
public class Worker implements Runnable {

    private final TaskQueue taskQueue;


    public Worker(TaskQueue taskQueue) {
        this.taskQueue = taskQueue;
    }


    @Override
    public void run() {
        for (; ; ) {
            Task task = taskQueue.take();
            if (task == null) {
                break;
            }
            execute(task);
        }
    }

    protected void execute(Task task) {
        try {
            Page page = new Page(task.getDownloader().download(task));
            task.getExtractor().extract(page);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}