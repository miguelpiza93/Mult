package com.app.multiplicando;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

public class ResultadoActivity extends AppCompatActivity
{
    @Override
    protected void onCreate( Bundle savedInstanceState )
    {
        super.onCreate( savedInstanceState );
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView( R.layout.activity_resultado );

        int juego = getIntent( ).getExtras( ).getInt( "Juego" );
        int correctas = getIntent( ).getExtras( ).getInt( "Puntaje" );

        TextView text = findViewById( R.id.txt_Resultado );
        TextView resultText = findViewById( R.id.tvScoreResultGame2 );

        Typeface typeface = Typeface.createFromAsset( getAssets( ), "fonts/homeheart.ttf" );
        //text.setTypeface( typeface, Typeface.BOLD );

        //resultText.setTypeface(typeface, Typeface.BOLD);

        if( juego == 1 )
        {
            if( correctas > 5 )
                text.setText( "Súper!, Tu resultado es" );
            else
                text.setText( "Puedes hacerlo mejor.\n Tu resultado es" );

            float result = (float)correctas/(float)2.0;
            resultText.setText(String.valueOf(result));
        }
        else
        {
            if( correctas > 10 )
                text.setText( "Súper!, tu resultado es" );
            else if (correctas < 5)
                text.setText( "Puedes hacerlo mejor.\n Tu resultado es" );
            else
                text.setText( "Tu resultado es" );

            resultText.setText(String.valueOf(correctas));
        }

        ImageView home = findViewById( R.id.home );
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
