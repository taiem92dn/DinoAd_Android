package vn.dinosys.dinoad.data.net.repository;

import java.util.List;

import retrofit2.Call;
import vn.dinosys.dinoad.data.net.model.Banner;

/**
 * Created by htsi.
 * Since: 7/4/16 on 9:51 AM
 * Project: DinoAd
 */
public interface IBannerRepository {

    Call<List<Banner>> getAllBanners();
}
