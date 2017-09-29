package com.hayk.myapplication.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hayk.myapplication.R;
import com.hayk.myapplication.adapters.LoginInfoAdapter;
import com.hayk.myapplication.controllers.LoginInfoController;

/**
 * Created by User on 14.09.2017.
 */

public class LoginInfoFragment extends Fragment {

    public static LoginInfoFragment newInstance() {
        return new LoginInfoFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.registered_data_fragment, container, false);

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        LoginInfoAdapter adapter = new LoginInfoAdapter(LoginInfoController.getInstance().getRegisteredData(getActivity()), getActivity());
        recyclerView.setAdapter(adapter);

        return view;
    }

}
