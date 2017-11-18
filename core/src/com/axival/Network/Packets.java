package com.axival.Network;

import java.lang.reflect.Array;

public class Packets {

    public static class BufferPlayerClass{
        //Sending on connection occurred
        public Integer heroSelect;
    }

    public static class BufferTellReady{
        boolean ready;
    }

    public static class BufferLobbyPlayer{
        Integer playerCount;
    }

}
