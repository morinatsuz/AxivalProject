package com.axival.Network;

import com.axival.game.CardPlay;
import com.axival.game.SelectHeroScreen;
import com.axival.game.WaitingScreen;
import com.axival.game.fade.FadeScence;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Connection;
import java.io.IOException;


public class ClientListener extends Listener {
    private Client client;
    private CardPlay cardPlay;

    private boolean connected;

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
        WaitingScreen.lobbyStatus = "Waiting for player (" + ((Packets.BufferLobbyPlayer) o).playerCount + "/4)";
    }

    }

    public boolean getConnected() {
        return connected;
    }
}



