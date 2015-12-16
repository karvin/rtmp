package com.karvin.rtmp.common.amf;

import com.karvin.rtmp.common.utils.NumberUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Map;

/**
 * Created by karvin on 15/12/16.
 */
public class AmfMap extends AmfObject{

    private int size = -1;

    public AmfMap(){

    }

    public void writeTo(OutputStream out) throws IOException{
        out.write(AmfType.MAP.getValue());
        NumberUtils.writeInt(out,0);
        for(Map.Entry<String,AmfData> entry:properties.entrySet()){
            AmfString amfString = new AmfString(entry.getKey(),true);
            AmfData amfData = entry.getValue();
            amfString.writeTo(out);
            amfData.writeTo(out);
        }
        out.write(AmfObject.OBJECT_END_MARKER);
    }

    public void readFrom(InputStream in) throws IOException{
        int length = NumberUtils.readInt(in);
        super.readFrom(in);
        size += 4;
    }

    public int getSize(){
        if(size == -1){
            size = super.getSize();
            size += 4;
        }
        return size;
    }

}
