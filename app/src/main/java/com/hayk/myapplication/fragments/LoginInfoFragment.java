package com.hayk.myapplication.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hayk.myapplication.R;
import com.hayk.myapplication.adapters.LoginInfoAdapter;
import com.hayk.myapplication.controllers.LoginInfoController;
import com.hayk.myapplication.interfaces.OnItemClickListener;

/**
 * Created by User on 14.09.2017.
 */

public class LoginInfoFragment extends Fragment implements OnItemClickListener {

    private TextView showTextView;

    public static LoginInfoFragment newInstance() {
        return new LoginInfoFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.registered_data_fragment, container, false);

        showTextView = (TextView) view.findViewById(R.id.showText);

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        LoginInfoAdapter adapter = new LoginInfoAdapter(LoginInfoController.getInstance().getRegisteredData(getActivity()), getActivity());
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(this);

        return view;
    }

    @Override
    public void onItemClick(int position) {
        String selectedEmail = LoginInfoController.getInstance().getRegisteredData(getActivity()).get(position).getRegisteredEmail();
        showTextView.setText(getString(R.string.selected_email) + selectedEmail);
    }
}
