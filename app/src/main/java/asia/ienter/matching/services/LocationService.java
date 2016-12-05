package asia.ienter.matching.services;

import android.Manifest;
import android.app.Service;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.Process;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.gson.Gson;

import asia.ienter.matching.MCApp;
import asia.ienter.matching.models.User;
import asia.ienter.matching.utils.MLog;

/**
 * Created by phamquangmanh on 12/2/16.
 */
public class LocationService extends Service implements GoogleApiClient.ConnectionCallbacks,GoogleApiClient.OnConnectionFailedListener, LocationListener{

    GoogleApiClient mGoogleApiClient;
    LocationRequest mLocationRequest;
    Location mLastLocation;
    @Override
    public void onConnected(@Nullable Bundle bundle) {
        MLog.d(LocationService.class,"connect GoogleApiClient Service ok");

        mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(1000);
        mLocationRequest.setFastestInterval(1000);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest,this);
        }
    }

    @Override
    public void onConnectionSuspended(int i) {
        MLog.d(LocationService.class,"connect GoogleApiClient Service suspend");
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        MLog.d(LocationService.class,"connect GoogleApiClient Service false");
    }

    // Handler that receives messages from the thread

    @Override
    public void onCreate() {
        // Start up the thread running the service.  Note that we create a
        // separate thread because the service normally runs in the process's
        // main thread, which we don't want to block.  We also make it
        // background priority so CPU-intensive work will not disrupt our UI.

        MLog.d(LocationService.class,"onCreate Service");

        if (mGoogleApiClient == null) {
            mGoogleApiClient = new GoogleApiClient.Builder(this)
                    .addConnectionCallbacks(this)
                    .addOnConnectionFailedListener(this)
                    .addApi(LocationServices.API)
                    .build();
            mGoogleApiClient.connect();
        }
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        MLog.d(LocationService.class,"service starting");


        // If we get killed, after returning from here, restart
        return START_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        // We don't provide binding, so return null
        MLog.d(LocationService.class,"service onBind");
        return null;
    }

    @Override
    public void onDestroy() {
        mGoogleApiClient.disconnect();
        MLog.d(LocationService.class,"service onDestroy");
    }

    @Override
    public void onLocationChanged(Location location) {

        if(mLastLocation==null) mLastLocation = location;
        if((mLastLocation.getLatitude()!=location.getLatitude()&&(mLastLocation.getLongitude()!=location.getLongitude()))){
            MLog.d(LocationService.class,"mLastLocation!=location");
            mLastLocation = location;
            User user = MCApp.getInstance().getUserInstance();
            user.setLat(mLastLocation.getLatitude());
            user.setLong(mLastLocation.getLongitude());
            MCApp.setUserInstance(user);
            UserServices.getInstance().updateUserInformation(user);

        }
    }
}