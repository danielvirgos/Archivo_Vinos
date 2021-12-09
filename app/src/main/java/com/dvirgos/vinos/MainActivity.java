package com.dvirgos.vinos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.dvirgos.vinos.data.Vino;
import com.dvirgos.vinos.util.Csv;
import com.google.android.material.textfield.TextInputLayout;

public class MainActivity extends AppCompatActivity {

    private TextView textView;
    TextInputLayout tlid;
    Csv csv;
    Vino[] vino = new Vino[0];
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
    }

    private void startEditVino(double id) {

    }

    private boolean comprueboId(Vino[] vino, double id) {
        Boolean boleano = false;
        for (int i = 0; i<vino.length; i++) {
            double auxId = vino[i].getId();
            if (auxId == id) {
                boleano = true;
            } else {
                boleano = false;
            }
        }
        return boleano;
    }

}