package com.karvin.rtmp.common.commands;

/**
 * Created by karvin on 15/12/15.
 */
public class ConnectParameter {

    private String app;
    private String flashver;
    private String swfUrl;
    private String tcUrl;
    private boolean fpad;
    private int audioCodec;
    private int videoCodec;
    private String pageUrl;
    private int objectCoding;

    public String getApp() {
        return app;
    }

    public void setApp(String app) {
        this.app = app;
    }

    public int getAudioCodec() {
        return audioCodec;
    }

    public void setAudioCodec(int audioCodec) {
        this.audioCodec = audioCodec;
    }

    public String getFlashver() {
        return flashver;
    }

    public void setFlashver(String flashver) {
        this.flashver = flashver;
    }

    public boolean isFpad() {
        return fpad;
    }

    public void setFpad(boolean fpad) {
        this.fpad = fpad;
    }

    public int getObjectCoding() {
        return objectCoding;
    }

    public void setObjectCoding(int objectCoding) {
        this.objectCoding = objectCoding;
    }

    public String getPageUrl() {
        return pageUrl;
    }

    public void setPageUrl(String pageUrl) {
        this.pageUrl = pageUrl;
    }

    public String getSwfUrl() {
        return swfUrl;
    }

    public void setSwfUrl(String swfUrl) {
        this.swfUrl = swfUrl;
    }

    public String getTcUrl() {
        return tcUrl;
    }

    public void setTcUrl(String tcUrl) {
        this.tcUrl = tcUrl;
    }

    public int getVideoCodec() {
        return videoCodec;
    }

    public void setVideoCodec(int videoCodec) {
        this.videoCodec = videoCodec;
    }
}
