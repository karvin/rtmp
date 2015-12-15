package com.karvin.rtmp.common.amf;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by karvin on 15/12/15.
 */
public enum  AmfType {

    NUMBER(0x00),
    BOOLEAN(0x01),
    STRING(0x02),
    OBJECT(0x03),
    NULL(0x05),
    MAP(0x08),
    ARRAY(0x0A);


    private int value;

    private static Map<Integer,AmfType> amfTypeMap = new HashMap<Integer,AmfType>();

    static {
        for(AmfType amfType:AmfType.values()){
            amfTypeMap.put(amfType.value,amfType);
        }
    }

    private AmfType(int value){
        this.value = value;
    }
}
