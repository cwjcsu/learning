package com.cwjcsu.misc.qrcode;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageConfig;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Weijun on 2016/6/12.
 */
public class LogoQrCode {
    public static void main(String[] args) throws WriterException, IOException {
        QrCodeProfile p = new QrCodeProfile();
        p.setText("一好友失恋，发条动态:\n“为什么听歌都会感到窒息？”\n神回复:“麻烦不要把耳机塞在鼻孔好吗”\n By the way,I miss you!");
//        p.setLogoImage(new FileInputStream(new File("logo.png")));
        p.setLogoImage(LogoQrCode.class.getResourceAsStream("/qrcode/logo.png"));
        File file = new File("qrcode-withlogo.png");
        if (!file.exists()) {
            file.createNewFile();
        }
        OutputStream os =new FileOutputStream(file);
        p.setOutputImage(os);
        // notice that the color format is "0x(alpha: 1 byte)(RGB: 3 bytes)"
        p.setImageConfig(new MatrixToImageConfig(0xFFFF0000, 0xFFFFFFFF));
        try {
            generateQrCode(p);
        }finally {
            os.close();
        }

    }

    public static void generateQrCode(QrCodeProfile p) throws IOException, WriterException {
        Map<EncodeHintType, Object> hints = new HashMap<EncodeHintType, Object>();
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
        hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
        QRCodeWriter  qrWriter = new QRCodeWriter();
        BitMatrix matrix = qrWriter.encode(p.getText(),
                BarcodeFormat.QR_CODE,
                p.getQrcodeWidth(),
                p.getQrcodeHeight(),
                hints);
        BufferedImage image =p.getImageConfig()==null?MatrixToImageWriter.toBufferedImage(matrix):MatrixToImageWriter.toBufferedImage(matrix,p.getImageConfig());
        BufferedImage overlay = ImageIO.read(p.getLogoImage());
        int deltaHeight = image.getHeight() - overlay.getHeight();
        int deltaWidth  = image.getWidth()  - overlay.getWidth();

        BufferedImage combined = new BufferedImage(p.getQrcodeWidth(), p.getQrcodeHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics2D  g = (Graphics2D)combined.getGraphics();
        g.drawImage(image, 0, 0, null);
        g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f)) ;
        g.drawImage(overlay, (int)Math.round(deltaWidth/2), (int)Math.round(deltaHeight/2), null);
        ImageIO.write(combined, "PNG", p.getOutputImage());
    }
}
