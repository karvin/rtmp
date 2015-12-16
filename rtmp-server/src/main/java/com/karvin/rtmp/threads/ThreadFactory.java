package com.karvin.rtmp.threads;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Created by karvin on 15/12/16.
 */
public class ThreadFactory {

    private static ThreadFactory factory = new ThreadFactory();

    private int max = 200;

    private AtomicBoolean started=new AtomicBoolean(false);

    private RtmpThread[] busyThreads = new RtmpThread[max];

    private List<ThreadIndex> idleThreads = new ArrayList<ThreadIndex>();

    private List<RtmpWorker> waitWorkers = new ArrayList<RtmpWorker>();

    private Object workLock = new Object();

    private Object waitLock = new Object();

    private int busyCount = 0;

    private int current = 0;

    private ThreadFactory() {
        for(int i=0;i<max;i++){
            idleThreads.add(new ThreadIndex(i));
        }
    }

    public void setMax(int max) {
        this.max = max;
    }

    public static ThreadFactory getInstance() {
        return factory;
    }

    public void submit(RtmpWorker worker) {
        if (!started.get()) {
            throw new IllegalStateException("ThreadFactory has not been started or has stop, cant submit worker");
        }
        synchronized (workLock) {
            if (idleThreads.size() == 0) {
                synchronized (waitLock) {
                    waitWorkers.add(worker);
                    waitLock.notify();
                }
            } else {
                ThreadIndex threadIndex = idleThreads.remove(0);
                int index = threadIndex.index;
                System.out.println("use thread "+index);
                RtmpThread thread = busyThreads[index];
                if (thread == null) {
                    thread = new RtmpThread(worker,threadIndex);
                    busyThreads[index] = thread;
                    thread.start();
                } else {
                    thread.setWorker(worker);
                    thread.dispatch();
                }
                current++;
                busyCount++;
                workLock.notify();
            }
        }
    }

    public void start() {
        Thread thread = new Thread(new Runnable() {
            public void run() {
                while (started.get()) {
                    System.out.println("for new loop");
                    if (idleThreads.size() == 0) {
                        synchronized (workLock) {
                            try {
                                //worker is full wait
                                System.out.println("worker is full");
                                workLock.wait();
                            } catch (InterruptedException e) {
                                //full wait
                            }
                        }
                    } else if (idleThreads.size() <= max) {
                        if (!waitWorkers.isEmpty()) {
                            synchronized (waitLock) {
                                System.out.println("waiting one");
                                RtmpWorker worker = waitWorkers.remove(0);
                                ThreadIndex threadIndex = idleThreads.remove(0);
                                int index = threadIndex.index;
                                RtmpThread thread = busyThreads[index];
                                if (thread == null) {
                                    thread = new RtmpThread(worker,threadIndex);
                                    busyThreads[index] = thread;
                                    thread.start();
                                } else {
                                    thread.setWorker(worker);
                                    thread.dispatch();
                                }
                            }
                        } else {
                            synchronized (waitLock) {
                                try {
                                    //worker is empty wait
                                    System.out.println("worker is empty");
                                    waitLock.wait();
                                } catch (InterruptedException e) {
                                    //ignore
                                }
                            }
                        }
                    }
                }
            }
        });
        thread.start();
        started.set(true);
    }

    private class RtmpThread extends Thread{
        private RtmpWorker worker;
        private ThreadIndex threadIndex;
        private Object lock = new Object();

        public RtmpThread(RtmpWorker worker,ThreadIndex threadIndex){
            this.worker = worker;
            this.threadIndex = threadIndex;
        }

        public void setWorker(RtmpWorker worker){
            this.worker = worker;
        }

        @Override
        public void run(){
            while(true){
                if(worker != null) {
                    System.out.println("working thread:"+this.threadIndex.getIndex()+" worker:"+worker.hashCode());
                    worker.run();
                }
                synchronized (lock) {
                    try {
                        idleThreads.add(this.threadIndex);
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        public void dispatch(){
            synchronized (lock){
                idleThreads.remove(this.threadIndex);
                lock.notify();
            }
        }

    }

    private class ThreadIndex{
        private int index;

        public ThreadIndex(int index){
            this.index = index;
        }

        public int getIndex(){
            return index;
        }

    }

    public void stop() {
        if(started.getAndSet(false)) {
            for (Thread thread : busyThreads) {
                thread.stop();
            }
        }
    }

}
