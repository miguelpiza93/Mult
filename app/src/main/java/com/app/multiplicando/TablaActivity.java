package com.app.multiplicando;

import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.app.multiplicando.mediaplayer.MediaPlayerHolder;
import com.app.multiplicando.mediaplayer.PlaybackInfoListener;
import com.app.multiplicando.mediaplayer.PlayerAdapter;

import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;

public class TablaActivity extends AppCompatActivity {
    public static final String TAG = "TablaActivity";
    private static final String PRACTICE = "prac";

    private HashMap<Integer, Integer> tablesMap;
    private int actualTable;
    private Button butBack;
    private Button butNext;
    private Button butPractice;
    private Button butPausePractice;
    private SeekBar seekBar;
    private TextView tvTablePractice;
    private TextView tvTitle;
    private Button butClosePractice;
    private boolean isPracticing;
    private PlayerAdapter mPlayerAdapter;
    private boolean mUserIsSeeking;

    private boolean pausePractice;
    private int actualNumber;
    private Timer timer;
    private TimerTask timerTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_tabla);

        System.gc();
        actualTable = getIntent().getIntExtra("Tabla", 0);
        initializeComponents();
        addListenerBackNextButtons();
    }

    private void addListenerBackNextButtons() {
        butBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                actualTable--;
                changeTable();
                if (isPracticing) {
                    changeTablePractice();
                    if (!pausePractice) {
                        timer.cancel();
                        mPlayerAdapter.reset();
                        initializeSound();
                    }
                }
            }
        });

        butNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                actualTable++;
                changeTable();
                if (isPracticing) {
                    changeTablePractice();
                    if (!pausePractice) {
                        timer.cancel();
                        mPlayerAdapter.reset();
                        initializeSound();
                    }
                }
            }
        });
        butPractice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isPracticing = true;
                changeVisibilityControls();
                changeTablePractice();
                initializeSound();
            }
        });

        butClosePractice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isPracticing = false;
                changeVisibilityControls();
                changeTable();
                timer.cancel();
            }
        });

        butPausePractice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pausePractice = pausePractice ? false : true;
                onPausePractice(pausePractice);
            }
        });

    }

    private void onPausePractice(boolean pausePractice) {
        if (pausePractice) {
            butPausePractice.setBackgroundResource(R.drawable.play);
            mPlayerAdapter.pause();
            mPlayerAdapter.release();
            timer.cancel();
        } else {
            butPausePractice.setBackgroundResource(R.drawable.pause);
            loadMedia();
            initializeTimer();
        }
    }

    private void initializeSound() {
        actualNumber = 1;
        seekBar.setProgress(actualNumber);
        loadMedia();
        initializeTimer();
    }

    private void initializeTimer() {
        final Handler handler = new Handler();
        timer = new Timer(true);
        timerTask = new TimerTask() {
            @Override
            public void run() {
                handler.post(new Runnable() {
                    @Override
                    public void run() {

                        String tableToShow = tvTablePractice.getText().toString();
                        String tableToShowResult = tableToShow.replaceFirst("__", String.valueOf(actualTable * actualNumber));
                        tvTablePractice.setText(tableToShowResult);

                        actualNumber++;
                        if (actualNumber == 11) {
                            timer.cancel();
                        } else {
                            loadMedia();
                        }
                        seekBar.setProgress(actualNumber);
                    }
                });
            }
        };
        timer.scheduleAtFixedRate(timerTask, 4000, 4000);
    }

    private void loadMedia() {
        String nameResource = String.format("%s%d", PRACTICE, actualTable);
        int resId = getResources().getIdentifier(nameResource, "raw", getPackageName());
        if(actualTable == 10)
        {
            resId = getResources().getIdentifier(nameResource + "1", "raw", getPackageName());
        }

        mPlayerAdapter.play(resId);

        mPlayerAdapter.addQueue(R.raw.por);

        nameResource = String.format("%s%d", PRACTICE, actualNumber);
        resId = getResources().getIdentifier(nameResource, "raw", getPackageName());
        mPlayerAdapter.addQueue(resId);
    }

    private void changeTablePractice() {
        String tableSource = getString(R.string.tabla_practice);
        String tableToShow = tableSource.replace("mul", String.valueOf(actualTable));

        tvTablePractice.setText(tableToShow);
    }

    private void initializeComponents() {
        tablesMap = new HashMap<>();
        tablesMap.put(1, R.drawable.tabla1);
        tablesMap.put(2, R.drawable.tabla2);
        tablesMap.put(3, R.drawable.tabla3);
        tablesMap.put(4, R.drawable.tabla4);
        tablesMap.put(5, R.drawable.tabla5);
        tablesMap.put(6, R.drawable.tabla6);
        tablesMap.put(7, R.drawable.tabla7);
        tablesMap.put(8, R.drawable.tabla8);
        tablesMap.put(9, R.drawable.tabla9);
        tablesMap.put(10, R.drawable.tabla10);

        butBack = findViewById(R.id.butBack);
        butNext = findViewById(R.id.butNext);
        butPractice = findViewById(R.id.butPractice);
        butClosePractice = findViewById(R.id.butClosePractice);
        butPausePractice = findViewById(R.id.butPausePractice);

        seekBar = findViewById(R.id.seekBar);

        tvTablePractice = findViewById(R.id.tvTablesPractice);
        tvTitle = findViewById(R.id.tvTitle);

        changeTable();
        initializeSeekbar();
        initializePlaybackController();
    }

    private void changeTable() {
        int idResource = 0;
        String title = "";
        if (tablesMap.containsKey(actualTable)) {
            idResource = tablesMap.get(actualTable);
            title = String.format(getString(R.string.table_title), String.valueOf(actualTable));
        } else {
            idResource = R.drawable.image_not_available;
        }

        if (!isPracticing) {
            String tableToShowResult = initializePracticeTableText(11);
            tvTablePractice.setText(tableToShowResult);
        }
        ConstraintLayout layout = findViewById(R.id.idLayoutTabla);
        layout.setBackgroundResource(idResource);

        tvTitle.setText(title);

        evaluateActualTableToDisableButtond();
    }

    private String initializePracticeTableText(int endIndex) {

        String tableSource = getString(R.string.tabla_practice);
        String tableToShow = tableSource.replace("mul", String.valueOf(actualTable));
        String tableToShowResult = tableToShow;
        for (int i = 1; i < endIndex; i++) {
            tableToShowResult = tableToShow.replaceFirst("__", String.valueOf(actualTable * i));
            tableToShow = tableToShowResult;
        }
        return tableToShowResult;
    }

    private void evaluateActualTableToDisableButtond() {
        if (actualTable <= 1) {
            butBack.setVisibility(View.INVISIBLE);
            butNext.setVisibility(View.VISIBLE);
        } else if (actualTable >= 10) {
            butBack.setVisibility(View.VISIBLE);
            butNext.setVisibility(View.INVISIBLE);
        } else {
            butBack.setVisibility(View.VISIBLE);
            butNext.setVisibility(View.VISIBLE);
        }
    }

    private void initializeSeekbar() {
        //seekBar.setMax(10);
        seekBar.setOnSeekBarChangeListener(
                new SeekBar.OnSeekBarChangeListener() {
                    int userSelectedPosition = 1;

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {
                    }

                    @Override
                    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                        if (progress == 0) {
                            progress = 1;
                            seekBar.setProgress(progress);
                        }

                        userSelectedPosition = progress;
                        if (fromUser) {
                            actualNumber = progress;
                            String tableToShowResult = initializePracticeTableText(progress);
                            tvTablePractice.setText(tableToShowResult);
                            timer.cancel();
                            mPlayerAdapter.reset();
                            if (!pausePractice) {
                                loadMedia();
                                initializeTimer();
                            }
                        }
                    }

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {
                    }
                });
    }

    private void initializePlaybackController() {
        MediaPlayerHolder mMediaPlayerHolder = new MediaPlayerHolder(this);
        Log.d(TAG, "initializePlaybackController: created MediaPlayerHolder");
        mMediaPlayerHolder.setPlaybackInfoListener(new PlaybackListener());
        mPlayerAdapter = mMediaPlayerHolder;
        Log.d(TAG, "initializePlaybackController: MediaPlayerHolder progress callback set");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart: create MediaPlayer");
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (isChangingConfigurations() && mPlayerAdapter.isPlaying()) {
            Log.d(TAG, "onStop: don't release MediaPlayer as screen is rotating & playing");
        } else {
            if(butPausePractice != null && butPausePractice.getVisibility() == View.VISIBLE)
            {
                butPausePractice.setBackgroundResource(R.drawable.play);
                pausePractice = true;
            }
            mPlayerAdapter.release();
            if (timer != null) {
                timer.cancel();
            }
            Log.d(TAG, "onStop: release MediaPlayer");
        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "onRestart");
    }

    public class PlaybackListener extends PlaybackInfoListener {

        @Override
        public void onDurationChanged(int duration) {
            seekBar.setMax(duration);
            Log.d(TAG, String.format("setPlaybackDuration: setMax(%d)", duration));
        }

        @Override
        public void onPositionChanged(int position) {
            if (!mUserIsSeeking) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    seekBar.setProgress(position, true);
                } else {
                    seekBar.setProgress(position);
                }
                Log.d(TAG, String.format("setPlaybackPosition: setProgress(%d)", position));
            }
        }

        @Override
        public void onStateChanged(@State int state) {
            String stateToString = PlaybackInfoListener.convertStateToString(state);
        }

        @Override
        public void onLogUpdated(String message) {
            Log.i(TAG, message);
        }

    }

    private void changeVisibilityControls() {
        if (isPracticing) {
            tvTablePractice.setVisibility(View.VISIBLE);
            butPractice.setVisibility(View.INVISIBLE);
            butClosePractice.setVisibility(View.VISIBLE);
            seekBar.setVisibility(View.VISIBLE);
            butPausePractice.setVisibility(View.VISIBLE);
        } else {
            butPractice.setVisibility(View.VISIBLE);
            butClosePractice.setVisibility(View.INVISIBLE);
            seekBar.setVisibility(View.INVISIBLE);
            butPausePractice.setBackgroundResource(R.drawable.pause);
            butPausePractice.setVisibility(View.INVISIBLE);
            pausePractice = false;
        }
    }
}
