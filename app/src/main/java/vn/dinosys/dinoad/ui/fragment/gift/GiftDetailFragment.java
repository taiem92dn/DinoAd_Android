package vn.dinosys.dinoad.ui.fragment.gift;

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
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import vn.dinosys.dinoad.R;
import vn.dinosys.dinoad.app.Constants;
import vn.dinosys.dinoad.data.net.model.GiftCard;
import vn.dinosys.dinoad.ui.adapter.GiftCardAdapter;
import vn.dinosys.dinoad.ui.fragment.base.BaseFragment;

/**
 * Created by huutai.
 * Since: 7/28/16 on 4:43 PM
 * Project: DinoAd
 */
public class GiftDetailFragment extends BaseFragment {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @BindView(R.id.list)
    ListView mListView;

    private Constants.GiftCardType mGiftCardType;

    public static GiftDetailFragment newInstance(String title, Constants.GiftCardType pType) {
        GiftDetailFragment fragment = new GiftDetailFragment();
        Bundle bundle = new Bundle();
        bundle.putString("TITLE", title);
        fragment.setArguments(bundle);

        Log.d("GiftDetail", pType.toString());
        fragment.mGiftCardType = pType;

        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_gift_detail, container, false);
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

        if (actionBar != null) {
            if (mGiftCardType == Constants.GiftCardType.Prepaid) {
                actionBar.setTitle(R.string.prepaid_card);
            } else if (mGiftCardType == Constants.GiftCardType.Game) {
                actionBar.setTitle(R.string.game_card);
            }
        }
    }


    private void setupUI() {
        List<GiftCard> giftCards = new ArrayList<>();
        GiftCardAdapter adapter = new GiftCardAdapter(giftCards);

        if (mGiftCardType == Constants.GiftCardType.Prepaid) {
            giftCards.add(new GiftCard("Vinaphone 20,000 VND", 20000));
            giftCards.add(new GiftCard("Mobifone 20,000 VND", 20000));
            giftCards.add(new GiftCard("Viettel 20,000 VND", 20000));
            giftCards.add(new GiftCard("Vietnamobile 20,000 VND", 20000));
            giftCards.add(new GiftCard("Vinaphone 50,000 VND", 50000));
            giftCards.add(new GiftCard("Mobifone 50,000 VND", 50000));
            giftCards.add(new GiftCard("Viettel 50,000 VND", 50000));
            giftCards.add(new GiftCard("Vietnamobile 50,000 VND", 50000));
        }
        else if (mGiftCardType == Constants.GiftCardType.Game) {
            giftCards.add(new GiftCard("Gate 20,000 VND", 20000));
            giftCards.add(new GiftCard("Vcoin 20,000 VND", 20000));
            giftCards.add(new GiftCard("Zing 20,000 VND", 20000));
            giftCards.add(new GiftCard("Garena 20,000 VND", 20000));
            giftCards.add(new GiftCard("Gate 50,000 VND", 50000));
            giftCards.add(new GiftCard("Vcoin 50,000 VND", 50000));
            giftCards.add(new GiftCard("Zing 50,000 VND", 50000));
            giftCards.add(new GiftCard("Garena 50,000 VND", 50000));
        }

        mListView.setAdapter(adapter);
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
