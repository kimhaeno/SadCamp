package com.example.sadcamp;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public class GallaryAdapter extends BaseAdapter {

    private Context mContext;

    public int[] imageArray = {
            R.drawable.a,R.drawable.b,R.drawable.c,
            R.drawable.d,R.drawable.e
    };

    public GallaryAdapter(Context mContext){
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return imageArray.length;
    }

    @Override
    public Object getItem(int position){
        return imageArray[position];
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        return null;
    }
}
