package com.karvin.rtmp.common.amf;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by karvin on 15/12/16.
 */
public class AmfObject implements AmfData {

    protected Map<String,AmfData> properties = new LinkedHashMap<String,AmfData>();

    private int size = -1;

    public static final byte[] OBJECT_END_MARKER = new byte[]{0x00,0x00,0x09};

    public AmfObject(){

    }

    public AmfData getProperty(String key){
        return properties.get(key);
    }

    public void setProperty(String key,AmfData amfData){
        properties.put(key,amfData);
    }

    public void setProperty(String key,boolean bool){
        AmfBoolean amfBoolean = new AmfBoolean(bool);
        properties.put(key,amfBoolean);
    }

    public void setProperty(String key,int number){
        AmfNumber amfNumber = new AmfNumber(number);
        properties.put(key, amfNumber);
    }

    public void setProperty(String key,double number){
        AmfNumber amfNumber = new AmfNumber(number);
        properties.put(key, amfNumber);
    }

    public void setProperty(String key,String value){
        AmfString amfString = new AmfString(value,false);
        properties.put(key, amfString);
    }

    public void writeTo(OutputStream out) throws IOException {
        out.write(AmfType.OBJECT.getValue());
        for(Map.Entry<String,AmfData> entry:properties.entrySet()){
            String key = entry.getKey();
            AmfString amfString = new AmfString(key,true);
            amfString.writeTo(out);
            AmfData amfData = entry.getValue();
            amfData.writeTo(out);
        }
        out.write(OBJECT_END_MARKER);
    }

    public void readFrom(InputStream in) throws IOException {

        InputStream is = in.markSupported()?in:new BufferedInputStream(in);
        size = 1;
        while(true){
            byte[] bytes = new byte[3];
            is.read(bytes);
            if(bytes[0] == OBJECT_END_MARKER[0] && bytes[1] == OBJECT_END_MARKER[1] && bytes[2] == OBJECT_END_MARKER[2]){
                //object read over
                size += 3;
                return;
            }else{
                is.reset();
                AmfString amfString = new AmfString("",true);
                amfString.readFrom(is);
                size += amfString.getSize();
                AmfData amfData = AmfDecoder.decode(in);
                size += amfData.getSize();
                properties.put(amfString.getValue(),amfData);
            }
        }

    }

    public int getSize() {
        if(size == -1) {
            size = 1;
            for (Map.Entry<String, AmfData> entry : properties.entrySet()) {
                AmfString amfString = new AmfString(entry.getKey(), true);
                size += amfString.getSize();
                size += entry.getValue().getSize();
            }
            size += 3;
        }
        return size;
    }
}
