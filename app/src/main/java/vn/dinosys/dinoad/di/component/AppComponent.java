package vn.dinosys.dinoad.di.component;

import javax.inject.Singleton;

import dagger.Component;
import vn.dinosys.dinoad.data.net.repository.banner.IBannerRepository;
import vn.dinosys.dinoad.di.module.AppModule;
import vn.dinosys.dinoad.ui.activity.base.BaseActivity;
import vn.dinosys.dinoad.ui.fragment.base.BaseFragment;
import vn.dinosys.dinoad.ui.fragment.lockscreen.LockScreenFragment;

/**
 * Created by htsi.
 * Since: 7/1/16 on 6:02 PM
 * Project: DinoAd
 */
@Singleton
@Component( modules = {
        AppModule.class
})
public interface AppComponent {

    IBannerRepository bannerRepository();

    void inject(BaseActivity pBaseActivity);
    void inject(BaseFragment pBaseFragment);
    void inject(LockScreenFragment pLockScreenFragment);
}
