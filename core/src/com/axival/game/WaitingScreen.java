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
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.Client;
import com.sun.org.apache.xpath.internal.SourceTree;

import java.io.IOException;

public class WaitingScreen implements Screen {
    public static String lobbyStatus = "null";
    public Client client;
    private ClientListener cnl;
    private BitmapFont font;
    private CardPlay cardPlay;
    private Animation<TextureRegion> animationWaiting;
    private float timePlay;

    private boolean statusAlready;

    private TextureAtlas textureAtlas;

    private FadeScence fadeScence;

    public WaitingScreen(CardPlay cardPlay){

        font = new BitmapFont();
        font.setColor(Color.WHITE);
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
        Gdx.gl.glClearColor(1, 0,0, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        update(delta);
        cardPlay.batch.begin();
        //draw font

        cardPlay.batch.draw(animationWaiting.getKeyFrame(timePlay, true), 0, 0, 1280, 720);
        font.draw(cardPlay.batch,lobbyStatus, 635, 100);
        cardPlay.batch.end();
<<<<<<< HEAD
        if (statusAlready){
=======
        if (statusAlready || timePlay>5){
>>>>>>> AssetManage
            timePlay = 0;
            statusAlready = false;
            //cardPlay.fadeScreenStage.act(delta);
            //cardPlay.fadeScreenStage.draw();
            fadeScence.screenfadeIn(new Image(cardPlay.assetManager.get("tone/black.jpg", Texture.class)),
                    "select", 0);
            //cardPlay.fadeScreenStage.draw();
            //cardPlay.setScreen(new SelectHeroScreen(cardPlay));
        }
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
