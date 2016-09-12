package com.app.multiplicando;

//import com.google.android.gms.ads.*;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
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
    private View rootView;

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
        rootView = inflater.inflate( R.layout.fragment_main, container, false );

        // Create the interstitial.
        interstitial = new InterstitialAd( getActivity( ) );
        interstitial.setAdUnitId("ca-app-pub-6455142835794611/5671550288");

        // Create ad request.
        AdRequest adRequestI = new AdRequest.Builder()
                //.addTestDevice( AdRequest.DEVICE_ID_EMULATOR )
                .addTestDevice("E6D875D21E5D7044F76A3C6603BC25D6")
                .addTestDevice("1D6E14D9D821973C13370F0C46ECD264")
                .build();

        // Begin loading your interstitial.
        interstitial.loadAd(adRequestI);

        b = new Bundle( );

        final TextView text = (TextView)rootView.findViewById( R.id.txt_Menu );
        Typeface typeface = Typeface.createFromAsset( getActivity( ).getAssets( ), "fonts/homeheart.ttf" );
        text.setTypeface( typeface );

        final TextView textNivel = (TextView)rootView.findViewById( R.id.text_Nivel );
        textNivel.setTypeface( typeface );

        final ListView listView = (ListView) rootView.findViewById( R.id.listView );

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

        final Button j1 = (Button) rootView.findViewById( R.id.buttonj1 );
        final Button j2 = (Button) rootView.findViewById( R.id.buttonj2 );
        j2.setOnClickListener( new OnClickListener( )
        {
            @Override
            public void onClick( View arg0 )
            {
                aprendiz.setChecked( false );
                normal.setChecked( false );
                reto.setChecked( false );
                juego = 2;
                j1.setVisibility(Button.GONE);
                j2.setVisibility(Button.GONE);
                textNivel.setVisibility( TextView.VISIBLE );
                grupo.setVisibility( RadioGroup.VISIBLE );
            }
        } );

        j1.setOnClickListener( new OnClickListener( )
        {
            @Override
            public void onClick( View arg0 )
            {
                aprendiz.setChecked( false );
                normal.setChecked( false );
                reto.setChecked( false );
                juego = 1;
                j1.setVisibility(Button.GONE);
                j2.setVisibility(Button.GONE);
                textNivel.setVisibility( TextView.VISIBLE );
                grupo.setVisibility( RadioGroup.VISIBLE );
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

                textView.setTextColor(Color.BLACK);

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

                LinearLayout layout=(LinearLayout)rootView.findViewById(R.id.mainLayout);
                layout.setBackgroundResource(R.drawable.tablas_opaca);

                listView.setVisibility( ListView.GONE );
                text.setVisibility( TextView.GONE );
                j1.setVisibility( Button.VISIBLE );
                j2.setVisibility( Button.VISIBLE );
            }
        });

        return rootView;
    }
}
