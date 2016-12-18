package com.ablack13.mosbyexampleapp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import com.ablack13.mosbyexampleapp.fragments.books_list.BooksFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Fragment panel1 = getSupportFragmentManager().findFragmentByTag("panel1");
        if (panel1==null ||!panel1.isAdded()) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fl_content, new BooksFragment(),"panel1")
                    .commitAllowingStateLoss();
        }
    }
}
