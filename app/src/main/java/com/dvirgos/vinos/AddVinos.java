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
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;

public class AddVinos extends AppCompatActivity {

    ArrayList<Vino> vino = new ArrayList<>();
    Vino vinito = new Vino();
    private Button btacept, btCancel;
    private TextView tvText;
    Csv csv;
    Context context;
    private TextInputLayout tlnombre, tlbodega, tlorigen, tlcolor, tlfecha, tlgraduacion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_vinos);
        init();
    }

    private void init() {
        //ViewModel
        Bundle bundle = getIntent().getExtras();
        double id;
        id = bundle.getDouble("valorBundle");
        vino = csv.readInternalFleArray(this);

        tlbodega = findViewById(R.id.texInLaBodegaadd);
        tlcolor = findViewById(R.id.texInLaColoradd);
        tlnombre = findViewById(R.id.texInLaNombreadd);
        tlorigen = findViewById(R.id.texInLaOrigenadd);
        tlfecha = findViewById(R.id.texInLaFechaadd);
        tlgraduacion = findViewById(R.id.texInLaGraduacionadd);

        btacept = findViewById(R.id.btAddAcept);
        btCancel = findViewById(R.id.btAddCancel);

        btCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                volverActivity();
            }
        });
        btacept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vinito.setColor(tlcolor.toString());
                vinito.setNombre(tlnombre.toString());
                vinito.setBodega(tlbodega.toString());
                vinito.setOrigen(tlorigen.toString());
                vinito.setGraduacion(Double.parseDouble(tlgraduacion.toString()));
                vinito.setFecha(Integer.parseInt(tlfecha.toString()));
                vino.add(vinito);

                creoVinito();
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