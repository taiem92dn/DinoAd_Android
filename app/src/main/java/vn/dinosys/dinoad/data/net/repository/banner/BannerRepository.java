package vn.dinosys.dinoad.data.net.repository.banner;

import java.util.List;

import retrofit2.Call;
import vn.dinosys.dinoad.data.net.model.Banner;
import vn.dinosys.dinoad.data.net.service.banner.BannerService;

/**
 * Created by htsi.
 * Since: 7/4/16 on 9:51 AM
 * Project: DinoAd
 */
public class BannerRepository implements IBannerRepository {

    private BannerService mBannerService;

    public BannerRepository(BannerService pBannerService) {
        mBannerService = pBannerService;
    }

    @Override
    public Call<List<Banner>> getAllBanners() {
        return mBannerService.getAllBanners();
    }
}
