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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import butterknife.BindView;
import butterknife.OnClick;

public class AuthenticationFragment extends BaseFragment implements ValueEventListener {
    @BindView(R.id.login_ET)
    EditText login_ET;
    @BindView(R.id.password_ET)
    EditText password_ET;
    private AppCompatActivity appCompatActivity;
    private View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_authentication, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.appCompatActivity = super.appCompatActivity;
        this.view = super.view;
    }

    @OnClick(R.id.login_Btn)
    public void login() {
        validation();
    }

    private void validation() {
        if (login_ET.getText().toString().toCharArray().length > 3 ||
                password_ET.getText().toString().toCharArray().length > 3) {
            getDataBase();
        } else {
            makeToast("login or password must be more than 3 chars ...");
        }
    }

    private void getDataBase() {
        databaseReference = database.getReference("users").child(login_ET.getText().toString());
        databaseReference.addListenerForSingleValueEvent(this);
    }

    @Override
    public void onDataChange(DataSnapshot dataSnapshot) {
        try {
            currentUser = gson.fromJson(
                    String.valueOf(dataSnapshot.getValue().toString()), UserModel.class);
            checkPassword();
        } catch (NullPointerException e) {
            makeToast("invalid login / password");
        }
    }

    private void checkPassword() {
        if (currentUser.getPassword().equals(password_ET.getText().toString())) {
//            initNewFragment(appCompatActivity, view, new ChatMessengerFragment());
            new ChatMessengerFragment()
                    .initNewFragment(appCompatActivity, view, "ChatMessengerFragment");
        } else {
            makeToast("invalid login / password");
        }
    }

    @Override
    public void onCancelled(DatabaseError databaseError) {
    }

    private void makeToast(String string) {
        Toast.makeText(getActivity(), string, Toast.LENGTH_SHORT).show();
    }

    @OnClick(R.id.reg_Button)
    public void registration() {
//        initNewFragment(appCompatActivity, view, new ProfileCreateEditFragment());
        new ProfileCreateEditFragment()
                .initNewFragment(appCompatActivity, view, "ProfileCreateEditFragment");
    }
}
