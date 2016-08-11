package vn.dinosys.dinoad.presenter.promotion;

import android.content.Context;
import android.os.AsyncTask;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

import javax.inject.Inject;

import vn.dinosys.dinoad.data.net.model.PromotionItem;
import vn.dinosys.dinoad.presenter.base.IBasePresenter;
import vn.dinosys.dinoad.ui.view.IPromotionView;
import vn.dinosys.dinoad.util.Util;

/**
 * Created by huutai.
 * Since: 8/11/16 on 1:45 PM
 * Project: DinoAd
 */
public class PromotionPresenter implements IBasePresenter<IPromotionView> {

    @Inject
    Context mContext;

    private IPromotionView mPromotionView;

    @Inject
    public PromotionPresenter() {

    }


    @Override
    public void setView(IPromotionView pPromotionView) {
        mPromotionView = pPromotionView;
    }

    public void loadPromotions() {
        new AsyncTask<Void, Void, List<PromotionItem>>() {
            @Override
            protected List<PromotionItem> doInBackground(Void... pVoids) {
                String json = Util.readFileFromAsset("promotions.json", mContext);
                List<PromotionItem> promotionItems = new Gson().fromJson(json, new TypeToken<List<PromotionItem>>(){}.getType());
                return promotionItems;
            }

            @Override
            protected void onPostExecute(List<PromotionItem> pPromotionItems) {
                mPromotionView.renderPromotions(pPromotionItems);
            }
        }.execute();
    }

    @Override
    public void destroyView() {

    }
}
