package com.sunil.user.a3x3tictactoe;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import static java.lang.StrictMath.max;
import static java.lang.StrictMath.min;

public class SinglePlayer extends AppCompatActivity {


    int activePlayer = 0;
    boolean gameIsActive = true;

    int[] gameState = {2, 2, 2, 2, 2, 2, 2, 2, 2};

    boolean isMovesLeft(int gameState[]) {
        for(int i=0; i<9; i++) {
                if(gameState[i]==2)
                    return true;
        }
        return false;
    }

    int evaluate(int gameState[]) {
        for (int i = 0; i<9; i++) {
            if(gameState[0] == gameState[1] && gameState[1] == gameState[2] && gameState[0] != 2) {
                if (gameState[0] == 0)
                    return +10;
                else if (gameState[0] == 1)
                    return -10;
            }
        }

        for (int j = 0; j<9; j++) {
            if(gameState[0] == gameState[3] && gameState[3] == gameState[6] && gameState[0] != 2) {
                if (gameState[0] == 0)
                    return +10;
                else if (gameState[0] == 1)
                    return -10;
            }
        }

        if(gameState[0] == gameState[4] && gameState[8] == gameState[4] && gameState[0] != 2) {
            if (gameState[0] == 0)
                return +10;
            else if(gameState[0] == 1)
                return -10;
        }

        if(gameState[2] == gameState[4] && gameState[4] == gameState[6] && gameState[2] != 2) {
            if (gameState[2] == 0)
                return +10;
            else if(gameState[2] == 1)
                return -10;
        }

        return 0;
    }

    int minimax(int gameState[], int depth, boolean isMax) {

        int score = evaluate(gameState);

        if(score == 10)
            return score;

        if(score == -10)
            return score;

        if(isMovesLeft(gameState) == false)
            return 0;

        if (isMax) {
            int best = -1000;

            for (int i=0; i<9; i++) {
                if(gameState[i] == 2) {
                    gameState[i] = activePlayer;

                    best = max(best, minimax(gameState, depth + 1, !isMax));

                    gameState[i] = 2;
                }
            }

            return best;
        }

        else {
            int best = 1000;

            for (int i=0; i<9; i++) {
                    if(gameState[i] == 2) {
                        gameState[i] = activePlayer;

                        best = min(best, minimax(gameState, depth + 1, !isMax));

                        gameState[i] = 2;
                    }
            }
            return best;
        }
    }

    int findBestMove(int gameState[]) {
        int bestValue = -1000;
        int row = -1;

        for(int i=0; i<9; i++) {
                if(gameState[i] == 2)
                {
                    gameState[i] = activePlayer;
                    int movVal = minimax(gameState, 1, false);

                    gameState[i] = 2;

                    if (movVal > bestValue) {
                        row = i;
                        bestValue = movVal;
                    }
                }
        }
        return row;
    }


    public void dropIn(View view) {

        if(gameIsActive == true) {

            ImageView counter = (ImageView) view;
            int tappedCounter = Integer.parseInt(counter.getTag().toString());

            if (gameState[tappedCounter] == 2 && gameIsActive) {

                gameState[tappedCounter] = activePlayer;
                //counter.setTranslationY(-1000f);

                if (activePlayer == 0) {
                    counter.setImageResource(R.drawable.o);
                    //counter.animate().translationYBy(1000f);
                    activePlayer = 1;
                }
            }
        }

        isGameWon();

        if(gameIsActive == true) {
            if (activePlayer == 1) {
                int bestMove = findBestMove(gameState);
                if(bestMove >= 0) {
                    gameState[bestMove] = activePlayer;
                    ImageView comp = findViewById(R.id.gridLayout).findViewWithTag(Integer.toString(bestMove));
                    comp.setVisibility(View.INVISIBLE);
                    comp.setImageResource(R.drawable.x);
                    comp.setVisibility(View.VISIBLE);
                    activePlayer = 0;
                }
            }
        }

        isGameWon();

    }


    private void isGameWon() {

        if(gameState[0] == gameState[1] && gameState[1] == gameState[2] && gameState[0] != 2) {
                    if (gameState[0] == 0) {
                        Toast.makeText(this, "Player O has won", Toast.LENGTH_LONG).show();
                        gameIsActive = false;
                    }
                    else if (gameState[0] == 1) {
                        Toast.makeText(this, "Player X has won", Toast.LENGTH_LONG).show();
                        gameIsActive = false;
                    }
        }

        if(gameState[0] == gameState[3] && gameState[3] == gameState[6] && gameState[0] != 2) {
                    if (gameState[0] == 0)
                    {
                        Toast.makeText(this, "Player O has won", Toast.LENGTH_LONG).show();
                        gameIsActive = false;
                    }
                    else if (gameState[0] == 1)
                    {
                        Toast.makeText(this, "Player X has won", Toast.LENGTH_LONG).show();
                        gameIsActive = false;
                    }
        }

        if(gameState[0] == gameState[4] && gameState[8] == gameState[4] && gameState[0] != 2) {
                if (gameState[0] == 0)
                {
                    Toast.makeText(this, "Player O has won", Toast.LENGTH_LONG).show();
                    gameIsActive = false;
                }
                else if(gameState[0] == 1)
                {
                    Toast.makeText(this, "Player X has won", Toast.LENGTH_LONG).show();
                    gameIsActive = false;
                }
        }

        if(gameState[2] == gameState[4] && gameState[4] == gameState[6] && gameState[2] != 2) {
                if (gameState[2] == 0)
                {
                    Toast.makeText(this, "Player O has won", Toast.LENGTH_LONG).show();
                    gameIsActive = false;
                }
                else if(gameState[2] == 1)
                {
                    Toast.makeText(this, "Player X has won", Toast.LENGTH_LONG).show();
                    gameIsActive = false;
                }
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_player);
    }

}
