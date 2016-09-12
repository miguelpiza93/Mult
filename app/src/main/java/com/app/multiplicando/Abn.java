package com.app.multiplicando;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

/**
 * A placeholder fragment containing a simple view.
 */
public class Abn extends Fragment
{
    private AdView adView;


    public Abn(  )
    {

    }

    @Override
    public View onCreateView( LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState )
    {
        super.onCreate(savedInstanceState);
        View rootView = inflater.inflate( R.layout.abn, container, false );

        // Buscar AdView como recurso y cargar una solicitud.
        adView = (AdView)rootView.findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice("E6D875D21E5D7044F76A3C6603BC25D6")
                .addTestDevice("1D6E14D9D821973C13370F0C46ECD264")
                .build();
        adView.loadAd(adRequest);

        return rootView;
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
