package vn.dinosys.dinoad.ui.fragment.login;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.OnClick;
import vn.dinosys.dinoad.R;
import vn.dinosys.dinoad.ui.activity.home.HomeActivity;
import vn.dinosys.dinoad.ui.fragment.base.BaseFragment;

/**
 * Created by htsi.
 * Since: 7/12/16 on 10:30 AM
 * Project: DinoAd
 */
public class SignInFragment extends BaseFragment {

    @BindView(R.id.editPassword)
    EditText mEditPassword;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_sign_in, container, false);
    }

    @Override
    protected void onScreenVisible() {
        super.onScreenVisible();
        setupUI();
    }

    private void setupUI() {
    }

    @OnClick(R.id.btnSignIn)
    public void onSignInClicked(View pView) {
        startActivity(HomeActivity.createIntent(getContext()));
    }

    @OnClick(R.id.btnSignUp)
    public void onSignUpClicked(View pView) {
        LoginContainerFragment fragment = (LoginContainerFragment) getParentFragment();
        fragment.togglePage();
    }
}
