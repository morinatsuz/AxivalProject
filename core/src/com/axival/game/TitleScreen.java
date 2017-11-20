package com.axival.game;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.viewport.StretchViewport;

public class TitleScreen implements Screen{
    private CardPlay cardPlay;
    private Animation<TextureRegion> animationBg;
    private Texture texture;
    private Image image;
    private Stage stage;
    public TitleScreen(CardPlay cardPlay){
        this.cardPlay = cardPlay;
        //animationBg = new Animation<TextureRegion>()
        texture = new Texture("Main-Menu/MENU.jpg");
        this.stage = new Stage(new StretchViewport(CardPlay.V_WIDTH, CardPlay.V_HEIGHT, cardPlay.camera));
        image = new Image(new Texture("Main-Menu/MENU.jpg"));

    }
    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        cardPlay.batch.begin();
        cardPlay.batch.draw(texture, 0 ,0);
        cardPlay.batch.end();
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
}
