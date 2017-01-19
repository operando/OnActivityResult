package com.vanniktech.onactivityresult.sample;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.location.Location;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.vanniktech.onactivityresult.sample.databinding.ActivityLocationSelectBinding;

public class LocationSelectActivity extends AppCompatActivity {

    public static final String RESULT_SELECT_LOCATION = "select_location";

    private ActivityLocationSelectBinding binding;

    public static Intent createIntent(Context context) {
        Intent i = new Intent(context, LocationSelectActivity.class);
        return i;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_location_select);

        binding.ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                Location location = new Location("");
                location.setLatitude(35.6777894);
                location.setLongitude(139.7691548);
                intent.putExtra(RESULT_SELECT_LOCATION, location);
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }
}
