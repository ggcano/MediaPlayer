package com.example.mediaplayer_tarea2;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.Toast;
import android.widget.VideoView;

import com.example.mediaplayer_tarea2.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    MediaPlayer mediaPlayer = new MediaPlayer();
    private int playbackPosition = 0;
    public static final int arriba2 = R.raw.arriba2;
    public static final int escritorio = R.raw.escritorio2;
    public static final int manos2 = R.raw.manos2;
    public static final int texturas2 = R.raw.textura2;
    private VideoView video;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        onClickOnRadioButton();

        stopBtn();
        pauseBtn();
        playBtn();

    }



    private void playBtn() {
        binding.startPlayerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                video.start();
            }
        });
    }

    private void loadVideo(int raw) {
        video = (VideoView) findViewById(R.id.videoView);
        String path = "android.resource://" + getPackageName() + "/" + raw;
        video.setVideoURI(Uri.parse(path));
    }


    private void stopBtn() {
        binding.stopPlayerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (video != null) {
                    playbackPosition = 0;
                    video.seekTo(0);
                    video.pause();
                }
            }
        });

    }


    private void pauseBtn() {
        binding.pausePlayerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (video != null && video.isPlaying()) {
                    playbackPosition = video.getCurrentPosition();
                    video.pause();
                }
            }
        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        killMediaPlayer();
    }

    private void killMediaPlayer() {
        if (mediaPlayer != null) {
            try {
                mediaPlayer.release();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    private void onClickOnRadioButton() {
        RadioButton rb1 = (RadioButton) findViewById(R.id.radioButton_1_desde);
        rb1.setOnClickListener(v -> loadVideo(arriba2));

        RadioButton rb2 = (RadioButton) findViewById(R.id.radioButton_2_escritorio);
        rb2.setOnClickListener(v -> loadVideo(escritorio));

        RadioButton rb3 = (RadioButton) findViewById(R.id.radioButton_3_Manos);
        rb3.setOnClickListener(v -> loadVideo(manos2));

        RadioButton rb4 = (RadioButton) findViewById(R.id.radioButton_4_Texturas);
        rb4.setOnClickListener(v -> loadVideo(texturas2) );

    }



}
