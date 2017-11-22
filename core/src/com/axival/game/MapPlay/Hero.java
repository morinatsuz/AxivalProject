package com.axival.game.MapPlay;

import com.axival.game.CardPlay;
import com.axival.game.StatusAxival;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

import java.util.LinkedList;

public class Hero extends TextureAtlas {
    public enum State {STANDING, WALKING, LEFT, RIGHT, PAIN, DEAD}

    public int job;
    public int col;
    public int row;
    public String action;
    public State facing;
    public State currentState;
    public State previousState;
    public int walk = 4;
    private boolean attacking = false;
    private boolean carding = false;
    public boolean pain = false;
    public float attackedTime = 0;
    public int actionUsing = -1;
    public int skillUsed = 0;
    public Skill[] ability;
    private Texture img;
    private TextureAtlas atlas;
    public Animation<TextureRegion> heroAnimation[];
    private Vector2 coordinates;
    private Vector2 target;
    private Vector2 targetCo;
    private Vector2 des;
    private Vector2 src;
    private LinkedList<Vector2> enemys;
    private float frameDuration;
    private float deltaTime;
    private float elapsedTime = 0f;
    private float startTime = 0f;
    private float erXR, erYR;
    private float erXL, erYL;
    private float[] errX;
    private float[] errY;
    private static int walking = 0;
    private CardPlay game;
    private MapScreen screen;
    private Board board;
    private int index;
    public boolean live = true;
    public int health;


