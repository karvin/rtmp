package com.karvin.rtmp.threads;

import com.karvin.rtmp.common.io.RtmpConnection;

/**
 * Created by karvin on 15/12/16.
 */
public class RtmpWorker implements Runnable {

    private RtmpConnection rtmpConnection;


    public RtmpWorker(RtmpConnection rtmpConnection) {
        this.rtmpConnection = rtmpConnection;
    }

    public void run() {
        //System.out.println("running:"+this.rtmpConnection.hashCode());
    }
}
