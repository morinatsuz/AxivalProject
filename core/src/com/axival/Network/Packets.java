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

    public static class BufferUpdatePhase{
        public Integer playerNo;
        public Integer p0;
        public Integer p1;
        public Integer p2;
        public Integer p3;
        public Integer p4;
        public Integer p5;
        public Integer p6;
        public Integer p7;
        public Integer p8;
        public Integer p9;
        public Integer p10;
        public Integer p11;
        public Integer p12;

    }

    public static class BufferLivePick{
        public Integer playerNo;
        public Integer liveClassNo;
        public String liveClassPick;
    }

}
