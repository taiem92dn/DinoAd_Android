package vn.dinosys.dinoad.data.net.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by htsi.
 * Since: 7/3/16 on 9:51 AM
 * Project: DinoAd
 */
public class Banner {

    @SerializedName("bannerid")
    private String mBannerID;

    @SerializedName("contenttype")
    private String mContentType;

    @SerializedName("storagetype")
    private String mStorageType;

    @SerializedName("filename")
    private String mImgFileName;

    @SerializedName("url")
    private String mDestUrl;

    @SerializedName("htmltemplate")
    private String mHtmlTemplate;

    public String getBannerID() {
        return mBannerID;
    }

    public String getContentType() {
        return mContentType;
    }

    public String getStorageType() {
        return mStorageType;
    }

    public String getImgFileName() {
        return mImgFileName;
    }

    public String getDestUrl() {
        return mDestUrl;
    }

    public String getHtmlTemplate() {
        return mHtmlTemplate;
    }

    @Override
    public String toString() {
        return "Banner's ID : " + this.mBannerID + "\n"
                + "Destination URL: " + this.mDestUrl + "\n"
                + "Image file name: " + this.mImgFileName + "\n"
                + "Storage Type: " + this.mStorageType + "\n"
                + "HTM Template: " + this.mHtmlTemplate;
    }
}
