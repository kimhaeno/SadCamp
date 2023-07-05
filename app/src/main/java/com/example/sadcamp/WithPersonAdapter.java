/*package com.example.sadcamp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class WithPersonAdapter extends RecyclerView.Adapter<WithPersonAdapter.PersonViewHolder> {
    private ArrayList<String> nameList;
    private boolean[] checkedStates;  // checked states of checkboxes

    public static class PersonViewHolder extends RecyclerView.ViewHolder {
        public TextView personName;
        public CheckBox checkBox;  // Checkbox added

        public PersonViewHolder(@NonNull View itemView) {
            super(itemView);
            personName = itemView.findViewById(R.id.with_person_name);
            checkBox = itemView.findViewById(R.id.checkbox);  // Assuming you have a checkbox with id "checkbox"
        }
    }

    public WithPersonAdapter(ArrayList<String> nameList) {
        this.nameList = nameList;
        this.checkedStates = new boolean[nameList.size()];
    }

    @NonNull
    @Override
    public PersonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_with_person, parent, false);
        return new PersonViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull PersonViewHolder holder, int position) {
        holder.personName.setText(nameList.get(position));
        holder.checkBox.setOnCheckedChangeListener(null);
        holder.checkBox.setChecked(checkedStates[position]);

        holder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                int pos = holder.getAdapterPosition();
                if (pos != RecyclerView.NO_POSITION) {
                    checkedStates[pos] = isChecked;
                }
            }
        });
    }


    @Override
    public int getItemCount() {
        return nameList.size();
    }

    public ArrayList<String> getCheckedNames() {
        ArrayList<String> checkedNames = new ArrayList<>();
        for (int i = 0; i < nameList.size(); i++) {
            if (checkedStates[i]) {
                checkedNames.add(nameList.get(i));
            }
        }
        return checkedNames;
    }
}*/
