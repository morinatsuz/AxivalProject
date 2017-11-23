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
import com.esotericsoftware.kryonet.Client;


public class UIplay implements Screen {
    //define variable
    private Texture overlayLBottom, overlayRButtom, leftPlayer1, leftPlayer2, rightPlayer1, rightPlayer2, arrow;
    private Texture nextPhase, overlaybigbottom, overlaybigtop, mana_right, mana_left, sword, shoe, shield, myTurn;
    private Texture Heart_colour, Mana_colour;
    private Texture heart_left;
    private Image overlayBg;

    private Client client;

    private ScreenPlay screenPlay;
    private CardPlay cardPlay;

    private Stage stage;
    private Image nextPhaseImg, leftPlayerImg1, leftPlayerImg2, rightPlayerImg1, rightPlayerImg2;

    private Image skillCutInDark, skillCutInWizard, skillCutInPriest;

    private Image victoryImg, defeatImg;

    private Image attackImg, defenceImg, attackOn, defenceOn;

    private Image skill01, skill02, skill03, skill01on, skill02on, skill03on;

    private Image descriptSkill01, descriptSkill02, descriptSkill03, descriptAtk, descriptDef;

    private TextureAtlas backCount, classLabel, fontCount, packUi;

    private Sprite textBackhealth, textClass, textFonthealth, textBackmana, textFontmana, textSpeed, textAttack, textDefense;
    private Sprite textSpeed1, textAttack1, textDefense1;
    private Sprite charFontHeal1, charFontHeal2, charFontHeal3, charFontHeal4;
    private Sprite charBackHeal1, charBackHeal2, charBackHeal3, charBackHeal4;
    private Sprite charFontMana1, charFontMana2, charFontMana3, charFontMana4;

    private Texture countAp;

    private int selectedHero;

    private Image actionBar1, actionBar2, drawBar, travelBar, endBar, chainBar;

    public static boolean statusButton;

