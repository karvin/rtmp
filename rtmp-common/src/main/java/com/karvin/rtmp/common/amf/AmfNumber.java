package com.karvin.rtmp.common.amf;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by karvin on 15/12/15.
 */
public class AmfNumber implements AmfData {

    private static final int SIZE = 9;

    private double value;

    public AmfNumber(double value){
        this.value = value;
    }

    public AmfNumber(){

    }

    public void writeTo(OutputStream out) throws IOException {
        out.write(SIZE);
    }

    public void readFrom(InputStream in) throws IOException {
        in.read();

    }

    public int getSize() {
        return SIZE;
    }
}
