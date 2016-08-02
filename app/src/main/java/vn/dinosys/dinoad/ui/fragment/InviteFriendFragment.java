package vn.dinosys.dinoad.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import vn.dinosys.dinoad.R;
import vn.dinosys.dinoad.data.net.model.ShareItem;
import vn.dinosys.dinoad.ui.adapter.InviteFriendAdapter;
import vn.dinosys.dinoad.ui.fragment.base.BaseFragment;


public class InviteFriendFragment extends BaseFragment {

    public static InviteFriendFragment newInstance() {
        InviteFriendFragment fragment = new InviteFriendFragment();
//        Bundle bundle = new Bundle();
//        bundle.putString("TITLE", title);
//        fragment.setArguments(bundle);

        return fragment;
    }


    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @BindView(R.id.rvInviteFriends)
    RecyclerView mRvShare;

    public enum Invite {
        Facebook, Google, Messenger, Email, SMS, CopyURL("Copy URL");

        private String name;

        Invite() {}

        Invite(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            if (TextUtils.isEmpty(this.name))
                return super.toString();
            else
                return name;
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_invite_friend, container, false);
    }

    @Override
    protected void onScreenVisible() {
        super.onScreenVisible();
        setHasOptionsMenu(true);

//        String title = getArguments().getString("TITLE");

        initToolbar();
        setupUI();
    }

    private void setupUI() {
        List<ShareItem> shareItems = new ArrayList<>();
        shareItems.add(new ShareItem(R.drawable.ic_facebook_round, Invite.Facebook.toString()));
        shareItems.add(new ShareItem(R.drawable.ic_facebook_round, Invite.Google.toString()));
        shareItems.add(new ShareItem(R.drawable.ic_facebook_round, Invite.Messenger.toString()));
        shareItems.add(new ShareItem(R.drawable.ic_facebook_round, Invite.Email.toString()));
        shareItems.add(new ShareItem(R.drawable.ic_facebook_round, Invite.SMS.toString()));
        shareItems.add(new ShareItem(R.drawable.ic_facebook_round, Invite.CopyURL.toString()));

        InviteFriendAdapter adapter = new InviteFriendAdapter(shareItems);
        mRvShare.setAdapter(adapter);
        mRvShare.setVerticalScrollBarEnabled(false);
        mRvShare.setNestedScrollingEnabled(false);
        adapter.setOnItemClickListener(position -> {
            Toast.makeText(getActivity(), "Share " + Invite.values()[position].toString(), Toast.LENGTH_SHORT).show();
            switch (Invite.values()[position]) {
                case Facebook:
                    break;
                case Google:

                    break;
                case Messenger:

                    break;
                case SMS:

                    break;
            }
        });

    }

    private void initToolbar() {
        AppCompatActivity appCompatActivity = ((AppCompatActivity) getActivity());
        appCompatActivity.setSupportActionBar(mToolbar);
        ActionBar actionBar = appCompatActivity.getSupportActionBar();

        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle(R.string.invite_friends);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == android.R.id.home) {
            getActivity().finish();
        }

        return super.onOptionsItemSelected(item);
    }
}
