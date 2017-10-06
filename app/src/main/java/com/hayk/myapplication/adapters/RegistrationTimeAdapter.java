package com.hayk.myapplication.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hayk.myapplication.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;

/**
 * Created by User on 28.09.2017.
 */

public class RegistrationTimeAdapter extends RecyclerView.Adapter<RegistrationTimeAdapter.ViewHolder> {

    private ArrayList<Long> timeList;

    public RegistrationTimeAdapter(ArrayList<Long> list) {
        this.timeList = list;
    }

    @Override
    public RegistrationTimeAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_time_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        long date = timeList.get(position);
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy  HH:mm:ss", Locale.getDefault());
        String currentTime = dateFormat.format(date);

        holder.registrationTimeView.setText(currentTime);
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


