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

public class MainActivity extends AppCompatActivity {

    private TextView textView, txVino;
    TextInputLayout tlid;
    Csv csv;
    ArrayList<Vino> vino = new ArrayList<Vino>();
    Button btAdd, btEdit;
    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    public void init() {
        String string = csv.readInternalFle(this);
        textView = findViewById(R.id.tvExplicativo);
        txVino = findViewById(R.id.tvVino);

        txVino.setText(string);

        tlid = findViewById(R.id.texInLaId);
        btAdd = findViewById(R.id.btAdd);
        btEdit = findViewById(R.id.btEdit);
        double id = Double.parseDouble(tlid.toString());
        context = this;
        btEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vino = csv.readInternalFleArray(context);
                if(comprueboId(vino, id)){
                    startEditVino(id);
                } else {
                    textView.setText("Este id no existe");
                }
            }
        });

        btAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vino = csv.readInternalFleArray(context);
                if(!comprueboId(vino, id)){
                    startAddVino(id);
                } else {
                    textView.setText("Este id ya existe");
                }
            }
        });
    }

    private void startAddVino(double id) {
        Intent intencion = new Intent(this, AddVinos.class);
        Bundle bundle = new Bundle();
        bundle.putDouble("valorBundle", id);
        intencion.putExtras(bundle);
        startActivity(intencion);
    }

    private void startEditVino(double id) {
        Intent intencion = new Intent(this, EditVinos.class);
        Bundle bundle = new Bundle();
        bundle.putDouble("valorBundle", id);
        intencion.putExtras(bundle);
        startActivity(intencion);
    }

    private boolean comprueboId(ArrayList<Vino> vino, double id) {
        Boolean boleano = false;
        for (int i = 0; i<vino.size(); i++) {
            double auxId = vino.get(i).getId();
            if (auxId == id) {
                boleano = true;
            } else {
                boleano = false;
            }
        }
        return boleano;
    }

}