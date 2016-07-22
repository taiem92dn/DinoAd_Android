package vn.dinosys.dinoad.ui.fragment.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.Arrays;

import butterknife.BindView;
import butterknife.OnClick;
import vn.dinosys.dinoad.R;
import vn.dinosys.dinoad.ui.fragment.base.BaseFragment;
import vn.dinosys.dinoad.ui.view.ISignUpView;
import vn.dinosys.dinoad.util.Util;

/**
 * Created by htsi.
 * Since: 7/12/16 on 10:30 AM
 * Project: DinoAd
 */
public class SignUpFragment extends BaseFragment implements ISignUpView {

    private static final String TAG = SignUpFragment.class.getSimpleName();


    @BindView(R.id.editUsername)
    EditText mEditUsername;

    @BindView(R.id.editPassword)
    EditText mEditPassword;

    @BindView(R.id.editRetype)
    EditText mEditRetype;

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
    public void onSignInClicked(View pView) {
        LoginContainerFragment fragment = (LoginContainerFragment) getParentFragment();
        fragment.togglePage();
    }

    @OnClick(R.id.btnRegister)
    public void onRegisterClicked(View pView) {
        Util.hideKeyboard(pView);
        if (isInputValidate()) {
            String username = mEditUsername.getText() + "";
            String password = mEditPassword.getText() + "";
            if (Util.isNetworkAvailable(getContext())) {
//                mAuthenticatePresenter.login(username.trim(), password);
//                mPdLoading.show();
            }
            else {
                showError(getString(R.string.no_internet_connection));
            }
        }
    }


    private boolean isInputValidate() {
        String username = mEditUsername.getText() + "";
        String password = mEditPassword.getText() + "";
        String retype = mEditRetype.getText() + "";

        boolean isValidate = true;
        if (username.isEmpty()) {
            mEditUsername.setError(getString(R.string.input_username));
            isValidate = false;
        }
        if (password.isEmpty()) {
            mEditPassword.setError(getString(R.string.input_password));
            isValidate = false;
        }
        if (retype.isEmpty()) {
            mEditRetype.setError(getString(R.string.input_retype));
            isValidate = false;
        }
        else if (!retype.equals(password)) {
            mEditRetype.setError(getString(R.string.retype_not_match));
            isValidate = false;
        }

        return isValidate;
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

    @Override
    public void showSignUpSuccess() {

    }

    @Override
    public void showSignUpFail() {

    }

    @Override
    public void showError(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }
}