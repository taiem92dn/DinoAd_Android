package vn.dinosys.dinoad.ui.fragment.home;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.BindView;
import vn.dinosys.dinoad.R;
import vn.dinosys.dinoad.ui.fragment.base.BaseFragment;

/**
 * Created by htsi.
 * Since: 7/18/16 on 10:07 AM
 * Project: DinoAd
 */
public class GiftFragment extends BaseFragment {

    public static GiftFragment newInstance(String title) {
        GiftFragment fragment = new GiftFragment();
        Bundle bundle = new Bundle();
        bundle.putString("TITLE", title);
        fragment.setArguments(bundle);
        return fragment;
    }

    @BindView(R.id.textTitle)
    TextView mTextTitle;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    protected void onScreenVisible() {
        super.onScreenVisible();

        String title = getArguments().getString("TITLE");
        mTextTitle.setText(title);
    }
}
