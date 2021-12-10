package com.dvirgos.vinos;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.dvirgos.vinos.data.Vino;
import com.dvirgos.vinos.util.Csv;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;

public class EditVinos extends AppCompatActivity {

    ArrayList<Vino> vino = new ArrayList<>();
    private Button btacept, btDelete, btCancel;
    private TextView tvText;
    Csv csv;
    Context context;
    private TextInputLayout tlnombre, tlbodega, tlorigen, tlcolor, tlfecha, tlgraduacion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_vinos);
        init();
    }

    private void init() {

        //ViewModel
        Bundle bundle = getIntent().getExtras();
        double id;
        id = bundle.getDouble("valorBundle");

        tlbodega = findViewById(R.id.texInLaBodega);
        tlcolor = findViewById(R.id.texInLaColor);
        tlnombre = findViewById(R.id.texInLaNombre);
        tlorigen = findViewById(R.id.texInLaOrigen);
        tlfecha = findViewById(R.id.texInLaFecha);
        tlgraduacion = findViewById(R.id.texInLaGraduacion);

        vino = csv.readInternalFleArray(this);
        getInfoVinos(id);

        btacept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i = 0; i<vino.size(); i++) {
                    if (vino.get(i).getId() == id) {
                        vino.get(i).setFecha(Integer.parseInt(tlfecha.toString()));
                        vino.get(i).setGraduacion(Double.parseDouble(tlgraduacion.toString()));
                        vino.get(i).setOrigen(tlorigen.toString());
                        vino.get(i).setBodega(tlbodega.toString());
                        vino.get(i).setNombre(tlnombre.toString());
                        vino.get(i).setColor(tlcolor.toString());

                        volverMainActivity(vino);
                    }
                }
            }
        });

        btDelete = findViewById(R.id.btDelete);
        btDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                eliminarVino(id);
            }
        });

        btCancel = findViewById(R.id.btEditCancel);
        btCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                volverActivity();
            }
        });
    }

    private void volverActivity() {
        Intent intencion = new Intent(this, MainActivity.class);
        startActivity(intencion);
    }

    private void eliminarVino(double id) {
        String textoArchivo, linea;
        textoArchivo = "";
        for (int i = 0; i<vino.size(); i++) {
            if (vino.get(i).getId() != id) {
                linea = csv.getCsv(vino.get(i));
                textoArchivo += linea + "\n";
            }
        }
        csv.writeInternalFile(this, textoArchivo);

        volverActivity();
    }

    private void volverMainActivity(ArrayList<Vino> vino) {
        String textoArchivo = getString(vino);

        csv.writeInternalFile(this, textoArchivo);

        volverActivity();
    }

    @NonNull
    private String getString(ArrayList<Vino> vino) {
        String textoArchivo, linea;
        textoArchivo = "";
        for (int i = 0; i< vino.size(); i++) {
            linea = csv.getCsv(vino.get(i));
            textoArchivo += linea + "\n";
        }
        return textoArchivo;
    }

    private void getInfoVinos(double id) {
        for (int i = 0; i<vino.size(); i++) {
            if (vino.get(i).getId() == id) {
                tlbodega.setHelperText(vino.get(i).getBodega());
                tlcolor.setHelperText(vino.get(i).getColor());
                tlfecha.setHelperText(String.valueOf(vino.get(i).getFecha()));
                tlgraduacion.setHelperText(String.valueOf(vino.get(i).getGraduacion()));
                tlnombre.setHelperText(vino.get(i).getNombre());
                tlorigen.setHelperText(vino.get(i).getOrigen());
            }
        }
    }
}