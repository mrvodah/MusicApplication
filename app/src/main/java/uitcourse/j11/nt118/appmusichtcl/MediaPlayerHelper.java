package uitcourse.j11.nt118.appmusichtcl;

import android.media.MediaPlayer;

public class MediaPlayerHelper {
    private static MediaPlayer mediaPlayer;

    public static MediaPlayer getMediaPlayer() {
        if(mediaPlayer == null) {
            synchronized (MediaPlayerHelper.class) {
                if(mediaPlayer == null) {
                    mediaPlayer = new MediaPlayer();
                }
            }
        }
        return mediaPlayer;
    }
}
