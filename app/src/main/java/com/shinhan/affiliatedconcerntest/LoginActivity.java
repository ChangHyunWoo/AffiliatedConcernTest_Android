package com.shinhan.affiliatedconcerntest;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;


public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView mLogin = null;
    private ImageView mJoin = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mLogin = findViewById(R.id.login);
        mLogin.setOnClickListener(this);

        mJoin = findViewById(R.id.join);
        mJoin.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        Intent acIntent = new Intent();

        acIntent.setAction(Intent.ACTION_VIEW);
        acIntent.addCategory(Intent.CATEGORY_DEFAULT);
        acIntent.addCategory(Intent.CATEGORY_BROWSABLE);

        String host = null;
        String acName = "ssg";

        switch (v.getId()) {
            case R.id.login:
                host = "login";
                break;

            case R.id.join:
                host = "join";
                break;
        }

        acIntent.setData(Uri.parse(MainActivity.AFFILIATED_CONCERN_SCHEME_VALUE + "://" + host + "?" + MainActivity.AFFILIATED_CONCERN_NAME + "=" + acName));

        try {
            startActivity(acIntent);
        }
        catch (ActivityNotFoundException e) {
            showToast(this, "ActivityNotFoundException~!!!", Toast.LENGTH_SHORT);
        }
        catch (Exception e) {
            showToast(this, e.toString(), Toast.LENGTH_SHORT);
        }
        finally {
            acIntent = null;

            setResult(RESULT_OK);
        }
    }

    public void showToast(Context context, String message, int duration) {
        Toast t = Toast.makeText(context, message, duration);
        t.setGravity(Gravity.CENTER, 0, 0);
        t.show();
    }
}