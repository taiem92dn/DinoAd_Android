package vn.dinosys.dinoad.data.net.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by huutai.
 * Since: 8/4/16 on 2:54 PM
 * Project: DinoAd
 */
public class PromotionItem {

    @SerializedName("type")
    private String type;

    @SerializedName("imgUrl")
    private String imgUrl;

    private int drawableId;

    @SerializedName("url")
    private String url;

    @SerializedName("promotionName")
    private String promotionName;

    @SerializedName("promotionDesc")
    private String promotionDesc;

    @SerializedName("bonusPoint")
    private int bonusPoint;
//
//    public PromotionItem(Constants.PromotionType pType, int pDrawableId, String pUrl, String pPromotionName, int pBonusPoint) {
//        type = pType;
//        drawableId = pDrawableId;
//        url = pUrl;
//        promotionName = pPromotionName;
//        bonusPoint = pBonusPoint;
//        promotionDesc = "Install and run";
//    }
//
//    public PromotionItem(Constants.PromotionType pType, String pImgUrl, String pUrl, String pPromotionName, int pBonusPoint) {
//        type = pType;
//        imgUrl = pImgUrl;
//        url = pUrl;
//        promotionName = pPromotionName;
//        bonusPoint = pBonusPoint;
//        promotionDesc = "Install and run";
//    }
//
//    public PromotionItem(Constants.PromotionType pType, int pDrawableId, String pUrl, String pPromotionName, String pPromotionDesc, int pBonusPoint) {
//        type = pType;
//        drawableId = pDrawableId;
//        url = pUrl;
//        promotionName = pPromotionName;
//        promotionDesc = pPromotionDesc;
//        bonusPoint = pBonusPoint;
//    }

    public int getDrawableId() {
        return drawableId;
    }

    public void setDrawableId(int pDrawableId) {
        drawableId = pDrawableId;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String pImgUrl) {
        imgUrl = pImgUrl;
    }

    public String getPromotionName() {
        return promotionName;
    }

    public void setPromotionName(String pPromotionName) {
        promotionName = pPromotionName;
    }

    public String getPromotionDesc() {
        return promotionDesc;
    }

    public void setPromotionDesc(String pPromotionDesc) {
        promotionDesc = pPromotionDesc;
    }

    public int getBonusPoint() {
        return bonusPoint;
    }

    public void setBonusPoint(int pBonusPoint) {
        bonusPoint = pBonusPoint;
    }

    public String getType() {
        return type;
    }

    public void setType(String pType) {
        type = pType;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String pUrl) {
        url = pUrl;
    }
}
