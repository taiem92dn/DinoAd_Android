package vn.dinosys.dinoad.app;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;



/**
 * Created by htsi.
 * Since: 7/4/16 on 2:23 PM
 * Project: DinoAd
 */
public class LockScreenReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context pContext, Intent pIntent) {
        if (pIntent.getAction().equals(Intent.ACTION_SCREEN_OFF)) {
            Intent lockIntent = new Intent(Constants.LOCK_SCREEN_ACTION);
            lockIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            pContext.startActivity(lockIntent);
        }
    }

}
