package com.axival.game.screen;

import com.axival.game.*;
import com.axival.game.MapPlay.Hero;
import com.axival.game.MapPlay.MapScreen;
import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.ParticleEffectPool;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.GdxRuntimeException;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.viewport.StretchViewport;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;


public class ScreenPlay implements Screen, InputProcessor {
    public Stage stage;

    private ParticleEffect prototype;
    private ParticleEffectPool pool;
    private Array<ParticleEffectPool.PooledEffect> effect;

    public RandomCard randomCard;


    public boolean solveUp, solveDown, solveLeft, solveRight;

    public Image[] cardDeck;
    private int maxCard=23, currentCard=0;

    public int cardInHand=0;
    public float cardCountPosY1;

    private CardAction cardAction;

    private UIplay uIplay;

    private final CardPlay cardPlay;

    private MapScreen mapScreen;

    public int[] statusPhase;

    private long startTime = 0;
    private int countInLoop = 0;

    private CalculatorManager calculatorManager;

    private int chooseSkill = 0;
    private int chooseCard = 0;

    public ScreenPlay(final CardPlay cardPlay){
        //set main render object and Input
        this.cardPlay = cardPlay;
        this.stage = new Stage(new StretchViewport(CardPlay.V_WIDTH, CardPlay.V_HEIGHT, cardPlay.camera));
        this.cardCountPosY1 = 0;
        InputMultiplexer inputMultiplexer = new InputMultiplexer(stage, this);
        Gdx.input.setInputProcessor(inputMultiplexer);

        //cursor change
        Pixmap pm = new Pixmap(Gdx.files.internal("cursorImage2.png"));
        Gdx.graphics.setCursor(Gdx.graphics.newCursor(pm, pm.getWidth()/2, pm.getHeight()/2));
        pm.dispose();

        //effect define
        prototype = new ParticleEffect();
        prototype = cardPlay.assetManager.get("effect01.party");
        prototype.getEmitters().first().setPosition(Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2);
        prototype.start();

        //set value from network
        statusPhase = new int[13];
        statusInput();

        //set parameter other class
        this.randomCard = new RandomCard(cardPlay);
        this.cardDeck = randomCard.allCardDeck(maxCard);
        this.cardAction = new CardAction(this, randomCard);
        this.uIplay = new UIplay(this.cardPlay, this);
        this.mapScreen = new MapScreen(this.cardPlay, this);
        this.calculatorManager = new CalculatorManager(this, mapScreen);

        //check phase
        phaseAll();

        //check memory
        cardPlay.javaFreeMem();

        //statusPhase[0] = 0;
        System.out.println("statusPhase"+ Arrays.toString(statusPhase));
    }

    @Override
    public void show() {
        System.out.println("screen");
    }

    public void setCardHandR(int indexCard){
        stage.addActor(cardDeck[indexCard]);
    }

    public void cardHandAction(int delay){
        System.out.println("Size Count Hand : "+randomCard.sizeCountCardInHand());
        if(randomCard.sizeCountCardInHand()==1){
            cardAction.cardHandActionFirst(currentCard, delay);
        }
        else if(randomCard.sizeCountCardInHand()==2){
            cardAction.cardHandActionSecond(currentCard, delay);
        }
        else if(randomCard.sizeCountCardInHand()==3){
            cardAction.cardHandActionThirst(currentCard, delay);
        }
        else if(randomCard.sizeCountCardInHand()==4){
            cardAction.cardHandActionFourth(currentCard, delay);
        }
        else if(randomCard.sizeCountCardInHand()==5){
            cardAction.cardHandActionFifth(currentCard, delay);
        }
        else if(randomCard.sizeCountCardInHand()==6){
            cardAction.cardHandActionFull(currentCard, delay);
            System.out.println("Out Hand");
        }
    }


