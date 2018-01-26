package com.sunil.user.a3x3tictactoe;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class MultiPlayer extends AppCompatActivity {

    int activePlayer = 0;
    boolean gameIsActive = true;

    int[] gameState = {2, 2, 2, 2, 2, 2, 2, 2, 2};

    int[][] winningPositions = {{0,1,2}, {3,4,5}, {6,7,8}, {0,3,6}, {1,4,7}, {2,5,8}, {2,4,6}, {0,4,8}};

    public void dropIn(View view) {

        ImageView counter = (ImageView) view;
        int tappedCounter = Integer.parseInt(counter.getTag().toString());

        if(gameState[tappedCounter] == 2 && gameIsActive) {

            gameState[tappedCounter] = activePlayer;
            counter.setTranslationY(-1000f);

            if(activePlayer ==0) {
                counter.setImageResource(R.drawable.o);
                activePlayer = 1;
            }
            else {
                counter.setImageResource(R.drawable.x);
                activePlayer = 0;
            }

            counter.animate().translationYBy(1000f).rotation(360).setDuration(300);

            for (int[] winningPosition: winningPositions) {

                if(gameState[winningPosition[0]] == gameState[winningPosition[1]] && gameState[winningPosition[1]] == gameState[winningPosition[2]] & gameState[winningPosition[0]] != 2) {
                    gameIsActive = false;
                    String winner = "X";

                    if(gameState[winningPosition[0]] == 0) {
                        winner = "O";
                    }
                    Toast.makeText(this, winner + " Has won!", Toast.LENGTH_LONG).show();
                }
                else {
                    boolean gameIsOver = true;
                    for(int counterState: gameState) {
                        if (counterState == 2)
                            gameIsOver = false;
                    }
                    if( gameIsOver) {
                        Toast.makeText(this, "Game Over", Toast.LENGTH_LONG).show();
                    }
                }
            }

        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multi_player);
    }
}
