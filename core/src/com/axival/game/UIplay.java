package com.axival.game;

import com.axival.game.screen.ScreenPlay;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;


public class UIplay implements Screen {
    //define variable
    private Texture overlayLBottom, overlayRButtom, leftPlayer1, leftPlayer2, rightPlayer1, rightPlayer2;
    private Texture nextPhase, overlaybigbottom, overlaybigtop, mana_right, mana_left, sword, shoe, shield, skill, turn_line;
    private Texture Heart_colour, Mana_colour;
    private Texture heart_left;

    private ScreenPlay screenPlay;
    private CardPlay cardPlay;

    private Stage stage;
    private Image nextPhaseImg, leftPlayerImg1, leftPlayerImg2, rightPlayerImg1, rightPlayerImg2, skillImg1, skillImg2;

    private Image skillCutInDark, skillCutInWizard, skillCutInPriest;

    private Image victoryImg, defeatImg;

    private Image attackImg, defenceImg, attackOn, defenceOn;

    private Image skill01, skill02, skill03, skill01on, skill02on, skill03on;

    private TextureAtlas backCount, classLabel, fontCount;

    private Sprite textBackhealth, textClass, textFonthealth, textBackmana, textFontmana, textSpeed, textAttack, textDefense;
    private Sprite textSpeed1, textAttack1, textDefense1;
    private Sprite charFontHeal1, charFontHeal2, charFontHeal3, charFontHeal4;
    private Sprite charBackHeal1, charBackHeal2, charBackHeal3, charBackHeal4;
    private Sprite charFontMana1, charFontMana2, charFontMana3, charFontMana4;
    private Sprite charBackMana1, charBackMana2, charBackMana3, charBackMana4;

    private int selectedHero;

