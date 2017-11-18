package com.axival.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;

public class SoundManager {
    private Music musicBg1, musicBg2, sfx;
    public SoundManager(){
        this.musicBg1 =  Gdx.audio.newMusic(Gdx.files.internal("sound/bgm/bgChase.ogg"));
        this.musicBg2 = Gdx.audio.newMusic(Gdx.files.internal("sound/bgm/bgFantasy.ogg"));
        this.sfx = Gdx.audio.newMusic(Gdx.files.internal("sound/fx/Draw.ogg"));
    }
    public void playBgm(int statusSound){
        if(statusSound==0) {
            musicBg1.play();
        }
        else if(statusSound==1) {
            musicBg2.play();
        }
    }
    public void pauseBgm(int statusSound){
        if(statusSound==0) {
            musicBg1.pause();
        }
        else if(statusSound==1) {
            musicBg2.pause();
        }
    }
    public void stopBgm(int statusSound){
        if(statusSound==0) {
            musicBg1.stop();
        }
        else if(statusSound==1) {
            musicBg2.stop();
        }
    }
    public void playSfx(int statusSound){
        if(statusSound==0) {
            sfx.play();
        }
        else if(statusSound==1) {
            //code sfx2
        }
    }
    public void pauseSfx(int statusSound){
        if(statusSound==0) {
            sfx.pause();
        }
        else if(statusSound==1) {
            //code sfx2
        }
    }
    public void stopSfx(int statusSound){
        if(statusSound==0) {
            sfx.stop();
        }
        else if(statusSound==1) {
            //code sfx2
        }
    }
    public boolean checkMusicStatusBgm(int statusSound){
        if (statusSound==0){
            return musicBg1.isPlaying();
        }
        else if (statusSound==1){
            return musicBg2.isPlaying();
        }
        return false;
    }
    public boolean checkMusicStatusSfx(int statusSound){
        if (statusSound==0){
            return sfx.isPlaying();
        }
        else if (statusSound==1){
            return false;
        }
        return false;
    }
}
