package com.axival.game;

import com.axival.Network.ClientListener;
import com.axival.Network.Packets;
import com.axival.game.fade.FadeScence;
import com.axival.game.input.MyTextInputListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.Client;
import com.sun.org.apache.xpath.internal.SourceTree;

import java.io.IOException;

public class WaitingScreen implements Screen {
    public static String lobbyStatus = "Connecting to " + MyTextInputListener.networkId;
    public Client client;
    private ClientListener cnl;
    private BitmapFont font;
    private BitmapFont font2;
    private CardPlay cardPlay;
    private Animation<TextureRegion> animationWaiting;
    private float timePlay;

    public static boolean statusAlready;
    public static boolean statusConnected;

    private TextureAtlas textureAtlas;

    private FadeScence fadeScence;

    public WaitingScreen(CardPlay cardPlay){

        font = new BitmapFont();
        font.setColor(Color.WHITE);
        font2 = new BitmapFont();
        font2.setColor(Color.WHITE);
        this.cardPlay = cardPlay;
        this.fadeScence = new FadeScence(cardPlay);
        //animationWaiting = GifDecoder.loadGIFAnimation(Animation.PlayMode.LOOP, Gdx.files.internal("waiting/loading2.gif").read());
        textureAtlas = new TextureAtlas("waiting/wait.atlas");
        animationWaiting = new Animation<TextureRegion>(1f/20f, textureAtlas.getRegions());
        timePlay = 0;
        cardPlay.soundManager.stopBgm(0);
        cardPlay.soundManager.playBgm(4);
    }

    @Override
    public void show() {
        fadeScence.screenfadeOut(new Image(cardPlay.assetManager.get("tone/white.jpg", Texture.class)), 1);
    }

    @Override
    public void render(float delta) {
        timePlay += delta;
        Gdx.gl.glClearColor(1, 0, 0, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        update(delta);
        cardPlay.batch.begin();
        //draw font
        cardPlay.batch.draw(animationWaiting.getKeyFrame(timePlay, true), 0, 0, 1280, 720);
        font.draw(cardPlay.batch, "/   " + lobbyStatus + ", Please wait...", 80, 80);
        font.draw(cardPlay.batch, "//   Connecting to Axival server on " + MyTextInputListener.networkId, 80, 55);
        cardPlay.batch.end();
        if (statusAlready) {
            timePlay = 0;
            statusAlready = false;
                //cardPlay.fadeScreenStage.act(delta);
                //cardPlay.fadeScreenStage.draw();
                fadeScence.screenfadeIn(new Image(cardPlay.assetManager.get("tone/white.jpg", Texture.class)),
                        "select", 0);
                //cardPlay.fadeScreenStage.draw();
                //cardPlay.setScreen(new SelectHeroScreen(cardPlay));
        }

//        if (statusConnected = false ||timePlay > 5){
//            System.out.println("No Connected");
//            for (int j = 5; j > 0; j--){
//                lobbyStatus = "Can't connect to server, Returning to main menu in " + j;
//            }
//            cardPlay.setScreen(new Menu(cardPlay));
//        }
        cardPlay.fadeScreenStage.draw();
    }



    public void update(float delta){
        cardPlay.fadeScreenStage.act(delta);
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose()
    {
        //cardPlay.batch.dispose();
    }
}
