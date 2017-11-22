package com.axival.game.MapPlay;

import com.axival.game.CardPlay;
import com.axival.game.StatusAxival;
import com.axival.game.screen.ScreenPlay;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.HexagonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import static java.lang.Math.sqrt;

public class MapScreen implements Screen {
    //status phase variable
    public int[] statusPhase;

    //order variable
    int order;

    private CardPlay game;
    private OrthographicCamera gamecam;
    private Viewport gamePort;

    //map variables
    private Texture map, tile[];

    //Tiled map variables
    private TmxMapLoader mapLoader;
    private TiledMap hexes;
    private HexagonalTiledMapRenderer renderer;
    private MapOverlay overlay;

    // Width and Height from Map Properties variables
    MapProperties prop;
    public int mapWidth;
    public int mapHeight;
    public int tilePixelWidth;
    public int tilePixelHeight;
    public int mapPixelWidth;
    public int mapPixelHeight;

    //font variable
    private BitmapFont font;

    //Coordinates variables
    private Vector3 screenCoordinates;
    private Vector2 des;

    //Hero variables
    public Hero[] player;
    public int idx = 0;

    //Board variables
    public Board board;

    //ScreenPlay variables
    public ScreenPlay screen;

    //Path for walking
    public List<Vector2> path;

    //Heroes Position variables
    private LinkedList<Vector3> heroes;

    //Test Clicking
    public onClick click;

    //Navigator variable
    public Navigator walker;

    int ipctrl = 0;

    public MapScreen(CardPlay game, ScreenPlay screen) {

        this.game = game;
        this.screen = screen;
        //create cam used to follow hero through cam world
        gamecam = new OrthographicCamera();

        //create a FitViewport to maintain virtual aspect ratio despite
        gamePort = new FitViewport(CardPlay.V_WIDTH, CardPlay.V_HEIGHT, gamecam);

        //Load our map and setup our map renderer
        mapLoader = new TmxMapLoader();
        hexes = mapLoader.load("tiled-maps/map.tmx");
        renderer = new HexagonalTiledMapRenderer(hexes);

        // Get Width and Height from Map Properties
        prop = hexes.getProperties();
        mapWidth = prop.get("width", Integer.class);
        mapHeight = prop.get("height", Integer.class);
        tilePixelWidth = prop.get("tilewidth", Integer.class);
        tilePixelHeight = prop.get("tileheight", Integer.class);
        mapPixelWidth = mapWidth * tilePixelWidth;
        mapPixelHeight = mapHeight * tilePixelHeight;


        tile = new Texture[4];
        tile[0] = new Texture("map-imgs/ol red.png");
        tile[1] = new Texture("map-imgs/ol green.png");
        tile[2] = new Texture("map-imgs/ol orange.png");
        tile[3] = new Texture("map-imgs/ol violet.png");

        //create board
        board = new Board(this);

        //create map
        map = new Texture("map-imgs/no-grid-map2.png");
        overlay = new MapOverlay(this, board, game.batch);

        //create onClick
        click = new onClick(this, board);

        //create navigator
        walker = new Navigator(this);

        //create coordinate
        screenCoordinates = new Vector3();
        des = new Vector2(0, 0);

        //create and set font
        font = new BitmapFont();
        font.setColor(255, 255, 255, 1);

        /*
        //create phase status
        statusPhase = new int[9];
        statusPhase[1] = 4;
        statusPhase[2] = 2;
        statusPhase[3] = 3;
        statusPhase[4] = 3;
        statusPhase[5] = 0;

        //set actionPhase
        statusPhase[6] = 1;
        */

        //create hero and set spritesheet
        player = new Hero[4];
        settingHero();
    }

