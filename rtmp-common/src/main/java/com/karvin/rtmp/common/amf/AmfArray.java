package com.karvin.rtmp.common.amf;

import com.karvin.rtmp.common.utils.NumberUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by karvin on 15/12/16.
 */
public class AmfArray implements AmfData {

    private List<AmfData> datas = new ArrayList<AmfData>();
    private int size = -1;

    public void writeTo(OutputStream out) throws IOException {
        out.write(AmfType.ARRAY.getValue());
        NumberUtils.writeInt(out,datas.size());
        for(AmfData amfData:datas){
            amfData.writeTo(out);
        }
    }

    public void readFrom(InputStream in) throws IOException {
        int length = NumberUtils.readInt(in);
        size = 5;
        datas = new ArrayList<AmfData>(length);
        for(int i=0;i<length;i++){
            AmfData data = AmfDecoder.decode(in);
            size += data.getSize();
            datas.add(data);
        }
    }

    public int getSize() {
        if(size == -1) {
            size = 5;
            if(datas != null) {
                for (AmfData amfData : datas) {
                    size += amfData.getSize();
                }
            }
        }
        return size;
    }

    public List<AmfData> getItems(){
        return this.datas;
    }

    public void addItem(AmfData data){
        getItems().add(data);
    }

}
