package com.example.pruprac2rolmor;


import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Handler;
import android.view.View;
import android.widget.MediaController;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.IOException;

public class MainActivity extends AppCompatActivity implements MediaController.MediaPlayerControl
{
    private MediaPlayer mediaPlayer;
    private MediaController mediaController;
    private Handler handler;
    int[] canciones = {R.raw.una, R.raw.dos, R.raw.tres, R.raw.cuatro, R.raw.cinco};
    private int indice=0;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) ->
        {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        mediaPlayer = new MediaPlayer();
        mediaController = new MediaController(this);
        mediaController.setMediaPlayer(this);
        mediaController.setAnchorView(findViewById(R.id.main));
        mediaController.setTop(1);
        handler = new Handler();
        ponerCancion(indice);
        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener()
        {
            @Override
            public void onPrepared(MediaPlayer mediaPlayer)
            {
                handler.post(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        mediaController.show(20000);
                        mediaPlayer.start();
                    }
                });
            }
        });
        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener()
        {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer)
            {
                indice++;
                ponerCancion(indice);
            }
        });

        mediaController.setPrevNextListeners(new View.OnClickListener()
         {
             @Override
             public void onClick(View view) {
                 int indiceS = mediaPlayer.getCurrentPosition();
                 indiceS++;
                 if (indiceS >= canciones.length - 1) {
                     indiceS = 0;
                 }
                 ponerCancion(indiceS);
             }
         },
            new View.OnClickListener()
            {
                @Override
                public void onClick(View view)
                {
                    int indiceS = mediaPlayer.getCurrentPosition();
                    indiceS--;
                    if (indiceS < 0) {
                        indiceS = canciones.length - 1;
                    }
                    ponerCancion(indiceS);
                }
            });
    }

    public void ponerCancion(int indice)
    {
        try
        {
            mediaPlayer.setDataSource(this, Uri.parse("android.resource://" + getPackageName() + "/" + canciones[indice]));
            mediaPlayer.prepare();
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }
    }
    @Override protected void onDestroy()
    {
        super.onDestroy();
        mediaPlayer.stop();
        mediaPlayer.release();
    }

    @Override public boolean canPause()
    {
        return true;
    }

    @Override public boolean canSeekBackward()
    {
        return false;
    }

    @Override public boolean canSeekForward()
    {
        return false;
    }

    @Override public int getAudioSessionId()
    {
        return 0;
    }

    @Override public int getBufferPercentage()
    {
        return 0;
    }

    @Override public int getCurrentPosition()
    {
        return mediaPlayer.getCurrentPosition();
    }

    @Override public int getDuration()
    {
        return mediaPlayer.getDuration();
    }

    @Override public boolean isPlaying()
    {
        return mediaPlayer.isPlaying();
    }

    @Override public void pause()
    {
        if(mediaPlayer.isPlaying())
        {
            mediaPlayer.pause();
        }
    }

    @Override public void seekTo(int pos)
    {
        mediaPlayer.seekTo(pos);
    }

    @Override public void start()
    {
        mediaPlayer.start();
    }
}