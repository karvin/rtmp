package com.karvin.rtmp.common.amf;

import com.karvin.rtmp.common.utils.NumberUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;

/**
 * Created by karvin on 15/12/16.
 */
public class AmfString implements AmfData {

    private String value;
    private boolean isKey = false;

    public AmfString(String string,boolean isKey){
        this.value = string;
        this.isKey = isKey;
    }

    public AmfString(String string){
        this(string, false);
    }

    public AmfString(){
        this("");
    }

    public void writeTo(OutputStream out) throws IOException {
        byte[] bytes = this.value.getBytes("ASCII");
        if(isKey){
            out.write(AmfType.STRING.getValue());
        }
        out.write(NumberUtils.getByes((short) bytes.length));
        out.write(bytes);
    }

    public void readFrom(InputStream in) throws IOException {
        if(!isKey){
            in.read();
        }
        byte[] bytes = new byte[2];
        in.read(bytes);
        int length = NumberUtils.toInt16(bytes);
        byte[] result = new byte[length];
        in.read(result);
        this.value = new String(bytes,"ASCII");
    }

    public int getSize() {
        return sizeOf(this.value,this.isKey);
    }

    public static int sizeOf(String str,boolean isKey){
        try {
            int size = 0;
            size = (isKey ? 0 : 1) + 2 + str.getBytes("ASCII").length;
            return size;
        }catch (UnsupportedEncodingException e){
            throw new RuntimeException("unsupport encoding");
        }
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
