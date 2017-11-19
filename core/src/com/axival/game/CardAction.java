package com.axival.game;

import com.axival.game.screen.ScreenPlay;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import java.util.Arrays;

public class CardAction {
    public ScreenPlay screenPlay;
    int[] cardHandIndex = new int[6];
    private Image popup, popupYes, popupNo;
    private Group popupG;
    private boolean popupStatus;
    public CardAction(ScreenPlay screenPlay){
        this.screenPlay = screenPlay;
    }
    public void cardHandActionFirst(final int currentCard, int delayNum){
        screenPlay.cardDeck[Integer.parseInt(screenPlay.randomCard.getCountCardInHand().get(0))].addAction(Actions.sequence(Actions.alpha(.7f),
                Actions.parallel(Actions.fadeIn(3f, Interpolation.pow2),
                        Actions.moveTo(1280/1.9f, -30, 2f),
                        Actions.scaleTo(.12f,.12f,2f)), Actions.delay(delayNum)));
        screenPlay.cardCountPosY1 = screenPlay.cardDeck[currentCard].getY();
        //cardHandIndex[0] = Integer.parseInt(screenPlay.randomCard.getCountCardInHand().get(0));
        //System.out.println("CardIndex: "+ Arrays.toString(cardHandIndex));
        screenPlay.cardDeck[currentCard].addListener(new ClickListener(){
            private int currentCardListener = currentCard;
            @Override
            public void clicked(InputEvent event, float x, float y){
                System.out.println("Click : "+currentCardListener+", findIncount: "+screenPlay.randomCard.getCountCardInHand().indexOf(currentCardListener+""));
                popupShow(currentCardListener, screenPlay.randomCard.getIdenCardAll()[currentCard]);
            }
            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor){
                screenPlay.cardDeck[currentCardListener].addAction(Actions.sequence(Actions.parallel(Actions.moveTo(screenPlay.cardDeck[currentCardListener].getX(),
                        screenPlay.cardDeck[currentCardListener].getY()+150, .5f),
                        Actions.scaleTo(.23f, .23f, .5f))));
                System.out.println("Hover 1");
            }
            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor fromActor){
                screenPlay.cardDeck[currentCardListener].addAction(Actions.sequence(Actions.parallel(Actions.moveTo(screenPlay.cardDeck[currentCardListener].getX(),
                        CardPlay.V_HEIGHT-screenPlay.cardCountPosY1-51, .5f),
                        Actions.scaleTo(.12f, .12f, .5f))));
                System.out.println("exit Hover 1");
            }
        });
        System.out.printf("PicX: %f, PicY: %f, Size: %fx%f\n", screenPlay.cardDeck[Integer.parseInt(screenPlay.randomCard.getCountCardInHand().get(0))].getX(),
                screenPlay.cardDeck[Integer.parseInt(screenPlay.randomCard.getCountCardInHand().get(0))].getY(),
                screenPlay.cardDeck[Integer.parseInt(screenPlay.randomCard.getCountCardInHand().get(0))].getWidth()*.05*.2,
                screenPlay.cardDeck[Integer.parseInt(screenPlay.randomCard.getCountCardInHand().get(0))].getHeight()*.05*.2);
        screenPlay.cardInHand = 1;
    }
    public void cardHandActionSecond(final int currentCard, int delayNum){
        screenPlay.cardDeck[Integer.parseInt(screenPlay.randomCard.getCountCardInHand().get(1))].addAction(Actions.sequence(Actions.alpha(.7f),
                Actions.parallel(Actions.fadeIn(3f, Interpolation.pow2),
                        Actions.moveTo(1280/2.3f+50, -30, 2f),
                        Actions.scaleTo(.12f,.12f,2f)), Actions.delay(delayNum)));
        screenPlay.cardDeck[Integer.parseInt(screenPlay.randomCard.getCountCardInHand().get(0))].addAction(Actions.sequence(Actions.moveTo(1280/2.2f-50, -30, 1f), Actions.delay(delayNum)));
        screenPlay.cardCountPosY1 = screenPlay.cardDeck[currentCard].getY();
        //cardHandIndex[1] = Integer.parseInt(screenPlay.randomCard.getCountCardInHand().get(1));
        //System.out.println("CardIndex: "+ Arrays.toString(cardHandIndex));
        screenPlay.cardDeck[currentCard].addListener(new ClickListener(){
            private int currentCardListener = currentCard;
            @Override
            public void clicked(InputEvent event, float x, float y){
                System.out.println("Click : "+currentCardListener+", findIncount: "+screenPlay.randomCard.getCountCardInHand().indexOf(currentCardListener+""));
                popupShow(currentCardListener, screenPlay.randomCard.getIdenCardAll()[currentCard]);
                //cardHandActionDel(screenPlay.randomCard.getCountCardInHand().indexOf(currentCardListener+""));
            }
            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor){
                screenPlay.cardDeck[currentCardListener].addAction(Actions.sequence(Actions.parallel(Actions.moveTo(screenPlay.cardDeck[currentCardListener].getX(),
                        screenPlay.cardDeck[currentCardListener].getY()+150, .5f),
                        Actions.scaleTo(.23f, .23f, .5f))));
                System.out.println("Hover 2");
            }
            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor fromActor){
                screenPlay.cardDeck[currentCardListener].addAction(Actions.sequence(Actions.parallel(Actions.moveTo(screenPlay.cardDeck[currentCardListener].getX(),
                        CardPlay.V_HEIGHT-screenPlay.cardCountPosY1-51, .5f),
                        Actions.scaleTo(.12f, .12f, .5f))));
                System.out.println("exit Hover 2");
            }
        });
        System.out.printf("PicX: %f, PicY: %f, Size: %fx%f\n", screenPlay.cardDeck[Integer.parseInt(screenPlay.randomCard.getCountCardInHand().get(1))].getX(),
                screenPlay.cardDeck[Integer.parseInt(screenPlay.randomCard.getCountCardInHand().get(1))].getY(),
                screenPlay.cardDeck[Integer.parseInt(screenPlay.randomCard.getCountCardInHand().get(1))].getWidth()*.05*.2,
                screenPlay.cardDeck[Integer.parseInt(screenPlay.randomCard.getCountCardInHand().get(1))].getHeight()*.05*.2);
        screenPlay.cardInHand = 2;
    }
    public void cardHandActionThirst(final int currentCard, int delayNum){
        screenPlay.cardDeck[Integer.parseInt(screenPlay.randomCard.getCountCardInHand().get(2))].addAction(Actions.sequence(Actions.alpha(.7f),
                Actions.parallel(Actions.fadeIn(3f, Interpolation.pow2),
                        Actions.moveTo(1280/2.2f+100, -30, 2f),
                        Actions.scaleTo(.12f,.12f,2f)), Actions.delay(delayNum)));
        screenPlay.cardDeck[Integer.parseInt(screenPlay.randomCard.getCountCardInHand().get(1))].addAction(Actions.sequence(Actions.moveTo(1280/2.2f, -30, 1f), Actions.delay(delayNum)));
        screenPlay.cardDeck[Integer.parseInt(screenPlay.randomCard.getCountCardInHand().get(0))].addAction(Actions.sequence(Actions.moveTo(1280/2.2f-100, -30, 1f), Actions.delay(delayNum)));
        screenPlay.cardCountPosY1 = screenPlay.cardDeck[currentCard].getY();
        //cardHandIndex[2] = Integer.parseInt(screenPlay.randomCard.getCountCardInHand().get(2));
        //System.out.println("CardIndex: "+ Arrays.toString(cardHandIndex));
        screenPlay.cardDeck[currentCard].addListener(new ClickListener(){
            private int currentCardListener = currentCard;
            @Override
            public void clicked(InputEvent event, float x, float y){
                System.out.println("Click : "+currentCardListener+", findIncount: "+screenPlay.randomCard.getCountCardInHand().indexOf(currentCardListener+""));
                popupShow(currentCardListener, screenPlay.randomCard.getIdenCardAll()[currentCard]);
                //cardHandActionDel(screenPlay.randomCard.getCountCardInHand().indexOf(currentCardListener+""));
            }
            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor){
                screenPlay.cardDeck[currentCardListener].addAction(Actions.sequence(Actions.parallel(Actions.moveTo(screenPlay.cardDeck[currentCardListener].getX(),
                        screenPlay.cardDeck[currentCardListener].getY()+150, .5f),
                        Actions.scaleTo(.23f, .23f, .5f))));
                System.out.println("Hover 3");
            }
            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor fromActor){
                screenPlay.cardDeck[currentCardListener].addAction(Actions.sequence(Actions.parallel(Actions.moveTo(screenPlay.cardDeck[currentCardListener].getX(),
                        CardPlay.V_HEIGHT-screenPlay.cardCountPosY1-51, .5f),
                        Actions.scaleTo(.12f, .12f, .5f))));
                System.out.println("exit Hover 3");
            }
        });
        System.out.printf("PicX: %f, PicY: %f, Size: %fx%f\n", screenPlay.cardDeck[Integer.parseInt(screenPlay.randomCard.getCountCardInHand().get(2))].getX(),
                screenPlay.cardDeck[Integer.parseInt(screenPlay.randomCard.getCountCardInHand().get(2))].getY(),
                screenPlay.cardDeck[Integer.parseInt(screenPlay.randomCard.getCountCardInHand().get(2))].getWidth()*.05*.2,
                screenPlay.cardDeck[Integer.parseInt(screenPlay.randomCard.getCountCardInHand().get(2))].getHeight()*.05*.2);
        screenPlay.cardInHand = 3;
    }
    public void cardHandActionFourth(final int currentCard, int delayNum) {
        screenPlay.cardDeck[Integer.parseInt(screenPlay.randomCard.getCountCardInHand().get(3))].addAction(Actions.sequence(Actions.alpha(.7f),
                Actions.parallel(Actions.fadeIn(3f, Interpolation.pow2),
                        Actions.moveTo(1280 / 2.2f + 150, -30, 2f),
                        Actions.scaleTo(.12f, .12f, 2f)), Actions.delay(delayNum)));
        screenPlay.cardDeck[Integer.parseInt(screenPlay.randomCard.getCountCardInHand().get(2))].addAction(Actions.sequence(Actions.moveTo(1280 / 2.2f + 50, -30, 1f), Actions.delay(delayNum)));
        screenPlay.cardDeck[Integer.parseInt(screenPlay.randomCard.getCountCardInHand().get(1))].addAction(Actions.sequence(Actions.moveTo(1280 / 2.2f - 50, -30, 1f), Actions.delay(delayNum)));
        screenPlay.cardDeck[Integer.parseInt(screenPlay.randomCard.getCountCardInHand().get(0))].addAction(Actions.sequence(Actions.moveTo(1280 / 2.2f - 150, -30, 1f), Actions.delay(delayNum)));
        screenPlay.cardCountPosY1 = screenPlay.cardDeck[currentCard].getY();
        //cardHandIndex[3] = Integer.parseInt(screenPlay.randomCard.getCountCardInHand().get(3));
        //System.out.println("CardIndex: "+ Arrays.toString(cardHandIndex));
        screenPlay.cardDeck[currentCard].addListener(new ClickListener() {
            private int currentCardListener = currentCard;
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("Click : "+currentCardListener+", findIncount: "+screenPlay.randomCard.getCountCardInHand().indexOf(currentCardListener+""));
                popupShow(currentCardListener, screenPlay.randomCard.getIdenCardAll()[currentCard]);
                //cardHandActionDel(screenPlay.randomCard.getCountCardInHand().indexOf(currentCardListener+""));
            }

            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                screenPlay.cardDeck[currentCardListener].addAction(Actions.sequence(Actions.parallel(Actions.moveTo(screenPlay.cardDeck[currentCardListener].getX(),
                        screenPlay.cardDeck[currentCardListener].getY() + 150, .5f),
                        Actions.scaleTo(.23f, .23f, .5f))));
                System.out.println("Hover 4");
            }

            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                screenPlay.cardDeck[currentCardListener].addAction(Actions.sequence(Actions.parallel(Actions.moveTo(screenPlay.cardDeck[currentCardListener].getX(),
                        CardPlay.V_HEIGHT - screenPlay.cardCountPosY1 - 51, .5f),
                        Actions.scaleTo(.12f, .12f, .5f))));
                System.out.println("exit Hover 4");
            }
        });
        screenPlay.cardInHand = 4;
    }
    public void cardHandActionFifth(final int currentCard, int delayNum){
        screenPlay.cardDeck[Integer.parseInt(screenPlay.randomCard.getCountCardInHand().get(4))].addAction(Actions.sequence(Actions.alpha(.7f),
                Actions.parallel(Actions.fadeIn(3f, Interpolation.pow2),
                        Actions.moveTo(1280/2.2f+200, -30, 2f),
                        Actions.scaleTo(.12f,.12f,2f)), Actions.delay(delayNum)));
        screenPlay.cardDeck[Integer.parseInt(screenPlay.randomCard.getCountCardInHand().get(3))].addAction(Actions.sequence(Actions.moveTo(1280/2.2f+100, -30, 1f), Actions.delay(delayNum)));
        screenPlay.cardDeck[Integer.parseInt(screenPlay.randomCard.getCountCardInHand().get(2))].addAction(Actions.sequence(Actions.moveTo(1280/2.2f, -30, 1f), Actions.delay(delayNum)));
        screenPlay.cardDeck[Integer.parseInt(screenPlay.randomCard.getCountCardInHand().get(1))].addAction(Actions.sequence(Actions.moveTo(1280/2.2f-100, -30, 1f), Actions.delay(delayNum)));
        screenPlay.cardDeck[Integer.parseInt(screenPlay.randomCard.getCountCardInHand().get(0))].addAction(Actions.sequence(Actions.moveTo(1280/2.2f-200, -30, 1f), Actions.delay(delayNum)));
        screenPlay.cardCountPosY1 = screenPlay.cardDeck[currentCard].getY();
        //cardHandIndex[4] = Integer.parseInt(screenPlay.randomCard.getCountCardInHand().get(4));
        //System.out.println("CardIndex: "+ Arrays.toString(cardHandIndex));
        screenPlay.cardDeck[currentCard].addListener(new ClickListener(){
            private int currentCardListener = currentCard;
            @Override
            public void clicked(InputEvent event, float x, float y){
                System.out.println("Click : "+currentCardListener+", findIncount: "+screenPlay.randomCard.getCountCardInHand().indexOf(currentCardListener+""));
                popupShow(currentCardListener, screenPlay.randomCard.getIdenCardAll()[currentCard]);
                //cardHandActionDel(screenPlay.randomCard.getCountCardInHand().indexOf(currentCardListener+""));
            }
            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor){
                screenPlay.cardDeck[currentCardListener].addAction(Actions.sequence(Actions.parallel(Actions.moveTo(screenPlay.cardDeck[currentCardListener].getX(),
                        screenPlay.cardDeck[currentCardListener].getY()+150, .5f),
                        Actions.scaleTo(.23f, .23f, .5f))));
                System.out.println("Hover 5");
            }
            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor fromActor){
                screenPlay.cardDeck[currentCardListener].addAction(Actions.sequence(Actions.parallel(Actions.moveTo(screenPlay.cardDeck[currentCardListener].getX(),
                        CardPlay.V_HEIGHT-screenPlay.cardCountPosY1-51, .5f),
                        Actions.scaleTo(.12f, .12f, .5f))));
                System.out.println("exit Hover 5");
            }
        });
        screenPlay.cardInHand = 5;
    }

    public void cardHandActionFull(final int currentCard, int delayNum) {
        screenPlay.cardDeck[Integer.parseInt(screenPlay.randomCard.getCountCardInHand().get(5))].addAction(Actions.sequence(Actions.alpha(.7f),
                Actions.parallel(Actions.fadeIn(3f, Interpolation.pow2),
                        Actions.moveTo(1280 / 2.2f + 200, -30, 2f),
                        Actions.scaleTo(.12f, .12f, 2f)), Actions.delay(delayNum)));
        screenPlay.cardDeck[Integer.parseInt(screenPlay.randomCard.getCountCardInHand().get(3))].addAction(Actions.sequence(Actions.moveTo(1280 / 2.2f + 100, -30, 1f), Actions.delay(delayNum)));
        screenPlay.cardDeck[Integer.parseInt(screenPlay.randomCard.getCountCardInHand().get(2))].addAction(Actions.sequence(Actions.moveTo(1280 / 2.2f, -30, 1f), Actions.delay(delayNum)));
        screenPlay.cardDeck[Integer.parseInt(screenPlay.randomCard.getCountCardInHand().get(1))].addAction(Actions.sequence(Actions.moveTo(1280 / 2.2f - 100, -30, 1f), Actions.delay(delayNum)));
        screenPlay.cardCountPosY1 = screenPlay.cardDeck[currentCard].getY();
        screenPlay.cardDeck[currentCard].addListener(new ClickListener(){
            private int currentCardListener = currentCard;
            @Override
            public void clicked(InputEvent event, float x, float y){
                System.out.println("Click Full: "+currentCardListener+", findIncount: "+screenPlay.randomCard.getCountCardInHand().indexOf(currentCardListener+""));
                popupShow(currentCardListener, screenPlay.randomCard.getIdenCardAll()[currentCard]);
                //cardHandActionDel(screenPlay.randomCard.getCountCardInHand().indexOf(currentCardListener+""));
            }
            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor){
                screenPlay.cardDeck[currentCardListener].addAction(Actions.sequence(Actions.parallel(Actions.moveTo(screenPlay.cardDeck[currentCardListener].getX(),
                        screenPlay.cardDeck[currentCardListener].getY()+150, .5f),
                        Actions.scaleTo(.23f, .23f, .5f))));
                System.out.println("Hover out");
            }
            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor fromActor){
                screenPlay.cardDeck[currentCardListener].addAction(Actions.sequence(Actions.parallel(Actions.moveTo(screenPlay.cardDeck[currentCardListener].getX(),
                        CardPlay.V_HEIGHT-screenPlay.cardCountPosY1-51, .5f),
                        Actions.scaleTo(.12f, .12f, .5f))));
                System.out.println("exit Hover out");
            }
        });
        //cardHandActionDelIndex(currentCard-1);
        //screenPlay.randomCard.removeCardInHandIndex(Integer.parseInt(screenPlay.randomCard.getCountCardInHand().get(5-1)));
        System.out.println("Click FullPO: "+currentCard+", findIncount: "+screenPlay.randomCard.getCountCardInHand().indexOf((Integer.parseInt(screenPlay.randomCard.getCountCardInHand().get(4)))+""));
        cardHandActionDelIndex(screenPlay.randomCard.getCountCardInHand().indexOf((Integer.parseInt(screenPlay.randomCard.getCountCardInHand().get(4)))+""));
        System.out.println("CardIndex: "+ Arrays.toString(cardHandIndex));
        screenPlay.cardInHand = 4;
    }

    public void cardHandActionDelIndex(int indexCard){
        screenPlay.cardDeck[Integer.parseInt(screenPlay.randomCard.getCountCardInHand().get(indexCard))].addAction(Actions.sequence(Actions.fadeOut(2f), Actions.scaleTo(.6f,.6f), Actions.removeActor()));
        screenPlay.randomCard.removeCardInHandIndex(indexCard);
        //System.out.println("Remove: "+screenPlay.randomCard.getCountCardInHand());
    }

    public void cardHandActionDelFirstInOne(int indexCard){
        cardHandActionDelIndex(indexCard);

    }
    public void cardHandActionDelFirstInTwo(int indexCard){
        cardHandActionDelIndex(indexCard);
        screenPlay.cardDeck[Integer.parseInt(screenPlay.randomCard.getCountCardInHand().get(0))].addAction(Actions.sequence(Actions.moveTo(1280/2.2f, -30, 1f)));

    }
    public void cardHandActionDelFirstInThree(int indexCard){
        cardHandActionDelIndex(indexCard);
        screenPlay.cardDeck[Integer.parseInt(screenPlay.randomCard.getCountCardInHand().get(0))].addAction(Actions.sequence(Actions.moveTo(1280/2.2f-50, -30, 1f)));
        screenPlay.cardDeck[Integer.parseInt(screenPlay.randomCard.getCountCardInHand().get(1))].addAction(Actions.sequence(Actions.moveTo(1280/2.2f+50, -30, 1f)));
    }
    public void cardHandActionDelFirstInFour(int indexCard){
        cardHandActionDelIndex(indexCard);
        screenPlay.cardDeck[Integer.parseInt(screenPlay.randomCard.getCountCardInHand().get(0))].addAction(Actions.sequence(Actions.moveTo(1280/2.2f-100, -30, 1f)));
        screenPlay.cardDeck[Integer.parseInt(screenPlay.randomCard.getCountCardInHand().get(1))].addAction(Actions.sequence(Actions.moveTo(1280/2.2f, -30, 1f)));
        screenPlay.cardDeck[Integer.parseInt(screenPlay.randomCard.getCountCardInHand().get(2))].addAction(Actions.sequence(Actions.moveTo(1280/2.2f+100, -30, 1f)));
    }
    public void cardHandActionDelFirstInFive(int indexCard){
        cardHandActionDelIndex(indexCard);
        screenPlay.cardDeck[Integer.parseInt(screenPlay.randomCard.getCountCardInHand().get(0))].addAction(Actions.sequence(Actions.moveTo(1280/2.2f-150, -30, 1f)));
        screenPlay.cardDeck[Integer.parseInt(screenPlay.randomCard.getCountCardInHand().get(1))].addAction(Actions.sequence(Actions.moveTo(1280/2.2f-50, -30, 1f)));
        screenPlay.cardDeck[Integer.parseInt(screenPlay.randomCard.getCountCardInHand().get(2))].addAction(Actions.sequence(Actions.moveTo(1280/2.2f+50, -30, 1f)));
        screenPlay.cardDeck[Integer.parseInt(screenPlay.randomCard.getCountCardInHand().get(3))].addAction(Actions.sequence(Actions.moveTo(1280/2.2f+150, -30, 1f)));
    }


    public void cardHandActionDel(int indexCard){
        if(screenPlay.randomCard.sizeCountCardInHand()==1){
            cardHandActionDelFirstInOne(indexCard);
        }
        else if(screenPlay.randomCard.sizeCountCardInHand()==2){
            cardHandActionDelFirstInTwo(indexCard);
        }
        else if(screenPlay.randomCard.sizeCountCardInHand()==3){
            cardHandActionDelFirstInThree(indexCard);
        }
        else if(screenPlay.randomCard.sizeCountCardInHand()==4){
            cardHandActionDelFirstInFour(indexCard);
        }
        else if(screenPlay.randomCard.sizeCountCardInHand()==5){
            cardHandActionDelFirstInFive(indexCard);
        }
    }

    public void popupShow(final int currentCardListener, final int indexUse){
        if(getPopupOff()){
            return;
        }
        System.out.println("popupShow");
        genPopup();
        screenPlay.stage.addActor(popupG);
        popupG.addAction(Actions.sequence(Actions.fadeIn(1f)));
        popupYes.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("currentCardListener : "+currentCardListener);
                cardHandActionDel(screenPlay.randomCard.getCountCardInHand().indexOf(currentCardListener+""));
                popupDel();
                screenPlay.setChooseCard(indexUse);
            }
        });
        popupNo.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                popupDel();
            }
        });
    }
    public void popupDel(){
        popupG.addAction(Actions.sequence(Actions.fadeOut(1f), Actions.removeActor()));
    }
    public void genPopup(){
        popupG = new Group();
        popup = new Image(new Texture("Popup/Popup BG@1x.png"));
        popup.setPosition(Gdx.graphics.getWidth()/2 - popup.getWidth()/2, Gdx.graphics.getHeight()/2 - popup.getHeight()/2);
        popupYes = new Image(new Texture("Popup/Yes@1x.png"));
        popupYes.setPosition(Gdx.graphics.getWidth()/2 - popup.getWidth()/2 + 70, Gdx.graphics.getHeight()/2 - popup.getHeight()/2 + 40);
        popupNo = new Image(new Texture("Popup/No@1x.png"));
        popupNo.setPosition(Gdx.graphics.getWidth()/2 - popup.getWidth()/2 + 170, Gdx.graphics.getHeight()/2 - popup.getHeight()/2 +40);
        popupG.addActor(popup);
        popupG.addActor(popupNo);
        popupG.addActor(popupYes);
    }
    public boolean getPopupOff(){
        return popupStatus;
    }

    public void setPopupOff(boolean popupStatus){
        this.popupStatus = popupStatus;
        System.out.println("show!!!! popupStatus = "+popupStatus);
    }
}