    @Override
    public void render(float delta) {
        statusPhase[8] = chooseCard;
        if(solveUp) {
            /*
            cardHandR.getChildren().get(0).addAction(Actions.parallel(Actions.moveTo(200, 0, 5),
                    Actions.rotateBy(90, 5)));*/
            //cardHandAction();
        }
        if(solveLeft){
            //cardHandR.getChildren().get(0).addAction(Actions.sequence(Actions.moveTo(1200, 500)));
        }
        Gdx.gl.glClearColor(.25f,.25f,.25f,1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        update(delta);
        cardPlay.batch.setProjectionMatrix(cardPlay.camera.combined);
        mapScreen.render(delta);
        cardPlay.batch.begin();
        uIplay.runningDraw();
        cardPlay.bitmapFont.draw(cardPlay.batch, "Screen: Playing..,", 100, 100);
        prototype.draw(cardPlay.batch);
        cardPlay.batch.end();
        stage.draw();
        if(prototype.isComplete()){
            prototype.reset();
        }
    }

    public void update(float delta)
    {
        //mapScreen.update(delta);
        stage.act(delta);
        prototype.update(delta);
    }

    @Override
    public void resize(int width, int height) {
        mapScreen.resize(width, height);
        stage.getViewport().update(width, height, false);
    }

    @Override
    public void pause() {
        System.out.println("pause");
    }

    @Override
    public void resume() {
        System.out.println("resume");
    }

    @Override
    public void hide() {
        System.out.println("hide");
    }

    @Override
    public void dispose() {
        System.out.println("dispose");
    }

    @Override
    public boolean keyDown(int keycode) {
        if (keycode== Input.Keys.UP) {
            solveUp = true;

//            setCardHandR(currentCard);
//            randomCard.setCardInHandIndex(currentCard);
//            cardHandAction(0);
//            Timer.schedule(new Timer.Task() {
//                @Override
//                public void run() {
//                    currentCard++;
//                    setCardHandR(currentCard);
//                    randomCard.setCardInHandIndex(currentCard);
//                    cardHandAction(0);
//                    if (currentCard==9){
//                        Timer.instance().stop();
//                    }
//                }
//            }, 3, 2);
        }
        if (keycode == Input.Keys.UP) {
            solveUp = true;
        }
        if (keycode == Input.Keys.DOWN) {
            solveDown = true;
        }
        if (keycode == Input.Keys.LEFT) {
            solveLeft = true;
        }
        if (keycode == Input.Keys.RIGHT) {
            solveRight = true;
        }
        if (keycode == Input.Keys.O) {
            currentCard++;
            if (currentCard < maxCard) {
                setCardHandR(currentCard);
                randomCard.setCardInHandIndex(currentCard);
            }
        }

        //Testing all animation
        if (keycode == Input.Keys.Q) {
            System.out.println("N.Atk is Activated");
            chooseSkill = 0;
        }
        if (keycode == Input.Keys.W) {
            System.out.println("1st Skill is Activated");
            chooseSkill = 1;
        }
        if (keycode == Input.Keys.E) {
            System.out.println("2nd Skill is Activated");
            chooseSkill = 2;
        }
        if (keycode == Input.Keys.R) {
            System.out.println("3rd Skill is Activated");
            chooseSkill = 3;
        }
        if (keycode == Input.Keys.Z) {
            System.out.println("Potion is Activated");
            chooseSkill = 4;
            chooseCard = 0;
        }
        if (keycode == Input.Keys.X) {
            System.out.println("Mega Potion is Activated");
            chooseSkill = 5;
            chooseCard = 1;
        }
        if (keycode == Input.Keys.C) {
            System.out.println("Shield is Activated");
            chooseSkill = 6;
            chooseCard = 2;
        }
        if (keycode == Input.Keys.V) {
            System.out.println("Super Armor is Activated");
            chooseSkill = 7;
            chooseCard = 3;
        }
        if (keycode == Input.Keys.B) {
            System.out.println("Thunder Bolt is Activated");
            chooseSkill = 8;
            chooseCard = 4;
        }
        if (keycode == Input.Keys.NUM_1) {
            System.out.println("Changed to 1st Player");
            mapScreen.idx = 0;
        }
        if (keycode == Input.Keys.NUM_2) {
            System.out.println("Changed to 2nd Player");
            mapScreen.idx = 1;
        }
        if (keycode == Input.Keys.NUM_3) {
            System.out.println("Changed to 3rd Player");
            mapScreen.idx = 2;
        }
        if (keycode == Input.Keys.NUM_4) {
            System.out.println("Changed to 4th Player");
            mapScreen.idx = 3;
        }
        if (keycode == Input.Keys.NUM_5) {
            System.out.println("Commit Suicide");
            mapScreen.player[mapScreen.idx].health = -1;
        }
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        if (keycode== Input.Keys.UP) {
            solveUp = false;
        }
        if (keycode== Input.Keys.DOWN){
            solveDown = false;
        }
        if (keycode== Input.Keys.LEFT){
            solveLeft = false;
        }
        if (keycode== Input.Keys.RIGHT){
            solveRight = false;
        }
        if (keycode== Input.Keys.S){

        }
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        Vector2 rowcol = mapScreen.click.getRowCol(screenX, Math.abs(mapScreen.mapPixelHeight - screenY));
        List<Vector2> area = new LinkedList<Vector2>();
        area.addAll(mapScreen.board.getOverlay(mapScreen.player[mapScreen.idx].col,
                mapScreen.player[mapScreen.idx].row, mapScreen.player[mapScreen.idx].walk));

        System.out.println("Mouse clicked!");
        float x = Gdx.input.getX();
        float y = Math.abs(mapScreen.mapPixelHeight - Gdx.input.getY());
        Vector2 goal = mapScreen.click.getRowCol(x, y);

        if (!mapScreen.board.map[(int) rowcol.y][(int) rowcol.x].isObstacle() && mapScreen.walker.getRoute() == 0
                && area.contains(rowcol) && statusPhase[6] == 2) {
            if (Gdx.input.isButtonPressed(Input.Buttons.LEFT) && mapScreen.walker.isRouting() == 0) {
                mapScreen.walker.setRouting(1);
//                System.out.println("Mouse clicked!");
//                float x = Gdx.input.getX();
//                float y = Math.abs(mapScreen.mapPixelHeight - Gdx.input.getY());
                mapScreen.path = new LinkedList<Vector2>();
//                Vector2 goal = mapScreen.click.getRowCol(x, y);

                mapScreen.player[mapScreen.idx].setSource(mapScreen.player[mapScreen.idx].col,
                        mapScreen.player[mapScreen.idx].row);

                System.out.println("Column-Row = " + goal.x + "," + goal.y);
                mapScreen.path.addAll(mapScreen.board.getPath(mapScreen.player[mapScreen.idx].getRowCol(), goal));
                mapScreen.walker.setPath(mapScreen.player[mapScreen.idx].getRowCol(), mapScreen.path);
                mapScreen.walker.routing();
            }
        } else if (Gdx.input.isButtonPressed(Input.Buttons.LEFT) && statusPhase[6] == 1 ||
                statusPhase[6] == 3 && mapScreen.player[mapScreen.idx].skillUsing == -1) {
            mapScreen.player[mapScreen.idx].resetElapsedTime();
            mapScreen.player[mapScreen.idx].setStartTime();
            mapScreen.player[mapScreen.idx].skillUsing = chooseSkill;
            mapScreen.player[mapScreen.idx].cardUsing = chooseCard;
            mapScreen.player[mapScreen.idx].attacking = true;
            System.out.println("cardUsing = " + mapScreen.player[mapScreen.idx].cardUsing);
//            System.out.println("Kuy 0/0 left click");
//            int k = 0/0;
        }
        System.out.println("statusPhase = " + statusPhase[6] + " In screenPlay");
        if (statusPhase[6] == 1 || statusPhase[6] == 3) {
            if (Gdx.input.isButtonPressed(Input.Buttons.RIGHT) && mapScreen.player[mapScreen.idx].attacking == false) {
                System.out.println("Skill is used.");
                mapScreen.player[mapScreen.idx].setTarget(goal);
                if (goal.x < mapScreen.player[mapScreen.idx].col) {
                    if (mapScreen.player[mapScreen.idx].facing.compareTo(Hero.State.RIGHT) == 0) {
                        mapScreen.player[mapScreen.idx].facing = Hero.State.LEFT;
                    }
                } else {
                    if (mapScreen.player[mapScreen.idx].facing.compareTo(Hero.State.LEFT) == 0) {
                        mapScreen.player[mapScreen.idx].facing = Hero.State.RIGHT;
                    }
                }
                mapScreen.player[mapScreen.idx].resetElapsedTime();
                mapScreen.player[mapScreen.idx].setStartTime();
                mapScreen.player[mapScreen.idx].skillUsing = chooseSkill;
                mapScreen.player[mapScreen.idx].attacking = true;
//                mapScreen.statusPhase[6] = 2;
            }
        }
        return false;
    }

    @Override
    public boolean touchUp ( int screenX, int screenY, int pointer, int button)
    {
        Gdx.app.log("Mouse", "Up");
        return false;
    }

    @Override
    public boolean touchDragged ( int screenX, int screenY, int pointer)
    {
        Gdx.app.log("Mouse", "Dragged");

        return false;
    }

    @Override
    public boolean mouseMoved ( int screenX, int screenY)
    {
                //Gdx.app.log("Mouse Position", screenX+", "+screenY);
                //rendexX = screenX - texture.getWidth();
                //renderY = Gdx.graphics.getHeight() - screenY - texture.getHeight();
        return false;
    }

    @Override
    public boolean scrolled ( int amount)
    {
                //Gdx.app.log("Mouse", "Scroll :"+amount);
        return false;
    }

    //Phase control
    public void statusInput(){
        statusPhase[0] = 0; //Amount turn
        statusPhase[1] = 1; //character class
        statusPhase[2] = 2; //character class
        statusPhase[3] = 3; //character class
        statusPhase[4] = 4; //character class
        statusPhase[5] = 0; //who's in turn
        statusPhase[6] = 0; //turn 0=draw, 1,3=action, 2=travel, 4=end
        statusPhase[7] = 0; //action start player default=0
        statusPhase[8] = 0; //action attacker default=0
        statusPhase[9] = 0; //action target default=0
        statusPhase[10] = 0; //Travel phase who default= -1
        statusPhase[11] = 0; //Travel phase to col default= -1
        statusPhase[12] = 0; //Travel phase to row default= -1
    }

    public void editStatusPhase(int index, int condition, int value){
        //condition 0:+, 1:-, 2:*, 3:/, 4:=
        if (condition==0) {
            statusPhase[index] += value;
        }
        else if (condition==1) {
            statusPhase[index] -= value;
        }
        else if (condition==2) {
            statusPhase[index] -= value;
        }
        else if (condition==3) {
            statusPhase[index] -= value;
        }
        else if (condition==4){
            statusPhase[index] = value;
        }
        phaseAll();
    }

    public void phaseAll(){
        if (statusPhase[5]==0){
            phaseInTurn();
        }
        else{
            phaseOutTurn();
        }
    }

    public void phaseInTurn(){
        if(statusPhase[6]%5==0){
            drawPhase();
        }
        if(statusPhase[6]%5==1 || statusPhase[6]%5==3){
            actionPhase();
        }
        if(statusPhase[6]%5==2){
            travelPhase();
        }
        if(statusPhase[6]%5==4){
            endPhase();
        }
    }

    public void phaseOutTurn(){
        waitPhase();
        if (statusPhase[6]==1){
            chainPhase();
        }
    }

    public void drawPhase(){
        cardAction.setPopupOff(true);
        if (statusPhase[0]==0){
            Timer.schedule(new Timer.Task() {
                @Override
                public void run() {
                    if(currentCard<5) {
                        setCardHandR(currentCard);
                        randomCard.setCardInHandIndex(currentCard);
                        cardHandAction(0);
                        System.out.println("run time");
                    }
                    else if(currentCard==5){
                        Timer.instance().clear();
                        System.out.println("run time clear");
                    }
                    else {
                        Timer.instance().stop();
                        System.out.println("run time stop");
                    }
                    currentCard++;
                }
            }, 3, 2);
        }
        else{
            if (currentCard<maxCard){
                setCardHandR(currentCard);
                randomCard.setCardInHandIndex(currentCard);
                cardHandAction(0);
                currentCard++;
            }
        }
    }

    public void phaseInTurnWait(){
        //wait your player discussion in chain phase, i can't do anything
    }

    public void actionPhase(){
        cardAction.setPopupOff(false);
    }

    public void travelPhase(){
        cardAction.setPopupOff(true);
    }


    public void endPhase(){
        cardAction.setPopupOff(true);
    }

    public void waitPhase(){
        //can't do anything about other player but game is show you screen real time and check you want to chain phase or not


    }

    public void chainPhase(){
        //show you when other player do something about you

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

    public int getChooseSkill(){
        return chooseSkill;
    }
    public int getChooseCard(){
        return chooseCard;
    }
    public void setChooseSkill(int choose){
        this.chooseSkill = choose;
    }
    public void setChooseCard(int choose){
        this.chooseCard = choose;
    }
}
