package com.dvirgos.vinos;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class EditVinos extends AppCompatActivity {

    private static final String TAG = "xyzyx";
    private EditText etText;
    private TextView tvText;
    private String fileName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_vinos);
        init();
    }

    private void init() {

    }

    private boolean writeFile(File file, String fileName, String string) {
        //https://docs.oracle.com/javase/tutorial/essential/exceptions/tryResourceClose.html
        File f = new File(file, fileName);
        FileWriter fw = null;
        boolean ok = true;
        try {
            fw = new FileWriter(f, true);
            fw.write(string);
            fw.write("\n");
            fw.flush();
            fw.close();
        } catch (IOException e) {
            ok = false;
            Log.v(TAG, e.toString());
        }
        return ok;
    }

    private void writeResult(boolean result) {
        String mensaje = getString(R.string.message_ok);
        if(!result) {
            mensaje = getString(R.string.message_no);
        }
        tvText.setText(mensaje);
    }

    private void writeInternalFile() {
        String text = etText.getText().toString();
        writeResult(writeFile(getFilesDir(), fileName, text));
    }

    private String readFile(File file, String fileName) {
        //https://docs.oracle.com/javase/tutorial/essential/exceptions/tryResourceClose.html
        File f = new File(file, fileName);
        String texto = "";
        try {
            BufferedReader br = new BufferedReader(new FileReader(f));
            String linea;
            while ((linea = br.readLine()) != null) {
                texto += linea + "\n";
            }
            br.close();
        } catch (IOException e) {
            texto = null;
            Log.v(TAG, e.toString());
        }
        return texto;
    }

    private void writeReadResult(String result) {
        String string = result;
        if(result == null) {
            string = getString(R.string.read_no);
        } else if(result.isEmpty()) {
            string = getString(R.string.read_ok);
        }
        tvText.setText(string);
    }

    private void readInternalFle() {
        writeReadResult(readFile(getFilesDir(), fileName));
    }
}