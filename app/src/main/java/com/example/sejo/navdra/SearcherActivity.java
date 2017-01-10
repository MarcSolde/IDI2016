package com.example.sejo.navdra;

import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v7.app.AppCompatActivity;
import android.widget.SearchView;
import android.os.Bundle;

/**
 * Created by sejo on 8/01/17.
 */

public class SearcherActivity extends AppCompatActivity {
private SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_search);
        searchView = (SearchView) findViewById(R.id.searcher);
        //String query = (String) searchView.getQuery();

    }

    //@Override
    //public void on



}
