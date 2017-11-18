package com.axival.Network;

import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Connection;
import java.io.IOException;


public class ClientListener extends Listener {
    private Client client;

    public void init(Client client) {
        this.client = client;
    }

    public void connected(Connection c){
        System.out.println("You're connected to server!");

        Packets.Packet02PlayerInfo playerData = new Packets.Packet02PlayerInfo();
        playerData.playerName = "Ton";
        playerData.playerMessage = "Hello Server!";
        c.sendTCP(playerData);
        System.out.println("Data Sent");
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
    }

