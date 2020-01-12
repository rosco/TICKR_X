package com.chelsie.tickrx;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.chelsie.tickrx.ui.main.SectionsPagerAdapter;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);
        FloatingActionButton fab = findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own TICKR action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    public void sendMessage(View view) {
        Log.i ("MAIN_TAG", "sendMessage from Discover Button");
        switch (view.getId()) {
            case R.id.buttonstartdiscovery:
                startService(new Intent(this, TICKRXService.class));
                Toast.makeText(getApplicationContext(), "startService", Toast.LENGTH_LONG).show();
                break;
            case R.id.buttonstopdiscovery:
                stopService(new Intent(this, TICKRXService.class));
                Toast.makeText(getApplicationContext(), "stopService", Toast.LENGTH_LONG).show();
                break;
        }
    }
}