package com.app.multiplicando;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Vibrator;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class GameActivity2 extends Activity
{
    public final static int APRENDIZ = 150;
    public final static int NORMAL = 50;
    public final static int RETO = 20;

    private boolean continua;
    private ProgressBar progressBar;
    private int progressStatus = 0;
    private TextView pregunta, puntos;
    private final Handler handler = new Handler();
    private Random random;
    private int numA, numB,numC;
    private Button ba, bb, start;
    private int turno;
    private int veces = 0;
    private ArrayList< Integer > mostrados;
    private boolean todas;
    private int tiempoNivel;
    private MediaPlayer tecla_sonido, fin;
    private int puntajeMaximo;
    private int nivel;
    private RelativeLayout layout;

    public GameActivity2()
    {

    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_game_activity2);
        layout = (RelativeLayout)this.findViewById(R.id.layout_game2);

        tecla_sonido = MediaPlayer.create( this, R.raw.sonido );
        fin = MediaPlayer.create( this, R.raw.fin );
        continua = true;
        turno = 0;
        mostrados = new ArrayList< Integer >( 10 );
        random = new Random();
        progressBar = (ProgressBar) findViewById(R.id.progressBarJ2);
        pregunta =(TextView) findViewById(R.id.preguntaJ2);
        puntos =(TextView) findViewById(R.id.puntos);
        puntos.setText("Puntos: " + veces );
        ba = (Button) findViewById(R.id.button1J2);
        numA = getIntent( ).getExtras( ).getInt( "Tabla" );
        todas = ( numA == -1 );

        nivel = getIntent( ).getExtras( ).getInt( "Nivel" );

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

        puntajeMaximo = DataBase.getInstanse( getApplicationContext() ).darMayorPuntaje( 2 , numA, nivel);

        TextView puntajeM = ( TextView ) findViewById( R.id.puntajeMax );
        puntajeM.setText( puntajeMaximo+"");

        ba.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v)
            {
                if(continua)
                {
                    reiniciarBarra();
                    progressBar.setProgress(progressStatus);
                    switch (turno)
                    {
                        case 1:
                            if( tecla_sonido != null && !tecla_sonido.isPlaying( ) )
                                tecla_sonido.start( );
                            veces++;
                            puntos.setText("Puntos: " + veces );
                            generarPregunta();
                            break;
                        default:
                            continua=false;
                            Vibrator vi = (Vibrator) getApplicationContext().getSystemService(Context.VIBRATOR_SERVICE);
                            // Vibrate for 500 milliseconds
                            vi.vibrate(200);
                            mostrarResultado( );
                            break;
                    }
                }
            }
        });
        bb = (Button) findViewById(R.id.button2J2);
        bb.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v)
            {
                if(continua)
                {
                    reiniciarBarra();
                    progressBar.setProgress(progressStatus);
                    switch (turno)
                    {
                        case 1:
                            continua=false;
                            Vibrator vi = (Vibrator) getApplicationContext().getSystemService(Context.VIBRATOR_SERVICE);
                            // Vibrate for 500 milliseconds
                            vi.vibrate(200);
                            mostrarResultado( );
                            break;
                        default:
                            if( tecla_sonido != null && !tecla_sonido.isPlaying( ) )
                                tecla_sonido.start( );
                            veces++;
                            puntos.setText("Puntos: " + veces );
                            generarPregunta();
                            break;
                    }
                }
            }
        });

        start = (Button) findViewById(R.id.start);
        start.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View arg0) {
                layout.setBackgroundResource(R.drawable.background_juego);
                start();
            }
        });
    }

    private void start()
    {
        ba.setVisibility(Button.VISIBLE);
        bb.setVisibility(Button.VISIBLE);
        start.setVisibility(Button.GONE);

        generarPregunta();

        new Thread(new Runnable()
        {
            public void run()
            {
                while (continua)
                {
                    if(tiempoNivel > 0)
                    {
                        if( progressStatus == 100 )
                        {
                            continua = false;
                            Vibrator vi = (Vibrator) getApplicationContext().getSystemService(Context.VIBRATOR_SERVICE);
                            // Vibrate for 500 milliseconds
                            vi.vibrate(200);
                            reiniciarBarra();
                            progressBar.setProgress(progressStatus);
                            mostrarResultado( );
                        }
                        else
                            progressStatus += 1;
                        //TODO
                        // Update the progress bar and display the
                        //current value in the text view
                        handler.post(new Runnable()
                        {
                            public void run()
                            {
                                progressBar.setProgress(progressStatus);
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

    private void generarPregunta()
    {
        if( todas )
            numA = random.nextInt(10) + 1;

        numB = random.nextInt(10) + 1;
        if(mostrados.size()==10)
        {
            mostrados.clear();
        }
        else
        {
            while( mostrados.contains( numB ) )
            {
                numB = random.nextInt( 10 ) + 1;
            }
        }
        mostrados.add( numB );
        if(numB>1)
            numC = numB-1;
        else
            numC = numB+1;

        turno = random.nextInt(2);
        switch (turno) {
            case 1:
                ba.setText(""+numA*numB);
                bb.setText(""+numA*numC);

                break;
            default:
                ba.setText(""+numA*numC);
                bb.setText(""+numA*numB);
                break;
        }
        pregunta.setText(numA + " x " + numB + " = ? ");
    }

    public void mostrarResultado()
    {
        fin.start( );

        if( veces > puntajeMaximo  )
        {
            if( todas )
                DataBase.getInstanse( getApplicationContext() ).actualizarPuntaje( 2, 10, nivel, veces );
            else
                DataBase.getInstanse( getApplicationContext() ).actualizarPuntaje( 2, numA, nivel, veces );
        }

        Bundle b = new Bundle( );
        b.putInt( "Juego", 2 );
        b.putInt( "Puntaje", veces );

        Intent intent = new Intent( GameActivity2.this, ResultadoActivity.class);
        intent.putExtras( b );
        startActivity( intent );

        GameActivity2.this.finish( );
    }

    protected void reiniciarBarra()
    {
        progressStatus = 0;
    }

    @Override
    protected void onDestroy( )
    {
        continua = false;
        super.onDestroy( );
    }
}
