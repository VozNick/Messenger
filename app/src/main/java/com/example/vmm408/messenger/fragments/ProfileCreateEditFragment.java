package com.example.vmm408.messenger.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.example.vmm408.messenger.R;
import com.example.vmm408.messenger.models.UserModel;

import butterknife.BindView;
import butterknife.OnClick;

public class ProfileCreateEditFragment extends BaseFragment {
    @BindView(R.id.login_ET)
    EditText login_ET;
    @BindView(R.id.password_ET)
    EditText password_ET;
    @BindView(R.id.name_ET)
    EditText name_ET;
    @BindView(R.id.last_name_ET)
    EditText last_name_ET;
    @BindView(R.id.age_ET)
    EditText age_ET;
    @BindView(R.id.about_ET)
    EditText about_ET;
    private AppCompatActivity appCompatActivity;
    private View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_profile_create_edit, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        databaseReference = database.getReference("users");
        this.appCompatActivity = super.appCompatActivity;
        this.view = super.view;
        tryArgs();
    }

    private void tryArgs() {
        try {
            if (getArguments().getBoolean("edit")) {
                setToWidgets();
            }
        } catch (Exception ignored) {
        }
    }

    private void setToWidgets() {
        login_ET.setText(currentUser.getLogin());
        password_ET.setText(currentUser.getPassword());
        name_ET.setText(currentUser.getName());
        last_name_ET.setText(currentUser.getLastName());
        age_ET.setText(String.valueOf(currentUser.getAge()));
        about_ET.setText(currentUser.getAbout());
    }

    @OnClick(R.id.save_Btn)
    public void save() {
        validation();
    }

    private void validation() {
        if (login_ET.getText().toString().toCharArray().length > 3 ||
                password_ET.getText().toString().toCharArray().length > 3) {
            ageCheck();
        } else {
            makeToast("login or password must be more than 3 chars ...");
        }
    }

    private void ageCheck() {
        if (age_ET == null) age_ET.setText(0);
        setDataBase();
    }

    private void setDataBase() {
        databaseReference.child(add(login_ET)).setValue(gson.toJson(initUser()));
//        initNewFragment(new ChatMessengerFragment());
        new ChatMessengerFragment()
                .initNewFragment(appCompatActivity, view, "ChatMessengerFragment");
    }

    private UserModel initUser() {
        currentUser = new UserModel(add(login_ET), add(password_ET),
                add(name_ET), add(last_name_ET), Integer.parseInt(add(age_ET)), add(about_ET));
        return currentUser;
    }

    private String add(EditText editText) {
        return editText.getText().toString();
    }

    @OnClick(R.id.cancel_Btn)
    public void cancel() {
//        initNewFragment(new AuthenticationFragment());
        new AuthenticationFragment()
                .initNewFragment(appCompatActivity, view, "AuthenticationFragment");
    }

    private void makeToast(String string) {
        Toast.makeText(getActivity(), string, Toast.LENGTH_SHORT).show();
    }
}
