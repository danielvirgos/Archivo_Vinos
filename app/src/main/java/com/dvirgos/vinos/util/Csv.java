package com.dvirgos.vinos.util;

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

public class Csv {

    public static Vino getVino(String str) {
        String[] atributos = str.split(",");
        Vino v = new Vino();
        if( atributos.length >= 6 ) {
            try {
                v.setId(Long.parseLong(atributos[0].trim()));
            } catch (Exception e) {
            }
            v.setNombre(atributos[1].trim());
            v.setBodega(atributos[2].trim());
            v.setColor(atributos[3].trim());
            v.setOrigen(atributos[4].trim());
            try {
                v.setFecha(Integer.parseInt(atributos[5].trim()));
            }catch (Exception e) {
            }try {
                v.setGraduacion(Double.parseDouble(atributos[6].trim()));
            } catch (Exception e) {
            }
        }

        return v;
    }

    public static String getCsv (Vino v) {
        return v.getId() + "; " +
                v.getNombre() + "; " +
                v.getBodega() + "; " +
                v.getColor() + "; " +
                v.getOrigen() + "; " +
                v.getGraduacion() + "; " +
                v.getFecha();
    }


}
