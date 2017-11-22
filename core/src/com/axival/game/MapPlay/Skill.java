package com.axival.game.MapPlay;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;

public class Skill {
    private String name;
    private int frame;
    private int job;
    private  float elapsetime=0f;
    public float[] erRht;
    public float[] erLft;
    public boolean target;
    private TextureAtlas atlas;
    private TextureAtlas.AtlasRegion texture;
    private Animation<TextureRegion> animation;
    private MapScreen screen;
    public Skill(String atlasPath, String name, int job, boolean target, MapScreen screen) {
        this.erRht = new float[2];
        this.erLft = new float[2];
        this.target = target;
        this.name = name;
        this.job = job;
        this.atlas = new TextureAtlas(atlasPath);
        this.frame = atlas.getRegions().size;
        erRht[0] = 0;
        erRht[1] = 0;
        erLft[0] = 0;
        erLft[1] = 0;
        this.screen = screen;
    }

    public void setAtlas(String path) {
        atlas = new TextureAtlas(path);
    }

    public Animation<TextureRegion> getSkillAction(float deltaTime) {
        float frameDuration = deltaTime/frame;
        Array<TextureAtlas.AtlasRegion> array = atlas.findRegions(name);
        animation = new Animation<TextureRegion>(frameDuration, array);
        this.setError();
        return animation;
    }

    public void update(float delta) {
        this.elapsetime += delta;
        if (elapsetime > 100) { elapsetime = 0;}

    }

    public void setError() {
        float width = animation.getKeyFrame(screen.player[screen.idx].getElapsedTime()).getRegionWidth();
        float height = animation.getKeyFrame(screen.player[screen.idx].getElapsedTime()).getRegionHeight();
        if (job == 0) {
            if (name == "cd0") {
                erRht[0] = -width/2;
                erRht[1] = -height/4;
            }
            else if (name == "cd1") {
                erRht[0] = -width/2;
                erRht[1] = -height/4;
            }
            else if (name == "cd2") {
                erRht[0] = -width/2 + 10;
                erRht[1] = - 10;
            }
            else if (name == "cd3") {
                erRht[0] = -width/2;
                erRht[1] = -height/4 + 20;
            }
            else {
                erRht[0] = -width/2;
                erRht[1] = 0;
            }
        }
        else if (job == 1) {
            if (name == "dt0") {
                erRht[0] = width/2-20; //right(width/2+40, 0)
                erRht[1] = height/2-20;
                erLft[0] = -width/2+5;//left(-(width/2-40), 0)
                erLft[1] = height/2-20;
            }
            else if (name == "dt1") {
                erRht[0] = width/2 - 5; //right(wight/2 + 40, -12)
                erRht[1] = + 10;
                erLft[0] = -width/2-10; // left(-(width/2 - 20), -12)
                erLft[1] = height/8;
            }
            else if (name == "dt2") {
                erRht[0] = width/2 + 30; //right(width/2 + 30, -85)
                erRht[1] = -85;
                erLft[0] = -(width-165); //left(-(width-30), -85)
                erLft[1] = -85;
            }
            else {
                erRht[0] = width/2+80; //right(width/2+80,-100)
                erRht[1] = -100;
                erLft[0] = -(width/2 + 20); //left(-(width/2 + 20), -85)
                erLft[1] = -100;
            }
        }
        else if (job ==2) {
            if (name == "wz0") {
                erRht[0] = width/2 - 10; //right(width/2 + 20, -(height/2-40))
                erRht[1] = -height/2 + 60;
                erLft[0] = -width/2; //left(-(width/2 - 30),  -(height/2-40))
                erLft[1] = -height/2 + 60;
            }
            else if (name == "wz1") {
                erRht[0] = width/2 + 30; //right(width/2 + 30, -100)
                erRht[1] = -100;
                erLft[0] = -width/2 + 35; //left(-(width/2 - 35), -100)
                erLft[1] = -100;
            }
            else if (name == "wz2") {
                erRht[0] = width/2 + 20; //right(width/2 + 20, -80)
                erRht[1] = -80;
                erLft[0] = -(width/2 - 40); //left(-(width/2 - 40), -80)
                erLft[1] = -80;
            }
            else {
                erRht[0] = width/2; //right(width/2, -50)
                erRht[1] = -50;
                erLft[0] = -(width/2); //left(-(width/2),-50)
                erLft[1] = -50;
            }
        }
        else {
            if (name == "pr0") {
                erRht[0] = width/2; //right(width/2+30,-50)
                erRht[1] = -40;
                erLft[0] = -(width/2); //left(-(width/2 - 40), -50)
                erLft[1] = -40;
            }
            else if (name == "pr1") {
                erRht[0] = width/2 + 10; //right(width/2 + 35, -50)
                erRht[1] = -40;
                erLft[0] = -(width/2 - 10); //  left(-(width/2 - 35), -50)
                erLft[1] = -40;
            }
            else if (name == "pr2") {
                erRht[0] = width/2 + 20; //right(width/2 + 20, -80)
                erRht[1] = -80;
                erLft[0] = -(width/2 - 30); //left(-(width/2 - 30), -80)
                erLft[1] = -80;
            }
            else {
                erRht[0] = width/2-10; //right(width/2 + 25, -70)
                erRht[1] = -40;
                erLft[0] = -(width/2 - 10); //left(-(width/2 - 35), -70)
                erLft[1] = -40;
            }
        }
    }
}
