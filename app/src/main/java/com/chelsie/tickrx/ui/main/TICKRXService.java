package com.chelsie.tickrx.ui.main;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.NonNull;

import com.wahoofitness.connector.HardwareConnector;
import com.wahoofitness.connector.HardwareConnectorEnums;
import com.wahoofitness.connector.HardwareConnectorTypes;
import com.wahoofitness.connector.conn.connections.SensorConnection;

public class TICKRXService extends Service {
    private HardwareConnector mHardwareConnector;
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
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        com.wahoofitness.common.log.Logger.setLogLevel(Log.VERBOSE);
        mHardwareConnector = new HardwareConnector(this, mHardwareConnectorListener);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mHardwareConnector.shutdown();
    }
}
