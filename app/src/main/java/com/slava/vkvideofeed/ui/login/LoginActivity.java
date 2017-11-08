package com.slava.vkvideofeed.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Button;

import com.slava.vkvideofeed.R;
import com.slava.vkvideofeed.ui.base.BaseActivity;
import com.slava.vkvideofeed.ui.main.MainActivity;
import com.slava.vkvideofeed.util.LogUtil;
import com.vk.sdk.VKAccessToken;
import com.vk.sdk.VKCallback;
import com.vk.sdk.VKScope;
import com.vk.sdk.VKSdk;
import com.vk.sdk.api.VKError;
import com.vk.sdk.api.VKRequest;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoginActivity extends BaseActivity implements LoginMvp.View {

    private boolean isResumed = false;

    public static int LAYOUT = R.layout.activity_login;

    private static final String[] sMyScope = new String[]{
            VKScope.VIDEO,
            VKScope.NOHTTPS,
            VKScope.WALL,
            VKScope.FRIENDS
    };

    @BindView(R.id.auth_button)
    Button authButton;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);

        authButton.setOnClickListener(v -> VKSdk.login(this, sMyScope));

        VKSdk.wakeUpSession(this, new VKCallback<VKSdk.LoginState>() {
            @Override
            public void onResult(VKSdk.LoginState res) {
                if (isResumed) {
                    switch (res) {
                        case LoggedOut:
                            //showLogin();
                            LogUtil.info(this, "Logout");
                            break;
                        case LoggedIn:
                            //showLogout();
                            LogUtil.info(this, "Login");
                            break;
                        case Pending:
                            break;
                        case Unknown:
                            break;
                    }
                }
            }

            @Override
            public void onError(VKError error) {

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        VKCallback<VKAccessToken> callback = new VKCallback<VKAccessToken>() {
            @Override
            public void onResult(VKAccessToken res) {
                // User passed Authorization
                startMainActivity();
            }

            @Override
            public void onError(VKError error) {
                // User didn't pass Authorization
            }
        };

        if (!VKSdk.onActivityResult(requestCode, resultCode, data, callback)) {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    private void startMainActivity() {

        startActivity(new Intent(this, MainActivity.class));
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    public int getLayout() {
        return LAYOUT;
    }
}
