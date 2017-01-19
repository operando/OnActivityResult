package com.vanniktech.onactivityresult.sample;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.vanniktech.onactivityresult.sample.databinding.ActivityMySampleMainBinding;

import java.util.ArrayList;

import onactivityresult.ActivityResult;
import onactivityresult.ExtraString;
import onactivityresult.OnActivityResult;

public class MySampleMainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    private static final int REQUEST_SELECT_IMAGE = 1;

    private ActivityMySampleMainBinding binding;

    public static Intent createIntent(Context context) {
        Intent i = new Intent(context, MySampleMainActivity.class);
        return i;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_my_sample_main);

        binding.selectImages.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(ImageSelectActivity.createIntent(MySampleMainActivity.this), REQUEST_SELECT_IMAGE);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        ActivityResult.onResult(requestCode, resultCode, data).into(this);
    }

    @OnActivityResult(requestCode = REQUEST_SELECT_IMAGE)
    void onHumuHumu() {
        // resultCodesを設定しているメソッドが先に実行される
        Toast.makeText(this, "onHumuHumu", Toast.LENGTH_SHORT).show();
    }

    @OnActivityResult(requestCode = REQUEST_SELECT_IMAGE, resultCodes = RESULT_CANCELED)
    void onCancel() {
        Toast.makeText(this, "cancel select image¸", Toast.LENGTH_SHORT).show();
    }

    @OnActivityResult(requestCode = REQUEST_SELECT_IMAGE, resultCodes = ImageSelectActivity.RESULT_CODE_SELECT_IMAGE)
    void onSelectImage(Intent intent) {
        ArrayList<String> selectImages = intent.getStringArrayListExtra(ImageSelectActivity.RESULT_SELECT_IMAGES);
        Toast.makeText(this, "onSelectImage selectImages : " + selectImages, Toast.LENGTH_SHORT).show();
    }

    @OnActivityResult(requestCode = REQUEST_SELECT_IMAGE, resultCodes = ImageSelectActivity.RESULT_CODE_TAKE_PHOTO)
    void onTakePhoto(@ExtraString(name = ImageSelectActivity.RESULT_TAKE_PHOTO) final String takePhoto) {
        Toast.makeText(this, "onTakePhoto takePhoto : " + takePhoto, Toast.LENGTH_SHORT).show();
    }
}