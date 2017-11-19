package com.axival.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;

public class SoundManager {
    //define variable
    private Music musicMenu, musicCharacter, musicGameplayNormal, musicGameplayCritical, musicLoading, musicVictory, musicDefeate;
    private Music fxClickCard, fxSelectCharacter, fxSelected;
    private boolean status1=true, status2=true;

    public SoundManager(){
        //set Music assets in variable
        this.musicMenu =  Gdx.audio.newMusic(Gdx.files.internal("sound/bgm/1.Menu bgm.ogg"));
        this.musicCharacter = Gdx.audio.newMusic(Gdx.files.internal("sound/bgm/2.Character select - bgm (20sec).ogg"));
        this.musicGameplayNormal = Gdx.audio.newMusic(Gdx.files.internal("sound/bgm/3.Gameplay bgm(Witcher3).ogg"));
        this.musicGameplayCritical = Gdx.audio.newMusic(Gdx.files.internal("sound/bgm/4.Gameplay - Critical sound.ogg"));
        this.fxClickCard = Gdx.audio.newMusic(Gdx.files.internal("sound/fx/Game play - click card.ogg")); //main click
        this.fxSelectCharacter = Gdx.audio.newMusic(Gdx.files.internal("sound/fx/select character - click card.ogg")); //select card
        this.fxSelected = Gdx.audio.newMusic(Gdx.files.internal("sound/fx/Select character - selected.ogg")); //count down
        this.musicVictory = Gdx.audio.newMusic(Gdx.files.internal("sound/bgm/6.Victory BGM.ogg"));
        this.musicDefeate = Gdx.audio.newMusic(Gdx.files.internal("sound/bgm/5.Defeated - Ash - The Secession Studios (mp3cut.net).ogg"));
        this.musicLoading = Gdx.audio.newMusic(Gdx.files.internal("sound/bgm/Original - UI (mp3cut.net).ogg"));

        //set volume music
        musicMenu.setVolume(.2f);
        musicCharacter.setVolume(.2f);
        musicGameplayNormal.setVolume(.2f);
        musicGameplayCritical.setVolume(.2f);
        musicLoading.setVolume(6f);
    }

    //play select Bgm
    public void playBgm(int statusSound){
        //status sound => 0=menu, 1=character, 2=gameplay1, 3=gameplay2, 4=loading, 5=victory, 6=defeat
        if(status1) {
            if (statusSound == 0) {
                musicMenu.play();
                musicMenu.setLooping(true);
            } else if (statusSound == 1) {
                musicCharacter.play();
                musicCharacter.setLooping(true);
            } else if (statusSound == 2) {
                musicGameplayNormal.play();
                musicGameplayNormal.setLooping(true);
            } else if (statusSound == 3) {
                musicGameplayCritical.play();
                musicGameplayCritical.setLooping(true);
            } else if (statusSound == 4) {
                musicLoading.play();
                musicLoading.setLooping(true);
            } else if (statusSound == 5) {
                musicVictory.play();
                //musicVictory.setLooping(true);
            } else if (statusSound == 6) {
                musicDefeate.play();
                //musicDefeate.setLooping(true);
            }
        }
    }

    //pause select Bgm
    public void pauseBgm(int statusSound){
        //status sound => 0=menu, 1=character, 2=gameplay1, 3=gameplay2, 4=loading, 5=victory, 6=defeat
        if(statusSound==0) {
            musicMenu.pause();
        }
        else if(statusSound==1) {
            musicCharacter.pause();
        }
        else if(statusSound==2) {
            musicGameplayNormal.pause();
        }
        else if(statusSound==3) {
            musicGameplayCritical.pause();
        }
        else if(statusSound==4) {
            musicLoading.pause();
        }
        else if(statusSound==5) {
            musicVictory.pause();
        }
        else if(statusSound==6) {
            musicDefeate.pause();
        }
    }

    //stop select Bgm
    public void stopBgm(int statusSound){
        //status sound => 0=menu, 1=character, 2=gameplay1, 3=gameplay2, 4=loading, 5=victory, 6=defeat
        if(statusSound==0) {
            musicMenu.stop();
        }
        else if(statusSound==1) {
            musicCharacter.stop();
        }
        else if(statusSound==2) {
            musicGameplayNormal.stop();
        }
        else if(statusSound==3) {
            musicGameplayCritical.stop();
        }
        else if(statusSound==4) {
            musicLoading.stop();
        }
        else if(statusSound==5) {
            musicVictory.stop();
        }
        else if(statusSound==6) {
            musicDefeate.stop();
        }
    }

    //stop all Bgm and set Status Bgm to off
    public void stopBgmAll(boolean check){
        if(check){
            musicMenu.stop();
            musicCharacter.stop();
            musicGameplayNormal.stop();
            musicGameplayCritical.stop();
            musicLoading.stop();
            musicVictory.stop();
            musicDefeate.stop();
            status1 = false;
        }
        else {
            status1 = true;
        }
    }

    //play select sfx
    public void playSfx(int statusSound){
        if(status2) {
            if (statusSound == 0) {
                fxClickCard.play();
            } else if (statusSound == 1) {
                fxSelectCharacter.play();
            } else if (statusSound == 2) {
                fxSelected.play();
            }
        }
    }

    //pause select sfx
    public void pauseSfx(int statusSound){
        if(statusSound==0) {
            fxClickCard.pause();
        }
        else if(statusSound==1) {
            fxSelectCharacter.pause();
        }
        else if(statusSound==2) {
            fxSelected.pause();
        }
    }

    //stop select sfx
    public void stopSfx(int statusSound){
        if(statusSound==0) {
            fxClickCard.stop();
        }
        else if(statusSound==1) {
            fxSelectCharacter.stop();
        }
        else if(statusSound==2) {
            fxSelected.stop();
        }
    }

    //stop all sfx and set status sfx to off
    public void stopSfxAll(boolean check){
        if(check) {
            fxClickCard.stop();
            fxSelectCharacter.stop();
            fxSelected.stop();
            status2 = false;
        }
        else {
            status2 = true;
        }
    }

    //check bgm from select
    public boolean checkMusicStatusBgm(int statusSound){
        if(statusSound==0) {
            return musicMenu.isPlaying();
        }
        else if(statusSound==1) {
            return musicCharacter.isPlaying();
        }
        else if(statusSound==2) {
            return musicGameplayNormal.isPlaying();
        }
        else if(statusSound==3) {
            return musicGameplayCritical.isPlaying();
        }
        return false;
    }

    //check sfx from select
    public boolean checkMusicStatusSfx(int statusSound){
        if(statusSound==0) {
            return fxClickCard.isPlaying();
        }
        else if(statusSound==1) {
            return fxSelectCharacter.isPlaying();
        }
        else if(statusSound==2) {
            return fxSelected.isPlaying();
        }
        return false;
    }

    //check status sfx all
    public boolean checkMusicStatusSfxAll(){
        return status2;
    }

    public boolean checkMusicStatusBgmAll(){
        return status1;
    }
}
