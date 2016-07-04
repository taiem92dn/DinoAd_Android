package vn.dinosys.dinoad.ui.fragment.lockscreen;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import javax.inject.Inject;

import vn.dinosys.dinoad.R;
import vn.dinosys.dinoad.data.net.model.Banner;
import vn.dinosys.dinoad.di.component.AppComponent;
import vn.dinosys.dinoad.presenter.lockscreen.LockScreenPresenter;
import vn.dinosys.dinoad.ui.fragment.base.BaseFragment;
import vn.dinosys.dinoad.ui.view.ILockScreenView;

/**
 * Created by htsi.
 * Since: 7/3/16 on 9:50 AM
 * Project: DinoAd
 */
public class LockScreenFragment extends BaseFragment implements ILockScreenView {

    @Inject
    LockScreenPresenter mLockScreenPresenter;

    public static LockScreenFragment newInstance() {
        return new LockScreenFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_lockscreen, container, false);
    }

    @Override
    protected void onScreenVisible() {
        super.onScreenVisible();
        setupUI();
    }

    private void setupUI() {
        initialize();
    }

    private void initialize() {
        this.getComponent(AppComponent.class).inject(this);
        mLockScreenPresenter.setView(this);
        mLockScreenPresenter.loadBanners();
    }

    @Override
    public void renderBannerList(List<Banner> pBannerList) {

    }
}
