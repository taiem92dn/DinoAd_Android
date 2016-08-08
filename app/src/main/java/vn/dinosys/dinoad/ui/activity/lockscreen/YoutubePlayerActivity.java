package vn.dinosys.dinoad.ui.activity.lockscreen;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

import vn.dinosys.dinoad.R;
import vn.dinosys.dinoad.util.NotificationHelper;

/**
 * Created by htsi.
 * Since: 7/5/16 on 11:10 AM
 * Project: DinoAd
 */
public class YoutubePlayerActivity extends YouTubeBaseActivity implements YouTubePlayer.OnInitializedListener {

    private static final int RECOVERY_DIALOG_REQUEST = 1;

    private static final String VIDEO_ID_KEY = "video_id";

    private String mVideoId;

    private YouTubePlayer mPlayer;

    public static final Intent createIntent(Context pContext, String pVideoId) {
        Intent intent = new Intent(pContext, YoutubePlayerActivity.class);
        intent.putExtra(VIDEO_ID_KEY, pVideoId);
        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        setContentView(R.layout.activity_youtube_player);

        mVideoId = getIntent().getStringExtra(VIDEO_ID_KEY);

        YouTubePlayerView youTubePlayerView = (YouTubePlayerView)findViewById(R.id.youtube_view);
        youTubePlayerView.initialize(getString(R.string.youtube_api_key), this);

    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mPlayer != null && !mPlayer.isPlaying()) {
            mPlayer.loadVideo(mVideoId, mPlayer.getCurrentTimeMillis());
        }
    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider pProvider, YouTubePlayer pYouTubePlayer, boolean pB) {
        pYouTubePlayer.addFullscreenControlFlag(YouTubePlayer.FULLSCREEN_FLAG_CONTROL_ORIENTATION);
        pYouTubePlayer.setPlayerStyle(YouTubePlayer.PlayerStyle.CHROMELESS);
        mPlayer = pYouTubePlayer;
        pYouTubePlayer.setPlayerStateChangeListener(new YouTubePlayer.PlayerStateChangeListener() {
            @Override
            public void onLoading() {

            }

            @Override
            public void onLoaded(String pS) {

            }

            @Override
            public void onAdStarted() {

            }

            @Override
            public void onVideoStarted() {
                /*Timer timer = new Timer();
                timer.scheduleAtFixedRate(new TimerTask() {
                    @Override
                    public void run() {
                        if (mPlayer.getCurrentTimeMillis() != 0) {
                            runOnUiThread(() -> mTextRemainingTime.setText(mPlayer.getCurrentTimeMillis() / 1000 + ""));
                        }
                        else {
                            timer.cancel();
                        }
                    }
                }, 0, 1000);*/
            }

            @Override
            public void onVideoEnded() {
                YoutubePlayerActivity.this.finish();
                NotificationHelper.getInstance().sendNotificationReward(100);
            }

            @Override
            public void onError(YouTubePlayer.ErrorReason pErrorReason) {

            }
        });

        if (!pB) {
            pYouTubePlayer.loadVideo(mVideoId);
        }
    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider pProvider, YouTubeInitializationResult pYouTubeInitializationResult) {
        if (pYouTubeInitializationResult.isUserRecoverableError()) {
            pYouTubeInitializationResult.getErrorDialog(this, RECOVERY_DIALOG_REQUEST).show();
        } else {
            String errorMessage = String.format(getString(R.string.error_player), pYouTubeInitializationResult.toString());
            Toast.makeText(this, errorMessage, Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onBackPressed() {
        /*super.onBackPressed();*/
    }
}
