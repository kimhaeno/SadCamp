package com.example.sadcamp;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CallAdapter extends RecyclerView.Adapter<CallAdapter.ViewHolder> {

    private ArrayList<ContactData> contactList;

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView nameView;
        private final TextView emailView;
        private final ImageView imgView;

        public ViewHolder(View view) {
            super(view);
            // Define click listener for the ViewHolder's View

            nameView = (TextView) view.findViewById(R.id.name);
            emailView = (TextView) view.findViewById(R.id.email);
            imgView = (ImageView) view.findViewById(R.id.imageview);
        }

        public TextView getNameView() {
            return nameView;
        }

        public TextView getEmailView() {
            return emailView;
        }

        public ImageView getImgView() {
            return imgView;
        }
    }

    /**
     * Initialize the dataset of the Adapter.
     *
     * @param dataSet String[] containing the data to populate views to be used
     * by RecyclerView.
     */
    public CallAdapter(ArrayList<ContactData> dataSet) {
        contactList = dataSet;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view, which defines the UI of the list item
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_view, viewGroup, false);

        return new ViewHolder(view);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        viewHolder.getNameView().setText(contactList.get(position).getName());
        viewHolder.getEmailView().setText(contactList.get(position).getMail());
        viewHolder.getImgView().setImageResource(contactList.get(position).getPic());
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return contactList.size();
    }
}
