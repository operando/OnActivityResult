package com.vanniktech.onactivityresult.sample;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.vanniktech.onactivityresult.sample.databinding.ActivityCameraBinding;

public class CameraActivity extends AppCompatActivity {

    public static final String RESULT_TAKE_PHOTO = "take_photo";

    private ActivityCameraBinding binding;

    public static Intent createIntent(Context context) {
        Intent i = new Intent(context, CameraActivity.class);
        return i;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_camera);

        binding.ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra(RESULT_TAKE_PHOTO, "take photo");
                setResult(Activity.RESULT_OK, intent);
                finish();
            }
        });
    }
}