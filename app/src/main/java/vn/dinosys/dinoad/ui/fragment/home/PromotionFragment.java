package vn.dinosys.dinoad.ui.fragment.home;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import vn.dinosys.dinoad.R;
import vn.dinosys.dinoad.data.net.model.Banner;
import vn.dinosys.dinoad.ui.adapter.PromotionAdapter;
import vn.dinosys.dinoad.ui.fragment.base.BaseFragment;

/**
 * Created by htsi.
 * Since: 7/18/16 on 10:07 AM
 * Project: DinoAd
 */
public class PromotionFragment extends BaseFragment {

    public static PromotionFragment newInstance(String title) {
        PromotionFragment fragment = new PromotionFragment();
        Bundle bundle = new Bundle();
        bundle.putString("TITLE", title);
        fragment.setArguments(bundle);
        return fragment;
    }


    @BindView(R.id.list)
    ListView mListView;

    private PromotionAdapter mAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_promotion, container, false);
    }

    @Override
    protected void onScreenVisible() {
        super.onScreenVisible();

        String title = getArguments().getString("TITLE");

        setupUI();
    }

    private void setupUI() {
        List<Banner> banners = new ArrayList<>();
        banners.add(new Banner());
        banners.add(new Banner());
        banners.add(new Banner());
        banners.add(new Banner());
        banners.add(new Banner());
        mAdapter = new PromotionAdapter(banners);
        mListView.setAdapter(mAdapter);

    }
}