    public void settingHero() {
        int job;
        for (int i = 1; i < 5; i++) {
            if (StatusAxival.statusPhase[i] > 3) {
                order = StatusAxival.statusPhase[i] - 3;
                job = order;
            } else {
                job = StatusAxival.statusPhase[i];
            }
            if (job == 1) {
                player[i - 1] = new Hero(game, this, board, board.getHeroCoordinates(),
                        1,i-1, "hero-imgs/DarkTemplarSpritesheet/DarkTemplarSpritesheet.atlas", game );
            } else if (job == 2) {
                player[i - 1] = new Hero(game, this, board, board.getHeroCoordinates(),
                        2,i-1, "hero-imgs/WizardSpritesheet/WizardSpritesheet.atlas", game);
            } else {
                player[i - 1] = new Hero(game, this, board, board.getHeroCoordinates(),
                        3,i-1, "hero-imgs/PriestSpritesheet/PriestSpritesheet.atlas", game);
            }
            if (i % 2 == 0) {
                player[i - 1].setFacing(Hero.State.LEFT);
            }
            board.map[player[i - 1].row][player[i - 1].col].setObstacle(2);
        }
    }

    public void simulatedInput(float dt) {
        //Right-move control
        if (walker.getRoute() == 1 && player[idx].getWalking() == 0) {
            player[idx].setWalking(1);
            player[idx].setCurrentState(Hero.State.WALKING);
            player[idx].setDes(board.map[player[idx].row][player[idx].col + 1].corX,
                    board.map[player[idx].row][player[idx].col + 1].corY);
        } else if (player[idx].getWalking() == 1 && walker.getRoute() == 1) {
            if (player[idx].getCoordinates().x < player[idx].getDes().x) {
                player[idx].setFacing(Hero.State.RIGHT);
                player[idx].setCurrentState(Hero.State.WALKING);
                player[idx].setCoordinates(player[idx].getCoordinates().x += tilePixelWidth * dt,
                        player[idx].getCoordinates().y);
            } else {
                player[idx].setRowCol(player[idx].row, player[idx].col + 1);
                player[idx].setWalking(0);
                player[idx].setCurrentState(Hero.State.STANDING);
                walker.routing();
            }
        }

        //Left-move control
        if (walker.getRoute() == 2 && player[idx].getWalking() == 0) {
            player[idx].setWalking(2);
            player[idx].setCurrentState(Hero.State.WALKING);
            player[idx].setDes(board.map[player[idx].row][Math.max(0, player[idx].col - 1)].corX,
                    board.map[player[idx].row][Math.max(0, player[idx].col - 1)].corY);
        } else if (player[idx].getWalking() == 2 && walker.getRoute() == 2) {
            if (player[idx].getCoordinates().x > player[idx].getDes().x) {
                player[idx].setFacing(Hero.State.LEFT);
                player[idx].setCurrentState(Hero.State.WALKING);
                player[idx].setCoordinates(player[idx].getCoordinates().x -= tilePixelWidth * dt,
                        player[idx].getCoordinates().y);
            } else {
                player[idx].setRowCol(player[idx].row, Math.max(0, player[idx].col - 1));
                player[idx].setWalking(0);
                player[idx].setCurrentState(Hero.State.STANDING);
                walker.routing();
            }
        }

        //Up-Right combination move
        if (walker.getRoute() == 3 && player[idx].getWalking() == 0) {
            player[idx].setWalking(3);
            player[idx].setCurrentState(Hero.State.WALKING);
            if (player[idx].row % 2 == 0) {
                player[idx].setDes(board.map[Math.max(0, player[idx].row - 1)][player[idx].col + 1].corX,
                        board.map[Math.max(0, player[idx].row - 1)][player[idx].col + 1].corY);
            } else {
                player[idx].setDes(board.map[Math.max(0, player[idx].row - 1)][player[idx].col].corX,
                        board.map[Math.max(0, player[idx].row - 1)][player[idx].col].corY);
            }
        } else if (player[idx].getWalking() == 3 && walker.getRoute() == 3) {
            if (player[idx].getCoordinates().x < player[idx].getDes().x || player[idx].getCoordinates().y < player[idx].getDes().y) {
                player[idx].setFacing(Hero.State.RIGHT);
                player[idx].setCurrentState(Hero.State.WALKING);
                if (player[idx].getCoordinates().x < player[idx].getDes().x) {
                    player[idx].setCoordinates(player[idx].getCoordinates().x += (1 / sqrt(3)) * tilePixelWidth * dt,
                            player[idx].getCoordinates().y);
                }
                if (player[idx].getCoordinates().y < player[idx].getDes().y) {
                    player[idx].setCoordinates(player[idx].getCoordinates().x,
                            player[idx].getCoordinates().y += (sqrt(3) / 2) * tilePixelHeight * dt);
                }
            } else if (player[idx].getCoordinates().x >= player[idx].getDes().x && player[idx].getCoordinates().y >= player[idx].getDes().y) {
                if (player[idx].row % 2 == 0) {
                    player[idx].setRowCol(Math.max(0, player[idx].row - 1), player[idx].col + 1);
                } else {
                    player[idx].setRowCol(Math.max(0, player[idx].row - 1), player[idx].col);
                }
                player[idx].setWalking(0);
                player[idx].setCurrentState(Hero.State.STANDING);
                walker.routing();
            }
        }

        //Down-Right combination move
        if (walker.getRoute() == 4 && player[idx].getWalking() == 0) {
            player[idx].setWalking(4);
            player[idx].setCurrentState(Hero.State.WALKING);
            if (player[idx].row % 2 == 0) {
                player[idx].setDes(board.map[player[idx].row + 1][player[idx].col + 1].corX, board.map[player[idx].row + 1][player[idx].col + 1].corY);
            } else {
                player[idx].setDes(board.map[player[idx].row + 1][player[idx].col].corX, board.map[player[idx].row + 1][player[idx].col].corY);
            }
        } else if (player[idx].getWalking() == 4 && walker.getRoute() == 4) {
            if (player[idx].getCoordinates().x < player[idx].getDes().x || player[idx].getCoordinates().y > player[idx].getDes().y) {
                player[idx].setFacing(Hero.State.RIGHT);
                player[idx].setCurrentState(Hero.State.WALKING);
                if (player[idx].getCoordinates().x < player[idx].getDes().x) {
                    player[idx].setCoordinates(player[idx].getCoordinates().x += (1 / sqrt(3)) * tilePixelWidth * dt,
                            player[idx].getCoordinates().y);
                }
                if (player[idx].getCoordinates().y > player[idx].getDes().y) {
                    player[idx].setCoordinates(player[idx].getCoordinates().x,
                            player[idx].getCoordinates().y -= (sqrt(3) / 2) * tilePixelHeight * dt);
                }
            } else if (player[idx].getCoordinates().x >= player[idx].getDes().x && player[idx].getCoordinates().y <= player[idx].getDes().y) {
                if (player[idx].row % 2 == 0) {
                    player[idx].setRowCol(player[idx].row + 1, player[idx].col + 1);
                } else {
                    player[idx].setRowCol(player[idx].row + 1, player[idx].col);
                }
                player[idx].setWalking(0);
                player[idx].setCurrentState(Hero.State.STANDING);
                walker.routing();
            }
        }

        //Up-Left combination move
        if (walker.getRoute() == 5 && player[idx].getWalking() == 0) {
            player[idx].setWalking(5);
            player[idx].setCurrentState(Hero.State.WALKING);
            if (player[idx].row % 2 == 0) {
                player[idx].setDes(board.map[player[idx].row - 1][player[idx].col].corX, board.map[player[idx].row - 1][player[idx].col].corY);
            } else {
                player[idx].setDes(board.map[player[idx].row - 1][player[idx].col - 1].corX, board.map[player[idx].row - 1][player[idx].col - 1].corY);
            }
        } else if (player[idx].getWalking() == 5 && walker.getRoute() == 5) {
            if (player[idx].getCoordinates().x > player[idx].getDes().x || player[idx].getCoordinates().y < player[idx].getDes().y) {
                player[idx].setFacing(Hero.State.LEFT);
                player[idx].setCurrentState(Hero.State.WALKING);
                if (player[idx].getCoordinates().x > player[idx].getDes().x) {
                    player[idx].setCoordinates(player[idx].getCoordinates().x -= (1 / sqrt(3)) * tilePixelWidth * dt,
                            player[idx].getCoordinates().y);
                }
                if (player[idx].getCoordinates().y < player[idx].getDes().y) {
                    player[idx].setCoordinates(player[idx].getCoordinates().x,
                            player[idx].getCoordinates().y += (sqrt(3) / 2) * tilePixelHeight * dt);
                }
            } else if (player[idx].getCoordinates().x <= player[idx].getDes().x && player[idx].getCoordinates().y >= player[idx].getDes().y) {
                if (player[idx].row % 2 == 0) {
                    player[idx].setRowCol(player[idx].row - 1, player[idx].col);
                } else {
                    player[idx].setRowCol(player[idx].row - 1, player[idx].col - 1);
                }
                player[idx].setWalking(0);
                player[idx].setCurrentState(Hero.State.STANDING);
                walker.routing();
            }
        }

        //Down-Left combination move
        if (walker.getRoute() == 6 && player[idx].getWalking() == 0) {
            player[idx].setWalking(6);
            player[idx].setCurrentState(Hero.State.WALKING);
            if (player[idx].row % 2 == 0) {
                player[idx].setDes(board.map[player[idx].row + 1][player[idx].col].corX, board.map[player[idx].row + 1][player[idx].col].corY);
            } else {
                player[idx].setDes(board.map[player[idx].row + 1][player[idx].col - 1].corX, board.map[player[idx].row + 1][player[idx].col - 1].corY);
            }
        } else if (player[idx].getWalking() == 6 && walker.getRoute() == 6) {
            if (player[idx].getCoordinates().x > player[idx].getDes().x || player[idx].getCoordinates().y > player[idx].getDes().y) {
                player[idx].setFacing(Hero.State.LEFT);
                player[idx].setCurrentState(Hero.State.WALKING);
                if (player[idx].getCoordinates().x > player[idx].getDes().x) {
                    player[idx].setCoordinates(player[idx].getCoordinates().x -= (1 / sqrt(3)) * tilePixelWidth * dt,
                            player[idx].getCoordinates().y);
                }
                if (player[idx].getCoordinates().y > player[idx].getDes().y) {
                    player[idx].setCoordinates(player[idx].getCoordinates().x,
                            player[idx].getCoordinates().y -= (sqrt(3) / 2) * tilePixelHeight * dt);
                }
            } else if (player[idx].getCoordinates().x <= player[idx].getDes().x && player[idx].getCoordinates().y <= player[idx].getDes().y) {
                if (player[idx].row % 2 == 0) {
                    player[idx].setRowCol(player[idx].row + 1, player[idx].col);
                } else {
                    player[idx].setRowCol(player[idx].row + 1, player[idx].col - 1);
                }
                player[idx].setWalking(0);
                player[idx].setCurrentState(Hero.State.STANDING);
                walker.routing();
            }
        }

    }

