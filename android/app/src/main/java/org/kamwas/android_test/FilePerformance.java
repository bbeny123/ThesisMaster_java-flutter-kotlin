package org.kamwas.android_test;

import android.content.Context;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.RandomAccessFile;

public class FilePerformance {

    public byte[] generateFile(int sizeMB) throws Exception {
        RandomAccessFile file = new RandomAccessFile("t", "rw");
        file.setLength(1024 * 1024 * sizeMB);

        byte[] data = new byte[(int) file.length()];
        file.readFully(data);

        return data;
    }

    public void saveFile(Context context, String name, byte[] data) {
        File file = new File(context.getFilesDir(), name);
        try (FileOutputStream out = new FileOutputStream(file)) {
            out.write(data);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public byte[] readFile(Context context, String name) {
        File file = new File(context.getFilesDir(), name);
        byte[] data = new byte[(int) file.length()];

        try (FileInputStream in = new FileInputStream(file)) {
            in.read(data);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return data;
    }

    public boolean deleteFile(Context context, String name) {
        File file = new File(context.getFilesDir(), name);
        return file.delete();
    }

}
