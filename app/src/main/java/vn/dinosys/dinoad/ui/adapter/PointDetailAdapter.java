package vn.dinosys.dinoad.ui.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;

import java.util.HashMap;
import java.util.List;

import vn.dinosys.dinoad.data.net.model.PointDetail;

/**
 * Created by huutai.
 * Since: 7/29/16 on 4:35 PM
 * Project: DinoAd
 */
public class PointDetailAdapter extends BaseExpandableListAdapter {


    private PointDetail[] mGroupData;
    private HashMap<PointDetail, List<PointDetail>> mData;

    public PointDetailAdapter(HashMap<PointDetail, List<PointDetail>> pData) {
        this.mData = pData;
        if (this.mData != null) {
            this.mGroupData = (PointDetail[]) mData.keySet().toArray();
        }
    }

    public void setData(HashMap<PointDetail, List<PointDetail>> pData) {
        if (pData != null) {
            this.mData = pData;
            notifyDataSetChanged();
        }
    }

    @Override
    public int getGroupCount() {
        return mGroupData.length;
    }

    @Override
    public int getChildrenCount(int pI) {
        return 0;
    }

    @Override
    public Object getGroup(int pI) {
        return mGroupData[pI];
    }

    @Override
    public Object getChild(int pI, int pI1) {
        return mData.get(mGroupData[pI]);
    }

    @Override
    public long getGroupId(int pI) {
        return pI;
    }

    @Override
    public long getChildId(int pI, int pI1) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public int getChildType(int groupPosition, int childPosition) {
        return super.getChildType(groupPosition, childPosition);
    }

    @Override
    public View getGroupView(int pI, boolean pB, View pView, ViewGroup pViewGroup) {
        return null;
    }

    @Override
    public View getChildView(int pI, int pI1, boolean pB, View pView, ViewGroup pViewGroup) {
        return null;
    }

    @Override
    public boolean isChildSelectable(int pI, int pI1) {
        return false;
    }
}
