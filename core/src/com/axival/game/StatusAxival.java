package com.axival.game;

import com.axival.game.MapPlay.MapScreen;
import com.axival.game.screen.ScreenPlay;

public class StatusAxival {
    private int[] allStatus;
    public static int[] statusPhase; //server
    public static int[][] statusPlayer; //local
    public StatusAxival(){
        statusPlayer = new int[4][5]; //[ho, ap, range, atk, def]
        statusPhase = new int[13];

        statusPhase[0] = 0; //Amount turn
        statusPhase[1] = 4; //character class
        statusPhase[2] = 1; //character class
        statusPhase[3] = 2; //character class
        statusPhase[4] = 3; //character class
        statusPhase[5] = 0; //who's in turn
        statusPhase[6] = 0; //turn 0=draw, 1,3=action, 2=travel, 4=end
        statusPhase[7] = 0; //action start player default=0
        statusPhase[8] = -1; //action attacker default=0
        statusPhase[9] = 0; //action target default=0
        statusPhase[10] = 0; //Travel phase who default= -1
        statusPhase[11] = 0; //Travel phase to col default= -1
        statusPhase[12] = 0; //Travel phase to row default= -1

        /*
        statusPhase[0] = 0; //Amount turn
        statusPhase[5] = 0; //who's in turn
        statusPhase[6] = 0; //turn 0=draw, 1,3=action, 2=travel, 4=end
        statusPhase[7] = 0; //action start player default=0
        statusPhase[8] = -1; //action attacker default=-1
        statusPhase[9] = 0; //action target default=0
        statusPhase[10] = 0; //Travel phase who default= -1
        statusPhase[11] = 0; //Travel phase to col default= -1
        statusPhase[12] = 0; //Travel phase to row default= -1
        */

        //DT status
        statusPlayer[0][0] = 35;
        statusPlayer[0][1] = 3;
        statusPlayer[0][2] = 1;
        statusPlayer[0][3] = 4;
        statusPlayer[0][4] = 3;

        //Wizard status
        statusPlayer[1][0] = 30;
        statusPlayer[1][1] = 3;
        statusPlayer[1][2] = 3;
        statusPlayer[1][3] = 6;
        statusPlayer[1][4] = 1;

        //Priest status
        statusPlayer[2][0] = 25;
        statusPlayer[2][1] = 3;
        statusPlayer[2][2] = 2;
        statusPlayer[2][3] = 3;
        statusPlayer[2][4] = 2;
    }

    public void setApInPhase(int heroIndex){
        if (statusPhase[0]==0){
            statusPlayer[heroIndex][1] = 3;
        }
        else if (statusPhase[0]<10){
            statusPlayer[heroIndex][1] += 1;
        }
        else {
            statusPlayer[heroIndex][1] = 10;
        }
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
