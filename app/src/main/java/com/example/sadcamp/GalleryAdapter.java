package com.example.sadcamp;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

public class GalleryAdapter extends BaseAdapter {

    private Context mContext;
    private ArrayList<Bitmap> mImages;  // 이미지를 저장하는 리스트

    public GalleryAdapter(Context context, ArrayList<Bitmap> images) {
        this.mContext = context;
        this.mImages = images;
    }

    public View getView(final int position, View convertView, ViewGroup parent){
        ImageView imageView;

        if (convertView == null){
            imageView = new ImageView(mContext);
            imageView.setLayoutParams(new GridView.LayoutParams(85, 85));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setPadding(8,8,8,8);
        } else {
            imageView =(ImageView) convertView;
        }

        imageView.setImageBitmap(mImages.get(position));
        imageView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                // here position is the image position in the grid view.
                // handle your click event here.
                // you can show your dialog from here.

                Bundle bundle =new Bundle();
                bundle.putInt("position", position); //key랑 value

                MyDialogFragment dialog = new MyDialogFragment();
                dialog.setArguments(bundle);
                dialog.show(((FragmentActivity)mContext).getSupportFragmentManager(), "myDialog");
            }
        });
        return imageView;
    }


    @Override
    public int getCount() {
        return mImages.size();
    }

    @Override
    public Object getItem(int position) {
        return mImages.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

}
