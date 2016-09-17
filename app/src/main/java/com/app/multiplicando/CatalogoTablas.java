package com.app.multiplicando;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.multiplicando.adapters.Item;
import com.app.multiplicando.adapters.ItemAdapter;
import com.app.multiplicando.interfaces.IAdapterComunication;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Miguel on 24/02/2016.
 */
public class CatalogoTablas extends Fragment implements IAdapterComunication {

    private RecyclerView recycler;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager lManager;
    private static InterstitialAd interstitial;
    private static AdView adView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        View rootView = inflater.inflate(R.layout.activity_catalogo_tablas, container, false);

        adView = (AdView) rootView.findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();
        adView.loadAd(adRequest);

        // Create the interstitial.
        interstitial = new InterstitialAd(getActivity());
        interstitial.setAdUnitId(getString(R.string.intersticial));

        // Create ad request.
        AdRequest adRequestI = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .addTestDevice("E6D875D21E5D7044F76A3C6603BC25D6")//Lo
                .addTestDevice("1D6E14D9D821973C13370F0C46ECD264")//Mi
                .addTestDevice("04675459C2BE09CF506EDD1002143111")//Genymotion tablet
                .addTestDevice("2911693A4370B61588F14C331189465F")//Nexus one
                .build();

        // Begin loading your interstitial.
        interstitial.loadAd(adRequestI);

        // Obtener el Recycler
        recycler = (RecyclerView) rootView.findViewById(R.id.reciclador);
        recycler.setHasFixedSize(true);

        // Usar un administrador para LinearLayout
        lManager = new LinearLayoutManager(rootView.getContext());
        recycler.setLayoutManager(lManager);

        try {
            adapter = new ItemAdapter(CatalogoTablas.this, getItemsTablas());
            recycler.setAdapter(adapter);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rootView;
    }

    private List<Item> getItemsTablas(){
        List<Item> tablas = new ArrayList<>();
        for (int i = 1; i <10 ; i++) {
            tablas.add(new Item("Tabla del ".concat(String.valueOf(i)),
                    "Estudia la tabla del " + i, ""));
        }
        return tablas;
    }

    @Override
    public void callBack(String tabla) {
        Intent intent = null;

        if (intent != null) {
            startActivity(intent);
            if (interstitial.isLoaded()) {
                interstitial.show();
            }
        }
    }

    @Override
    public void onPause() {
        adView.pause();
        super.onPause();
    }

    @Override
    public void onResume() {
        adView.resume();
        super.onResume();
    }

    @Override
    public void onDestroy() {
        adView.destroy();
        super.onDestroy();
    }
}
