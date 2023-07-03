package com.example.sadcamp;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import androidx.fragment.app.DialogFragment;
import androidx.appcompat.app.AlertDialog;


public class MyDialogFragment extends DialogFragment {

    private static final String ARG_POSITION = "position";

    public static MyDialogFragment newInstance(int position){
        MyDialogFragment fragment = new MyDialogFragment();
        Bundle args =new Bundle();
        args.putInt(ARG_POSITION, position);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        // Get the selected image position from GalleryAdapter
        int position = getArguments().getInt("position");

        // Get the user information based on position
        // You can get data from the database
        String userInfo = "User info for image position: " + position;

        builder.setTitle("User Information")
                .setMessage(userInfo)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User clicked OK button
                    }
                });

        return builder.create();
    }
}
