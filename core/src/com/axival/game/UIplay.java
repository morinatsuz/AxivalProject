package com.axival.game;

import com.axival.game.screen.ScreenPlay;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;


public class UIplay implements Screen {
    private Texture overlayLBottom, overlayRButtom, leftPlayer1, leftPlayer2, rightPlayer1, rightPlayer2;
    private Texture nextPhase, overlaybigbottom, overlaybigtop, mana_right, mana_left, sword, shoe, shield, skill, turn_line;
    private Texture Heart_colour, Mana_colour;
    private Texture heart_left;
    private Texture skill1, skill2;

    private ScreenPlay screenPlay;
    private CardPlay cardPlay;

    private Stage stage;
    private Image nextPhaseImg, leftPlayerImg1, leftPlayerImg2, rightPlayerImg1, rightPlayerImg2, skillImg1, skillImg2;

    private Image skillCutInDark, skillCutInWizard, skillCutInPriest;

    private Image victoryImg, defeatImg;

    private Image attackImg, defenceImg, attackOn, defenceOn;

    private Image skill01, skill02, skill03, skill01on, skill02on, skill03on;

    public UIplay(CardPlay cardPlay, final ScreenPlay screenPlay){
        this.cardPlay = cardPlay;
        this.screenPlay = screenPlay;
        overlayLBottom = new Texture("UI_Assets/Axival_UI_Assets/Overlay Bottom Left@1x.png");
        overlayRButtom = new Texture("UI_Assets/Axival_UI_Assets/Overlay Bottom Right@1x.png");
        leftPlayer1 = new Texture("UI_Assets/Axival_UI_Assets/Left Player 1@1x.png");
        leftPlayer2 = new Texture("UI_Assets/Axival_UI_Assets/Left Player 2@1x.png");
        rightPlayer1 = new Texture("UI_Assets/Axival_UI_Assets/Right Player 1@1x.png");
        rightPlayer2 = new Texture("UI_Assets/Axival_UI_Assets/Right Player 2@1x.png");
        nextPhase = new Texture("UI_Assets/Axival_UI_Assets/Next Phase Button@1x.png");
        overlaybigbottom = new Texture("UI_Assets/Axival_UI_Assets/Overlay Big Bottom@1x.png");
        overlaybigtop = new Texture("UI_Assets/Axival_UI_Assets/Overlay Big Top@1x.png");
        mana_right = new Texture("UI_Assets/Axival_UI_Assets/Mana Icon Full Right Bottom@1x.png");
        mana_left = new Texture("UI_Assets/Axival_UI_Assets/Mana Left Bottom@1x.png");
        sword = new Texture("UI_Assets/Axival_UI_Assets/Sword Right Bottom@1x.png");
        shield = new Texture("UI_Assets/Axival_UI_Assets/Shield Right Bottom@1x.png");
        shoe = new Texture("UI_Assets/Axival_UI_Assets/Shoe Right Bottom@1x.png");
        //skill = new Texture("UI_Assets/Axival_UI_Assets/Skill@1x.png");
        heart_left = new Texture("UI_Assets/Axival_UI_Assets/Heart Left Buttom@1x.png");
        turn_line = new Texture("UI_Assets/Axival_UI_Assets/Turn Line@1x.png");
        Heart_colour = new Texture("UI_Assets/Axival_UI_Assets/Heart Mini Playerbar@1x.png");
        Mana_colour = new Texture("UI_Assets/Axival_UI_Assets/Mana Mini Playerbar@1x.png");

        //skill cut in load asset
        skillCutInDark = new Image(new Texture("skillCutin/DarkTemp.png"));
        skillCutInWizard = new Image(new Texture("skillCutin/Mage.png"));
        skillCutInPriest = new Image(new Texture("skillCutin/Priest.png"));

        //result game cut in load asset
        victoryImg = new Image(new Texture("result/win.png"));
        defeatImg = new Image(new Texture("result/defeat.png"));

        //atk dff button
        attackImg = new Image(new Texture("skill Icon/Attack BW.png"));
        defenceImg = new Image(new Texture("skill Icon/Defence BW.png"));
        attackOn = new Image(new Texture("skill Icon/Attack.png"));
        defenceOn = new Image(new Texture("skill Icon/Defence.png"));

        //skill button
        if(false){
            skill01 = new Image(new Texture("skill Icon/DT_Fortify BW.png"));
            skill02 = new Image(new Texture("skill Icon/DT_Sword of Aggression BW.png"));
            skill01on = new Image(new Texture("skill Icon/DT_Fortify.png"));
            skill02on = new Image(new Texture("skill Icon/DT_Sword of Aggression.png"));
            skill03 = new Image(new Texture("skill Icon/DT_Blazing Destavation BW.png"));
            skill03on = new Image(new Texture("skill Icon/DT_Blazing Destavation.png"));
        }
        else if(true){
            skill01 = new Image(new Texture("skill Icon/W_Meteor BW.png"));
            skill02 = new Image(new Texture("skill Icon/W_Mana BW.png"));
            skill01on = new Image(new Texture("skill Icon/W_Meteor.png"));
            skill02on = new Image(new Texture("skill Icon/W_Mana.png"));
            skill03 = new Image(new Texture("skill Icon/W_Hurricane BW.png"));
            skill03on = new Image(new Texture("skill Icon/W_Hurricane.png"));
        }
        else if(false){
            skill01 = new Image(new Texture("skill Icon/P_Mercy BW.png"));
            skill02 = new Image(new Texture("skill Icon/P_Cleansing Light BW.png"));
            skill01on = new Image(new Texture("skill Icon/P_Mercy.png"));
            skill02on = new Image(new Texture("skill Icon/P_Cleansing Light.png"));
            skill03 = new Image(new Texture("skill Icon/P_Karma backfire BW.png"));
            skill03on = new Image(new Texture("skill Icon/P_Karma backfire.png"));
        }

        victoryImg.setScale(.1f);
        defeatImg.setScale(.1f);
        victoryImg.setPosition(6 - victoryImg.getWidth()/2, 360 - victoryImg.getHeight()/2);
        defeatImg.setPosition(640, 300);

        nextPhaseImg = new Image(nextPhase);
        nextPhaseImg.setPosition(535, 630);
        nextPhaseImg.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){
                screenPlay.editStatusPhase(6, 0, 1);
                endGameCutIn();
            }
            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                nextPhaseImg.addAction(Actions.sequence(Actions.parallel(Actions.scaleTo(1.1f,1.1f,.5f),
                        Actions.moveTo(525, 630, .5f))));
            }

            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                nextPhaseImg.addAction(Actions.sequence(Actions.parallel(Actions.scaleTo(1f,1f,.5f),
                        Actions.moveTo(535, 630, .5f))));
            }
        });

        leftPlayerImg1 = new Image(leftPlayer1);
        leftPlayerImg2 = new Image(leftPlayer2);
        rightPlayerImg1 = new Image(rightPlayer1);
        rightPlayerImg2 = new Image(rightPlayer2);

        leftPlayerImg1.setPosition(100,646);
        leftPlayerImg2.setPosition(246, 646);
        rightPlayerImg1.setPosition(878, 646);
        rightPlayerImg2.setPosition(1024,646);

        skill01.setPosition(860+60,55);
        skill01.setScale(.1f);
        skill02.setPosition(860+60, 5);
        skill02.setScale(.1f);
        skill03.setPosition(860+110, 25);
        skill03.setScale(.1f);

        attackImg.setPosition(300,55);
        attackImg.setScale(.1f);
        attackOn.setPosition(300,55);
        attackOn.setScale(.1f);

        defenceImg.setPosition(300,5);
        defenceImg.setScale(.1f);
        defenceOn.setPosition(300,5);
        defenceOn.setScale(.1f);

        skill01.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){
                //play skill cut-in
                skillCutIn(1);

            }
            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                //show describe
                skill01.addAction(Actions.sequence(Actions.scaleTo(.1f, .1f, .5f)));
            }

            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                //not show describe
                skill01.addAction(Actions.sequence(Actions.scaleTo(.1f, .1f, .5f)));
            }
        });

        skill02.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){
                //play skill cut-in
                skillCutIn(0);

            }
            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                //show describe
                skill02.addAction(Actions.sequence(Actions.scaleTo(.1f, .1f, .5f)));
            }

            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                //not show describe
                skill02.addAction(Actions.sequence(Actions.scaleTo(.1f, .1f, .5f)));
            }
        });

        skill03.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){
                //play skill cut-in
                skillCutIn(0);

            }
            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                //show describe
                skill03.addAction(Actions.sequence(Actions.scaleTo(.1f, .1f, .5f)));
            }

            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                //not show describe
                skill03.addAction(Actions.sequence(Actions.scaleTo(.1f, .1f, .5f)));
            }
        });



        screenPlay.stage.addActor(nextPhaseImg);
        screenPlay.stage.addActor(leftPlayerImg1);
        screenPlay.stage.addActor(leftPlayerImg2);
        screenPlay.stage.addActor(rightPlayerImg1);
        screenPlay.stage.addActor(rightPlayerImg2);
        screenPlay.stage.addActor(skill01);
        screenPlay.stage.addActor(skill02);
        screenPlay.stage.addActor(skill03);
        screenPlay.stage.addActor(attackOn);
        screenPlay.stage.addActor(defenceImg);
    }

    public void render(){
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
    }

    public void runningDraw(){
        cardPlay.batch.draw(overlayLBottom, 0, 0, 235, 125);
        cardPlay.batch.draw(overlayRButtom,1045,0, 235, 125);
        //cardPlay.batch.draw(leftPlayer1,200,656, 166, 64);
        //cardPlay.batch.draw(leftPlayer2,346,656, 166, 64);
        //cardPlay.batch.draw(rightPlayer1,778,656, 166, 64);
        //cardPlay.batch.draw(rightPlayer2,924,656, 166, 64);
        //cardPlay.batch.draw(nextPhase,550,630, 197, 33);
        cardPlay.batch.draw(overlaybigbottom,0,0, 1280, 250);
        cardPlay.batch.draw(overlaybigtop,0,600, 1280, 250);
        cardPlay.batch.draw(mana_right,1100,15, 14, 14);
        cardPlay.batch.draw(mana_right,1100,50, 14, 14);
        cardPlay.batch.draw(mana_right,1100,85, 14, 14);
        cardPlay.batch.draw(turn_line, 435, 670);
        cardPlay.batch.draw(turn_line, 435, 690);
        cardPlay.batch.draw(sword,1240,15, 26, 26);
        cardPlay.batch.draw(shield,1240,50, 20, 24);
        cardPlay.batch.draw(shoe,1240,85, 26, 24);
        cardPlay.batch.draw(mana_left,10,25, 25, 25);
        cardPlay.batch.draw(heart_left,10,70, 25, 25);
        cardPlay.batch.draw(Heart_colour,125,635, 13, 13);
        cardPlay.batch.draw(Mana_colour,200,635, 13, 13);
        cardPlay.batch.draw(Heart_colour,270,635, 13, 13);
        cardPlay.batch.draw(Mana_colour,340,635, 13, 13);
        cardPlay.batch.draw(Heart_colour,893,635, 13, 13);
        cardPlay.batch.draw(Mana_colour,963,635, 13, 13);
        cardPlay.batch.draw(Heart_colour,1038,635, 13, 13);
        cardPlay.batch.draw(Mana_colour,1108,635, 13, 13);
        //cardPlay.batch.draw(skill1, 860,10, 62, 55);
        //cardPlay.batch.draw(skill2, 860,65, 62,55);
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

    public void skillCutIn(int hero){
        if (hero==0){
            skillAction(skillCutInDark);
        }
        else if (hero==1){
            skillAction(skillCutInWizard);
        }
        else if (hero==2){
            skillAction(skillCutInPriest);
        }
    }

    public void skillAction(Image heros){
        heros.setPosition(1280,0);
        heros.setScale(.206f);
        // use 2 second
        screenPlay.stage.addActor(heros);
        heros.addAction(Actions.sequence(Actions.alpha(0f),
                Actions.parallel(Actions.fadeIn(.5f),
                        Actions.moveTo(800, 0, 1f)),
                Actions.delay(.5f),
                Actions.parallel(Actions.moveTo(-1280,0,.3f),
                        Actions.fadeOut(1f)),
                        Actions.removeActor()));
    }

    public void endGameCutIn(){
        if (true){
            victoryCutIn();
        }
        else {
            defeatCutIn();
        }
    }

    public void victoryCutIn(){
        victoryImg.addAction(Actions.sequence(Actions.parallel(Actions.fadeIn(.2f),
                Actions.scaleTo(.5f, .5f, .5f)),
                Actions.delay(1f), Actions.fadeOut(.2f), Actions.removeActor()));
        screenPlay.stage.addActor(victoryImg);
    }

    public void defeatCutIn(){

    }

}

