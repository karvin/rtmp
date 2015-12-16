package com.karvin.rtmp.threads;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by karvin on 15/12/16.
 */
public class ThreadFactory {

    private static ThreadFactory factory = new ThreadFactory();

    private int max = 200;

    private boolean started;

    private List<Thread> threads = new ArrayList<Thread>();

    private List<RtmpWorker> rtmpWorkers = new ArrayList<RtmpWorker>();

    private List<RtmpWorker> waitWorkers = new ArrayList<RtmpWorker>();

    private Object workLock = new Object();

    private Object waitLock = new Object();

    private int busyCount = 0;

    private ThreadFactory(){

    }

    public void setMax(int max){
        this.max = max;
    }

    public static ThreadFactory getInstance(){
        return factory;
    }

    public void submit(RtmpWorker worker){
        if(!started){
            throw new IllegalStateException("ThreadFactory has not been started or has stop, cant submit worker");
        }
        synchronized (workLock){
            if(rtmpWorkers.size() == max){
                synchronized (waitLock) {
                    rtmpWorkers.add(worker);
                    busyCount++;
                    notify();
                }
            }else{
                rtmpWorkers.add(worker);
            }
        }
    }

    public void start(){
        Thread thread = new Thread(new Runnable() {
            public void run() {
                while(true){
                    if(busyCount == max){
                        try {
                            //worker is full wait
                            wait();
                        } catch (InterruptedException e) {
                            //full wait
                        }
                    }else if(busyCount < max){
                        if(!waitWorkers.isEmpty()) {
                            RtmpWorker worker = waitWorkers.remove(0);
                            Thread thread = new Thread(worker);
                            threads.add(thread);
                        } else{
                            try {
                                //worker is empty wait
                                wait();
                            } catch (InterruptedException e) {
                                //ignore
                            }
                        }
                    }
                }
            }
        });
        thread.start();
    }

    public void stop(){
        for(Thread thread:threads){
            thread.stop();
        }
    }

}
