package com.snake.game.tools;

import java.io.Serializable;

public class GameData implements Serializable {


private static final long serialVersionUID = 1;
private final int MAX_NUMBER_SCORES = 10;
private int[] highscores;
private String[] names;
private int score;

public GameData(){
    highscores = new int[MAX_NUMBER_SCORES];
    names = new String[MAX_NUMBER_SCORES];
}

public void init(){

    for(int i = 0; i < MAX_NUMBER_SCORES; i++){
        highscores[i] = 0;
        names[i] = "-----";
    }
}

public int[] getHighscores(){
    return highscores;
}

public String[] getNames() {
        return names;
}

public int getScore(){
    return score;
}

public void setScore(int newScore) {
    score = newScore;
}

public boolean isHighscore(int score){
    return score > highscores[MAX_NUMBER_SCORES - 1];
}

public void addHighscore(int newScore, String playerName){

if(isHighscore(newScore)){
    highscores[MAX_NUMBER_SCORES-1]  = newScore;
    names[MAX_NUMBER_SCORES-1] = playerName;
    sortHighscores();
}

}

public void sortHighscores() {
    for(int i = 0; i < MAX_NUMBER_SCORES; i++){
        int score = highscores[i];
        String playerName = names[i];
        int j;
        for(j = i - 1; j >= 0 && highscores[j] < score; j++){
            highscores[j+1] = highscores[j];
            names[j+1] = names[j];
        }

        highscores[j+1] = score;
        names[j+1] = playerName;
    }

}


}
