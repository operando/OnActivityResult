package com.vanniktech.onactivityresult.sample;

import android.app.Activity;
import android.databinding.ObservableField;
import android.location.Location;

import onactivityresult.Extra;
import onactivityresult.OnActivityResult;

public class MySampleMainViewModel {

    //    public ObservableParcelable<Location> location = new ObservableParcelable<>();
    public ObservableField<String> location = new ObservableField<>("0,0");

    @OnActivityResult(requestCode = MySampleMainActivity.REQUEST_SELECT_LOCATION, resultCodes = Activity.RESULT_OK)
    void onSelectedLocation(@Extra(name = LocationSelectActivity.RESULT_SELECT_LOCATION) Location location) {
        this.location.set(location.toString());
    }
}