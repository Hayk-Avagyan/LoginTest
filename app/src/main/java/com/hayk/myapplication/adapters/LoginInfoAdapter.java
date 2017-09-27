package com.hayk.myapplication.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hayk.myapplication.R;
import com.hayk.myapplication.interfaces.OnItemClickListener;
import com.hayk.myapplication.model.UserInfo;

import java.util.ArrayList;

/**
 * Created by User on 14.09.2017.
 */

public class LoginInfoAdapter extends RecyclerView.Adapter<LoginInfoAdapter.ViewHolder> {

    private final String TAG = getClass().getSimpleName();
    private ArrayList<UserInfo> userInfo;
    private OnItemClickListener onItemClickListener;
    private Context context;

    public LoginInfoAdapter(ArrayList<UserInfo> dataLists, Context context) {
        this.userInfo = dataLists;
        this.context = context;
    }

    @Override
    public LoginInfoAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.recycler_view_items, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.registeredEmail.setText(userInfo.get(position).getRegisteredEmail());
        holder.registrationTime.setText(userInfo.get(position).getRegistrationTime());

    }

    @Override
    public int getItemCount() {
        return userInfo.size();
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView registeredEmail;
        TextView registrationTime;

        ViewHolder(final View itemView) {
            super(itemView);
            registeredEmail = (TextView) itemView.findViewById(R.id.registered_email);
            registrationTime = (TextView) itemView.findViewById(R.id.registration_time);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickListener.onItemClick(getAdapterPosition());
                    /*FragmentManager manager = ((Activity) itemView.getContext()).getFragmentManager();
                    manager.beginTransaction()
                            .setCustomAnimations(R.animator.slide_right_enter, R.animator.slide_left_exit, R.animator.slide_left_enter, R.animator.slide_right_exit)
                            .replace(R.id.fragment_container, RegisteredUserProfileFragment.newInstance(email))
                            .addToBackStack(TAG)
                            .commit();*/
                }
            });
        }
    }
}
