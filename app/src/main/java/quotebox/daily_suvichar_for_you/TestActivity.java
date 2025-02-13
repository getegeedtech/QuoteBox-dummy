package quotebox.daily_suvichar_for_you;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.SurfaceTexture;
import android.hardware.display.VirtualDisplay;
import android.media.MediaRecorder;
import android.media.projection.MediaProjection;
import android.media.projection.MediaProjectionManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.HandlerThread;
import android.util.Log;
import android.view.MotionEvent;
import android.view.Surface;
import android.view.TextureView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.MediaItem;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import quotebox.daily_suvichar_for_you.commen.User;

import com.arthenica.ffmpegkit.FFmpegKit;

public class TestActivity extends AppCompatActivity {
    private ExoPlayer exoPlayer;
    private TextureView textureView;
    ImageView prot1;
    private String videoUrl = "https://quotes.getege.com/quotebox_11269868.mp4";
    String profile = "https://oreation.coresites.in/assets/images/profile/quotebox_806521784.jpg";
    User user;




    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        user = new User(this);

        textureView = findViewById(R.id.textureView);
        prot1 = findViewById(R.id.prot1);
        RelativeLayout animation1 = findViewById(R.id.animation1);
        RelativeLayout mainLayout = findViewById(R.id.mainLayout);

        Picasso.with(TestActivity.this).load(profile).placeholder(R.drawable.progress_animation).into(prot1);
        textureView.setSurfaceTextureListener(new TextureView.SurfaceTextureListener() {
            @Override
            public void onSurfaceTextureAvailable(@NonNull android.graphics.SurfaceTexture surface, int width, int height) {
                prepareVideo(surface, videoUrl);
            }

            @Override
            public void onSurfaceTextureSizeChanged(@NonNull android.graphics.SurfaceTexture surface, int width, int height) {}

            @Override
            public boolean onSurfaceTextureDestroyed(@NonNull android.graphics.SurfaceTexture surface) {
                stopPlayback();
                return true;
            }

            @Override
            public void onSurfaceTextureUpdated(@NonNull android.graphics.SurfaceTexture surface) {}
        });


        animation1.setOnTouchListener(new View.OnTouchListener() {
            private float downRawX, downRawY;
            private float dX, dY;
            private static final int CLICK_THRESHOLD = 10;
            private boolean isDragging = false;

            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getActionMasked()) {
                    case MotionEvent.ACTION_DOWN:
                        downRawX = motionEvent.getRawX();
                        downRawY = motionEvent.getRawY();
                        dX = view.getX() - downRawX;
                        dY = view.getY() - downRawY;
                        isDragging = false;
                        return true;

                    case MotionEvent.ACTION_MOVE:
                        float deltaX = motionEvent.getRawX() - downRawX;
                        float deltaY = motionEvent.getRawY() - downRawY;

                        if (Math.abs(deltaX) > CLICK_THRESHOLD || Math.abs(deltaY) > CLICK_THRESHOLD) {
                            isDragging = true;

                            float newX = motionEvent.getRawX() + dX;
                            float newY = motionEvent.getRawY() + dY;

                            float parentWidth = mainLayout.getWidth();
                            float parentHeight = mainLayout.getHeight();

                            newX = Math.max(0, Math.min(newX, parentWidth - view.getWidth()));
                            newY = Math.max(0, Math.min(newY, parentHeight - view.getHeight()));

                            view.setX(newX);
                            view.setY(newY);
                        }
                        return true;

                    case MotionEvent.ACTION_UP:
                        if (!isDragging) {
                            view.performClick();
                        }
                        return true;

                    default:
                        return false;
                }
            }
        });





        Button share = findViewById(R.id.share);
        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(TestActivity.this, "I want to share main Layout with Video", Toast.LENGTH_SHORT).show();
            }
        });
    }








    private void prepareVideo(SurfaceTexture surfaceTexture, String videoUrl) {
        if (surfaceTexture == null) return;

        Surface surface = new Surface(surfaceTexture);
        if (exoPlayer == null) {
            exoPlayer = new ExoPlayer.Builder(this).build();
        }

        exoPlayer.setVideoSurface(surface);
        MediaItem mediaItem = MediaItem.fromUri(Uri.parse(videoUrl));
        exoPlayer.setMediaItem(mediaItem);
        exoPlayer.prepare();
        exoPlayer.pause();
        exoPlayer.setRepeatMode(ExoPlayer.REPEAT_MODE_ONE);

        exoPlayer.play();
    }
    public void playVideo(SurfaceTexture surfaceTexture, String videoUrl, TextureView textureView, LottieAnimationView animationView) {
        stopPlayback();
        if (surfaceTexture == null) {
            textureView.setSurfaceTextureListener(new TextureView.SurfaceTextureListener() {
                @Override
                public void onSurfaceTextureAvailable(SurfaceTexture surface, int width, int height) {
                    playVideo(surface, videoUrl, textureView, animationView);
                }

                @Override
                public void onSurfaceTextureSizeChanged(SurfaceTexture surface, int width, int height) {}

                @Override
                public boolean onSurfaceTextureDestroyed(SurfaceTexture surface) {
                    stopPlayback();
                    return true;
                }

                @Override
                public void onSurfaceTextureUpdated(SurfaceTexture surface) {}
            });
            return;
        }
        animationView.setVisibility(View.GONE);
        Surface surface = new Surface(surfaceTexture);
        exoPlayer = new ExoPlayer.Builder(this).build();
        exoPlayer.setVideoSurface(surface);

        MediaItem mediaItem = MediaItem.fromUri(Uri.parse(videoUrl));
        exoPlayer.setMediaItem(mediaItem);
        exoPlayer.setRepeatMode(ExoPlayer.REPEAT_MODE_ONE);
        exoPlayer.prepare();
        exoPlayer.play();
    }
    public void stopPlayback() {
        if (exoPlayer != null) {
            exoPlayer.stop();
            exoPlayer.release();
            exoPlayer = null;
        }
    }
}