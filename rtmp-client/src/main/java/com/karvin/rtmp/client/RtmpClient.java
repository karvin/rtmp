package com.karvin.rtmp.client;

import com.karvin.rtmp.common.io.RtmpConnection;
import com.karvin.rtmp.common.io.impl.RtmpConnectionImpl;

import java.io.IOException;

/**
 * Created by karvin on 15/12/16.
 */
public class RtmpClient {

    private RtmpConnection connection;

    private String host;
    private int port;

    public RtmpClient(String host,int port) throws IOException {
        this.host = host;
        this.port = port;
    }

    public void connect() throws IOException{
        this.connection = new RtmpConnectionImpl(this.host,this.port);
        this.connection.connect();
    }

    public void close() throws IOException{
        this.connection.close();
    }

    public static void main(String[] args){
        try {
            for(int i=0;i<500;i++) {
                RtmpClient rtmpClient = new RtmpClient("127.0.0.1", 1935);
                rtmpClient.connect();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
