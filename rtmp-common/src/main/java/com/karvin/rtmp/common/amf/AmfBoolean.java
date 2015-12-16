package com.karvin.rtmp.common.amf;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by karvin on 15/12/16.
 */
public class AmfBoolean implements AmfData {

    public static final int SIZE = 2;

    private boolean value;

    public AmfBoolean(boolean bool){
        this.value = bool;
    }

    public AmfBoolean(){

    }

    public void writeTo(OutputStream out) throws IOException {
        out.write(AmfType.BOOLEAN.getValue());
        byte b = 0;
        if(value){
            b = 1;
        }
        out.write(b);
    }

    public void readFrom(InputStream in) throws IOException {
        byte[] bytes = new byte[1];
        in.read(bytes);
        if(bytes[0] == 1){
            this.value = true;
        }else{
            this.value = false;
        }
    }

    public int getSize() {
        return SIZE;
    }
}