    public Hero(CardPlay game, MapScreen screen, Board board, Vector2 vector, int job, int index, String path) {
        this.job = job;
        this.index = index;
        this.setAtlas(path);
        this.target = new Vector2(10, 10);
        this.targetCo = new Vector2(10, 10);
        this.enemys = new LinkedList<Vector2>();
        ability = new Skill[9];
        heroAnimation = new Animation[9];
        frameDuration = 0.3f;
        if (job == 1) {
            health = 35;
            StatusAxival.statusPlayer[index][0] = health;
            ability[0] = new Skill("skills/DT_Skill_Spritesheet/DT_Skill0_Spritesheet/dt_skill0.atlas",
                    "dt0", 1, true, screen);
            ability[1] = new Skill("skills/DT_Skill_Spritesheet/DT_Skill1_Spritesheet/dt_skill1.atlas",
                    "dt1", 1, true, screen);
            ability[2] = new Skill("skills/DT_Skill_Spritesheet/DT_Skill2_Spritesheet/dt_skill2.atlas",
                    "dt2", 1, false, screen);
            ability[3] = new Skill("skills/DT_Skill_Spritesheet/DT_Skill3_Spritesheet/dt_skill3.atlas",
                    "dt3", 1, false, screen);
            heroAnimation[0] = new Animation<TextureRegion>(0.2f, this.atlas.findRegions("swingOF"));
            heroAnimation[1] = new Animation<TextureRegion>(0.3f, this.atlas.findRegions("fortify"));
            heroAnimation[2] = new Animation<TextureRegion>(0.25f, this.atlas.findRegions("stabOF"));
            heroAnimation[3] = new Animation<TextureRegion>(0.3f, this.atlas.findRegions("swingO1"));
        } else if (job == 2) {
            health = 30;
            StatusAxival.statusPlayer[index][0] = health;
            ability[0] = new Skill("skills/WZ_Skill_Spritesheet/WZ_Skill0_Spritesheet/wz_skill0.atlas",
                    "wz0", 2, true, screen);
            ability[1] = new Skill("skills/WZ_Skill_Spritesheet/WZ_Skill1_Spritesheet/wz_skill1.atlas",
                    "wz1", 2, true, screen);
            ability[2] = new Skill("skills/WZ_Skill_Spritesheet/WZ_Skill2_Spritesheet/wz_skill2.atlas",
                    "wz2", 2, false, screen);
            ability[3] = new Skill("skills/WZ_Skill_Spritesheet/WZ_Skill3_Spritesheet/wz_skill3.atlas",
                    "wz3", 2, true, screen);
            heroAnimation[0] = new Animation<TextureRegion>(frameDuration, this.atlas.findRegions("swingO1"));
            heroAnimation[1] = new Animation<TextureRegion>(frameDuration, this.atlas.findRegions("swingO2"));
            heroAnimation[2] = new Animation<TextureRegion>(frameDuration, this.atlas.findRegions("stand1"));
            heroAnimation[3] = new Animation<TextureRegion>(0.3f, this.atlas.findRegions("swingO3"));
        } else {
            health = 25;
            StatusAxival.statusPlayer[index][0] = health;
            ability[0] = new Skill("skills/PR_Skill_Spritesheet/PR_Skill0_Spritesheet/pr_skill0.atlas",
                    "pr0", 3, true, screen);
            ability[1] = new Skill("skills/PR_Skill_Spritesheet/PR_Skill1_Spritesheet/pr_skill1.atlas",
                    "pr1", 3, true, screen);
            ability[2] = new Skill("skills/PR_Skill_Spritesheet/PR_Skill2_Spritesheet/pr_skill2.atlas",
                    "pr2", 3, false, screen);
            ability[3] = new Skill("skills/PR_Skill_Spritesheet/PR_Skill3_Spritesheet/pr_skill3.atlas",
                    "pr3", 3, true, screen);
            heroAnimation[0] = new Animation<TextureRegion>(0.2f, this.atlas.findRegions("swingO1"));
            heroAnimation[1] = new Animation<TextureRegion>(frameDuration, this.atlas.findRegions("heal"));
            heroAnimation[2] = new Animation<TextureRegion>(frameDuration, this.atlas.findRegions("swingO2"));
            heroAnimation[3] = new Animation<TextureRegion>(0.5f, this.atlas.findRegions("stabO1"));
        }
        ability[4] = new Skill("skills/CD_Skill_Spritesheet/CD_Skill0_Spritesheet/cd_skill0.atlas",
                "cd0", 0, true, screen);
        ability[5] = new Skill("skills/CD_Skill_Spritesheet/CD_Skill1_Spritesheet/cd_skill1.atlas",
                "cd1", 0, true, screen);
        ability[6] = new Skill("skills/CD_Skill_Spritesheet/CD_Skill2_Spritesheet/cd_skill2.atlas",
                "cd2", 0, true, screen);
        ability[7] = new Skill("skills/CD_Skill_Spritesheet/CD_Skill4_Spritesheet/cd_skill4.atlas",
                "cd4", 0, true, screen);
        ability[8] = new Skill("skills/CD_Skill_Spritesheet/CD_Skill3_Spritesheet/cd_skill3.atlas",
                "cd3", 0, true, screen);
        heroAnimation[4] = new Animation<TextureRegion>(1 / 2f, this.atlas.findRegions("alert"));
        heroAnimation[5] = new Animation<TextureRegion>(1 / 2f, this.atlas.findRegions("pain"));
        heroAnimation[6] = new Animation<TextureRegion>(0.6f, this.atlas.findRegions("dead"));
        this.game = game;
        this.screen = screen;
        this.board = board;
        this.row = (int) vector.y;
        this.col = (int) vector.x;
        this.src = new Vector2(col, row);
        this.des = new Vector2();
        this.coordinates = new Vector2();
        this.coordinates.set(board.map[row][col].corX, board.map[row][col].corY);
        this.des.set(screen.board.map[row][col].corX, screen.board.map[row][col].corY);
        facing = State.RIGHT;
        currentState = State.STANDING;
        previousState = State.STANDING;
    }

    public void update(float delta) {
        if (live == true) {
            health = StatusAxival.statusPlayer[index][0];
        } else {
            StatusAxival.statusPlayer[index][0] = 0;
        }
        if (health <= 0 && live == true) {
            health = -1;
            live = false;
            this.resetElapsedTime();
            this.setStartTime();
        }
        if (elapsedTime > 100) {
            elapsedTime = 0;
        }
        this.elapsedTime += delta;
        for (Skill skill : ability) {
            skill.update(delta);
        }
    }

