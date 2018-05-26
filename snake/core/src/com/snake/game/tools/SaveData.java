package com.snake.game.tools;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;

public class SaveData {

    public static GameData gameData;

    public static void saveData(){
        try {
            ObjectOutputStream outfile = new ObjectOutputStream(new FileOutputStream("highscores.sav"));
            outfile.writeObject(gameData);
            outfile.close();
        }catch (Exception e){
            e.printStackTrace();
            Gdx.app.exit();
        }

    }

    public static void loadData(){
        try {
            if(!existsSaveFile()){
                init();
            }
            ObjectInputStream infile = new ObjectInputStream(new FileInputStream("highscores.sav"));
            gameData = (GameData) infile.readObject();
            infile.close();
        }catch (Exception e){
            e.printStackTrace();
            Gdx.app.exit();
        }




    }

    public static boolean existsSaveFile(){
        File file = new File("highscores.sav");
        return file.exists();
    }

    public static void init(){
        gameData = new GameData();
        gameData.init();
        saveData();
    }



}
