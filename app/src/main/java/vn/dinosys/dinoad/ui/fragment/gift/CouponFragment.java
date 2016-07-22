package vn.dinosys.dinoad.ui.fragment.gift;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import vn.dinosys.dinoad.R;
import vn.dinosys.dinoad.ui.fragment.base.BaseFragment;

/**
 * Created by huutai.
 * Since: 7/21/16 on 5:43 PM
 * Project: DinoAd
 */
public class CouponFragment extends BaseFragment {

    public static CouponFragment newInstance(String title) {
        CouponFragment fragment = new CouponFragment();
        Bundle bundle = new Bundle();
        bundle.putString("TITLE", title);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_coupon, container, false);
    }

    @Override
    protected void onScreenVisible() {
        super.onScreenVisible();

        String title = getArguments().getString("TITLE");

        setupUI();
    }

    private void setupUI() {

    }
}
