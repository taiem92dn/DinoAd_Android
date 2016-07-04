package vn.dinosys.dinoad.ui.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import vn.dinosys.dinoad.R;
import vn.dinosys.dinoad.ui.activity.base.BaseActivity;
import vn.dinosys.dinoad.ui.activity.lockscreen.LockScreenActivity;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void goToLockScreen(View pView) {
        startActivity(new Intent(this, LockScreenActivity.class));
    }
}
