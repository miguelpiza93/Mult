package com.app.multiplicando;

import java.util.ArrayList;
import java.util.Random;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Vibrator;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.TextView;

public class GameActivity extends Activity
{
    public final static int APRENDIZ = 150;

    public final static int NORMAL = 100;

    public final static int RETO = 50;

    private int mProgressStatus;

    private int tiempoNivel;

    private int intentos_restantes;

    private int correctas;

    private int numA, numB;

    private Random random;

    private ProgressBar mProgress;

    private Handler mHandler;

    private int puntajeMaximo;

    private boolean continua;

    private boolean todas;

    private ArrayList< Integer > mostrados;

    private MediaPlayer tecla_sonido, fin;

    private int nivel;

    private TextView labresp;

    @Override
    protected void onCreate( Bundle savedInstanceState )
    {
        super.onCreate( savedInstanceState );
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView( R.layout.activity_game );

        mHandler = new Handler( )
        {
            @Override
            public void handleMessage(Message msg)
            {
                nextQuestion( );
            }
        };

        tecla_sonido = MediaPlayer.create( this, R.raw.sonido );
        fin = MediaPlayer.create( this, R.raw.fin );

        random = new Random( );
        mProgressStatus = 0;
        correctas = 0;
        intentos_restantes = 10;
        mostrados = new ArrayList< Integer >( 10 );

        numA = getIntent( ).getExtras( ).getInt( "Tabla" );
        todas = ( numA == 10 );

        nivel = getIntent( ).getExtras( ).getInt( "Nivel" );
        puntajeMaximo = DataBase.getInstanse( getApplicationContext() ).darMayorPuntaje( 1 , numA, nivel );

        switch ( nivel )
        {
            case 1:
                tiempoNivel = APRENDIZ;
                break;
            case 2:
                tiempoNivel = NORMAL;
                break;
            case 3:
                tiempoNivel = RETO;
                break;
            default:
                tiempoNivel = 0;
                break;
        }

        labresp = ( TextView ) findViewById( R.id.txtAnswer );

        TextView puntajeM = ( TextView ) findViewById( R.id.puntajeMax );
        puntajeM.setText( ((double)puntajeMaximo)/2.0 + "");

        Button but0 = (Button) findViewById( R.id.Button0 );
        Button but1 = (Button) findViewById( R.id.Button01 );
        Button but2 = (Button) findViewById( R.id.Button02 );
        Button but3 = (Button) findViewById( R.id.Button03 );
        Button but4 = (Button) findViewById( R.id.Button04 );
        Button but5 = (Button) findViewById( R.id.Button05 );
        Button but6 = (Button) findViewById( R.id.Button06 );
        Button but7 = (Button) findViewById( R.id.Button07 );
        Button but8 = (Button) findViewById( R.id.Button08 );
        Button but9 = (Button) findViewById( R.id.Button09 );
        Button but10 = (Button) findViewById( R.id.Button10 );
        Button butOk = (Button) findViewById( R.id.buttonOk );

        but0.setOnClickListener( new OnClickListener( )
        {
            @Override
            public void onClick( View v )
            {
                if( continua )
                {
                    if( tecla_sonido != null && !tecla_sonido.isPlaying( ) )
                        tecla_sonido.start( );
                    actualizarRespuesta("0");
                }
            }
        } );

        but1.setOnClickListener( new OnClickListener( )
        {
            @Override
            public void onClick( View v )
            {
                if( continua )
                {
                    if( tecla_sonido != null && !tecla_sonido.isPlaying( ) )
                        tecla_sonido.start( );
                    actualizarRespuesta("1");
                }
            }
        } );

        but2.setOnClickListener( new OnClickListener( )
        {
            @Override
            public void onClick( View v )
            {
                if( continua )
                {
                    if( tecla_sonido != null && !tecla_sonido.isPlaying( ) )
                        tecla_sonido.start( );
                    actualizarRespuesta("2");
                }
            }
        } );

        but3.setOnClickListener( new OnClickListener( )
        {
            @Override
            public void onClick( View v )
            {
                if( continua )
                {
                    if( tecla_sonido != null && !tecla_sonido.isPlaying( ) )
                        tecla_sonido.start( );
                    String r = labresp.getText( ).toString( );
                    actualizarRespuesta("3");
                }
            }
        } );

        but4.setOnClickListener( new OnClickListener( )
        {
            @Override
            public void onClick( View v )
            {
                if( continua )
                {
                    if( tecla_sonido != null && !tecla_sonido.isPlaying( ) )
                        tecla_sonido.start( );
                    actualizarRespuesta("4");
                }
            }
        } );

        but5.setOnClickListener( new OnClickListener( )
        {
            @Override
            public void onClick( View v )
            {
                if( continua )
                {
                    if( tecla_sonido != null && !tecla_sonido.isPlaying( ) )
                        tecla_sonido.start( );
                    actualizarRespuesta("5");
                }
            }
        } );

        but6.setOnClickListener( new OnClickListener( )
        {
            @Override
            public void onClick( View v )
            {
                if( continua )
                {
                    if( tecla_sonido != null && !tecla_sonido.isPlaying( ) )
                        tecla_sonido.start( );
                    actualizarRespuesta("6");
                }
            }
        } );

        but7.setOnClickListener( new OnClickListener( )
        {
            @Override
            public void onClick( View v )
            {
                if( continua )
                {
                    if( tecla_sonido != null && !tecla_sonido.isPlaying( ) )
                        tecla_sonido.start( );
                    actualizarRespuesta("7");
                }
            }
        } );

        but8.setOnClickListener( new OnClickListener( )
        {
            @Override
            public void onClick( View v )
            {
                if( continua )
                {
                    if( tecla_sonido != null && !tecla_sonido.isPlaying( ) )
                        tecla_sonido.start( );
                    actualizarRespuesta("8");
                }
            }
        } );

        but9.setOnClickListener( new OnClickListener( )
        {
            @Override
            public void onClick( View v )
            {
                if( continua )
                {
                    if( tecla_sonido != null && !tecla_sonido.isPlaying( ) )
                        tecla_sonido.start( );
                    actualizarRespuesta("9");
                }
            }
        } );

        but10.setOnClickListener( new OnClickListener( )
        {
            @Override
            public void onClick( View v )
            {
                if( tecla_sonido != null && !tecla_sonido.isPlaying( ) )
                    tecla_sonido.start( );
                actualizarRespuesta(null);
            }
        } );

        butOk.setOnClickListener( new OnClickListener( )
        {
            @Override
            public void onClick( View v )
            {
                if( continua )
                {
                    checkAnswer( );
                    mProgressStatus = 0;
                    if ( intentos_restantes == 0 )
                    {
                        continua = false;
                        mostrarResultado( );
                    }
                    else
                        nextQuestion( );
                }
            }
        } );

        mProgress = ( ProgressBar ) findViewById( R.id.progressBar );

        continua = true;

        nextQuestion( );

        new Thread(new Runnable()
        {
            public void run()
            {
                while (continua)
                {
                    if(tiempoNivel > 0)
                    {
                        if( mProgressStatus == 100 )
                        {
                            checkAnswer( );
                            mProgressStatus = 0;
                            mProgress.setProgress(mProgressStatus);

                            if ( intentos_restantes == 0 )
                            {
                                continua = false;
                                mostrarResultado( );
                            }
                            else
                                mHandler.sendMessage( new Message( ) );
                        }
                        else
                            mProgressStatus += 1;
                        // Update the progress bar and display the
                        //current value in the text view
                        mHandler.post(new Runnable()
                        {
                            public void run()
                            {
                                mProgress.setProgress(mProgressStatus);
                            }
                        });
                        try
                        {
                            Thread.sleep(tiempoNivel);
                        }
                        catch (InterruptedException e)
                        {
                            e.printStackTrace();
                        }
                    }
                }
            }


        }).start();

    }

