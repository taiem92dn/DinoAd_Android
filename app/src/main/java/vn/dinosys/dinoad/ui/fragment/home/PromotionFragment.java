package vn.dinosys.dinoad.ui.fragment.home;

import android.app.ActivityManager;
import android.content.ActivityNotFoundException;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.jaredrummler.android.processes.AndroidProcesses;
import com.jaredrummler.android.processes.models.AndroidAppProcess;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javax.inject.Inject;

import butterknife.BindView;
import vn.dinosys.dinoad.R;
import vn.dinosys.dinoad.app.Constants;
import vn.dinosys.dinoad.data.net.model.PromotionItem;
import vn.dinosys.dinoad.di.component.AppComponent;
import vn.dinosys.dinoad.presenter.promotion.PromotionPresenter;
import vn.dinosys.dinoad.ui.activity.lockscreen.YoutubePlayerActivity;
import vn.dinosys.dinoad.ui.adapter.PromotionAdapter;
import vn.dinosys.dinoad.ui.fragment.base.BaseFragment;
import vn.dinosys.dinoad.ui.view.IPromotionView;
import vn.dinosys.dinoad.util.NotificationHelper;

/**
 * Created by htsi.
 * Since: 7/18/16 on 10:07 AM
 * Project: DinoAd
 */
public class PromotionFragment extends BaseFragment implements IPromotionView {

    public static PromotionFragment newInstance(String title) {
        PromotionFragment fragment = new PromotionFragment();
        Bundle bundle = new Bundle();
        bundle.putString("TITLE", title);
        fragment.setArguments(bundle);
        return fragment;
    }

    private final String TAG = PromotionFragment.class.getSimpleName();

    @BindView(R.id.list)
    ListView mListView;

    @Inject
    PromotionPresenter mPromotionPresenter;

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
        mAdapter = new PromotionAdapter(new ArrayList<>());
        mListView.setAdapter(mAdapter);

        mListView.setOnItemClickListener((pAdapterView, pView, pI, pL) -> {
            PromotionItem item = mAdapter.getItem(pI);
            if (item.getType().equals(Constants.PromotionType.Video.toString())) {

                startActivity(YoutubePlayerActivity.createIntent(getContext(), item.getUrl()));
            } else if (item.getType().equals(Constants.PromotionType.AppRun.toString())) {

                checkConditionApp(item);
                openAppInStore(item.getUrl());
            }else if (item.getType().equals(Constants.PromotionType.AppInstall.toString())) {

                checkConditionApp(item);
                openAppInStore(item.getUrl());
            }
        });

        initialize();
    }


    private void initialize() {
        this.getComponent(AppComponent.class).inject(this);
        mPromotionPresenter.setView(this);
        mPromotionPresenter.loadPromotions();
    }

    @Override
    public void renderPromotions(List<PromotionItem> pPromotionItems) {
        mAdapter.setData(pPromotionItems);
    }

    private void checkConditionApp(PromotionItem pPromotionItem) {
        checkPromotionItem = pPromotionItem;
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(Intent.ACTION_PACKAGE_ADDED);
        intentFilter.addDataScheme("package");
        getContext().registerReceiver(mReceiverAppInstall, intentFilter);

    }

    private PromotionItem checkPromotionItem;

    private BroadcastReceiver mReceiverAppInstall = new BroadcastReceiver() {
        @Override
        public void onReceive(Context pContext, Intent pIntent) {
            String packageName = pIntent.getData().getEncodedSchemeSpecificPart();
            Log.d(TAG, "app install " + packageName);
            if (packageName.equals(checkPromotionItem.getUrl())) {
                if (checkPromotionItem.getType().equals(Constants.PromotionType.AppInstall.toString())) {
                    NotificationHelper.getInstance().sendNotificationReward(checkPromotionItem.getBonusPoint());
                    getContext().unregisterReceiver(mReceiverAppInstall);
                }
                else if (checkPromotionItem.getType().equals(Constants.PromotionType.AppRun.toString())) {
                    checkAppIsRunning(checkPromotionItem);
                }
            }
        }
    };

    private void openAppInStore(String appPackageName) {
        try {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + appPackageName)));
        } catch (ActivityNotFoundException ex) {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + appPackageName)));
        }

    }

    private void checkAppIsRunning(PromotionItem pPromotionItem) {
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                List<AndroidAppProcess> foregroundApps = AndroidProcesses.getRunningForegroundApps(getContext());

                boolean isForeGround = false;
                for (AndroidAppProcess androidAppProcess : foregroundApps) {
                    if (androidAppProcess.getPackageName().equals(pPromotionItem.getUrl())) {
                        isForeGround = true;
                        break;
                    }
                }

                Log.d(TAG, "app foreground " + foregroundApps.get(0) + " " + foregroundApps.size());

                if (isForeGround) {
                    getActivity().runOnUiThread(() -> NotificationHelper.getInstance().sendNotificationReward(pPromotionItem.getBonusPoint()));
                    timer.cancel();
                    getContext().unregisterReceiver(mReceiverAppInstall);
                }
            }

        }, 500, 500);
    }

    private String getForegroundApp(Context pContext) {
        ActivityManager.RunningAppProcessInfo result = null, info = null;
        ActivityManager activityManager = (ActivityManager) pContext.getSystemService(Context.ACTIVITY_SERVICE);

        activityManager.getAppTasks();
        List<ActivityManager.RunningAppProcessInfo> l = activityManager.getRunningAppProcesses(); /* deprecated in android 5.1.1 */
        Iterator<ActivityManager.RunningAppProcessInfo> i = l.iterator();
        Log.d(TAG, "RunningAppProcesses " + activityManager.getAppTasks().size());
        while (i.hasNext()) {
            info = i.next();
            if (info.importance == ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND
                    && !isRunningService(pContext, info.processName)) {
                result = info;
                break;
            }
        }
        return result != null ? result.processName : "";
    }


    private boolean isRunningService(Context pContext, String processname) {
        if (processname == null || processname.isEmpty())
            return false;

        ActivityManager.RunningServiceInfo service;

        ActivityManager activityManager = (ActivityManager) pContext.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningServiceInfo> l = activityManager.getRunningServices(9999);
        Iterator<ActivityManager.RunningServiceInfo> i = l.iterator();
        Log.d(TAG, "RunningServiceInfo " + l.size());
        while (i.hasNext()) {
            service = i.next();
            if (service.process.equals(processname))
                return true;
        }

        return false;
    }

}