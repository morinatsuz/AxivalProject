package com.axival.Network;

import java.lang.reflect.Array;

public class Packets {

    public static class BufferTellReady{
        boolean ready;
    }

    public static class BufferRequestPlayerData{

    }

    public static class BufferGoSelectChar{
        Integer playerNo;
        String teamWith;
    }

    public static class BufferLobbyPlayer{
        Integer playerCount;
    }

    public static class BufferPlayerData{
        public Integer playerNo;
        public String playerName;
        public String playerTeam;
        public Integer playerClass;

    }

    public static class BufferPhasePlay{
        String authenName;
        Array phasePlay;
    }

    public static class BufferLivePick{
        public Integer playerNo;
        public String liveClassPick;
    }

}
