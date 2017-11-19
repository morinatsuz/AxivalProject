package com.axival.game;

import com.axival.game.screen.LoadingComponent;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.StretchViewport;


public class CardPlay extends Game {
    public static final String TITLE = "Axivals";
    public static final float VERSION = 0.1f;
    public static final int V_WIDTH = 1280;
    public static final int V_HEIGHT = 720;

    //main camera
    public OrthographicCamera camera;

    //main render
    public SpriteBatch batch;

    //main font
    public BitmapFont bitmapFont;

    //Loading all assets in game
    public AssetManager assetManager;

    //Loading all sound in game and manage
    public SoundManager soundManager;

    //effect stage between 2 screen
    public Stage fadeScreenStage;

    @Override
    public void create(){
        //create construct
        assetManager = new AssetManager();
        soundManager = new SoundManager();
        camera = new OrthographicCamera();
        camera.setToOrtho(false, V_WIDTH, V_HEIGHT);
        fadeScreenStage = new Stage(new StretchViewport(CardPlay.V_WIDTH, CardPlay.V_HEIGHT, camera));
        batch = new SpriteBatch();
        bitmapFont = new BitmapFont();
        bitmapFont.setColor(Color.WHITE);

        //check memory
        javaFreeMem();

        //change screen to LoadingComponent class
        this.setScreen(new LoadingComponent(this));
    }

    @Override
    public void render(){
        super.render();
    }

    @Override
    public void dispose() {
        //dispose main
        fadeScreenStage.dispose();
        batch.dispose();
        bitmapFont.dispose();
        assetManager.dispose();
        this.getScreen().dispose();
    }

    public void javaFreeMem() {
        // check memory
        System.out.println("Total Memory"+Runtime.getRuntime().totalMemory());
        // Java's garbage-collected clean Memory Not use
        System.out.println("Free Memory"+Runtime.getRuntime().freeMemory());
    }
}
