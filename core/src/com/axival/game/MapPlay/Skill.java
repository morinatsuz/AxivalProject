package com.axival.game.MapPlay;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;

public class Skill {
    private String name;
    private int frame;
    private  float elapsetime=0f;
    public float[] erXY;
    public boolean target;
    private TextureAtlas atlas;
    private TextureAtlas.AtlasRegion texture;
    private Animation<TextureRegion> animation;
    public Skill(String atlasPath, String name, float xLft, float xRht, float erY, boolean target) {
        this.erXY = new float[3];
        this.erXY[0] = xLft;
        this.erXY[1] = xRht;
        this.erXY[2] = erY;
        this.target = target;
        this.name = name;
        this.atlas = new TextureAtlas(atlasPath);
        this.frame = atlas.getRegions().size;
    }

    public void setAtlas(String path) {
        atlas = new TextureAtlas(path);
    }

    public Animation<TextureRegion> getSkillAction(float deltaTime) {
        Array<TextureAtlas.AtlasRegion> array = atlas.findRegions(name);
        animation = new Animation<TextureRegion>((float)deltaTime/frame, array);
        return animation;
    }

    public void update(float delta) {
        this.elapsetime += delta;
        if (elapsetime > 100) { elapsetime = 0;}
    }
}
