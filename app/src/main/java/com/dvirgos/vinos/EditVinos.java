package com.dvirgos.vinos;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.dvirgos.vinos.data.Vino;
import com.dvirgos.vinos.util.Csv;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;

public class EditVinos extends AppCompatActivity {

    private String fileName = String.valueOf((R.string.file_name));
    ArrayList<Vino> vino = new ArrayList<>();
    private Button btacept, btDelete, btCancel;
    private TextView tvText;
    Csv csv = new Csv();
    Context context;
    private TextInputEditText tlnombre, tlbodega, tlorigen, tlcolor, tlfecha, tlgraduacion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_vinos);
        init();
    }

    private void init() {
        context = this;
        //ViewModel
        Bundle bundle = getIntent().getExtras();
        long id;
        id = bundle.getLong("valorBundle");
        Log.v("zzzz", "edit: "+id);
        tlbodega = findViewById(R.id.textineditBodega);
        tlcolor = findViewById(R.id.textineditColor);
        tlnombre = findViewById(R.id.textineditNom);
        tlorigen = findViewById(R.id.textineditOrigen);
        tlfecha = findViewById(R.id.textineditFecha);
        tlgraduacion = findViewById(R.id.textineditGraduacion);

        vino = csv.readFileArray(getFilesDir(), fileName);
        Log.v("zzzz", vino.toString());
        getInfoVinos(id);

        btacept = findViewById(R.id.btEditAcept);
        btacept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i = 0; i<vino.size(); i++) {
                    if (vino.get(i).getId() == id) {
                        vino.get(i).setFecha(Integer.parseInt(tlfecha.getText().toString()));
                        vino.get(i).setGraduacion(Double.parseDouble(tlgraduacion.getText().toString()));
                        vino.get(i).setOrigen(tlorigen.getText().toString());
                        vino.get(i).setBodega(tlbodega.getText().toString());
                        vino.get(i).setNombre(tlnombre.getText().toString());
                        vino.get(i).setColor(tlcolor.getText().toString());

                        volverMainActivity(vino.get(i));
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

    private void eliminarVino(long id) {
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

    private void volverMainActivity(Vino vino) {
        String strvinito = csv.getCsv(vino);
        csv.writeInternalFile(context, strvinito);

        //String textoArchivo = getString(vino);

        //csv.writeInternalFile(this, textoArchivo);

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

    private void getInfoVinos(long id) {
        for (int i = 0; i<vino.size(); i++) {
            if (vino.get(i).getId() == id) {
                Log.v("zzzz", "getinfovinos: "+vino.get(i).toString());
                tlbodega.setText(vino.get(i).getBodega());
                tlcolor.setText(vino.get(i).getColor());
                tlfecha.setText(String.valueOf(vino.get(i).getFecha()));
                tlgraduacion.setText(String.valueOf(vino.get(i).getGraduacion()));
                tlnombre.setText(vino.get(i).getNombre());
                tlorigen.setText(vino.get(i).getOrigen());
            }
        }
    }
}