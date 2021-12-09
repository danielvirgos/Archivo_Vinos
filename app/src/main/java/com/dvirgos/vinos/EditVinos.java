package com.dvirgos.vinos;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.dvirgos.vinos.data.Vino;
import com.google.android.material.textfield.TextInputLayout;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class EditVinos extends AppCompatActivity {

    Vino[] vino = new Vino[0];
    private Button btacept;
    private TextView tvText;
    private TextInputLayout tlid, tlnombre, tlbodega, tlorigen, tlcolor, tlfecha, tlgraduacion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_vinos);
        init();
    }

    private void init() {

        tlbodega = findViewById(R.id.texInLaBodega);
        tlcolor = findViewById(R.id.texInLaColor);
        tlnombre = findViewById(R.id.texInLaNombre);
        tlorigen = findViewById(R.id.texInLaOrigen);
        tlfecha = findViewById(R.id.texInLaFecha);
        tlgraduacion = findViewById(R.id.texInLaGraduacion);

        btacept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}