package com.karvin.rtmp.common.amf;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by karvin on 15/12/15.
 */
public enum  AmfType {

    NUMBER((byte)0x00),
    BOOLEAN((byte)0x01),
    STRING((byte)0x02),
    OBJECT((byte)0x03),
    NULL((byte)0x05),
    MAP((byte)0x08),
    ARRAY((byte)0x0A);


    private byte value;

    private static Map<Byte,AmfType> amfTypeMap = new HashMap<Byte,AmfType>();

    static {
        for(AmfType amfType:AmfType.values()){
            amfTypeMap.put(amfType.value,amfType);
        }
    }

    private AmfType(byte value){
        this.value = value;
    }

    public byte getValue(){
        return this.value;
    }

    public static AmfType getByValue(byte value){
        return amfTypeMap.get(value);
    }
}
