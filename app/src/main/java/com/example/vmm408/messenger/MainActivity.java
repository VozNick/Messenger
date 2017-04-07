package com.example.vmm408.messenger;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.RelativeLayout;

import com.example.vmm408.messenger.fragments.AuthenticationFragment;
import com.example.vmm408.messenger.fragments.BaseFragment;
import com.example.vmm408.messenger.fragments.ChatMessengerFragment;
import com.example.vmm408.messenger.fragments.ProfileCreateEditFragment;
import com.example.vmm408.messenger.fragments.ProfileViewFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.activity_main_container)
    RelativeLayout activity_main_container;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        new AuthenticationFragment()
                .initNewFragment(this, activity_main_container, "AuthenticationFragment");
    }

//    @Override
//    public boolean onPrepareOptionsMenu(Menu menu) {
//        if (BaseFragment.currentUser == null) {
//            menu.setGroupVisible(0, false);
//        } else {
//            menu.setGroupVisible(0, true);
//        }
//        return true;
//    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (R.id.profile_item == item.getItemId())
            new ProfileViewFragment()
                    .initNewFragment(this, activity_main_container, "ProfileViewFragment");
        return true;
    }

    @Override
    public void onBackPressed() {
        if (currentFrag("ChatMessengerFragment") != null) {
            new AuthenticationFragment()
                    .initNewFragment(this, activity_main_container, "AuthenticationFragment");
        } else if (currentFrag("ProfileCreateEditFragment") != null) {
            checkNewOrEdit();
        } else if (currentFrag("ProfileViewFragment") != null) {
            new ChatMessengerFragment()
                    .initNewFragment(this, activity_main_container, "ChatMessengerFragment");
        } else {
            super.onBackPressed();
        }
    }

    private void checkNewOrEdit() {
        try {
            if (new ProfileCreateEditFragment().getArguments().getBoolean("edit")) {
                new ProfileViewFragment()
                        .initNewFragment(this, activity_main_container, "ProfileViewFragment");
            }
        } catch (Exception ignored) {
            new AuthenticationFragment()
                    .initNewFragment(this, activity_main_container, "AuthenticationFragment");
        }
    }

    private Fragment currentFrag(String tag) {
        return getSupportFragmentManager().findFragmentByTag(tag);
    }

    @Override
    protected void onDestroy() {
        BaseFragment.currentUser = null;

        super.onDestroy();
    }
}
