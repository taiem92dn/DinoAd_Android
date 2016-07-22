package vn.dinosys.dinoad.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import vn.dinosys.dinoad.R;
import vn.dinosys.dinoad.data.net.model.Banner;

/**
 * Created by huutai.
 * Since: 7/21/16 on 3:23 PM
 * Project: DinoAd
 */
public class PromotionAdapter extends BaseAdapter {

    private List<Banner> mData;

    public PromotionAdapter(List<Banner> pBanners) {
        mData = pBanners;
    }

    public void setData(List<Banner> pBanners) {
        if (pBanners != null) {
            mData = pBanners;
            notifyDataSetChanged();
        }
    }

    @Override
    public int getCount() {
        return mData != null ? mData.size() : 0;
    }

    @Override
    public Banner getItem(int position) {
        return mData.get(position);
    }

    @Override
    public long getItemId(int pI) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup pViewGroup) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(pViewGroup.getContext()).inflate(R.layout.list_promotion_item, pViewGroup, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }
        else {
            holder = (ViewHolder) convertView.getTag();
        }


        return convertView;
    }

    static class ViewHolder {

        @BindView(R.id.ivPromotion)
        ImageView ivPromotion;

        @BindView(R.id.textPromotionName)
        TextView textPromotionName;

        @BindView(R.id.textPromotionDesc)
        TextView textPromotionDesc;

        @BindView(R.id.textPromotionPrice)
        TextView textPromotionPrice;

        ViewHolder(View pView) {
            ButterKnife.bind(this, pView);
        }
    }
}
