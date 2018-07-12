package com.shinhan.affiliatedconcerntest;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String AFFILIATED_CONCERN_SCHEME_VALUE = "affiliatedconcern";
    public static final String AFFILIATED_CONCERN_NAME = "ACName";
    public static final int REQUEST_CODE_GO_MENU = 1000;
    public static final int REQUEST_CODE_GO_LOGIN = 1001;


    private ImageView mMenuButton = null;
    private RelativeLayout mLoginStatus = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        mButtonJoin = findViewById(R.id.buttonJoin);
//        mButtonLogin = findViewById(R.id.buttonLogin);
//
//        mButtonJoin.setOnClickListener(this);
//        mButtonLogin.setOnClickListener(this);

        mMenuButton = findViewById(R.id.menuButton);
        mMenuButton.setOnClickListener(this);
        mLoginStatus = findViewById(R.id.loginStatus);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.menuButton:
                Intent intent = new Intent(this, MenuActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivityForResult(intent, REQUEST_CODE_GO_MENU);

                intent = null;
                break;
        }
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);

        doACSetting(intent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (RESULT_OK == resultCode) {
            switch (requestCode) {
                case REQUEST_CODE_GO_MENU:

                    break;
            }
        }
    }

    private void doACSetting(Intent intent) {
        if (null != intent) {
            Uri uri = intent.getData();

            String host = uri.getHost();
            String acName = uri.getQueryParameter(AFFILIATED_CONCERN_NAME);

            showToast(this, host + " from " + acName, Toast.LENGTH_LONG);

            if (null != host && host.equals("login"))
                mLoginStatus.setVisibility(View.VISIBLE);
        }
    }

    public void showToast(Context context, String message, int duration) {
        Toast t = Toast.makeText(context, message, duration);
        t.setGravity(Gravity.CENTER, 0, 0);
        t.show();
    }
}