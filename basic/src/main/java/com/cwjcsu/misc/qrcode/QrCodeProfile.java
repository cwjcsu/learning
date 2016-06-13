package com.cwjcsu.misc.qrcode;

import com.google.zxing.client.j2se.MatrixToImageConfig;

import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by Weijun on 2016/6/12.
 */
public class QrCodeProfile {
    private int qrcodeWidth=300;
    private int qrcodeHeight=300;
    private String text;
    private InputStream logoImage;
    private OutputStream outputImage;

    private MatrixToImageConfig imageConfig;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public InputStream getLogoImage() {
        return logoImage;
    }

    public void setLogoImage(InputStream logoImage) {
        this.logoImage = logoImage;
    }

    public OutputStream getOutputImage() {
        return outputImage;
    }

    public void setOutputImage(OutputStream outputImage) {
        this.outputImage = outputImage;
    }

    public int getQrcodeWidth() {
        return qrcodeWidth;
    }

    public void setQrcodeWidth(int qrcodeWidth) {
        this.qrcodeWidth = qrcodeWidth;
    }

    public int getQrcodeHeight() {
        return qrcodeHeight;
    }

    public void setQrcodeHeight(int qrcodeHeight) {
        this.qrcodeHeight = qrcodeHeight;
    }

    public MatrixToImageConfig getImageConfig() {
        return imageConfig;
    }

    public void setImageConfig(MatrixToImageConfig imageConfig) {
        this.imageConfig = imageConfig;
    }
}
