package com.dvirgos.vinos.util;

import android.content.Context;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

import com.dvirgos.vinos.R;
import com.dvirgos.vinos.data.Vino;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Csv {

    private static final String TAG = "xyzyx";
    private String fileName = String.valueOf((R.string.file_name));

    public static Vino getVino(String str) {
        String[] vinos = str.split(";");
        Vino v = new Vino();
        if( vinos.length >= 7 ) {
            try {
                v.setId(Long.parseLong(vinos[0].trim()));
            } catch (Exception e) {
            }
            v.setNombre(vinos[1].trim());
            v.setBodega(vinos[2].trim());
            v.setColor(vinos[3].trim());
            v.setOrigen(vinos[4].trim());
            try {
                v.setFecha(Integer.parseInt(vinos[5].trim()));
            }catch (Exception e) {
            }try {
                v.setGraduacion(Double.parseDouble(vinos[6].trim()));
            } catch (Exception e) {
            }
        }

        return v;
    }

    public String getCsv (Vino v) {
        return v.getId() + "; " +
                v.getNombre() + "; " +
                v.getBodega() + "; " +
                v.getColor() + "; " +
                v.getOrigen() + "; " +   
                v.getFecha() + "; " +
                v.getGraduacion();
    }

    public void writeFile(File file, String fileName, String string) {
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
    }

    public String writeResult(boolean result) {
        String mensaje = String.valueOf((R.string.message_ok));
        if(!result) {
            mensaje = String.valueOf((R.string.message_no));
        }
        return mensaje;
    }

    public void writeInternalFile(Context context, String text) {

        writeFile(context.getFilesDir(), fileName, text);
    }

    public String readFile(File file, String fileName) {
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

    public String writeReadResult(String result) {
        String string = result;
        if(result == null) {
            string = String.valueOf((R.string.read_no));
        } else if(result.isEmpty()) {
            string = String.valueOf((R.string.read_ok));
        }

        return result;
    }

    public String readInternalFle(Context context) {
        String string = writeReadResult(readFile(context.getFilesDir(), fileName));
        return string;
    }

    public ArrayList<Vino> readFileArray(File file, String fileName) {
        ArrayList<Vino> vino = new ArrayList<>();
        File f = new File(file, fileName);
        String texto = "";
        try {
            BufferedReader br = new BufferedReader(new FileReader(f));
            String linea;
            while ((linea = br.readLine()) != null) {

                Log.v("zzzz", "Leo array: "+getVino(linea).toString());
                vino.add(getVino(linea));
            }
            br.close();
        } catch (IOException e) {
            texto = null;
            Log.v(TAG, e.toString());
        }
        //Log.v("zzzz", "arraylist"+vino.get(0).toString());
        return vino;
    }

    public ArrayList<Vino> readInternalFleArray(Context context) {
        ArrayList<Vino> vino = readFileArray(context.getFilesDir(), fileName);
        return vino;
    }

}
