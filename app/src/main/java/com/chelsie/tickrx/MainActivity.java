package com.chelsie.tickrx;

import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.chelsie.tickrx.ui.main.SectionsPagerAdapter;
import com.wahoofitness.connector.HardwareConnector;
import com.wahoofitness.connector.conn.connections.params.ConnectionParams;
import com.wahoofitness.connector.listeners.discovery.DiscoveryListener;
import com.wahoofitness.connector.listeners.discovery.DiscoveryResult;


public class MainActivity extends AppCompatActivity {
    private ComponentName service;
    private HardwareConnector mHardwareConnector;

    private final DiscoveryListener discoveryListener = new DiscoveryListener() {
        @Override
        public void onDeviceDiscovered(@NonNull ConnectionParams connectionParams) {
            Log.i ("DISCOVERY_LISTENER_TAG", "ConnectionParams: " + connectionParams);
            Toast.makeText(getApplicationContext(), "ConnectionParams: " + connectionParams, Toast.LENGTH_LONG).show();
        }

        @Override
        public void onDiscoveredDeviceLost(@NonNull ConnectionParams connectionParams) {

        }

        @Override
        public void onDiscoveredDeviceRssiChanged(@NonNull ConnectionParams connectionParams, int i) {

        }
    };

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
        switch (view.getId()) {
            case R.id.buttonconnect:
                service = startService(new Intent(this, TICKRXService.class));
                Toast.makeText(getApplicationContext(), "startService", Toast.LENGTH_LONG).show();
                break;
            case R.id.buttondisconnect:
                stopService(new Intent(this, TICKRXService.class));
                Toast.makeText(getApplicationContext(), "stopService", Toast.LENGTH_LONG).show();
                break;
            case R.id.buttonstartdiscovery:
                Log.i ("DISCOVERY_TAG", "Start Discovery");
                mHardwareConnector = TICKRXService.mHardwareConnector;
                Log.i ("DISCOVERY_TAG", "ApiVersion: " + mHardwareConnector.getApiVersion());
                Toast.makeText(getApplicationContext(), "Starting Discovery", Toast.LENGTH_LONG).show();
                DiscoveryResult discoveryResult = mHardwareConnector.startDiscovery(discoveryListener);
                Log.i ("DISCOVERY_TAG", "DiscoveryResult: " + discoveryResult);
                break;
            case R.id.buttonstopdiscovery:
                Log.i ("DISCOVERY_TAG", "Stop Discovery");
                mHardwareConnector = TICKRXService.mHardwareConnector;
                Toast.makeText(getApplicationContext(), "Stopping Discovery", Toast.LENGTH_LONG).show();
                mHardwareConnector.stopDiscovery(discoveryListener);
                break;
        }
    }
}