package com.app.multiplicando;

//import com.google.android.gms.ads.*;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;

/**
 * A placeholder fragment containing a simple view.
 */
public class PlaceholderFragment extends Fragment {
    private int nivel;
    private Bundle b;
    private static InterstitialAd interstitial;
    private int juego;
    private Button game1;
    private Button game2;
    private Button game3;
    private TextView text;
    private ListView listView;
    private TextView textNivel;

    /**
     * Atributo para identificar si estamos en la pantalla principal
     * Con el fin de saber si hacer visible la lista o salir de la app
     */
    private boolean pantallaPrincipal;

    /**
     * Spiner que contiene la seleccion de la dificultad
     */
    private Spinner spinnerNivel;

    /**
     * Returns a new instance of this fragment for the given section number.
     */
    public static PlaceholderFragment newInstance() {
        return new PlaceholderFragment();
    }

    public PlaceholderFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);

        // Create the interstitial.
        interstitial = new InterstitialAd(getActivity());
        interstitial.setAdUnitId("ca-app-pub-6455142835794611/5671550288");

        // Create ad request.
        AdRequest adRequestI;

        if (BuildConfig.DEBUG) {
            adRequestI = new AdRequest.Builder()
                    .addTestDevice("C79063870167F0917969F257CD70A642")//Lo
                    .addTestDevice("C23936013EF2D9BA46D36B1A21AF18B6")//Lo
                    .addTestDevice("1D6E14D9D821973C13370F0C46ECD264")//Mi
                    .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                    .addTestDevice("04675459C2BE09CF506EDD1002143111")//Genymotion tablet
                    .addTestDevice("2911693A4370B61588F14C331189465F")//Nexus one
                    .build();
        } else {
            adRequestI = new AdRequest.Builder()
                    .build();
        }

        // Begin loading your interstitial.
        interstitial.loadAd(adRequestI);

        pantallaPrincipal = true;

        spinnerNivel = rootView.findViewById(R.id.nivel_spinner);

        b = new Bundle();

        text = rootView.findViewById(R.id.txt_Menu);
        Typeface typeface = Typeface.createFromAsset(getActivity().getAssets(), "fonts/homeheart.ttf");
        text.setTypeface(typeface);

        textNivel = rootView.findViewById(R.id.text_Nivel);
        textNivel.setTypeface(typeface);

        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapterSpiner = ArrayAdapter.createFromResource(getActivity(),
                R.array.nivel_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapterSpiner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner

        spinnerNivel.setAdapter(adapterSpiner);
        spinnerNivel.setSelection(adapterSpiner.getPosition(getString(R.string.Normal)));

        listView = rootView.findViewById(R.id.listView);

        game1 = rootView.findViewById(R.id.buttonj1);
        game2 = rootView.findViewById(R.id.buttonj2);
        game3 = rootView.findViewById(R.id.butGame3);
        game2.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View arg0) {
                juego = 2;
                iniciarJuego();
            }
        });

        game1.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View arg0) {
                juego = 1;
                iniciarJuego();
            }
        });

        game3.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                juego = 3;
                iniciarJuego();
            }
        });

        String[] values = new String[]{"Todas las tablas", "Tabla del 1", "Tabla del 2", "Tabla del 3", "Tabla del 4", "Tabla del 5",
                "Tabla del 6", "Tabla del 7", "Tabla del 8", "Tabla del 9", "Tabla del 10"};

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_list_item_1, android.R.id.text1, values);
        /*ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity( ), android.R.layout.simple_list_item_1, android.R.id.text1, values)
        {

            @Override
            public View getView(int position, View convertView,	ViewGroup parent)
            {
                View view =super.getView(position, convertView, parent);

                TextView textView=(TextView) view.findViewById(android.R.id.text1);

                textView.setTextColor(Color.WHITE);

                return view;
            }
        };*/
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // ListView Clicked item index
                int tabla = position == 0 ? -1 : position; //-1 = todas las tablas
                b.putInt("Tabla", tabla);
                cambiarPantallaPrincipal(false);
            }
        });

        return rootView;
    }

    private void iniciarJuego() {
        String nivelSel = spinnerNivel.getSelectedItem().toString();
        if (nivelSel.equals(getString(R.string.SinTiempo))) {
            nivel = 0;
        } else if (nivelSel.equals(getString(R.string.Aprendiz))) {
            nivel = 1;
        } else if (nivelSel.equals(getString(R.string.Normal))) {
            nivel = 2;
        } else {
            nivel = 3;
        }

        Intent intent;
        switch (juego) {
            case 2:
                intent = new Intent(getActivity(), GameActivity2.class);
                break;
            case 3:
                intent = new Intent(getActivity(), TapGame.class);
                break;
            default:
                intent = new Intent(getActivity(), GameActivity.class);
                break;
        }
        b.putInt("Nivel", nivel);
        intent.putExtras(b);
        startActivity(intent);
        //	getActivity( ).finish( );

        /**if (interstitial.isLoaded())
         {
         interstitial.show();
         }**/
    }

    public void cambiarPantallaPrincipal(boolean mostrarInicial) {
        LinearLayout layout = (LinearLayout) getActivity().findViewById(R.id.mainLayout);
        GridLayout gridGames = getActivity().findViewById(R.id.gridGames);
        if (mostrarInicial) {
            listView.setVisibility(ListView.VISIBLE);
            text.setVisibility(TextView.VISIBLE);
            textNivel.setVisibility(TextView.GONE);
            gridGames.setVisibility(View.GONE);
            spinnerNivel.setVisibility(Spinner.GONE);
            layout.setBackgroundResource(R.drawable.tablas);
            pantallaPrincipal = true;
        } else {
            listView.setVisibility(ListView.GONE);
            text.setVisibility(TextView.GONE);
            textNivel.setVisibility(TextView.VISIBLE);
            gridGames.setVisibility(View.VISIBLE);
            spinnerNivel.setVisibility(Spinner.VISIBLE);
            layout.setBackgroundResource(R.drawable.tablas_opaca);
            pantallaPrincipal = false;
        }
    }

    public boolean isInHome() {
        return pantallaPrincipal;
    }
}
