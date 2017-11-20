package com.axival.Network;

import java.lang.reflect.Array;

public class Packets {

    public static class BufferTellReady{
        boolean ready;
    }

    public static class BufferRequestPlayerData{

    }

    public static class BufferLobbyPlayer{
        Integer playerCount;
    }

    public static class BufferPlayerData{
        Integer playerNo;
        String playerName;
        String playerTeam;
        Integer playerClass;
    }

    public static class BufferGoSelectChar{
        String teamWith;
    }

    public static class BufferStartGame{
        Array startArray;
    }

    public static class BufferPhasePlay{
        String authenName;
        Array phasePlay;
    }

}