    public void fontDrawDebugging() {
        //render screen coordinates
        font.draw(game.batch, "Screen Coordinates", 155, 660);
        font.draw(game.batch, (int) screenCoordinates.x + " , "
                + (int) Math.abs(mapPixelHeight - screenCoordinates.y), 190, 635);

        //render hero coordinates
        font.draw(game.batch, "Hero Coordinates (changed)", 400, 660);
        font.draw(game.batch, (int) player[idx].getCoordinates().x + " , " + (int) player[idx].getCoordinates().y, 435, 635);

        //render destination coordinates
        font.draw(game.batch, "Destination Coordinates", 600, 660);
        font.draw(game.batch, (int) player[idx].getDes().x + " , " + (int) player[idx].getDes().y, 635, 635);

        //render walk coordinates
        font.draw(game.batch, "Status", 800, 660);
        font.draw(game.batch, Integer.toString(player[idx].getWalking()), 820, 635);

        //row-col coordinates
        font.draw(game.batch, "Row-Column", 900, 660);
        font.draw(game.batch, player[idx].row + " , " + player[idx].col, 920, 635);

        //render walk coordinates
        font.draw(game.batch, "Routing", 1000, 660);
        font.draw(game.batch, Integer.toString(player[idx].getWalking()), 1000, 635);

    }

