package com.app.multiplicando;


import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerSupportFragment;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;


public class YoutubeFragment extends Fragment implements YouTubePlayer.OnInitializedListener {

    public static final String YOUTUBE_API_KEY = "AIzaSyAdXa9UtZHm5RaXcNV9Ggby6-9FYcIBoyU";
    private static final int RECOVERY_REQUEST = 1;
    private YouTubePlayer YPlayer;
    private MyPlayerStateChangeListener playerStateChangeListener;
    private MyPlaybackEventListener playbackEventListener;
    private YouTubePlayer player;
    private Map<String,String> playList;
    private int positionActual;
    private AdView adView;
    private FragmentActivity myContext;

    @Override
    public void onAttach(Context activity) {

        if (activity instanceof FragmentActivity) {
            myContext = (FragmentActivity) activity;
        }
        super.onAttach(activity);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        View rootView = inflater.inflate(R.layout.fragment_yotube, container, false);

        adView = (AdView) rootView.findViewById(R.id.adView);

        AdRequest adRequest;

        if(BuildConfig.DEBUG){
            adRequest = new AdRequest.Builder()
                    .addTestDevice("C79063870167F0917969F257CD70A642")//Lo
                    .addTestDevice("C23936013EF2D9BA46D36B1A21AF18B6")//Lo
                    .addTestDevice("D6F02D1770A7AFF153B334692F8304F9")//Mi
                    .addTestDevice("04675459C2BE09CF506EDD1002143111")//Genymotion tablet
                    .addTestDevice("2911693A4370B61588F14C331189465F")//Nexus one
                    .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                    .build();
        }
        else{
            adRequest = new AdRequest.Builder().build();
        }

        adView.loadAd(adRequest);

        YouTubePlayerSupportFragment youTubePlayerFragment = YouTubePlayerSupportFragment.newInstance();
        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        transaction.add(R.id.youtube_fragment, youTubePlayerFragment).commit();

        youTubePlayerFragment.initialize(YOUTUBE_API_KEY, this);

        playerStateChangeListener = new MyPlayerStateChangeListener();
        playbackEventListener = new MyPlaybackEventListener();

        final ListView listView = (ListView) rootView.findViewById( R.id.listView1 );

        initPlayList();
        String[] values = getNameSongs();

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_list_item_1, android.R.id.text1, values)
        {
            @Override
            public View getView(int position, View convertView,	ViewGroup parent)
            {
                View view =super.getView(position, convertView, parent);
                TextView textView=(TextView) view.findViewById(android.R.id.text1);
                textView.setTextColor(Color.WHITE);
                return view;
            }
        };
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                reproducirVideo( position );
            }
        });
        return rootView;
    }

    private void initPlayList(){
        playList = new LinkedHashMap<>();
        playList.put("Tabla del 2","rON4CY6Vc9Q"); //https://www.youtube.com/watch?v=aEYcmNhz7Uc
        playList.put("Tabla del 3","Adk2z1bTwIU"); //https://www.youtube.com/watch?v=hq3yfQnllfQ
        playList.put("Tabla del 4","0EP3C0pr0Gk"); //https://www.youtube.com/watch?v=zAlX1V3lK5s
        playList.put("Tabla del 5","5SZuyQCKnmE"); //https://www.youtube.com/watch?v=Q8JmK5z6QD4
        playList.put("Tabla del 6","vsrYhgeX7ls"); //https://www.youtube.com/watch?v=ML8IL77gQ3k
        playList.put("Tabla del 7","2WraVEMCiMA"); //https://www.youtube.com/watch?v=783EsrHchXA
        playList.put("Tabla del 8","grcO7kYYvM"); //https://www.youtube.com/watch?v=SWvBAQf7v8g
        playList.put("Tabla del 9","5yn0Nl2a6Dw"); //https://www.youtube.com/watch?v=cR-Qr1V8e_w
        playList.put("Tabla del 10","meipDRbzDjg"); //https://www.youtube.com/watch?v=B5csN8gQY4E

    }

    private void reproducirVideo(int position) {
        String key = getNameSongs()[position];
        if(positionActual != position)
        {
            player.cueVideo(playList.get(key));
        }
        positionActual = position;
    }

    private String[] getNameSongs(){
        String[] names = new String[playList.size()];
        return playList.keySet().toArray( names );
    }

    private String[] getUrlSong(int position){
        String[] names = new String[playList.size()];

        return playList.keySet().toArray( names );
    }


    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer player, boolean wasRestored) {
        this.player = player;
        player.setPlayerStateChangeListener(playerStateChangeListener);
        player.setPlaybackEventListener(playbackEventListener);

        if (!wasRestored) {
            player.cueVideo(playList.get(getNameSongs()[0])); // Plays https://www.youtube.com/watch?v=zAlX1V3lK5s
            positionActual = 0;
        }
    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult errorReason) {
        if (errorReason.isUserRecoverableError()) {
            errorReason.getErrorDialog(this.getActivity(), RECOVERY_REQUEST).show();
        } else {
            String error = String.format(getString(R.string.player_error), errorReason.toString());
            Toast.makeText(this.getActivity(), error, Toast.LENGTH_SHORT).show();
        }
    }
    /*
        @Override
        public void onActivityResult(int requestCode, int resultCode, Intent data) {
            if (requestCode == RECOVERY_REQUEST) {
                // Retry initialization if user performed a recovery action
                getYouTubePlayerProvider().initialize(YOUTUBE_API_KEY, this);
            }
            //super.onActivityResult(requestCode, resultCode, data);
        }
        protected Provider getYouTubePlayerProvider() {
            return youTubeView;
        }
    */
    private final class MyPlaybackEventListener implements YouTubePlayer.PlaybackEventListener {

        @Override
        public void onPlaying() {
            // Called when playback starts, either due to user action or call to play().
        }

        @Override
        public void onPaused() {
            // Called when playback is paused, either due to user action or call to pause().
        }

        @Override
        public void onStopped() {
            // Called when playback stops for a reason other than being paused.
        }

        @Override
        public void onBuffering(boolean b) {
            // Called when buffering starts or ends.
        }

        @Override
        public void onSeekTo(int i) {
            // Called when a jump in playback position occurs, either
            // due to user scrubbing or call to seekRelativeMillis() or seekToMillis()
        }
    }

    private final class MyPlayerStateChangeListener implements YouTubePlayer.PlayerStateChangeListener {

        @Override
        public void onLoading() {
            // Called when the player is loading a video
            // At this point, it's not ready to accept commands affecting playback such as play() or pause()
        }

        @Override
        public void onLoaded(String s) {
            // Called when a video is done loading.
            // Playback methods such as play(), pause() or seekToMillis(int) may be called after this callback.
        }

        @Override
        public void onAdStarted() {
            // Called when playback of an advertisement starts.
        }

        @Override
        public void onVideoStarted() {
            // Called when playback of the video starts.
        }

        @Override
        public void onVideoEnded() {
            // Called when the video reaches its end.
        }

        @Override
        public void onError(YouTubePlayer.ErrorReason errorReason) {
            // Called when an error occurs.
        }
    }

    @Override
    public void onPause()
    {
        adView.pause();
        super.onPause();
    }

    @Override
    public void onResume()
    {
        super.onResume();
        adView.resume();
    }

    @Override
    public void onDestroy()
    {
        adView.destroy();
        super.onDestroy();
    }
}
