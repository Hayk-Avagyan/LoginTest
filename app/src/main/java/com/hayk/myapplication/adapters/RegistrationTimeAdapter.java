package com.hayk.myapplication.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hayk.myapplication.R;

import java.util.ArrayList;

/**
 * Created by User on 28.09.2017.
 */

public class RegistrationTimeAdapter extends RecyclerView.Adapter<RegistrationTimeAdapter.ViewHolder> {

    private ArrayList<String> timeList;

    public RegistrationTimeAdapter(ArrayList<String> list) {
        this.timeList = list;
    }

    @Override
    public RegistrationTimeAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_time_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.registrationTimeView.setText(timeList.get(position));
    }

    @Override
    public int getItemCount() {
        return timeList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView registrationTimeView;

        ViewHolder(View itemView) {
            super(itemView);
            registrationTimeView = (TextView) itemView.findViewById(R.id.registration_time_list);
        }
    }
}


