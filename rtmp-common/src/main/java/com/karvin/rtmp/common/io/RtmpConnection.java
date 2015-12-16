package com.karvin.rtmp.common.io;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.InetSocketAddress;

/**
 * Created by karvin on 15/12/16.
 */
public interface RtmpConnection {

    InetSocketAddress getRemoteAddress();

    int getRemotePort();

    int getLocalPort();

    InetAddress getLocalAddress();

    InputStream getInputStream() throws IOException;

    OutputStream getOutputStream() throws IOException;

    RtmpRequest getRequest();

    RtmpResponse getResponse();

    void connect() throws IOException;

    void close() throws IOException;

    CloseHandler getCloseHandler();

    void setCloseHandler(CloseHandler closeHandler);

}
