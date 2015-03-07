package com.example.tylerpollak.sae_ucsd;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.os.Bundle;
import android.os.RemoteException;
import android.util.Log;
import android.widget.Toast;

import com.estimote.sdk.Beacon;
import com.estimote.sdk.BeaconManager;
import com.estimote.sdk.Region;

import java.util.List;

/**
 * Created by tylerpollak on 3/6/15.
 */
public class estimoteActivity extends Activity {

    private static final String ESTIMOTE_PROXIMITY_UUID = "B9407F30-F5F8-466E-AFF9-25556B57FE6D";
    private static final Region ALL_ESTIMOTE_BEACONS = new Region("regionId", ESTIMOTE_PROXIMITY_UUID, null, null);

    private BeaconManager beaconManager = new BeaconManager(this);

    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estimote);


        beaconManager.setRangingListener(new BeaconManager.RangingListener() {
            @Override public void onBeaconsDiscovered(Region region, List<Beacon> beacons) {
                Log.d("TAG", "Ranged beacons: " + beacons);
            }
        });

    }

    @Override
    public void onStart(){
        super.onStart();
        // Check if device supports Bluetooth Low Energy.
        if (!beaconManager.hasBluetooth()) {
            Toast.makeText(this, "Device does not have Bluetooth Low Energy", Toast.LENGTH_LONG).show();
            return;
        }

        // Should be invoked in #onStart.
        beaconManager.connect(new BeaconManager.ServiceReadyCallback() {
            @Override public void onServiceReady() {
                try {
                    System.out.println("ASDASDASDAEWEWAEAE@#!#!@#!@#!@#!@#!@#!@#!@#");
                    beaconManager.startRanging(ALL_ESTIMOTE_BEACONS);
                    Log.d("TAG", "ADASDASDASD");
                } catch (RemoteException e) {
                    Log.d("TAG", "HERE");
                    //Log.e(TAG, "Cannot start ranging", e);
                }
            }
        });
    }

}