    private void mostrarResultado( )
    {
        fin.start( );

        if( correctas > puntajeMaximo )
        {
            if( todas )
                DataBase.getInstanse( getApplicationContext() ).actualizarPuntaje( 1, 10, nivel, correctas );
            else
                DataBase.getInstanse( getApplicationContext() ).actualizarPuntaje( 1, numA, nivel, correctas );
        }

        Bundle b = new Bundle( );
        b.putInt( "Juego", 1 );
        b.putInt( "Puntaje", correctas );

        Intent intent = new Intent( GameActivity.this, ResultadoActivity.class);
        intent.putExtras( b );
        startActivity( intent );

        GameActivity.this.finish( );
    }

    private void actualizarRespuesta(String resp){
        String r = labresp.getText( ).toString( );
        if( resp != null && !resp.isEmpty() ){
            if( r.equals( "?" ) )
            {
                labresp.setText( resp );
            }
            else {
                if(r.length() < 2)
                    labresp.setText(r + resp);
            }
        }
        else{
            if( !r.equals( "?" ) && r.length( ) > 0 )
            {
                labresp.setText( r.substring( 0, r.length( ) - 1 ) );
            }
        }
    }

    private void nextQuestion( )
    {
        if( todas )
            numA = random.nextInt( 10 ) + 1;

        numB = random.nextInt( 10 ) + 1;

        while( mostrados.contains( numB ) )
        {
            numB = random.nextInt( 10 ) + 1;
        }
        mostrados.add( numB );

        final TextView labQuestion = ( TextView ) findViewById( R.id.labQuestion );
        String question =  numA + " x " + numB + " =";
        labQuestion.setText( question );

        TextView labresp = ( TextView ) findViewById( R.id.txtAnswer );
        labresp.setText( "?" );
    }

    private void checkAnswer( )
    {
        int result = numA * numB;

        TextView answer = ( TextView ) findViewById( R.id.txtAnswer );
        RatingBar ratingBar = ( RatingBar ) findViewById( R.id.ratingBar );

        String answerText = answer.getText( ).toString( );

        try
        {
            int answerNumber = Integer.parseInt( answerText );

            if ( answerNumber == result )
            {
                correctas++;
            }
            else
            {
                Vibrator vi = (Vibrator) getApplicationContext().getSystemService(Context.VIBRATOR_SERVICE);
                // Vibrate for 500 milliseconds
                vi.vibrate(200);
            }
        }
        catch ( NumberFormatException e )
        {
            Vibrator vi = (Vibrator) getApplicationContext().getSystemService(Context.VIBRATOR_SERVICE);
            // Vibrate for 500 milliseconds
            vi.vibrate(200);
        }
        intentos_restantes--;

        float rating = (float)correctas/(float)2.0;

        ratingBar.setRating( rating );
    }

    @Override
    protected void onDestroy( )
    {
        continua = false;
        super.onDestroy( );
    }

}
