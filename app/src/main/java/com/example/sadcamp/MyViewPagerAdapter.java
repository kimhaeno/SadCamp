package com.example.sadcamp;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.sadcamp.fragments.CallFragment;
import com.example.sadcamp.fragments.FreeFragment;
import com.example.sadcamp.fragments.GalleryFragment;

public class MyViewPagerAdapter extends FragmentStateAdapter {
    public MyViewPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0:
                return new CallFragment();
            case 1:
                return new GalleryFragment();
            case 2:
                return new FreeFragment();
            default:
                return new CallFragment();
        }
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
