// Generated code from OnActivityResult. Do not modify!
package com.vanniktech.onactivityresult.sample;

import android.content.Intent;
import onactivityresult.IntentHelper;
import onactivityresult.internal.IOnActivityResult;

public class ImageSelectActivity$$OnActivityResult<T extends ImageSelectActivity> implements IOnActivityResult<T> {
  @Override
  public boolean onResult(final T t, final int requestCode, final int resultCode,
      final Intent intent) {
    boolean didHandle = false;
    if (requestCode == 1) {
      if (resultCode == -1) {
        final String take_photoExtraString = IntentHelper.getExtraString(intent, "take_photo", "");
        t.onTakePhoto(take_photoExtraString);
        didHandle = true;
      } else if (resultCode == 0) {
        t.onCancelTakePhoto();
        didHandle = true;
      }
    }
    return didHandle;
  }
}
