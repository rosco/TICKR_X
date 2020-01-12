package com.chelsie.tickrx;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.wahoofitness.connector.HardwareConnector;
import com.wahoofitness.connector.HardwareConnectorEnums;
import com.wahoofitness.connector.HardwareConnectorTypes;
import com.wahoofitness.connector.conn.connections.SensorConnection;

public class TICKRXService extends Service {
    public static HardwareConnector mHardwareConnector;
    private final HardwareConnector.Listener mHardwareConnectorListener = new HardwareConnector.Listener() {
        @Override
        public void onHardwareConnectorStateChanged(@NonNull HardwareConnectorTypes.NetworkType networkType, @NonNull HardwareConnectorEnums.HardwareConnectorState hardwareConnectorState) {

        }

        @Override
        public void onFirmwareUpdateRequired(@NonNull SensorConnection sensorConnection, @NonNull String s, @NonNull String s1) {

        }
    };


    public TICKRXService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        return null;
    }

    private void initWahooHardwareConnector() {
        com.wahoofitness.common.log.Logger.setLogLevel(Log.INFO);
        mHardwareConnector = new HardwareConnector (getApplicationContext(), mHardwareConnectorListener);
    }

    @Override
    public void onCreate() {
        Toast.makeText(getApplicationContext(), "TICKRXService.onCreate()", Toast.LENGTH_LONG).show();
        super.onCreate();
        initWahooHardwareConnector();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mHardwareConnector.shutdown();
    }
}