    public void renderingHero(int idx) {
        //render hero every action
        int count = 0;
        int num = idx+1;
        if (num > 3) { num = 0;}
        while (count < 4) {
            player[num].useSkill();
            count++;
            num++;
            if (num > 3) {
                num = 0;
            }
        }
    }

    public void updateAllplayer(float delta) {
        //update all players
        player[0].update(delta);
        player[1].update(delta);
        player[2].update(delta);
        player[3].update(delta);
    }

    public void rendererColorOverlay(int idx) {
        //Color Overlay under Hero feet.
        if (player[idx].live == true) {
            game.batch.draw(tile[idx], player[idx].getCoordinates().x + 8, player[idx].getCoordinates().y,
                    tile[idx].getWidth() * 0.95f, tile[idx].getHeight() * 0.95f);
        }
    }

    public void renderAllHeroOverlay() {
        //show each skill overlay when hold the skill
        if ((StatusAxival.statusPhase[6] == 1 || StatusAxival.statusPhase[6] == 3) && screen.getChooseAction() > -1 && screen.getChooseAction() < 9
                && StatusAxival.statusPhase[5] == idx && !player[idx].isHeroPlaying()) {
            int job = player[idx].job;
            int skill = screen.getChooseAction();
            Vector2 heroPos = player[idx].getRowCol();
            LinkedList<Vector2> overlaySkill = board.getSkillOverlay(skill, job, heroPos);
            overlay.showOverlay(overlaySkill);
        }

        //render walking overlay
        if (StatusAxival.statusPhase[6] == 2 && (!player[idx].isHeroPlaying())&& player[idx].live == true) {
            overlay.showOverlay(player[idx].col, player[idx].row, player[idx].walk);
        }
    }

