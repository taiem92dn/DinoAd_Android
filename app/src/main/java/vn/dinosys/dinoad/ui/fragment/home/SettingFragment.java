package vn.dinosys.dinoad.ui.fragment.home;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.preference.PreferenceFragmentCompat;
import android.support.v7.widget.AppCompatSpinner;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;

import vn.dinosys.dinoad.R;

/**
 * Created by htsi.
 * Since: 7/18/16 on 10:07 AM
 * Project: DinoAd
 */
public class SettingFragment extends PreferenceFragmentCompat implements SharedPreferences.OnSharedPreferenceChangeListener{

    public static SettingFragment newInstance(String title) {
        SettingFragment fragment = new SettingFragment();
        Bundle bundle = new Bundle();
        bundle.putString("TITLE", title);
        fragment.setArguments(bundle);
        return fragment;
    }

    public static final String KEY_ACTIVE_LOCK_SCREEN = "active_lock_screen";
    private AlertDialog mInactiveLockscreenDialog;


    @Override
    public void onCreatePreferences(Bundle pBundle, String pS) {
        addPreferencesFromResource(R.xml.pref_setting);

        mInactiveLockscreenDialog = createInactiveLockScreenDialog();
    }

    @Override
    public void onResume() {
        super.onResume();
        getPreferenceManager().getSharedPreferences().registerOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        getPreferenceManager().getSharedPreferences().unregisterOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences pSharedPreferences, String key) {

        if (key.equals(KEY_ACTIVE_LOCK_SCREEN)) {
//            Toast.makeText(getContext(), "active lock screen " + pSharedPreferences.getBoolean(key, false), Toast.LENGTH_SHORT)
//                    .show();
            if (!pSharedPreferences.getBoolean(key, false)) {
                mInactiveLockscreenDialog.show();
            }
        }
    }

    public AlertDialog createInactiveLockScreenDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        View view = LayoutInflater.from(getContext()).inflate(R.layout.spinner, null);
        AppCompatSpinner spinner = (AppCompatSpinner) view.findViewById(R.id.spinner);
        ArrayAdapter arrayAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_dropdown_item
                                , getResources().getStringArray(R.array.inactive_lock_screen));
        spinner.setAdapter(arrayAdapter);
        builder.setView(view);
        builder.setTitle(R.string.inactive_lock_screen_question);
        builder.setPositiveButton(android.R.string.ok, (pDialogInterface, pI) -> {

        });
        builder.setNegativeButton(android.R.string.cancel, null);

        return builder.create();
    }
}