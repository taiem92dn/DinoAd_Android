package vn.dinosys.dinoad.presenter.base;

/**
 * Created by htsi.
 * Since: 7/4/16 on 9:57 AM
 * Project: DinoAd
 */
public interface IBasePresenter<ViewType> {

    void setView(ViewType pViewType);
    void destroyView();
}
