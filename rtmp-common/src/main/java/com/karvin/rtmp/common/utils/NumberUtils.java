package com.karvin.rtmp.common.utils;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by karvin on 15/12/15.
 */
public class NumberUtils {

    public static byte[] getBytes(int number){
        byte[] bytes = new byte[4];
        bytes[0] = (byte)((number>>24)&0xff);
        bytes[1] = (byte)((number>>16)&0xff);
        bytes[2] = (byte)((number>>8)&0xff);
        bytes[3] = (byte)(number&0xff);
        return bytes;
    }

    public static byte[] getBytes(long number){
        byte[] bytes = new byte[8];
        bytes[0] = (byte)((number>>56)&0xff);
        bytes[1] = (byte)((number>>48)&0xff);
        bytes[2] = (byte)((number>>40)&0xff);
        bytes[3] = (byte)((number>>32)&0xff);
        bytes[4] = (byte)((number>>24)&0xff);
        bytes[5] = (byte)((number>>16)&0xff);
        bytes[6] = (byte)((number>>8)&0xff);
        bytes[7] = (byte)(number&0xff);
        return bytes;
    }

    public static byte[] getByes(short number){
        byte[] bytes = new byte[2];
        bytes[0] = (byte)((number>>8)&0xff);
        bytes[1] = (byte)(number&0xff);
        return bytes;
    }

    public static long toLong(byte[] bytes){
        long l = (long)(bytes[0]<<56|bytes[1]<<48|bytes[2]<<40|bytes[3]<<32|bytes[4]<<24|bytes[5]<<16|bytes[6]<<8|bytes[7]);
        return l;
    }

    public static int toInt32(byte[] bytes){
        return bytes[0]<<24|bytes[1]<<16|bytes[2]<<8|bytes[3];
    }

    public static int toInt16(byte[] bytes){
        return bytes[0]<<8|bytes[1];
    }

    public static byte[] getBytes(double number){
        long l = Double.doubleToRawLongBits(number);
        byte[] bytes = new byte[8];
        bytes[0] = (byte)(l>>56&0xff);
        bytes[1] = (byte)(l>>48&0xff);
        bytes[2] = (byte)(l>>40&0xff);
        bytes[3] = (byte)(l>>32&0xff);
        bytes[4] = (byte)(l>>24&0xff);
        bytes[5] = (byte)(l>>16&0xff);
        bytes[6] = (byte)(l>>8&0xff);
        bytes[7] = (byte)(l&0xff);
        return bytes;
    }

    public static double toDouble(byte[] bytes){
        long l = (long)(bytes[0]<<56|bytes[1]<<48|bytes[2]<<40|bytes[3]<<32|bytes[4]<<24|bytes[5]<<16|bytes[6]<<8|bytes[7]);
        return Double.longBitsToDouble(l);
    }

    public static int readInt(InputStream in) throws IOException{
        byte[] bytes = new byte[4];
        in.read(bytes);
        return toInt32(bytes);
    }

    public static double readDouble(InputStream in)throws IOException{
        byte[] bytes = new byte[8];
        in.read(bytes);
        return toDouble(bytes);
    }

    public static short readShort(InputStream in) throws IOException{
        byte[] bytes = new byte[2];
        in.read(bytes);
        return (short)toInt16(bytes);
    }

    public static void writeInt(OutputStream out,int number) throws IOException{
        byte[] bytes = getBytes(number);
        out.write(bytes);
    }

    public static void writeShort(OutputStream out,short number) throws IOException{
        byte[] bytes = getBytes(number);
        out.write(bytes);
    }

    public static void writeDouble(OutputStream out,double number) throws IOException{
        byte[] bytes = getBytes(number);
        out.write(bytes);
    }

    public static void writeLong(OutputStream out,long number) throws IOException{
        byte[] bytes = getBytes(number);
        out.write(bytes);
    }

}
