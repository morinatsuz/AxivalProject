package com.axival.game;

import com.axival.game.MapPlay.MapScreen;
import com.axival.game.screen.ScreenPlay;

public class CalculatorManager {
    private ScreenPlay screenPlay;
    private MapScreen mapScreen;
    private  int apScore=3;
    public CalculatorManager(ScreenPlay screenPlay, MapScreen mapScreen){
        this.screenPlay = screenPlay;
        this.mapScreen = mapScreen;
    }

    public void setAp(){
        if (screenPlay.statusPhase[0]==0){
            apScore = 3;
        }
        else if (screenPlay.statusPhase[0]<10){
            apScore++;
        }
        else {
            apScore = 10;
        }
    }

    public int getApScore(){
        return apScore;
    }
}
