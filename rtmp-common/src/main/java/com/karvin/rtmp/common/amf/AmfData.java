package com.karvin.rtmp.common.amf;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by karvin on 15/12/15.
 */
public interface AmfData {

    void writeTo(OutputStream out) throws IOException;

    void readFrom(InputStream in) throws IOException;

    int getSize();

}
