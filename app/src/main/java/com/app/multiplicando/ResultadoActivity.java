package com.app.multiplicando;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class ResultadoActivity extends Activity
{
    @Override
    protected void onCreate( Bundle savedInstanceState )
    {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_resultado );

        int juego = getIntent( ).getExtras( ).getInt( "Juego" );
        int correctas = getIntent( ).getExtras( ).getInt( "Puntaje" );

        TextView text = (TextView) findViewById( R.id.txt_Resultado );

        Typeface typeface = Typeface.createFromAsset( getAssets( ), "fonts/homeheart.ttf" );
        text.setTypeface( typeface, Typeface.BOLD );

        if( juego == 1 )
        {
            if( correctas > 5 )
                text.setText( "Bien!, Tu resultado es:\n" + (float)correctas/(float)2.0 );
            else
                text.setText( "Puedes hacerlo mejor!\nTu resultado es: " + (float)correctas/(float)2.0 );
        }
        else
        {
            text.setText( "Tu puntaje es: " + correctas );
        }

        ImageView home = (ImageView) findViewById( R.id.home );
        home.setOnClickListener( new View.OnClickListener( )
        {

            @Override
            public void onClick( View arg0 )
            {
                ResultadoActivity.this.finish( );
            }
        } );

    }

}
