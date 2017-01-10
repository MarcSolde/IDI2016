package com.example.sejo.navdra;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddMovie extends AppCompatActivity {

    private Button ok;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        System.out.println("pene");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_movie);
        ok = (Button)findViewById(R.id.Boto);
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v.getId() == R.id.Boto)
                    AddMovies(v);
            }
        });

    }

    public void AddMovies(View view) {
        String titol, pais, any, director, prot, nota;
        titol = ((EditText) findViewById(R.id.tEdit)).getText().toString();
        pais = ((EditText) findViewById(R.id.tEdit)).getText().toString();
        any = ((EditText) findViewById(R.id.tEdit)).getText().toString();
        director = ((EditText) findViewById(R.id.tEdit)).getText().toString();
        prot = ((EditText) findViewById(R.id.tEdit)).getText().toString();
        nota = ((EditText) findViewById(R.id.tEdit)).getText().toString();
        //MySQLiteHelper db = new MySQLiteHelper(AddMovie.this);
        //db.insert(titol, pais, any, director, prot, nota);
        FilmData fd = new FilmData(AddMovie.this);
        fd.createFilm(titol, pais, any, director, prot, nota);
        Toast.makeText(AddMovie.this, "Pelicula Guardada", Toast.LENGTH_SHORT).show();
    }

}
