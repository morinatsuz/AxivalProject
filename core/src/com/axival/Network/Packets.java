package com.axival.Network;

import java.lang.reflect.Array;

public class Packets {

    public static class Packet01NetworkStatus{
        //Sending on connection occurred
        Boolean accepted;
    }

    public static class Packet02PlayerInfo{
        //Sending on connection accept, Send Once!
        String playerName;
        String playerMessage;
    }

    public static class Packet03StageStatus{
        //Let the game know which stage to go
        String stageStatus;
    }

    public static class Packet04GameData{
        //Action in round
        Array gameData;
    }

    public static class Packet05TeamData{
        //Team Info - Server act as switch Save data and broadcast it to other team
        Integer playerNo;
        String teamSign; //A or B
    }
}

