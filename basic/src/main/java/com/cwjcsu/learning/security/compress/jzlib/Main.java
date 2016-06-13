package com.cwjcsu.learning.security.compress.jzlib;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.zip.Deflater;
import java.util.zip.DeflaterOutputStream;

/**
 * 
 * @author atlas
 * @date 2012-9-7
 */
public class Main {
	public static void main(String... args) throws IOException {
        final String filename = "/home/atlas/Downloads/QQ2012.exe";
        final File file = new File(filename);
        DataInputStream dis = new DataInputStream(new FileInputStream(file));
        byte[] bytes = new byte[(int) file.length()];
        dis.readFully(bytes);
        test(bytes, false);
        for (int i = 0; i < 5; i++)
            test(bytes, true);
    }

    private static void test(byte[] bytes, boolean print) throws IOException {
        OutputStream out = new ByteArrayOutputStream(bytes.length);
        Deflater def = new Deflater(Deflater.BEST_SPEED);
        DeflaterOutputStream dos = new DeflaterOutputStream(out, def, 4 * 1024);
        long start = System.nanoTime();
        int count = 0;
        int size = 5 * 1024;
        for (int i = 0; i < bytes.length - size; i += size, count++) {
            dos.write(bytes, i, size);
            dos.flush();
        }
        dos.close();
        long time = System.nanoTime() - start;
        long latency = time / count;
        // 1 byte per ns = 1000 MB/s.
        long bandwidth = (count * size * 1000L) / time;
        if (print)
            System.out.println("Average latency " + latency + " ns. Bandwidth " + bandwidth + " MB/s.");    
    }
}
