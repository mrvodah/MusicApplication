package uitcourse.j11.nt118.appmusichtcl.Service;

import android.app.Service;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;

import java.io.IOException;

public class PlayerService extends Service {

    Thread t;
    Handler h;
    String url;
    MediaPlayer mediaPlayer;
    public PlayerService() {
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        url = intent.getStringExtra("songurl");

        playAudio();
        return super.onStartCommand(intent, flags, startId);
    }

    private void playAudio()
    {
        if(h==null)
        {
            t = new Thread(new Runnable() {
                @Override
                public void run() {
                    Looper.prepare();
                    h  = new Handler();
                    h.post(playAudioFromUrl());
                    Looper.loop();
                }
            });
            t.start();
        }
        else
        {
            h.post(playAudioFromUrl());
        }

    }

    private Runnable playAudioFromUrl()
    {
        return new Runnable() {
            @Override
            public void run() {
                mediaPlayer = new MediaPlayer();
                mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                try {
                    mediaPlayer.setDataSource(url);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    mediaPlayer.prepare();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                mediaPlayer.start();

            }
        };
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
