package com.example.sadcamp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class GalleryAdapter extends BaseAdapter {

    private Context mContext;

    LayoutInflater inflater;

    public int[] photoArray;



    public GalleryAdapter(Context mContext, int[] photoArray){
        this.mContext = mContext;
        this.photoArray = photoArray;
    }

    @Override
    public int getCount() {
        return photoArray.length;
    }

    @Override
    public Object getItem(int i){
        return photoArray[i];
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        ImageView imageView = new ImageView(mContext);
        imageView.setImageResource(photoArray[i]);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        imageView.setLayoutParams(new ViewGroup.LayoutParams(340, 350));

        return imageView;
    }

}