    public UIplay(CardPlay cardPlay, final ScreenPlay screenPlay){
        //create construct
        this.cardPlay = cardPlay;
        this.screenPlay = screenPlay;

        //hero select
        selectedHero = 0;

        //set assets to variable
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
        if(selectedHero==0){
            skill01 = new Image(new Texture("skill Icon/DT_Fortify BW.png"));
            skill02 = new Image(new Texture("skill Icon/DT_Sword of Aggression BW.png"));
            skill01on = new Image(new Texture("skill Icon/DT_Fortify.png"));
            skill02on = new Image(new Texture("skill Icon/DT_Sword of Aggression.png"));
            skill03 = new Image(new Texture("skill Icon/DT_Blazing Destavation BW.png"));
            skill03on = new Image(new Texture("skill Icon/DT_Blazing Destavation.png"));
        }
        else if(selectedHero==1){
            skill01 = new Image(new Texture("skill Icon/W_Meteor BW.png"));
            skill02 = new Image(new Texture("skill Icon/W_Mana BW.png"));
            skill01on = new Image(new Texture("skill Icon/W_Meteor.png"));
            skill02on = new Image(new Texture("skill Icon/W_Mana.png"));
            skill03 = new Image(new Texture("skill Icon/W_Hurricane BW.png"));
            skill03on = new Image(new Texture("skill Icon/W_Hurricane.png"));
        }
        else if(selectedHero==2){
            skill01 = new Image(new Texture("skill Icon/P_Mercy BW.png"));
            skill02 = new Image(new Texture("skill Icon/P_Cleansing Light BW.png"));
            skill01on = new Image(new Texture("skill Icon/P_Mercy.png"));
            skill02on = new Image(new Texture("skill Icon/P_Cleansing Light.png"));
            skill03 = new Image(new Texture("skill Icon/P_Karma backfire BW.png"));
            skill03on = new Image(new Texture("skill Icon/P_Karma backfire.png"));
        }

        //result game
        victoryImg.setScale(.1f);
        defeatImg.setScale(.1f);
        victoryImg.setPosition(6 - victoryImg.getWidth()/2, 360 - victoryImg.getHeight()/2);
        defeatImg.setPosition(640, 300);

        //set action in button next phase
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

        //set value in var
        leftPlayerImg1 = new Image(leftPlayer1);
        leftPlayerImg2 = new Image(leftPlayer2);
        rightPlayerImg1 = new Image(rightPlayer1);
        rightPlayerImg2 = new Image(rightPlayer2);

        //set action in var
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
                skillCutIn(selectedHero);

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
                skillCutIn(selectedHero);

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
                skillCutIn(selectedHero);

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


        //status game
        backCount = new TextureAtlas("UI_Assets/backCount/backCount.atlas");
        classLabel = new TextureAtlas("UI_Assets/classLabel/classLabel.atlas");
        fontCount = new TextureAtlas("UI_Assets/fontCount/fontCount.atlas");

        //hero health max
        textBackhealth = new Sprite(backCount.findRegion(String.format("s%d", 25)));
        textBackhealth.setPosition(45+10, 35-5);
        textBackhealth.setScale(0.4f);
        textFonthealth = new Sprite(fontCount.findRegion(String.format(2+"")));

        //hero class type
        textClass = new Sprite(classLabel.findRegion(String.format("cw")));
        textClass.setPosition(-60-25, 23);
        textClass.setScale(0.3f);

        //hero health num
        textFonthealth.setPosition(33+7, 35-5);
        textFonthealth.setScale(0.4f);

        //hero max ap
        textBackmana = new Sprite(backCount.findRegion(String.format("s%d", 25)));
        textBackmana.setPosition(45+10, 0-5);
        textBackmana.setScale(0.4f);

        //hero ap num
        textFontmana = new Sprite(fontCount.findRegion(String.format(5+"")));
        textFontmana.setPosition(33+7, 0-5);
        textFontmana.setScale(0.4f);

        //speed ap use
        textSpeed = new Sprite(fontCount.findRegion(String.format(10+"")));
        textSpeed.setPosition(1100, 64);
        textSpeed.setScale(0.4f);

        //attack ap use
        textAttack = new Sprite(fontCount.findRegion(String.format(12+"")));
        textAttack.setPosition(1100, -7);
        textAttack.setScale(0.4f);

        //defence ap use
        textDefense = new Sprite(fontCount.findRegion(String.format(11+"")));
        textDefense.setPosition(1100, 30);
        textDefense.setScale(0.4f);

        //speed range
        textSpeed1 = new Sprite(fontCount.findRegion(String.format(14+"")));
        textSpeed1.setPosition(1170, 64);
        textSpeed1.setScale(0.4f);

        //attack num
        textAttack1 = new Sprite(fontCount.findRegion(String.format(14+"")));
        textAttack1.setPosition(1170, -7);
        textAttack1.setScale(0.4f);

        //defence num
        textDefense1 = new Sprite(fontCount.findRegion(String.format(14+"")));
        textDefense1.setPosition(1170, 30);
        textDefense1.setScale(0.4f);

        //player 1  health
        charFontHeal1 = new Sprite(fontCount.findRegion(String.format(14+"")));
        charFontHeal1.setPosition(220-90, 624-11);
        charFontHeal1.setScale(0.3f);

        //player 2 health
        charFontHeal2 = new Sprite(fontCount.findRegion(String.format(1+"")));
        charFontHeal2.setPosition(360-90, 624-11);
        charFontHeal2.setScale(0.3f);

        //player 3 health
        charFontHeal3 = new Sprite(fontCount.findRegion(String.format(1+"")));
        charFontHeal3.setPosition(808+90, 624-11);
        charFontHeal3.setScale(0.3f);

        //player 4 health
        charFontHeal4 = new Sprite(fontCount.findRegion(String.format(1+"")));
        charFontHeal4.setPosition(950+90, 624-11);
        charFontHeal4.setScale(0.3f);

        //player 1 health full
        charBackHeal1 = new Sprite(backCount.findRegion(String.format("s%d", 25)));
        charBackHeal1.setPosition(230-90, 624-11);
        charBackHeal1.setScale(0.3f);

        //player 2 health full
        charBackHeal2 = new Sprite(backCount.findRegion(String.format("s%d", 25)));
        charBackHeal2.setPosition(370-90, 624-11);
        charBackHeal2.setScale(0.3f);

        //player 3 health full
        charBackHeal3 = new Sprite(backCount.findRegion(String.format("s%d", 25)));
        charBackHeal3.setPosition(818+90, 624-11);
        charBackHeal3.setScale(0.3f);

        //player 4 health full
        charBackHeal4 = new Sprite(backCount.findRegion(String.format("s%d", 25)));
        charBackHeal4.setPosition(960+90, 624-11);
        charBackHeal4.setScale(0.3f);

        //player 1 mana
        charFontMana1 = new Sprite(fontCount.findRegion(String.format(1+"")));
        charFontMana1.setPosition(292-90, 624-11);
        charFontMana1.setScale(0.3f);

        //player 2 mana
        charFontMana2 = new Sprite(fontCount.findRegion(String.format(1+"")));
        charFontMana2.setPosition(430-90, 624-11);
        charFontMana2.setScale(0.3f);

        //player 3 mana
        charFontMana3 = new Sprite(fontCount.findRegion(String.format(1+"")));
        charFontMana3.setPosition(878+80, 624-11);
        charFontMana3.setScale(0.3f);

        //player 4 mana
        charFontMana4 = new Sprite(fontCount.findRegion(String.format(1+"")));
        charFontMana4.setPosition(1028+80, 624-11);
        charFontMana4.setScale(0.3f);

        //player 1 mana full
        charBackMana1 = new Sprite(backCount.findRegion(String.format("s%d", 25)));
        charBackMana1.setPosition(300-90, 624-11);
        charBackMana1.setScale(0.3f);

        //player 2 mana full
        charBackMana2 = new Sprite(backCount.findRegion(String.format("s%d", 25)));
        charBackMana2.setPosition(440-90, 624-11);
        charBackMana2.setScale(0.3f);

        //player 3 mana full
        charBackMana3 = new Sprite(backCount.findRegion(String.format("s%d", 25)));
        charBackMana3.setPosition(888+80, 624-11);
        charBackMana3.setScale(0.3f);

        //player 4 mana full
        charBackMana4 = new Sprite(backCount.findRegion(String.format("s%d", 25)));
        charBackMana4.setPosition(1038+80, 624-11);
        charBackMana4.setScale(0.3f);

        //add actor to stage
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

    //draw assets
    public void runningDraw(){
        cardPlay.batch.draw(overlayLBottom, 0, 0, 235, 125);
        cardPlay.batch.draw(overlayRButtom,1045,0, 235, 125);
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
        cardPlay.batch.draw(mana_left,22,15, 25, 24);
        cardPlay.batch.draw(heart_left,22,50, 25, 21);
        cardPlay.batch.draw(Heart_colour,125,635, 13, 13);
        cardPlay.batch.draw(Mana_colour,200,635, 13, 13);
        cardPlay.batch.draw(Heart_colour,270,635, 13, 13);
        cardPlay.batch.draw(Mana_colour,340,635, 13, 13);
        cardPlay.batch.draw(Heart_colour,893,635, 13, 13);
        cardPlay.batch.draw(Mana_colour,963,635, 13, 13);
        cardPlay.batch.draw(Heart_colour,1038,635, 13, 13);
        cardPlay.batch.draw(Mana_colour,1108,635, 13, 13);

        //draw status in game
        textBackhealth.draw(cardPlay.batch);
        textClass.draw(cardPlay.batch);
        textFonthealth.draw(cardPlay.batch);
        textBackmana.draw((cardPlay.batch));
        textFontmana.draw((cardPlay.batch));
        textDefense.draw((cardPlay.batch));
        textAttack.draw((cardPlay.batch));
        textSpeed.draw((cardPlay.batch));
        textDefense1.draw((cardPlay.batch));
        textAttack1.draw((cardPlay.batch));
        textSpeed1.draw((cardPlay.batch));
        charFontHeal1.draw((cardPlay.batch));
        charFontHeal2.draw((cardPlay.batch));
        charFontHeal3.draw((cardPlay.batch));
        charFontHeal4.draw((cardPlay.batch));
        charBackHeal1.draw((cardPlay.batch));
        charBackHeal2.draw((cardPlay.batch));
        charBackHeal3.draw((cardPlay.batch));
        charBackHeal4.draw((cardPlay.batch));
        charFontMana1.draw((cardPlay.batch));
        charFontMana2.draw((cardPlay.batch));
        charFontMana3.draw((cardPlay.batch));
        charFontMana4.draw((cardPlay.batch));
        charBackMana1.draw((cardPlay.batch));
        charBackMana2.draw((cardPlay.batch));
        charBackMana3.draw((cardPlay.batch));
        charBackMana4.draw((cardPlay.batch));
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

    //show skill cutin when click button skill
    public void skillCutIn(int hero){
        //DT=0, W=1, P=2
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

    //add actor and action to stage
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

    //show result menu
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

