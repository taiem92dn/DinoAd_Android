package vn.dinosys.dinoad.util;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.util.Log;

import java.util.Locale;

import vn.dinosys.dinoad.R;
import vn.dinosys.dinoad.ui.activity.home.HomeActivity;

/**
 * Created by huutai.
 * Since: 8/8/16 on 2:46 PM
 * Project: DinoAd
 */

public class NotificationHelper {


    private static NotificationHelper mInstance;

    private final Context mContext;

    private final String TAG = NotificationHelper.class.getSimpleName();

    private enum NotificationType {
        REWARD
    }

    private NotificationHelper(Context pContext) {
        mContext = pContext;

        Log.d(TAG, "create new notification helper");
    }

    public static void init(Context pContext) {
        mInstance = new NotificationHelper(pContext);
    }

    public static NotificationHelper getInstance() {
        if (mInstance == null)
            throw new IllegalArgumentException("Call init() to initialize first");
        return mInstance;
    }


    public void sendNotification(String message, int idNotification) {
        Intent intent = new Intent(mContext, HomeActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);


        PendingIntent pendingIntent = PendingIntent.getActivity(mContext, 0 /* Request code */,
                intent,
                PendingIntent.FLAG_ONE_SHOT);


        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(mContext)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentText(message)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setOnlyAlertOnce(true)
                .setAutoCancel(true)
                .setSound(defaultSoundUri)
                .setContentIntent(pendingIntent);

        String contentTitle = mContext.getString(R.string.app_name);

        notificationBuilder.setContentTitle(contentTitle);
        notificationBuilder.setContentText(message);
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(mContext);

        Notification notification = notificationBuilder.build();
//        notification.flags |= Notification.FLAG_AUTO_CANCEL;
        notificationManager.notify(idNotification /* ID of notification */, notification);
    }

    public void sendNotificationReward(int point) {
        String message = String.format(Locale.US, mContext.getString(R.string.point_reward), point);
        sendNotification(message, NotificationType.REWARD.ordinal());
    }
}
