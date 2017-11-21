package com.axival.Network;

import com.axival.game.CardPlay;
import com.axival.game.SelectHeroScreen;
import com.axival.game.StatusAxival;
import com.axival.game.WaitingScreen;
import com.axival.game.fade.FadeScence;
import com.axival.game.input.MyNameInputListener;
import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Connection;



public class ClientListener extends Listener {
    FadeScence fadeScence;
    private Client client;
    private CardPlay cardPlay;

    public boolean connected;

    public ClientListener(){

    }

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
        WaitingScreen.statusConnected = true;
        WaitingScreen.lobbyStatus = "Waiting for player (" + ((Packets.BufferLobbyPlayer) o).playerCount + "/4)";
    }

    if (o instanceof Packets.BufferGoSelectChar){
        WaitingScreen.statusAlready = true;
        SelectHeroScreen.friendName = ((Packets.BufferGoSelectChar) o).teamWith;
        SelectHeroScreen.playerNo = ((Packets.BufferGoSelectChar) o).playerNo;
        System.out.println("You're team with" + SelectHeroScreen.friendName + "You is player" + SelectHeroScreen.playerNo);
    }

    if (o instanceof Packets.BufferLivePick){
        SelectHeroScreen.teamPick = ((Packets.BufferLivePick) o).liveClassPick;
    }

    if (o instanceof Packets.BufferRequestPlayerData){
        System.out.println("Server request for Player data");
        Packets.BufferPlayerData playerData = new Packets.BufferPlayerData();
        playerData.playerName = MyNameInputListener.nameId;
        client.sendTCP(playerData);
        System.out.println("Player data sended");
        System.out.println("Connected is true");
    }

    if (o instanceof Packets.BufferUpdatePhase){
        StatusAxival.statusPhase = ((Packets.BufferUpdatePhase) o).updatePhase;
        System.out.println("Status update from Player " + ((Packets.BufferUpdatePhase) o).playerNo);
        System.out.println(StatusAxival.statusPhase);
    }

    }

    public boolean getConnected() {
        return connected;
    }
}



