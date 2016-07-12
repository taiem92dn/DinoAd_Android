package vn.dinosys.dinoad.ui.activity.login;

import vn.dinosys.dinoad.di.base.HasComponent;
import vn.dinosys.dinoad.di.component.UserComponent;
import vn.dinosys.dinoad.ui.activity.base.BaseActivity;
import vn.dinosys.dinoad.ui.fragment.base.BaseFragment;
import vn.dinosys.dinoad.ui.fragment.login.LoginContainerFragment;

/**
 * Created by htsi.
 * Since: 7/7/16 on 1:43 PM
 * Project: DinoAd
 */
public class LoginActivity extends BaseActivity implements HasComponent<UserComponent> {

    @Override
    protected BaseFragment hostFragment() {
        return new LoginContainerFragment();
    }

    @Override
    public UserComponent getComponent() {
        return null;
    }
}
