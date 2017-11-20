package com.axival.game.input;

import com.axival.game.CardPlay;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;

public class MyNameInputListener implements Input.TextInputListener {
    private Stage stage;
    public static String nameId;
    private CardPlay cardPlay;
    public boolean successInput;
    private MyTextInputListener listener;
    public MyNameInputListener(Stage stage, CardPlay cardPlay){
        this.stage = stage;
        this.cardPlay = cardPlay;
        this.listener = new MyTextInputListener(stage, cardPlay);
    }
    @Override
    public void input (String text) {
        nameId = text;
        Gdx.input.getTextInput(listener, "Please insert your Server's IP", "", "192.168.1.0");
    }

    public String getInput(){
        return nameId;
    }

    @Override
    public void canceled () {
        stage.getRoot().setColor(.2f, 1, 1, 0);
        stage.getRoot().addAction(Actions.sequence(Actions.parallel(Actions.fadeIn(1f), Actions.scaleTo(1f, 1f, 1f))));
    }
}
