package com.example.joseramos.lyricsappjlrf.presentation.utils;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Environment;
import android.provider.Settings;
import android.support.v4.content.ContextCompat;
import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

import javax.inject.Inject;

import static com.example.joseramos.lyricsappjlrf.data.database.AppDataBaseKt.DATABASE_NAME;

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


    /**
     * Exports Data Base
     */
    public void exportDataBases() throws IOException {
        writeToSD(mContext, DATABASE_NAME);
    }

    /**
     * Exports the data base file to /sdcard folder with the name mac_technique_test.db
     * This method only works on Debug Mode
     *
     * @param context Application Context
     * @throws IOException
     */
    private void writeToSD(Context context, String databaseName) throws IOException {
        String DB_PATH;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            DB_PATH = context.getFilesDir().getAbsolutePath().replace("files", "databases") + File.separator;
        } else {
            DB_PATH = context.getFilesDir().getPath() + context.getPackageName() + "/databases/";
        }
        File sd = Environment.getExternalStorageDirectory();

        if (sd.canWrite()) {
            String currentDBPath = databaseName;
            String backupDBPath = "demo_" + databaseName;
            File currentDB = new File(DB_PATH, currentDBPath);
            File backupDB = new File(sd, backupDBPath);

            if (currentDB.exists()) {
                FileChannel src = new FileInputStream(currentDB).getChannel();
                FileChannel dst = new FileOutputStream(backupDB).getChannel();
                dst.transferFrom(src, 0, src.size());
                src.close();
                dst.close();
                Log.d("JLRF", "exported-path:" + backupDB.getPath());
            }
        }
    }

}
