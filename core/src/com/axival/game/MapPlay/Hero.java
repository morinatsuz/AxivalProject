package com.axival.game.MapPlay;

import com.axival.game.CardPlay;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

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
    float[] errX;
    float[] errY;
    private static int walking = 0;
    private CardPlay game;
    private MapScreen screen;
    private Board board;

    public boolean live = true;
    public int health;

    public Hero(CardPlay game, MapScreen screen, Board board, Vector2 vector, int job, String path) {
        this.job = job;
        this.setAtlas(path);
        this.target = new Vector2(10,10);
        ability = new Skill[9];
        heroAnimation = new Animation[9];
        frameDuration = 0.3f;
        if (job == 1) {
            health = 35;
            ability[0] = new Skill("skills/DT_Skill_Spritesheet/DT_Skill0_Spritesheet/dt_skill0.atlas",
                    "dt0",0,0,0, true);
            ability[1] = new Skill("skills/DT_Skill_Spritesheet/DT_Skill1_Spritesheet/dt_skill1.atlas",
                    "dt1",0,0,0,true);
            ability[2] = new Skill("skills/DT_Skill_Spritesheet/DT_Skill2_Spritesheet/dt_skill2.atlas",
                    "dt2",0,0,0, false);
            ability[3] = new Skill("skills/DT_Skill_Spritesheet/DT_Skill3_Spritesheet/dt_skill3.atlas",
                    "dt3",0,0,0, false);
            heroAnimation[0] = new Animation<TextureRegion>(0.2f, this.atlas.findRegions("swingOF"));
            heroAnimation[1] = new Animation<TextureRegion>(0.3f, this.atlas.findRegions("fortify"));
            heroAnimation[2] = new Animation<TextureRegion>(0.25f, this.atlas.findRegions("stabOF"));
            heroAnimation[3] = new Animation<TextureRegion>(0.3f, this.atlas.findRegions("swingO1"));
        }
        else if (job == 2) {
            health = 30;
            ability[0] = new Skill("skills/WZ_Skill_Spritesheet/WZ_Skill0_Spritesheet/wz_skill0.atlas",
                    "wz0",0,0,0, true);
            ability[1] = new Skill("skills/WZ_Skill_Spritesheet/WZ_Skill1_Spritesheet/wz_skill1.atlas",
                    "wz1",0,0,0, false);
            ability[2] = new Skill("skills/WZ_Skill_Spritesheet/WZ_Skill2_Spritesheet/wz_skill2.atlas",
                    "wz2",0,0,0,false);
            ability[3] = new Skill("skills/WZ_Skill_Spritesheet/WZ_Skill3_Spritesheet/wz_skill3.atlas",
                    "wz3",0,0,0, false);
            heroAnimation[0] = new Animation<TextureRegion>(frameDuration, this.atlas.findRegions("swingO1"));
            heroAnimation[1] = new Animation<TextureRegion>(frameDuration, this.atlas.findRegions("swingO2"));
            heroAnimation[2] = new Animation<TextureRegion>(frameDuration, this.atlas.findRegions("stand1"));
            heroAnimation[3] = new Animation<TextureRegion>(frameDuration, this.atlas.findRegions("swingO3"));
        }
        else {
            health = 25;
            ability[0] = new Skill("skills/PR_Skill_Spritesheet/PR_Skill0_Spritesheet/pr_skill0.atlas",
                    "pr0", 0,0,0,true);
            ability[1] = new Skill("skills/PR_Skill_Spritesheet/PR_Skill1_Spritesheet/pr_skill1.atlas",
                    "pr1", 0,0,0, true);
            ability[2] = new Skill("skills/PR_Skill_Spritesheet/PR_Skill2_Spritesheet/pr_skill2.atlas",
                    "pr2", 0,0,0,false);
            ability[3] = new Skill("skills/PR_Skill_Spritesheet/PR_Skill3_Spritesheet/pr_skill3.atlas",
                    "pr3", 0,0,0,true);
            heroAnimation[0] = new Animation<TextureRegion>(frameDuration, this.atlas.findRegions("swingO1"));
            heroAnimation[1] = new Animation<TextureRegion>(frameDuration, this.atlas.findRegions("heal"));
            heroAnimation[2] = new Animation<TextureRegion>(frameDuration, this.atlas.findRegions("swingO2"));
            heroAnimation[3] = new Animation<TextureRegion>(frameDuration, this.atlas.findRegions("stabO1"));
        }
        ability[4] = new Skill("skills/CD_Skill_Spritesheet/CD_Skill0_Spritesheet/cd_skill0.atlas",
                "cd0",0,0,0,true);
        ability[5] = new Skill("skills/CD_Skill_Spritesheet/CD_Skill1_Spritesheet/cd_skill1.atlas",
                "cd1",0,0,0,true);
        ability[6] = new Skill("skills/CD_Skill_Spritesheet/CD_Skill2_Spritesheet/cd_skill2.atlas",
                "cd2",0,0,0,true);
        ability[7] = new Skill("skills/CD_Skill_Spritesheet/CD_Skill3_Spritesheet/cd_skill3.atlas",
                "cd3",0,0,0,true);
        ability[8] = new Skill("skills/CD_Skill_Spritesheet/CD_Skill4_Spritesheet/cd_skill4.atlas",
                "cd4",0,0,0,true);
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
//        errY[1] = -50f;
//        if (job == 1) {
//            errX[0] = -120f;
//            errY[0] = -50f;
//            errX[1] = -120f;
//            errY[1] = -50f;
//        }
//        if (job == 2) {
//            errX[0] = -120f;
//            errY[0] = -50f;
//            errX[1] = -120f;
//            errY[1] = -50f;
//        }
//        if (job == 3) {
//            errX[0] = -120f;
//            errY[0] = -50f;
//            errX[1] = -120f;
//            errY[1] = -50f;
//        }

        //Hero Action
        if (facing.compareTo(Hero.State.RIGHT) == 0) {
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
        if (skillUsing > -1 && skillUsing < 4 && elapsedTime < startTime + heroAnimation[skillUsing].getAnimationDuration()
                && attacking == true && live == true)
        {





            deltaTime = heroAnimation[skillUsing].getAnimationDuration();
            //Right Acting
            if (facing.compareTo(State.RIGHT) == 0)
            {
                //Hero Animation
                game.batch.draw(heroAnimation[skillUsing].getKeyFrame(elapsedTime, true),
                        coordinates.x + 170f,
                        coordinates.y - 50f,
                        -(heroAnimation[skillUsing].getKeyFrame(elapsedTime, true).getRegionWidth()),
                        heroAnimation[skillUsing].getKeyFrame(elapsedTime, true).getRegionHeight());
                //Skill Animation
                game.batch.draw(ability[skillUsing].getSkillAction(deltaTime).getKeyFrame(elapsedTime, true),
                        coordinates.x + ability[skillUsing].getSkillAction(deltaTime).getKeyFrame(elapsedTime,
                                true).getRegionWidth()/2 + 80,
                        coordinates.y,
                        -(ability[skillUsing].getSkillAction(deltaTime).getKeyFrame(elapsedTime, true).getRegionWidth()),
                        ability[skillUsing].getSkillAction(deltaTime).getKeyFrame(elapsedTime, true).getRegionHeight());
            }
            //Left Acting
            else
            {
                //Hero Animation
                game.batch.draw(heroAnimation[skillUsing].getKeyFrame(elapsedTime, true),
                        coordinates.x - 120f,
                        coordinates.y - 50f);
                //Skill Animation
                game.batch.draw(ability[skillUsing].getSkillAction(deltaTime).getKeyFrame(elapsedTime, true),
                        coordinates.x - (ability[skillUsing].getSkillAction(deltaTime).getKeyFrame(elapsedTime,
                                true).getRegionWidth()/2 + 20),
                        coordinates.y);
            }
        }
        //Alert Acting after Perform Attack
        else if (skillUsing > -1 && skillUsing < 4 && startTime + deltaTime <= elapsedTime && elapsedTime <=
                startTime + deltaTime + heroAnimation[4].getAnimationDuration() && live == true)
        {
            //Right Acting
            if (facing.compareTo(State.RIGHT) == 0)
            {
                game.batch.draw(heroAnimation[4].getKeyFrame(elapsedTime, true),
                        coordinates.x + 180f,
                        coordinates.y - 50f,
                        -(heroAnimation[4].getKeyFrame(elapsedTime, true).getRegionWidth()),
                        heroAnimation[4].getKeyFrame(elapsedTime, true).getRegionHeight());
                //Left Acting
            }
            else
            {
                game.batch.draw(heroAnimation[4].getKeyFrame(elapsedTime, true),
                        coordinates.x - 120f,
                        coordinates.y - 50f);
            }
            //Into Walk & Stand State
        }
        else if (live == true)
        {
            skillUsing = -1;
            attacking = false;
            this.renderWalking();
            screen.statusPhase[6] = 1;
        }

        //using Card
        if (cardUsing > -1 && skillUsing == -1 && elapsedTime < startTime +
                ability[cardUsing].getSkillAction(1f).getAnimationDuration()
                && live == true)
        {
            game.batch.draw(ability[cardUsing+4].getSkillAction(1f).getKeyFrame(elapsedTime, true),
                    board.map[(int)target.y][(int)target.x].corX,
                    board.map[(int)target.y][(int)target.x].corY);
        }
        else if (skillUsing == -1 && startTime + deltaTime <= elapsedTime && live == true)
        {
            cardUsing = -1;
            attacking = false;
        }

        //Pain animation


        //dead animation
        if (elapsedTime < 5 && live == false && health == -1)
        {
            if (facing.compareTo(State.RIGHT) == 0)
            {
                game.batch.draw(heroAnimation[6].getKeyFrame(elapsedTime, true),
                        coordinates.x + ( heroAnimation[6].getKeyFrame(elapsedTime, true).getRegionWidth()/2)
                        + 40f, coordinates.y - 50f + elapsedTime* 100,
                        -( heroAnimation[6].getKeyFrame(elapsedTime, true).getRegionWidth()),
                        heroAnimation[6].getKeyFrame(elapsedTime, true).getRegionHeight());
            }
            else
            {
                game.batch.draw(heroAnimation[6].getKeyFrame(elapsedTime, true),
                        coordinates.x - 120f,
                        coordinates.y - 50f + elapsedTime * 100);
            }
        }
        else if (elapsedTime >= 5 && live == false && health == -1)
        {
            health = -2;
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
