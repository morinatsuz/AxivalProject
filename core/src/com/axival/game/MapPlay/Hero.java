package com.axival.game.MapPlay;

import com.axival.game.CardPlay;
import com.axival.game.screen.ScreenPlay;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import java.util.Arrays;

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
    public boolean attacking = false;
    public int skillUsing = -1;
    public int cardUsing = -1;
    public Skill[] ability;
    private Texture img;
    private TextureAtlas atlas;
    private Animation<TextureRegion> heroAnimation[];
    private Vector2 coordinates, target, des, src;
    private float frameDuration;
    private float deltaTime;
    private float elapsedTime = 0f;
    private float startTime = 0f;
    private float widthErr = 0;
    private float heightErr = 0;
    private static int walking = 0;
    private CardPlay game;
    private MapScreen screen;
    private Board board;

    private ScreenPlay screenPlay;

    public boolean live = true;
    public int health;

    private int countAction = 0;

    public Hero(CardPlay game, MapScreen screen, Board board, Vector2 vector, int job, String path, ScreenPlay screenPlay) {
        this.job = job;
        this.setAtlas(path);
        this.target = new Vector2(10,10);
        this.screenPlay =screenPlay;
        ability = new Skill[9];
        heroAnimation = new Animation[9];
        frameDuration = 0.2f;
        if (job == 1) {
            health = 200;
            ability[0] = new Skill("skills/DT_Skill_Spritesheet/DT_Skill0_Spritesheet/dt_skill0.atlas",
                    "dt0",0,0,0, false);
            ability[1] = new Skill("skills/DT_Skill_Spritesheet/DT_Skill1_Spritesheet/dt_skill1.atlas",
                    "dt1",0,0,0,false);

            //No effect in 2nd skill
            ability[2] = new Skill("skills/DT_Skill_Spritesheet/DT_Skill3_Spritesheet/dt_skill3.atlas",
                    "dt3",0,0,0, false);

            ability[3] = new Skill("skills/DT_Skill_Spritesheet/DT_Skill3_Spritesheet/dt_skill3.atlas",
                    "dt3",0,0,0, false);
            heroAnimation[0] = new Animation<TextureRegion>(frameDuration, this.atlas.findRegions("swingOF"));
            heroAnimation[1] = new Animation<TextureRegion>(frameDuration, this.atlas.findRegions("fortify"));
            heroAnimation[2] = new Animation<TextureRegion>(frameDuration, this.atlas.findRegions("stabOF"));
            heroAnimation[3] = new Animation<TextureRegion>(frameDuration, this.atlas.findRegions("swingO1"));
        } else if (job == 2) {
            health = 150;
            ability[0] = new Skill("skills/WZ_Skill_Spritesheet/WZ_Skill0_Spritesheet/wz_skill0.atlas",
                    "wz0",0,0,0, false);
            ability[1] = new Skill("skills/WZ_Skill_Spritesheet/WZ_Skill1_Spritesheet/wz_skill1.atlas",
                    "wz1",0,0,0, false);

            //No effect in 2nd skill
            ability[2] = new Skill("skills/WZ_Skill_Spritesheet/WZ_Skill3_Spritesheet/wz_skill3.atlas",
                    "wz3",0,0,0,false);

            ability[3] = new Skill("skills/WZ_Skill_Spritesheet/WZ_Skill3_Spritesheet/wz_skill3.atlas",
                    "wz3",0,0,0, false);
            heroAnimation[0] = new Animation<TextureRegion>(frameDuration, this.atlas.findRegions("swingO1"));
            heroAnimation[1] = new Animation<TextureRegion>(frameDuration, this.atlas.findRegions("swingO2"));
            //Wizard have no 2nd ability.
            heroAnimation[3] = new Animation<TextureRegion>(frameDuration, this.atlas.findRegions("swingO3"));
        } else {
            health = 120;
            ability[0] = new Skill("skills/PR_Skill_Spritesheet/PR_Skill0_Spritesheet/pr_skill0.atlas",
                    "pr0", 0,0,0,false);
            ability[1] = new Skill("skills/PR_Skill_Spritesheet/PR_Skill1_Spritesheet/pr_skill1.atlas",
                    "pr1", 0,0,0, true);
            ability[2] = new Skill("skills/PR_Skill_Spritesheet/PR_Skill2_Spritesheet/pr_skill2.atlas",
                    "pr2", 0,0,0,false);
            ability[3] = new Skill("skills/PR_Skill_Spritesheet/PR_Skill3_Spritesheet/pr_skill3.atlas",
                    "pr3", 0,0,0,false);
            heroAnimation[0] = new Animation<TextureRegion>(frameDuration, this.atlas.findRegions("swingO1"));
            heroAnimation[1] = new Animation<TextureRegion>(frameDuration, this.atlas.findRegions("heal"));
            heroAnimation[2] = new Animation<TextureRegion>(frameDuration, this.atlas.findRegions("swingO2"));
            heroAnimation[3] = new Animation<TextureRegion>(frameDuration, this.atlas.findRegions("stabO1"));
        }

        //card ability define
        ability[4] = new Skill("skills/CD_Skill_Spritesheet/CD_Skill0_Spritesheet/cd_skill0.atlas",
                "cd0",0,0,0,true);
        ability[5] = new Skill("skills/CD_Skill_Spritesheet/CD_Skill1_Spritesheet/cd_skill1.atlas",
                "cd1",0,0,0,true);
        ability[6] = new Skill("skills/CD_Skill_Spritesheet/CD_Skill2_Spritesheet/cd_skill2.atlas",
                "cd2",0,0,0,true);
        ability[8] = new Skill("skills/CD_Skill_Spritesheet/CD_Skill3_Spritesheet/cd_skill3.atlas",
                "cd3",0,0,0,true);
        ability[7] = new Skill("skills/CD_Skill_Spritesheet/CD_Skill4_Spritesheet/cd_skill4.atlas",
                "cd4",0,0,0,true);
        heroAnimation[4] = new Animation<TextureRegion>(1 / 2f, this.atlas.findRegions("alert"));
        heroAnimation[5] = new Animation<TextureRegion>(1 / 2f, this.atlas.findRegions("pain"));
        heroAnimation[6] = new Animation<TextureRegion>(1 / 2f, this.atlas.findRegions("dead"));
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
        if (health < 0 && live == true) {
            health = 0;
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

    public Vector2 getCoordinates() {
        return this.coordinates;
    }

    public Vector2 getDes() {
        return this.des;
    }

    public Vector2 getRowCol() {
        return new Vector2(this.col, this.row);
    }

    public Vector2 getSrc() {
        return this.src;
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
        float errR = 0;
        float errL = 0;
        if (job == 1) {
            errL = -22f;
        }
        if (job == 2) {
            errR = 5f;
            errL = 10f;
        }
        if (job == 3) {
            errR = -10f;
            errL = -2f;
        }

        //Hero Action
        if (facing.compareTo(Hero.State.RIGHT) == 0) {
            game.batch.draw(this.walkAction().getKeyFrame(elapsedTime, true),
                    this.getCoordinates().x + (this.walkAction().getKeyFrame(elapsedTime, true).getRegionWidth()) + errR,
                    this.getCoordinates().y,
                    -(this.walkAction().getKeyFrame(elapsedTime, true).getRegionWidth()),
                    this.walkAction().getKeyFrame(elapsedTime, true).getRegionHeight());
        } else {
            game.batch.draw(this.walkAction().getKeyFrame(elapsedTime, true),
                    this.getCoordinates().x + errL, this.getCoordinates().y);
        }
    }

    public void useSkill() {
        if (skillUsing > -1 && skillUsing < 4 && elapsedTime < startTime + heroAnimation[skillUsing].getAnimationDuration() &&
                attacking == true && live == true) {
            float corX;
            float corY;
            if (ability[skillUsing].target == true) {
                corX = target.x;
                corY = target.y;
            }
            else {
                corX = coordinates.x + widthErr;
                corY = coordinates.y + heightErr;
            }
            deltaTime = heroAnimation[skillUsing].getAnimationDuration();
            if (facing.compareTo(State.RIGHT) == 0) {
                game.batch.draw(heroAnimation[skillUsing].getKeyFrame(elapsedTime, true),
                        coordinates.x,
                        coordinates.y,
                        -(heroAnimation[skillUsing].getKeyFrame(elapsedTime, true).getRegionWidth()),
                        heroAnimation[skillUsing].getKeyFrame(elapsedTime, true).getRegionHeight());
                game.batch.draw(ability[skillUsing].getSkillAction(deltaTime).getKeyFrame(elapsedTime, true),
                        corX, corY,
                        -(ability[skillUsing].getSkillAction(deltaTime).getKeyFrame(elapsedTime, true).getRegionWidth()),
                        ability[skillUsing].getSkillAction(deltaTime).getKeyFrame(elapsedTime, true).getRegionHeight());
            } else {
                game.batch.draw(heroAnimation[skillUsing].getKeyFrame(elapsedTime, true), coordinates.x, coordinates.y);
                game.batch.draw(ability[skillUsing].getSkillAction(deltaTime).getKeyFrame(elapsedTime, true),
                        corX, corY);
            }
        } else if (skillUsing > -1 && skillUsing < 4 && startTime + deltaTime <= elapsedTime && elapsedTime <=
                startTime + deltaTime + heroAnimation[4].getAnimationDuration() && live == true) {
            if (facing.compareTo(State.RIGHT) == 0) {
                game.batch.draw(heroAnimation[4].getKeyFrame(elapsedTime, true),
                        coordinates.x + heroAnimation[4].getKeyFrame(elapsedTime, true).getRegionWidth(),
                        coordinates.y,
                        -(heroAnimation[4].getKeyFrame(elapsedTime, true).getRegionWidth()),
                        heroAnimation[4].getKeyFrame(elapsedTime, true).getRegionHeight());
            } else {
                game.batch.draw(heroAnimation[4].getKeyFrame(elapsedTime, true),
                        coordinates.x - 30,
                        coordinates.y);
            }
        } else if (live == true) {
            skillUsing = -1;
            attacking = false;
            this.renderWalking();
        }

        //use card active

        /*if(screenPlay.statusPhase[8]>0 && countIn==0) {
            resetElapsedTime();
            setStartTime();
            cardUsing = screenPlay.statusPhase[8];
            System.out.println("cardUsing statusphase: "+cardUsing+ " : "+ Arrays.toString(screenPlay.statusPhase));*/
           // screenPlay.statusPhase[8] = 0;
            //System.out.println("cardUsing : " + cardUsing);
        //System.out.println("cardUsing statusphase: "+cardUsing+ " : "+ Arrays.toString(screenPlay.statusPhase));

        /*if (skillUsing > 3 && skillUsing  < 9 && elapsedTime < startTime +
                    ability[cardUsing].getSkillAction(1f).getAnimationDuration()
                    && live == true) {
                //cardUsing = screenPlay.statusPhase[8]-1;
                //screenPlay.statusPhase[6] = 0;
                game.batch.draw(ability[cardUsing+4].getSkillAction(1f).getKeyFrame(elapsedTime, true),
                        board.map[(int) target.y][(int) target.x].corX,
                        board.map[(int) target.y][(int) target.x].corY);
                System.out.println("drw skill"+cardUsing);
            } else if (skillUsing > 3 && skillUsing < 9 && startTime + deltaTime <= elapsedTime && live == true) {
                skillUsing = -1;
                attacking = false;
            }*/
        //}

        //Pain animation


        //dead animation
        if (elapsedTime < 5 && live == false && health == 0) {
            game.batch.draw(heroAnimation[6].getKeyFrame(elapsedTime, true),
                    coordinates.x + elapsedTime,
                    coordinates.y + elapsedTime * 100);
        }
        else if (elapsedTime >= 5 && live == false && health == 0) {
            health = -1;
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
        this.target = target;
    }

    public void resetElapsedTime() {
        elapsedTime = 0;
    }

    public void setStartTime() {
        startTime = elapsedTime;
    }

    public boolean isAvlive() {
        return live;
    }
}
