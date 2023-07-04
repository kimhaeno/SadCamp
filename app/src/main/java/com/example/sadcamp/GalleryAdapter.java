package com.example.sadcamp;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import java.util.ArrayList;

public class GalleryAdapter extends BaseAdapter {

    private Context mContext;
    private ArrayList<Bitmap> mImages;  // 이미지 fileneme을 저장하는 리스트

    public GalleryAdapter(Context context, ArrayList<Bitmap> images) {
        this.mContext = context;
        this.mImages = images;
    }
    public void deleteImage(int position) {
        // 해당 위치의 사진을 삭제하는 작업 수행
        // 예를 들어, 이미지 리스트에서 해당 위치의 사진을 제거하고 어댑터를 갱신합니다.
        mImages.remove(position);
        notifyDataSetChanged();
    }


    public View getView(final int position, View convertView, ViewGroup parent){
        ImageView imageView;

        if (convertView == null){
            imageView = new ImageView(mContext);
            imageView.setLayoutParams(new GridView.LayoutParams(330, 330));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setPadding(8,8,8,8);
        } else {
            imageView =(ImageView) convertView;
        }

        imageView.setImageBitmap(mImages.get(position));



        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, PhotoActivity.class);

                intent.putExtra("position", position);
                mContext.startActivity(intent);
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
