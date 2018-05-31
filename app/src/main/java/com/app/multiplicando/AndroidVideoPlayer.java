package com.app.multiplicando;

import android.graphics.Color;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

public class AndroidVideoPlayer extends Fragment
{
    MediaPlayer mediaPlayer;
    SurfaceView surfaceView;
    SurfaceHolder surfaceHolder;
    boolean pausing = false;

    public AndroidVideoPlayer( )
    {

    }

    /** Called when the activity is first created. */
    @Override
    public View onCreateView( LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState )
    {
        super.onCreate(savedInstanceState);
        final View rootView = inflater.inflate( R.layout.fragment_video, container, false );

        VideoView videoView = (VideoView) rootView.findViewById(R.id.videoview);
        MediaController mc = new MediaController( getActivity( ) );

        videoView.setMediaController(mc);

        final ListView listView = (ListView) rootView.findViewById( R.id.listView2 );
        String[] values = new String[] { "Sin selección", "Tabla del 2", "Tabla del 3", "Tabla del 4", "Tabla del 5",
                "Tabla del 6", "Tabla del 7", "Tabla del 8", "Tabla del 9", "Tabla del 10" };

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity( ), android.R.layout.simple_list_item_1, android.R.id.text1, values)
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

        listView.setOnItemClickListener(new OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                reproducirTabla( position, ( VideoView ) rootView.findViewById( R.id.videoview ) );
            }
        });

        return rootView;
    }

    private void reproducirTabla( int tabla, VideoView mVideoView )
    {
        String uriPath;
        Uri uri;
        /**
        switch ( tabla )
        {
            case 0:
                mVideoView.stopPlayback( );
                break;
            case 1:
                uriPath = "android.resource://com.app.multiplicando/"+R.raw.t2;
                uri = Uri.parse(uriPath);
                mVideoView.setVideoURI(uri);
                mVideoView.requestFocus();
                mVideoView.start();
                break;
            case 2:
                uriPath = "android.resource://com.app.multiplicando/"+R.raw.t3;
                uri = Uri.parse(uriPath);
                mVideoView.setVideoURI(uri);
                mVideoView.requestFocus();
                mVideoView.start();
                break;
            case 3:
                uriPath = "android.resource://com.app.multiplicando/"+R.raw.t4;
                uri = Uri.parse(uriPath);
                mVideoView.setVideoURI(uri);
                mVideoView.requestFocus();
                mVideoView.start();
                break;
            case 4:
                uriPath = "android.resource://com.app.multiplicando/"+R.raw.t5;
                uri = Uri.parse(uriPath);
                mVideoView.setVideoURI(uri);
                mVideoView.requestFocus();
                mVideoView.start();
                break;
            case 5:
                uriPath = "android.resource://com.app.multiplicando/"+R.raw.t6;
                uri = Uri.parse(uriPath);
                mVideoView.setVideoURI(uri);
                mVideoView.requestFocus();
                mVideoView.start();
                break;
            case 6:
                uriPath = "android.resource://com.app.multiplicando/"+R.raw.t7;
                uri = Uri.parse(uriPath);
                mVideoView.setVideoURI(uri);
                mVideoView.requestFocus();
                mVideoView.start();
                break;
            case 7:
                uriPath = "android.resource://com.app.multiplicando/"+R.raw.t8;
                uri = Uri.parse(uriPath);
                mVideoView.setVideoURI(uri);
                mVideoView.requestFocus();
                mVideoView.start();
                break;
            case 8:
                uriPath = "android.resource://com.app.multiplicando/"+R.raw.t9;
                uri = Uri.parse(uriPath);
                mVideoView.setVideoURI(uri);
                mVideoView.requestFocus();
                mVideoView.start();
                break;
            case 9:
                uriPath = "android.resource://com.app.multiplicando/"+R.raw.t10;
                uri = Uri.parse(uriPath);
                mVideoView.setVideoURI(uri);
                mVideoView.requestFocus();
                mVideoView.start();
                break;

            default:
                break;
        }**/
    }
}
