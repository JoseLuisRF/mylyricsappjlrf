package com.example.joseramos.lyricsappjlrf.presentation.utils;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;

import javax.inject.Inject;

public class PermissionsManager {
    public static final int LOCATION_PERMISSION_REQUEST_CODE = 154;
    public static final int MEDIA_PERMISSION_REQUEST_CODE = 153;

    private Context mContext;

    @Inject
    public PermissionsManager(Context context) {
        this.mContext = context;
    }

    /**
     * Method to check if the app has permission to access location
     *
     * @return true if the location permission has been granted, false otherwise.
     */
    public boolean isLocationPermissionGranted() {
        boolean isPermissionGranted = false;

        if (isPermissionGranted(mContext, Manifest.permission.ACCESS_COARSE_LOCATION)
                && isPermissionGranted(mContext, Manifest.permission.ACCESS_FINE_LOCATION)) {
            isPermissionGranted = true;
        }

        return isPermissionGranted;
    }

    /**
     * Method to check if the app has permission to access External Storage
     *
     * @return True if Storage permission is granted, false otherwise.
     */
    public boolean isStoragePermissionGranted() {
        boolean isPermissionGranted = false;

        if (isPermissionGranted(mContext, Manifest.permission.READ_EXTERNAL_STORAGE)
                && isPermissionGranted(mContext, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            isPermissionGranted = true;
        }

        return isPermissionGranted;
    }

    /**
     * Utility method to check if a particular permission has been granted
     *
     * @param context    The application context
     * @param permission A string key identifying the android permission.
     * @return True if the permission has been granted, false otherwise.
     */
    public static boolean isPermissionGranted(Context context, String permission) {
        return ContextCompat.checkSelfPermission(context, permission) == PackageManager.PERMISSION_GRANTED;
    }

    public boolean shouldExplainStoragePermission(Activity activity) {
        return ActivityCompat.shouldShowRequestPermissionRationale(activity, Manifest.permission.READ_EXTERNAL_STORAGE)
                && ActivityCompat.shouldShowRequestPermissionRationale(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE);
    }

    public boolean shouldExplainLocationPermission(Activity activity) {
        return ActivityCompat.shouldShowRequestPermissionRationale(activity, Manifest.permission.ACCESS_COARSE_LOCATION)
                && ActivityCompat.shouldShowRequestPermissionRationale(activity, Manifest.permission.ACCESS_FINE_LOCATION);
    }

    public void showLocationPermissionsDialog(final Fragment fragment, final int requestCode) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            return;
        }


        if (ContextCompat.checkSelfPermission(fragment.getContext().getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                || ContextCompat.checkSelfPermission(fragment.getContext().getApplicationContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {


            fragment.requestPermissions(
                    new String[]{
                            Manifest.permission.ACCESS_COARSE_LOCATION,
                            Manifest.permission.ACCESS_FINE_LOCATION},
                    requestCode);
        }
    }

    public void showLocationPermissionsDialog(final Activity activity, final int requestCode) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            return;
        }


        if (ContextCompat.checkSelfPermission(activity.getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                || ContextCompat.checkSelfPermission(activity.getApplicationContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {


            activity.requestPermissions(
                    new String[]{
                            Manifest.permission.ACCESS_COARSE_LOCATION,
                            Manifest.permission.ACCESS_FINE_LOCATION},
                    requestCode);
        }
    }

    public void showMediaPermissionsDialog(final Fragment fragment, final int requestCode) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            return;
        }


        if (ContextCompat.checkSelfPermission(fragment.getContext(), Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED
                || ContextCompat.checkSelfPermission(fragment.getContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {

            fragment.requestPermissions(
                    new String[]{
                            Manifest.permission.READ_EXTERNAL_STORAGE,
                            Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    requestCode);
        }
    }

    public void showMediaPermissionsDialog(Activity context, final int requestCode) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            return;
        }


        if (ContextCompat.checkSelfPermission(context, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED
                || ContextCompat.checkSelfPermission(context, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {

            context.requestPermissions(
                    new String[]{
                            Manifest.permission.READ_EXTERNAL_STORAGE,
                            Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    requestCode);
        }
    }
}
