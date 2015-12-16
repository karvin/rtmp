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
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Created by karvin on 15/12/16.
 */
public class RtmpServer {

    private static final Logger logger = LoggerFactory.getLogger(RtmpServer.class);

    private int port = 1935;

    private String host="localhost";

    private ServerSocket socket;

    private AtomicBoolean started = new AtomicBoolean(false);

    private List<RtmpConnection> connections = new ArrayList<RtmpConnection>();

    private final CloseHandler closeHandler = new CloseHandlerImpl();

    public RtmpServer(String host,int port) {
        this.host = host;
        this.port = port;
    }

    public RtmpServer(int port){
        this("localhost",port);
    }

    public RtmpServer() {

    }

    public void start() {
        try {
            socket = new ServerSocket(port, 200);
            socket.setReuseAddress(true);
            ThreadFactory.getInstance().start();
        } catch (IOException e) {
            logger.error("failed to start rtmp server", e);
            System.exit(1);
        }
        started.set(true);
        while (started.get()) {
            try {
                Socket clientSocket = socket.accept();
                logger.info("new connection come");
                RtmpConnection connection = new RtmpConnectionImpl(clientSocket);
                connection.setCloseHandler(closeHandler);
                RtmpWorker rtmpWorker = new RtmpWorker(connection);
                ThreadFactory.getInstance().submit(rtmpWorker);
            } catch (Exception e) {
                e.printStackTrace();
                logger.error("failed to get new client socket", e);
            }
        }
    }

    public void stop() {
        boolean stop = started.compareAndSet(true, false);
        if (stop) {
            ThreadFactory.getInstance().stop();
            try {
                socket.close();
            } catch (IOException e) {
                //ignore
            }
        }
    }

    private class CloseHandlerImpl implements CloseHandler {

        public void handle(RtmpConnection rtmpConnection) {
            if (rtmpConnection == null) {
                return;
            }
            logger.info("close rtmp connection, address:" + rtmpConnection.getLocalAddress().getHostAddress());
            RtmpServer.this.connections.remove(rtmpConnection);
        }
    }

    public static void main(String[] args) {
        RtmpServer server = new RtmpServer();
        server.start();
    }

}
