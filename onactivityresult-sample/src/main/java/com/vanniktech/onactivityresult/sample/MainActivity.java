package com.vanniktech.onactivityresult.sample;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import onactivityresult.ActivityResult;
import onactivityresult.IntentData;
import onactivityresult.OnActivityResult;

public class MainActivity extends AppCompatActivity {
    private static final int REQUEST_CODE_TEST_ACTIVITY   = 3;
    private static final int REQUEST_CODE_PICK_IMAGE      = 4;
    private static final int REQUEST_CODE_TEST_2_ACTIVITY = 5;

    @Bind(R.id.toolbar) Toolbar toolbar;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        this.setSupportActionBar(toolbar);
    }

    @Override
    protected void onActivityResult(final int requestCode, final int resultCode, final Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        ActivityResult.onResult(requestCode, resultCode, data).into(this);
    }

    @OnClick(R.id.start_activity_for_result_button)
    void onStartActivityForResult() {
        final Intent intent = new Intent(this, ForResultActivity.class);
        this.startActivityForResult(intent, REQUEST_CODE_TEST_ACTIVITY);
    }

    @OnClick(R.id.start_activity_for_result_2_button)
    void onStartActivityForResult2() {
        final Intent intent = new Intent(this, ForResultActivity.class);
        this.startActivityForResult(intent, REQUEST_CODE_TEST_2_ACTIVITY);
    }

    @OnClick(R.id.pick_image_from_gallery)
    void onPickImageFromGallery() {
        final Intent chooserIntent = new Intent(Intent.ACTION_GET_CONTENT);
        chooserIntent.setType("image/*");

        this.startActivityForResult(chooserIntent, REQUEST_CODE_PICK_IMAGE);
    }

    @OnActivityResult(requestCode = REQUEST_CODE_TEST_ACTIVITY)
    void onActivityResultTestActivity(final int resultCode) {
        Toast.makeText(this, "Got activity for result " + resultCode, Toast.LENGTH_SHORT).show();
    }

    @OnActivityResult(requestCode = REQUEST_CODE_TEST_2_ACTIVITY)
    void onActivityResultTest2Activity(final int resultCode, final Intent intent) {
        Toast.makeText(this, "Got activity for result 2 " + resultCode + " with intent " + intent, Toast.LENGTH_SHORT).show();
    }

    @OnActivityResult(requestCode = REQUEST_CODE_PICK_IMAGE)
    void onActivityResultPickImage(final int resultCode, final Intent intent, @IntentData final Uri uri) {
        Toast.makeText(this, "Got image for result " + resultCode + " with intent " + intent + " and uri " + uri, Toast.LENGTH_SHORT).show();
    }
}