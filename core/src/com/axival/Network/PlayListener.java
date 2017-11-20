package com.axival.Network;

import com.axival.game.CardPlay;
import com.axival.game.WaitingScreen;
import com.axival.game.fade.FadeScence;
import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Connection;



public class PlayListener extends Listener {
    FadeScence fadeScence;
    private Client client;
    private CardPlay cardPlay;

    private boolean connected;



    public void init(Client client) {
        this.client = client;
    }

    public void connected(Connection c){
        System.out.println("You're connected to server!");

    }

    public void disconnected(Connection c){

        System.out.println("You're disconnected!");

    }

    public void received(Connection c, Object o) {

        if (o instanceof Packets.BufferLobbyPlayer){
            WaitingScreen.lobbyStatus = "Waiting for player (" + ((Packets.BufferLobbyPlayer) o).playerCount + "/4)";
        }

//    if (o instanceof Packets.BufferTellReady){
//        if (((Packets.BufferTellReady) o).ready = true){
//            WaitingScreen.lobbyStatus = "Player Enough!, Game starting...";
//            fadeScence.screenfadeIn(new Image(cardPlay.assetManager.get("tone/black.jpg", Texture.class)),
//                    "select", 0);
//        }
//    }

        if (o instanceof Packets.BufferRequestPlayerData){
            System.out.println("Server request for Player data");
            Packets.BufferPlayerData playerData = new Packets.BufferPlayerData();
            playerData.playerName = "Ton";
            client.sendTCP(playerData);
            System.out.println("Player data sended");
        }



    }

    public boolean getConnected() {
        return connected;
    }
}



