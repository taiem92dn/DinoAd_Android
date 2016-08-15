package vn.dinosys.dinoad.ui.activity;

import android.app.Activity;
import android.content.Intent;

import vn.dinosys.dinoad.di.base.HasComponent;
import vn.dinosys.dinoad.di.component.AppComponent;
import vn.dinosys.dinoad.ui.activity.base.BaseActivity;
import vn.dinosys.dinoad.ui.fragment.MyInfoFragment;
import vn.dinosys.dinoad.ui.fragment.base.BaseFragment;

/**
 * Created by huutai.
 * Since: 8/12/16 on 11:23 AM
 * Project: DinoAd
 */
public class MyInfoActivity extends BaseActivity implements HasComponent<AppComponent> {


    public static void show(Activity pActivity) {
        Intent intent = new Intent(pActivity, MyInfoActivity.class);
        pActivity.startActivity(intent);
    }

    @Override
    protected BaseFragment hostFragment() {
        return MyInfoFragment.newInstance();
    }

    @Override
    public AppComponent getComponent() {
        return getApplicationComponent();
    }
}
