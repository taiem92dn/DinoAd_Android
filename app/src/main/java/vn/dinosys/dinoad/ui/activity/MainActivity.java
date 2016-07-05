package vn.dinosys.dinoad.ui.activity;

import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.r0adkll.slidr.Slidr;

import vn.dinosys.dinoad.R;
import vn.dinosys.dinoad.app.DinoAdminReceiver;
import vn.dinosys.dinoad.app.LockScreenService;
import vn.dinosys.dinoad.ui.activity.base.BaseActivity;
import vn.dinosys.dinoad.ui.activity.lockscreen.LockScreenActivity;

public class MainActivity extends BaseActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //int primary = getResources().getColor(R.color.colorPrimary);
        //int secondary = getResources().getColor(R.color.colorPrimaryDark);
    }

    public void goToLockScreen(View pView) {
        //startActivity(new Intent(this, LockScreenActivity.class));
        ComponentName componentName = new ComponentName(this, DinoAdminReceiver.class);

        Intent intent = new Intent(DevicePolicyManager.ACTION_ADD_DEVICE_ADMIN);
        intent.putExtra(DevicePolicyManager.EXTRA_DEVICE_ADMIN, componentName);
        startActivityForResult(intent, 0);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == 0) {
            Intent intent = new Intent();
            intent.setClass(this, LockScreenService.class);
            startService(intent);
            Toast.makeText(this, "Registered As Admin", Toast.LENGTH_LONG).show();
            DevicePolicyManager policyManager = (DevicePolicyManager) getSystemService(DEVICE_POLICY_SERVICE);
            policyManager.lockNow();
        }
    }
}
