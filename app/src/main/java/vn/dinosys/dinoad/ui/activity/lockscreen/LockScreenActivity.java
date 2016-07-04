package vn.dinosys.dinoad.ui.activity.lockscreen;

import vn.dinosys.dinoad.di.base.HasComponent;
import vn.dinosys.dinoad.di.component.AppComponent;
import vn.dinosys.dinoad.ui.activity.base.BaseActivity;
import vn.dinosys.dinoad.ui.fragment.base.BaseFragment;
import vn.dinosys.dinoad.ui.fragment.lockscreen.LockScreenFragment;

/**
 * Created by htsi.
 * Since: 7/3/16 on 9:50 AM
 * Project: DinoAd
 */
public class LockScreenActivity extends BaseActivity implements HasComponent<AppComponent> {

    @Override
    protected BaseFragment hostFragment() {
        return LockScreenFragment.newInstance();
    }

    @Override
    public AppComponent getComponent() {
        return getApplicationComponent();
    }
}
