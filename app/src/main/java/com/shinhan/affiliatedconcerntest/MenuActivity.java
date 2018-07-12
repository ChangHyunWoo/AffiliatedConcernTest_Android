package com.shinhan.affiliatedconcerntest;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

public class MenuActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView mLoginButton = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        mLoginButton = findViewById(R.id.loginButton);
        mLoginButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.loginButton:
                Intent intent = new Intent(this, LoginActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
                startActivityForResult(intent, MainActivity.REQUEST_CODE_GO_LOGIN);

                intent = null;
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (RESULT_OK == resultCode) {
            switch (requestCode) {
                case MainActivity.REQUEST_CODE_GO_LOGIN:
                    setResult(RESULT_OK);
                    finish();
                    break;
            }
        }
    }
}