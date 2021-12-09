package com.dvirgos.vinos;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.dvirgos.vinos.data.Vino;
import com.dvirgos.vinos.util.Csv;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    public void init() {
        Vino v = new Vino(1,"Betis", "Man que pierda", "Verde", "Benito VillaMarin", 31082020, 2.5);
        String csv = Csv.getCsv(v);
        Log.v("zzzz", csv);
        Vino v2 = new Vino(1,"Betis", "Man que pierda", "Verde", "Benito VillaMarin", 31082020, 2.5);

        //Hacer una apliacion que nos muestre los vinos guardados en el archivo csv
        //Con un boton add que nos permita añadir mas vinos, si el id del vino esta repetido, no nos permite crearlo
        //También añadimos un boton de editar, que nos busque el id que hemos introducido y podamos editar el vino
        //En la seccion de edición, ponemos un boton de borrar
    }

}