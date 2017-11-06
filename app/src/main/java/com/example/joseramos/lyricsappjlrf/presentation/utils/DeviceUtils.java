package com.example.joseramos.lyricsappjlrf.presentation.utils;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.provider.Settings;
import android.support.v4.content.ContextCompat;

import javax.inject.Inject;

public class DeviceUtils {

    private static final String LOG_TAG = DeviceUtils.class.getName();
    private Context mContext;

    @Inject
    public DeviceUtils(Context context) {
        this.mContext = context;
    }

    /**
     * Detects the availability of a working network connection to open a
     * http/https connection.
     *
     * @return true if network is available, false otherwise.
     */
    public boolean isNetworkAvailable() {
        ConnectivityManager conMgr = (ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (conMgr != null) {
            NetworkInfo networkInfo = conMgr.getActiveNetworkInfo();
            return networkInfo != null;
        }
        return false;
    }

    /**
     * Returns a boolean value indicating whether the GPS
     * is enabled or not.
     *
     * @return True if GPS is enabled, false otherwise.
     */
    public boolean isGpsEnabled() {
        if (ContextCompat.checkSelfPermission(mContext, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            LocationManager locationManager = (LocationManager) mContext.getSystemService(Context.LOCATION_SERVICE);
            return locationManager != null && locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        }

        final int locationMode;
        try {
            locationMode = Settings.Secure.getInt(
                    mContext.getContentResolver(),
                    Settings.Secure.LOCATION_MODE);
        } catch (Settings.SettingNotFoundException e) {
            e.printStackTrace();
            return false;
        }
        switch (locationMode) {

            case Settings.Secure.LOCATION_MODE_HIGH_ACCURACY:
            case Settings.Secure.LOCATION_MODE_SENSORS_ONLY:
                return true;
            case Settings.Secure.LOCATION_MODE_BATTERY_SAVING:
            case Settings.Secure.LOCATION_MODE_OFF:
            default:
                return false;
        }
    }
}
