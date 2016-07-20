package vn.dinosys.dinoad.ui.fragment.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.Arrays;

import butterknife.OnClick;
import vn.dinosys.dinoad.R;
import vn.dinosys.dinoad.ui.fragment.base.BaseFragment;

/**
 * Created by htsi.
 * Since: 7/12/16 on 10:30 AM
 * Project: DinoAd
 */
public class SignUpFragment extends BaseFragment {

    private static final String TAG = SignUpFragment.class.getSimpleName();

    private GoogleApiClient mGoogleApiClient;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_sign_up, container, false);
    }

    @Override
    protected void onScreenVisible() {
        super.onScreenVisible();

        LoginManager.getInstance().registerCallback(CallbackManager.Factory.create(), new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult pLoginResult) {
                Log.i(TAG,"onSuccess");
            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException error) {

            }
        });

        mGoogleApiClient = new GoogleApiClient.Builder(getContext())
                .enableAutoManage(getActivity(), pConnectionResult -> {

                })
                .addApi(Auth.GOOGLE_SIGN_IN_API).build();
    }

    @OnClick(R.id.btnSignIn)
    public void onSignUpClicked(View pView) {
        LoginContainerFragment fragment = (LoginContainerFragment) getParentFragment();
        fragment.togglePage();
    }

    @OnClick(R.id.btnLoginFaceBook)
    public void onLoginFacebook(View pView) {
        LoginManager.getInstance().logInWithReadPermissions(this, Arrays.asList("public_profile", "user_friends"));
    }

    @OnClick(R.id.btnLoginGPlus)
    public void onLoginGPlus(View pView) {


        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        startActivityForResult(signInIntent, 100);
    }
}
