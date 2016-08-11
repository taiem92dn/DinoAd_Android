package vn.dinosys.dinoad.ui.view;

import java.util.List;

import vn.dinosys.dinoad.data.net.model.PromotionItem;

/**
 * Created by huutai.
 * Since: 8/11/16 on 1:47 PM
 * Project: DinoAd
 */
public interface IPromotionView {

    void renderPromotions(List<PromotionItem> pPromotionItems);
}
