package vn.dinosys.dinoad.data.net.model;

/**
 * Created by huutai.
 * Since: 7/28/16 on 5:35 PM
 * Project: DinoAd
 */
public class GiftCard {

    private String mTitle;

    private int price;

    public GiftCard(String pTitle, int pPrice) {
        price = pPrice;
        mTitle = pTitle;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String pTitle) {
        mTitle = pTitle;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int pPrice) {
        price = pPrice;
    }
}