    public void showCombat(int attacker, int target, int skill) {
        float frameDuration = player[attacker].heroAnimation[skill].getAnimationDuration();
        player[target].showPain(frameDuration);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        StatusAxival.statusPhase[5] = idx;

        //update all player
        updateAllplayer(delta);

        //hero walking simulating
        simulatedInput(delta);

        //Clear the game screen with Black
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // get coordinates
        screenCoordinates.set(Gdx.input.getX(), Gdx.input.getY(), 0);

        game.batch.begin();

        //render map
        game.batch.draw(map, 0, 0, CardPlay.V_WIDTH, CardPlay.V_HEIGHT);

        rendererColorOverlay(0);
        rendererColorOverlay(1);
        rendererColorOverlay(2);
        rendererColorOverlay(3);

        //fontDrawDebugging();

        renderAllHeroOverlay();

        renderingHero(idx);

        game.batch.end();
    }

    public LinkedList<Vector3> getHeroesCoordinate() {
        heroes = new LinkedList<Vector3>();
        for (int i = 0; i < 4; i++) {
            heroes.add(new Vector3(i, player[i].col, player[i].row));
        }
        return heroes;
    }



    public LinkedList<Vector2> getAllOverlay() {
        return overlay.avaiableOverlay();
    }

    @Override
    public void resize(int width, int height) {
        gamePort.update(width, height);
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
