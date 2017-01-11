package com.example.sejo.navdra;

import android.content.Intent;

import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SearchView;
import android.os.Bundle;
import java.util.List;
/**
 * Created by sejo on 8/01/17.
 */

public class SearcherActivity extends AppCompatActivity {
    private FilmData filmData;
    private List<Film> values;
    private ArrayAdapter<Film> adapter;

    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_search);
        //searchView.setImeOptions(EditorInfo.IME_ACTION_SEARCH);
        EditText editText = (EditText) findViewById(R.id.searcher);

        filmData = new FilmData(SearcherActivity.this);
        filmData.open();

        listView = (ListView) findViewById(R.id.list_films);

        values = filmData.getAllFilms();
        adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1,values);
        listView.setAdapter(adapter);

        editText.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable s) {}

            @Override
            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {
                if(s.length() != 0){
                    values = filmData.FindFilms(s.toString());
                    adapter.clear();
                    adapter.addAll(values);
                }
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {
                Intent intent = new Intent(SearcherActivity.this, vistaPeli.class);
                Film f = (Film) listView.getItemAtPosition(position);
                intent.putExtra("title", f.getTitle());
                intent.putExtra("dire", f.getDirector());
                startActivity(intent);
            }
        });

    }

    @Override
    protected void onResume() {
        filmData.open();
        ArrayAdapter<Film> adapter = (ArrayAdapter<Film>) listView.getAdapter();
        adapter.clear();
        adapter.addAll(filmData.getAllFilms());
        super.onResume();
    }

    @Override
    protected void onPause() {
        filmData.close();
        super.onPause();
    }

}
