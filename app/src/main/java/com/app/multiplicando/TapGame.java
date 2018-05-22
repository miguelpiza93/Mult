package com.app.multiplicando;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TapGame extends AppCompatActivity {

    private static final int PLAYER_1 = 1;
    private static final int PLAYER_2 = 2;

    private TextView tvPlayer11;
    private TextView tvPlayer12;
    private TextView tvPlayer21;
    private TextView tvPlayer22;
    private TextView tvQuestion;
    private TextView tvQuestion2;
    private TextView tvScorePlayer1;
    private TextView tvScorePlayer2;
    private LottieAnimationView animationPlayer1;
    private LottieAnimationView animationPlayer2;
    private LottieAnimationView butStartGame;
    private Random random;
    private int numA;
    private int numB;
    private int correctAnswer;
    private TextView tvInstruction;
    private TextView tvInstruction2;
    private Button butPauseGame;
    private boolean validatingResponse;
    private MediaPlayer sound;
    private ImageView tvRemainingLives31;
    private ImageView tvRemainingLives21;
    private ImageView tvRemainingLives11;
    private ImageView tvRemainingLives32;
    private ImageView tvRemainingLives22;
    private ImageView tvRemainingLives12;
    private List<ImageView> remainingLivesPlayer1;
    private List<ImageView> remainingLivesPlayer2;
    private LottieAnimationView animationFinish;
    private LottieAnimationView animationFinish2;
    private MediaPlayer soundFail;
    private boolean allTables;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_tap_game);

        //Intent intent = new Intent( TapGame.this, NativeAdActivity.class);
        //startActivity(intent);

        numA = getIntent().getExtras().getInt("Tabla");
        allTables = (numA == -1);

        initializeControls();

        butStartGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                butStartGame.pauseAnimation();
                showWidgets(true);
                showQuestion();
                addListenerToAnswer();

            }
        });

        butPauseGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showWidgets(false);
            }
        });
    }

    private void addListenerToAnswer() {
        tvPlayer11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int answer = Integer.parseInt(tvPlayer11.getText().toString());
                validateAnswer(answer, PLAYER_1);
            }
        });
        tvPlayer12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int answer = Integer.parseInt(tvPlayer12.getText().toString());
                validateAnswer(answer, PLAYER_2);
            }
        });
        tvPlayer21.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int answer = Integer.parseInt(tvPlayer21.getText().toString());
                validateAnswer(answer, PLAYER_1);
            }
        });
        tvPlayer22.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int answer = Integer.parseInt(tvPlayer22.getText().toString());
                validateAnswer(answer, PLAYER_2);
            }
        });
    }

    private void validateAnswer(int answer, int player) {
        if (!validatingResponse) {
            validatingResponse = true;
            if (correctAnswer == answer) {
                if (player == PLAYER_1) {
                    animationPlayer1.playAnimation();
                    int score = Integer.parseInt(tvScorePlayer1.getText().toString());
                    score++;
                    tvScorePlayer1.setText("" + score);

                } else {
                    animationPlayer2.playAnimation();
                    int score = Integer.parseInt(tvScorePlayer2.getText().toString());
                    score++;
                    tvScorePlayer2.setText("" + score);
                }
                if (sound != null) {
                    sound.start();
                }

                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        showQuestion();
                        validatingResponse = false;
                    }
                }, 1000);
            } else {
                if (player == PLAYER_1) {
                    int score = Integer.parseInt(tvScorePlayer1.getText().toString());
                    if (score > 0) {
                        score--;
                    }
                    tvScorePlayer1.setText("" + score);

                    ImageView image = remainingLivesPlayer1.get(remainingLivesPlayer1.size() - 1);
                    image.setImageResource(R.drawable.peel);
                    remainingLivesPlayer1.remove(remainingLivesPlayer1.size() - 1);

                    if (remainingLivesPlayer1.isEmpty()) {
                        endGame(PLAYER_1);
                    }

                } else {
                    int score = Integer.parseInt(tvScorePlayer2.getText().toString());
                    if (score > 0) {
                        score--;
                    }
                    tvScorePlayer2.setText("" + score);

                    ImageView image = remainingLivesPlayer2.get(remainingLivesPlayer2.size() - 1);
                    image.setImageResource(R.drawable.peel);
                    remainingLivesPlayer2.remove(remainingLivesPlayer2.size() - 1);

                    if (remainingLivesPlayer2.isEmpty()) {
                        endGame(PLAYER_2);
                    }
                }
                if (soundFail != null) {
                    soundFail.start();
                }
                validatingResponse = false;
            }
        }
    }

    private void endGame(int player1) {
        switch (player1) {
            case PLAYER_1:
                tvInstruction.setText(R.string.play_again);
                tvInstruction2.setText(R.string.win);
                animationFinish2 = findViewById(R.id.animationFinal2);
                animationFinish2.setVisibility(View.VISIBLE);
                animationFinish2.playAnimation();
                break;
            case PLAYER_2:
                tvInstruction2.setText(R.string.play_again);
                tvInstruction.setText(R.string.win);
                animationFinish = findViewById(R.id.animationFinal);
                animationFinish.setVisibility(View.VISIBLE);
                animationFinish.playAnimation();
                break;
            default:
                break;
        }
        showWidgets(false);
        initializeRemainingLives();
    }

    private void showQuestion() {
        //Question
        if (allTables)
            numA = random.nextInt(10) + 1;
        numB = random.nextInt(10) + 1;
        correctAnswer = numA * numB;
        tvQuestion.setText(numA + " x " + numB);
        tvQuestion2.setText(numA + " x " + numB);

        //position to put the answer on button
        int positionPlayer1 = random.nextInt(2) + 1;
        int positionPlayer2 = random.nextInt(2) + 1;

        //num decides +1 or -1 en numC
        int numcRandom = random.nextInt(2) + 1;

        int numC = 0;
        switch (numcRandom) {
            case 1:
                //restart
                if (numB > 1)
                    numC = numB - 1;
                else
                    numC = numB + 1;
                break;
            default:
                numC = numB + 1;
                break;
        }

        //Decides in which botton letf or right show the correct answer for player 1
        switch (positionPlayer1) {
            case 1:
                tvPlayer11.setText("" + numA * numB);
                tvPlayer21.setText("" + numA * numC);
                break;
            default:
                tvPlayer11.setText("" + numA * numC);
                tvPlayer21.setText("" + numA * numB);
                break;
        }

        //Decides in which botton letf or right show the correct answer for player 2
        switch (positionPlayer2) {
            case 1:
                tvPlayer12.setText("" + numA * numB);
                tvPlayer22.setText("" + numA * numC);
                break;
            default:
                tvPlayer12.setText("" + numA * numC);
                tvPlayer22.setText("" + numA * numB);
                break;
        }
    }

    private void initializeControls() {
        tvPlayer11 = findViewById(R.id.tvAnswer1Player1);
        tvPlayer12 = findViewById(R.id.tvAnswer1Player2);
        tvPlayer21 = findViewById(R.id.tvAnswer2Player1);
        tvPlayer22 = findViewById(R.id.tvAnswer2Player2);

        tvInstruction = findViewById(R.id.tvInstruction);
        tvInstruction2 = findViewById(R.id.tvInstruction2);

        tvQuestion = findViewById(R.id.tvQuestion);
        tvQuestion2 = findViewById(R.id.tvQuestion2);

        tvScorePlayer1 = findViewById(R.id.tvScorePlayer1);
        tvScorePlayer2 = findViewById(R.id.tvScorePlayer2);

        animationPlayer1 = findViewById(R.id.animationPlayer1);
        animationPlayer2 = findViewById(R.id.animationPlayer2);

        animationPlayer1.setAnimation(R.raw.star2);
        animationPlayer2.setAnimation(R.raw.star2);

        butStartGame = findViewById(R.id.butStart);
        butPauseGame = findViewById(R.id.butPause);

        random = new Random();
        sound = MediaPlayer.create(this, R.raw.star_sound);
        soundFail = MediaPlayer.create(this, R.raw.ate_banana);

        tvRemainingLives31 = findViewById(R.id.tvBanana3Player1);
        tvRemainingLives21 = findViewById(R.id.tvBanana2Player1);
        tvRemainingLives11 = findViewById(R.id.tvBanana1Player1);

        tvRemainingLives32 = findViewById(R.id.tvBanana3Player2);
        tvRemainingLives22 = findViewById(R.id.tvBanana2Player2);
        tvRemainingLives12 = findViewById(R.id.tvBanana1Player2);

        initializeRemainingLives();
    }

    private void initializeRemainingLives() {

        tvRemainingLives31.setImageResource(R.drawable.banana);
        tvRemainingLives21.setImageResource(R.drawable.banana);
        tvRemainingLives11.setImageResource(R.drawable.banana);

        tvRemainingLives32.setImageResource(R.drawable.banana);
        tvRemainingLives22.setImageResource(R.drawable.banana);
        tvRemainingLives12.setImageResource(R.drawable.banana);

        remainingLivesPlayer1 = new ArrayList<>();
        remainingLivesPlayer1.add(tvRemainingLives11);
        remainingLivesPlayer1.add(tvRemainingLives21);
        remainingLivesPlayer1.add(tvRemainingLives31);

        remainingLivesPlayer2 = new ArrayList<>();
        remainingLivesPlayer2.add(tvRemainingLives12);
        remainingLivesPlayer2.add(tvRemainingLives22);
        remainingLivesPlayer2.add(tvRemainingLives32);

        tvScorePlayer1.setText(""+0);
        tvScorePlayer2.setText(""+0);
    }

    private void showWidgets(boolean visible) {
        if (visible) {
            tvPlayer11.setVisibility(View.VISIBLE);
            tvPlayer12.setVisibility(View.VISIBLE);
            tvPlayer21.setVisibility(View.VISIBLE);
            tvPlayer22.setVisibility(View.VISIBLE);

            tvQuestion.setVisibility(View.VISIBLE);
            tvQuestion2.setVisibility(View.VISIBLE);

            tvScorePlayer1.setVisibility(View.VISIBLE);
            tvScorePlayer2.setVisibility(View.VISIBLE);

            animationPlayer1.setVisibility(View.VISIBLE);
            animationPlayer2.setVisibility(View.VISIBLE);

            //Hide instructions
            tvInstruction.setVisibility(View.GONE);
            tvInstruction2.setVisibility(View.GONE);

            butPauseGame.setVisibility(View.VISIBLE);
            butStartGame.setVisibility(View.INVISIBLE);

            //show remaining lives
            tvRemainingLives31.setVisibility(View.VISIBLE);
            tvRemainingLives21.setVisibility(View.VISIBLE);
            tvRemainingLives11.setVisibility(View.VISIBLE);

            tvRemainingLives32.setVisibility(View.VISIBLE);
            tvRemainingLives22.setVisibility(View.VISIBLE);
            tvRemainingLives12.setVisibility(View.VISIBLE);


            if (animationFinish != null) {
                animationFinish.setVisibility(View.INVISIBLE);
            }

            if (animationFinish2 != null) {
                animationFinish2.setVisibility(View.INVISIBLE);
            }
            tvInstruction.setText(R.string.instruction_multiplayer);
            tvInstruction2.setText(R.string.instruction_multiplayer);


        } else {
            tvPlayer11.setVisibility(View.INVISIBLE);
            tvPlayer12.setVisibility(View.INVISIBLE);
            tvPlayer21.setVisibility(View.INVISIBLE);
            tvPlayer22.setVisibility(View.INVISIBLE);

            tvQuestion.setVisibility(View.INVISIBLE);
            tvQuestion2.setVisibility(View.INVISIBLE);

            tvScorePlayer1.setVisibility(View.INVISIBLE);
            tvScorePlayer2.setVisibility(View.INVISIBLE);

            animationPlayer1.setVisibility(View.INVISIBLE);
            animationPlayer2.setVisibility(View.INVISIBLE);

            //Show instructions
            tvInstruction.setVisibility(View.VISIBLE);
            tvInstruction2.setVisibility(View.VISIBLE);

            butPauseGame.setVisibility(View.INVISIBLE);
            butStartGame.setVisibility(View.VISIBLE);
            butStartGame.playAnimation();

            //Hide remaining lives
            tvRemainingLives31.setVisibility(View.INVISIBLE);
            tvRemainingLives21.setVisibility(View.INVISIBLE);
            tvRemainingLives11.setVisibility(View.INVISIBLE);

            tvRemainingLives32.setVisibility(View.INVISIBLE);
            tvRemainingLives22.setVisibility(View.INVISIBLE);
            tvRemainingLives12.setVisibility(View.INVISIBLE);

        }
    }
}
