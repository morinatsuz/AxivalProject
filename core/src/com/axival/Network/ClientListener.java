package com.axival.Network;

import com.axival.game.CardPlay;
import com.axival.game.SelectHeroScreen;
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

    public ClientListener(CardPlay cardPlay){
        this.cardPlay = cardPlay;
    }

    public void init(Client client) {
        this.client = client;
    }

    public void connected(Connection c){
        System.out.println("You're connected to server!");
        connected = true;
        c.setKeepAliveTCP(1000);
        c.setTimeout(1000);
    }

    public void disconnected(Connection c){

        System.out.println("You're disconnected!");

    }

    public void received(Connection c, Object o){

        if (o instanceof Packets.Packet01NetworkStatus){
            System.out.println("Network status recieved");
            boolean accepted = ((Packets.Packet01NetworkStatus) o).accepted;

            }
        }

    public boolean getConnected() {
        return connected;
    }
}



