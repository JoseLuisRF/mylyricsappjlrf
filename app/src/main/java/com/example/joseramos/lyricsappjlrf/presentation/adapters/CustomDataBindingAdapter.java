package com.example.joseramos.lyricsappjlrf.presentation.adapters;


import android.databinding.BindingAdapter;
import android.text.TextUtils;
import android.widget.ImageView;

import com.example.joseramos.lyricsappjlrf.R;
import com.squareup.picasso.Picasso;

public class CustomDataBindingAdapter {

    @BindingAdapter("imageUrl")
    public static void loadImage(ImageView imageView, String url) {
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
