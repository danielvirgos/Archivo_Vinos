package com.dvirgos.vinos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.dvirgos.vinos.data.Vino;
import com.dvirgos.vinos.util.Csv;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;

public class AddVinos extends AppCompatActivity {

    ArrayList<Vino> vino = new ArrayList<>();
    Vino vinito = new Vino();
    private Button btacept, btCancel;
    private TextView tvText;
    Csv csv = new Csv();
    Context context;
    private TextInputEditText tlnombre, tlbodega, tlorigen, tlcolor, tlfecha, tlgraduacion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_vinos);
        init();
    }

    private void init() {
        //ViewModel
        Bundle bundle = getIntent().getExtras();
        long id;
        id = bundle.getLong("valorBundle");
        vino = csv.readInternalFleArray(this);

        tlbodega = findViewById(R.id.textineditBodegadd);
        tlcolor = findViewById(R.id.textineditColoradd);
        tlnombre = findViewById(R.id.textineditNomadd);
        tlorigen = findViewById(R.id.textineditOrigenadd);
        tlfecha = findViewById(R.id.textineditFehcadd);
        tlgraduacion = findViewById(R.id.textineditGraduadd);

        btacept = findViewById(R.id.btAddAcept);
        btCancel = findViewById(R.id.btAddCancel);
        context = this;

        btCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                volverActivity();
            }
        });
        btacept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vinito.setId(id);
                vinito.setColor(tlcolor.getText().toString());
                vinito.setNombre(tlnombre.getText().toString());
                vinito.setBodega(tlbodega.getText().toString());
                vinito.setOrigen(tlorigen.getText().toString());
                vinito.setGraduacion(Double.parseDouble(tlgraduacion.getText().toString()));
                vinito.setFecha(Integer.parseInt(tlfecha.getText().toString()));
                String strvinito = csv.getCsv(vinito);
                csv.writeInternalFile(context, strvinito);
                //vino.add(vinito);

                //creoVinito();
                volverActivity();
            }
        });
    }

    private void creoVinito() {
        String textoArchivo = getString(vino);

        csv.writeInternalFile(this, textoArchivo);

        volverActivity();
    }

    private String getString(ArrayList<Vino> vino) {
        String textoArchivo, linea;
        textoArchivo = "";
        for (int i = 0; i< vino.size(); i++) {
            linea = csv.getCsv(vino.get(i));
            textoArchivo += linea + "\n";
        }
        return textoArchivo;
    }

    private void volverActivity() {
        Intent intencion = new Intent(this, MainActivity.class);
        startActivity(intencion);
    }


}