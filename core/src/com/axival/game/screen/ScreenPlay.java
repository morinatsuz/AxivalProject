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
import com.badlogic.gdx.math.Vector3;
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
    private int maxCard = 23, currentCard = 0;

    public int cardInHand = 0;
    public float cardCountPosY1;

    private CardAction cardAction;

    private UIplay uIplay;

    private final CardPlay cardPlay;

    private MapScreen mapScreen;

    private long startTime = 0;
    private int countInLoop = 0;

    private StatusAxival statusAxival;

    private int chooseAction = -1;

    public ScreenPlay(final CardPlay cardPlay) {
        //set main render object and Input
        this.cardPlay = cardPlay;
        this.stage = new Stage(new StretchViewport(CardPlay.V_WIDTH, CardPlay.V_HEIGHT, cardPlay.camera));
        this.cardCountPosY1 = 0;
        InputMultiplexer inputMultiplexer = new InputMultiplexer(stage, this);
        Gdx.input.setInputProcessor(inputMultiplexer);

        //cursor change
        Pixmap pm = new Pixmap(Gdx.files.internal("cursorImage2.png"));
        Gdx.graphics.setCursor(Gdx.graphics.newCursor(pm, pm.getWidth() / 2, pm.getHeight() / 2));
        pm.dispose();

        //effect define
        prototype = new ParticleEffect();
        prototype = cardPlay.assetManager.get("effect01.party");
        prototype.getEmitters().first().setPosition(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2);
        prototype.start();

        //set parameter other class
        this.randomCard = new RandomCard(cardPlay);
        this.cardDeck = randomCard.allCardDeck(maxCard);
        this.cardAction = new CardAction(this, cardPlay);
        this.uIplay = new UIplay(this.cardPlay, this);
        this.mapScreen = new MapScreen(this.cardPlay, this);
        //this.statusAxival = new StatusAxival(this, mapScreen);

        //check phase
        phaseAll();

        //check memory
        cardPlay.javaFreeMem();

        //play bgm 2
        cardPlay.soundManager.stopBgm(1);
        cardPlay.soundManager.playBgm(2);

        //statusPhase[0] = 0;
        System.out.println("statusPhase" + Arrays.toString(StatusAxival.statusPhase));
    }

    @Override
    public void show() {
        System.out.println("screen");
    }

    public void setCardHandR(int indexCard) {
        stage.addActor(cardDeck[indexCard]);
    }

    public void cardHandAction(int delay) {
        System.out.println("Size Count Hand : " + randomCard.sizeCountCardInHand());
        if (randomCard.sizeCountCardInHand() == 1) {
            cardAction.cardHandActionFirst(currentCard, delay);
        } else if (randomCard.sizeCountCardInHand() == 2) {
            cardAction.cardHandActionSecond(currentCard, delay);
        } else if (randomCard.sizeCountCardInHand() == 3) {
            cardAction.cardHandActionThirst(currentCard, delay);
        } else if (randomCard.sizeCountCardInHand() == 4) {
            cardAction.cardHandActionFourth(currentCard, delay);
        } else if (randomCard.sizeCountCardInHand() == 5) {
            cardAction.cardHandActionFifth(currentCard, delay);
        } else if (randomCard.sizeCountCardInHand() == 6) {
            cardAction.cardHandActionFull(currentCard, delay);
            System.out.println("Out Hand");
        }
    }


    @Override
    public void render(float delta) {
        if (solveUp) {
            /*
            cardHandR.getChildren().get(0).addAction(Actions.parallel(Actions.moveTo(200, 0, 5),
                    Actions.rotateBy(90, 5)));*/
            //cardHandAction();
        }
        if (solveLeft) {
            //cardHandR.getChildren().get(0).addAction(Actions.sequence(Actions.moveTo(1200, 500)));
        }
        Gdx.gl.glClearColor(.25f, .25f, .25f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        update(delta);
        cardPlay.batch.setProjectionMatrix(cardPlay.camera.combined);
        mapScreen.render(delta);
        cardPlay.batch.begin();
        uIplay.runningDraw();
        prototype.draw(cardPlay.batch);
        cardPlay.batch.end();
        stage.draw();
        if (prototype.isComplete()) {
            prototype.reset();
        }
    }

    public void update(float delta) {
//        mapScreen.update(delta);
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
        if (keycode == Input.Keys.UP) {
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
            chooseAction = 0;
        }
        if (keycode == Input.Keys.W) {
            System.out.println("1st Skill is Activated");
            chooseAction = 1;
        }
        if (keycode == Input.Keys.E) {
            System.out.println("2nd Skill is Activated");
            chooseAction = 2;
        }
        if (keycode == Input.Keys.R) {
            System.out.println("3rd Skill is Activated");
            chooseAction = 3;
        }
        if (keycode == Input.Keys.Z) {
            System.out.println("Potion is Activated");
            chooseAction = 4;
        }
        if (keycode == Input.Keys.X) {
            System.out.println("Mega Potion is Activated");
            chooseAction = 5;
        }
        if (keycode == Input.Keys.C) {
            System.out.println("Shield is Activated");
            chooseAction = 6;
        }
        if (keycode == Input.Keys.V) {
            System.out.println("Super Armor is Activated");
            chooseAction = 7;
        }
        if (keycode == Input.Keys.B) {
            System.out.println("Thunder Bolt is Activated");
            chooseAction = 8;
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
        if (keycode == Input.Keys.TAB) {
            System.out.println("Change to next phase");
            if (StatusAxival.statusPhase[6] == 1) {
                StatusAxival.statusPhase[6] = 2;
            } else {
                StatusAxival.statusPhase[6] = 1;
            }

        }
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        if (keycode == Input.Keys.UP) {
            solveUp = false;
        }
        if (keycode == Input.Keys.DOWN) {
            solveDown = false;
        }
        if (keycode == Input.Keys.LEFT) {
            solveLeft = false;
        }
        if (keycode == Input.Keys.RIGHT) {
            solveRight = false;
        }
        if (keycode == Input.Keys.S) {

        }
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        //Row Column of clicked block
        Vector2 rowcol = mapScreen.click.getRowCol(screenX, Math.abs(mapScreen.mapPixelHeight - screenY));

        List<Vector2> area = new LinkedList<Vector2>();

        //List of Block that hero can reach.
        area.addAll(mapScreen.board.getOverlay(mapScreen.player[mapScreen.idx].col,
                mapScreen.player[mapScreen.idx].row, mapScreen.player[mapScreen.idx].walk));

        float x = Gdx.input.getX();
        float y = Math.abs(mapScreen.mapPixelHeight - Gdx.input.getY());
//        float x = screenX;
//        float y = Math.abs(mapScreen.mapPixelHeight - screenY);

        //Block that user clicked in Row Column
        Vector2 goal = mapScreen.click.getRowCol(x, y);

        //Set the Block Target that user clicked into Hero
        mapScreen.player[mapScreen.idx].setTarget(new Vector2(x, y));

        //prepare the variables for walking
        if (StatusAxival.statusPhase[6] == 2 && !mapScreen.board.map[(int) rowcol.y][(int) rowcol.x].isObstacle() &&
                mapScreen.walker.getRoute() == 0 && area.contains(rowcol)) {
            if (Gdx.input.isButtonPressed(Input.Buttons.LEFT) && mapScreen.walker.isRouting() == 0) {
                mapScreen.walker.setRouting(1);
                mapScreen.path = new LinkedList<Vector2>();
                mapScreen.player[mapScreen.idx].setSource(mapScreen.player[mapScreen.idx].col, mapScreen.player[mapScreen.idx].row);
                System.out.println("Column-Row = " + goal.x + "," + goal.y);
                mapScreen.path.addAll(mapScreen.board.getPath(mapScreen.player[mapScreen.idx].getRowCol(), goal));
                mapScreen.walker.setPath(mapScreen.player[mapScreen.idx].getRowCol(), mapScreen.path);
                mapScreen.walker.routing();
            }

        }
        System.out.println("statusPhase = " + StatusAxival.statusPhase[6] + " In ScreenPlay");
        Vector2 vec = mapScreen.player[mapScreen.idx].getRowCol();
        float corX = vec.x;
        float corY = vec.y;
        if (StatusAxival.statusPhase[6] == 1 || StatusAxival.statusPhase[6] == 3) {
            LinkedList<Vector2> skillOverlay = mapScreen.board.getSkillOverlay(chooseAction, mapScreen.player[mapScreen.idx].job, vec);
            LinkedList<Vector3> heroCoordinates = mapScreen.getHeroesCoordinate();
            LinkedList<Vector2> heroCoordinates2 = new LinkedList<Vector2>();
            LinkedList<Vector2> enemey = new LinkedList<Vector2>();
            for (Vector3 vec3 : heroCoordinates) {
                heroCoordinates2.add(new Vector2(vec3.y, vec3.z));
                System.out.println("Hero Cooradiante = " + vec3);
                if (mapScreen.idx != vec3.x && (mapScreen.idx % 2 == 0) != (vec3.x % 2 == 0)) {
                    System.out.println("Enemy = " + vec3);
                    enemey.add(new Vector2(vec3.y, vec3.z));
                }
            }
            Hero player = mapScreen.player[mapScreen.idx];
            boolean onlyYou = player.getRowCol().equals(goal);
            boolean offend = enemey.contains(goal);
            boolean inArea = skillOverlay.contains(goal);
            boolean allHero = heroCoordinates2.contains(goal);
            boolean beAlly = allHero && !offend;
            int job = mapScreen.player[mapScreen.idx].job;


            if (Gdx.input.isButtonPressed(Input.Buttons.LEFT) && !player.isHeroPlaying() && player.actionUsing == -1) {
//                if (chooseAction > -1 && chooseAction < 4) { // p2
//                    //Use with all heroes except him/herself
//                    if (chooseAction == 3 && job == 3)
//                    {
//                        if (allHero && !onlyYou) {
//                            //handle health change calculate
//                            heroFaceToTheRightSide(goal);
//                            playCardSkill(false);
//                        }
//                    }//Use with ally only d1 w2 p1 p2 |  c0 c1 c2
//                    else if ((chooseAction == 1 && job ==1) || (chooseAction == 2 && job == 2)
//                            || (chooseAction == 1 && job == 3) || (chooseAction == 2 && job == 3))
//                    {
//                        if (chooseAction == 1 && job ==1 && allHero && !offend) {
//                            heroFaceToTheRightSide(goal);
//                            playCardSkill(false);
//                        }
//                        else if (chooseAction == 2 && job == 2 && onlyYou) {
//                            heroFaceToTheRightSide(goal);
//                            playCardSkill(false);
//                        }
//                        else if (chooseAction == 1 && job == 3 && allHero && !offend) {
//                            heroFaceToTheRightSide(goal);
//                            playCardSkill(false);
//                        }
//                        else if (chooseAction == 2 && job == 3 && onlyYou) {
//                            heroFaceToTheRightSide(goal);
//                            playCardSkill(false);
//                        }
//                    }//Use with enemies only
//                    else
//                    {
//                        if (offend) {
//                            if (chooseAction == 3 && job == 2) {
//                                heroFaceToTheRightSide(goal);
//                                playCardSkill(false);
//                            }
//                            else {
//                                heroFaceToTheRightSide(goal);
//                                playCardSkill(false);
//                            }
//
//                        }
//                    }
//                }
//                else if (chooseAction > 3 && chooseAction < 9)
//                {
//                    if (chooseAction == 4 && !offend && allHero) {
//                        playCardSkill(true);
//                    }
//                    else if (chooseAction == 5 && !offend && allHero) {
//                        heroFaceToTheRightSide(goal);
//                        playCardSkill(true);
//                    }
//                    else if (chooseAction == 6 && !offend && allHero) {
//                        heroFaceToTheRightSide(goal);
//                        playCardSkill(true);
//                    }
//                    else if (chooseAction == 7 && onlyYou) {
//                        heroFaceToTheRightSide(goal);
//                        playCardSkill(true);
//                    }
//                    else if (chooseAction == 8 && offend) {
//                        heroFaceToTheRightSide(goal);
//                        playCardSkill(true);
//                    }
//                }
                if (-1 < chooseAction && chooseAction < 4) {
                    if (job == 1) {
                        if (chooseAction == 0 && offend) {
                            heroFaceToTheRightSide(goal);
                            playCardSkill(false);
                            StatusAxival.statusPlayer[this.getIndexOfTarget(heroCoordinates, goal)][0] -= 4;
                        } else if (chooseAction == 1 && allHero && !offend) {
                            heroFaceToTheRightSide(goal);
                            playCardSkill(false);
                        } else if (chooseAction == 2 && offend) {
                            heroFaceToTheRightSide(goal);
                            playCardSkill(false);
                        } else if (chooseAction == 3 && offend) {
                            heroFaceToTheRightSide(goal);
                            playCardSkill(false);
                        }
                    } else if (job == 2) {
                        if (chooseAction == 0 && offend) {
                            heroFaceToTheRightSide(goal);
                            playCardSkill(false);
                        } else if (chooseAction == 1 && offend) {
                            heroFaceToTheRightSide(goal);
                            playCardSkill(false);
                        } else if (chooseAction == 2 && onlyYou) {
                            heroFaceToTheRightSide(goal);
                            playCardSkill(false);
                        } else if (chooseAction == 3 && offend) {
                            heroFaceToTheRightSide(goal);
                            playCardSkill(false);
                        }
                    } else if (job == 3) {
                        if (chooseAction == 0 == offend) {
                            heroFaceToTheRightSide(goal);
                            playCardSkill(false);
                        } else if (chooseAction == 1 && allHero && !offend) {
                            heroFaceToTheRightSide(goal);
                            playCardSkill(false);
                        } else if (chooseAction == 2 && onlyYou) {
                            heroFaceToTheRightSide(goal);
                            playCardSkill(false);
                        } else if (chooseAction == 3 && allHero && !onlyYou) {
                            heroFaceToTheRightSide(goal);
                            playCardSkill(false);
                        }
                    }
                } else if (3 < chooseAction && chooseAction < 9) {
                    if (chooseAction == 4 && !offend && allHero) {
                        heroFaceToTheRightSide(goal);
                        playCardSkill(true);
                    } else if (chooseAction == 5 && !offend && allHero) {
                        heroFaceToTheRightSide(goal);
                        playCardSkill(true);
                    } else if (chooseAction == 6 && !offend && allHero) {
                        heroFaceToTheRightSide(goal);
                        playCardSkill(true);
                    } else if (chooseAction == 7 && onlyYou) {
                        heroFaceToTheRightSide(goal);
                        playCardSkill(true);
                    } else if (chooseAction == 8 && offend) {
                        heroFaceToTheRightSide(goal);
                        playCardSkill(true);
                    }
                }

            }
        }
        return false;
    }

    //play animation
    private void playCardSkill(boolean bool) {
        mapScreen.player[mapScreen.idx].resetElapsedTime();
        mapScreen.player[mapScreen.idx].setStartTime();
        mapScreen.player[mapScreen.idx].actionUsing = chooseAction;
        if (bool) {
            //PLay Card Animation
            mapScreen.player[mapScreen.idx].setCarding(true);
        } else {
            //Play Hero Skill Animation
            mapScreen.player[mapScreen.idx].setAttacking(true);
        }
    }


    public void heroFaceToTheRightSide(Vector2 goal) {
        //Turn hero to right side that iser clicked
        if (goal.x < mapScreen.player[mapScreen.idx].col) {
            if (mapScreen.player[mapScreen.idx].facing.compareTo(Hero.State.RIGHT) == 0) {
                mapScreen.player[mapScreen.idx].facing = Hero.State.LEFT;
            }
        } else {
            if (mapScreen.player[mapScreen.idx].facing.compareTo(Hero.State.LEFT) == 0) {
                mapScreen.player[mapScreen.idx].facing = Hero.State.RIGHT;
            }
        }
    }

    public int getIndexOfTarget(LinkedList<Vector3> heroes, Vector2 goal) {
        for (Vector3 vec : heroes) {
            if (new Vector2(vec.y, vec.z).equals(goal)) {
                return (int) vec.x;
            }
        }
        return 0;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        Gdx.app.log("Mouse", "Up");
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        Gdx.app.log("Mouse", "Dragged");

        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        //Gdx.app.log("Mouse Position", screenX+", "+screenY);
        //rendexX = screenX - texture.getWidth();
        //renderY = Gdx.graphics.getHeight() - screenY - texture.getHeight();
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        //Gdx.app.log("Mouse", "Scroll :"+amount);
        return false;
    }

    public void phaseAll() {
        if (StatusAxival.statusPhase[5] == 0) {
            phaseInTurn();
        } else {
            phaseOutTurn();
        }
    }

    public void phaseInTurn() {
        if (StatusAxival.statusPhase[6] == 0) {
            System.out.println("draw phase");
            drawPhase();
        }
        else if (StatusAxival.statusPhase[6]== 1 || StatusAxival.statusPhase[6] == 3) {
            System.out.println("action phase");
            actionPhase();
        }
        else if (StatusAxival.statusPhase[6]== 2) {
            System.out.println("travel phase");
            travelPhase();
        }
        else if (StatusAxival.statusPhase[6] == 4) {
            System.out.println("end phase");
            endPhase();
        }
        else if (StatusAxival.statusPhase[6]>4) {
            StatusAxival.statusPhase[6] = 0;
            System.out.println("draw phase");
            drawPhase();
        }
    }

    public void phaseOutTurn() {
        waitPhase();
        if (StatusAxival.statusPhase[8] == 1) {
            chainPhase();
        }
    }

    public void drawPhase() {
        cardAction.setPopupOff(true);
        if (StatusAxival.statusPhase[0] == 0) {
            Timer.schedule(new Timer.Task() {
                @Override
                public void run() {
                    if (currentCard < 5) {
                        setCardHandR(currentCard);
                        randomCard.setCardInHandIndex(currentCard);
                        cardHandAction(0);
                        System.out.println("run time");
                    } else if (currentCard == 5) {
                        Timer.instance().clear();
                        System.out.println("run time clear");
                    } else {
                        Timer.instance().stop();
                        System.out.println("run time stop");
                    }
                    currentCard++;
                }
            }, 1, 1);
        } else {
            if (currentCard < maxCard) {
                setCardHandR(currentCard);
                randomCard.setCardInHandIndex(currentCard);
                cardHandAction(0);
                currentCard++;
            }
        }
    }

    public void phaseInTurnWait() {
        //wait your player discussion in chain phase, i can't do anything
    }

    public void actionPhase() {
        cardAction.setPopupOff(false);
    }

    public void travelPhase() {
        cardAction.setPopupOff(true);
    }


    public void endPhase() {
        cardAction.setPopupOff(true);
    }

    public void waitPhase() {
        //can't do anything about other player but game is show you screen real time and check you want to chain phase or not


    }

    public void chainPhase() {
        //show you when other player do something about you

    }

    public static Texture getTexture() {

        Pixmap pixmap;
        try {
            pixmap = new Pixmap(1, 1, Pixmap.Format.RGBA8888);
        } catch (GdxRuntimeException e) {
            pixmap = new Pixmap(1, 1, Pixmap.Format.RGB565);
        }
        pixmap.setColor(Color.WHITE);
        pixmap.drawRectangle(0, 0, 1, 1);

        return new Texture(pixmap);
    }

    public int getChooseAction() {
        System.out.println(chooseAction);
        return chooseAction;
    }
    public void setChooseAction(int choose){
        this.chooseAction = choose;
    }
}
