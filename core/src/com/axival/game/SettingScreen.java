package com.axival.game;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.StretchViewport;

public class SettingScreen implements Screen, InputProcessor {
    private Texture textureBg;
    private Image imageOn1, imageOn2;
    private Image imageOff1, imageOff2;

    private Stage stage;
    private CardPlay cardPlay;

    public SettingScreen(CardPlay cardPlay){
        this.cardPlay = cardPlay;
        this.stage = new Stage(new StretchViewport(CardPlay.V_WIDTH, CardPlay.V_HEIGHT, cardPlay.camera));
        InputMultiplexer inputMultiplexer = new InputMultiplexer(stage, this);
        Gdx.input.setInputProcessor(inputMultiplexer);
    }

    @Override
    public void show() {
        textureBg = cardPlay.assetManager.get("setting/BG.png", Texture.class);
        imageOff1 = new Image(cardPlay.assetManager.get("setting/Off.png", Texture.class));
        imageOn1 = new Image(cardPlay.assetManager.get("setting/On.png", Texture.class));
        imageOff2 = new Image(cardPlay.assetManager.get("setting/Off.png", Texture.class));
        imageOn2 = new Image(cardPlay.assetManager.get("setting/On.png", Texture.class));
        imageOn1.setPosition(250, 400);
        imageOff1.setPosition(240, 400);
        imageOn2.setPosition(250, 320);
        imageOff2.setPosition(240, 320);
        imageOn1.addListener(new ClickListener(){
                @Override
                public void clicked(InputEvent event, float x, float y){
                    imageOn1.addAction(Actions.sequence(Actions.removeActor()));
                    cardPlay.soundManager.stopBgmAll(true);
                    stage.addActor(imageOff1);
                    imageOff1.addAction(Actions.sequence(Actions.fadeIn(1f)));
                }
        });
        imageOff1.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){
                imageOff1.addAction(Actions.sequence(Actions.removeActor()));
                cardPlay.soundManager.stopBgmAll(false);
                cardPlay.soundManager.playBgm(0);
                stage.addActor(imageOn1);
                imageOn1.addAction(Actions.sequence(Actions.fadeIn(1f)));
            }
        });
        imageOn2.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){
                imageOn2.addAction(Actions.sequence(Actions.removeActor()));
                cardPlay.soundManager.stopSfxAll(true);
                stage.addActor(imageOff2);
                imageOff2.addAction(Actions.sequence(Actions.fadeIn(1f)));
            }
        });
        imageOff2.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){
                imageOff2.addAction(Actions.sequence(Actions.removeActor()));
                cardPlay.soundManager.stopSfxAll(false);
                stage.addActor(imageOn2);
                imageOn2.addAction(Actions.sequence(Actions.fadeIn(1f)));
            }
        });
        if(cardPlay.soundManager.checkMusicStatusBgm(0)) {
            stage.addActor(imageOn1);
        }
        else {
            stage.addActor(imageOff1);
        }
        if(cardPlay.soundManager.checkMusicStatusSfx(0)) {
            stage.addActor(imageOn2);
        }
        else {
            stage.addActor(imageOff2);
        }

    }

    public void update(float delta){
        stage.act(delta);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        update(delta);
        cardPlay.batch.setProjectionMatrix(cardPlay.camera.combined);
        cardPlay.batch.begin();
        cardPlay.batch.draw(textureBg, 0, 0, 1280,720);
        cardPlay.batch.end();
        stage.draw();
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
    public void dispose() {

    }

    @Override
    public boolean keyDown(int keycode) {
        if(keycode== Input.Keys.ESCAPE){
            cardPlay.setScreen(new Menu(cardPlay));
        }
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}
