package com.axival.game;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.GdxRuntimeException;

public class TransitionScreen implements Screen {
    private CardPlay cardPlay;
    private Screen first;
    private Stage stage;
    private Texture texture;
    private Image _transitionImage, image;
    private Action _screenFadeOutAction, _screenFadeInAction;
    public TransitionScreen(CardPlay cardPlay){
        this.cardPlay = cardPlay;
        //this.first = screen;
        Pixmap pixmap = new Pixmap(1, 1, Pixmap.Format.RGBA8888);
        pixmap.setColor(Color.BLACK);
        pixmap.fill();
        Drawable drawable = new TextureRegionDrawable(
                new TextureRegion(new Texture(pixmap)));
        _transitionImage = new Image();
        _transitionImage.setFillParent(true);
        _transitionImage.setDrawable(drawable);
        _screenFadeOutAction = new Action() {
            @Override
            public boolean act(float delta) {
                _transitionImage.addAction(
                        Actions.sequence(
                                Actions.alpha(0),
                                Actions.fadeIn(3)
                        ));
                return true;
            }
        };
        _screenFadeInAction = new Action() {
            @Override
            public boolean act(float delta) {
                _transitionImage.addAction(
                        Actions.sequence(
                                Actions.alpha(1),
                                Actions.fadeOut(3)
                        ));
                return true;
            }
        };
    }
    public void setWhiteStartFade(){
        image= new Image(new TextureRegion(getTexture()));
        image.setSize(cardPlay.transitionStage.getWidth(),cardPlay.transitionStage.getHeight());
        image.setOrigin(cardPlay.transitionStage.getWidth()/2,cardPlay.transitionStage.getHeight()/2);
        image.setColor(Color.WHITE);
    }
    public void setBlackStartFade(){
        image = new Image(new TextureRegion(getTexture()));
        image.setSize(cardPlay.transitionStage.getWidth(),cardPlay.transitionStage.getHeight());
        image.setOrigin(cardPlay.transitionStage.getWidth()/2,cardPlay.transitionStage.getHeight()/2);
        image.setColor(Color.BLACK);
    }

    public Image getImage(){
        return image;
    }

    public void setFade(int colorId){
        if(colorId==0){
            setWhiteStartFade();
            cardPlay.transitionStage.addActor(getImage());
            image.addAction(Actions.sequence(Actions.color(Color.WHITE,1), Actions.run(new Runnable() {
                @Override
                public void run() {
                    cardPlay.setScreen(new Menu(cardPlay));
                    System.out.println("in fade");
                }
            })));
            image.addAction(Actions.sequence(Actions.color(Color.BLACK,1)));
        }
        else if(colorId==1){
            setBlackStartFade();
            cardPlay.transitionStage.addActor(getImage());
            image.addAction(Actions.sequence(Actions.color(Color.BLACK,1), Actions.run(new Runnable() {
                @Override
                public void run() {
                    cardPlay.setScreen(new Menu(cardPlay));
                    System.out.println("in fade");
                }
            })));
            image.addAction(Actions.sequence(Actions.color(Color.WHITE,1)));
        }
    }

    public void fadeOut(int colorId){
        if(colorId==0){
            image.addAction(Actions.sequence(Actions.color(Color.WHITE, 2f), Actions.removeActor()));
            cardPlay.transitionStage.addActor(image);
        }
    }

    @Override
    public void show() {
        setFade(1);
    }

    public static Texture getTexture(){

        Pixmap pixmap;
        try {
            pixmap = new Pixmap(1, 1, Pixmap.Format.RGBA8888);
        }catch (GdxRuntimeException e)
        {
            pixmap = new Pixmap(1,1, Pixmap.Format.RGB565);
        }
        pixmap.setColor(Color.WHITE);
        pixmap.drawRectangle(0,0,1,1);

        return new Texture(pixmap);
    }

    @Override
    public void render(float delta) {
        //Gdx.gl.glClearColor(1, 1, 1, 1);
        //Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        //cardPlay.transitionStage.getRoot().setColor(1, 1, 1, 0);
        //cardPlay.transitionStage.getRoot().addAction(Actions.sequence(Actions.fadeIn(3f)));
        update(delta);
        cardPlay.transitionStage.draw();
    }

    public void update(float delta){
        cardPlay.transitionStage.act(delta);
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
