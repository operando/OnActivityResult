// Generated code from OnActivityResult. Do not modify!
package com.vanniktech.onactivityresult.sample;

import android.content.Intent;
import android.location.Location;
import onactivityresult.IntentHelper;
import onactivityresult.internal.IOnActivityResult;

public class MySampleMainActivity$$OnActivityResult<T extends MySampleMainActivity> implements IOnActivityResult<T> {
  @Override
  public boolean onResult(final T t, final int requestCode, final int resultCode,
      final Intent intent) {
    boolean didHandle = false;
    if (requestCode == 1) {
      if (resultCode == 0) {
        t.onCancel();
      } else if (resultCode == 1) {
        t.onSelectImage(intent);
      } else if (resultCode == 2) {
        final String take_photoExtraString = IntentHelper.getExtraString(intent, "take_photo", "");
        t.onTakePhoto(take_photoExtraString);
      }
      t.onHumuHumu();
      didHandle = true;
    } else if (requestCode == 2) {
      if (resultCode == -1) {
        final Location select_locationExtraLocation = IntentHelper.getExtraParcelable(intent, "select_location", null);
        t.onSelectedLocation(select_locationExtraLocation);
        didHandle = true;
      }
    }
    return didHandle;
  }
}
