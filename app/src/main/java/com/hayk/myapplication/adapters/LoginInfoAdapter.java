package com.hayk.myapplication.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hayk.myapplication.R;
import com.hayk.myapplication.model.LoginInfo;

import java.util.ArrayList;

/**
 * Created by User on 14.09.2017.
 */

public class LoginInfoAdapter extends RecyclerView.Adapter<LoginInfoAdapter.ViewHolder> {

    private ArrayList<LoginInfo> loginInfo;

    public LoginInfoAdapter(ArrayList<LoginInfo> dataLists) {
        this.loginInfo = dataLists;
    }

    @Override
    public LoginInfoAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_items, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.registeredEmail.setText(loginInfo.get(position).getRegisteredEmail());
        holder.registrationTime.setText(loginInfo.get(position).getRegistrationTime());

    }

    @Override
    public int getItemCount() {
        return loginInfo.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView registeredEmail;
        TextView registrationTime;

        ViewHolder(View itemView) {
            super(itemView);
            registeredEmail = (TextView) itemView.findViewById(R.id.registered_email);
            registrationTime = (TextView) itemView.findViewById(R.id.registration_time);
        }
    }
}
