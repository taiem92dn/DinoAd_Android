package vn.dinosys.dinoad.ui.view;

import java.util.List;

import vn.dinosys.dinoad.data.net.model.Banner;

/**
 * Created by htsi.
 * Since: 7/4/16 on 9:59 AM
 * Project: DinoAd
 */
public interface ILockScreenView {

    void renderBannerList(List<Banner> pBannerList);
}
