package com.vanniktech.onactivityresult.sample;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.vanniktech.onactivityresult.sample.databinding.ActivitySampleSelectBinding;

public class SampleSelectActivity extends AppCompatActivity {

    private ActivitySampleSelectBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_sample_select);

        binding.mySample.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(MySampleMainActivity.createIntent(SampleSelectActivity.this));
            }
        });

        binding.vanniktechSample.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(MainActivity.createIntent(SampleSelectActivity.this));
            }
        });
    }
}