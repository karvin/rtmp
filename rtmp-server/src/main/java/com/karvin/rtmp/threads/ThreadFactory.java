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

    private List<Integer> idleThreads = new ArrayList<Integer>();

    private Object[] threadLocks = new Object[max];

    private List<RtmpWorker> waitWorkers = new ArrayList<RtmpWorker>();

    private Object workLock = new Object();

    private Object waitLock = new Object();

    private int busyCount = 0;

    private int current = 0;

    private ThreadFactory() {
        for(int i=0;i<max;i++){
            idleThreads.add(i);
            threadLocks[i] = new Object();
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
                int index = idleThreads.remove(0);
                synchronized (threadLocks[index]) {
                    RtmpThread thread = busyThreads[index];
                    if (thread == null) {
                        thread = new RtmpThread(worker, index);
                        busyThreads[index] = thread;
                    } else {
                        thread.setWorker(worker);
                    }
                    threadLocks[index].notify();
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
                                int index = idleThreads.remove(0);
                                synchronized (threadLocks[index]) {
                                    RtmpThread thread = busyThreads[index];
                                    if (thread == null) {
                                        thread = new RtmpThread(worker, index);
                                        busyThreads[index] = thread;
                                        thread.start();
                                    } else {
                                        thread.setWorker(worker);
                                        threadLocks[index].notify();
                                    }
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

        private int index;

        public RtmpThread(RtmpWorker worker,int index){
            this.worker = worker;
            this.index = index;
        }

        public RtmpThread(int index){
            this(null,index);
        }

        public void setWorker(RtmpWorker worker){
            this.worker = worker;
        }

        @Override
        public void start(){
            while(true){
                if(worker != null) {
                    worker.run();
                }
                synchronized (threadLocks[index]) {
                    try {
                        idleThreads.add(index);
                        threadLocks[index].wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
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
