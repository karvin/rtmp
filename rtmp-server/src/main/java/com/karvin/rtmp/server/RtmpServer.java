package com.karvin.rtmp.server;

import com.karvin.rtmp.common.io.CloseHandler;
import com.karvin.rtmp.common.io.RtmpConnection;
import com.karvin.rtmp.common.io.impl.RtmpConnectionImpl;
import com.karvin.rtmp.threads.RtmpWorker;
import com.karvin.rtmp.threads.ThreadFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by karvin on 15/12/16.
 */
public class RtmpServer {

    private static final Logger logger = LoggerFactory.getLogger(RtmpServer.class);

    private int port = 1935;

    private ServerSocket socket;

    private List<RtmpConnection> connections = new ArrayList<RtmpConnection>();

    public RtmpServer(int port){
        this.port = port;
    }

    public RtmpServer(){

    }

    public void start(){
        try {
            socket = new ServerSocket(port,200);
            socket.setReuseAddress(true);
        } catch (IOException e) {
            logger.error("failed to start rtmp server",e);
            System.exit(1);
        }
        while(true){
            try {
                Socket clientSocket = socket.accept();
                RtmpConnection connection = new RtmpConnectionImpl(clientSocket);
                RtmpWorker rtmpWorker = new RtmpWorker(connection);
                ThreadFactory.getInstance().submit(rtmpWorker);
            }catch (Exception e){
                logger.error("failed to get new client socket",e);
            }
        }
    }

    public void stop(){

    }

    private class CloseHandlerImpl implements CloseHandler{

        public void handle(RtmpConnection rtmpConnection) {
            if(rtmpConnection == null){
                return;
            }
            logger.info("close rtmp connection, address:"+rtmpConnection.getLocalAddress().getHostAddress());
            RtmpServer.this.connections.remove(rtmpConnection);
        }
    }

}
