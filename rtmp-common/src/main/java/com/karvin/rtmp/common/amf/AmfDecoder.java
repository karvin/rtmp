package com.karvin.rtmp.common.amf;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by karvin on 15/12/16.
 */
public class AmfDecoder {

    public static AmfData decode(InputStream is) throws IOException{
        byte[] bytes = new byte[1];
        is.read(bytes);
        AmfType type = AmfType.getByValue(bytes[1]);
        AmfData amfData = null;
        if(AmfType.BOOLEAN.equals(type)){
            amfData = new AmfBoolean();
        }else if(AmfType.NULL.equals(type)){
            amfData = new AmfNull();
        }else if(AmfType.NUMBER.equals(type)){
            amfData = new AmfNumber();
        }else if(AmfType.STRING.equals(type)){
            amfData = new AmfString();
        }else if(AmfType.ARRAY.equals(type)){
            amfData = new AmfArray();
        }else if(AmfType.MAP.equals(type)){
            amfData = new AmfMap();
        }else if(AmfType.OBJECT.equals(type)){
            amfData = new AmfObject();
        }
        amfData.readFrom(is);
        return amfData;
    }

}
