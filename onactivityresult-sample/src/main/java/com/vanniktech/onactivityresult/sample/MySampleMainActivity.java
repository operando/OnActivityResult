package com.vanniktech.onactivityresult.sample;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.location.Location;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.vanniktech.onactivityresult.sample.databinding.ActivityMySampleMainBinding;

import java.util.ArrayList;

import onactivityresult.ActivityResult;
import onactivityresult.Extra;
import onactivityresult.ExtraString;
import onactivityresult.OnActivityResult;

public class MySampleMainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    private static final int REQUEST_SELECT_IMAGE = 1;
    static final int REQUEST_SELECT_LOCATION = 2;

    private ActivityMySampleMainBinding binding;
    private MySampleMainViewModel viewModel = new MySampleMainViewModel();

    public static Intent createIntent(Context context) {
        Intent i = new Intent(context, MySampleMainActivity.class);
        return i;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_my_sample_main);
        binding.setViewModel(viewModel);

        binding.selectImages.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(ImageSelectActivity.createIntent(MySampleMainActivity.this), REQUEST_SELECT_IMAGE);
            }
        });

        binding.selectLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(LocationSelectActivity.createIntent(MySampleMainActivity.this), REQUEST_SELECT_LOCATION);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        ActivityResult.onResult(requestCode, resultCode, data).into(this);
        ActivityResult.onResult(requestCode, resultCode, data).into(viewModel);
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

    @OnActivityResult(requestCode = REQUEST_SELECT_LOCATION, resultCodes = Activity.RESULT_OK)
    void onSelectedLocation(@Extra(name = LocationSelectActivity.RESULT_SELECT_LOCATION) Location location) {
        Toast.makeText(this, "onSelectedLocation location: " + location, Toast.LENGTH_SHORT).show();
    }
}