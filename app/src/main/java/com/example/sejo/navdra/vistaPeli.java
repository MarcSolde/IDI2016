package com.example.sejo.navdra;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

public class vistaPeli extends AppCompatActivity {
    private Film f;
    private Button b;
    private RatingBar ratingBar;
    private Integer NumStars = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vista_peli);
        final FilmData fd = new FilmData(vistaPeli.this);
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
        Integer rate = f.getCritics_rate();
        final TextView nota_num = (TextView)findViewById(R.id.nota_num);
        nota_num.setText(Integer.toString(rate));
        fd.close();
        ratingBar = (RatingBar) findViewById(R.id.nota);
        ratingBar.setNumStars(NumStars);
        ratingBar.setRating(rate*NumStars/10);

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

        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener(){
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating,
                                        boolean fromUser) {
                Integer rate = (int) rating*10/NumStars;
                fd.open();
                fd.setCriticsOnFilm(f, rate);
                nota_num.setText(String.valueOf(rate));
                fd.close();
        }});
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
