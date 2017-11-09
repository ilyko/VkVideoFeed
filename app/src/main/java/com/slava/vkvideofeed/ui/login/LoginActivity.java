package com.slava.vkvideofeed.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Button;

import com.slava.vkvideofeed.R;
import com.slava.vkvideofeed.ui.base.BaseActivity;
import com.slava.vkvideofeed.ui.main.MainActivity;
import com.vk.sdk.VKAccessToken;
import com.vk.sdk.VKCallback;
import com.vk.sdk.VKScope;
import com.vk.sdk.VKSdk;
import com.vk.sdk.api.VKError;

import butterknife.BindView;

public class LoginActivity extends BaseActivity implements LoginMvp.View {


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
        authButton.setOnClickListener(v -> VKSdk.login(this, sMyScope));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        VKCallback<VKAccessToken> callback = new VKCallback<VKAccessToken>() {
            @Override
            public void onResult(VKAccessToken res) {
                Intent intent = new Intent(LoginActivity.this,
                        MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP |
                        Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
            }

            @Override
            public void onError(VKError error) {

            }
        };

        if (!VKSdk.onActivityResult(requestCode, resultCode, data, callback)) {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    @Override
    public int getLayout() {
        return LAYOUT;
    }
}
