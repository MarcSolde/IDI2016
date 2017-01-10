package com.example.sejo.navdra;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class vistaPeli extends AppCompatActivity {

    TextView t, a, p, d, n, pr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vista_peli);
        t = (TextView)findViewById(R.id.tEdit);
        t.setText(getIntent().getStringExtra("titol"));
        a= (TextView)findViewById(R.id.aEdit);;
//        a.setText(getIntent().getStringExtra("any"));
        p= (TextView)findViewById(R.id.pEdit);;
//        p.setText(getIntent().getStringExtra("pais"));
        d= (TextView)findViewById(R.id.dEdit);;
//        d.setText(getIntent().getStringExtra("director"));
        n= (TextView)findViewById(R.id.nEdit);;
  //      n.setText(getIntent().getStringExtra("nota"));
        pr= (TextView)findViewById(R.id.Protagonista);;
//        pr.setText(getIntent().getStringExtra("prota"));
        /*
        TODO Encontrar la manera que pueda recuperar todos los datos de la base de datos a partir de el titulo y el director ya
                FilmData hace un Override de la funcion to String para solo poner dos valores en la lista
                    1.- Hacer una query en la que se supone que no puede haver dos peliculas con el mismo nombre y director (mirar constraints later)

         */
    }
}
