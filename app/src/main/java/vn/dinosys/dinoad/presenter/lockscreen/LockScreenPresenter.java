package vn.dinosys.dinoad.presenter.lockscreen;

import android.util.Log;

import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import vn.dinosys.dinoad.data.net.model.Banner;
import vn.dinosys.dinoad.data.net.repository.IBannerRepository;
import vn.dinosys.dinoad.presenter.base.IBasePresenter;
import vn.dinosys.dinoad.ui.view.ILockScreenView;

/**
 * Created by htsi.
 * Since: 7/4/16 on 10:02 AM
 * Project: DinoAd
 */
public class LockScreenPresenter implements IBasePresenter<ILockScreenView> {

    private static final String TAG = LockScreenPresenter.class.getName();

    private ILockScreenView mLockScreenView;
    private IBannerRepository mBannerRepository;

    @Inject
    public LockScreenPresenter(IBannerRepository pIBannerRepository) {
        mBannerRepository = pIBannerRepository;
    }

    @Override
    public void setView(ILockScreenView pILockScreenView) {
        mLockScreenView = pILockScreenView;
        initialize();
    }

    private void initialize () {
        loadBanners();
    }

    private void loadBanners () {

        mBannerRepository.getAllBanners().enqueue(new Callback<List<Banner>>() {
            @Override
            public void onResponse(Call<List<Banner>> call, Response<List<Banner>> response) {
                Log.d(TAG, "onResponse");
            }

            @Override
            public void onFailure(Call<List<Banner>> call, Throwable t) {
                Log.d(TAG, "onFailure");
            }
        });
    }

    @Override
    public void destroyView() {

    }
}
