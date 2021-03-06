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
        //TODO: quitar test de anuncios
        adView = (AdView)rootView.findViewById(R.id.adView);

        AdRequest adRequest;

        if(BuildConfig.DEBUG){
            adRequest = new AdRequest.Builder()
                    .addTestDevice("C79063870167F0917969F257CD70A642")//Lo
                    .addTestDevice("C23936013EF2D9BA46D36B1A21AF18B6")//Lo
                    .addTestDevice("1D6E14D9D821973C13370F0C46ECD264")//Mi
                    .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                    .addTestDevice("04675459C2BE09CF506EDD1002143111")//Genymotion tablet
                    .addTestDevice("2911693A4370B61588F14C331189465F")//Nexus one
                    .build();
        }
        else{
            adRequest = new AdRequest.Builder()
                .build();
        }

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
