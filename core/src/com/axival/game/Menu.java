package com.axival.game;

import com.axival.game.fade.FadeScence;
import com.axival.game.input.MyTextInputListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.ParticleEffectPool;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.StretchViewport;

public class Menu implements Screen {
    private Stage stage;
    private Label outputLabel;
    private final CardPlay cardPlay;

    private Texture textureBg;
    private Image buttonImgPlay, buttonImgTutorial, buttonImgSetting, buttonImgExit, logoMenu;

    private ParticleEffect prototypeM;
    private ParticleEffectPool pool;
    private Array<ParticleEffectPool.PooledEffect> effect;

    private MyTextInputListener listener;
    private FadeScence fadeScence;

    private int count=1;

    public Menu(final CardPlay cardPlay){
        this.fadeScence = new FadeScence(cardPlay);
        this.cardPlay = cardPlay;
        this.stage = new Stage(new StretchViewport(CardPlay.V_WIDTH, CardPlay.V_HEIGHT, cardPlay.camera));
        Gdx.input.setInputProcessor(stage);

        prototypeM = new ParticleEffect();
        prototypeM = cardPlay.assetManager.get("effect01.party");
        prototypeM.getEmitters().first().setPosition(Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2);
        prototypeM.start();

        //sound play bgm 0
            cardPlay.soundManager.playBgm(0);

        //check memory
        cardPlay.javaFreeMem();

        //input dialog
        this.listener = new MyTextInputListener(stage, cardPlay);

        //TransitionScreen transitionScreen = new TransitionScreen(cardPlay);
        //transitionScreen.setWhiteStartFade();
        //transitionScreen.fadeOut(0);
    }

    @Override
    public void show() {
        textureBg = cardPlay.assetManager.get("bgM.jpg", Texture.class);
        logoMenu = new Image(cardPlay.assetManager.get("Main-Menu/Game Logo.png", Texture.class));
        logoMenu.setPosition(59, 630);
        buttonImgPlay = new Image(cardPlay.assetManager.get("Main-Menu/Play.png", Texture.class));
        buttonImgPlay.setScale(.95f);
        buttonImgPlay.setPosition(40, 15);
        buttonImgSetting = new Image(cardPlay.assetManager.get("Main-Menu/Setting.png", Texture.class));
        buttonImgSetting.setScale(.95f);
        buttonImgSetting.setPosition(620, 15);
        buttonImgTutorial = new Image(cardPlay.assetManager.get("Main-Menu/Tutorial.png", Texture.class));
        buttonImgTutorial.setScale(.95f);
        buttonImgTutorial.setPosition(390, 15);
        buttonImgExit = new Image(cardPlay.assetManager.get("Main-Menu/Exit.png", Texture.class));
        buttonImgExit.setScale(.95f);
        buttonImgExit.setPosition(880,49);

        buttonImgPlay.addListener(new InputListener(){
            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
            }
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                System.out.println("Menu");
                cardPlay.soundManager.playSfx(0);
                //cardPlay.setScreen(new ScreenPlay(cardPlay));
                stage.getRoot().setColor(.2f, 1, 1, 1);
                stage.getRoot().addAction(Actions.sequence(Actions.parallel(Actions.fadeOut(1f), Actions.scaleTo(.1f, .1f, 1f))));
                Gdx.input.getTextInput(listener, "Please insert your Server's IP", "", "192.168.1.0");
                //cardPlay.setScreen(new WaitingScreen(cardPlay));
                return true;
            }
        });



        buttonImgSetting.addListener(new InputListener(){
            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
            }
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                System.out.println("setting");
                cardPlay.soundManager.playSfx(0);
                cardPlay.setScreen(new SettingScreen(cardPlay));
                return true;
            }
        });
        buttonImgExit.addListener(new InputListener(){
            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
            }
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                System.out.println("Exit");
                cardPlay.soundManager.playSfx(0);
                Gdx.app.exit();
                return true;
            }
        });

        stage.addActor(buttonImgPlay);
        stage.addActor(buttonImgSetting);
        stage.addActor(buttonImgTutorial);
        stage.addActor(buttonImgExit);
        stage.addActor(logoMenu);

        /*
        Action switchScreenAction = new Action(){
            @Override
            public boolean act(float delta){
                cardPlay.setScreen(new ScreenPlay(cardPlay));
                return true;
            }
        };

        stage.addAction(Actions.sequence(
                Actions.fadeIn(2)
                , switchScreenAction
        ));*/

        fadeScence.screenfadeOut(new Image(cardPlay.assetManager.get("tone/white.jpg", Texture.class)), 1);

    }

    @Override
    public void render(float delta) {

        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        update(delta);
        cardPlay.batch.setProjectionMatrix(cardPlay.camera.combined);
        cardPlay.batch.begin();
        cardPlay.batch.draw(textureBg, 0, 0, 1280,720);
        prototypeM.draw(cardPlay.batch);
        cardPlay.batch.end();
        stage.draw();

        if (listener.getInput()!=null && count == 1){
            fadeScence.screenfadeIn(new Image(cardPlay.assetManager.get("tone/white.jpg", Texture.class)), "wait", 0);
            count = 0;
            //cardPlay.setScreen(new WaitingScreen(cardPlay));
        }

        //stage.getRoot().setColor(.2f, 1, 1, 0);
        //stage.getRoot().addAction(Actions.sequence(Actions.fadeIn(3f)));

        if(prototypeM.isComplete()){
            prototypeM.reset();
        }
        cardPlay.fadeScreenStage.draw();

    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, false);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    public void update(float delta){
        stage.act(delta);
        cardPlay.fadeScreenStage.act(delta);
        prototypeM.update(delta);
    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}

