package vn.dinosys.dinoad.ui.activity.lockscreen;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

import vn.dinosys.dinoad.R;

/**
 * Created by htsi.
 * Since: 7/5/16 on 11:10 AM
 * Project: DinoAd
 */
public class YoutubePlayerActivity extends YouTubeBaseActivity implements YouTubePlayer.OnInitializedListener {

    private static final int RECOVERY_DIALOG_REQUEST = 1;
    public static final String DEVELOPER_KEY = "AIzaSyAkcnHqlwdAJczlCDGs6zicr8P7Zbxr3Xs";

    private YouTubePlayer mYouTubePlayer;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        setContentView(R.layout.activity_youtube_player);

        YouTubePlayerView youTubePlayerView = (YouTubePlayerView)findViewById(R.id.youtube_view);
        youTubePlayerView.initialize(DEVELOPER_KEY, this);
    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider pProvider, YouTubePlayer pYouTubePlayer, boolean pB) {
        this.mYouTubePlayer = pYouTubePlayer;
        this.mYouTubePlayer.addFullscreenControlFlag(YouTubePlayer.FULLSCREEN_FLAG_CONTROL_ORIENTATION);
        if (!pB) {
            this.mYouTubePlayer.loadVideo("g3vprAJ2X-k");
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
}