    public void setImg(String path) {
        this.img = new Texture(path);
    }

    public void setWalking(int n) {
        this.walking = n;
    }

    public int getWalking() {
        return walking;
    }

    public void setCoordinates(float x, float y) {
        this.coordinates = coordinates.set(x, y);
    }

    public void setDes(float x, float y) {
        this.des.set(x, y);
    }

    public void setRowCol(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public void setSrc() {
        this.src = new Vector2(col, row);
    }

    public Vector2 getSrc() {
        return this.src;
    }

    public Vector2 getCoordinates() {
        return this.coordinates;
    }

    public Vector2 getDes() {
        return this.des;
    }

    public Vector2 getRowCol() {
        return new Vector2(this.col, this.row);
    }

    public void setAtlas(String path) {
        this.atlas = new TextureAtlas(path);
    }

    public Animation<TextureRegion> walkAction() {
        action = "stand1";
        float frameDuration = 1 / 3f;
        if (this.currentState.compareTo(State.WALKING) == 0) {
            action = "walk1";
        }
        return new Animation<TextureRegion>(frameDuration, this.atlas.findRegions(action));
    }

    public void renderWalking() {
        errX = new float[2];
        errY = new float[2];
        errX[0] = -120f;
        errY[0] = -50f;
        errX[1] = -120f;
        errY[1] = -50f;
        //Hero Action
        if (facing.compareTo(State.RIGHT) == 0) {
            game.batch.draw(this.walkAction().getKeyFrame(elapsedTime, true),
                    this.getCoordinates().x + (this.walkAction().getKeyFrame(elapsedTime, true).getRegionWidth())
                            + errX[1],
                    this.getCoordinates().y + errY[1],
                    -(this.walkAction().getKeyFrame(elapsedTime, true).getRegionWidth()),
                    this.walkAction().getKeyFrame(elapsedTime, true).getRegionHeight());
        } else {
            game.batch.draw(this.walkAction().getKeyFrame(elapsedTime, true),
                    this.getCoordinates().x + errX[0],
                    this.getCoordinates().y + errY[0]);
        }
    }

    public void useSkill() {
        //Increase time of Wizard 3rd Skill
        float multiplier;
        if (job == 2 && actionUsing == 3) {
            multiplier = 3.5f;
        } else {
            multiplier = 1;
        }

        //Playing Hero Skill Animation
        if (actionUsing > -1 && actionUsing < 4 && elapsedTime < startTime +
                heroAnimation[actionUsing].getAnimationDuration() * multiplier
                && attacking == true && carding == false && pain == false && live == true) {
            //Handle Error for each Skill Animation
            if (ability[actionUsing].target == true) {
                erXR = targetCo.x + ability[actionUsing].erRht[0];
                erYR = targetCo.y + ability[actionUsing].erRht[1];
                erXL = targetCo.x + ability[actionUsing].erLft[0];
                erYL = targetCo.y + ability[actionUsing].erLft[1];

            } else {
                erXR = coordinates.x + ability[actionUsing].erRht[0];
                erYR = coordinates.y + ability[actionUsing].erRht[1];
                erXL = coordinates.x + ability[actionUsing].erLft[0];
                erYL = coordinates.y + ability[actionUsing].erLft[1];
            }
            deltaTime = heroAnimation[actionUsing].getAnimationDuration();
            //Right Acting
            if (facing.compareTo(State.RIGHT) == 0) {
                //Hero Animation
                game.batch.draw(heroAnimation[actionUsing].getKeyFrame(elapsedTime, true),
                        coordinates.x + 180f,
                        coordinates.y - 50f,
                        -(heroAnimation[actionUsing].getKeyFrame(elapsedTime, true).getRegionWidth()),
                        heroAnimation[actionUsing].getKeyFrame(elapsedTime, true).getRegionHeight());
                //Wizard Skill Animation
                if (job == 2) { //18,2 18,6 18,10
                    if (actionUsing == 3) {
                        game.batch.draw(ability[actionUsing].getSkillAction(deltaTime).getKeyFrame(elapsedTime, true),
                                board.map[2][23].corX + 700 - elapsedTime * (600),
                                board.map[2][23].corY - 50,
                                -(ability[actionUsing].getSkillAction(deltaTime).getKeyFrame(elapsedTime, true).getRegionWidth()),
                                ability[actionUsing].getSkillAction(deltaTime).getKeyFrame(elapsedTime, true).getRegionHeight());
                        game.batch.draw(ability[actionUsing].getSkillAction(deltaTime).getKeyFrame(elapsedTime, true),
                                board.map[7][23].corX + 700 - elapsedTime * (600),
                                board.map[7][23].corY - 50,
                                -(ability[actionUsing].getSkillAction(deltaTime).getKeyFrame(elapsedTime, true).getRegionWidth()),
                                ability[actionUsing].getSkillAction(deltaTime).getKeyFrame(elapsedTime, true).getRegionHeight());
                        game.batch.draw(ability[actionUsing].getSkillAction(deltaTime).getKeyFrame(elapsedTime, true),
                                board.map[11][23].corX + 700 - elapsedTime * (600),
                                board.map[11][23].corY - 50,
                                -(ability[actionUsing].getSkillAction(deltaTime).getKeyFrame(elapsedTime, true).getRegionWidth()),
                                ability[actionUsing].getSkillAction(deltaTime).getKeyFrame(elapsedTime, true).getRegionHeight());
                    } else if (actionUsing == 1) {
                        if (enemys.size() == 2) {
                            game.batch.draw(ability[actionUsing].getSkillAction(deltaTime).getKeyFrame(elapsedTime, true),
                                    board.map[(int)enemys.get(0).y][(int)enemys.get(0).x].corX + ability[actionUsing].erRht[0],
                                    board.map[(int)enemys.get(0).y][(int)enemys.get(0).x].corY + ability[actionUsing].erRht[1],
                                    -(ability[actionUsing].getSkillAction(deltaTime).getKeyFrame(elapsedTime, true).getRegionWidth()),
                                    ability[actionUsing].getSkillAction(deltaTime).getKeyFrame(elapsedTime, true).getRegionHeight());
                            game.batch.draw(ability[actionUsing].getSkillAction(deltaTime).getKeyFrame(elapsedTime, true),
                                    board.map[(int)enemys.get(1).y][(int)enemys.get(1).x].corX + ability[actionUsing].erRht[0],
                                    board.map[(int)enemys.get(1).y][(int)enemys.get(1).x].corY + ability[actionUsing].erRht[1],
                                    -(ability[actionUsing].getSkillAction(deltaTime).getKeyFrame(elapsedTime, true).getRegionWidth()),
                                    ability[actionUsing].getSkillAction(deltaTime).getKeyFrame(elapsedTime, true).getRegionHeight());
                        }
                        else if (enemys.size() == 1) {
                            game.batch.draw(ability[actionUsing].getSkillAction(deltaTime).getKeyFrame(elapsedTime, true),
                                    board.map[(int)enemys.get(0).y][(int)enemys.get(0).x].corX + ability[actionUsing].erRht[0],
                                    board.map[(int)enemys.get(0).y][(int)enemys.get(0).x].corY + ability[actionUsing].erRht[1],
                                    -(ability[actionUsing].getSkillAction(deltaTime).getKeyFrame(elapsedTime, true).getRegionWidth()),
                                    ability[actionUsing].getSkillAction(deltaTime).getKeyFrame(elapsedTime, true).getRegionHeight());
                        }
                    }
                } else {
                    game.batch.draw(ability[actionUsing].getSkillAction(deltaTime).getKeyFrame(elapsedTime, true),
                            erXR,
                            erYR,
                            -(ability[actionUsing].getSkillAction(deltaTime).getKeyFrame(elapsedTime, true).getRegionWidth()),
                            ability[actionUsing].getSkillAction(deltaTime).getKeyFrame(elapsedTime, true).getRegionHeight());
                }

            }
            //Left Acting
            else {
                //Hero Animation
                game.batch.draw(heroAnimation[actionUsing].getKeyFrame(elapsedTime, true),
                        coordinates.x - 120f,
                        coordinates.y - 50f);
                if (job == 2) {
                    if (actionUsing == 3) {
                        //Skill Animation
                        game.batch.draw(ability[actionUsing].getSkillAction(deltaTime).getKeyFrame(elapsedTime, true),
                                board.map[2][0].corX - 700 + elapsedTime * (600),
                                board.map[2][0].corY - 50);
                        //Skill Animation
                        game.batch.draw(ability[actionUsing].getSkillAction(deltaTime).getKeyFrame(elapsedTime, true),
                                board.map[7][0].corX - 700 + elapsedTime * (600),
                                board.map[7][0].corY - 50);
                        //Skill Animation
                        game.batch.draw(ability[actionUsing].getSkillAction(deltaTime).getKeyFrame(elapsedTime, true),
                                board.map[11][0].corX - 700 + elapsedTime * (600),
                                board.map[11][0].corY - 50);
                    } else if (actionUsing == 1) {
                        if (enemys.size() == 2) {
                            game.batch.draw(ability[actionUsing].getSkillAction(deltaTime).getKeyFrame(elapsedTime, true),
                                    board.map[(int)enemys.get(0).y][(int)enemys.get(0).x].corX + ability[actionUsing].erLft[0],
                                    board.map[(int)enemys.get(0).y][(int)enemys.get(0).x].corY + ability[actionUsing].erLft[1]);
                            game.batch.draw(ability[actionUsing].getSkillAction(deltaTime).getKeyFrame(elapsedTime, true),
                                    board.map[(int)enemys.get(1).y][(int)enemys.get(1).x].corX + ability[actionUsing].erLft[0],
                                    board.map[(int)enemys.get(1).y][(int)enemys.get(1).x].corY + ability[actionUsing].erLft[1]);
                        }
                        else if (enemys.size() == 1) {
                            game.batch.draw(ability[actionUsing].getSkillAction(deltaTime).getKeyFrame(elapsedTime, true),
                                    board.map[(int)enemys.get(0).y][(int)enemys.get(0).x].corX + ability[actionUsing].erLft[0],
                                    board.map[(int)enemys.get(0).y][(int)enemys.get(0).x].corY + ability[actionUsing].erLft[1]);
                        }
                    } else {
                        //Skill Animation
                        game.batch.draw(ability[actionUsing].getSkillAction(deltaTime).getKeyFrame(elapsedTime, true),
                                erXL, erYL);
                    }
                }
            }
        }
        //Alert Acting after Perform Attack
        else if (actionUsing > -1 && actionUsing < 4 && startTime + deltaTime <= elapsedTime && elapsedTime <=
                startTime + deltaTime + heroAnimation[4].getAnimationDuration() && pain == false && live == true) {
            //Right Acting
            if (facing.compareTo(State.RIGHT) == 0) {
                game.batch.draw(heroAnimation[4].getKeyFrame(elapsedTime, true),
                        coordinates.x + 180f,
                        coordinates.y - 50f,
                        -(heroAnimation[4].getKeyFrame(elapsedTime, true).getRegionWidth()),
                        heroAnimation[4].getKeyFrame(elapsedTime, true).getRegionHeight());
                //Left Acting
            } else {
                game.batch.draw(heroAnimation[4].getKeyFrame(elapsedTime, true),
                        coordinates.x - 120f,
                        coordinates.y - 50f);
            }
            //Into Walk & Stand State
        } else if (live == true && pain == false) {
            //End Skill State Animation
            this.attacking = false;
            this.renderWalking();
            if (carding == false) {
                actionUsing = -1;
            }

        }

        //Using Card
        if (actionUsing > 3 && actionUsing < 9 && elapsedTime < startTime +
                ability[actionUsing].getSkillAction(1f).getAnimationDuration()
                && attacking == false && carding == true && live == true) {
            //set the position error
            erXR = targetCo.x + ability[actionUsing].erRht[0];
            erYR = targetCo.y + ability[actionUsing].erRht[1];

//            System.out.println("Card was used  -> " + actionUsing);

            game.batch.draw(ability[actionUsing].getSkillAction(1f).getKeyFrame(elapsedTime,
                    true),
                    erXR,
                    erYR);
        } else if (actionUsing > 3 && actionUsing < 9 && startTime + deltaTime <= elapsedTime && live == true) {
//            System.out.println("It's the end, actionUsing = " + actionUsing);
            actionUsing = -1;
            carding = false;
        }

        //Pain animation
        if (pain == true || (elapsedTime < attackedTime && live == false)) {
            if (elapsedTime < startTime + attackedTime) {
                //Right Acting
                if (facing.compareTo(State.RIGHT) == 0) {
                    game.batch.draw(heroAnimation[5].getKeyFrame(elapsedTime, true),
                            coordinates.x + 180f,
                            coordinates.y - 50f,
                            -(heroAnimation[4].getKeyFrame(elapsedTime, true).getRegionWidth()),
                            heroAnimation[4].getKeyFrame(elapsedTime, true).getRegionHeight());

                }//Left Acting
                else {
                    game.batch.draw(heroAnimation[5].getKeyFrame(elapsedTime, true),
                            coordinates.x - 120f,
                            coordinates.y - 50f);
                }
            } else {
                pain = false;
            }
        }

        //dead animation
        if (live == false && health == -1 && elapsedTime > attackedTime) {
            //set block that hero stand no obstacle
            board.map[row][col].setObstacle(0);

            if (facing.compareTo(State.RIGHT) == 0) {
                game.batch.draw(heroAnimation[6].getKeyFrame(elapsedTime, true),
                        coordinates.x + (heroAnimation[6].getKeyFrame(elapsedTime, true).getRegionWidth() / 2)
                                + 40f, coordinates.y - 100f + elapsedTime * 100,
                        -(heroAnimation[6].getKeyFrame(elapsedTime, true).getRegionWidth()),
                        heroAnimation[6].getKeyFrame(elapsedTime, true).getRegionHeight());
            } else {
                game.batch.draw(heroAnimation[6].getKeyFrame(elapsedTime, true),
                        coordinates.x - 120f,
                        coordinates.y - 100f + elapsedTime * 100);
            }
        } else if (elapsedTime >= 4 && live == false && health == -1) {
            health = -2;
        }
    }

    //Pain Acting
    public void showPain(float frameDuration) {
        this.resetElapsedTime();
        this.setStartTime();
        pain = true;
    }

    public boolean isHeroPlaying() {
        if (attacking == false && carding == false) {
            return false;
        } else {
            return true;
        }
    }

    public void setFacing(State facing) {
        this.facing = facing;
    }

    public void setCurrentState(State currentState) {
        this.previousState = this.currentState;
        this.currentState = currentState;
    }

    public void setSource(int col, int row) {
        this.src.set(col, row);
    }

    public Vector2 getSource() {
        return src;
    }

    public void setTarget(Vector2 target) {
        this.targetCo = target;
    }

    public void resetElapsedTime() {
        elapsedTime = 0;
    }

    public float getElapsedTime() {
        return elapsedTime;
    }

    public void setStartTime() {
        startTime = elapsedTime;
    }

    public boolean isAvlive() {
        return live;
    }

    public void setAttacking(boolean bool) {
        this.attacking = bool;
    }

    public void setCarding(boolean bool) {
        this.carding = bool;
    }

    public void setAttackedTime(float attackedTime) {
        this.attackedTime = attackedTime;
    }

    public void setEnemys(LinkedList<Vector2> enemys) {
        this.enemys = enemys;
    }

    public LinkedList<Vector2> getEnemys() {
        return enemys;
    }
}
