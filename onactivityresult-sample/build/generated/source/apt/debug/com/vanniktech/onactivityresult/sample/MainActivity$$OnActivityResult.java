// Generated code from OnActivityResult. Do not modify!
package com.vanniktech.onactivityresult.sample;

import android.content.Intent;
import android.net.Uri;
import onactivityresult.IntentHelper;
import onactivityresult.internal.IOnActivityResult;

public class MainActivity$$OnActivityResult<T extends MainActivity> implements IOnActivityResult<T> {
  @Override
  public boolean onResult(final T t, final int requestCode, final int resultCode,
      final Intent intent) {
    boolean didHandle = false;
    if (requestCode == 3) {
      if (resultCode == -1) {
        t.onActivityResultTestActivityOk();
        didHandle = true;
      } else if (resultCode == 0) {
        t.onActivityResultTestActivityCanceled();
        didHandle = true;
      } else if (resultCode == 2) {
        t.onActivityResultTestActivityUserDefined(resultCode);
        didHandle = true;
      }
    } else if (requestCode == 4) {
      final Uri intentDataNullable = IntentHelper.getIntentDataNullable(intent);
      t.onActivityResultPickImage(resultCode, intent, intentDataNullable);
      didHandle = true;
    } else if (requestCode == 5) {
      t.onActivityResultTest2Activity(resultCode, intent);
      didHandle = true;
    }
    return didHandle;
  }
}
