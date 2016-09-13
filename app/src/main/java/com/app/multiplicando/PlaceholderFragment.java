package com.app.multiplicando;

//import com.google.android.gms.ads.*;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;

/**
 * A placeholder fragment containing a simple view.
 */
public class PlaceholderFragment extends Fragment
{
    private int nivel;
    private Bundle b;
    private static InterstitialAd interstitial;
    private int juego;

    /**
     * Returns a new instance of this fragment for the given section number.
     */
    public static PlaceholderFragment newInstance( )
    {
        return new PlaceholderFragment( );
    }

    public PlaceholderFragment(  )
    {

    }

    @Override
    public View onCreateView( LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState )
    {
        super.onCreate(savedInstanceState);
        View rootView = inflater.inflate( R.layout.fragment_main, container, false );

        // Create the interstitial.
        interstitial = new InterstitialAd( getActivity( ) );
        interstitial.setAdUnitId("ca-app-pub-6455142835794611/5671550288");

        // Create ad request.
        AdRequest adRequestI = new AdRequest.Builder()
                //.addTestDevice( AdRequest.DEVICE_ID_EMULATOR )
                .addTestDevice("E6D875D21E5D7044F76A3C6603BC25D6")
                .build();

        // Begin loading your interstitial.
        interstitial.loadAd(adRequestI);

        b = new Bundle( );

        final TextView text = (TextView)rootView.findViewById( R.id.txt_Menu );
        Typeface typeface = Typeface.createFromAsset( getActivity( ).getAssets( ), "fonts/BRADHIT0.ttf" );
        text.setTypeface( typeface );

        final TextView textNivel = (TextView)rootView.findViewById( R.id.text_Nivel );
        textNivel.setTypeface( typeface );

        final Spinner spinner = (Spinner) rootView.findViewById(R.id.nivel_spinner);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapterSpiner = ArrayAdapter.createFromResource(getActivity( ),
                R.array.nivel_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapterSpiner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapterSpiner);

        final ListView listView = (ListView) rootView.findViewById( R.id.listView );
/*
        final RadioGroup grupo = (RadioGroup) rootView.findViewById( R.id.Grupo_Nivel );

        final RadioButton sinTiempo = (RadioButton) rootView.findViewById( R.id.SinTiempo );
        sinTiempo.setOnClickListener( new OnClickListener( )
        {
            @Override
            public void onClick( View arg0 )
            {
                listView.setVisibility( ListView.VISIBLE );
                text.setVisibility( TextView.VISIBLE );
                textNivel.setVisibility( TextView.GONE );
                grupo.setVisibility( RadioGroup.GONE );

                nivel = 0;
                Intent intent;
                switch (juego) {
                    case 2:
                        intent = new Intent( getActivity( ), GameActivity2.class);
                        break;
                    default:
                        intent = new Intent( getActivity( ), GameActivity.class);
                        break;
                }

                b.putInt( "Nivel", nivel );
                intent.putExtras( b );
                startActivity( intent );
                //getActivity( ).finish( );

                if (interstitial.isLoaded())
                {
                    interstitial.show();
                }
            }
        } );

        final RadioButton aprendiz = (RadioButton) rootView.findViewById( R.id.Aprendiz );
        aprendiz.setOnClickListener( new OnClickListener( )
        {
            @Override
            public void onClick( View arg0 )
            {
                listView.setVisibility( ListView.VISIBLE );
                text.setVisibility( TextView.VISIBLE );
                textNivel.setVisibility( TextView.GONE );
                grupo.setVisibility( RadioGroup.GONE );

                nivel = 1;
                Intent intent;
                switch (juego) {
                    case 2:
                        intent = new Intent( getActivity( ), GameActivity2.class);
                        break;
                    default:
                        intent = new Intent( getActivity( ), GameActivity.class);
                        break;
                }

                b.putInt( "Nivel", nivel );
                intent.putExtras( b );
                startActivity( intent );

                if (interstitial.isLoaded())
                {
                    interstitial.show();
                }
            }
        } );

        final RadioButton normal = (RadioButton) rootView.findViewById( R.id.Normal );
        normal.setOnClickListener( new OnClickListener( )
        {
            @Override
            public void onClick( View arg0 )
            {
                listView.setVisibility( ListView.VISIBLE );
                text.setVisibility( TextView.VISIBLE );
                textNivel.setVisibility( TextView.GONE );
                grupo.setVisibility( RadioGroup.GONE );

                nivel = 2;
                Intent intent;
                switch (juego) {
                    case 2:
                        intent = new Intent( getActivity( ), GameActivity2.class);
                        break;
                    default:
                        intent = new Intent( getActivity( ), GameActivity.class);
                        break;
                }
                b.putInt( "Nivel", nivel );
                intent.putExtras( b );
                startActivity( intent );
                //	getActivity( ).finish( );

                if (interstitial.isLoaded())
                {
                    interstitial.show();
                }
            }
        } );

        final RadioButton reto = (RadioButton) rootView.findViewById( R.id.Reto );
        reto.setOnClickListener( new OnClickListener( )
        {
            @Override
            public void onClick( View arg0 )
            {
                listView.setVisibility( ListView.VISIBLE );
                text.setVisibility( TextView.VISIBLE );
                textNivel.setVisibility( TextView.GONE );
                grupo.setVisibility( RadioGroup.GONE );

                nivel = 3;
                Intent intent;
                switch (juego) {
                    case 2:
                        intent = new Intent( getActivity( ), GameActivity2.class);
                        break;
                    default:
                        intent = new Intent( getActivity( ), GameActivity.class);
                        break;
                }
                b.putInt( "Nivel", nivel );
                intent.putExtras( b );
                startActivity( intent );
                //	getActivity( ).finish( );

                if (interstitial.isLoaded())
                {
                    interstitial.show();
                }
            }
        } );
*/


        final Button j1 = (Button) rootView.findViewById( R.id.buttonj1 );
        final Button j2 = (Button) rootView.findViewById( R.id.buttonj2 );
        j2.setOnClickListener( new OnClickListener( )
        {
            @Override
            public void onClick( View arg0 )
            {
                juego = 2;
                j1.setVisibility(Button.GONE);
                j2.setVisibility(Button.GONE);
                textNivel.setVisibility( TextView.VISIBLE );
                iniciarJuego(spinner.getSelectedItem().toString());
            }
        } );

        j1.setOnClickListener( new OnClickListener( )
        {
            @Override
            public void onClick( View arg0 )
            {
                juego = 1;
                j1.setVisibility(Button.GONE);
                j2.setVisibility(Button.GONE);
                textNivel.setVisibility( TextView.VISIBLE );
                iniciarJuego(spinner.getSelectedItem().toString());
            }
        } );

        String[] values = new String[] { "Tabla del 1", "Tabla del 2", "Tabla del 3", "Tabla del 4", "Tabla del 5",
                "Tabla del 6", "Tabla del 7", "Tabla del 8", "Tabla del 9", "Todas las tablas" };

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
                // ListView Clicked item index
                int tabla = position + 1;
                b.putInt( "Tabla", tabla );
                listView.setVisibility( ListView.GONE );
                text.setVisibility( TextView.GONE );
                // j1.setVisibility( Button.VISIBLE );
                // j2.setVisibility( Button.VISIBLE );
            }
        });

        return rootView;
    }

    private void iniciarJuego(String nivelSel){
        if (nivelSel.equals(getString(R.string.SinTiempo))){
            nivel = 0;
        }
        else if(nivelSel.equals(getString(R.string.Aprendiz))){
            nivel = 1;
        }
        else if(nivelSel.equals(getString(R.string.Normal))){
            nivel = 2;
        }
        else{
            nivel = 3;
        }



        Intent intent;
        switch (juego) {
            case 2:
                intent = new Intent( getActivity( ), GameActivity2.class);
                break;
            default:
                intent = new Intent( getActivity( ), GameActivity.class);
                break;
        }
        b.putInt( "Nivel", nivel );
        intent.putExtras( b );
        startActivity( intent );
        //	getActivity( ).finish( );

        if (interstitial.isLoaded())
        {
            interstitial.show();
        }
    }

}
