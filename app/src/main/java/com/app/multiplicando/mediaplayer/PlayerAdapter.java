package com.app.multiplicando.mediaplayer;

/**
 * Created by Leebeth Anacona on 3/05/2018.
 */

public interface PlayerAdapter {

    void loadMedia();

    void release();

    boolean isPlaying();

    void play(int resourceId);

    void reset();

    void pause();

    void seekTo(int position);

    void addQueue(int resId);
}
