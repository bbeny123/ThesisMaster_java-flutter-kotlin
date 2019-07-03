package org.kamwas.android_test;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import org.kamwas.android_test.helper.Util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.security.SecureRandom;

import static java.lang.System.nanoTime;

public class FileActivity extends AppCompatActivity {

    private static final double times = 50;
    private long timer = 0L;

    private static final int FILE_SIZE = 100;
    private static final String FILE_NAME = "test_file";
    private static final String FILE_NAME_2 = "test_file2";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file);
        setSupportActionBar(findViewById(R.id.toolbar));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Util.benchmarkListener(findViewById(R.id.saveButton), findViewById(R.id.saveResult), this::saveFile);
        Util.benchmarkListener(findViewById(R.id.readButton), findViewById(R.id.readResult), this::readFile);
        Util.benchmarkListener(findViewById(R.id.deleteButton), findViewById(R.id.deleteResult), this::deleteFile);
    }

    public double saveFile() {
        long result = 0L;

        generateFile(FILE_NAME_2, FILE_SIZE);
        byte[] file = readFile(FILE_NAME_2);
        for (int i = 0; i < times; i++) {
            timer = nanoTime();
            saveFile(FILE_NAME, file);
            result += nanoTime() - timer;
            deleteFile(FILE_NAME);
        }

        Log.i("FileActivity", "Save File finished");
        deleteFile(FILE_NAME_2);
        return result / times;
    }

    public double readFile() {
        long result = 0L;
        int dummy = 0;

        generateFile(FILE_NAME, FILE_SIZE);
        for (int i = 0; i < times; i++) {
            timer = nanoTime();
            dummy += readFile(FILE_NAME).length;
            result += nanoTime() - timer;
        }

        Log.i("FileActivity", "Read File finished " + dummy);
        deleteFile(FILE_NAME);
        return result / times;
    }

    public double deleteFile() {
        long result = 0L;
        boolean dummy = false;

        generateFile(FILE_NAME_2, FILE_SIZE);
        byte[] file = readFile(FILE_NAME_2);
        for (int i = 0; i < times; i++) {
            saveFile(FILE_NAME, file);
            timer = nanoTime();
            dummy = deleteFile(FILE_NAME);
            result += nanoTime() - timer;
        }

        Log.i("FileActivity", "Delete File finished " + dummy);
        deleteFile(FILE_NAME_2);
        return result / times;
    }

    public void saveFile(String name, byte[] data) {
        File file = new File(getFilesDir(), name);

        try (FileOutputStream out = new FileOutputStream(file)) {
            out.write(data);
        } catch (Exception ex) {
            Log.d("FileActivity", "Save File", ex);
        }
    }

    public byte[] readFile(String name) {
        File file = new File(getFilesDir(), name);
        byte[] data = new byte[(int) file.length()];

        try (FileInputStream in = new FileInputStream(file)) {
            in.read(data);
        } catch (Exception ex) {
            Log.d("FileActivity", "File Read Error", ex);
        }

        return data;
    }

    public boolean deleteFile(String name) {
        return new File(getFilesDir(), name).delete();
    }

    public void generateFile(String name, int sizeMB) {
        byte[] data = new byte[1024 * 1024 * sizeMB];
        new SecureRandom().nextBytes(data);

        try (FileOutputStream out = new FileOutputStream(new File(getFilesDir(), name))) {
            out.write(data);
        } catch (Exception ex) {
            Log.d("FileActivity", "File Generation Error", ex);
        }
    }

}
