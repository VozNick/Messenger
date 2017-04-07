package com.example.vmm408.messenger.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.vmm408.messenger.R;

import butterknife.BindView;
import butterknife.OnClick;

public class ProfileViewFragment extends BaseFragment {
    @BindView(R.id.name_TV)
    TextView name_TV;
    @BindView(R.id.last_name_TV)
    TextView last_name_TV;
    @BindView(R.id.age_TV)
    TextView age_TV;
    @BindView(R.id.about_TV)
    TextView about_TV;
    private AppCompatActivity appCompatActivity;
    private View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_profile_view, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.appCompatActivity = super.appCompatActivity;
        this.view = super.view;
        setToWidgets();
    }

    private void setToWidgets() {
        name_TV.setText(currentUser.getName());
        last_name_TV.setText(currentUser.getLastName());
        age_TV.setText(String.valueOf(currentUser.getAge()));
        about_TV.setText(currentUser.getAbout());
    }

    @OnClick(R.id.edit_Btn)
    public void edit() {
        Bundle bundle = new Bundle();
        bundle.putBoolean("edit", true);
        ProfileCreateEditFragment profileCreateEditFragment = new ProfileCreateEditFragment();
        profileCreateEditFragment.setArguments(bundle);
//        initNewFragment(profileCreateEditFragment);
        new ProfileCreateEditFragment()
                .initNewFragment(appCompatActivity, view, "ProfileCreateEditFragment");
    }
}
