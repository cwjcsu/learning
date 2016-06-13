package com.cwjcsu.misc.qrcode;

import net.glxn.qrgen.core.image.ImageType;
import net.glxn.qrgen.javase.QRCode;

import java.io.*;

/**
 * https://github.com/kenglxn/QRGen
 * Created by Weijun on 2016/6/12.
 */
public class GenerateQrCode {
    public static void main(String[] args) {
        try {
            generateQrCode("你好啊","abc.png");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void generateQrCode(String text, String file) throws IOException {
        File f = new File(file);
        if (!f.exists()) {
            f.createNewFile();
        }
        OutputStream out = new FileOutputStream(f);
        QRCode.from(text).withCharset("UTF-8").withColor(0xFFFF0000, 0xFFFFFFAA).to(ImageType.PNG).writeTo(out);
    }
}
