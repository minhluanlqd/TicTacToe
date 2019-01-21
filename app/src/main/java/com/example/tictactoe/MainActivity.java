package com.example.tictactoe;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    // 0: star, 1: panda, 2: empty
    int [] gameStage = {2,2,2,2,2,2,2,2,2};
    int [][] winningPosition = {{0,1,2}, {3,4,5}, {6,7,8}, {0,3,6}, {1,4,7}, {2,5,8}, {0,4,8}, {2,4,6}};
    int activePLayer = 0;
    boolean gameActive = true;

    @SuppressLint("SetTextI18n")
    public void dropIn(View view){
        ImageView counter = (ImageView) view;

        int tappedCounter = Integer.parseInt(counter.getTag().toString());

        if (gameStage[tappedCounter] == 2 && gameActive) {

            gameStage[tappedCounter] = activePLayer;
            counter.setTranslationY(-1500);

            if (activePLayer == 0) {
                counter.setImageResource(R.drawable.icon1);
                activePLayer = 1;
            } else {
                counter.setImageResource(R.drawable.icon2);
                activePLayer = 0;
            }
            counter.animate().translationYBy(1500).setDuration(300);

            for (int[] winningPosition : winningPosition) {
                if (gameStage[winningPosition[0]] == gameStage[winningPosition[1]] && gameStage[winningPosition[1]] == gameStage[winningPosition[2]] && gameStage[winningPosition[0]] != 2) {

                    gameActive = false;
                    String winner = "";
                    if (activePLayer == 1) {
                        winner = "Star";
                    } else {
                        winner = "Panda";
                    }
                    Button playAgainButton = findViewById(R.id.buttonPlayAgain);
                    TextView winnerTextView =  findViewById(R.id.textViewWinner);
                    winnerTextView.setText(winner + " has won!");
                    playAgainButton.setVisibility(View.VISIBLE);
                    winnerTextView.setVisibility(View.VISIBLE);
                }
            }
        }
    }
    public void playAgain(View view){
        Button playAgainButton = findViewById(R.id.buttonPlayAgain);
        TextView winnerTextView =  findViewById(R.id.textViewWinner);
        playAgainButton.setVisibility(View.INVISIBLE);
        winnerTextView.setVisibility(View.INVISIBLE);

        GridLayout gridLayout = findViewById(R.id.gridLayout);

        for (int i =0; i<gridLayout.getChildCount(); i++){

            ImageView counter = (ImageView) gridLayout.getChildAt(i);

            counter.setImageDrawable(null);
        }
        for (int i = 0; i<gameStage.length; i++){
            gameStage[i] = 2;
        }
        activePLayer = 0;
        gameActive = true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
