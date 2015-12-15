package com.karvin.rtmp.common.chunk;

/**
 * Created by karvin on 15/12/15.
 */
public class Chunk {

    private ChunkHeader header;
    private BasicHeader basicHeader;
    private int extendTimestamp;
    private byte[] data;

    public Chunk(BasicHeader basicHeader,ChunkHeader chunkHeader,int extendTimestamp,byte[] data){
        this.header = chunkHeader;
        this.basicHeader = basicHeader;
        this.extendTimestamp = extendTimestamp;
        this.data = data;
    }

    public byte[] getBytes(){
        byte[] chunkHeaders = this.header.getBytes();
        byte[] basicHeaders = this.basicHeader.getContent();
        byte[] extendTimes = new byte[0];
        if(!(header instanceof ChunkHeader3)){
           if(header instanceof ChunkHeader0){
               ChunkHeader0 header0 = (ChunkHeader0)header;
               if(header0.getTimestamp()==0xffffff){
                   extendTimes = new byte[4];
                   extendTimes[0] = (byte)((this.extendTimestamp>>24)&0xff);
                   extendTimes[1] = (byte)((this.extendTimestamp>>16)&0xff);
                   extendTimes[2] = (byte)((this.extendTimestamp>>8)&0xff);
                   extendTimes[3] = (byte)(this.extendTimestamp&0xff);
               }
           }
        }
        byte[] total = new byte[chunkHeaders.length+basicHeaders.length+extendTimes.length+data.length];
        System.arraycopy(basicHeaders,0,total,0,basicHeaders.length);
        System.arraycopy(chunkHeaders,0,total,basicHeaders.length,chunkHeaders.length);
        if(extendTimes.length > 0){
            System.arraycopy(extendTimes,0,total,basicHeaders.length+chunkHeaders.length,extendTimes.length);
        }
        System.arraycopy(data,0,total,basicHeaders.length+chunkHeaders.length+extendTimes.length,data.length);
        return total;
    }

}
