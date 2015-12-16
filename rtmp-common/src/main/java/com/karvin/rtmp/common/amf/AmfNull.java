package com.karvin.rtmp.common.amf;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by karvin on 15/12/16.
 */
public class AmfNull implements AmfData {

    private static final int SIZE = 1;

    public AmfNull(){

    }

    public void writeTo(OutputStream out) throws IOException {
        out.write(AmfType.NULL.getValue());
    }

    public void readFrom(InputStream in) throws IOException {

    }

    public int getSize() {
        return SIZE;
    }
}
