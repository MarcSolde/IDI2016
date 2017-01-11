package com.example.sejo.navdra;

import android.content.Intent;

import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.os.Bundle;
import java.util.List;
/**
 * Created by sejo on 8/01/17.
 */

public class SearcherActivity extends AppCompatActivity {
    private SearchView searchView;
    private FilmData filmData;
    private List<Film> values;
    private ArrayAdapter<Film> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_search);
        searchView = (SearchView) findViewById(R.id.searcher);
        //searchView.setImeOptions(EditorInfo.IME_ACTION_SEARCH);

        filmData = new FilmData(SearcherActivity.this);
        filmData.open();
        ListView listView = (ListView) findViewById(R.id.list_films);
        values = filmData.getAllFilms();
        adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1,values);
        listView.setAdapter(adapter);
        //newOnQueryTextListener(new searchView.OnQueryTextListener());
    }


    private Object newOnQueryTextListener(final SearchView.OnQueryTextListener listener) {
        return new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                String query = (String) searchView.getQuery();
                values = filmData.FindFilms(query);
                adapter.clear();
                adapter.addAll(values);
                return listener.onQueryTextSubmit(s);
            }

            @Override
            public boolean onQueryTextChange(String s) {
                String query = (String) searchView.getQuery();
                values = filmData.FindFilms(query);
                adapter.clear();
                adapter.addAll(values);
                return listener.onQueryTextChange(s);
            }
        };
    }


}
