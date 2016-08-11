package vn.dinosys.dinoad.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import vn.dinosys.dinoad.R;
import vn.dinosys.dinoad.app.Constants;
import vn.dinosys.dinoad.data.net.model.PromotionItem;
import vn.dinosys.dinoad.util.ImageLoaderUtil;

/**
 * Created by huutai.
 * Since: 7/21/16 on 3:23 PM
 * Project: DinoAd
 */
public class PromotionAdapter extends BaseAdapter {

    private List<PromotionItem> mData;

    public PromotionAdapter(List<PromotionItem> pPromotionItems) {
        mData = pPromotionItems;
    }

    public void setData(List<PromotionItem> pPromotionItems) {
        if (pPromotionItems != null) {
            mData = pPromotionItems;
            notifyDataSetChanged();
        }
    }

    @Override
    public int getCount() {
        return mData != null ? mData.size() : 0;
    }

    @Override
    public PromotionItem getItem(int position) {
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

        PromotionItem item = getItem(position);
        holder.textPromotionName.setText(item.getPromotionName());
        holder.textPromotionPrice.setText(String.format(Locale.US, "%,d đ", item.getBonusPoint()));
        holder.textPromotionDesc.setText(item.getPromotionDesc());
        if (item.getType().equals(Constants.PromotionType.Video.toString())) {
            holder.ivPromotion.setImageResource(R.drawable.ic_video);
        }
        else {
            ImageLoaderUtil.loadImage(pViewGroup.getContext(), item.getImgUrl(), holder.ivPromotion);
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
