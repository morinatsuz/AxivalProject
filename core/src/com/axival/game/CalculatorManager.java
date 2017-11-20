package com.axival.game;

import com.axival.game.MapPlay.MapScreen;
import com.axival.game.screen.ScreenPlay;

public class CalculatorManager {
    private ScreenPlay screenPlay;
    private MapScreen mapScreen;
    private  int apScore=3, hp=0, range=0, attk=0, def=0;
    private int[] allStatus;
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

    public void setHp(int hp){
        this.hp = hp;
    }

    public int getHp(){
        return hp;
    }

    public int getRange() {
        return range;
    }

    public void setRange(int range) {
        this.range = range;
    }

    public int getAttk() {
        return attk;
    }

    public void setAttk(int attk) {
        this.attk = attk;
    }

    public int getDef() {
        return def;
    }

    public void setDef(int def) {
        this.def = def;
    }

    public int[] getAllStatus() {
        return allStatus;
    }

    public void setAllStatus(int[] allStatus) {
        this.allStatus = allStatus;
    }

    public void calculateStatus(int select, int index, int value){
        //0=+, 1=-, 2=*, 3=/, 4=change value
        if(select==0){
            allStatus[index] += value;
        }
        else if(select==1){
            allStatus[index] -= value;
        }
        else if(select==2){
            allStatus[index] *= value;
        }
        else if(select==3){
            allStatus[index] /= value;
        }
        else if(select==4){
            allStatus[index] = value;
        }
    }

}
