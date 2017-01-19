package com.vanniktech.onactivityresult.sample;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.vanniktech.onactivityresult.sample.databinding.ActivityImageSelectBinding;

import java.util.ArrayList;

import onactivityresult.ActivityResult;
import onactivityresult.ExtraString;
import onactivityresult.OnActivityResult;

public class ImageSelectActivity extends AppCompatActivity {

    public static final String RESULT_SELECT_IMAGES = "select_images";
    public static final String RESULT_TAKE_PHOTO = "take_photo";

    static final int RESULT_CODE_SELECT_IMAGE = Activity.RESULT_FIRST_USER;
    static final int RESULT_CODE_TAKE_PHOTO = Activity.RESULT_FIRST_USER + 1;

    private static final int REQUEST_TAKE_PHOTO = 1;

    private ActivityImageSelectBinding binding;

    public static Intent createIntent(Context context) {
        Intent i = new Intent(context, ImageSelectActivity.class);
        return i;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_image_select);

        binding.selectImages.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                ArrayList<String> images = new ArrayList<>();
                images.add("1");
                images.add("2");
                intent.putStringArrayListExtra(RESULT_SELECT_IMAGES, images);
                setResult(RESULT_CODE_SELECT_IMAGE, intent);
                finish();
            }
        });

        binding.takePhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(CameraActivity.createIntent(ImageSelectActivity.this), REQUEST_TAKE_PHOTO);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        ActivityResult.onResult(requestCode, resultCode, data).into(this);
    }

    @OnActivityResult(requestCode = REQUEST_TAKE_PHOTO, resultCodes = RESULT_CANCELED)
    void onCancelTakePhoto() {
        Toast.makeText(this, "onCancelTakePhoto", Toast.LENGTH_SHORT).show();
    }

    @OnActivityResult(requestCode = REQUEST_TAKE_PHOTO, resultCodes = RESULT_OK)
    void onTakePhoto(@ExtraString(name = CameraActivity.RESULT_TAKE_PHOTO) final String takePhoto) {
        Intent resultIntent = new Intent();
        resultIntent.putExtra(RESULT_TAKE_PHOTO, takePhoto);
        setResult(RESULT_CODE_TAKE_PHOTO, resultIntent);
        finish();
    }
}