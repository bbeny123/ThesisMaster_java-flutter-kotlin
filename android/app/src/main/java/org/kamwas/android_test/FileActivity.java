package org.kamwas.android_test;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import org.kamwas.android_test.helper.Utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.RandomAccessFile;

import static java.lang.System.nanoTime;

public class FileActivity extends AppCompatActivity {

    private static final int FILE_SIZE = 100;
    private static final String FILE_NAME = "test_file";
    private static final String FILE_NAME_2 = "test_file2";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file);
        setSupportActionBar(findViewById(R.id.toolbar));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Button fileStart  = findViewById(R.id.fileStart);
        Button saveButton  = findViewById(R.id.saveButton);
        Button readButton  = findViewById(R.id.readButton);
        Button deleteButton  = findViewById(R.id.deleteButton);
        TextView saveResult  = findViewById(R.id.saveResult);
        TextView readResult  = findViewById(R.id.readResult);
        TextView deleteResult  = findViewById(R.id.deleteResult);

        fileStart.setOnClickListener(b -> {
            Utils.start(saveResult);
            Utils.start(readResult);
            Utils.start(deleteResult);
            AsyncTask.execute(() -> saveFile(saveResult));
            AsyncTask.execute(() -> readFile(readResult));
            AsyncTask.execute(() -> deleteFile(deleteResult));
        });

        saveButton.setOnClickListener(b -> {
            Utils.start(saveResult);
            AsyncTask.execute(() -> saveFile(saveResult));
        });

        readButton.setOnClickListener(b -> {
            Utils.start(readResult);
            AsyncTask.execute(() -> readFile(readResult));
        });

        deleteButton.setOnClickListener(b -> {
            Utils.start(deleteResult);
            AsyncTask.execute(() -> deleteFile(deleteResult));
        });
    }

    public void saveFile(TextView textView) {
        long result = 0L;
        long timer;
        byte[] file = generateFile(FILE_SIZE);

        for (int i = 0; i < 1; i++) {
            timer = nanoTime();
            saveFile(file);
            result = nanoTime() - timer;
            deleteFile();
        }

        Log.i("FileActivity", "Save File finished");
        Utils.setResult(textView, result, 1000);
    }

    public void readFile(TextView textView) {
        long result = 0L;
        long timer;
        byte[] file = generateFile(FILE_SIZE);
        saveFile(file);

        for (int i = 0; i < 10; i++) {
            timer = nanoTime();
            readFile();
            result = nanoTime() - timer;
        }

        deleteFile();
        Log.i("FileActivity", "Read File finished");
        Utils.setResult(textView, result, 10);
    }

    public void deleteFile(TextView textView) {
        long result = 0L;
        long timer;
        byte[] file = generateFile(FILE_SIZE);

        for (int i = 0; i < 10; i++) {
            saveFile(file);
            timer = nanoTime();
            deleteFile();
            result = nanoTime() - timer;
        }
        Log.i("FileActivity", "Delete File finished");
        Utils.setResult(textView, result, 10);
    }

    public byte[] generateFile(int sizeMB) {
        byte[] data = null;
        try {
            RandomAccessFile file = new RandomAccessFile(new File(getFilesDir(), FILE_NAME_2), "rw");
            file.setLength(1024 * 1024 * sizeMB);

            data = new byte[(int) file.length()];
            file.readFully(data);
        } catch (Exception ex) {
            Log.d("FileActivity", "File Generation Error", ex);
        }

        return data;
    }

    public void saveFile(byte[] data) {
        File file = new File(getFilesDir(), FILE_NAME);
        try (FileOutputStream out = new FileOutputStream(file)) {
            out.write(data);
        } catch (Exception ex) {
            Log.d("FileActivity", "Save File", ex);
        }
    }

    public byte[] readFile() {
        File file = new File(getFilesDir(), FILE_NAME);
        byte[] data = new byte[(int) file.length()];

        try (FileInputStream in = new FileInputStream(file)) {
            in.read(data);
        } catch (Exception ex) {
            Log.d("FileActivity", "File Read Error", ex);
        }

        return data;
    }

    public boolean deleteFile() {
        File file = new File(getFilesDir(), FILE_NAME);
        return file.delete();
    }


}
