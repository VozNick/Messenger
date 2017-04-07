package com.example.vmm408.messenger.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.vmm408.messenger.models.UserModel;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public class BaseFragment extends Fragment {
    private Unbinder unbinder;
    protected View view;
    protected AppCompatActivity appCompatActivity;
    protected FirebaseDatabase database;
    protected DatabaseReference databaseReference;
    protected Gson gson = new GsonBuilder().create();
    public static UserModel currentUser;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        unbinder = ButterKnife.bind(this, view);
        database = FirebaseDatabase.getInstance();
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        unbinder.unbind();
        super.onDestroyView();
    }

    public void initNewFragment(AppCompatActivity appCompatActivity, View view, String tag) {
        this.appCompatActivity = appCompatActivity;
        this.view = view;
        appCompatActivity.getSupportFragmentManager().beginTransaction()
                .replace(view.getId(), this, tag).commit();
    }
//
//    public void initNewFragment(Fragment fragment) {
//        appCompatActivity.getSupportFragmentManager().beginTransaction()
//                .replace(view.getId(), fragment).commit();
//    }
}
