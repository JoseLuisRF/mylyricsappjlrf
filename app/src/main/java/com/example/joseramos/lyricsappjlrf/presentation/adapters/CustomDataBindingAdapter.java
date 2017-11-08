package com.example.joseramos.lyricsappjlrf.presentation.adapters;


import android.databinding.BindingAdapter;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Environment;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.widget.ImageView;

import com.example.joseramos.lyricsappjlrf.R;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.io.File;
import java.io.FileOutputStream;

public class CustomDataBindingAdapter {

    @BindingAdapter("imageUrl")
    public static void loadImage(final ImageView imageView, final String url) {
        if (!TextUtils.isEmpty(url)) {
            Picasso.with(imageView.getContext())
                    .load(url)
                    .placeholder(R.color.colorPrimary)
                    .error(android.R.color.holo_red_dark)
                    .fit()
                    .into(imageView);
        }
    }
}
