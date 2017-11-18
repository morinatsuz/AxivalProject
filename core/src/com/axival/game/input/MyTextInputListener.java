package com.axival.game.input;

import com.axival.game.CardPlay;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;

public class MyTextInputListener implements Input.TextInputListener {
    private Stage stage;
    public static String networkId;
    private CardPlay cardPlay;
    public boolean successInput;
    public MyTextInputListener(Stage stage, CardPlay cardPlay){
        this.stage = stage;
        this.cardPlay = cardPlay;
    }
    @Override
    public void input (String text) {
        networkId = text;
    }

    public String getInput(){
        return networkId;
    }

    @Override
    public void canceled () {
        stage.getRoot().setColor(.2f, 1, 1, 0);
        stage.getRoot().addAction(Actions.sequence(Actions.parallel(Actions.fadeIn(1f), Actions.scaleTo(1f, 1f, 1f))));
    }
}
