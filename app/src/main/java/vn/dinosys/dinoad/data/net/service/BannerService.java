package vn.dinosys.dinoad.data.net.service;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import vn.dinosys.dinoad.data.net.model.Banner;

/**
 * Created by htsi.
 * Since: 7/3/16 on 9:51 AM
 * Project: DinoAd
 */
public interface BannerService {

    @GET("adserver/www/delivery/api.php/rv_banners")
    Call<List<Banner>> getAllBanners();
}
