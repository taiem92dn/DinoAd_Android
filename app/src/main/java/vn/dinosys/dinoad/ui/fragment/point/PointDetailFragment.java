package vn.dinosys.dinoad.ui.fragment.point;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

import butterknife.BindView;
import vn.dinosys.dinoad.R;
import vn.dinosys.dinoad.ui.fragment.base.BaseFragment;

/**
 * Created by huutai.
 * Since: 7/29/16 on 3:17 PM
 * Project: DinoAd
 */
public class PointDetailFragment  extends BaseFragment {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @BindView(R.id.elvPointDetail)
    ExpandableListView mElvPointDetail;

    public static PointDetailFragment newInstance(String title) {
        PointDetailFragment fragment = new PointDetailFragment();
        Bundle bundle = new Bundle();
        bundle.putString("TITLE", title);
        fragment.setArguments(bundle);

        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_point_detail, container, false);
    }

    @Override
    protected void onScreenVisible() {
        super.onScreenVisible();
        setHasOptionsMenu(true);

        String title = getArguments().getString("TITLE");

        initToolbar();
        setupUI();
    }


    private void initToolbar() {
        AppCompatActivity appCompatActivity = ((AppCompatActivity) getActivity());
        appCompatActivity.setSupportActionBar(mToolbar);
        ActionBar actionBar = appCompatActivity.getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
    }

    private void setupUI() {

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        Log.d("GiftDetail", "onOptionsItemSelected");

        if (id == android.R.id.home) {
            getActivity().finish();
        }

        return super.onOptionsItemSelected(item);
    }
}