    public UIplay(CardPlay cardPlay, final ScreenPlay screenPlay, final Client client){
        //create construct
        this.cardPlay = cardPlay;
        this.screenPlay = screenPlay;
        this.client = client;

        //hero select
        selectedHero = 0;

        //set assets to variable
        overlayLBottom = cardPlay.assetManager.get("UI_Assets/Axival_UI_Assets/Overlay Bottom Left@1x.png", Texture.class);
        overlayRButtom = cardPlay.assetManager.get("UI_Assets/Axival_UI_Assets/Overlay Bottom Right@1x.png", Texture.class);
        leftPlayer1 = cardPlay.assetManager.get("UI_Assets/Axival_UI_Assets/Left Player 1@1x.png", Texture.class);
        leftPlayer2 = cardPlay.assetManager.get("UI_Assets/Axival_UI_Assets/Left Player 2@1x.png", Texture.class);
        rightPlayer1 = cardPlay.assetManager.get("UI_Assets/Axival_UI_Assets/Right Player 1@1x.png", Texture.class);
        rightPlayer2 = cardPlay.assetManager.get("UI_Assets/Axival_UI_Assets/Right Player 2@1x.png", Texture.class);
        nextPhase = cardPlay.assetManager.get("UI_Assets/Axival_UI_Assets/Next Phase Button@1x.png", Texture.class);
        overlaybigbottom = cardPlay.assetManager.get("UI_Assets/Axival_UI_Assets/Overlay Big Bottom@1x.png", Texture.class);
        overlaybigtop = cardPlay.assetManager.get("UI_Assets/Axival_UI_Assets/Overlay Big Top@1x.png", Texture.class);
        mana_right = cardPlay.assetManager.get("UI_Assets/Axival_UI_Assets/Mana Icon Full Right Bottom@1x.png", Texture.class);
        mana_left = cardPlay.assetManager.get("UI_Assets/Axival_UI_Assets/Mana Left Bottom@1x.png", Texture.class);
        sword = cardPlay.assetManager.get("UI_Assets/Axival_UI_Assets/Sword Right Bottom@1x.png", Texture.class);
        shield = cardPlay.assetManager.get("UI_Assets/Axival_UI_Assets/Shield Right Bottom@1x.png", Texture.class);
        shoe = cardPlay.assetManager.get("UI_Assets/Axival_UI_Assets/Shoe Right Bottom@1x.png", Texture.class);
        heart_left = cardPlay.assetManager.get("UI_Assets/Axival_UI_Assets/Heart Left Buttom@1x.png", Texture.class);
        Heart_colour = cardPlay.assetManager.get("UI_Assets/Axival_UI_Assets/Heart Mini Playerbar@1x.png", Texture.class);
        Mana_colour = cardPlay.assetManager.get("UI_Assets/Axival_UI_Assets/Mana Mini Playerbar@1x.png", Texture.class);
        myTurn = cardPlay.assetManager.get("UI_Assets/Axival_UI_Assets/Indicator Trun@1x.png", Texture.class);
        arrow = cardPlay.assetManager.get("UI_Assets/Axival_UI_Assets/arrow.png", Texture.class);

        //skill cut in load asset
        skillCutInDark = new Image(cardPlay.assetManager.get("skillCutin/DarkTemp.png", Texture.class));
        skillCutInWizard = new Image(cardPlay.assetManager.get("skillCutin/Mage.png", Texture.class));
        skillCutInPriest = new Image(cardPlay.assetManager.get("skillCutin/Priest.png", Texture.class));

        //result game cut in load asset
        victoryImg = new Image(cardPlay.assetManager.get("result/win.png", Texture.class));
        defeatImg = new Image(cardPlay.assetManager.get("result/defeat.png", Texture.class));

        //atk dff button
        attackImg = new Image(cardPlay.assetManager.get("skill Icon/Attack BW.png", Texture.class));
        defenceImg = new Image(cardPlay.assetManager.get("skill Icon/Defence BW.png", Texture.class));
        attackOn = new Image(cardPlay.assetManager.get("skill Icon/Attack.png", Texture.class));
        defenceOn = new Image(cardPlay.assetManager.get("skill Icon/Defence.png", Texture.class));

        StatusAxival.genClass();
        StatusAxival.genToStatusPlayer(0);
        StatusAxival.genToStatusPlayer(1);
        StatusAxival.genToStatusPlayer(2);
        StatusAxival.genToStatusPlayer(3);

        //skill button
        if(StatusAxival.myClass - 1==0){
            skill01 = new Image(cardPlay.assetManager.get("skill Icon/DT_Fortify BW.png", Texture.class));
            skill02 = new Image(cardPlay.assetManager.get("skill Icon/DT_Sword of Aggression BW.png", Texture.class));
            skill01on = new Image(cardPlay.assetManager.get("skill Icon/DT_Fortify.png", Texture.class));
            skill02on = new Image(cardPlay.assetManager.get("skill Icon/DT_Sword of Aggression.png", Texture.class));
            skill03 = new Image(cardPlay.assetManager.get("skill Icon/DT_Blazing Destavation BW.png", Texture.class));
            skill03on = new Image(cardPlay.assetManager.get("skill Icon/DT_Blazing Destavation.png", Texture.class));
            descriptSkill03 = new Image(cardPlay.assetManager.get("UI_Assets/UI - skill text box/DT_Blazing Devastation.jpg", Texture.class));
            descriptSkill01 = new Image(cardPlay.assetManager.get("UI_Assets/UI - skill text box/DT_Fortify.jpg", Texture.class));
            descriptSkill02 = new Image(cardPlay.assetManager.get("UI_Assets/UI - skill text box/DT_sword of aggression.jpg", Texture.class));
        }
        else if(StatusAxival.myClass - 1==1){
            skill01 = new Image(cardPlay.assetManager.get("skill Icon/W_Meteor BW.png", Texture.class));
            skill02 = new Image(cardPlay.assetManager.get("skill Icon/W_Mana BW.png", Texture.class));
            skill01on = new Image(cardPlay.assetManager.get("skill Icon/W_Meteor.png", Texture.class));
            skill02on = new Image(cardPlay.assetManager.get("skill Icon/W_Mana.png", Texture.class));
            skill03 = new Image(cardPlay.assetManager.get("skill Icon/W_Hurricane BW.png", Texture.class));
            skill03on = new Image(cardPlay.assetManager.get("skill Icon/W_Hurricane.png", Texture.class));
            descriptSkill03 = new Image(cardPlay.assetManager.get("UI_Assets/UI - skill text box/Mage_Hurricane.jpg", Texture.class));
            descriptSkill02 = new Image(cardPlay.assetManager.get("UI_Assets/UI - skill text box/Mage_Mana Redemption v.2.jpg", Texture.class));
            descriptSkill01 = new Image(cardPlay.assetManager.get("UI_Assets/UI - skill text box/Mage_Meteor.jpg", Texture.class));
        }
        else if(StatusAxival.myClass - 1==2){
            skill01 = new Image(cardPlay.assetManager.get("skill Icon/P_Mercy BW.png", Texture.class));
            skill02 = new Image(cardPlay.assetManager.get("skill Icon/P_Cleansing Light BW.png", Texture.class));
            skill01on = new Image(cardPlay.assetManager.get("skill Icon/P_Mercy.png", Texture.class));
            skill02on = new Image(cardPlay.assetManager.get("skill Icon/P_Cleansing Light.png", Texture.class));
            skill03 = new Image(cardPlay.assetManager.get("skill Icon/P_Karma backfire BW.png", Texture.class));
            skill03on = new Image(cardPlay.assetManager.get("skill Icon/P_Karma backfire.png", Texture.class));
            descriptSkill02 = new Image(cardPlay.assetManager.get("UI_Assets/UI - skill text box/Priest_Cleansing Light.jpg", Texture.class));
            descriptSkill03 = new Image(cardPlay.assetManager.get("UI_Assets/UI - skill text box/Priest_Karma Backfire.jpg", Texture.class));
            descriptSkill01 = new Image(cardPlay.assetManager.get("UI_Assets/UI - skill text box/Priest_Mercy.jpg", Texture.class));
        }

        //atk def
        descriptAtk = new Image(cardPlay.assetManager.get("UI_Assets/UI - skill text box/Attack.jpg", Texture.class));
        descriptDef = new Image(cardPlay.assetManager.get("UI_Assets/UI - skill text box/Def.jpg", Texture.class));

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
                if(UIplay.statusButton) {
                    StatusAxival.statusPhase[6] += 1;
                    if (StatusAxival.statusPhase[6] > 4) {
                        StatusAxival.statusPhase[6] = 0;
                    }
                    screenPlay.updateStatus();
                    screenPlay.phaseAll();
                }
                //System.out.println("nextPh : "+StatusAxival.statusPhase[6]);
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
        skill01on.setPosition(860+60,55);
        skill01on.setScale(.1f);
        skill02on.setPosition(860+60, 5);
        skill02on.setScale(.1f);
        skill03on.setPosition(860+110, 25);
        skill03on.setScale(.1f);

        attackImg.setPosition(300,55);
        attackImg.setScale(.1f);
        attackOn.setPosition(300,55);
        attackOn.setScale(.1f);

        defenceImg.setPosition(300,5);
        defenceImg.setScale(.1f);
        defenceOn.setPosition(300,5);
        defenceOn.setScale(.1f);

        descriptSkill01.setScale(.15f);
        descriptSkill02.setScale(.15f);
        descriptSkill03.setScale(.15f);

        descriptAtk.setScale(.15f);
        descriptDef.setScale(.15f);
        descriptAtk.setPosition(908, 0);
        descriptDef.setPosition(908, 0);
        skill01.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){
                //play skill cut-in
                if(UIplay.statusButton) {
                    skillCutIn(StatusAxival.myClass - 1);
                    screenPlay.setChooseAction(1);
                }
            }
            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                //show describe
                skill01.addAction(Actions.sequence(Actions.scaleTo(.11f, .11f, .5f)));
                screenPlay.stage.addActor(descriptSkill01);
            }

            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                //not show describe
                skill01.addAction(Actions.sequence(Actions.scaleTo(.1f, .1f, .5f)));
                descriptSkill01.addAction(Actions.sequence(Actions.removeActor()));
            }
        });

        skill02.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){
                //play skill cut-in
                if(UIplay.statusButton) {
                    skillCutIn(StatusAxival.myClass - 1);
                    screenPlay.setChooseAction(2);
                }
            }
            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                //show describe
                skill02.addAction(Actions.sequence(Actions.scaleTo(.11f, .11f, .5f)));
                screenPlay.stage.addActor(descriptSkill02);
            }

            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                //not show describe
                skill02.addAction(Actions.sequence(Actions.scaleTo(.1f, .1f, .5f)));
                descriptSkill02.addAction(Actions.sequence(Actions.removeActor()));
            }
        });

        skill03.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){
                if(UIplay.statusButton) {
                    if (CardAction.skillUlti) {
                        //play skill cut-in
                        skillCutIn(StatusAxival.myClass - 1);
                        screenPlay.setChooseAction(3);
                    }
                }
            }
            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                //show describe
                skill03.addAction(Actions.sequence(Actions.scaleTo(.11f, .11f, .5f)));
                screenPlay.stage.addActor(descriptSkill03);
            }

            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                //not show describe
                skill03.addAction(Actions.sequence(Actions.scaleTo(.1f, .1f, .5f)));
                descriptSkill03.addAction(Actions.sequence(Actions.removeActor()));
            }
        });

        skill01on.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){
                //play skill cut-in
                if(UIplay.statusButton) {
                    skillCutIn(StatusAxival.myClass - 1);
                    screenPlay.setChooseAction(1);
                }
            }
            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                //show describe
                skill01on.addAction(Actions.sequence(Actions.scaleTo(.11f, .11f, .5f)));
                screenPlay.stage.addActor(descriptSkill01);
            }

            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                //not show describe
                skill01on.addAction(Actions.sequence(Actions.scaleTo(.1f, .1f, .5f)));
                descriptSkill01.addAction(Actions.sequence(Actions.removeActor()));
            }
        });

        skill02on.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){
                //play skill cut-in
                if(UIplay.statusButton) {
                    skillCutIn(StatusAxival.myClass - 1);
                    screenPlay.setChooseAction(2);
                }
            }
            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                //show describe
                skill02on.addAction(Actions.sequence(Actions.scaleTo(.11f, .11f, .5f)));
                screenPlay.stage.addActor(descriptSkill02);
            }

            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                //not show describe
                skill02on.addAction(Actions.sequence(Actions.scaleTo(.1f, .1f, .5f)));
                descriptSkill02.addAction(Actions.sequence(Actions.removeActor()));
            }
        });

        skill03on.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){
                if(UIplay.statusButton) {
                    if (CardAction.skillUlti) {
                        //play skill cut-in
                        skillCutIn(StatusAxival.myClass - 1);
                        screenPlay.setChooseAction(3);
                        CardAction.skillUlti = false;
                    }
                }
            }
            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                //show describe
                skill03on.addAction(Actions.sequence(Actions.scaleTo(.11f, .11f, .5f)));
                screenPlay.stage.addActor(descriptSkill03);
            }

            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                //not show describe
                skill03on.addAction(Actions.sequence(Actions.scaleTo(.1f, .1f, .5f)));
                descriptSkill03.addAction(Actions.sequence(Actions.removeActor()));
            }
        });

        attackOn.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){
                    //play skill cut-in
                if(UIplay.statusButton) {
                    skillCutIn(StatusAxival.myClass - 1);
                    screenPlay.setChooseAction(0);
                }
            }
            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                //show describe
                attackOn.addAction(Actions.sequence(Actions.scaleTo(.11f, .11f, .5f)));
                screenPlay.stage.addActor(descriptAtk);
            }

            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                //not show describe
                attackOn.addAction(Actions.sequence(Actions.scaleTo(.1f, .1f, .5f)));
                descriptAtk.addAction(Actions.sequence(Actions.removeActor()));
            }
        });

        attackImg.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){
                //play skill cut-in
                if(UIplay.statusButton) {
                    skillCutIn(StatusAxival.myClass - 1);
                    screenPlay.setChooseAction(0);
                }
            }
            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                //show describe
                attackImg.addAction(Actions.sequence(Actions.scaleTo(.11f, .11f, .5f)));
                screenPlay.stage.addActor(descriptAtk);
            }

            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                //not show describe
                attackImg.addAction(Actions.sequence(Actions.scaleTo(.1f, .1f, .5f)));
                descriptAtk.addAction(Actions.sequence(Actions.removeActor()));
            }
        });



        //status game
        backCount = cardPlay.assetManager.get("UI_Assets/backCount/backCount.atlas", TextureAtlas.class);
        classLabel = cardPlay.assetManager.get("UI_Assets/classLabel/classLabel.atlas", TextureAtlas.class);
        fontCount = cardPlay.assetManager.get("UI_Assets/fontCount/fontCount.atlas", TextureAtlas.class);


        //hero In screen

        //player 1 health full
        charBackHeal1 = new Sprite(backCount.findRegion(String.format("s%d", StatusAxival.statusPlayer[0][0])));
        charBackHeal1.setPosition(230-90, 624-11);
        charBackHeal1.setScale(0.3f);

        //player 2 health full
        charBackHeal2 = new Sprite(backCount.findRegion(String.format("s%d", StatusAxival.statusPlayer[2][0])));
        charBackHeal2.setPosition(370-90, 624-11);
        charBackHeal2.setScale(0.3f);

        //player 3 health full
        charBackHeal3 = new Sprite(backCount.findRegion(String.format("s%d", StatusAxival.statusPlayer[1][0])));
        charBackHeal3.setPosition(818+90, 624-11);
        charBackHeal3.setScale(0.3f);

        //player 4 health full
        charBackHeal4 = new Sprite(backCount.findRegion(String.format("s%d", StatusAxival.statusPlayer[3][0])));
        charBackHeal4.setPosition(960+90, 624-11);
        charBackHeal4.setScale(0.3f);

        //create packUI to use top bar and overlay effect
        packUi = cardPlay.assetManager.get("UI_Assets/pack/packUI.atlas", TextureAtlas.class);
        actionBar1 = new Image(packUi.findRegion("Action1"));
        actionBar2 = new Image(packUi.findRegion("Action2"));
        travelBar = new Image(packUi.findRegion("Travel"));
        drawBar = new Image(packUi.findRegion("Draw"));
        endBar = new Image(packUi.findRegion("End"));
        overlayBg = new Image(packUi.findRegion("Overlay"));
        chainBar = new Image(packUi.findRegion("Chain"));

        drawBar.setScale(.7f);
        drawBar.setPosition(425, 670);
        drawBar.setWidth(600);
        drawBar.setHeight(55);
        actionBar1.setScale(.7f);
        actionBar1.setPosition(425, 670);
        actionBar1.setWidth(600);
        actionBar1.setHeight(55);
        actionBar2.setScale(.7f);
        actionBar2.setPosition(425, 670);
        actionBar2.setWidth(600);
        actionBar2.setHeight(55);
        travelBar.setScale(.7f);
        travelBar.setPosition(425, 670);
        travelBar.setWidth(600);
        travelBar.setHeight(55);
        endBar.setScale(.7f);
        endBar.setPosition(425, 670);
        endBar.setWidth(600);
        endBar.setHeight(55);
        chainBar.setScale(.7f);
        chainBar.setPosition(425, 670);
        chainBar.setWidth(600);
        chainBar.setHeight(55);

        //max Ap
        countAp = cardPlay.assetManager.get("UI_Assets/backCount/s10.png", Texture.class);

        //hero class type
        if(StatusAxival.myClass - 1 == 0) {
            textClass = new Sprite(classLabel.findRegion(String.format("cd")));
            textClass.setPosition(-60-90, 23);
            textClass.setScale(0.3f);
            //hero health max
            textBackhealth = new Sprite(backCount.findRegion(String.format("s%d", StatusAxival.playerDict[0][0])));
            textBackhealth.setPosition(45+10, 35-5);
            textBackhealth.setScale(0.4f);
            //speed range
            textSpeed1 = new Sprite(fontCount.findRegion(String.format(StatusAxival.playerDict[0][2]+"")));
            textSpeed1.setPosition(1170, 64);
            textSpeed1.setScale(0.4f);

            //speed ap use
            textSpeed = new Sprite(fontCount.findRegion(String.format(StatusAxival.playerDictUseAp[0][0]+"")));
            textSpeed.setPosition(1100, 64);
            textSpeed.setScale(0.4f);

            //attack ap use
            textAttack = new Sprite(fontCount.findRegion(String.format(StatusAxival.playerDictUseAp[0][1]+"")));
            textAttack.setPosition(1100, -7);
            textAttack.setScale(0.4f);

            //defence ap use
            textDefense = new Sprite(fontCount.findRegion(String.format(StatusAxival.playerDictUseAp[0][2]+"")));
            textDefense.setPosition(1100, 30);
            textDefense.setScale(0.4f);
        }
        else if(StatusAxival.myClass - 1==1){
            textClass = new Sprite(classLabel.findRegion(String.format("cw")));
            textClass.setPosition(-60-25, 23);
            textClass.setScale(0.3f);
            //hero health max
            textBackhealth = new Sprite(backCount.findRegion(String.format("s%d", StatusAxival.playerDict[1][0])));
            textBackhealth.setPosition(45+10, 35-5);
            textBackhealth.setScale(0.4f);
            //speed range
            textSpeed1 = new Sprite(fontCount.findRegion(String.format(StatusAxival.playerDict[1][2]+"")));
            textSpeed1.setPosition(1170, 64);
            textSpeed1.setScale(0.4f);

            //speed ap use
            textSpeed = new Sprite(fontCount.findRegion(String.format(StatusAxival.playerDictUseAp[1][0]+"")));
            textSpeed.setPosition(1100, 64);
            textSpeed.setScale(0.4f);

            //attack ap use
            textAttack = new Sprite(fontCount.findRegion(String.format(StatusAxival.playerDictUseAp[1][1]+"")));
            textAttack.setPosition(1100, -7);
            textAttack.setScale(0.4f);

            //defence ap use
            textDefense = new Sprite(fontCount.findRegion(String.format(StatusAxival.playerDictUseAp[1][2]+"")));
            textDefense.setPosition(1100, 30);
            textDefense.setScale(0.4f);
        }
        else if(StatusAxival.myClass - 1==2){
            textClass = new Sprite(classLabel.findRegion(String.format("cp")));
            textClass.setPosition(-60-25, 23);
            textClass.setScale(0.3f);
            //hero health max
            textBackhealth = new Sprite(backCount.findRegion(String.format("s%d", StatusAxival.playerDict[2][0])));
            textBackhealth.setPosition(45+10, 35-5);
            textBackhealth.setScale(0.4f);
            //speed range
            textSpeed1 = new Sprite(fontCount.findRegion(String.format(StatusAxival.playerDict[2][2]+"")));
            textSpeed1.setPosition(1170, 64);
            textSpeed1.setScale(0.4f);

            //speed ap use
            textSpeed = new Sprite(fontCount.findRegion(String.format(StatusAxival.playerDictUseAp[2][0]+"")));
            textSpeed.setPosition(1100, 64);
            textSpeed.setScale(0.4f);

            //attack ap use
            textAttack = new Sprite(fontCount.findRegion(String.format(StatusAxival.playerDictUseAp[2][1]+"")));
            textAttack.setPosition(1100, -7);
            textAttack.setScale(0.4f);

            //defence ap use
            textDefense = new Sprite(fontCount.findRegion(String.format(StatusAxival.playerDictUseAp[2][2]+"")));
            textDefense.setPosition(1100, 30);
            textDefense.setScale(0.4f);
        }


        //add actor to stage
        //screenPlay.stage.addActor(overlayBg);
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
        StatusAxival.genClass();
        //StatusAxival.updateEquip();
        if(StatusAxival.statusPhase[6]==0){
            System.out.println("Phase draw Bar!!");
            endBar.addAction(Actions.sequence(Actions.fadeOut(1f), Actions.removeActor(), Actions.removeActor()));
            screenPlay.stage.addActor(drawBar);
        }
        else if(StatusAxival.statusPhase[6]==1){
            System.out.println("Phase Action 1 Bar!!");
            drawBar.addAction(Actions.sequence(Actions.alpha(1f), Actions.fadeOut(1f), Actions.removeActor()));
            screenPlay.stage.addActor(actionBar1);
        }
        else if(StatusAxival.statusPhase[6]==2){
            System.out.println("Phase Travel Bar!!");
            actionBar1.addAction(Actions.sequence(Actions.alpha(1f), Actions.fadeOut(1f), Actions.removeActor()));
            screenPlay.stage.addActor(travelBar);
        }
        else if(StatusAxival.statusPhase[6]==3){
            System.out.println("Phase Action 2 Bar!!");
            travelBar.addAction(Actions.sequence(Actions.alpha(1f), Actions.fadeOut(1f), Actions.removeActor()));
            screenPlay.stage.addActor(actionBar2);
            endBar.addAction(Actions.sequence(Actions.alpha(1f), Actions.fadeIn(1f)));
        }
        else if(StatusAxival.statusPhase[6]==4){
            System.out.println("Phase End Bar!!");
            endBar.addAction(Actions.sequence(Actions.alpha(1f), Actions.fadeIn(1f)));
            screenPlay.stage.addActor(endBar);
            actionBar1.addAction(Actions.sequence(Actions.alpha(1f), Actions.fadeIn(1f)));
            actionBar2.addAction(Actions.sequence(Actions.alpha(1f), Actions.fadeIn(1f)));
            travelBar.addAction(Actions.sequence(Actions.alpha(1f), Actions.fadeIn(1f)));
            drawBar.addAction(Actions.sequence(Actions.alpha(1f), Actions.fadeIn(1f)));
        }
        else if(StatusAxival.statusPhase[6]==5){
            endBar.addAction(Actions.sequence(Actions.fadeOut(1f), Actions.removeActor(), Actions.removeActor()));
            screenPlay.stage.addActor(drawBar);
            StatusAxival.statusPhase[6] = 0;
        }
        cardPlay.batch.draw(overlayLBottom, 0, 0, 235, 125);
        cardPlay.batch.draw(overlayRButtom,1045,0, 235, 125);
        cardPlay.batch.draw(overlaybigbottom,0,0, 1280, 250);
        cardPlay.batch.draw(overlaybigtop,0,600, 1280, 250);
        cardPlay.batch.draw(mana_right,1100,15, 14, 14);
        cardPlay.batch.draw(mana_right,1100,50, 14, 14);
        cardPlay.batch.draw(mana_right,1100,85, 14, 14);
        cardPlay.batch.draw(arrow, 1140, 69, 50, 50);
        cardPlay.batch.draw(arrow, 1140, 34, 50, 50);
        cardPlay.batch.draw(arrow, 1140, -1, 50, 50);
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
        //my Ap
        cardPlay.batch.draw(countAp, 77, 13, countAp.getWidth()*.38f, countAp.getHeight()*.38f);
        //all player ap
        cardPlay.batch.draw(countAp, 210+28, 613+20, countAp.getWidth()*.3f, countAp.getHeight()*.3f);
        cardPlay.batch.draw(countAp, 350+28, 613+20, countAp.getWidth()*.3f, countAp.getHeight()*.3f);
        cardPlay.batch.draw(countAp, 968+28, 613+20, countAp.getWidth()*.3f, countAp.getHeight()*.3f);
        cardPlay.batch.draw(countAp, 1118+28, 613+20, countAp.getWidth()*.3f, countAp.getHeight()*.3f);

        //pointer in turn
        if(StatusAxival.statusPhase[5]==0) {
            cardPlay.batch.draw(myTurn, 180, 646 + 63);
        }
        else if(StatusAxival.statusPhase[5]==2) {
            cardPlay.batch.draw(myTurn, 320, 646 + 63);
        }
        else if(StatusAxival.statusPhase[5]==1) {
            cardPlay.batch.draw(myTurn, 950, 646 + 63);
        }
        else if(StatusAxival.statusPhase[5]==3) {
            cardPlay.batch.draw(myTurn, 1090, 646 + 63);
        }


        //hero health num statusPlayer[indexuserFromNetwork][select]
        textFonthealth = new Sprite(fontCount.findRegion(String.format(StatusAxival.statusPlayer[StatusAxival.myClassPosition][0]+"")));
        textFonthealth.setPosition(33+7, 35-5);
        textFonthealth.setScale(0.4f);

        //hero ap num statusPlayer[indexuserFromNetwork][select]
        textFontmana = new Sprite(fontCount.findRegion(String.format(StatusAxival.statusPlayer[StatusAxival.myClassPosition][1]+"")));
        textFontmana.setPosition(33+7, 0-5);
        textFontmana.setScale(0.4f);

        //attack num statusPlayer[indexuserFromNetwork][select]
        textAttack1 = new Sprite(fontCount.findRegion(String.format(StatusAxival.statusPlayer[StatusAxival.myClassPosition][3]+"")));
        textAttack1.setPosition(1170, -7);
        textAttack1.setScale(0.4f);

        //defence num statusPlayer[indexuserFromNetwork][select]
        textDefense1 = new Sprite(fontCount.findRegion(String.format(StatusAxival.statusPlayer[StatusAxival.myClassPosition][4]+"")));
        textDefense1.setPosition(1170, 30);
        textDefense1.setScale(0.4f);

        //player 1  health
        charFontHeal1 = new Sprite(fontCount.findRegion(String.format(StatusAxival.statusPlayer[0][0]+"")));
        charFontHeal1.setPosition(220-90, 624-11);
        charFontHeal1.setScale(0.3f);

        //player 2 health
        charFontHeal2 = new Sprite(fontCount.findRegion(String.format(StatusAxival.statusPlayer[2][0]+"")));
        charFontHeal2.setPosition(360-90, 624-11);
        charFontHeal2.setScale(0.3f);

        //player 3 health
        charFontHeal3 = new Sprite(fontCount.findRegion(String.format(StatusAxival.statusPlayer[1][0]+"")));
        charFontHeal3.setPosition(808+90, 624-11);
        charFontHeal3.setScale(0.3f);

        //player 4 health
        charFontHeal4 = new Sprite(fontCount.findRegion(String.format(StatusAxival.statusPlayer[3][0]+"")));
        charFontHeal4.setPosition(950+90, 624-11);
        charFontHeal4.setScale(0.3f);

        //player 1 mana
        charFontMana1 = new Sprite(fontCount.findRegion(String.format(StatusAxival.statusPlayer[0][1]+"")));
        charFontMana1.setPosition(292-90, 624-11);
        charFontMana1.setScale(0.3f);

        //player 2 mana
        charFontMana2 = new Sprite(fontCount.findRegion(String.format(StatusAxival.statusPlayer[2][1]+"")));
        charFontMana2.setPosition(430-90, 624-11);
        charFontMana2.setScale(0.3f);

        //player 3 mana
        charFontMana3 = new Sprite(fontCount.findRegion(String.format(StatusAxival.statusPlayer[1][1]+"")));
        charFontMana3.setPosition(878+80, 624-11);
        charFontMana3.setScale(0.3f);

        //player 4 mana
        charFontMana4 = new Sprite(fontCount.findRegion(String.format(StatusAxival.statusPlayer[3][1]+"")));
        charFontMana4.setPosition(1028+80, 624-11);
        charFontMana4.setScale(0.3f);

        //skill active button
        if(StatusAxival.myClass -1==0) {
            //set skill button on or off
            if(StatusAxival.statusPlayer[StatusAxival.myClassPosition][1]<2){
                skill01.addAction(Actions.sequence(Actions.fadeIn(1f)));
                skill01on.addAction(Actions.sequence(Actions.fadeOut(1f), Actions.removeActor()));
                screenPlay.stage.addActor(skill01);
            }
            else{
                skill01on.addAction(Actions.sequence( Actions.fadeIn(1f)));
                skill01.addAction(Actions.sequence(Actions.fadeOut(1f), Actions.removeActor()));
                screenPlay.stage.addActor(skill01on);
            }
            if(StatusAxival.statusPlayer[StatusAxival.myClassPosition][1]<4){
                skill02.addAction(Actions.sequence(Actions.fadeIn(1f)));
                skill02on.addAction(Actions.sequence(Actions.removeActor()));
                screenPlay.stage.addActor(skill02);
            }
            else{
                skill02on.addAction(Actions.sequence(Actions.fadeIn(1f)));
                skill02.addAction(Actions.sequence(Actions.fadeOut(1f), Actions.removeActor()));
                screenPlay.stage.addActor(skill02on);
            }
            if(StatusAxival.statusPlayer[StatusAxival.myClassPosition][1]<8 && CardAction.skillUlti==false){
                skill03.addAction(Actions.sequence(Actions.fadeIn(1f)));
                skill03on.addAction(Actions.sequence(Actions.removeActor()));
                screenPlay.stage.addActor(skill03);
            }
            else{
                skill03on.addAction(Actions.sequence(Actions.fadeIn(1f)));
                skill03.addAction(Actions.sequence(Actions.fadeOut(1f), Actions.removeActor()));
                screenPlay.stage.addActor(skill03on);
            }
        }
        else if(StatusAxival.myClass -1==1) {
            //set skill button on or off
            if(StatusAxival.statusPlayer[StatusAxival.myClassPosition][1]<4){
                skill01.addAction(Actions.sequence(Actions.fadeIn(1f)));
                skill01on.addAction(Actions.sequence(Actions.fadeOut(1f), Actions.removeActor()));
                screenPlay.stage.addActor(skill01);

            }
            else{
                skill01on.addAction(Actions.sequence( Actions.fadeIn(1f)));
                skill01.addAction(Actions.sequence(Actions.fadeOut(1f), Actions.removeActor()));
                screenPlay.stage.addActor(skill01on);
            }
            if(StatusAxival.statusPlayer[StatusAxival.myClassPosition][1]<0){
                skill02.addAction(Actions.sequence(Actions.fadeIn(1f)));
                skill02on.addAction(Actions.sequence(Actions.removeActor()));
                screenPlay.stage.addActor(skill02);
            }
            else{
                skill02on.addAction(Actions.sequence(Actions.fadeIn(1f)));
                skill02.addAction(Actions.sequence(Actions.fadeOut(1f), Actions.removeActor()));
                screenPlay.stage.addActor(skill02on);
            }
            if(StatusAxival.statusPlayer[StatusAxival.myClassPosition][1]<10 && CardAction.skillUlti==false){
                skill03.addAction(Actions.sequence(Actions.fadeIn(1f)));
                skill03on.addAction(Actions.sequence(Actions.removeActor()));
                screenPlay.stage.addActor(skill03);
            }
            else{
                skill03on.addAction(Actions.sequence(Actions.fadeIn(1f)));
                skill03.addAction(Actions.sequence(Actions.fadeOut(1f), Actions.removeActor()));
                screenPlay.stage.addActor(skill03on);
            }
        }
        else if(StatusAxival.myClass -1==2) {
            //set skill button on or off
            if(StatusAxival.statusPlayer[StatusAxival.myClassPosition][1]<3){
                skill01.addAction(Actions.sequence(Actions.fadeIn(1f)));
                skill01on.addAction(Actions.sequence(Actions.fadeOut(1f), Actions.removeActor()));
                screenPlay.stage.addActor(skill01);
            }
            else{
                skill01on.addAction(Actions.sequence( Actions.fadeIn(1f)));
                skill01.addAction(Actions.sequence(Actions.fadeOut(1f), Actions.removeActor()));
                screenPlay.stage.addActor(skill01on);
            }
            if(StatusAxival.statusPlayer[StatusAxival.myClassPosition][1]<5){
                skill02.addAction(Actions.sequence(Actions.fadeIn(1f)));
                skill02on.addAction(Actions.sequence(Actions.removeActor()));
                screenPlay.stage.addActor(skill02);
            }
            else{
                skill02on.addAction(Actions.sequence(Actions.fadeIn(1f)));
                skill02.addAction(Actions.sequence(Actions.fadeOut(1f), Actions.removeActor()));
                screenPlay.stage.addActor(skill02on);
            }
            if(StatusAxival.statusPlayer[StatusAxival.myClassPosition][1]<7 && CardAction.skillUlti==false){
                skill03.addAction(Actions.sequence(Actions.fadeIn(1f)));
                skill03on.addAction(Actions.sequence(Actions.removeActor()));
                screenPlay.stage.addActor(skill03);
            }
            else{
                skill03on.addAction(Actions.sequence(Actions.fadeIn(1f)));
                skill03.addAction(Actions.sequence(Actions.fadeOut(1f), Actions.removeActor()));
                screenPlay.stage.addActor(skill03on);
            }
        }

        //atk def
        if(StatusAxival.myClass -1==0) {
            //set atk def button on or off
            if(StatusAxival.statusPlayer[StatusAxival.myClassPosition][1]<2){
                attackImg.addAction(Actions.sequence(Actions.fadeIn(1f)));
                attackOn.addAction(Actions.sequence(Actions.fadeOut(1f), Actions.removeActor()));
                screenPlay.stage.addActor(attackImg);
            }
            else{
                attackOn.addAction(Actions.sequence( Actions.fadeIn(1f)));
                attackImg.addAction(Actions.sequence(Actions.fadeOut(1f), Actions.removeActor()));
                screenPlay.stage.addActor(attackOn);
            }

            if(StatusAxival.statusPlayer[StatusAxival.myClassPosition][1]<0){
                defenceImg.addAction(Actions.sequence(Actions.fadeIn(1f)));
                defenceOn.addAction(Actions.sequence(Actions.removeActor()));
                screenPlay.stage.addActor(defenceImg);
            }
            else{
                defenceOn.addAction(Actions.sequence(Actions.fadeIn(1f)));
                defenceImg.addAction(Actions.sequence(Actions.fadeOut(1f), Actions.removeActor()));
                screenPlay.stage.addActor(defenceOn);
            }
        }
        else if(StatusAxival.myClass -1==1) {
            //set skill button on or off
            if(StatusAxival.statusPlayer[StatusAxival.myClassPosition][1]<3){
                attackImg.addAction(Actions.sequence(Actions.fadeIn(1f)));
                attackOn.addAction(Actions.sequence(Actions.fadeOut(1f), Actions.removeActor()));
                screenPlay.stage.addActor(attackImg);
            }
            else{
                attackOn.addAction(Actions.sequence( Actions.fadeIn(1f)));
                attackImg.addAction(Actions.sequence(Actions.fadeOut(1f), Actions.removeActor()));
                screenPlay.stage.addActor(attackOn);
            }

            if(StatusAxival.statusPlayer[StatusAxival.myClassPosition][1]<0){
                defenceImg.addAction(Actions.sequence(Actions.fadeIn(1f)));
                defenceOn.addAction(Actions.sequence(Actions.removeActor()));
                screenPlay.stage.addActor(defenceImg);
            }
            else{
                defenceOn.addAction(Actions.sequence(Actions.fadeIn(1f)));
                defenceImg.addAction(Actions.sequence(Actions.fadeOut(1f), Actions.removeActor()));
                screenPlay.stage.addActor(defenceOn);
            }
        }
        else if(StatusAxival.myClass -1==2) {
            //set skill button on or off
            if(StatusAxival.statusPlayer[StatusAxival.myClassPosition][1]<3){
                attackImg.addAction(Actions.sequence(Actions.fadeIn(1f)));
                attackOn.addAction(Actions.sequence(Actions.fadeOut(1f), Actions.removeActor()));
                screenPlay.stage.addActor(attackImg);
            }
            else{
                attackOn.addAction(Actions.sequence( Actions.fadeIn(1f)));
                attackImg.addAction(Actions.sequence(Actions.fadeOut(1f), Actions.removeActor()));
                screenPlay.stage.addActor(attackOn);
            }

            if(StatusAxival.statusPlayer[StatusAxival.myClassPosition][1]<0){
                defenceImg.addAction(Actions.sequence(Actions.fadeIn(1f)));
                defenceOn.addAction(Actions.sequence(Actions.removeActor()));
                screenPlay.stage.addActor(defenceImg);
            }
            else{
                defenceOn.addAction(Actions.sequence(Actions.fadeIn(1f)));
                defenceImg.addAction(Actions.sequence(Actions.fadeOut(1f), Actions.removeActor()));
                screenPlay.stage.addActor(defenceOn);
            }
        }

        //draw status in game
        textBackhealth.draw(cardPlay.batch);
        textClass.draw(cardPlay.batch);
        textFonthealth.draw(cardPlay.batch);
//        textBackmana.draw((cardPlay.batch));
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

