package com.axival.Network;

import com.axival.game.CardPlay;
import com.axival.game.SelectHeroScreen;
import com.axival.game.StatusAxival;
import com.axival.game.WaitingScreen;
import com.axival.game.fade.FadeScence;
import com.axival.game.input.MyNameInputListener;
import com.axival.game.screen.ScreenPlay;
import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Connection;

import java.util.Arrays;


public class ClientListener extends Listener {
    FadeScence fadeScence;
    private Client client;
    private CardPlay cardPlay;
    public static Boolean locatePlayer = true;

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
        System.out.println(Arrays.toString(StatusAxival.statusPhase));
        if (locatePlayer){
            StatusAxival.statusPhase[1] = ((Packets.BufferUpdatePhase) o).p1;
            StatusAxival.statusPhase[2] = ((Packets.BufferUpdatePhase) o).p2;
            StatusAxival.statusPhase[3] = ((Packets.BufferUpdatePhase) o).p3;
            StatusAxival.statusPhase[4] = ((Packets.BufferUpdatePhase) o).p4;
            StatusAxival.statusPhase[SelectHeroScreen.playerNo] += 3;
            locatePlayer = false;
            System.out.println("");
            System.out.println("[Player "+ SelectHeroScreen.playerNo + "] //////////Default array set!");
            System.out.println("[Player "+ SelectHeroScreen.playerNo + "] " + Arrays.toString(StatusAxival.statusPhase));
            System.out.println("");

        } else {
            StatusAxival.statusPhase[0] = ((Packets.BufferUpdatePhase) o).p0;
            StatusAxival.statusPhase[5] = ((Packets.BufferUpdatePhase) o).p5;
            StatusAxival.statusPhase[6] = ((Packets.BufferUpdatePhase) o).p6;
            StatusAxival.statusPhase[7] = ((Packets.BufferUpdatePhase) o).p7;
            StatusAxival.statusPhase[8] = ((Packets.BufferUpdatePhase) o).p8;
            StatusAxival.statusPhase[9] = ((Packets.BufferUpdatePhase) o).p9;
            StatusAxival.statusPhase[10] = ((Packets.BufferUpdatePhase) o).p10;
            StatusAxival.statusPhase[11] = ((Packets.BufferUpdatePhase) o).p11;
            StatusAxival.statusPhase[12] = ((Packets.BufferUpdatePhase) o).p12;
            System.out.println("");
            System.out.println("[Player " + SelectHeroScreen.playerNo + "] ///////////Array got updated from Player " +((Packets.BufferUpdatePhase) o).playerNo);
            System.out.println(Arrays.toString(StatusAxival.statusPhase));
            System.out.println("");
        }
    }

    }

    public boolean getConnected() {
        return connected;
    }
}



