package com.example.sejo.navdra;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class vistaPeli extends AppCompatActivity {
    private Film f;
    private Button b;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vista_peli);
        FilmData fd = new FilmData(vistaPeli.this);
        fd.open();
        Intent myIntent = getIntent();
        f = fd.getFilm(myIntent.getStringExtra("title"), myIntent.getStringExtra("dire"));
        TextView aux = (TextView) findViewById(R.id.tEdit);
        aux.setText(f.getTitle());
        TextView aux2 = (TextView) findViewById(R.id.Pais);
        aux2.setText(f.getCountry());
        aux = (TextView) findViewById(R.id.Any);
        aux.setText(Integer.toString(f.getYear()));
        ((TextView)findViewById(R.id.Director)).setText(f.getDirector());
        ((TextView)findViewById(R.id.Protagonista)).setText(f.getProtagonist());
        ((TextView)findViewById(R.id.Nota)).setText(Integer.toString(f.getCritics_rate()));
        fd.close();
        b = (Button)findViewById(R.id.Boto);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v.getId() == R.id.Boto)
                    Toast.makeText(vistaPeli.this, "Manté pulsat per esborrar", Toast.LENGTH_SHORT).show();
            }

        });
        b.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Esborrar(v);
                return true;
            }
        });
    }

    public void Esborrar(View view) {
        FilmData fd = new FilmData(vistaPeli.this);
        fd.open();
        fd.deleteFilm(f);
        fd.close();
        Toast.makeText(vistaPeli.this, "Pelicula Esborrada", Toast.LENGTH_SHORT).show();
        finish();
    }


}
