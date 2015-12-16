package com.karvin.rtmp.common.io.impl;

import com.karvin.rtmp.common.io.CloseHandler;
import com.karvin.rtmp.common.io.RtmpConnection;
import com.karvin.rtmp.common.io.RtmpRequest;
import com.karvin.rtmp.common.io.RtmpResponse;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 * Created by karvin on 15/12/16.
 */
public class RtmpConnectionImpl implements RtmpConnection {

    private InetSocketAddress remoteAddress;
    private InetAddress localAddress;
    private Socket socket;
    private RtmpRequest request;
    private RtmpResponse response;
    private CloseHandler closeHandler;

    public RtmpConnectionImpl(Socket socket){
        this.socket = socket;
        this.remoteAddress = ((InetSocketAddress)socket.getRemoteSocketAddress());
        this.localAddress = socket.getLocalAddress();
    }

    public RtmpConnectionImpl(String host,int port){
        this.socket = new Socket();
        this.remoteAddress = new InetSocketAddress(host,port);
    }

    public InetSocketAddress getRemoteAddress() {
        return remoteAddress;
    }

    public int getRemotePort() {
        return socket.getPort();
    }

    public int getLocalPort() {
        return socket.getLocalPort();
    }

    public InetAddress getLocalAddress() {
        return this.localAddress;
    }

    public InputStream getInputStream() throws IOException{
        return socket.getInputStream();
    }

    public OutputStream getOutputStream() throws IOException{
        return socket.getOutputStream();
    }

    public RtmpRequest getRequest() {
        return this.request;
    }

    public RtmpResponse getResponse() {
        return this.response;
    }

    public void connect() throws IOException {
        socket.connect(remoteAddress);
    }

    public void close() throws IOException {
        socket.close();
        if(closeHandler != null){
            closeHandler.handle(this);
        }
    }

    public CloseHandler getCloseHandler() {
        return closeHandler;
    }

    public void setCloseHandler(CloseHandler closeHandler){
        this.closeHandler = closeHandler;
    }
}
